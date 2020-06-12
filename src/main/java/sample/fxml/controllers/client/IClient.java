package sample.fxml.controllers.client;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;

import game.sink.util.concurrent.DisruptorExecutor;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/04/01 11:47
 */
public interface IClient {

    void sendBuffer(ChannelBuffer buffer, int moduleId, int sequence);

    void dispose();

    void disconnect();//主动断开

    void onEnterScene();

    void setServerTime(long serverTime);

    long getServerTime();

    void setHeros(Hero[] heros);

    void sendBuffer(int moduleId, int sequnce);

    void onMessage(ChannelBuffer message);

    void onReceiveMsg(int moduleId, int sequenceId, ChannelBuffer message);

    void onDisconnect();//被动断开

    void onConnect();

    byte[] getRoleName();


    void sendGmMsg(String all);

    void setChannel(Channel channel);

    void setConnTimeOut(IConnTimeOut timeOut);

    int getAccountId();

    DisruptorExecutor getDisruptorExecutor();

    boolean isUseWebSocket();

}
