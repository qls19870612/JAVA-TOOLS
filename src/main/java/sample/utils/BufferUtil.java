package sample.utils;

import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.MessageLite;

import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.util.internal.jzlib.JZlib;
import org.jboss.netty.util.internal.jzlib.ZStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

import game.sink.util.StringEncoder;

public class BufferUtil {
    private static final Logger logger = LoggerFactory.getLogger(BufferUtil.class);

    public static final ChannelBuffer[] EMPTY_ARRAY = new ChannelBuffer[0];
    private static ByteOrder endian = ByteOrder.BIG_ENDIAN;

    private BufferUtil() {
    }

    /**
     * 确保估计的大小不会比实际需要的小。创建个使用内存更少的ChannelBuffer，但是不会resize他的capacity
     *
     * @param moduleID
     * @param sequenceID
     * @param sureSizeExcludingSizeAndID
     *            和别的方法区分，无视传入的值
     * @return
     */
    public static ChannelBuffer newFixedSizeMessage(int moduleID, int sequenceID, int sureSizeExcludingSizeAndID) {
        ChannelBuffer buffer = newFixedSizeMessage(2 + sureSizeExcludingSizeAndID + 32);
        //        buffer.writeShort(10);
        buffer.writeShort(0);// place holder for buffer size
        buffer.writeInt(moduleID * 1000 + sequenceID);
        return buffer;
    }
    public static ChannelBuffer newFixedSizeMessage(int size) {
        return new BigEndianHeapChannelBuffer(size);
    }

    public static ChannelBuffer dynamicBuffer() {
        return dynamicBuffer(64);
    }

    public static ChannelBuffer dynamicBuffer(int size) {
        return ChannelBuffers.dynamicBuffer(endian, size);
    }

    public static ChannelBuffer newDynamicMessage(int moduleID, int sequenceID) {
        return newDynamicMessage(moduleID, sequenceID, 64);
    }

    /**
     * 返回的ChannelBuffer会按需加大
     *
     * @param moduleID
     * @param sequenceID
     * @param approxSizeExcludingSizeAndID
     * @return
     */
    public static ChannelBuffer newDynamicMessage(int moduleID, int sequenceID, int approxSizeExcludingSizeAndID) {
        ChannelBuffer buffer = ChannelBuffers.dynamicBuffer(endian, 12 + approxSizeExcludingSizeAndID);
        //        buffer.writeShort(10);
        buffer.writeShort(0);// place holder for buffer size
        buffer.writeInt(moduleID * 1000 + sequenceID);
        return buffer;
    }

    public static ChannelBuffer onlySendHeaderMessage(int moduleID, int sequenceID) {
        return newFixedSizeMessage(moduleID, sequenceID, 0);
    }

