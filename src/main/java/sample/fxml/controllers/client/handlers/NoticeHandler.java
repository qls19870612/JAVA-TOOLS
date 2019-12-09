package sample.fxml.controllers.client.handlers;

import com.google.protobuf.InvalidProtocolBufferException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.protobuf.client.GoodsContent.GoodsProto;
import app.protobuf.client.GoodsContent.PrizeProto;
import sample.Controller;
import sample.fxml.controllers.client.IClient;
import sample.fxml.controllers.client.Modules;
import sample.fxml.controllers.client.config.NoticeData;
import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.utils.BufferUtil;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 11:58
 */
@Handler(moduleId = Modules.NOTICE_MODULE_ID)
public class NoticeHandler extends HandlerBase {
    private static final Logger logger = LoggerFactory.getLogger(NoticeHandler.class);

    @Override
    public boolean handle(IClient client, int sequence, ChannelBuffer buffer) throws InvalidProtocolBufferException {
        int moduleId = BufferUtil.readVarInt32(buffer);
        int noticeId = BufferUtil.readVarInt32(buffer);
        NoticeData data = Controller.getClientDepends().noticeDatas.getData(moduleId, noticeId);
        if (data == null) {
            logger.info("未配置的消息 moduleId:{},sequence:{}", moduleId, sequence);
            return false;
        }
        String notice = data.msg;
        for (String argType : data.argTypes) {
            switch (argType) {
                case "":
                case "s":
                    String str = BufferUtil.readUTF(buffer);
                    notice = replace(notice, argType, str);

                    break;

                case "i":
                    int num = BufferUtil.readVarInt32(buffer);
                    notice = replace(notice, argType, String.valueOf(num));
                    break;
                case "g":
                    int goodsId = BufferUtil.readVarInt32(buffer);
                    notice = replace(notice, argType, "物品" + goodsId);
                    break;
                case "h":
                    String heroId = BufferUtil.readUTF(buffer);
                    String heroName = BufferUtil.readUTF(buffer);
                    notice = replace(notice, argType, "heroId:" + heroId + "-" + heroName);
                    break;
                case "p":
                    int sceneId = BufferUtil.readVarInt32(buffer);
                    int x = BufferUtil.readVarInt32(buffer);
                    int y = BufferUtil.readVarInt32(buffer);
                    notice = replace(notice, argType, "sceneId:" + sceneId + "(" + x + "," + y + ")");
                    break;
                case "G":
                    byte[] bytes = BufferUtil.readUTFBytes(buffer);
                    GoodsProto goodsProto = GoodsProto.parseFrom(bytes);
                    notice = replace(notice, argType, goodsProto.toString());
                    break;
                case "P":

                    byte[] bytes1 = BufferUtil.readUTFBytes(buffer);
                    PrizeProto prizeProto = PrizeProto.parseFrom(bytes1);
                    byte race = buffer.readByte();
                    byte sex = buffer.readByte();
                    int level = BufferUtil.readVarInt32(buffer);
                    notice = replace(notice, argType, prizeProto.toString() + "->" + race + "_" + sex + "_" + level);
                    break;
                default:
                    logger.debug("未处理的协议参数 argType:{}", argType);
                    return false;

            }
        }
        //        logger.debug("handle StringEncoder.encode.client.getRoleName:{},notice:{}", StringEncoder.encode(client.getRoleName()), notice);
        Controller.log2Robot(notice);
        return true;
    }

    private String replace(String notice, String argType, String arg) {
        return notice.replace("{" + argType + "}", arg);
    }
}
