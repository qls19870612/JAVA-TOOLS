package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.config.AppConfig;

import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;

public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppConfig.initFactory();
        AppConfig.parseDataTypeMap();
        AppConfig.parserTemplate();
        AppConfig.parseClassConfig();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.load();
        Controller controller = loader.getController();
        controller.init();
        Parent root = loader.getRoot();
        primaryStage.setTitle("XLS文件转java配置类");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
        primaryStage.addEventHandler(WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

                controller.gmProxyController.onCloseApp();

            }
        });

    }


    public static void main(String[] args) {
        launch(args);
    }
}
