package sample.fxml.controllers.gm;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

import game.service.SafeScheduledExecutorService;
import game.sink.util.concurrent.PaddedAtomicInteger;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 16:29
 */
public class TimeService {
    public SafeScheduledExecutorService getScheduledExec() {
        return scheduledExec;
    }

    private final SafeScheduledExecutorService scheduledExec;

    public TimeService() {
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
}
