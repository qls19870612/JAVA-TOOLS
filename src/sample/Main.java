package sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import ch.qos.logback.core.db.dialect.SQLiteDialect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.config.AppConfig;

import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;


public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        AppConfig.initSqlLite();
        AppConfig.initFactory();
        AppConfig.parseDataTypeMap();
        AppConfig.parserTemplate();
        AppConfig.parseClassConfig();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.load();
        Controller controller = loader.getController();

        Parent root = loader.getRoot();
        primaryStage.setTitle("XLS文件转java配置类");
        primaryStage.setScene(new Scene(root, 820, 600));
        primaryStage.show();
        root.getScene().getStylesheets().add(this.getClass().getResource("listview.css").toExternalForm());
        controller.init();
        primaryStage.addEventHandler(WINDOW_CLOSE_REQUEST, event -> controller.onAppClose());
        SQLiteDialect dialect;

    }


    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class);
        launch(args);

    }


}
