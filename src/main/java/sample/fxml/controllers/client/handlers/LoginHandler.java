package sample.fxml.controllers.client.handlers;

import com.google.protobuf.InvalidProtocolBufferException;

import org.apache.commons.lang3.RandomUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.game.module.login.LoginModuleNotice;
import app.protobuf.client.HeroContent.HeroProto;
import sample.fxml.controllers.client.IClient;
import sample.fxml.controllers.client.Modules;
import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.fxml.controllers.client.msgs.LoginModuleMessages;
import sample.utils.BufferUtil;

import static sample.fxml.controllers.client.msgs.LoginModuleMessages.S2C_ACCOUNT_LOGIN_OK;

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
                try {
                    byte[] bytes = BufferUtil.readUTFBytes(message);
                    HeroProto heroProto = HeroProto.parseFrom(bytes);
                    logger.debug("onLogin heroProto:{}", heroProto);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
                client.onEnterScene();
                client.sendGmMsg("all");
                break;

            default:
                return false;
        }
        return true;
    }

    @Override
    public void onNotice(int noticeId, IClient client) {
        if (LoginModuleNotice.ACCOUNT_NOT_EXIT.isNotice(noticeId)) {
            createRole(client);
        }

    }

    public void createRole(IClient client) {
        int sex = RandomUtils.nextInt(1, 2);
        ChannelBuffer buffer = BufferUtil.newFixedSizeMessage(1 + client.getRoleName().length + 2);

        BufferUtil.writeVarInt32(buffer, sex);
        BufferUtil.writeUTF(buffer, client.getRoleName());
        logger.debug("createRole client.getRoleName:{}", client.getRoleName());
        client.sendBuffer(buffer, Modules.LOGIN_MODULE_ID, LoginModuleMessages.C2S_ACCOUNT_CREATE);
    }


}
