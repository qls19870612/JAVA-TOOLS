// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client/value.proto

package app.protobuf.client;

public final class ValueContent {
  private ValueContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  /**
   * Protobuf enum {@code proto.ValueType}
   *
   * <pre>
   * ------------ 货币类型 ------------
   * </pre>
   */
  public enum ValueType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>EXP = 0;</code>
     *
     * <pre>
     * 经验
     * </pre>
     */
    EXP(0, 0),
    /**
     * <code>GOLD = 1;</code>
     *
     * <pre>
     * 非绑金币
     * </pre>
     */
    GOLD(1, 1),
    /**
     * <code>BIND_GOLD = 2;</code>
     *
     * <pre>
     * 绑定金币
     * </pre>
     */
    BIND_GOLD(2, 2),
    /**
     * <code>SILVER = 3;</code>
     *
     * <pre>
     * 银币
     * </pre>
     */
    SILVER(3, 3),
    ;

    /**
     * <code>EXP = 0;</code>
     *
     * <pre>
     * 经验
     * </pre>
     */
    public static final int EXP_VALUE = 0;
    /**
     * <code>GOLD = 1;</code>
     *
     * <pre>
     * 非绑金币
     * </pre>
     */
    public static final int GOLD_VALUE = 1;
    /**
     * <code>BIND_GOLD = 2;</code>
     *
     * <pre>
     * 绑定金币
     * </pre>
     */
    public static final int BIND_GOLD_VALUE = 2;
    /**
     * <code>SILVER = 3;</code>
     *
     * <pre>
     * 银币
     * </pre>
     */
    public static final int SILVER_VALUE = 3;


    public final int getNumber() { return value; }

