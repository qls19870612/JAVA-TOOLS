package sample.fxml.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sample.Controller;
import sample.ITab;
import sample.file.FileOperator;
import sample.fxml.componet.InputBox;
import sample.fxml.controllers.client.ClientDepends;
import sample.fxml.controllers.client.gm.GmClient;
import sample.fxml.controllers.client.handlers.gm.CustomGmModule;
import sample.fxml.controllers.client.handlers.gm.GmCmd;
import sample.fxml.controllers.client.handlers.gm.GmModule;
import sample.fxml.renders.CmdItemRender;
import sample.fxml.renders.GmProxyItemRender;
import sample.fxml.renders.ModuleItemRender;
import sample.utils.StringUtils;
import sample.utils.Utils;

import static sample.utils.Utils.BR;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 15:03
 */
public class GMProxyController implements ITab {
    public TextField userIdTF;
    public Button loginBtn;


    public ListView<String> proxyListView;
    public ListView<GmModule> moduleListView;
    public ListView<GmCmd> cmdListView;
    public ComboBox<String> cmdComboBox;
    public ComboBox<String> moduleComboBox;
    public Button customModuleBtn;
    private boolean inited;
    private boolean isLogined;
    private GmClient client;

    private ArrayList<GmModule> modules = new ArrayList<>();
    private final String proxyClientListFile = "proxyClientList.txt";
    private final String searchFile = "searchFile.txt";


    public static GMProxyController THIS;
    private ClientDepends depends;
    public ArrayList<CustomGmModule> customModules = new ArrayList<>();

    public void loginBtnClick() {
        if (isLogined) {
            startLogout();
        } else {
            if (StringUtils.isEmpty(userIdTF.getText())) {
                userIdTF.setText(userIdTF.getPromptText());
            }
            client.startLoginAccount(userIdTF.getText());
        }


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
            client.startLoginAccount(userIdTF.getText());
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
        proxyListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> refreshProxy());
        proxyListView.addEventHandler(ListView.editCommitEvent(), event -> Platform.runLater(this::saveProxyList));

        proxyListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        moduleListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        moduleListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            refreshCmdByModuleListViewSelect();

        });
        moduleComboBox.setEditable(true);
        moduleComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> searchModules(newValue));
        moduleComboBox.setVisibleRowCount(10);
        moduleComboBox.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String text = moduleComboBox.getEditor().getText();
                if (StringUtils.isEmpty(text)) {
                    return;
                }
                for (String s : moduleComboBox.getItems()) {
                    if (s.equals(text)) {
                        return;
                    }
                }
                moduleComboBox.getItems().add(0, text);
                if (moduleComboBox.getItems().size() > 10) {
                    moduleComboBox.getItems().remove(10);
                }
                moduleComboBox.getSelectionModel().select(0);
                moduleComboBox.getEditor().extendSelection(moduleComboBox.getEditor().getText().length());
            } else if (event.getCode() == KeyCode.DOWN) {
                moduleComboBox.show();
            }
        });

        cmdComboBox.setEditable(true);
        cmdComboBox.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (StringUtils.isEmpty(newValue)) {
                refreshCmdByModuleListViewSelect();
                return;
            }
            newValue = newValue.toLowerCase();
            cmdListView.getItems().clear();
            for (GmModule module : modules) {
                for (GmCmd cmd : module.gmCmds) {
                    if (cmd.lowerCmdName.contains(newValue) || cmd.getCommentPinYin().contains(newValue)) {
                        cmdListView.getItems().add(cmd);
                    }
                }
            }
        });
        cmdComboBox.setVisibleRowCount(10);
        cmdComboBox.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String text = cmdComboBox.getEditor().getText();
                if (StringUtils.isEmpty(text)) {
                    return;
                }
                for (String s : cmdComboBox.getItems()) {
                    if (s.equals(text)) {
                        return;
                    }
                }
                cmdComboBox.getItems().add(0, text);
                if (cmdComboBox.getItems().size() > 10) {
                    cmdComboBox.getItems().remove(10);
                }
                cmdComboBox.getSelectionModel().select(0);
                cmdComboBox.getEditor().extendSelection(cmdComboBox.getEditor().getText().length());
            } else if (event.getCode() == KeyCode.DOWN) {
                cmdComboBox.show();
            }
        });
    }


    public void refreshCmdByModuleListViewSelect() {
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
        } else {
            newValue = newValue.toLowerCase();
            for (GmModule module : modules) {
                if (module.getPinyinString().contains(newValue)) {
                    moduleListView.getItems().add(module);
                }
            }
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
        String s = FileOperator.readFiles(new File(proxyClientListFile));
        if (StringUtils.isEmpty(s)) {
            return;
        }
        String[] split = s.split(BR);
        proxyListView.getItems().addAll(split);

    }

    private void readSearchList() {
        String s = FileOperator.readFiles(new File(searchFile));
        if (StringUtils.isEmpty(s)) {
            return;
        }
        String[] listArr = s.split("=");
        if (listArr.length < 1) {
            return;
        }
        String userId = listArr[0];
        userIdTF.setText(userId);
        if (listArr.length < 2) {
            return;
        }
        String moduleList = listArr[1];
        String[] modules = moduleList.split(BR);
        for (String module : modules) {
            if (StringUtils.isNotEmpty(module)) {

                moduleComboBox.getItems().add(module);
            }
        }
        if (listArr.length < 3) {
            return;
        }
        String cmdList = listArr[2];
        String[] cmds = cmdList.split(BR);
        for (String cmd : cmds) {
            if (StringUtils.isNotEmpty(cmd)) {
                cmdComboBox.getItems().addAll(cmd);
            }
        }
        if (listArr.length < 4) {
            return;
        }
        String customModules = listArr[3];
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

    private void saveData() {
        saveProxyList();
        saveSelectList();
    }

    public void saveSelectList() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(userIdTF.getText());
        stringBuilder.append("=");
        for (String s : moduleComboBox.getItems()) {
            stringBuilder.append(s);
            stringBuilder.append(BR);
        }
        stringBuilder.append("=");
        for (String s : cmdComboBox.getItems()) {
            stringBuilder.append(s);
            stringBuilder.append(BR);
        }
        stringBuilder.append("=");
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
        FileOperator.writeFile(new File(searchFile), stringBuilder.toString());
    }

    private void saveProxyList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : proxyListView.getItems()) {
            stringBuilder.append(s);
            stringBuilder.append(BR);
        }
        FileOperator.writeFile(new File(proxyClientListFile), stringBuilder.toString());
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
        moduleListView.getItems().addAll(modules);
        refreshProxy();
    }

    public void sendGm(String s) {
        this.client.sendGmMsg(s);
    }

    public void setProxy(String s) {
        Platform.runLater(() -> {
            proxyListView.setUserData(s);
            proxyListView.refresh();

        });
    }

    public void clearSearchModuleBtnClick(MouseEvent mouseEvent) {
        moduleComboBox.getSelectionModel().select(-1);
    }

    public void clearSearchCmdBtnClick(MouseEvent mouseEvent) {
        cmdComboBox.getSelectionModel().select(-1);
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
