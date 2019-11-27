package sample.fxml.controllers.client.robots;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 15:20
 */
public class SimpleAccountInfo {
    public final String account;
    public final int operatorId;
    public final int serverId;

    public SimpleAccountInfo(String account, int operatorId, int serverId) {
        this.account = account;
        this.operatorId = operatorId;
        this.serverId = serverId;
    }
}
