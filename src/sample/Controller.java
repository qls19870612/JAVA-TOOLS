package sample;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import sample.config.AppConfig;
import sample.fxml.controllers.CreateConfigController;
import sample.fxml.controllers.GMProxyController;
import sample.fxml.controllers.StringFormatterController;
import sample.fxml.controllers.XLS2TXTController;

public class Controller {
    private static int count = 0;
    @FXML
    public CreateConfigController createConfigController;
    @FXML
    public StringFormatterController stringFormatterController;
    @FXML
    public XLS2TXTController xml2TXTController;
    @FXML
    public GMProxyController gmProxyController;
    @FXML
    public Label timeLabel;
    @FXML
    public Label infoLabel;
    @FXML
    public TabPane tabPanel;
    private static Controller instance = null;
    private SimpleDateFormat timeDataFormat;

    public void init() {
        createConfigController.init();
        try {
            stringFormatterController.init();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        xml2TXTController.init();
        timeDataFormat = new SimpleDateFormat("HH:mm:ss");
        instance = this;
        tabPanel.getSelectionModel().select(AppConfig.selectTab);
    }

    public static void log(String... args) {
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
        count++;
        instance.timeLabel.setText(count + ": " + instance.timeDataFormat.format(new Date()));
    }

    public void onChangeToGM(Event event) {
        if (event.getTarget() instanceof Tab) {
            Tab target = (Tab) event.getTarget();
            switch (target.getText()) {
                case "GM":

                    gmProxyController.onSelect();
                    break;

            }
        }
    }
}
