package sample.fxml.controllers;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.jboss.netty.handler.execution.FairOrderedDownstreamThreadPoolExecutor;
import org.openjdk.tools.javac.jvm.Code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import game.initializer.io.loader.FileLoader;
import game.initializer.io.loader.FileLoaderOS;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import sample.ITab;
import sample.config.AppConfig;
import sample.datas.vo.CodeInfo;
import sample.file.FileOperator;
import sample.fxml.componet.AlertBox;
import sample.fxml.componet.fxml.FileSelector;
import sample.fxml.renders.CreateJavaClassRender;
import sample.utils.CodeCreateUtils;
import sample.utils.StringUtils;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 $date$
 */
public class CreateConfigController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(CreateConfigController.class);
    @FXML
    public ListView list;
    public FileSelector xlsFolderSelector;
    public FileSelector javaFolderSelector;
    public AnchorPane settingPanel;
    private boolean inited;
    private Timer timer;
    private String javaPath;
    private String xlsPath;

    public void onSelect() {
        if (inited) {
            return;
        }
        refreshSetting();
        inited = true;
        list.setCellFactory(new Callback<ListView, ListCell>() {
            public ListCell call(ListView param) {
                return new CreateJavaClassRender();
            }
        });
        list.getItems().addAll(AppConfig.getCodeInfos());
        timer = new Timer();
        long period = 2 * 1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean isUpdate = AppConfig.parseClassConfig();
                if (isUpdate) {
                    Platform.runLater(() -> updateList());
                }
            }
        }, period, period);
    }

    @Override
    public void onAppClose() {
        if (!inited) {
            return;
        }
        timer.cancel();
    }

    private void updateList() {
        list.getItems().clear();
        list.getItems().addAll(AppConfig.getCodeInfos());
    }

    public void onSettingBtnClick(MouseEvent mouseEvent) {
        settingPanel.setVisible(!settingPanel.isVisible());
        if (!settingPanel.isVisible()) {

            refreshSetting();
        }
    }

    private void refreshSetting() {
        javaPath = javaFolderSelector.getPath();
        xlsPath = xlsFolderSelector.getPath();
    }

    public void settingCloseBtnClick(MouseEvent mouseEvent) {
        settingPanel.setVisible(false);
        refreshSetting();
    }

    public void onBatchBtnClick(MouseEvent mouseEvent) throws IOException {

        CodeInfo[] array = getCodeInfos();
        if (array == null) {
            return;
        }
        logger.debug("onBatchBtnClick length:{}", array.length);
        for (CodeInfo codeInfo : array) {
            logger.debug("onBatchBtnClick className:{},index:{}", codeInfo.className, codeInfo.index);
            CodeCreateUtils.createCode(codeInfo);

        }
        AlertBox.showAlert("全部转换完毕");
    }

    private CodeInfo[] getCodeInfos() throws IOException {
        ArrayList<CodeInfo> codeInfos;
        if (StringUtils.isEmpty(javaPath) || StringUtils.isEmpty(xlsPath)) {
            settingPanel.setVisible(true);
            return null;
        }
        codeInfos = new ArrayList<>();
        ArrayList<File> allFiles = FileOperator.getAllFiles(new File(xlsPath), ".xls");
        FileLoader fileLoader = FileLoaderOS.of(xlsPath);
        int codeInfoIndex = 0;
        for (File xlsFile : allFiles) {
            byte[] fileContent = fileLoader.fileToBytes(xlsFile.getName(), true);

            InputStream is = new ByteArrayInputStream(fileContent);
            HSSFWorkbook workbook = new HSSFWorkbook(is);

            for (int i = 0; i < 10; i++) {

                Sheet sheet = null;
                try {

                    sheet = workbook.getSheetAt(i);
                } catch (Exception e) {

                }
                if (sheet == null) {
                    break;
                }
                String sheetName = sheet.getSheetName();
                if (sheetName.startsWith("Sheet") || Pattern.compile("[\u4e00-\u9fa5]").matcher(sheetName).find()) {
                    continue;
                }
                CodeInfo codeInfo = new CodeInfo(codeInfoIndex++, sheetName + "Data", xlsFile.getPath(), javaPath, i);
                codeInfos.add(codeInfo);
            }
        }
        CodeInfo[] array = codeInfos.toArray(CodeInfo.EMPTY_CODE_INFO);
        return array;
    }

    public void onBatchListBtnClick(MouseEvent mouseEvent) throws IOException {
        CodeInfo[] array = getCodeInfos();
        if (array == null) {
            return;
        }
        list.getItems().clear();
        list.getItems().addAll(array);
    }
}
