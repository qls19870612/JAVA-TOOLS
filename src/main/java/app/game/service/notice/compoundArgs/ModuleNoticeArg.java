package app.game.service.notice.compoundArgs;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/09/17 12:14
 */
public interface ModuleNoticeArg {
    int totalSize();

    void writeToBuff(ChannelBuffer buffer);
 
}
