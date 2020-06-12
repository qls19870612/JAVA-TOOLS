package sample.fxml.controllers;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.URI;
import java.nio.ByteBuffer;

import sample.fxml.controllers.client.ClientBase;

/**
 *
 * 创建人  liangsong
 * 创建时间 2020/05/08 9:29
 */
public class CustomWebSocket extends WebSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(CustomWebSocket.class);
    private ClientBase clientBase;

    public CustomWebSocket(URI serverUri, ClientBase clientBase) {
        super(serverUri);
        this.clientBase = clientBase;

    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.debug("onOpen getHttpStatus:{},getHttpStatusMessage:{}", serverHandshake.getHttpStatus(), serverHandshake.getHttpStatusMessage());
        clientBase.onConnect();
        clientBase.setLogining(false);
    }

    @Override
    public void onMessage(String s) {
        logger.debug("onMessage s:{}", s);
    }

    @Override
    public void onMessage(ByteBuffer bytes) {
        ChannelBuffer buffer = new BigEndianHeapChannelBuffer(bytes.array());

        clientBase.onMessage(buffer);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.debug("onClose i:{},s:{},b:{}", i, s, b);
        clientBase.onDisconnect();
        clientBase.setLogining(false);
    }

    @Override
    public void onError(Exception e) {
        e.printStackTrace();
        logger.debug("onError getMessage:{}", e.getMessage());
        clientBase.setLogining(false);

    }

}
