package sample.fxml.renders;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.util.converter.DefaultStringConverter;
import sample.fxml.memu.RemoveMenu;
import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 21:24
 */
public class GmProxyItemRender extends TextFieldListCell<String> {
    public GmProxyItemRender() {
        super(new DefaultStringConverter());
        this.setEditable(true);

    }


    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if (StringUtils.isNotEmpty(item)) {
            setText(item);

            if (this.getListView() != null) {
                if (item.equals(getListView().getUserData())) {

                    setTextFill(Color.LIME);
                } else {
                    setTextFill(Color.GRAY);
                }
            } else {
                setTextFill(Color.WHITE);
            }

            GmProxyItemRender render = this;
            this.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    EventHandler<ActionEvent> eventEventHandler = event1 -> {
                        if (RemoveMenu.getOwner() instanceof GmProxyItemRender) {
                            GmProxyItemRender owner = (GmProxyItemRender) RemoveMenu.getOwner();

                            owner.getListView().getItems().remove(owner.getItem());
                        }
                    };
                    RemoveMenu.show(render, event.getScreenX(), event.getScreenY(), eventEventHandler);
                }
            });
        } else {
            this.setOnMouseClicked(null);
            setTextFill(Color.WHITE);
        }

    }
}
