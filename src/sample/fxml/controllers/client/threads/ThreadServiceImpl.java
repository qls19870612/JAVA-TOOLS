package sample.fxml.controllers.client.threads;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Singleton;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import game.service.IThreadService;
import game.service.SafeScheduledExecutorService;
import game.sink.util.CommonUtils;
import game.sink.util.concurrent.DisruptorExecutor;
import game.sink.util.concurrent.PaddedAtomicInteger;

@Singleton
public class ThreadServiceImpl implements IThreadService {

    @VisibleForTesting
    final DisruptorExecutor[] gameExec;

    @VisibleForTesting
    final ExecutorService dbExec;

    final ExecutorService fastDbExec;

    private final SafeScheduledExecutorService scheduledExec;

    @VisibleForTesting
    final int GAME_THREAD_COUNT_TO_MOD;

    private final DisruptorExecutor[] gameExecArrayCopy;

    private final Set<Thread> gameExecSet;

    public ThreadServiceImpl() {
        // game exec
        int threadCount = CommonUtils.getClosestPowerOf2(CommonUtils.CORE_NUM) * 2;

        if (threadCount <= 0) {
            throw new RuntimeException("core num * 2 <= 0 ?");
        }

        GAME_THREAD_COUNT_TO_MOD = threadCount - 1;

        this.gameExecSet = new HashSet<>(threadCount * 2);

        gameExec = new DisruptorExecutor[threadCount];

        for (int i = 0; i < gameExec.length; i++) {
            gameExec[i] = new DisruptorExecutor(i, 8192, "GAME_WORKER_" + i);
            this.gameExecSet.add(gameExec[i].getThread());
        }

        gameExecArrayCopy = Arrays.copyOf(gameExec, gameExec.length);

        // db exec
        int dbThreadCount = threadCount;

        dbExec =
                new ThreadPoolExecutor(dbThreadCount, dbThreadCount, 60L, TimeUnit.SECONDS, new LinkedTransferQueue<Runnable>(), new ThreadFactory() {

                    private final PaddedAtomicInteger idCounter = new PaddedAtomicInteger();

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread result = new Thread(r, "DB_WORKER_" + idCounter.incrementAndGet());
                        result.setPriority(Thread.NORM_PRIORITY);
                        return result;
                    }
                });

        fastDbExec =
                new ThreadPoolExecutor(dbThreadCount, dbThreadCount, 60L, TimeUnit.SECONDS, new LinkedTransferQueue<Runnable>(), new ThreadFactory() {

                    private final PaddedAtomicInteger idCounter = new PaddedAtomicInteger();

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread result = new Thread(r, "FAST_DB_WORKER_" + idCounter.incrementAndGet());
                        result.setPriority(Thread.MAX_PRIORITY);
                        return result;
                    }
                });

        // scheduled exec
        int scheduledThreadCount = 8;

        ScheduledThreadPoolExecutor scheduledExec = new ScheduledThreadPoolExecutor(scheduledThreadCount, new ThreadFactory() {

            private final PaddedAtomicInteger idCounter = new PaddedAtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread result = new Thread(r, "UPDATE_WORKER_" + idCounter.incrementAndGet());
                result.setPriority(Thread.MAX_PRIORITY);
                return result;
            }
        });
        scheduledExec.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);

        this.scheduledExec = new SafeScheduledExecutorService(scheduledExec);
    }

    /**
     * 返回是否是英雄线程
     * @param thread
     * @return
     */
    public boolean isGameThread(Thread thread) {
        return gameExecSet.contains(thread);
    }

    @Override
    public DisruptorExecutor[] getExecutorsArrayCopy() {
        return gameExecArrayCopy;
    }

    /*
     * (non-Javadoc)
     *
     * @see app.game.service.ThreadService#getExecutor(int)
     */
    @Override
    public DisruptorExecutor getExecutor(int id) {
        return gameExec[id & GAME_THREAD_COUNT_TO_MOD];
    }

    /*
     * (non-Javadoc)
     *
     * @see app.game.service.ThreadService#getDbExecutor()
     */
    @Override
    public ExecutorService getDbExecutor() {
        return dbExec;
    }

    @Override
    public ExecutorService getFastDbExecutor() {
        return fastDbExec;
    }

    @Override
    public SafeScheduledExecutorService getScheduledExecutorService() {
        return scheduledExec;
    }

    /*
     * (non-Javadoc)
     *
     * @see app.game.service.ThreadService#shutDown()
     */
    @Override
    public void close() {
        // 关闭顺序必须是schedule, gameExec, dbExec
        // schedule可能会放任务到gameExec
        // gameExec可能会放任务到dbExec
        shutDownUpdateExec();
        shutDownGameExec();
        shutDownDbExec();
    }

    void shutDownUpdateExec() {
        scheduledExec.getService().shutdown();

        try {
            scheduledExec.getService().awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            System.err.println("关闭updateExec时出错");
            e.printStackTrace();
        }
    }

    void shutDownGameExec() {
        for (DisruptorExecutor exec : gameExec) {
            exec.shutdown();
        }
    }

    void shutDownDbExec() {
        dbExec.shutdown();
        try {
            dbExec.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            System.err.println("关闭dbExec时出错");
            e.printStackTrace();
        }

        fastDbExec.shutdown();
        try {
            fastDbExec.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            System.err.println("关闭fastDbExec时出错");
            e.printStackTrace();
        }
    }
}
