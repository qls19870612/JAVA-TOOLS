package sample.fxml.controllers.gm;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.fxml.controllers.GMProxyController;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/26 18:00
 */
public class SingleDecoderHandler extends LengthFieldBasedFrameDecoder implements ChannelDownstreamHandler {
    private static final int DEFAULT_MSG_SIZE_LIMIT = 12000;
    private static final Logger logger = LoggerFactory.getLogger(SingleDecoderHandler.class);
    private Client client;

    private GMProxyController controller;

    public SingleDecoderHandler(GMProxyController controller) {
        super(DEFAULT_MSG_SIZE_LIMIT, 0, 2, -2, 0);
        this.controller = controller;
    }

    @Override
    protected ChannelBuffer extractFrame(ChannelBuffer buffer, int index, int length) {
        try {


            ChannelBuffer input = buffer.slice(index, length);
            client.onMessage(input);
        } catch (NullPointerException ex) {
            logger.error("extractFrame ex:{}", ex);
        } catch (Throwable ex) {
            //            logger.error("extractFrame ex:{}", ex);
            //            logger.error("extractFrame出错", ex);
        }
        return null;
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        if (client != null) {
            client.dispose();
        }

        client = new Client(controller.userIdTF.getText(), e.getChannel(), controller);
        client.onConnect();
        controller.setClient(client);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

        client.onDisconnect();
        client = null;
        super.channelDisconnected(ctx, e);
        logger.debug("socket closed");
    }

    @Override
    public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent evt) throws Exception {

        if (!(evt instanceof MessageEvent)) {
            ctx.sendDownstream(evt);
            return;
        }
        MessageEvent e = (MessageEvent) evt;
        //        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
        //
        //        int length = buffer.writerIndex();
        //
        //        buffer.setShort(0, length);
        ctx.sendDownstream(evt);
    }


}
