package sample.fxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import game.service.ScheduleRunnable;
import javafx.scene.control.TextArea;
import sample.Controller;
import sample.ITab;
import sample.config.AppConfig;
import sample.fxml.controllers.client.ClientDepends;
import sample.fxml.controllers.client.robots.RobotClient;
import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 15:01
 */
public class RobotController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(RobotController.class);
    private static final int LINE_CHAR_COUNT = 8;
    public TextArea logTF;
    private boolean inited;
    private int currIndex;
    private ArrayList<RobotClient> robots = new ArrayList<>(1024);
    private static int PER_TIMES_ADD_ROBOT = 30;
    private volatile boolean isAdding = false;
    private final AtomicInteger timeOutCount = new AtomicInteger(0);
    private ScheduledFuture<?> scheduledFuture;
    private ClientDepends clientDepends;
    private StringBuilder cacheLog;
    private int lineCount = 0;
    private int realCount = 0;
    private int MAX_LINE_COUNT = 30;
    private int MAX_REMOVE_LINE_COUNT = 1;
    private int[] cacheMsgLenArr;
    private int msgIndex;

    public void onStartCon() {
        //        showLog(String.valueOf((int) (Math.random() * 10000)));
        //        if (true) {
        //            return;
        //        }
        if (isAdding) {
            return;
        }
        ArrayList<String> allAccount = clientDepends.dbService.getAllAccount(100, AppConfig.operatorID, AppConfig.serverID);
        final int size = allAccount.size();
        if (size <= 0) {
            return;
        }
        isAdding = true;
        currIndex = 0;
        scheduledFuture = clientDepends.timeService.getScheduledExec().scheduleWithFixedDelay(new ScheduleRunnable("定时登录客户端") {


            @Override
            public void doRun() {

                for (int i = 0; i < PER_TIMES_ADD_ROBOT; i++) {
                    if (currIndex >= size) {
                        break;
                    }
                    String account = allAccount.get(currIndex);

                    RobotClient robotClient = new RobotClient(Controller.getClientDepends());
                    robotClient.startLoginAccount(account, AppConfig.gmIp, AppConfig.gmPort, AppConfig.serverID);
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
                if (currIndex >= size) {
                    scheduledFuture.cancel(true);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    @Override
    public void onSelect() {
        if (inited) {
            return;
        }
        inited = true;
        cacheLog = new StringBuilder();
        clientDepends = Controller.getClientDepends();
        cacheMsgLenArr = new int[MAX_LINE_COUNT];
        Arrays.fill(cacheMsgLenArr, -1);


    }

    @Override
    public void onAppClose() {
        if (!inited) {
            return;
        }
        clientDepends.close();
    }

    public void showLog(String... args) {
        if (!inited) {
            return;
        }


        for (String arg : args) {
            lineCount++;
            realCount++;
            cacheLog.append(fillLine(lineCount));
            cacheLog.append(arg);
            cacheLog.append("\n");
            msgIndex = lineCount % cacheMsgLenArr.length;
            cacheMsgLenArr[msgIndex] = arg.length();
            //            logger.debug("showLog lineCount:{},arg.length:{}", lineCount, arg.length());
            if (realCount % MAX_LINE_COUNT == 0) {
                int len = countPrevLogLength(cacheMsgLenArr, msgIndex);
                //                logger.debug("showLog len:{}", len);
                cacheLog.delete(0, len);
                realCount -= MAX_REMOVE_LINE_COUNT;

            }
        }

        //        logger.debug("showLog ==============cacheLog.length:{}", cacheLog.length());
        logTF.setText(cacheLog.toString());

    }

    private String fillLine(int lineCount) {
        return StringUtils.rightFill(String.valueOf(lineCount), LINE_CHAR_COUNT - 1, " ") + ":";
    }

    private int countPrevLogLength(int[] cacheMsgLenArr, int msgIndex) {
        int i = msgIndex + 1 - MAX_LINE_COUNT;
        if (i < 0) {
            i += MAX_LINE_COUNT;
        }
        int len = 0;
        for (int j = 0; j < MAX_REMOVE_LINE_COUNT; j++) {
            int index = (i + j) % MAX_LINE_COUNT;
            int size = cacheMsgLenArr[index];
            if (size != -1) {
                len += size + 1 + LINE_CHAR_COUNT;
                cacheMsgLenArr[index] = -1;
            }
        }

        return len;
    }
}
