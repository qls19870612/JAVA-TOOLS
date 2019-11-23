package sample.fxml.renders;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import sample.fxml.config.DbConfig;
import sample.fxml.controllers.DbEntityCreatorController;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/22 14:08
 */
public class DbConfigRender extends ListCell<DbConfig> {

    private final Label label;
    private final Button deleteBtn;
    private final HBox hbox;

    public DbConfigRender() {
        super();
        label = new Label();
        deleteBtn = new Button("删除");
        hbox = new HBox();
        Region region = new Region();
        hbox.getChildren().addAll(label, region, deleteBtn);
        HBox.setHgrow(region, Priority.ALWAYS);
        deleteBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onClickDelete();
            }
        });
    }

    private void onClickDelete() {
        DbConfig item = getItem();
        if (item == null) {
            return;
        }
        DbEntityCreatorController.THIS.deleteDbConfig(item);
    }

    @Override
    protected void updateItem(DbConfig item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            setGraphic(null);
            return;
        }
        label.setText(item.getDbName() + " | " + item.getDbUrl());
        setGraphic(hbox);
    }
}
