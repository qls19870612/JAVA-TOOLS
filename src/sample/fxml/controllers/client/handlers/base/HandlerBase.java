package sample.fxml.controllers.client.handlers.base;

import org.jboss.netty.buffer.ChannelBuffer;

import sample.fxml.controllers.client.IClient;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 10:18
 */
public abstract class HandlerBase {
    public abstract void handle(IClient client, int sequence, ChannelBuffer buffer);

}
