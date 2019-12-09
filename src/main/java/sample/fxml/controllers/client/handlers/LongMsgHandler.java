package sample.fxml.controllers.client.handlers;

import com.google.protobuf.InvalidProtocolBufferException;

import org.jboss.netty.buffer.ChannelBuffer;

import sample.fxml.controllers.client.IClient;
import sample.fxml.controllers.client.LongMsgCache;
import sample.fxml.controllers.client.Modules;
import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.utils.BufferUtil;

import static sample.fxml.controllers.client.msgs.HeroMiscModuleWrittenMessages.S2C_TOO_LONG_MESSAGE;

/**
 * 长消息特殊处理
 * 创建人  liangsong
 * 创建时间 2019/12/09 11:09
 */
@Handler(moduleId = Modules.HERO_MISC_MODULE_ID)
public class LongMsgHandler extends HandlerBase {

    private ThreadLocal<LongMsgCache> cacheBuffer = ThreadLocal.withInitial(LongMsgCache::new);

    @Override
    public boolean handle(IClient client, int sequence, ChannelBuffer buffer) throws InvalidProtocolBufferException {
        switch (sequence) {
            case S2C_TOO_LONG_MESSAGE:
                int total = BufferUtil.readVarInt32(buffer);
                int index = BufferUtil.readVarInt32(buffer);
                byte[] content = BufferUtil.readUTFBytes(buffer);
                LongMsgCache longMsgCache = cacheBuffer.get();
                if (index == 1) {
                    longMsgCache.reset(content);
                } else if (index == total) {
                    longMsgCache.add(content);
                    longMsgCache.handMsg(client);

                } else {
                    longMsgCache.add(content);
                }
                break;
            default:
                return false;
        }
        return true;
    }
}
