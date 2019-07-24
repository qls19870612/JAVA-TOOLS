package sample.fxml.controllers.client;


import com.google.protobuf.InvalidProtocolBufferException;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.HeapChannelBufferFactory;
import org.jboss.netty.buffer.LittleEndianHeapChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import game.service.IThreadService;
import game.service.ScheduleRunnable;
import game.sink.server.CheckSumStream;
import game.sink.util.StringEncoder;
import game.sink.util.concurrent.DisruptorExecutor;
import sample.Controller;
import sample.config.AppConfig;
import sample.fxml.componet.AlertBox;
import sample.fxml.controllers.client.handlers.base.HandlerBase;
import sample.fxml.controllers.client.msgs.MiscModuleMessages;
import sample.utils.BufferUtil;
import sample.utils.TimeUtils;
import sample.utils.Utils;

import static sample.fxml.controllers.client.msgs.GMModuleMessages.C2S_G_M;


/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 20:06
 */
public abstract class ClientBase implements IClient {
    private static final ChannelBuffer EMPTY_BUFF = new LittleEndianHeapChannelBuffer(0);
    private final CheckSumStream stream;
    protected boolean isLogined;
    private ClientBootstrap socket;


    private Channel channel;
    private int serverId;

    public boolean isLogining() {
        return isLogining;
    }

    private boolean isLogining;

    public TimeService getTimeService() {
        return timeService;
    }

    private final TimeService timeService;
    private final HandlerHub handlerHub;
    private IConnTimeOut conTimeOut;


    private int accountId;
    private final IThreadService threadService;

    @Override
    public byte[] getRoleName() {
        return roleName;
    }

    private byte[] roleName;
    private String account;

    private int msgCount = -1;
    private static final Logger logger = LoggerFactory.getLogger(ClientBase.class);
    private Hero[] heros;
    private long severTime;
    private long clientTime;
    private ScheduledFuture<?> scheduledFuture;

    public ClientBase(ClientDepends depends) {
        this.timeService = depends.timeService;
        this.handlerHub = depends.handlerHub;
        threadService = depends.threadService;

        stream = new CheckSumStream();
    }

    public int getAccountId() {
        return accountId;
    }


