package sample.fxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
import javafx.scene.layout.AnchorPane;
import sample.ITab;
import sample.file.FileOperator;
import sample.fxml.componet.fxml.FileSelector;
import sample.mapper.ConfigMapper;
import sample.utils.AttrXml2XlsUtils;
import sample.utils.CodeCreateUtils;
import sample.utils.StringUtils;
import sample.utils.Xml2XlsUtils;

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
    public FileSelector xlsFolderSelector;
    public FileSelector luaFolderSelector;
    public FileSelector tsFolderSelector;

    public AnchorPane settingPanel;
    public FileSelector xmlFolderSelector;
    public FileSelector xml2XlsFolderSelector;
    public FileSelector attrXmlFolderSelector;
    public FileSelector attrXml2XlsFolderSelector;
    private ThreadPoolExecutor threadPoolExecutor;
    private int threadCount = Runtime.getRuntime().availableProcessors() * 2;
    public final static int EMPTY_LINE = 99999999;
    private XLS2LUAController xls2LUAController;
    public static String luaTemplate;
    private boolean inited;
    @Autowired
    private ConfigMapper configMapper;

    public static String tsPath;
    public static String xlsPath;
    public static String luaPath;
    public static String xmlPath;
    public static String xml2XlsPath;
    public static String attrXmlPath;
    public static String attrXml2XlsPath;

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
        refreshPaths();
    }

    private void refreshPaths() {
        tsPath = tsFolderSelector.getPath();
        xlsPath = xlsFolderSelector.getPath();
        luaPath = luaFolderSelector.getPath();
        xmlPath = xmlFolderSelector.getPath();
        xml2XlsPath = xml2XlsFolderSelector.getPath();
        attrXmlPath = attrXmlFolderSelector.getPath();
        attrXml2XlsPath = attrXml2XlsFolderSelector.getPath();
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
        if (StringUtils.isEmpty(xlsPath)) {
            settingPanel.setVisible(true);
            return;
        }
        logger.debug("AppConfig.xlsPath:" + xlsPath);
        File root = new File(xlsPath);
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
        FileOperator.openFile(luaPath);

    }

    public void onOpenXlsBtnClick(MouseEvent mouseEvent) {
        FileOperator.openFile(xlsPath);
    }

    public void onCreateTsBtnClick(MouseEvent mouseEvent) {
        xls2LUAController.onCreateTsBtnClick(mouseEvent);
    }

    public void onOpenTsBtnClick(MouseEvent mouseEvent) {
        FileOperator.openFile(tsPath);
    }

    public void settingCloseBtnClick(MouseEvent mouseEvent) {

        refreshPaths();
        settingPanel.setVisible(false);
    }

    public void onCreateXLSBtnClick(MouseEvent mouseEvent) {
        if (StringUtils.isEmpty(xmlPath) || StringUtils.isEmpty(xml2XlsPath)) {
            settingPanel.setVisible(true);
            return;
        }
        Xml2XlsUtils.convert(xmlPath);
    }

    public void onOpenSettingBtnClick(MouseEvent mouseEvent) {
        settingPanel.setVisible(!settingPanel.isVisible());
        if (!settingPanel.isVisible()) {
            refreshPaths();
        }
    }

    public void onCreateAttrXmlXLSBtnClick(MouseEvent mouseEvent) {

        if (StringUtils.isEmpty(attrXmlPath) || StringUtils.isEmpty(attrXml2XlsPath)) {
            settingPanel.setVisible(true);
            return;
        }
        AttrXml2XlsUtils.convert(attrXmlPath);
    }
}
