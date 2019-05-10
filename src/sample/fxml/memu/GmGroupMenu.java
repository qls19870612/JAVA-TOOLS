package sample.fxml.memu;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import sample.fxml.controllers.GMProxyController;
import sample.fxml.controllers.client.handlers.gm.CustomGmModule;
import sample.fxml.controllers.client.handlers.gm.GmCmd;
import sample.fxml.renders.ModuleItemRender;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 21:31
 */
public class GmGroupMenu extends ContextMenu {
    /**
     * 单例
     */
    private static GmGroupMenu INSTANCE = null;
    private final MenuItem removeMenuItem;

    /**
     * 私有构造函数
     */
    private GmGroupMenu() {
        removeMenuItem = new MenuItem("删除");


    }

    public static void showForModuleItemRender(ModuleItemRender anchor, double screenX, double screenY) {


        getInstance().show(anchor, screenX, screenY);
    }


    @Override
    public void show(Node anchor, double screenX, double screenY) {
        super.show(anchor, screenX, screenY);
        getItems().clear();

        ModuleItemRender itemRender = (ModuleItemRender) anchor;
        if (itemRender.getItem() instanceof CustomGmModule) {
            getItems().add(removeMenuItem);
            removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    GMProxyController.THIS.customModules.remove(itemRender.getItem());
                    itemRender.getListView().getItems().remove(itemRender.getItem());
                }
            });
        }
        ArrayList<CustomGmModule> customModules = GMProxyController.THIS.customModules;
        for (CustomGmModule customModule : customModules) {
            if (customModule != itemRender.getItem()) {

                addItem("添加到 " + customModule.moduleName, event -> {
                    boolean isAdd = false;
                    for (GmCmd gmCmd : itemRender.getItem().gmCmds) {
                        if (!customModule.container(gmCmd)) {
                            customModule.addGm(gmCmd);
                            isAdd = true;
                        }
                    }
                    if (isAdd) {
                        GMProxyController.THIS.refreshCmdByModuleListViewSelect();
                        GMProxyController.THIS.saveSelectList();
                    }

                });

            }
        }
    }

    private void addItem(String s, EventHandler<ActionEvent> handler) {
        MenuItem settingMenuItem = new MenuItem(s);

        getItems().add(settingMenuItem);
        settingMenuItem.setOnAction(handler);
    }

    /**
     * 获取实例
     * @return RemoveMenu
     */
    public static GmGroupMenu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GmGroupMenu();
        }

        return INSTANCE;
    }

}
