package sample.fxml.controllers.gm.handlers.base;

import org.jboss.netty.buffer.ChannelBuffer;

import sample.fxml.controllers.gm.Client;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 10:18
 */
public abstract class HandlerBase {
    public abstract void handle(Client client, int sequence, ChannelBuffer buffer);

}
