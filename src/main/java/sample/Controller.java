package sample;

import com.sun.javafx.tk.Toolkit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import sample.config.AppConfig;
import sample.enums.ConfigType;
import sample.fxml.controllers.CocosCodeCreatorController;
import sample.fxml.controllers.CreateConfigController;
import sample.fxml.controllers.DbEntityCreatorController;
import sample.fxml.controllers.DiabloPublishController;
import sample.fxml.controllers.FileCleanController;
import sample.fxml.controllers.GMProxyController;
import sample.fxml.controllers.PhpLanguageConvertController;
import sample.fxml.controllers.RobotController;
import sample.fxml.controllers.StringFormatterController;
import sample.fxml.controllers.XlsController;
import sample.fxml.controllers.XlsDoubleColCompareController;
import sample.fxml.controllers.client.ClientDepends;
import sample.interfaces.AutowireInterface;
import sample.mapper.ConfigMapper;
import sample.utils.Utils;

public class Controller implements AutowireInterface {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private static int count = 0;
    private static ClientDepends clientDepends;

    @FXML
    public CreateConfigController createConfigController;
    @FXML
    public StringFormatterController stringFormatterController;
    @FXML
    public XlsController xlsController;
    @FXML
    public GMProxyController gmProxyController;
    @FXML
    public RobotController robotController;
    @FXML
    public DiabloPublishController diabloPublishController;

    @FXML
    public DbEntityCreatorController dbEntityCreatorController;
    @FXML
    public FileCleanController fileCleanController;
    @FXML
    public XlsDoubleColCompareController xlsDoubleColCompareController;
    @FXML
    public CocosCodeCreatorController cocosCodeCreatorController;
    @FXML
    public PhpLanguageConvertController phpLanguageConvertController;
    @FXML
    public Label timeLabel;
    @FXML
    public Label infoLabel;
    @FXML
    public TabPane tabPanel;
    private static Controller instance = null;
    public Tab gmTab;
    public Tab xlsTab;
    public Tab txtTab;
    public Tab configTab;
    public Tab robotTab;
    public Tab diabloPublishTab;
    public Tab dbEntityCreatorTab;
    public Tab fileCleanTab;
    public Tab xlsDoubleColCompareTab;
    public Tab cocosCodeCreatorTab;
    public Tab phpLanguageConvertTab;
    private SimpleDateFormat timeDataFormat;
    private HashMap<Tab,ITab> tabs;

    @Autowired
    private ConfigMapper configMapper;

    public static ClientDepends getClientDepends() {
        if (clientDepends == null) {
            clientDepends = new ClientDepends();
        }
        return clientDepends;
    }


    public void init() {
        tabs = new HashMap<>();
        tabs.put(configTab,createConfigController);
        tabs.put(txtTab,stringFormatterController);
        tabs.put(xlsTab,xlsController);
        tabs.put(gmTab,gmProxyController);
        tabs.put(robotTab,robotController);
        tabs.put(diabloPublishTab,diabloPublishController);
        tabs.put(dbEntityCreatorTab,dbEntityCreatorController);
        tabs.put(fileCleanTab,fileCleanController);
        tabs.put(xlsDoubleColCompareTab,xlsDoubleColCompareController);
        tabs.put(cocosCodeCreatorTab,cocosCodeCreatorController);
        tabs.put(phpLanguageConvertTab,phpLanguageConvertController);
        timeDataFormat = new SimpleDateFormat("HH:mm:ss");
        instance = this;
        tabPanel.getSelectionModel().select(-1);
        tabPanel.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {


                ITab iTab = tabs.get(newValue);

                if (iTab!=null) {
                    iTab.onSelect();
                } else {
                    throw new RuntimeException("未处理的Tab:" + newValue.getText());
                }
            }
        });
        tabPanel.getSelectionModel().select(AppConfig.selectTab);
        if (AppConfig.selectTab == 0) {
            createConfigController.onSelect();
        }
        infoLabel.setText("");
        timeLabel.setText("");
    }


    public static void log(String... args) {
        if (!Toolkit.getToolkit().isFxUserThread()) {
            Platform.runLater(() -> showLogInFxThread(args));
            return;
        }
        showLogInFxThread(args);
    }

    public static void log2Robot(String... args) {
        if (!Toolkit.getToolkit().isFxUserThread()) {
            Platform.runLater(() -> showRobotLogInFxThread(args));
            return;
        }
        showRobotLogInFxThread(args);
    }

    private static void showRobotLogInFxThread(String... args) {
        instance.robotController.showLog(args);
    }

    private static void showLogInFxThread(String[] args) {
        String showTxt = null;
        if (args.length == 1) {
            showTxt = args[0];
        } else if (args.length > 1) {
            StringBuilder stringBuffer = new StringBuilder();
            for (String arg : args) {
                if (stringBuffer.length() > 0) {
                    stringBuffer.append(">");
                }
                stringBuffer.append(arg);
            }
            showTxt = stringBuffer.toString();
        } else {
            return;
        }
        if (showTxt.length() > 70) {
            showTxt = showTxt.substring(0, 30) + "...." + showTxt.substring(showTxt.length() - 40);
        }
        instance.infoLabel.setText(showTxt);
        if (instance.infoLabel.getTooltip() == null) {

            Tooltip toolTip = new Tooltip();
            Utils.hackTooltipStartTiming(toolTip);
            instance.infoLabel.setTooltip(toolTip);
        }
        instance.infoLabel.getTooltip().setText(showTxt);
        count++;
        instance.timeLabel.setText(count + ": " + instance.timeDataFormat.format(new Date()));
    }


    public void onAppClose() {
        for (ITab value : tabs.values()) {
            value.onAppClose();
        }
        configMapper.setInt(ConfigType.TAB_SELECT_INDEX, tabPanel.getSelectionModel().getSelectedIndex());
    }
}
