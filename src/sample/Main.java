package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.config.AppConfig;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AppConfig.initFactory();
        AppConfig.parseDataTypeMap();
        AppConfig.parserTemplate();
        AppConfig.parseClassConfig();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("sample.fxml"));
        loader.load();
        Controller controller = loader.getController();
        controller.init();
        Parent root = loader.getRoot();
        primaryStage.setTitle("XLS文件转java配置类");
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
