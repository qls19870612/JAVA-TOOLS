package sample.fxml.controllers.client.gm;

import java.util.ArrayList;

import sample.fxml.controllers.GMProxyController;
import sample.fxml.controllers.client.ClientBase;
import sample.fxml.controllers.client.ClientDepends;
import sample.fxml.controllers.client.handlers.gm.GmModule;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 13:53
 */
public class GmClient extends ClientBase {
    private final GMProxyController gmProxyController;

    public GmClient(GMProxyController gmProxyController, ClientDepends depends) {
        super(depends);

        this.gmProxyController = gmProxyController;
    }

    @Override
    public void onEnterScene() {
        gmProxyController.onEnterScene();

    }

    @Override
    public void onDisconnect() {
        super.onDisconnect();
        gmProxyController.onSocketClose();
    }


    public void updateModules(ArrayList<GmModule> modules) {
        gmProxyController.updateModules(modules);
    }

    public void setProxy(String s) {
        gmProxyController.setProxy(s);
    }
}
