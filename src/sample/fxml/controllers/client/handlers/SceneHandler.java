package sample.fxml.controllers.client.handlers;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.fxml.controllers.client.IClient;
import sample.fxml.controllers.client.Modules;
import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.fxml.controllers.client.msgs.MiscModuleMessages;

import static sample.fxml.controllers.client.msgs.SceneModuleMessages.S2C_SCENE_LOAD_OK;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 11:59
 */
@Handler(moduleId = Modules.SCENE_MODULE_ID)
public class SceneHandler extends HandlerBase {

    private static final Logger logger = LoggerFactory.getLogger(SceneHandler.class);

    @Override
    public void handle(IClient client, int sequence, ChannelBuffer buffer) {
        onScene(client, sequence, buffer);
    }

    private void onScene(IClient client, int sequenceId, ChannelBuffer message) {
        switch (sequenceId) {
            case S2C_SCENE_LOAD_OK:
                client.onEnterScene();
                client.sendGmMsg("all");

                client.sendBuffer(Modules.MISC_MODULE_ID, MiscModuleMessages.C2S_GET_SERVER_TIME);
                break;
            default:
                logger.debug("onScene sequenceId:{}", sequenceId);


        }
    }


}
