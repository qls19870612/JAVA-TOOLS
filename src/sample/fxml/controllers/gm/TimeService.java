package sample.fxml.controllers.gm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(TimeService.class);

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

    public void close() {
        this.scheduledExec.getService().shutdown();

    }
}