    public static ChannelBuffer onlySendHeadAndABoolBytes(int moduleID, int sequenceID, boolean b) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, 1);
        writeBoolean(buffer, b);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndABoolWithAVarInt64Message(int moduleID, int sequenceID, boolean b, long c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, 1 + computeVarInt64Size(c));
        writeBoolean(buffer, b);
        writeVarInt64(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd2BoolBytes(int moduleID, int sequenceID, boolean b, boolean c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, 2);
        writeBoolean(buffer, b);
        writeBoolean(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAStringAndAVarInt64Bytes(int moduleID, int sequenceID, String a, long b) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeUTF(a) + computeVarInt64Size(b));
        writeUTF(buffer, a);
        writeVarInt64(buffer, b);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAUTFBytes(int moduleID, int sequenceID, byte[] utf) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, utf.length + 2);
        writeUTF(buffer, utf);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt64WithBytesMessage(int moduleID, int sequenceID, long b, byte[] c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt64Size(b) + c.length + 2);
        writeVarInt64(buffer, b);
        writeUTF(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt64TwoVarInt32WithBytesMessage(int moduleID, int sequenceID, long b, int c, int d, byte[] e) {
        ChannelBuffer buffer =
                newFixedSizeMessage(moduleID, sequenceID, computeVarInt64Size(b) + computeVarInt32Size(c) + computeVarInt32Size(d) + e.length + 2);
        writeVarInt64(buffer, b);
        writeVarInt32(buffer, c);
        writeVarInt32(buffer, d);
        writeUTF(buffer, e);
        return buffer;
    }

    /**
     * 最少一个 最多三个
     */
    public static ChannelBuffer onlySendHeadAndThreeVarInt64WithBytesMessage(int moduleID, int sequenceID, long b, byte[] c, long b2, byte[] c2,
            long b3, byte[] c3) {
        int sureSizeExcludingSizeAndID = computeVarInt64Size(b) + 2 + c.length;
        if (c2 != null) {
            sureSizeExcludingSizeAndID += (computeVarInt64Size(b2) + 2 + c2.length);
        }
        if (c3 != null) {
            sureSizeExcludingSizeAndID += (computeVarInt64Size(b3) + 2 + c3.length);
        }
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, sureSizeExcludingSizeAndID);
        writeVarInt64(buffer, b);
        writeUTF(buffer, c);
        if (c2 != null) {
            writeVarInt64(buffer, b2);
            writeUTF(buffer, c2);
        }
        if (c3 != null) {
            writeVarInt64(buffer, b3);
            writeUTF(buffer, c3);
        }
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt32WithBytesMessage(int moduleID, int sequenceID, int b, byte[] c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt32Size(b) + c.length + 2);
        writeVarInt32(buffer, b);
        writeUTF(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd2VarInt32WithBytesMessage(int moduleID, int sequenceID, int b, int c, byte[] d) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt32Size(b) + computeVarInt32Size(c) + d.length + 2);
        writeVarInt32(buffer, b);
        writeVarInt32(buffer, c);
        writeUTF(buffer, d);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd3VarInt32WithBytesMessage(int moduleID, int sequenceID, int b, int c, int d, byte[] e) {
        ChannelBuffer buffer =
                newFixedSizeMessage(moduleID, sequenceID, computeVarInt32Size(b) + computeVarInt32Size(c) + computeVarInt32Size(d) + e.length + 2);
        writeVarInt32(buffer, b);
        writeVarInt32(buffer, c);
        writeVarInt32(buffer, d);
        writeUTF(buffer, e);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt32WithBoolMessage(int moduleID, int sequenceID, int b, boolean c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt32Size(b) + 1);
        writeVarInt32(buffer, b);
        writeBoolean(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt32WithAVarInt64Message(int moduleID, int sequenceID, int b, long c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt32Size(b) + computeVarInt64Size(c));
        writeVarInt32(buffer, b);
        writeVarInt64(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt64WithABoolMessage(int moduleID, int sequenceID, long b, boolean c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt64Size(b) + 1);
        writeVarInt64(buffer, b);
        writeBoolean(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt64With2BoolMessage(int moduleID, int sequenceID, long b, boolean c, boolean d) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt64Size(b) + 1 + 1);
        writeVarInt64(buffer, b);
        writeBoolean(buffer, c);
        writeBoolean(buffer, d);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt64WithAVarInt32Message(int moduleID, int sequenceID, long b, int c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt64Size(b) + computeVarInt32Size(c));
        writeVarInt64(buffer, b);
        writeVarInt32(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt64WithAByteMessage(int moduleID, int sequenceID, long b, int c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt64Size(b) + 1);
        writeVarInt64(buffer, b);
        buffer.writeByte(c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAByteMessage(int moduleID, int sequenceID, int b) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, 1);
        buffer.writeByte(b);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt32Message(int moduleID, int sequenceID, int b) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt32Size(b));
        writeVarInt32(buffer, b);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd2VarInt32Message(int moduleID, int sequenceID, int b, int c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt32Size(b) + computeVarInt32Size(c));
        writeVarInt32(buffer, b);
        writeVarInt32(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd3VarInt32Message(int moduleID, int sequenceID, int b, int c, int d) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt32Size(b) + computeVarInt32Size(c) + computeVarInt32Size(d));
        writeVarInt32(buffer, b);
        writeVarInt32(buffer, c);
        writeVarInt32(buffer, d);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd4VarInt32Message(int moduleID, int sequenceID, int b, int c, int d, int e) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID,
                computeVarInt32Size(b) + computeVarInt32Size(c) + computeVarInt32Size(d) + computeVarInt32Size(e));
        writeVarInt32(buffer, b);
        writeVarInt32(buffer, c);
        writeVarInt32(buffer, d);
        writeVarInt32(buffer, e);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd5VarInt32Message(int moduleID, int sequenceID, int b, int c, int d, int e, int g) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID,
                computeVarInt32Size(b) + computeVarInt32Size(c) + computeVarInt32Size(d) + computeVarInt32Size(e) + computeVarInt32Size(g));
        writeVarInt32(buffer, b);
        writeVarInt32(buffer, c);
        writeVarInt32(buffer, d);
        writeVarInt32(buffer, e);
        writeVarInt32(buffer, g);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAStringMessage(int moduleID, int sequenceID, String b) {
        byte[] bytes = StringEncoder.encode(b);
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, 2 + bytes.length);
        writeUTF(buffer, bytes);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAStringAndABytesMessage(int moduleID, int sequenceID, String b, byte[] c) {
        byte[] bytes = StringEncoder.encode(b);
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, 4 + bytes.length + c.length);
        writeUTF(buffer, bytes);
        writeUTF(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt64Message(int moduleID, int sequenceID, long b) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt64Size(b));
        writeVarInt64(buffer, b);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd2VarInt64Message(int moduleID, int sequenceID, long b, long c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt64Size(b) + computeVarInt64Size(c));
        writeVarInt64(buffer, b);
        writeVarInt64(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAndAVarInt32With2VarInt64Message(int moduleID, int sequenceID, int a, long b, long c) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt32Size(a) + computeVarInt64Size(b) + computeVarInt64Size(c));
        writeVarInt32(buffer, a);
        writeVarInt64(buffer, b);
        writeVarInt64(buffer, c);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd3VarInt64Message(int moduleID, int sequenceID, long b, long c, long d) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, computeVarInt64Size(b) + computeVarInt64Size(c) + computeVarInt64Size(d));
        writeVarInt64(buffer, b);
        writeVarInt64(buffer, c);
        writeVarInt64(buffer, d);
        return buffer;
    }

    public static ChannelBuffer onlySendHeadAnd4VarInt64Message(int moduleID, int sequenceID, long b, long c, long d, long e) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID,
                computeVarInt64Size(b) + computeVarInt64Size(c) + computeVarInt64Size(d) + computeVarInt64Size(e));
        writeVarInt64(buffer, b);
        writeVarInt64(buffer, c);
        writeVarInt64(buffer, d);
        writeVarInt64(buffer, e);
        return buffer;
    }

    public static ChannelBuffer onlySendHeaderAndVarIntsMessage(int moduleID, int sequenceID, int... value) {
        int size = 0;
        for (int i : value) {
            size += computeVarInt32Size(i);
        }
        ChannelBuffer result = newFixedSizeMessage(moduleID, sequenceID, size);
        for (int i : value) {
            writeVarInt32(result, i);
        }
        return result;
    }

    public static ChannelBuffer onlySendHeadAndBytesMessage(int moduleID, int sequenceID, byte[] data) {
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, data.length + 2);
        writeUTF(buffer, data);
        return buffer;
    }

    public static ChannelBuffer newProtobufMessage(int moduleID, int sequenceID, MessageLite msg) {
        byte[] bs = msg.toByteArray();
        ChannelBuffer buffer = newFixedSizeMessage(moduleID, sequenceID, 2 + bs.length);
        writeUTF(buffer, bs);
        return buffer;
    }

    public static void writeProto(ChannelBuffer buffer, MessageLite msg) {
        int msgSize = msg.getSerializedSize(); // cached
        if (buffer.writableBytes() < msgSize) {
            // 大小是可变的
            buffer.ensureWritableBytes(msgSize);
        }

        if (buffer.hasArray()) {
            byte[] array = buffer.array();
            CodedOutputStream out = CodedOutputStream.newInstance(array, buffer.writerIndex(), msgSize);
            try {
                msg.writeTo(out);
            } catch (IOException e) {
                throw new RuntimeException("Serializing to a byte array threw an IOException " + "(should never happen).", e);
            }
            buffer.writerIndex(buffer.writerIndex() + msgSize);
        } else {
            buffer.writeBytes(msg.toByteArray());
        }
    }

    public static void writeUTFProto(ChannelBuffer buffer, MessageLite msg) {
        int msgSize = msg.getSerializedSize(); // cached
        buffer.writeShort(msgSize);
        writeProto(buffer, msg);
    }

    @SuppressWarnings("unchecked")
    public static <T extends MessageLite> T readProto(T defaultInstance, ChannelBuffer buf) throws IOException {
        if (buf.hasArray()) {
            final int offset = buf.readerIndex();
            return (T) defaultInstance.getParserForType().parseFrom(buf.array(), buf.arrayOffset() + offset, buf.readableBytes());
        } else {
            return (T) defaultInstance.getParserForType().parseFrom(new ChannelBufferInputStream(buf));
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends MessageLite> T readProto(T defaultInstance, ChannelBuffer buf, int offset) throws IOException {
        if (buf.hasArray()) {
            return (T) defaultInstance.getParserForType()
                    .parseFrom(buf.array(), buf.arrayOffset() + buf.readerIndex() + offset, buf.readableBytes() - offset);
        } else {
            return (T) defaultInstance.getParserForType().parseFrom(new ChannelBufferInputStream(buf));
        }
    }

    public static ChannelBuffer getCompressedMessage(int moduleID, int sequenceID, ChannelBuffer buffer) {
        ChannelBuffer result = newFixedSizeMessage(moduleID, sequenceID, getMaxCompressedSize(buffer.writerIndex()));
        compressBuffer(buffer, result);
        return result;
    }

    public static ChannelBuffer getCompressedMessage(int moduleID, int sequenceID, byte[] data, boolean bestCompression) {
        ChannelBuffer result = newFixedSizeMessage(moduleID, sequenceID, getMaxCompressedSize(data.length));
        compressBuffer(data, result, bestCompression);
        return result;
    }

    public static int getMaxCompressedSize(int size) {
        return ((int) Math.ceil(size * 1.001)) + 44;
    }

    public static void compressBuffer(ChannelBuffer input, ChannelBuffer output) {
        int length = input.writerIndex();
        ZStream z = new ZStream();
        z.deflateInit(JZlib.Z_BEST_COMPRESSION);

        z.next_in = input.array();
        z.avail_in = length;

        z.next_out = output.array();
        z.next_out_index = output.writerIndex();
        z.avail_out = output.writableBytes();

        int resultCode = z.deflate(JZlib.Z_FINISH);

        if (resultCode != JZlib.Z_OK && resultCode != JZlib.Z_STREAM_END) {
            System.err.println("压缩信息出错, code: " + resultCode);
        }

        output.writerIndex(z.next_out_index);

        z.deflateEnd();
    }

    public static void compressBuffer(byte[] input, ChannelBuffer output, boolean bestCompression) {
        compressBuffer(input, 0, input.length, output, bestCompression);
    }

    public static void compressBuffer(byte[] input, int off, int len, ChannelBuffer output, boolean bestCompression) {
        assert off >= 0 && len >= 0 && off + len <= input.length;

        ZStream z = new ZStream();
        z.deflateInit(bestCompression ? JZlib.Z_BEST_COMPRESSION : JZlib.Z_DEFAULT_COMPRESSION);

        z.next_in = input;
        z.next_in_index = off;
        z.avail_in = len;

        z.next_out = output.array();
        z.next_out_index = output.writerIndex();
        z.avail_out = output.writableBytes();

        int resultCode = z.deflate(JZlib.Z_FINISH);

        if (resultCode != JZlib.Z_OK && resultCode != JZlib.Z_STREAM_END) {
            System.err.println("压缩信息出错, code: " + resultCode);
        }

        output.writerIndex(z.next_out_index);

        z.deflateEnd();
    }

    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static final long readHeroId(ChannelBuffer buffer) {
        return Long.parseLong(readUTF(buffer));
    }

    public static final String readUTF(ChannelBuffer buffer) {
        int len = buffer.readUnsignedShort();
        if (buffer.hasArray()) {
            byte[] s = buffer.array();
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            buffer.skipBytes(len); // 确保有这么多byte可读
            return new String(s, offset, len, UTF_8);
        } else {
            byte[] s = new byte[len];
            buffer.readBytes(s);
            return new String(s, UTF_8);
        }
    }

    public static final String readUTF(ChannelBuffer buffer, int limit) {
        assert limit > 0 : "illegal read utf limit";
        int len = buffer.readUnsignedShort();
        if (len > limit) {
            buffer.skipBytes(len); // 跳过这个utf占用的空间
            throw UTF_LENGTH_EXCEED_LIMIT;
        }
        if (buffer.hasArray()) {
            byte[] s = buffer.array();
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            buffer.skipBytes(len); // 确保有这么多byte可读
            return new String(s, offset, len, UTF_8);
        } else {
            byte[] s = new byte[len];
            buffer.readBytes(s);
            return new String(s, UTF_8);
        }
    }

    /**
     * 获取一个UTF所对应的byte[], 方法new String(byte[], "utf8") 将获得这个UTF
     *
     * @param buffer
     * @return
     */
    public static final byte[] readUTFBytes(ChannelBuffer buffer) {
        byte[] s = new byte[buffer.readUnsignedShort()];
        buffer.readBytes(s);
        return s;
    }

    private static final IllegalArgumentException UTF_LENGTH_EXCEED_LIMIT = new IllegalArgumentException("UTF length exceed limit! ");

    /**
     * 读取utf对应的byte[], 有最大长度上限, 防止外挂发送很大的String耗费内存
     *
     * @param buffer
     * @param limit
     * @return
     */
    public static final byte[] readUTFBytes(ChannelBuffer buffer, int limit) {
        assert limit > 0 : "illegal read utf limit";
        int length = buffer.readUnsignedShort();
        if (length > limit) {
            buffer.skipBytes(length); // 跳过这个utf占用的空间
            throw UTF_LENGTH_EXCEED_LIMIT;
        }
        byte[] s = new byte[length];
        buffer.readBytes(s);
        return s;
    }

    public static final boolean readBoolean(ChannelBuffer buffer) {
        return buffer.readByte() != 0;
    }

    public static void writeBytes(ChannelBuffer buffer, String s) {
        int length = s.length();
        if (length <= 0) {
            return;
        }

        for (int i = 0; i < length; i++) {
            buffer.writeByte(s.charAt(i));
        }
    }

    public static final void writeUTF(ChannelBuffer buffer, String toWrite) {
        if (StringUtils.isEmpty(toWrite)) {
            buffer.writeShort(0);
        } else {
            byte[] b = StringEncoder.encode(toWrite);
            buffer.writeShort(b.length);
            buffer.writeBytes(b);
        }
    }

    public static final void writeUTF(ChannelBuffer buffer, byte[] toWrite) {
        if (toWrite == null || toWrite.length == 0) {
            buffer.writeShort(0);
        } else {
            buffer.writeShort(toWrite.length);
            buffer.writeBytes(toWrite);
        }
    }

    public static int writeUTF(byte[] array, int index, byte[] toWrite) {
        int len = toWrite.length;

        array[index++] = (byte) (len >>> 8);
        array[index++] = (byte) len;

        System.arraycopy(toWrite, 0, array, index, len);
        index += len;
        return index;
    }

    public static int writeBytes(byte[] array, int index, byte[] toWrite) {
        int len = toWrite.length;
        System.arraycopy(toWrite, 0, array, index, len);
        index += len;
        return index;
    }

    // ---- varlen utf ----

    public static void writeVarUTF(ChannelBuffer buffer, String toWrite) {
        if (toWrite == null) {
            writeVarInt32(buffer, 0);
        } else {
            byte[] b = StringEncoder.encode(toWrite);
            writeVarInt32(buffer, b.length);
            buffer.writeBytes(b);
        }
    }

    public static void writeVarUTF(ChannelBuffer buffer, byte[] toWrite) {
        if (toWrite == null) {
            writeVarInt32(buffer, 0);
        } else {
            writeVarInt32(buffer, toWrite.length);
            buffer.writeBytes(toWrite);
        }
    }

    public static int writeVarUTF(byte[] array, int index, byte[] toWrite) {
        index = writeVarInt32(array, index, toWrite.length);
        return writeBytes(array, index, toWrite);
    }

    public static String readVarUTF(ChannelBuffer buffer) {
        int len = readVarInt32(buffer);
        if (buffer.hasArray()) {
            byte[] s = buffer.array();
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            buffer.skipBytes(len); // 确保有这么多byte可读
            return new String(s, offset, len, UTF_8);
        } else {
            byte[] s = new byte[len];
            buffer.readBytes(s);
            return new String(s, UTF_8);
        }
    }

    public static String readVarUTF(ChannelBuffer buffer, int limit) {
        int len = readVarInt32(buffer);
        if (len > limit) {
            buffer.skipBytes(len); // 跳过这个utf占用的空间
            throw UTF_LENGTH_EXCEED_LIMIT;
        }
        if (buffer.hasArray()) {
            byte[] s = buffer.array();
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            buffer.skipBytes(len); // 确保有这么多byte可读
            return new String(s, offset, len, UTF_8);
        } else {
            byte[] s = new byte[len];
            buffer.readBytes(s);
            return new String(s, UTF_8);
        }
    }

    public static byte[] readVarUTFBytes(ChannelBuffer buffer) {
        byte[] s = new byte[readVarInt32(buffer)];
        buffer.readBytes(s);
        return s;
    }

    public static final byte[] readVarUTFBytes(ChannelBuffer buffer, int limit) {
        assert limit > 0 : "illegal read utf limit";
        int length = readVarInt32(buffer);
        if (length > limit) {
            buffer.skipBytes(length); // 跳过这个utf占用的空间
            throw UTF_LENGTH_EXCEED_LIMIT;
        }
        byte[] s = new byte[length];
        buffer.readBytes(s);
        return s;
    }

    // ---- tiny utf ----

    public static final String readTinyUTF(ChannelBuffer buffer) {
        int len = buffer.readUnsignedByte();
        if (buffer.hasArray()) {
            byte[] s = buffer.array();
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            buffer.skipBytes(len); // 确保有这么多byte可读
            return new String(s, offset, len, UTF_8);
        } else {
            byte[] s = new byte[len];
            buffer.readBytes(s);
            return new String(s, UTF_8);
        }
    }

    public static final String readTinyUTF(ChannelBuffer buffer, int limit) {
        assert limit > 0 : "illegal read utf limit";
        int len = buffer.readUnsignedByte();
        if (len > limit) {
            buffer.skipBytes(len); // 跳过这个utf占用的空间
            throw UTF_LENGTH_EXCEED_LIMIT;
        }
        if (buffer.hasArray()) {
            byte[] s = buffer.array();
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            buffer.skipBytes(len); // 确保有这么多byte可读
            return new String(s, offset, len, UTF_8);
        } else {
            byte[] s = new byte[len];
            buffer.readBytes(s);
            return new String(s, UTF_8);
        }
    }

    /**
     * 获取一个UTF所对应的byte[], 方法new String(byte[], "utf8") 将获得这个UTF
     *
     * @param buffer
     * @return
     */
    public static final byte[] readTinyUTFBytes(ChannelBuffer buffer) {
        byte[] s = new byte[buffer.readUnsignedByte()];
        buffer.readBytes(s);
        return s;
    }

    /**
     * 读取utf对应的byte[], 有最大长度上限, 防止外挂发送很大的String耗费内存
     *
     * @param buffer
     * @param limit
     * @return
     */
    public static final byte[] readTinyUTFBytes(ChannelBuffer buffer, int limit) {
        assert limit > 0 : "illegal read utf limit";
        int length = buffer.readUnsignedByte();
        if (length > limit) {
            buffer.skipBytes(length); // 跳过这个utf占用的空间
            throw UTF_LENGTH_EXCEED_LIMIT;
        }
        byte[] s = new byte[length];
        buffer.readBytes(s);
        return s;
    }

    public static final void writeTinyUTF(ChannelBuffer buffer, String toWrite) {
        if (toWrite == null) {
            buffer.writeByte(0);
        } else {
            byte[] b = StringEncoder.encode(toWrite);
            buffer.writeByte(b.length);
            buffer.writeBytes(b);
        }
    }

    public static final void writeTinyUTF(ChannelBuffer buffer, byte[] toWrite) {
        if (toWrite == null) {
            buffer.writeByte(0);
        } else {
            buffer.writeByte(toWrite.length);
            buffer.writeBytes(toWrite);
        }
    }

    // --- end of tiny utf ---

    public static final void writeBoolean(ChannelBuffer buffer, boolean toWrite) {
        buffer.writeByte(toWrite ? 1 : 0);
    }

    public static final void writeTrue(ChannelBuffer buffer) {
        buffer.writeByte(1);
    }

    public static final void writeFalse(ChannelBuffer buffer) {
        buffer.writeByte(0);
    }

    /**
     * 注意：length是byte
     *
     * @param buffer
     * @param toWrite
     */
    public static final void writeIntArray(ChannelBuffer buffer, int[] toWrite) {
        if (toWrite == null) {
            buffer.writeByte(0);
        } else {
            buffer.writeByte(toWrite.length);
            for (int i : toWrite) {
                buffer.writeInt(i);
            }
        }
    }

    public static final ChannelBuffer trimBuffer(ChannelBuffer buffer) {
        ChannelBuffer result = newFixedSizeMessage(buffer.writerIndex());
        result.writeBytes(buffer);
        return result;
    }

    public static final int writeShort(byte[] array, int index, int value) {
        array[index] = (byte) (value >>> 8);
        array[index + 1] = (byte) (value >>> 0);
        return index + 1;
    }

    public static final int writeMedium(byte[] array, int index, int value) {
        array[index] = (byte) (value >>> 16);
        array[index + 1] = (byte) (value >>> 8);
        array[index + 2] = (byte) (value >>> 0);
        return index + 2;
    }

    // ------- VarInt

    private static final RuntimeException MALFORMED_VarInt = new RuntimeException("Malformed VarInt");


    /**
     * 从ChannelBuffer中读取一个VarInt. 如果大于32bit, 更高位的bit将会被抛弃
     */
    public static final int readVarInt32(ChannelBuffer buffer) {
        byte tmp = buffer.readByte();
        if (tmp >= 0) {
            return tmp;
        }
        int result = tmp & 0x7f;
        if ((tmp = buffer.readByte()) >= 0) {
            result |= tmp << 7;
        } else {
            result |= (tmp & 0x7f) << 7;
            if ((tmp = buffer.readByte()) >= 0) {
                result |= tmp << 14;
            } else {
                result |= (tmp & 0x7f) << 14;
                if ((tmp = buffer.readByte()) >= 0) {
                    result |= tmp << 21;
                } else {
                    result |= (tmp & 0x7f) << 21;
                    result |= (tmp = buffer.readByte()) << 28;
                    if (tmp < 0) {
                        // Discard upper 32 bits.
                        for (int i = 0; i < 5; i++) {
                            if (buffer.readByte() >= 0) {
                                return result;
                            }
                        }
                        throw MALFORMED_VarInt;
                    }
                }
            }
        }
        return result;
    }

    /** Read a raw VarInt from the stream. */
    public static long readVarInt64(ChannelBuffer buffer) {
        int shift = 0;
        long result = 0;
        while (shift < 64) {
            final byte b = buffer.readByte();
            result |= (long) (b & 0x7F) << shift;
            if ((b & 0x80) == 0) {
                return result;
            }
            shift += 7;
        }
        throw MALFORMED_VarInt;
    }

    /**
     * Compute the number of bytes that would be needed to encode an
     * {@code int64} field, including tag.
     */
    public static int computeVarInt64Size(final long value) {
        if ((value & (0xffffffffffffffffL << 7)) == 0) {
            return 1;
        }
        if ((value & (0xffffffffffffffffL << 14)) == 0) {
            return 2;
        }
        if ((value & (0xffffffffffffffffL << 21)) == 0) {
            return 3;
        }
        if ((value & (0xffffffffffffffffL << 28)) == 0) {
            return 4;
        }
        if ((value & (0xffffffffffffffffL << 35)) == 0) {
            return 5;
        }
        if ((value & (0xffffffffffffffffL << 42)) == 0) {
            return 6;
        }
        if ((value & (0xffffffffffffffffL << 49)) == 0) {
            return 7;
        }
        if ((value & (0xffffffffffffffffL << 56)) == 0) {
            return 8;
        }
        if ((value & (0xffffffffffffffffL << 63)) == 0) {
            return 9;
        }
        return 10;
    }

    /**
     * Compute the number of bytes that would be needed to encode a VarInt.
     * {@code value} is treated as unsigned, so it won't be sign-extended if
     * negative.
     */
    public static int computeVarInt32Size(final int value) {
        if ((value & (0xffffffff << 7)) == 0) {
            return 1;
        }
        if ((value & (0xffffffff << 14)) == 0) {
            return 2;
        }
        if ((value & (0xffffffff << 21)) == 0) {
            return 3;
        }
        if ((value & (0xffffffff << 28)) == 0) {
            return 4;
        }
        return 5;
    }

    /**
     * 将一个int写入ChannelBuffer
     */
    public static void writeVarInt32(ChannelBuffer buffer, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                buffer.writeByte(value);
                return;
            } else {
                buffer.writeByte((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    public static final int computeUTF(String s) {
        return 2 + StringEncoder.encode(s).length;
    }

    /**
     * 将数值以VarInt32的形式写入数组
     *
     * @param array
     * @param index
     * @param value
     *            下一个可写入的数组index
     * @return
     */
    public static int writeVarInt32(byte[] array, int index, int value) {
        while (true) {
            if ((value & ~0x7F) == 0) {
                array[index++] = (byte) value;
                return index;
            } else {
                array[index++] = (byte) ((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    public static int writeVarInt64(byte[] array, int index, long value) {
        while (true) {
            if ((value & ~0x7FL) == 0) {
                array[index++] = (byte) value;
                return index;
            } else {
                array[index++] = (byte) ((value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    /**
     * 将一个long写入ChannelBuffer
     *
     * @param buffer
     * @param value
     */
    public static void writeVarInt64(ChannelBuffer buffer, long value) {
        while (true) {
            if ((value & ~0x7FL) == 0) {
                buffer.writeByte((int) value);
                return;
            } else {
                buffer.writeByte(((int) value & 0x7F) | 0x80);
                value >>>= 7;
            }
        }
    }

    public static byte[] readLeftBytes(ChannelBuffer buffer, int limit) {
        int bytesLength = buffer.readableBytes();

        byte[] data;
        if (bytesLength <= limit) {
            data = new byte[bytesLength];
        } else {
            data = new byte[limit];
        }

        buffer.readBytes(data);
        return data;
    }

    public static String readLeftUTF(ChannelBuffer buffer, int limit) {
        assert limit > 0 : "illegal read utf limit";
        int len = buffer.readableBytes();
        if (len > limit) {
            buffer.skipBytes(len); // 跳过这个utf占用的空间
            throw UTF_LENGTH_EXCEED_LIMIT;
        }

        if (buffer.hasArray()) {
            byte[] s = buffer.array();
            int offset = buffer.arrayOffset() + buffer.readerIndex();
            buffer.skipBytes(len); // 确保有这么多byte可读
            return new String(s, offset, len, UTF_8);
        } else {
            byte[] s = new byte[len];
            buffer.readBytes(s);
            return new String(s, UTF_8);
        }
    }
}
