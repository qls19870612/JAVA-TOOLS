package sample.fxml.renders;

import javafx.event.EventHandler;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.DefaultStringConverter;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 21:24
 */
public class GmProxyItemRender extends TextFieldListCell<String> {
    public GmProxyItemRender() {
        super(new DefaultStringConverter());
        this.setEditable(true);
        GmProxyItemRender render = this;
        this.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getButton() == MouseButton.SECONDARY) {

                    GlobalMenu.getInstance().show(render, event.getScreenX(), event.getScreenY());
                }
            }
        });
        //        this.editableProperty().setValue(true);
    }


    //    @Override
    //    public void updateItem(String item, boolean empty) {
    //        super.updateItem(item, empty);
    //        if (StringUtils.isNotEmpty(item)) {
    //
    //            setText(item);
    //        } else {
    //
    //        }
    //
    //    }
}
