package sample.fxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import sample.ITab;
import sample.config.AppConfig;
import sample.file.FileOperator;
import sample.utils.CodeCreateUtils;

import static sample.utils.Xls2TxtUtils.createTxt;

/**

 *@描述
 *@创建人 liangsong

 *@创建时间 2018/7/21/021 14:34
 */
public class XlsController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(XlsController.class);
    public ListView xlsList;
    public CheckBox updateTimeSortCb;
    private ThreadPoolExecutor threadPoolExecutor;
    private int threadCount = Runtime.getRuntime().availableProcessors() * 2;
    public final static int EMPTY_LINE = 99999999;
    private XLS2LUAController xls2LUAController;
    public static String luaTemplate;
    private boolean inited;


    public void onSelect() {
        if (inited) {
            return;
        }
        inited = true;
        CodeCreateUtils.parseDataTypeMap();

        luaTemplate = FileOperator.getConfig("config/luaTemplate.lua");

        ThreadFactory threadFactor = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r);
            }
        };
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        threadPoolExecutor = new ThreadPoolExecutor(threadCount, threadCount, 60L, TimeUnit.MINUTES, queue, threadFactor);
        xls2LUAController = new XLS2LUAController(this);
        xls2LUAController.onSelect();
    }

    @Override
    public void onAppClose() {
        if (!inited) {
            return;
        }
        threadPoolExecutor.shutdown();
        xls2LUAController.onAppClose();
    }

    public void onCreateBtnClick(MouseEvent mouseEvent) {
        //        Platform.runLater(new Runnable() {
        //            @Override
        //            public void run() {
        //
        //            }
        //        });
        logger.debug("AppConfig.xlsPath:" + AppConfig.xlsPath);
        File root = new File(AppConfig.xlsPath);
        convertAllFiles(root);
    }

    private void convertAllFiles(File root) {
        ArrayList<File> allFiles = FileOperator.getAllFiles(root, ".xls");
        Collections.sort(allFiles, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        int len = allFiles.size();
        int perThreadExecuteNum = (int) Math.ceil(len * 1f / threadCount);
        for (int i = 0; i < threadCount; i++) {
            List<File> list = allFiles.subList(i * perThreadExecuteNum, (i + 1) * perThreadExecuteNum);
            int finalI = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    startConvertXlsList(list, finalI);
                }
            });
        }
    }

    private void startConvertXlsList(List<File> list, int finalI) {
        logger.debug("{} debugListSize:{},startTime:{}", finalI, list.size(), CodeCreateUtils.dateTimeFormatter.get().format(new Date()));
        for (File file : list) {
            createTxt(file);
        }
        logger.debug("{} debugListSize:{},startTime:{}", finalI, list.size(), CodeCreateUtils.dateTimeFormatter.get().format(new Date()));
    }


    public void onCreateLuaBtnClick(MouseEvent mouseEvent) {
        xls2LUAController.onCreateLuaBtnClick(mouseEvent);

    }

    public void onUpdateTimeSortCb(MouseEvent mouseEvent) {
        xls2LUAController.onUpdateTimeSortCb(mouseEvent);
    }

    public void onOpenLuaBtnClick(MouseEvent mouseEvent) {
        FileOperator.openFile(AppConfig.luaPath);

    }

    public void onOpenXlsBtnClick(MouseEvent mouseEvent) {
        FileOperator.openFile(AppConfig.xlsPath);
    }
}
