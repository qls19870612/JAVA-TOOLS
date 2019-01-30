package sample.fxml.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import sample.config.AppConfig;
import sample.datas.vo.XlsInfo;
import sample.file.FileOperator;
import sample.fxml.renders.XlsItemRender;
import sample.utils.AlertBox;
import sample.utils.StringUtils;
import sample.utils.Xls2LuaUtils;

import static sample.config.AppConfig.readLuaUpdateCfg;
import static sample.file.FileOperator.NEX_LINE;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/10/26 9:54
 */
public class XLS2LUAController {
    private final XLS2TXTController xls2TXTController;
    private final ListView list;
    private HashMap<String, XlsInfo> allXlsInfoMap = new HashMap<>();
    private XlsInfo[] allXmlInfoArr = XlsInfo.EMPTY;
    public static XLS2LUAController instance;
    public final File luaCfgFile = new File("config/lua.cfg");
    private HashMap<String, Long> luaCfgData = new HashMap<>();

    public XLS2LUAController(XLS2TXTController xls2TXTController) {
        this.xls2TXTController = xls2TXTController;
        this.list = xls2TXTController.xlsList;
        instance = this;
        this.init();
    }

    private void init() {
        if (readLuaUpdateCfg) {
            readLuaCfg();
            readLuaCfg();
        }
        checkUpdateDp();
        list.setCellFactory((Callback<ListView, XlsItemRender>) param -> new XlsItemRender());
        list.getItems().addAll(getXlsInfos());
        Timer timer = new Timer();
        long period = 2 * 1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean isUpdate = checkUpdateDp();
                if (isUpdate) {
                    Platform.runLater(() -> updateList());
                }
            }

        }, period, period);
    }

    private void readLuaCfg() {
        String s = FileOperator.readFiles(luaCfgFile);
        if (StringUtils.isEmpty(s)) {
            return;
        }
        String[] split = s.split(NEX_LINE);
        for (String s1 : split) {
            if (StringUtils.isNotEmpty(s1)) {
                //                String[] fileUpdateInfo = s1.split("=");
                int lastIndexOf = s1.lastIndexOf("=");
                String fileName = s1.substring(0, lastIndexOf);
                long updateTime = Long.parseLong(s1.substring(lastIndexOf + 1));
                luaCfgData.put(fileName, updateTime);

            }
        }

    }


    private XlsInfo[] getXlsInfos() {
        return allXmlInfoArr;
    }

    private void updateList() {
        list.getItems().clear();
        list.getItems().addAll(getXlsInfos());
    }

    public void onCreateLuaBtnClick(MouseEvent mouseEvent) {
        boolean isUpdated = false;
        for (XlsInfo info : allXmlInfoArr) {
            if (info.isNeedUpdate()) {
                Xls2LuaUtils.createLua(info);
                isUpdated = true;
            }
        }
        if (isUpdated) {
            AlertBox.showAlert("已经全部生成!");
            updateList();
            updateLuaCfg();
        } else {
            AlertBox.showAlert("没有需要更新的文件!");
        }

    }

    public void updateLuaCfg() {
        StringBuilder stringBuilder = new StringBuilder();
        for (XlsInfo info : allXmlInfoArr) {
            if (!info.isNeedUpdate()) {
                stringBuilder.append(info.fileName);
                stringBuilder.append("=");
                stringBuilder.append(info.lastModified());
                stringBuilder.append(NEX_LINE);
            }
        }
        if (stringBuilder.length() > 0) {
            FileOperator.writeFile(luaCfgFile, stringBuilder.toString());
        }

    }

    private boolean checkUpdateDp() {
        File file = new File(AppConfig.xlsPath);

        ArrayList<File> allFiles = FileOperator.getAllFiles(file, entry -> {
            String name = entry.getName();
            return name.endsWith(".xls");
        });
        HashMap<String, XlsInfo> xlsInfos = new HashMap<>();
        boolean isUpdate = false;
        for (File allFile : allFiles) {
            XlsInfo xlsInfo = getXlsInfo(allFile);
            if (xlsInfo == null) {
                xlsInfo = new XlsInfo(allFile);
                Long lastCreateModifyTime = luaCfgData.get(allFile.getName());
                if (lastCreateModifyTime != null) {
                    xlsInfo.setNeedUpdate(xlsInfo.lastModified() != lastCreateModifyTime);
                }
                isUpdate = true;
            } else {
                if (allFile.lastModified() != xlsInfo.lastModified()) {
                    xlsInfo.updateModifyTime(allFile.lastModified());
                    isUpdate = true;
                }
            }
            xlsInfos.put(xlsInfo.fileName, xlsInfo);
        }
        if (!isUpdate) {
            if (xlsInfos.size() != this.allXlsInfoMap.size()) {
                isUpdate = true;
            }
        }
        if (isUpdate) {
            this.allXlsInfoMap = xlsInfos;
            this.allXmlInfoArr = allXlsInfoMap.values().toArray(XlsInfo.EMPTY);
            updateSort();
        }
        return isUpdate;
    }

    private XlsInfo getXlsInfo(File entry) {
        return allXlsInfoMap.get(entry.getName());
    }

    public void onUpdateTimeSortCb(MouseEvent mouseEvent) {
        updateSort();
        updateList();
    }

    private void updateSort() {
        if (xls2TXTController.updateTimeSortCb.isSelected()) {
            Arrays.sort(this.allXmlInfoArr, XlsInfo.MODIFY_TIME_SORT);

        } else {
            Arrays.sort(this.allXmlInfoArr, XlsInfo.NAME_SORT);
        }
    }
}
