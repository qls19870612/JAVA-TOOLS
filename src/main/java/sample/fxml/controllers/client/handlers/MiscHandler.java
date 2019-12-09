package sample.fxml.controllers.client.handlers;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.fxml.controllers.client.IClient;
import sample.fxml.controllers.client.Modules;
import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.utils.BufferUtil;

import static sample.fxml.controllers.client.msgs.MiscModuleMessages.S2C_GET_SERVER_TIME_OK;
import static sample.fxml.controllers.client.msgs.MiscModuleMessages.S2C_HEART_BEAT_OK;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 16:22
 */
@Handler(moduleId = Modules.MISC_MODULE_ID)
public class MiscHandler extends HandlerBase {
    private static final Logger logger = LoggerFactory.getLogger(MiscHandler.class);

    @Override
    public boolean handle(IClient client, int sequence, ChannelBuffer buffer) {
        switch (sequence) {
            case S2C_GET_SERVER_TIME_OK:
                long serverTime = BufferUtil.readVarInt64(buffer);
                client.setServerTime(serverTime);
                break;
            case S2C_HEART_BEAT_OK:
                break;
            default:
                return false;
        }
        return true;
    }
}
