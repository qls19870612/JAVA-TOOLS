package sample.fxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import sample.ITab;
import sample.config.AppConfig;
import sample.datas.vo.XlsInfo;
import sample.file.FileOperator;
import sample.fxml.componet.AlertBox;
import sample.fxml.renders.XlsItemRender;
import sample.utils.StringUtils;
import sample.utils.Xls2LuaUtils;
import sample.utils.Xls2TsUtils;

import static sample.config.AppConfig.readLuaUpdateCfg;
import static sample.file.FileOperator.NEX_LINE;
import static sample.fxml.controllers.XlsController.xlsPath;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/10/26 9:54
 */
public class XLS2LUAController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(XLS2LUAController.class);
    private final XlsController xlsController;
    private final ListView list;
    private HashMap<String, XlsInfo> allXlsInfoMap = new HashMap<>();
    private XlsInfo[] allXmlInfoArr = XlsInfo.EMPTY;
    public static XLS2LUAController instance;
    public final File luaCfgFile = new File("config/lua.cfg");
    private HashMap<String, Long> luaCfgData = new HashMap<>();
    private boolean inited;
    private Timer timer;

    public XLS2LUAController(XlsController xlsController) {
        this.xlsController = xlsController;
        this.list = xlsController.xlsList;
        instance = this;

    }

    private void init() {
        if (inited) {
            return;
        }
        inited = true;
        if (readLuaUpdateCfg) {
            readLuaCfg();
        }
        checkUpdateDp();
        list.setCellFactory((Callback<ListView, XlsItemRender>) param -> new XlsItemRender());
        list.getItems().addAll(getXlsInfos());
        timer = new Timer();
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
        createAllFiles(CODE_TYPE.LUA);

    }

    private void createAllFiles(CODE_TYPE type) {
        if (type == CODE_TYPE.TS) {
            if (StringUtils.isEmpty(XlsController.tsPath)) {
                xlsController.settingPanel.setVisible(true);
                return;
            }
        } else if (type == CODE_TYPE.LUA) {
            if (StringUtils.isEmpty(XlsController.luaPath)) {
                xlsController.settingPanel.setVisible(true);
                return;
            }
        }
        boolean isUpdated = false;
        for (XlsInfo info : allXmlInfoArr) {
            if (info.isNeedUpdate() || !readLuaUpdateCfg) {
                if (type == CODE_TYPE.LUA) {
                    Xls2LuaUtils.createLua(info);
                } else {
                    Xls2TsUtils.createTs(info);
                }
                isUpdated = true;
            } else {
                logger.info("createAllFiles 不需要更新文件 getAbsolutePath:{}", info.file.getAbsolutePath());
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

    public void onCreateTsBtnClick(MouseEvent mouseEvent) {

        createAllFiles(CODE_TYPE.TS);

    }

    public void updateLuaCfg() {
        if (!readLuaUpdateCfg) {
            return;
        }
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
        if (StringUtils.isEmpty(xlsPath)) {
            return false;
        }
        File file = new File(xlsPath);

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
            xlsInfos.put(xlsInfo.url, xlsInfo);
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
        return allXlsInfoMap.get(entry.getAbsolutePath());
    }

    public void onUpdateTimeSortCb(MouseEvent mouseEvent) {
        updateSort();
        updateList();
    }

    private void updateSort() {
        if (xlsController.updateTimeSortCb.isSelected()) {
            Arrays.sort(this.allXmlInfoArr, XlsInfo.MODIFY_TIME_SORT);

        } else {
            Arrays.sort(this.allXmlInfoArr, XlsInfo.NAME_SORT);
        }
    }

    @Override
    public void onSelect() {
        this.init();
    }

    @Override
    public void onAppClose() {
        if (!inited) {
            return;
        }
        timer.cancel();
    }
}
