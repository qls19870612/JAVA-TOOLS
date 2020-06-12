package sample.fxml.controllers.client;

import org.jboss.netty.buffer.ChannelBuffer;

import java.util.ArrayList;

import sample.utils.BufferUtil;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/12/09 11:24
 */
public class LongMsgCache {
    ArrayList<byte[]> msgArrayList = new ArrayList<byte[]>();

    public LongMsgCache() {
    }

    public void reset(byte[] content) {
        msgArrayList.clear();
        msgArrayList.add(content);
    }

    public void add(byte[] content) {
        msgArrayList.add(content);
    }

    public static byte[] concat(ArrayList<byte[]> msg) {
        int length = 0;
        for (byte[] array : msg) {
            length += array.length;
        }
        byte[] result = new byte[length];
        int pos = 0;
        for (byte[] array : msg) {
            System.arraycopy(array, 0, result, pos, array.length);
            pos += array.length;
        }
        return result;
    }

    public void handMsg(IClient client) {

        byte[] msg = concat(msgArrayList);
        msgArrayList.clear();
        ChannelBuffer buffer = BufferUtil.newFixedSizeMessage(msg.length);
        buffer.writeBytes(msg);
        buffer.resetReaderIndex();
        int msgId = BufferUtil.readVarInt32(buffer);
        int moduleId = msgId / 1000;
        int sequenceId = msgId % 1000;
        client.onReceiveMsg(moduleId, sequenceId, buffer);
    }
}
