package sample.fxml.controllers.gm.handlers;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.fxml.controllers.gm.Client;
import sample.fxml.controllers.gm.Modules;
import sample.fxml.controllers.gm.handlers.base.Handler;
import sample.fxml.controllers.gm.handlers.base.HandlerBase;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 11:58
 */
@Handler(moduleId = Modules.NOTICE_MODULE_ID)
public class NoticeHandler extends HandlerBase {
    private static final Logger logger = LoggerFactory.getLogger(NoticeHandler.class);

    @Override
    public void handle(Client client, int sequence, ChannelBuffer buffer) {
        logger.debug("handle sequence:{}", sequence);
    }
}
