package sample.fxml.renders;

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
public class GlobalMenu extends ContextMenu {
    /**
     * 单例
     */
    private static GlobalMenu INSTANCE = null;

    /**
     * 私有构造函数
     */
    private GlobalMenu() {
        MenuItem settingMenuItem = new MenuItem("删除");
        //        MenuItem updateMenuItem = new MenuItem("检查更新");
        //        MenuItem feedbackMenuItem = new MenuItem("官方论坛");
        //        MenuItem aboutMenuItem = new MenuItem("问题与建议");
        //        MenuItem companyMenuItem = new MenuItem("关于");


        getItems().add(settingMenuItem);
        //        getItems().add(updateMenuItem);
        //        getItems().add(companyMenuItem);
        //        getItems().add(feedbackMenuItem);
        //        getItems().add(aboutMenuItem);
        settingMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                onDelete();
            }
        });
    }

    private void onDelete() {
        if (this.getOwnerNode() instanceof GmProxyItemRender) {
            GmProxyItemRender ownerNode = (GmProxyItemRender) this.getOwnerNode();
            ownerNode.getListView().getItems().remove(ownerNode.getItem());
        }
    }

    @Override
    public void show(Node anchor, double screenX, double screenY) {
        super.show(anchor, screenX, screenY);

    }

    /**
     * 获取实例
     * @return GlobalMenu
     */
    public static GlobalMenu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GlobalMenu();
        }

        return INSTANCE;
    }

}