    public static ValueType valueOf(int value) {
      switch (value) {
        case 0: return EXP;
        case 1: return GOLD;
        case 2: return BIND_GOLD;
        case 3: return SILVER;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<ValueType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static com.google.protobuf.Internal.EnumLiteMap<ValueType>
        internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<ValueType>() {
            public ValueType findValueByNumber(int number) {
              return ValueType.valueOf(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return app.protobuf.client.ValueContent.getDescriptor().getEnumTypes().get(0);
    }

    private static final ValueType[] VALUES = values();

    public static ValueType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private ValueType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:proto.ValueType)
  }

  public interface ValueProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.ValueProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional .proto.ValueType type = 1;</code>
     *
     * <pre>
     * 类型
     * </pre>
     */
    boolean hasType();
    /**
     * <code>optional .proto.ValueType type = 1;</code>
     *
     * <pre>
     * 类型
     * </pre>
     */
    app.protobuf.client.ValueContent.ValueType getType();

    /**
     * <code>optional int64 value = 2;</code>
     *
     * <pre>
     * 数值
     * </pre>
     */
    boolean hasValue();
    /**
     * <code>optional int64 value = 2;</code>
     *
     * <pre>
     * 数值
     * </pre>
     */
    long getValue();
  }
  /**
   * Protobuf type {@code proto.ValueProto}
   */
  public static final class ValueProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.ValueProto)
      ValueProtoOrBuilder {
    // Use ValueProto.newBuilder() to construct.
    private ValueProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ValueProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ValueProto defaultInstance;
    public static ValueProto getDefaultInstance() {
      return defaultInstance;
    }

    public ValueProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ValueProto(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              int rawValue = input.readEnum();
              app.protobuf.client.ValueContent.ValueType value = app.protobuf.client.ValueContent.ValueType.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(1, rawValue);
              } else {
                bitField0_ |= 0x00000001;
                type_ = value;
              }
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              value_ = input.readInt64();
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return app.protobuf.client.ValueContent.internal_static_proto_ValueProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.client.ValueContent.internal_static_proto_ValueProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.client.ValueContent.ValueProto.class, app.protobuf.client.ValueContent.ValueProto.Builder.class);
    }

    public static com.google.protobuf.Parser<ValueProto> PARSER =
        new com.google.protobuf.AbstractParser<ValueProto>() {
      public ValueProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ValueProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ValueProto> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TYPE_FIELD_NUMBER = 1;
    private app.protobuf.client.ValueContent.ValueType type_;
    /**
     * <code>optional .proto.ValueType type = 1;</code>
     *
     * <pre>
     * 类型
     * </pre>
     */
    public boolean hasType() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional .proto.ValueType type = 1;</code>
     *
     * <pre>
     * 类型
     * </pre>
     */
    public app.protobuf.client.ValueContent.ValueType getType() {
      return type_;
    }

    public static final int VALUE_FIELD_NUMBER = 2;
    private long value_;
    /**
     * <code>optional int64 value = 2;</code>
     *
     * <pre>
     * 数值
     * </pre>
     */
    public boolean hasValue() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int64 value = 2;</code>
     *
     * <pre>
     * 数值
     * </pre>
     */
    public long getValue() {
      return value_;
    }

    private void initFields() {
      type_ = app.protobuf.client.ValueContent.ValueType.EXP;
      value_ = 0L;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeEnum(1, type_.getNumber());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt64(2, value_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(1, type_.getNumber());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, value_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static app.protobuf.client.ValueContent.ValueProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.ValueContent.ValueProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.ValueContent.ValueProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.ValueContent.ValueProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.ValueContent.ValueProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.ValueContent.ValueProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.ValueContent.ValueProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.client.ValueContent.ValueProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.ValueContent.ValueProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.ValueContent.ValueProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.client.ValueContent.ValueProto prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code proto.ValueProto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.ValueProto)
        app.protobuf.client.ValueContent.ValueProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.client.ValueContent.internal_static_proto_ValueProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.client.ValueContent.internal_static_proto_ValueProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.client.ValueContent.ValueProto.class, app.protobuf.client.ValueContent.ValueProto.Builder.class);
      }

      // Construct using app.protobuf.client.ValueContent.ValueProto.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        type_ = app.protobuf.client.ValueContent.ValueType.EXP;
        bitField0_ = (bitField0_ & ~0x00000001);
        value_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.client.ValueContent.internal_static_proto_ValueProto_descriptor;
      }

      public app.protobuf.client.ValueContent.ValueProto getDefaultInstanceForType() {
        return app.protobuf.client.ValueContent.ValueProto.getDefaultInstance();
      }

      public app.protobuf.client.ValueContent.ValueProto build() {
        app.protobuf.client.ValueContent.ValueProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.client.ValueContent.ValueProto buildPartial() {
        app.protobuf.client.ValueContent.ValueProto result = new app.protobuf.client.ValueContent.ValueProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.type_ = type_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.value_ = value_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.client.ValueContent.ValueProto) {
          return mergeFrom((app.protobuf.client.ValueContent.ValueProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.client.ValueContent.ValueProto other) {
        if (other == app.protobuf.client.ValueContent.ValueProto.getDefaultInstance()) return this;
        if (other.hasType()) {
          setType(other.getType());
        }
        if (other.hasValue()) {
          setValue(other.getValue());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        app.protobuf.client.ValueContent.ValueProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.client.ValueContent.ValueProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private app.protobuf.client.ValueContent.ValueType type_ = app.protobuf.client.ValueContent.ValueType.EXP;
      /**
       * <code>optional .proto.ValueType type = 1;</code>
       *
       * <pre>
       * 类型
       * </pre>
       */
      public boolean hasType() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional .proto.ValueType type = 1;</code>
       *
       * <pre>
       * 类型
       * </pre>
       */
      public app.protobuf.client.ValueContent.ValueType getType() {
        return type_;
      }
      /**
       * <code>optional .proto.ValueType type = 1;</code>
       *
       * <pre>
       * 类型
       * </pre>
       */
      public Builder setType(app.protobuf.client.ValueContent.ValueType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        type_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional .proto.ValueType type = 1;</code>
       *
       * <pre>
       * 类型
       * </pre>
       */
      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000001);
        type_ = app.protobuf.client.ValueContent.ValueType.EXP;
        onChanged();
        return this;
      }

      private long value_ ;
      /**
       * <code>optional int64 value = 2;</code>
       *
       * <pre>
       * 数值
       * </pre>
       */
      public boolean hasValue() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int64 value = 2;</code>
       *
       * <pre>
       * 数值
       * </pre>
       */
      public long getValue() {
        return value_;
      }
      /**
       * <code>optional int64 value = 2;</code>
       *
       * <pre>
       * 数值
       * </pre>
       */
      public Builder setValue(long value) {
        bitField0_ |= 0x00000002;
        value_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 value = 2;</code>
       *
       * <pre>
       * 数值
       * </pre>
       */
      public Builder clearValue() {
        bitField0_ = (bitField0_ & ~0x00000002);
        value_ = 0L;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.ValueProto)
    }

    static {
      defaultInstance = new ValueProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.ValueProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_ValueProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_ValueProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\022client/value.proto\022\005proto\";\n\nValueProt" +
      "o\022\036\n\004type\030\001 \001(\0162\020.proto.ValueType\022\r\n\005val" +
      "ue\030\002 \001(\003*9\n\tValueType\022\007\n\003EXP\020\000\022\010\n\004GOLD\020\001" +
      "\022\r\n\tBIND_GOLD\020\002\022\n\n\006SILVER\020\003B%\n\023app.proto" +
      "buf.clientB\014ValueContentH\001"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_proto_ValueProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_ValueProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_ValueProto_descriptor,
        new java.lang.String[] { "Type", "Value", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
