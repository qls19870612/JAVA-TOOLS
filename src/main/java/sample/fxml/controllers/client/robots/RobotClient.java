package sample.fxml.controllers.client.robots;

import app.protobuf.client.RelationModuleClientContent.ClientRelation;
import sample.fxml.controllers.client.ClientBase;
import sample.fxml.controllers.client.ClientDepends;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 11:46
 */
public class RobotClient extends ClientBase {
    public ClientRelation getClientRelation() {
        return clientRelation;
    }

    private ClientRelation clientRelation;

    public RobotClient(ClientDepends depends) {
        super(depends);
    }

    @Override
    public void onEnterScene() {

    }

    public void setCleintRelation(ClientRelation clientRelation) {

        this.clientRelation = clientRelation;
    }
}
