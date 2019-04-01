package sample.fxml.renders;

import javafx.scene.control.ListCell;
import sample.fxml.controllers.client.handlers.gm.GmModule;

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
            setText(item.moduleName);
        } else {
            setText("");
        }

    }
}
