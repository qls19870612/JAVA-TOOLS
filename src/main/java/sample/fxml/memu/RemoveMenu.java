package sample.fxml.memu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 21:31
 */
public class RemoveMenu extends ContextMenu {
    /**
     * 单例
     */
    private static RemoveMenu INSTANCE = null;
    private final MenuItem removeMenuItem;

    /**
     * 私有构造函数
     */
    private RemoveMenu() {
        removeMenuItem = new MenuItem("删除");


        getItems().add(removeMenuItem);

    }

    public static void show(Node anchor, double screenX, double screenY, EventHandler<ActionEvent> eventEventHandler) {
        getInstance().removeMenuItem.setOnAction(eventEventHandler);

        getInstance().show(anchor, screenX, screenY);
    }


    @Override
    public void show(Node anchor, double screenX, double screenY) {
        super.show(anchor, screenX, screenY);

    }

    public static Node getOwner() {
        return getInstance().getOwnerNode();
    }

    /**
     * 获取实例
     * @return RemoveMenu
     */
    public static RemoveMenu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoveMenu();
        }

        return INSTANCE;
    }

}
