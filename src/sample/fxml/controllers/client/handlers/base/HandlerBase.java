package sample.fxml.controllers.client.handlers.base;

import com.google.protobuf.InvalidProtocolBufferException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sample.fxml.controllers.client.IClient;
import sample.fxml.controllers.client.robots.RobotClient;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/27 10:18
 */
public abstract class HandlerBase {
    private static final Logger logger = LoggerFactory.getLogger(HandlerBase.class);

    public void handle(IClient client, int sequence, ChannelBuffer buffer) throws InvalidProtocolBufferException {
        if (client instanceof RobotClient) {
            RobotClient robotClient = (RobotClient) client;
            handleRobot(robotClient, sequence, buffer);
        }
    }

    public void handleRobot(RobotClient client, int sequence, ChannelBuffer buffer) throws InvalidProtocolBufferException {

    }

}
