package sample.fxml.componet;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public AlertBox display(String title, String message) {
        Stage window = new Stage();
        window.setTitle(title);
        //modality要使用Modality.APPLICATION_MODEL
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(300);
        window.setMinHeight(150);

        Button button = new Button("关 闭");
        button.setOnAction(e -> window.close());
        button.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                window.close();
            }
        });
        Label label = new Label(message);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, button);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        //使用showAndWait()先处理这个窗口，而如果不处理，main中的那个窗口不能响应
        window.showAndWait();
        return this;
    }

    public static AlertBox showAlert(String title, String message) {

        return new AlertBox().display(title, message);
    }

    public static void showAlert(String message) {

        Platform.runLater(() -> showAlert("警告", message));
    }
}
