package sample.fxml.controllers.client;

import org.jboss.netty.buffer.ChannelBuffer;

import sample.utils.BufferUtil;

/**
 *
 * 创建人  liangsong
 * 创建时间 2019/03/25 20:22
 */
public class Hero {

    public final String heroId;
    private final String heroName;
    private final int race;
    public final byte[] heroIdBytes;
    private int level;

    public Hero(ChannelBuffer message) {
        heroId = BufferUtil.readUTF(message);
        heroIdBytes = heroId.getBytes();
        heroName = BufferUtil.readUTF(message);
        race = BufferUtil.readVarInt32(message);
        level = BufferUtil.readVarInt32(message);
    }

    @Override
    public String toString() {
        return "Hero{" + "heroId='" + heroId + '\'' + ", heroName='" + heroName + '\'' + ", race=" + race + ", level=" + level + '}';
    }
}
