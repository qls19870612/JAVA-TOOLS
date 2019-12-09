package sample.fxml.controllers.client.handlers;

import com.google.protobuf.InvalidProtocolBufferException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.fxml.controllers.client.Modules;
import sample.fxml.controllers.client.handlers.base.Handler;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.fxml.controllers.client.robots.RobotClient;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 17:38
 */
@Handler(moduleId = Modules.RELATION_MODULE_ID)
public class RelationHandler extends HandlerBase {
    private static final Logger logger = LoggerFactory.getLogger(RelationHandler.class);

    @Override
    public boolean handleRobot(RobotClient client, int sequence, ChannelBuffer buffer) throws InvalidProtocolBufferException {
        //        switch (sequence) {
        //            case RelationModuleWrittenMessages.S2C_BASIC_RELATION:
        //
        //                byte[] bytes = BufferUtil.readUTFBytes(buffer);
        //                ClientRelation clientRelation = ClientRelation.parseFrom(bytes);
        //                client.setCleintRelation(clientRelation);
        //                client.sendBuffer(Modules.RELATION_MODULE_ID, C2S_GET_RELATIONS);
        //
        //                break;
        //            case RelationModuleMessages.S2C_GET_RELATIONS_OK:
        //                byte[] bytes2 = BufferUtil.readUTFBytes(buffer);
        //                AllClientRelationDetail allClientRelationDetail = AllClientRelationDetail.parseFrom(bytes2);
        //                List<ClientRelationDetail> detailList = allClientRelationDetail.getDetailList();
        //                HashMap<String, ClientRelationDetail> allDetails = new HashMap<>();
        //
        //                for (int i = 0; i < detailList.size(); i++) {
        //                    ClientRelationDetail clientRelationDetail = detailList.get(i);
        //                    allDetails.put(clientRelationDetail.getDetail().getId(), clientRelationDetail);
        //                }
        //                logger.debug("handleRobot detailList.size:{}", detailList.size());
        //                ClientRelation relation = client.getClientRelation();
        //
        //                logger.debug("handleRobot relation.getBlackIdCount:{},relation.getFriendsCount:{},relation.getEnemyListCount:{}",
        //                        relation.getBlackIdCount(), relation.getFriendsCount(), relation.getEnemyListCount());
        //                for (ClientFriendProto clientFriendProto : relation.getFriendsList()) {
        //                    ClientRelationDetail clientRelationDetail = allDetails.get(clientFriendProto.getFriendId());
        //                    Preconditions.checkNotNull(clientRelationDetail, "好友不能为空,clientFriendProto.getFriendId:%s", clientFriendProto.getFriendId());
        //                }
        //                for (String s : relation.getBlackIdList()) {
        //                    ClientRelationDetail clientRelationDetail = allDetails.get(s);
        //                    Preconditions.checkNotNull(clientRelationDetail, "黑名单 不能为空,s:%s", s);
        //
        //                }
        //                for (String s : relation.getEnemyListList()) {
        //                    ClientRelationDetail clientRelationDetail = allDetails.get(s);
        //                    Preconditions.checkNotNull(clientRelationDetail, "对手 不能为空,s:%s", s);
        //                }
        //                break;
        //        }
        return false;
    }
}
