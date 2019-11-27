package sample.fxml.renders;

import javafx.scene.control.ListCell;
import javafx.scene.input.MouseButton;
import sample.fxml.controllers.client.handlers.gm.CustomGmModule;
import sample.fxml.controllers.client.handlers.gm.GmModule;
import sample.fxml.memu.GmGroupMenu;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 14:37
 */
public class ModuleItemRender extends ListCell<GmModule> {
    @Override
    protected void updateItem(GmModule item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null) {
            if (item instanceof CustomGmModule) {
                setText(item.moduleName + " 自定义");

            } else {

                setText(item.moduleName);
            }


            this.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY) {
                    GmGroupMenu.showForModuleItemRender(this, event.getScreenX(), event.getScreenY());
                }
            });

        } else {
            this.setOnMouseClicked(null);
            setText("");
        }

    }
}
