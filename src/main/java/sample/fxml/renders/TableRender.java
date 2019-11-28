package sample.fxml.renders;


import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import sample.db.TableStruct;
import sample.fxml.controllers.DbEntityCreatorController;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/11/22 11:44
 */
public class TableRender extends ListCell<TableStruct> {

    private final Label lable;
    public final CheckBox checkBox;
    private final HBox hbox;

    public TableRender() {
        super();
        hbox = new HBox();
        checkBox = new CheckBox();

        checkBox.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            DbEntityCreatorController.THIS.onTableSelect(this);
        });
        lable = new Label();
        hbox.getChildren().addAll(checkBox, lable);
    }

    @Override
    protected void updateItem(TableStruct item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            setGraphic(null);
            return;
        }
        lable.setText(item.getCompoundLabel());
        checkBox.setSelected(item.isSelected());
        setGraphic(hbox);
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
