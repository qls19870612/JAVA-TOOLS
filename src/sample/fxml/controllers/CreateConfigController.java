package sample.fxml.controllers;

import javafx.application.Platform;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import sample.config.AppConfig;
import sample.fxml.renders.CreateJavaClassRender;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @描述
 * @创建人 liangsong
 * @创建时间 $date$
 */
public class CreateConfigController {
    public ListView list;

    public void init() {

        list.setCellFactory(new Callback<ListView, ListCell>() {
            public ListCell call(ListView param) {
                return new CreateJavaClassRender();
            }
        });
        list.getItems().addAll(AppConfig.getCodeInfos());
        Timer timer = new Timer();
        long period = 2*1000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                boolean isUpdate = AppConfig.parseClassConfig();
                if (isUpdate) {
                    Platform.runLater(() -> updateList());
                }
            }
        },period,period);
    }

    private void updateList() {
        list.getItems().clear();
        list.getItems().addAll(AppConfig.getCodeInfos());
    }
}
