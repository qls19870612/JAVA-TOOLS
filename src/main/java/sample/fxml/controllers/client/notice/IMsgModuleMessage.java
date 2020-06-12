package sample.fxml.controllers.client.notice;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/08/17 18:05
 */
public interface IMsgModuleMessage extends IModuleNotice {
    default ChannelBuffer getMsg() {
         return null;
    }

    default ChannelBuffer getMsg(Object... args) {
        return null;
    }
}
