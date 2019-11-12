package sample.fxml.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import sample.Controller;
import sample.ITab;
import sample.config.AppConfig;
import sample.enums.ConfigType;
import sample.fxml.componet.InputBox;
import sample.fxml.controllers.client.ClientDepends;
import sample.fxml.controllers.client.gm.GmClient;
import sample.fxml.controllers.client.handlers.gm.CustomGmModule;
import sample.fxml.controllers.client.handlers.gm.GmCmd;
import sample.fxml.controllers.client.handlers.gm.GmModule;
import sample.fxml.renders.CmdItemRender;
import sample.fxml.renders.GmProxyItemRender;
import sample.fxml.renders.ModuleItemRender;
import sample.mapper.ConfigMapper;
import sample.utils.StringUtils;
import sample.utils.Utils;

import static sample.enums.ConfigType.GM_CUSTOM_MODULE;
import static sample.enums.ConfigType.GM_IP;
import static sample.enums.ConfigType.GM_PORT;
import static sample.enums.ConfigType.GM_SEARCH_CMD;
import static sample.enums.ConfigType.GM_SEARCH_MODULE;
import static sample.enums.ConfigType.GM_SERVER_ID;
import static sample.enums.ConfigType.GM_USER_ID;
import static sample.utils.Utils.BR;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 15:03
 */
public class GMProxyController implements ITab {
    private static final Logger logger = LoggerFactory.getLogger(GMProxyController.class);
    public TextField userIdTF;
    public Button loginBtn;


    public ListView<String> proxyListView;
    public ListView<GmModule> moduleListView;
    public ListView<GmCmd> cmdListView;
    public ComboBox<String> cmdComboBox;
    public ComboBox<String> moduleComboBox;
    public Button customModuleBtn;
    public Button searchModuleBtn;
    public Button searchCmdBtn;
    public ComboBox<String> ipComboBox;
    public ComboBox<String> portComboBox;
    public ComboBox<String> serverIdComboBox;
    public AnchorPane border;
    public SplitPane splitPane;
    public HBox proxyListTitleHb;

    private boolean inited;
    private boolean isLogined;
    private GmClient client;

    private ArrayList<GmModule> modules = new ArrayList<>();


    public static GMProxyController THIS;
    private ClientDepends depends;
    public ArrayList<CustomGmModule> customModules = new ArrayList<>();
    private String cacheGmMsg = null;
    @Autowired
    private ConfigMapper configMapper;

    public void loginBtnClick() {
        if (isLogined) {
            startLogout();
        } else {
            if (client.isLogining()) {
                Controller.log("正在连接中...");
                return;
            }
            if (StringUtils.isEmpty(userIdTF.getText())) {
                userIdTF.setText(userIdTF.getPromptText());
            }
            client.startLoginAccount(userIdTF.getText(), getIp(), getPort(), getServerID());

        }


    }

    private int getServerID() {
        String text = serverIdComboBox.getEditor().getText();
        int serverId = Utils.safeParseInt(text, 0);
        if (serverId > 0) {
            return serverId;
        }
        return AppConfig.serverID;
    }

    private int getPort() {
        String text = portComboBox.getEditor().getText();
        int port = Utils.safeParseInt(text, 0);
        if (port > 0) {
            return port;
        }
        return AppConfig.gmPort;
    }

    private String getIp() {
        String text = ipComboBox.getEditor().getText();
        if (StringUtils.isNotEmpty(text)) {
            return text;
        }
        return AppConfig.gmIp;
    }

    private void startLogout() {
        if (client != null) {
            client.disconnect();
        }
    }


    public void onSocketClose() {
        setIsLogined(false);
    }


    public void onEnterScene() {
        setIsLogined(true);
    }

    public void refreshProxy() {
        if (!isLogined) {
            return;
        }
        if (proxyListView.getItems().size() <= 0) {

            return;

        }
        String selectedItem = proxyListView.getSelectionModel().getSelectedItem();
        if (StringUtils.isNotEmpty(selectedItem)) {

            client.sendGmMsg("proxy " + selectedItem);
        } else {
            client.sendGmMsg("proxy " + proxyListView.getItems().get(0));
        }
        if (cacheGmMsg != null) {
            client.sendGmMsg(cacheGmMsg);
            cacheGmMsg = null;
        }
    }

