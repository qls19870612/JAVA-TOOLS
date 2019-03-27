package sample.fxml.controllers.gm;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.LittleEndianHeapChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.Channels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import game.service.ScheduleRunnable;
import game.sink.server.CheckSumStream;
import game.sink.util.StringEncoder;
import sample.fxml.controllers.GMProxyController;
import sample.fxml.controllers.gm.handlers.base.HandlerBase;
import sample.fxml.controllers.gm.msgs.MiscModuleMessages;
import sample.utils.BufferUtil;
import sample.utils.TimeUtils;

import static sample.fxml.controllers.gm.msgs.GMModuleMessages.C2S_G_M;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 20:06
 */
public class Client {
    private static final ChannelBuffer EMPTY_BUFF = new LittleEndianHeapChannelBuffer(0);
    private final CheckSumStream stream;
    public final byte[] roleName;
    private final String account;
    private final Channel channel;
    public final GMProxyController gmProxyController;
    private int msgCount = -1;
    private static final Logger logger = LoggerFactory.getLogger(Client.class);
    private Hero[] heros;
    private long severTime;
    private long clientTime;
    private ScheduledFuture<?> scheduledFuture;

    public Client(String account, Channel channel, GMProxyController gmProxyController) {
        this.account = account;

        this.channel = channel;
        this.gmProxyController = gmProxyController;
        stream = new CheckSumStream();
        roleName = StringEncoder.encode(account);
    }

    private static int getMsgId(int moduleId, int sequence) {
        return moduleId * 1000 + sequence;
    }

    public void sendBuffer(ChannelBuffer buffer, int moduleId, int sequence) {

        byte[] array = buffer.toByteBuffer().array();
        int msgLen = 8 + array.length;
        ChannelBuffer sendBuffer = new LittleEndianHeapChannelBuffer(msgLen);
        sendBuffer.writeShort(msgLen);
        int writeIndex = sendBuffer.writerIndex();
        sendBuffer.writeByte(0);//检查字节
        ++msgCount;
        int calculateOffset = calculateOffset(msgCount);
        logger.debug("sendBuffer calculateOffset:{}", calculateOffset);
        logger.debug("sendBuffer msgCount:{},this.account:{},moduleId:{},sequence:{}", msgCount, this.account, moduleId, sequence);
        sendBuffer.writeByte(calculateOffset);//检验消息序列
        sendBuffer.writeInt(getMsgId(moduleId, sequence));//模块ID
        if (array.length > 0) {
            sendBuffer.writeBytes(array);
        }
        array = sendBuffer.toByteBuffer().array();
        stream.clear();
        stream.write(array, 3, array.length - 3);
        int checkSum = stream.getCheckSum();
        sendBuffer.setByte(writeIndex, checkSum);
        Channels.write(channel, sendBuffer);


    }

    private static int calculateOffset(int offset) {
        int v = offset;
        v ^= v >> 8;
        v ^= v >> 4;
        v &= 0xff;
        return v;
    }

    public void sendBuffer(int moduleId, int sequence, String s) {
        byte[] bytes = StringEncoder.encode(s);
        ChannelBuffer channelBuffer = new LittleEndianHeapChannelBuffer(bytes.length + 2);
        BufferUtil.writeUTF(channelBuffer, bytes);
        sendBuffer(channelBuffer, moduleId, sequence);
    }

    public void onReciveMsg(int moduleId, int sequenceId, ChannelBuffer message) {
        HandlerBase handler = gmProxyController.getHandler(moduleId);
        if (handler != null) {
            handler.handle(this, sequenceId, message);
        } else {
            logger.debug("onReciveMsg 未处理的模块消息 moduleId:{},sequenceId:{}", moduleId, sequenceId);
        }

    }


    private void onNotice(int sequenceId, ChannelBuffer message) {

    }


    public void sendGmMsg(String s) {

        sendBuffer(Modules.GM_MODULE_ID, C2S_G_M, s);
    }


    public void onMessage(ChannelBuffer message) {


        short len = message.readShort();

        int msgId = message.readInt();
        //int roleCount = message.readByte();
        int moduleId = msgId / 1000;
        int sequenceId = msgId % 1000;
        logger.debug("onMessage moduleId:{},sequenceId:{}", moduleId, sequenceId);
        onReciveMsg(moduleId, sequenceId, message);
    }

    public void onDisconnect() {
        gmProxyController.onSocketClose();
        dispose();
    }

    public void onConnect() {


        int ModuleId = 1;
        int sequence = 1;
        int operatorID = 1;
        int serverID = 1;
        byte[] deviceid = StringEncoder.encode("5ac0264b5c78c336af5ba94ddb69cc89");
        byte[] userId = StringEncoder.encode(gmProxyController.userIdTF.getText());
        int msgLen = userId.length + 2 + BufferUtil.computeVarInt32Size(operatorID) + BufferUtil.computeVarInt32Size(serverID) + deviceid.length + 2;

        ChannelBuffer channelBuffer = new LittleEndianHeapChannelBuffer(msgLen);
        BufferUtil.writeUTF(channelBuffer, userId);
        BufferUtil.writeVarInt32(channelBuffer, operatorID);
        BufferUtil.writeVarInt32(channelBuffer, serverID);
        BufferUtil.writeUTF(channelBuffer, deviceid);
        sendBuffer(channelBuffer, ModuleId, sequence);
    }

    public void setHeros(Hero[] heros) {
        this.heros = heros;
    }

    public void sendBuffer(int moduleId, int sequnce) {

        sendBuffer(EMPTY_BUFF, moduleId, sequnce);
    }

    public void setServerTime(long serverTime) {
        this.severTime = serverTime;
        this.clientTime = System.currentTimeMillis();
        scheduledFuture = gmProxyController.getTimeService().getScheduledExec().scheduleWithFixedDelay(new ScheduleRunnable("向服务器发送心跳") {
            @Override
            public void doRun() throws Throwable {
                int currServerSec = TimeUtils.millsToSecond(getServerTime());
                ChannelBuffer buffer = new LittleEndianHeapChannelBuffer(BufferUtil.computeVarInt32Size(currServerSec));
                BufferUtil.writeVarInt32(buffer, currServerSec);
                sendBuffer(buffer, Modules.MISC_MODULE_ID, MiscModuleMessages.C2S_HEART_BEAT);
            }
        }, 10, 10, TimeUnit.SECONDS);
    }

    public long getServerTime() {
        return this.severTime + System.currentTimeMillis() - this.clientTime;
    }

    public void dispose() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduledFuture = null;
        }
    }
}
