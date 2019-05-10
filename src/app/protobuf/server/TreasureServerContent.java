// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: server/treasure_server.proto

package app.protobuf.server;

public final class TreasureServerContent {
  private TreasureServerContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface TreasureModuleObjMinorProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.TreasureModuleObjMinorProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional .proto.DurationProto time = 1;</code>
     *
     * <pre>
     * 当前活动时间
     * </pre>
     */
    boolean hasTime();
    /**
     * <code>optional .proto.DurationProto time = 1;</code>
     *
     * <pre>
     * 当前活动时间
     * </pre>
     */
    app.protobuf.client.UtilContent.DurationProto getTime();
    /**
     * <code>optional .proto.DurationProto time = 1;</code>
     *
     * <pre>
     * 当前活动时间
     * </pre>
     */
    app.protobuf.client.UtilContent.DurationProtoOrBuilder getTimeOrBuilder();

    /**
     * <code>optional int32 count = 2;</code>
     *
     * <pre>
     * 已领取的次数，如果当前活动时间和消息通知的活动时间不一致，将已领取次数置为0
     * </pre>
     */
    boolean hasCount();
    /**
     * <code>optional int32 count = 2;</code>
     *
     * <pre>
     * 已领取的次数，如果当前活动时间和消息通知的活动时间不一致，将已领取次数置为0
     * </pre>
     */
    int getCount();
  }
  /**
   * Protobuf type {@code proto.TreasureModuleObjMinorProto}
   */
  public static final class TreasureModuleObjMinorProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.TreasureModuleObjMinorProto)
      TreasureModuleObjMinorProtoOrBuilder {
    // Use TreasureModuleObjMinorProto.newBuilder() to construct.
    private TreasureModuleObjMinorProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private TreasureModuleObjMinorProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final TreasureModuleObjMinorProto defaultInstance;
    public static TreasureModuleObjMinorProto getDefaultInstance() {
      return defaultInstance;
    }

    public TreasureModuleObjMinorProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private TreasureModuleObjMinorProto(
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
            case 10: {
              app.protobuf.client.UtilContent.DurationProto.Builder subBuilder = null;
              if (((bitField0_ & 0x00000001) == 0x00000001)) {
                subBuilder = time_.toBuilder();
              }
              time_ = input.readMessage(app.protobuf.client.UtilContent.DurationProto.PARSER, extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(time_);
                time_ = subBuilder.buildPartial();
              }
              bitField0_ |= 0x00000001;
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              count_ = input.readInt32();
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
      return app.protobuf.server.TreasureServerContent.internal_static_proto_TreasureModuleObjMinorProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.server.TreasureServerContent.internal_static_proto_TreasureModuleObjMinorProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto.class, app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto.Builder.class);
    }

    public static com.google.protobuf.Parser<TreasureModuleObjMinorProto> PARSER =
        new com.google.protobuf.AbstractParser<TreasureModuleObjMinorProto>() {
      public TreasureModuleObjMinorProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new TreasureModuleObjMinorProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<TreasureModuleObjMinorProto> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int TIME_FIELD_NUMBER = 1;
    private app.protobuf.client.UtilContent.DurationProto time_;
    /**
     * <code>optional .proto.DurationProto time = 1;</code>
     *
     * <pre>
     * 当前活动时间
     * </pre>
     */
    public boolean hasTime() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional .proto.DurationProto time = 1;</code>
     *
     * <pre>
     * 当前活动时间
     * </pre>
     */
    public app.protobuf.client.UtilContent.DurationProto getTime() {
      return time_;
    }
    /**
     * <code>optional .proto.DurationProto time = 1;</code>
     *
     * <pre>
     * 当前活动时间
     * </pre>
     */
    public app.protobuf.client.UtilContent.DurationProtoOrBuilder getTimeOrBuilder() {
      return time_;
    }

    public static final int COUNT_FIELD_NUMBER = 2;
    private int count_;
    /**
     * <code>optional int32 count = 2;</code>
     *
     * <pre>
     * 已领取的次数，如果当前活动时间和消息通知的活动时间不一致，将已领取次数置为0
     * </pre>
     */
    public boolean hasCount() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 count = 2;</code>
     *
     * <pre>
     * 已领取的次数，如果当前活动时间和消息通知的活动时间不一致，将已领取次数置为0
     * </pre>
     */
    public int getCount() {
      return count_;
    }

    private void initFields() {
      time_ = app.protobuf.client.UtilContent.DurationProto.getDefaultInstance();
      count_ = 0;
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
        output.writeMessage(1, time_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, count_);
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
          .computeMessageSize(1, time_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, count_);
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

    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto prototype) {
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
     * Protobuf type {@code proto.TreasureModuleObjMinorProto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.TreasureModuleObjMinorProto)
        app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.server.TreasureServerContent.internal_static_proto_TreasureModuleObjMinorProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.server.TreasureServerContent.internal_static_proto_TreasureModuleObjMinorProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto.class, app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto.Builder.class);
      }

      // Construct using app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto.newBuilder()
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
          getTimeFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (timeBuilder_ == null) {
          time_ = app.protobuf.client.UtilContent.DurationProto.getDefaultInstance();
        } else {
          timeBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        count_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.server.TreasureServerContent.internal_static_proto_TreasureModuleObjMinorProto_descriptor;
      }

      public app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto getDefaultInstanceForType() {
        return app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto.getDefaultInstance();
      }

      public app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto build() {
        app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto buildPartial() {
        app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto result = new app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        if (timeBuilder_ == null) {
          result.time_ = time_;
        } else {
          result.time_ = timeBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.count_ = count_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto) {
          return mergeFrom((app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto other) {
        if (other == app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto.getDefaultInstance()) return this;
        if (other.hasTime()) {
          mergeTime(other.getTime());
        }
        if (other.hasCount()) {
          setCount(other.getCount());
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
        app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.server.TreasureServerContent.TreasureModuleObjMinorProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private app.protobuf.client.UtilContent.DurationProto time_ = app.protobuf.client.UtilContent.DurationProto.getDefaultInstance();
      private com.google.protobuf.SingleFieldBuilder<
          app.protobuf.client.UtilContent.DurationProto, app.protobuf.client.UtilContent.DurationProto.Builder, app.protobuf.client.UtilContent.DurationProtoOrBuilder> timeBuilder_;
      /**
       * <code>optional .proto.DurationProto time = 1;</code>
       *
       * <pre>
       * 当前活动时间
       * </pre>
       */
      public boolean hasTime() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional .proto.DurationProto time = 1;</code>
       *
       * <pre>
       * 当前活动时间
       * </pre>
       */
      public app.protobuf.client.UtilContent.DurationProto getTime() {
        if (timeBuilder_ == null) {
          return time_;
        } else {
          return timeBuilder_.getMessage();
        }
      }
      /**
       * <code>optional .proto.DurationProto time = 1;</code>
       *
       * <pre>
       * 当前活动时间
       * </pre>
       */
      public Builder setTime(app.protobuf.client.UtilContent.DurationProto value) {
        if (timeBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          time_ = value;
          onChanged();
        } else {
          timeBuilder_.setMessage(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .proto.DurationProto time = 1;</code>
       *
       * <pre>
       * 当前活动时间
       * </pre>
       */
      public Builder setTime(
          app.protobuf.client.UtilContent.DurationProto.Builder builderForValue) {
        if (timeBuilder_ == null) {
          time_ = builderForValue.build();
          onChanged();
        } else {
          timeBuilder_.setMessage(builderForValue.build());
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .proto.DurationProto time = 1;</code>
       *
       * <pre>
       * 当前活动时间
       * </pre>
       */
      public Builder mergeTime(app.protobuf.client.UtilContent.DurationProto value) {
        if (timeBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001) &&
              time_ != app.protobuf.client.UtilContent.DurationProto.getDefaultInstance()) {
            time_ =
              app.protobuf.client.UtilContent.DurationProto.newBuilder(time_).mergeFrom(value).buildPartial();
          } else {
            time_ = value;
          }
          onChanged();
        } else {
          timeBuilder_.mergeFrom(value);
        }
        bitField0_ |= 0x00000001;
        return this;
      }
      /**
       * <code>optional .proto.DurationProto time = 1;</code>
       *
       * <pre>
       * 当前活动时间
       * </pre>
       */
      public Builder clearTime() {
        if (timeBuilder_ == null) {
          time_ = app.protobuf.client.UtilContent.DurationProto.getDefaultInstance();
          onChanged();
        } else {
          timeBuilder_.clear();
        }
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }
      /**
       * <code>optional .proto.DurationProto time = 1;</code>
       *
       * <pre>
       * 当前活动时间
       * </pre>
       */
      public app.protobuf.client.UtilContent.DurationProto.Builder getTimeBuilder() {
        bitField0_ |= 0x00000001;
        onChanged();
        return getTimeFieldBuilder().getBuilder();
      }
      /**
       * <code>optional .proto.DurationProto time = 1;</code>
       *
       * <pre>
       * 当前活动时间
       * </pre>
       */
      public app.protobuf.client.UtilContent.DurationProtoOrBuilder getTimeOrBuilder() {
        if (timeBuilder_ != null) {
          return timeBuilder_.getMessageOrBuilder();
        } else {
          return time_;
        }
      }
      /**
       * <code>optional .proto.DurationProto time = 1;</code>
       *
       * <pre>
       * 当前活动时间
       * </pre>
       */
      private com.google.protobuf.SingleFieldBuilder<
          app.protobuf.client.UtilContent.DurationProto, app.protobuf.client.UtilContent.DurationProto.Builder, app.protobuf.client.UtilContent.DurationProtoOrBuilder> 
          getTimeFieldBuilder() {
        if (timeBuilder_ == null) {
          timeBuilder_ = new com.google.protobuf.SingleFieldBuilder<
              app.protobuf.client.UtilContent.DurationProto, app.protobuf.client.UtilContent.DurationProto.Builder, app.protobuf.client.UtilContent.DurationProtoOrBuilder>(
                  getTime(),
                  getParentForChildren(),
                  isClean());
          time_ = null;
        }
        return timeBuilder_;
      }

      private int count_ ;
      /**
       * <code>optional int32 count = 2;</code>
       *
       * <pre>
       * 已领取的次数，如果当前活动时间和消息通知的活动时间不一致，将已领取次数置为0
       * </pre>
       */
      public boolean hasCount() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 count = 2;</code>
       *
       * <pre>
       * 已领取的次数，如果当前活动时间和消息通知的活动时间不一致，将已领取次数置为0
       * </pre>
       */
      public int getCount() {
        return count_;
      }
      /**
       * <code>optional int32 count = 2;</code>
       *
       * <pre>
       * 已领取的次数，如果当前活动时间和消息通知的活动时间不一致，将已领取次数置为0
       * </pre>
       */
      public Builder setCount(int value) {
        bitField0_ |= 0x00000002;
        count_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 count = 2;</code>
       *
       * <pre>
       * 已领取的次数，如果当前活动时间和消息通知的活动时间不一致，将已领取次数置为0
       * </pre>
       */
      public Builder clearCount() {
        bitField0_ = (bitField0_ & ~0x00000002);
        count_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.TreasureModuleObjMinorProto)
    }

    static {
      defaultInstance = new TreasureModuleObjMinorProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.TreasureModuleObjMinorProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_TreasureModuleObjMinorProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_TreasureModuleObjMinorProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\034server/treasure_server.proto\022\005proto\032\021c" +
      "lient/util.proto\"P\n\033TreasureModuleObjMin" +
      "orProto\022\"\n\004time\030\001 \001(\0132\024.proto.DurationPr" +
      "oto\022\r\n\005count\030\002 \001(\005B.\n\023app.protobuf.serve" +
      "rB\025TreasureServerContentH\001"
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
          app.protobuf.client.UtilContent.getDescriptor(),
        }, assigner);
    internal_static_proto_TreasureModuleObjMinorProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_TreasureModuleObjMinorProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_TreasureModuleObjMinorProto_descriptor,
        new java.lang.String[] { "Time", "Count", });
    app.protobuf.client.UtilContent.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}