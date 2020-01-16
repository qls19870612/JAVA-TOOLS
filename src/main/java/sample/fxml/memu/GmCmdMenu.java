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
import sample.fxml.renders.CmdItemRender;
import sample.fxml.renders.GmProxyItemRender;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 21:31
 */
public class GmCmdMenu extends ContextMenu {
    /**
     * 单例
     */
    private static GmCmdMenu INSTANCE = null;

    /**
     * 私有构造函数
     */
    private GmCmdMenu() {

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
        getItems().clear();
        if (anchor instanceof CmdItemRender) {
            CmdItemRender cmdItemRender = (CmdItemRender) anchor;
            GmCmd gmCmd = cmdItemRender.getGmCmd();
            CustomGmModule currShowParent = null;
            if (gmCmd.getCurrShowParent() instanceof CustomGmModule) {
                currShowParent = (CustomGmModule) gmCmd.getCurrShowParent();
                CustomGmModule finalParent = currShowParent;
                addItem("从 " + finalParent.moduleName + " 删除", event -> {
                    finalParent.remove(gmCmd);
                    GMProxyController.THIS.refreshCmdByModuleListViewSelect();
                    GMProxyController.THIS.saveSelectList();
                });
            }

            ArrayList<CustomGmModule> customModules = GMProxyController.THIS.customModules;
            for (CustomGmModule customModule : customModules) {
                if (customModule != currShowParent) {
                    if (!customModule.container(gmCmd)) {
                        addItem("添加到 " + customModule.moduleName, event -> {
                            customModule.menuAddGm(gmCmd);
                            GMProxyController.THIS.saveSelectList();
                        });
                    }
                }
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
    public static GmCmdMenu getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GmCmdMenu();
        }

        return INSTANCE;
    }

}
