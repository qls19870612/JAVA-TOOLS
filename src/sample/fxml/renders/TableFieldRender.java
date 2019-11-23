package sample.fxml.renders;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import sample.db.TableField;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/22 11:26
 */
public class TableFieldRender extends ListCell<TableField> {
    private final HBox hbox;
    private final CheckBox checkBox;
    private final Label nameLabel;
    private final Label desLabel;

    public TableFieldRender() {
        super();
        hbox = new HBox();
        checkBox = new CheckBox();
        nameLabel = new Label();
        desLabel = new Label();
        Region region = new Region();
        hbox.getChildren().addAll(checkBox, nameLabel, region, desLabel);
        HBox.setHgrow(region, Priority.ALWAYS);
    }

    @Override
    protected double computePrefHeight(double width) {
        return 24;

    }

    @Override
    protected double computeMinHeight(double width) {
        return 24;
    }

    @Override
    protected void updateItem(TableField item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            setGraphic(null);
            return;
        }
        nameLabel.setText(item.fieldName + "(" + item.upLowerFieldName + ")");
        desLabel.setText(item.desc);
        setGraphic(hbox);

    }
}
