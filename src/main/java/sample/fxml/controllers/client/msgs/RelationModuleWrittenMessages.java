package sample.fxml.controllers.client.msgs;


import org.jboss.netty.buffer.ChannelBuffer;

import app.protobuf.client.RelationModuleClientContent.ClientRelation;
import app.protobuf.client.RelationModuleClientContent.SingleRelation;
import sample.fxml.controllers.client.Modules;
import sample.utils.BufferUtil;

import static sample.utils.BufferUtil.computeUTF;
import static sample.utils.BufferUtil.computeVarInt32Size;
import static sample.utils.BufferUtil.newFixedSizeMessage;
import static sample.utils.BufferUtil.newProtobufMessage;
import static sample.utils.BufferUtil.onlySendHeadAndAStringAndABytesMessage;
import static sample.utils.BufferUtil.onlySendHeadAndAStringAndAVarInt64Bytes;
import static sample.utils.BufferUtil.onlySendHeadAndAStringMessage;
import static sample.utils.BufferUtil.writeBoolean;
import static sample.utils.BufferUtil.writeUTF;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/09/18 16:44
 */
public class RelationModuleWrittenMessages {
    public static final int MODULE_ID = Modules.RELATION_MODULE_ID;

    // ---- 获取关系 -----

    /**
     * 基本的关系数据
     *
     * bytes ClientRelation
     */
    public static final int S2C_BASIC_RELATION = 501;

    static final ChannelBuffer basicRelation(ClientRelation proto) {
        return newProtobufMessage(MODULE_ID, S2C_BASIC_RELATION, proto);
    }

    /**
     * 删除仇人
     *
     * string targetHeroId
     */
    public static final int S2C_ENEMY_REMOVED = 502;

    public static ChannelBuffer enemyRemoved(long id) {
        return onlySendHeadAndAStringMessage(MODULE_ID, S2C_ENEMY_REMOVED, String.valueOf(id));
    }

    /**
     * 添加仇人
     * boolean targetIsOnline
     * SingleRelation singleRelation
     */
    public static final int S2C_ENEMY_ADD = 503;

    public static ChannelBuffer enemyAdd(boolean targetIsOnline, SingleRelation singleRelation) {
        byte[] data = singleRelation.toByteArray();

        ChannelBuffer buffer = newFixedSizeMessage(MODULE_ID, S2C_ENEMY_ADD, 3 + data.length);
        writeBoolean(buffer, targetIsOnline);
        writeUTF(buffer, data);
        return buffer;
    }

    /**
     * 他人同意加好友，此时该人在线
     *
     * bool 是否在线
     * bytes SingleRelation
     */
    public static final int S2C_OTHER_AGREE_ADD_FRIEND = 504;

    static final ChannelBuffer getOtherAgreeAddFriend(boolean isOnline, SingleRelation singleRelation) {
        byte[] data = singleRelation.toByteArray();

        ChannelBuffer buffer = newFixedSizeMessage(MODULE_ID, S2C_OTHER_AGREE_ADD_FRIEND, 3 + data.length);
        writeBoolean(buffer, isOnline);
        writeUTF(buffer, data);
        return buffer;
    }

    /**
     *  好友度更新
     * string 的好友id
     * varint64 最新好友度
     */
    public static final int S2C_DEGREE_UPDATE = 505;

    public static ChannelBuffer updateDegree(long id, long degree) {
        return onlySendHeadAndAStringAndAVarInt64Bytes(MODULE_ID, S2C_DEGREE_UPDATE, String.valueOf(id), degree);
    }

    /**
     * 回复加好友成功，返回目标的数据
     *
     * bool 是否在线
     * bytes SingleRelation
     */
    public static final int S2C_AGREE_ADD_FRIEND_SUCCESS = 506;

    static final ChannelBuffer getAgreeAddFriendSuccess(boolean targetIsOnline, SingleRelation singleRelation) {

        byte[] data = singleRelation.toByteArray();
        ChannelBuffer buffer = newFixedSizeMessage(MODULE_ID, S2C_AGREE_ADD_FRIEND_SUCCESS, 3 + data.length);
        writeBoolean(buffer, targetIsOnline);
        writeUTF(buffer, data);
        return buffer;
    }

    /**
     * 你被别人添加好友了，等待你回复，该请求30秒后过期
     *
     * string 加我的人的id
     * varint32 level
     * string 加我的人的名字
     */
    public static final int S2C_ADD_FRIEND_WAIT_REPLY = 507;

    static final ChannelBuffer getAddFriendWaitReply(String id, int level, byte[] nameBytes) {
        ChannelBuffer buffer =
                newFixedSizeMessage(MODULE_ID, S2C_ADD_FRIEND_WAIT_REPLY, computeUTF(id) + computeVarInt32Size(level) + nameBytes.length + 2);
        writeUTF(buffer, id);
        BufferUtil.writeVarInt32(buffer, level);
        writeUTF(buffer, nameBytes);
        return buffer;
    }

    /**
     * 关系列表中某人的状态发生改变.
     *
     * 如果没有收到过好友列表详细信息, 则不需要保存此条信息中附带的状态.
     *
     * 如果有收到过：
     *  如果此人之前不在线, 那在把对方的信息改成消息中附带的信息的同时, 需要弹出提示, 说xxx上线了.
     *  如果策划有需要弹出提示说好友xxx上线了，客户端自己进行判断提示
     *
     * 附带SingleRelation的proto
     */
    static final int S2C_RELATION_DETAIL_CHANGED = 508;

    public static ChannelBuffer relationDetailChanged(SingleRelation sr) {
        return newProtobufMessage(MODULE_ID, S2C_RELATION_DETAIL_CHANGED, sr);
    }

    /**
     * 不论是否请求过好友信息
     *
     * 如果没收到好友列表详细信息，不处理
     *
     * 如果已经收到了好友列表详细信息(收到, 不是请求), 则需要把此人的状态设为不在线
     *
     * 如果关系列表中有人下线, 客户端都会收到这条消息. 需要根据策划需求弹出相应的提示.
     *
     * 附带
     *
     * string targetHeroId
     * bytes 名字
     */
    public static final int S2C_RELATION_OFFLINE = 509;

    public static ChannelBuffer relationOffline(long id, byte[] nameBytes) {
        return onlySendHeadAndAStringAndABytesMessage(MODULE_ID, S2C_RELATION_OFFLINE, String.valueOf(id), nameBytes);
    }

    /**
     * 他人拒绝加你好友
     *
     * string 别人的id
     * bytes 别人的名字
     */
    static final int S2C_OTHER_REFUSE_ADD_FRIEND = 510;

    static final ChannelBuffer getOtherRefuseAddFriend(long id, byte[] nameBytes) {
        return onlySendHeadAndAStringAndABytesMessage(MODULE_ID, S2C_OTHER_REFUSE_ADD_FRIEND, String.valueOf(id), nameBytes);
    }

    /**
     * 有人删除把你从他的好友列表里删除了, 如果已经收到好友信息, 则移除该好友
     *
     * string 别人的id,移除你的人，你顺便把他也移除了
     */
    public static final int S2C_OTHER_REMOVED_YOU_AS_FRIEND = 511;

    static final ChannelBuffer otherRemovedYouAsFriendMessage(long id) {
        return onlySendHeadAndAStringMessage(MODULE_ID, S2C_OTHER_REMOVED_YOU_AS_FRIEND, String.valueOf(id));
    }


}
