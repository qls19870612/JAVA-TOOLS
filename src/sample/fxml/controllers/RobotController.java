package sample.fxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import game.service.ScheduleRunnable;
import sample.Controller;
import sample.ITab;
import sample.fxml.controllers.client.ClientDepends;
import sample.fxml.controllers.client.HandlerHub;
import sample.fxml.controllers.client.robots.RobotClient;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 15:01
 */
public class RobotController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(RobotController.class);
    private boolean inited;
    private int currIndex;
    private ArrayList<RobotClient> robots = new ArrayList<>(1024);
    private static int PER_TIMES_ADD_ROBOT = 1;
    private volatile boolean isAdding = false;
    private HandlerHub handlerHub;
    private final AtomicInteger timeOutCount = new AtomicInteger(0);
    private ScheduledFuture<?> scheduledFuture;
    private ClientDepends clientDepends;

    public void onStartCon() {
        if (isAdding) {
            return;
        }
        ArrayList<String> allAccount = clientDepends.dbService.getAllAccount(100, 1, 2);
        if (allAccount.size() <= 0) {
            return;
        }
        isAdding = true;
        currIndex = 0;
        scheduledFuture = clientDepends.timeService.getScheduledExec().scheduleWithFixedDelay(new ScheduleRunnable("定时登录客户端") {


            @Override
            public void doRun() {

                for (int i = 0; i < PER_TIMES_ADD_ROBOT; i++) {
                    String account = allAccount.get(i);
                    if (currIndex >= allAccount.size()) {
                        break;
                    }
                    RobotClient robotClient = new RobotClient(Controller.getClientDepends());
                    robotClient.startLoginAccount(account);
                    robotClient.setConnTimeOut(client -> {
                        int count = timeOutCount.incrementAndGet();
                        if (count > 10) {
                            scheduledFuture.cancel(true);
                            logger.debug("onTimeOut count:{}", count);
                        }
                    });
                    robots.add(robotClient);

                    currIndex++;
                }
                if (currIndex >= allAccount.size()) {
                    isAdding = false;
                }
            }
        }, 500, 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void onSelect() {
        if (inited) {
            return;
        }
        inited = true;
        clientDepends = Controller.getClientDepends();

    }

    @Override
    public void onAppClose() {
        if (!inited) {
            return;
        }
        clientDepends.dbService.close();
    }
}
