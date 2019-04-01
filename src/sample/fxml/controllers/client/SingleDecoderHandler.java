package sample.fxml.controllers.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/26 18:00
 */
public class SingleDecoderHandler extends LengthFieldBasedFrameDecoder implements ChannelDownstreamHandler {
    private static final int DEFAULT_MSG_SIZE_LIMIT = 12000;
    private static final Logger logger = LoggerFactory.getLogger(SingleDecoderHandler.class);
    private final IClient client;

    public SingleDecoderHandler(IClient client) {
        super(DEFAULT_MSG_SIZE_LIMIT, 0, 2, -2, 0);

        this.client = client;
    }

    @Override
    protected ChannelBuffer extractFrame(ChannelBuffer buffer, int index, int length) {
        try {


            ChannelBuffer input = buffer.slice(index, length);
            client.getDisruptorExecutor().execute(() -> client.onMessage(input));

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


        client.setChannel(ctx.getChannel());
        client.onConnect();
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {

        client.onDisconnect();
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
