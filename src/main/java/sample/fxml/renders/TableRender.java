package sample.fxml.renders;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import sample.db.TableStruct;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/22 11:44
 */
public class TableRender extends ListCell<TableStruct> {

    private final Label lable;

    public TableRender() {
        super();
        lable = new Label();
    }

    @Override
    protected void updateItem(TableStruct item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            setGraphic(null);
            return;
        }
        lable.setText(item.toString());
        setGraphic(lable);
    }

    @Override
    protected double computePrefHeight(double width) {
        return 24;

    }

    @Override
    protected double computeMinHeight(double width) {
        return 24;
    }
}