    private void setIsLogined(boolean b) {
        isLogined = b;

        Platform.runLater(() -> {
            if (b) {
                userIdTF.setEditable(false);
                loginBtn.setText("退出(已登录)");
                loginBtn.setTextFill(Color.DARKGREEN);
            } else {
                userIdTF.setEditable(true);
                loginBtn.setTextFill(Color.DARKRED);
                loginBtn.setText("登录(未登录)");
            }
        });

    }


    public void onSelect() {
        if (inited) {
            return;
        }

        THIS = this;

        inited = true;
        depends = Controller.getClientDepends();
        client = new GmClient(this, depends);
        initComponent();
        readProxyList();
        readSearchList();
        if (StringUtils.isNotEmpty(userIdTF.getText())) {
            client.startLoginAccount(userIdTF.getText(), getIp(), getPort(), getServerID());
        }


    }

    private void initComponent() {
        Tooltip toolTip = new Tooltip();
        Utils.hackTooltipStartTiming(toolTip);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("添加自定义模块\n");
        stringBuilder.append("模块内可添加和删除GM命令\n");
        stringBuilder.append("在GM命令列表项中右键添加和删除\n");
        toolTip.setText(stringBuilder.toString());
        customModuleBtn.setTooltip(toolTip);
        moduleListView.setCellFactory(param -> new ModuleItemRender());

        cmdListView.setCellFactory(param -> new CmdItemRender());

        proxyListView.setCellFactory(param -> new GmProxyItemRender());
        proxyListView.setEditable(true);

        proxyListView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            if (event.getClickCount() == 2) {

                if (event.getTarget() instanceof ListView) {

                    proxyListView.getItems().add(String.valueOf(proxyListView.getItems().size()));
                    proxyListView.edit(proxyListView.getItems().size() - 1);
                } else if (event.getTarget() instanceof GmProxyItemRender) {
                    GmProxyItemRender target = (GmProxyItemRender) event.getTarget();

                    if (target.getIndex() >= proxyListView.getItems().size()) {

                        proxyListView.getItems().add(String.valueOf(proxyListView.getItems().size()));
                        proxyListView.edit(proxyListView.getItems().size() - 1);
                    }
                }
            }


        });
        proxyListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            refreshProxy();
        });
        proxyListView.addEventHandler(ListView.editCommitEvent(), event -> Platform.runLater(this::saveProxyList));

        proxyListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


        Scene scene = this.border.getScene();
        this.border.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Node focusOwner = scene.getFocusOwner();
                if (focusOwner instanceof TextField) {
                    TextField textField = (TextField) focusOwner;
                    textField.commitValue();
                    Node node = textField;
                    while ((node = node.getParent()) != null) {
                        if (node instanceof ListCell) {
                            ((ListCell) node).commitEdit(textField.getText());
                        }
                    }

                }
            }
        });

        moduleListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        moduleListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            refreshCmdByModuleListViewSelect();

        });

        moduleComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            searchModules(newValue);

        });


        cmdComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (StringUtils.isEmpty(newValue)) {
                refreshCmdByModuleListViewSelect();
                return;
            }
            searchCmd(newValue);
        });
        setUpComboBox(moduleComboBox);
        setUpComboBox(cmdComboBox);
        setUpComboBox(ipComboBox);
        setUpComboBox(portComboBox);
        setUpComboBox(serverIdComboBox);
        setUpSplitPanel();
    }

    private void setUpSplitPanel() {
        SplitPane.setResizableWithParent(proxyListTitleHb, true);
        double fixedHeight = 36d;
        splitPane.setDividerPositions(0.6d, 0.6d + fixedHeight / splitPane.getHeight());
        ObservableList<Divider> dividers = splitPane.getDividers();


        dividers.get(0).positionProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.floatValue() + fixedHeight / splitPane.getHeight();
            value = Math.min(value, 1d);
            dividers.get(1).setPosition(value);
        });
        dividers.get(1).positionProperty().addListener((observable, oldValue, newValue) -> {
            double value = newValue.floatValue() - fixedHeight / splitPane.getHeight();
            value = Math.max(value, 0d);
            dividers.get(0).setPosition(value);
        });
    }

    private void setUpComboBox(ComboBox<String> comboBox) {
        comboBox.setEditable(true);

        comboBox.setVisibleRowCount(10);
        comboBox.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String text = comboBox.getEditor().getText();
                if (StringUtils.isEmpty(text)) {
                    return;
                }
                for (String s : comboBox.getItems()) {
                    if (s.equals(text)) {
                        return;
                    }
                }
                comboBox.getItems().add(0, text);
                if (comboBox.getItems().size() > 10) {
                    comboBox.getItems().remove(10);
                }
                comboBox.getSelectionModel().select(0);
                comboBox.getEditor().extendSelection(comboBox.getEditor().getText().length());
            } else if (event.getCode() == KeyCode.DOWN) {
                comboBox.show();
            }
        });
    }

    private void searchCmd(String newValue) {
        newValue = newValue.toLowerCase();
        cmdListView.getItems().clear();
        for (GmModule module : modules) {
            for (GmCmd cmd : module.gmCmds) {
                if (cmd.lowerCmdName.contains(newValue) || cmd.getCommentPinYin().contains(newValue)) {
                    cmdListView.getItems().add(cmd);
                }
            }
        }
        searchCmdBtn.setText("取消");
    }


    public void refreshCmdByModuleListViewSelect() {
        searchCmdBtn.setText("应用");
        cmdListView.getItems().clear();
        ObservableList<GmModule> selectedItems = moduleListView.getSelectionModel().getSelectedItems();
        for (GmModule selectedItem : selectedItems) {

            cmdListView.getItems().addAll(selectedItem.gmCmds);
            for (GmCmd gmCmd : selectedItem.gmCmds) {
                gmCmd.setCurrShowParent(selectedItem);
            }
        }
    }

    private void searchModules(String newValue) {
        moduleListView.getItems().clear();
        if (StringUtils.isEmpty(newValue)) {
            moduleListView.getItems().addAll(modules);
            searchModuleBtn.setText("应用");
        } else {
            newValue = newValue.toLowerCase();
            for (GmModule module : modules) {
                if (module.getPinyinString().contains(newValue)) {
                    moduleListView.getItems().add(module);
                }
            }
            searchModuleBtn.setText("取消");
        }
    }

    @Override
    public void onAppClose() {
        if (!inited) {
            return;
        }
        saveData();

        if (this.client != null) {
            this.client.dispose();
        }
        depends.close();
    }


    private void readProxyList() {

        String s = configMapper.getConfig(ConfigType.GM_CLIENT_LIST);
        if (StringUtils.isEmpty(s)) {
            return;
        }
        String[] split = s.split(BR);
        proxyListView.getItems().addAll(split);


    }

    private void readSearchList() {

        String userId = configMapper.getConfig(GM_USER_ID);
        userIdTF.setText(userId);

        readComboBoxDataConfig(GM_SEARCH_MODULE, moduleComboBox);
        readComboBoxDataConfig(GM_SEARCH_CMD, cmdComboBox);
        readCustomModuleConfig();

        readComboBoxDataConfig(GM_IP, ipComboBox);


        readComboBoxDataConfig(GM_PORT, portComboBox);

        readComboBoxDataConfig(GM_SERVER_ID, serverIdComboBox);
    }

    private void readCustomModuleConfig() {
        String customModules = configMapper.getConfig(GM_CUSTOM_MODULE);
        if (StringUtils.isNotEmpty(customModules)) {

            String[] customModuleArr = customModules.split("@");
            for (String customModule : customModuleArr) {
                String[] split = customModule.split(BR);
                String moduleName = split[0];
                CustomGmModule customGmModule = new CustomGmModule(moduleName);
                if (split.length > 1) {
                    customGmModule.setCmds(Arrays.copyOfRange(split, 1, split.length));
                }
                this.customModules.add(customGmModule);
            }
        }
    }

    private void readComboBoxDataConfig(ConfigType configType, ComboBox<String> comboBox) {
        String config = configMapper.getConfig(configType);
        String[] datas = config.split(BR);
        boolean isFirst = true;
        int selectIndex = -1;
        comboBox.getItems().add("");
        for (String data : datas) {
            if (StringUtils.isNotEmpty(data)) {
                if (isFirst) {
                    isFirst = false;
                    selectIndex = Utils.safeParseInt(data, -1);
                } else {

                    comboBox.getItems().add(data);
                }
            }
        }

        comboBox.getSelectionModel().select(selectIndex);
        if (selectIndex != -1) {

            String value = comboBox.getItems().get(selectIndex);
            comboBox.getEditor().setText(value);
        }

    }

    private void saveData() {
        saveProxyList();
        saveSelectList();
    }

    public void saveSelectList() {
        configMapper.setConfig(GM_USER_ID, userIdTF.getText());

        saveComboBoxData(GM_SEARCH_MODULE, moduleComboBox);
        saveComboBoxData(GM_SEARCH_CMD, cmdComboBox);


        saveCustomModule();

        saveComboBoxData(GM_IP, ipComboBox);
        saveComboBoxData(GM_PORT, portComboBox);
        saveComboBoxData(GM_SERVER_ID, serverIdComboBox);

    }

    private void saveCustomModule() {
        StringBuilder stringBuilder = new StringBuilder();
        if (customModules.size() > 0) {
            for (CustomGmModule customModule : customModules) {
                stringBuilder.append(customModule.moduleName);
                stringBuilder.append(BR);
                if (customModule.gmCmds.size() > 0) {

                    for (GmCmd gmCmd : customModule.gmCmds) {
                        stringBuilder.append(gmCmd.lowerCmdName);
                        stringBuilder.append(BR);
                    }
                } else {
                    if (customModule.getCmds() != null) {
                        for (String s : customModule.getCmds()) {
                            stringBuilder.append(s);
                            stringBuilder.append(BR);
                        }
                    }
                }
                stringBuilder.append("@");
            }
            stringBuilder.setLength(stringBuilder.length() - 1);


        }
        configMapper.setConfig(GM_CUSTOM_MODULE, stringBuilder.toString());
    }

    private void saveComboBoxData(ConfigType configType, ComboBox<String> comboBox) {
        StringBuilder stringBuilder = new StringBuilder();
        int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();

        stringBuilder.append(selectedIndex);
        stringBuilder.append(BR);
        for (String s : comboBox.getItems()) {
            if (StringUtils.isNotEmpty(s)) {
                stringBuilder.append(s);
                stringBuilder.append(BR);
            }
        }
        configMapper.setConfig(configType, stringBuilder.toString());
    }

    private void saveProxyList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : proxyListView.getItems()) {
            stringBuilder.append(s);
            stringBuilder.append(BR);
        }
        configMapper.setConfig(ConfigType.GM_CLIENT_LIST, stringBuilder.toString());
    }


    public void updateModules(ArrayList<GmModule> modules) {
        this.modules = modules;

        HashMap<String, GmCmd> cmdHashMap = new HashMap<>();
        for (GmModule module : modules) {
            for (GmCmd cmd : module.gmCmds) {
                cmdHashMap.put(cmd.lowerCmdName, cmd);
            }
        }
        for (CustomGmModule customModule : customModules) {
            customModule.fillCmds(cmdHashMap);
        }
        this.modules.addAll(customModules);
        this.modules.sort(GmModule.moduleSort);
        Platform.runLater(() -> {
            moduleListView.getItems().clear();
            moduleListView.getItems().addAll(modules);
        });

        refreshProxy();
    }

    public void sendGm(String s) {
        if (isLogined) {

            this.client.sendGmMsg(s);
            cacheGmMsg = null;
        } else {
            loginBtnClick();
            cacheGmMsg = s;
        }
    }

    public void setProxy(String s) {
        Platform.runLater(() -> {
            proxyListView.setUserData(s);
            proxyListView.refresh();

        });
    }

    public void searchModuleBtnClick(MouseEvent mouseEvent) {

        moduleComboBox.getSelectionModel().clearSelection();
        //        moduleComboBox.getEditor().setText("");
        if (searchModuleBtn.getText().equals("应用")) {

            searchModules(moduleComboBox.getEditor().getText());
        } else {

            searchModules("");
        }
    }

    public void searchCmdBtnClick(MouseEvent mouseEvent) {
        //        cmdComboBox.getSelectionModel().select(-1);
        if (searchCmdBtn.getText().equals("应用")) {

            searchCmd(cmdComboBox.getEditor().getText());

        } else {

            refreshCmdByModuleListViewSelect();
        }
    }


    public void customModuleBtnClick(MouseEvent mouseEvent) {
        InputBox.showAlert("请输入模块名", text -> {
            if (StringUtils.isNotEmpty(text)) {
                CustomGmModule customGmModule = new CustomGmModule("[" + text + "]");
                modules.add(customGmModule);
                modules.sort(GmModule.moduleSort);
                customModules.add(customGmModule);
                moduleListView.getItems().setAll(modules);

            }
        });
    }
}