    @Override
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void startLoginAccount(String account, String ip, int port, int serverId) {
        if (isLogined) {
            return;
        }
        this.serverId = serverId;
        isLogining = true;
        this.account = account;
        accountId = Utils.safeParseInt(this.account, 0);
        if (accountId == 0) {
            accountId = account.hashCode();
        }
        roleName = StringEncoder.encode(account);


        socket = new ClientBootstrap(new NioClientSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        socket.setOption("bufferFactory", HeapChannelBufferFactory.getInstance(ByteOrder.LITTLE_ENDIAN));
        socket.setOption("tcpNoDelay", true);
        socket.setOption("keepAlive", false);
        socket.setOption("sendBufferSize", 8192);
        socket.setOption("receiveBufferSize", 8192 * 8);

        socket.setPipelineFactory(() -> {
            ChannelPipeline pipeline = Channels.pipeline();
            pipeline.addLast("dsf", new SingleDecoderHandler(this));
            return pipeline;

        });
        ChannelFuture connect = socket.connect(new InetSocketAddress(ip, port));
        connect.addListener(future -> {
            isLogining = false;
            if (!future.isSuccess()) {
                if (conTimeOut != null) {
                    conTimeOut.onTimeOut(this);
                }
                AlertBox.showAlert("无法连接服务器:" + ip + ":" + port);
                Controller.log("无法连接服务器:" + ip + ":" + port);
            } else {
                Controller.log("成功连接服务器:" + ip + ":" + port);
            }
        });
    }

    private static int getMsgId(int moduleId, int sequence) {
        return moduleId * 1000 + sequence;
    }

    @Override
    public void sendBuffer(ChannelBuffer buffer, int moduleId, int sequence) {

        byte[] array = buffer.toByteBuffer().array();
        int msgLen = 8 + array.length;
        ChannelBuffer sendBuffer = new LittleEndianHeapChannelBuffer(msgLen);
        sendBuffer.writeShort(msgLen);
        int writeIndex = sendBuffer.writerIndex();
        sendBuffer.writeByte(0);//检查字节
        ++msgCount;
        int calculateOffset = calculateOffset(msgCount);
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
        //        if (moduleId != 2 && sequence != 2) {
        //            logger.debug("sendBuffer moduleId:{},sequence:{}", moduleId, sequence);
        //        }

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
        HandlerBase handler = handlerHub.getHandler(moduleId);
        if (handler != null) {
            try {
                handler.handle(this, sequenceId, message);
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        } else {
            logger.debug("onReciveMsg 未处理的模块消息 moduleId:{},sequenceId:{}", moduleId, sequenceId);
        }

    }


    public void sendGmMsg(String s) {

        sendBuffer(Modules.GM_MODULE_ID, C2S_G_M, s);
    }

    @Override
    public void onMessage(ChannelBuffer message) {

        short len = message.readShort();

        int msgId = message.readInt();
        //int roleCount = message.readByte();
        int moduleId = msgId / 1000;
        int sequenceId = msgId % 1000;

        logger.debug("onMessage moduleId-sequenceId:{}-{}", moduleId, sequenceId);

        onReciveMsg(moduleId, sequenceId, message);
    }

    @Override
    public void onDisconnect() {
        dispose();
    }

    @Override
    public void onConnect() {
        msgCount = -1;
        loginAccount();
    }

    private void loginAccount() {
        int operatorID = AppConfig.operatorID;

        byte[] deviceid = StringEncoder.encode("5ac0264b5c78c336af5ba94ddb69cc89");
        byte[] userId = StringEncoder.encode(account);
        logger.debug("loginAccount account:{}", account);
        int msgLen = userId.length + 2 + BufferUtil.computeVarInt32Size(operatorID) + BufferUtil.computeVarInt32Size(serverId) + deviceid.length + 2;

        ChannelBuffer channelBuffer = new LittleEndianHeapChannelBuffer(msgLen);
        BufferUtil.writeUTF(channelBuffer, userId);
        BufferUtil.writeVarInt32(channelBuffer, operatorID);
        BufferUtil.writeVarInt32(channelBuffer, serverId);
        BufferUtil.writeUTF(channelBuffer, deviceid);

        sendBuffer(channelBuffer, Modules.LOGIN_MODULE_ID, sample.fxml.controllers.client.msgs.LoginModuleMessages.C2S_ACCOUNT_LOGIN);
    }

    @Override
    public void setHeros(Hero[] heros) {
        this.heros = heros;
    }

    @Override
    public void sendBuffer(int moduleId, int sequnce) {

        sendBuffer(EMPTY_BUFF, moduleId, sequnce);
    }

    @Override
    public void setServerTime(long serverTime) {
        this.severTime = serverTime;
        this.clientTime = System.currentTimeMillis();
        scheduledFuture = timeService.getScheduledExec().scheduleWithFixedDelay(new ScheduleRunnable("向服务器发送心跳") {
            @Override
            public void doRun() throws Throwable {
                int currServerSec = TimeUtils.millsToSecond(getServerTime());
                ChannelBuffer buffer = new LittleEndianHeapChannelBuffer(BufferUtil.computeVarInt32Size(currServerSec));
                BufferUtil.writeVarInt32(buffer, currServerSec);
                sendBuffer(buffer, Modules.MISC_MODULE_ID, MiscModuleMessages.C2S_HEART_BEAT);
            }
        }, 10, 10, TimeUnit.SECONDS);
    }

    @Override
    public long getServerTime() {
        return this.severTime + System.currentTimeMillis() - this.clientTime;
    }

    @Override
    public void dispose() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            scheduledFuture = null;
        }
        if (this.channel != null) {

            this.channel.close();
        }
        msgCount = -1;
        if (this.socket != null) {
            this.socket.releaseExternalResources();
        }
    }

    @Override
    public void disconnect() {
        this.channel.close();
    }

    @Override
    public String toString() {
        return "ClientBase{" + "account='" + account + '\'' + '}';
    }

    @Override
    public void setConnTimeOut(IConnTimeOut timeOut) {
        this.conTimeOut = timeOut;
    }

    @Override
    public DisruptorExecutor getDisruptorExecutor() {
        return threadService.getExecutor(accountId);
    }
}
