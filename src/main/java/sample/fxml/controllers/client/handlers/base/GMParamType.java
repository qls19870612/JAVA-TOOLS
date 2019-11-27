package sample.fxml.controllers.client.handlers.base;

import sample.utils.StringUtils;

/**
 *
 * 创建人  liangsong
 * 创建时间 2018/08/03 0:38
 */
public enum GMParamType {
    INT("int") {
        @Override
        public Object convert(String s) {

            return StringUtils.safeParseInt(s, 0);
        }
    },

    LONG("long") {
        @Override
        public Object convert(String s) {
            return StringUtils.safeParseLong(s, 0);
        }
    },

    BOOLEAN("boolean") {
        @Override
        public Object convert(String s) {
            return Boolean.parseBoolean(s);
        }
    },

    STRING("String") {
        @Override
        public Object convert(String s) {
            return s;
        }
    },;
    //    NO(0), VARINT32(4), VARINT64(8), STRING(0), BOOLEAN(1);
    //
    //    public final int size;
    //
    //    GMParamType(int size) {
    //        this.size = size;
    //    }
    //
    //    public int getValueSize(Object value) {
    //        if (this.equals(STRING)) {
    //            return BufferUtil.computeUTF((String) value);
    //        }
    //        return size;
    //    }
    //
    //    public void writeToBuff(ChannelBuffer ret, Object obj) {
    //        switch (this) {
    //            case STRING:
    //                BufferUtil.writeUTF(ret, (String) obj);
    //                break;
    //            case VARINT32:
    //                BufferUtil.writeVarInt32(ret, (Integer) obj);
    //                break;
    //            case VARINT64:
    //                BufferUtil.writeVarInt64(ret, (Long) obj);
    //                break;
    //            case BOOLEAN:
    //                BufferUtil.writeBoolean(ret, (Boolean) obj);
    //                break;
    //            default:
    //                throw new RuntimeException("GM参数 不支持的参数设定类型:" + this.name());
    //        }
    //
    //    }

    private final String type;

    GMParamType(String type) {
        this.type = type;
    }

    public static GMParamType of(String type) {
        String name = type.toUpperCase();
        return GMParamType.valueOf(name);
    }

    abstract public Object convert(String s);
}
