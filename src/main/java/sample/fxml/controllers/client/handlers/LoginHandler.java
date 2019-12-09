package sample.fxml.controllers.client.handlers;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.LittleEndianHeapChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.Controller;
import sample.fxml.controllers.client.Hero;
import sample.fxml.controllers.client.IClient;
import sample.fxml.controllers.client.Modules;
import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.fxml.controllers.client.msgs.LoginModuleMessages;
import sample.fxml.controllers.client.msgs.SceneModuleMessages;
import sample.utils.BufferUtil;

import static sample.fxml.controllers.client.msgs.LoginModuleMessages.S2C_ACCOUNT_LOGIN_OK;
import static sample.fxml.controllers.client.msgs.LoginModuleMessages.S2C_CREATE_ROLE_OK;
import static sample.fxml.controllers.client.msgs.LoginModuleMessages.S2C_ROLE_LOGIN_OK;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 10:18
 */
@Handler(moduleId = Modules.LOGIN_MODULE_ID)
public class LoginHandler extends HandlerBase {
    private static final Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    @Override
    public boolean handle(IClient client, int sequence, ChannelBuffer buffer) {
        return onLogin(client, sequence, buffer);
    }

    private boolean onLogin(IClient client, int sequenceId, ChannelBuffer message) {

        switch (sequenceId) {
            case S2C_ACCOUNT_LOGIN_OK:
            case S2C_CREATE_ROLE_OK:
                int roleCount = message.readByte();
                if (roleCount == 0) {
                    logger.debug("onLogin client:{}", client);
                    createRole(client);

                } else {
                    Hero[] heros = new Hero[roleCount];
                    for (int i = 0; i < roleCount; i++) {
                        Hero hero = new Hero(message);
                        heros[i] = hero;
                    }
                    client.setHeros(heros);
                    login(client, heros[0]);
                }
                break;
            case S2C_ROLE_LOGIN_OK:

                sendEnterScene(client);

                break;

            default:
                return false;
        }
        return true;
    }

    public void login(IClient client, Hero hero) {
        LittleEndianHeapChannelBuffer buffer = new LittleEndianHeapChannelBuffer(hero.heroIdBytes.length + 2);
        BufferUtil.writeUTF(buffer, hero.heroIdBytes);
        client.sendBuffer(buffer, Modules.LOGIN_MODULE_ID, LoginModuleMessages.C2S_ROLE_LOGIN);
        Controller.log2Robot("登录:" + hero.toString());
    }

    public void createRole(IClient client) {
        int race = 1;
        boolean isMan = true;

        LittleEndianHeapChannelBuffer buffer = new LittleEndianHeapChannelBuffer(1 + 1 + client.getRoleName().length + 2);
        buffer.writeByte(race);
        BufferUtil.writeBoolean(buffer, isMan);
        BufferUtil.writeUTF(buffer, client.getRoleName());
        logger.debug("createRole client.getRoleName:{}", client.getRoleName());
        client.sendBuffer(buffer, Modules.LOGIN_MODULE_ID, LoginModuleMessages.C2S_CREATE_ROLE);
    }

    public void sendEnterScene(IClient client) {
        int range = 5;
        LittleEndianHeapChannelBuffer buffer = new LittleEndianHeapChannelBuffer(BufferUtil.computeVarInt32Size(range));
        BufferUtil.writeVarInt32(buffer, range);
        client.sendBuffer(buffer, Modules.SCENE_MODULE_ID, SceneModuleMessages.C2S_SCENE_LOAD);

    }
}
