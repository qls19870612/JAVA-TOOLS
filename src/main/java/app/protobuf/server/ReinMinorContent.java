// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: server/rein_server.proto

package app.protobuf.server;

public final class ReinMinorContent {
  private ReinMinorContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface ReinModuleObjMinorObjOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.ReinModuleObjMinorObj)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 lastCompleteTaskId = 1;</code>
     *
     * <pre>
     * 最后一次完成的转职任务
     * </pre>
     */
    boolean hasLastCompleteTaskId();
    /**
     * <code>optional int32 lastCompleteTaskId = 1;</code>
     *
     * <pre>
     * 最后一次完成的转职任务
     * </pre>
     */
    int getLastCompleteTaskId();

    /**
     * <code>optional int32 doingReinLevel = 2;</code>
     */
    boolean hasDoingReinLevel();
    /**
     * <code>optional int32 doingReinLevel = 2;</code>
     */
    int getDoingReinLevel();

    /**
     * <code>optional int32 doingStage = 3;</code>
     */
    boolean hasDoingStage();
    /**
     * <code>optional int32 doingStage = 3;</code>
     */
    int getDoingStage();

    /**
     * <code>optional .proto.ReinStageStatus stageStatus = 4;</code>
     */
    boolean hasStageStatus();
    /**
     * <code>optional .proto.ReinStageStatus stageStatus = 4;</code>
     */
    app.protobuf.client.ReinClientContent.ReinStageStatus getStageStatus();

    /**
     * <code>optional int32 reachedTime = 5;</code>
     *
     * <pre>
     *转职的时间,处理排行榜
     * </pre>
     */
    boolean hasReachedTime();
    /**
     * <code>optional int32 reachedTime = 5;</code>
     *
     * <pre>
     *转职的时间,处理排行榜
     * </pre>
     */
    int getReachedTime();
  }
  /**
   * Protobuf type {@code proto.ReinModuleObjMinorObj}
   *
   * <pre>
   * </pre>
   */
  public static final class ReinModuleObjMinorObj extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.ReinModuleObjMinorObj)
      ReinModuleObjMinorObjOrBuilder {
    // Use ReinModuleObjMinorObj.newBuilder() to construct.
    private ReinModuleObjMinorObj(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private ReinModuleObjMinorObj(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final ReinModuleObjMinorObj defaultInstance;
    public static ReinModuleObjMinorObj getDefaultInstance() {
      return defaultInstance;
    }

    public ReinModuleObjMinorObj getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private ReinModuleObjMinorObj(
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
              bitField0_ |= 0x00000001;
              lastCompleteTaskId_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              doingReinLevel_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              doingStage_ = input.readInt32();
              break;
            }
            case 32: {
              int rawValue = input.readEnum();
              app.protobuf.client.ReinClientContent.ReinStageStatus value = app.protobuf.client.ReinClientContent.ReinStageStatus.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(4, rawValue);
              } else {
                bitField0_ |= 0x00000008;
                stageStatus_ = value;
              }
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              reachedTime_ = input.readInt32();
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
      return app.protobuf.server.ReinMinorContent.internal_static_proto_ReinModuleObjMinorObj_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.server.ReinMinorContent.internal_static_proto_ReinModuleObjMinorObj_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj.class, app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj.Builder.class);
    }

    public static com.google.protobuf.Parser<ReinModuleObjMinorObj> PARSER =
        new com.google.protobuf.AbstractParser<ReinModuleObjMinorObj>() {
      public ReinModuleObjMinorObj parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReinModuleObjMinorObj(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<ReinModuleObjMinorObj> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int LASTCOMPLETETASKID_FIELD_NUMBER = 1;
    private int lastCompleteTaskId_;
    /**
     * <code>optional int32 lastCompleteTaskId = 1;</code>
     *
     * <pre>
     * 最后一次完成的转职任务
     * </pre>
     */
    public boolean hasLastCompleteTaskId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 lastCompleteTaskId = 1;</code>
     *
     * <pre>
     * 最后一次完成的转职任务
     * </pre>
     */
    public int getLastCompleteTaskId() {
      return lastCompleteTaskId_;
    }

    public static final int DOINGREINLEVEL_FIELD_NUMBER = 2;
    private int doingReinLevel_;
    /**
     * <code>optional int32 doingReinLevel = 2;</code>
     */
    public boolean hasDoingReinLevel() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 doingReinLevel = 2;</code>
     */
    public int getDoingReinLevel() {
      return doingReinLevel_;
    }

    public static final int DOINGSTAGE_FIELD_NUMBER = 3;
    private int doingStage_;
    /**
     * <code>optional int32 doingStage = 3;</code>
     */
    public boolean hasDoingStage() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 doingStage = 3;</code>
     */
    public int getDoingStage() {
      return doingStage_;
    }

    public static final int STAGESTATUS_FIELD_NUMBER = 4;
    private app.protobuf.client.ReinClientContent.ReinStageStatus stageStatus_;
    /**
     * <code>optional .proto.ReinStageStatus stageStatus = 4;</code>
     */
    public boolean hasStageStatus() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional .proto.ReinStageStatus stageStatus = 4;</code>
     */
    public app.protobuf.client.ReinClientContent.ReinStageStatus getStageStatus() {
      return stageStatus_;
    }

    public static final int REACHEDTIME_FIELD_NUMBER = 5;
    private int reachedTime_;
    /**
     * <code>optional int32 reachedTime = 5;</code>
     *
     * <pre>
     *转职的时间,处理排行榜
     * </pre>
     */
    public boolean hasReachedTime() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>optional int32 reachedTime = 5;</code>
     *
     * <pre>
     *转职的时间,处理排行榜
     * </pre>
     */
    public int getReachedTime() {
      return reachedTime_;
    }

    private void initFields() {
      lastCompleteTaskId_ = 0;
      doingReinLevel_ = 0;
      doingStage_ = 0;
      stageStatus_ = app.protobuf.client.ReinClientContent.ReinStageStatus.STAGE_STATUS_WAITING_ENTER;
      reachedTime_ = 0;
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
        output.writeInt32(1, lastCompleteTaskId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, doingReinLevel_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, doingStage_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeEnum(4, stageStatus_.getNumber());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        output.writeInt32(5, reachedTime_);
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
          .computeInt32Size(1, lastCompleteTaskId_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, doingReinLevel_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, doingStage_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(4, stageStatus_.getNumber());
      }
      if (((bitField0_ & 0x00000010) == 0x00000010)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, reachedTime_);
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

    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj prototype) {
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
     * Protobuf type {@code proto.ReinModuleObjMinorObj}
     *
     * <pre>
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.ReinModuleObjMinorObj)
        app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObjOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.server.ReinMinorContent.internal_static_proto_ReinModuleObjMinorObj_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.server.ReinMinorContent.internal_static_proto_ReinModuleObjMinorObj_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj.class, app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj.Builder.class);
      }

      // Construct using app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj.newBuilder()
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
        lastCompleteTaskId_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        doingReinLevel_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        doingStage_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        stageStatus_ = app.protobuf.client.ReinClientContent.ReinStageStatus.STAGE_STATUS_WAITING_ENTER;
        bitField0_ = (bitField0_ & ~0x00000008);
        reachedTime_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.server.ReinMinorContent.internal_static_proto_ReinModuleObjMinorObj_descriptor;
      }

      public app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj getDefaultInstanceForType() {
        return app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj.getDefaultInstance();
      }

      public app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj build() {
        app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj buildPartial() {
        app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj result = new app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.lastCompleteTaskId_ = lastCompleteTaskId_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.doingReinLevel_ = doingReinLevel_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.doingStage_ = doingStage_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.stageStatus_ = stageStatus_;
        if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
          to_bitField0_ |= 0x00000010;
        }
        result.reachedTime_ = reachedTime_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj) {
          return mergeFrom((app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj other) {
        if (other == app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj.getDefaultInstance()) return this;
        if (other.hasLastCompleteTaskId()) {
          setLastCompleteTaskId(other.getLastCompleteTaskId());
        }
        if (other.hasDoingReinLevel()) {
          setDoingReinLevel(other.getDoingReinLevel());
        }
        if (other.hasDoingStage()) {
          setDoingStage(other.getDoingStage());
        }
        if (other.hasStageStatus()) {
          setStageStatus(other.getStageStatus());
        }
        if (other.hasReachedTime()) {
          setReachedTime(other.getReachedTime());
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
        app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.server.ReinMinorContent.ReinModuleObjMinorObj) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int lastCompleteTaskId_ ;
      /**
       * <code>optional int32 lastCompleteTaskId = 1;</code>
       *
       * <pre>
       * 最后一次完成的转职任务
       * </pre>
       */
      public boolean hasLastCompleteTaskId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 lastCompleteTaskId = 1;</code>
       *
       * <pre>
       * 最后一次完成的转职任务
       * </pre>
       */
      public int getLastCompleteTaskId() {
        return lastCompleteTaskId_;
      }
      /**
       * <code>optional int32 lastCompleteTaskId = 1;</code>
       *
       * <pre>
       * 最后一次完成的转职任务
       * </pre>
       */
      public Builder setLastCompleteTaskId(int value) {
        bitField0_ |= 0x00000001;
        lastCompleteTaskId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 lastCompleteTaskId = 1;</code>
       *
       * <pre>
       * 最后一次完成的转职任务
       * </pre>
       */
      public Builder clearLastCompleteTaskId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        lastCompleteTaskId_ = 0;
        onChanged();
        return this;
      }

      private int doingReinLevel_ ;
      /**
       * <code>optional int32 doingReinLevel = 2;</code>
       */
      public boolean hasDoingReinLevel() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 doingReinLevel = 2;</code>
       */
      public int getDoingReinLevel() {
        return doingReinLevel_;
      }
      /**
       * <code>optional int32 doingReinLevel = 2;</code>
       */
      public Builder setDoingReinLevel(int value) {
        bitField0_ |= 0x00000002;
        doingReinLevel_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 doingReinLevel = 2;</code>
       */
      public Builder clearDoingReinLevel() {
        bitField0_ = (bitField0_ & ~0x00000002);
        doingReinLevel_ = 0;
        onChanged();
        return this;
      }

      private int doingStage_ ;
      /**
       * <code>optional int32 doingStage = 3;</code>
       */
      public boolean hasDoingStage() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 doingStage = 3;</code>
       */
      public int getDoingStage() {
        return doingStage_;
      }
      /**
       * <code>optional int32 doingStage = 3;</code>
       */
      public Builder setDoingStage(int value) {
        bitField0_ |= 0x00000004;
        doingStage_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 doingStage = 3;</code>
       */
      public Builder clearDoingStage() {
        bitField0_ = (bitField0_ & ~0x00000004);
        doingStage_ = 0;
        onChanged();
        return this;
      }

      private app.protobuf.client.ReinClientContent.ReinStageStatus stageStatus_ = app.protobuf.client.ReinClientContent.ReinStageStatus.STAGE_STATUS_WAITING_ENTER;
      /**
       * <code>optional .proto.ReinStageStatus stageStatus = 4;</code>
       */
      public boolean hasStageStatus() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional .proto.ReinStageStatus stageStatus = 4;</code>
       */
      public app.protobuf.client.ReinClientContent.ReinStageStatus getStageStatus() {
        return stageStatus_;
      }
      /**
       * <code>optional .proto.ReinStageStatus stageStatus = 4;</code>
       */
      public Builder setStageStatus(app.protobuf.client.ReinClientContent.ReinStageStatus value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000008;
        stageStatus_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional .proto.ReinStageStatus stageStatus = 4;</code>
       */
      public Builder clearStageStatus() {
        bitField0_ = (bitField0_ & ~0x00000008);
        stageStatus_ = app.protobuf.client.ReinClientContent.ReinStageStatus.STAGE_STATUS_WAITING_ENTER;
        onChanged();
        return this;
      }

      private int reachedTime_ ;
      /**
       * <code>optional int32 reachedTime = 5;</code>
       *
       * <pre>
       *转职的时间,处理排行榜
       * </pre>
       */
      public boolean hasReachedTime() {
        return ((bitField0_ & 0x00000010) == 0x00000010);
      }
      /**
       * <code>optional int32 reachedTime = 5;</code>
       *
       * <pre>
       *转职的时间,处理排行榜
       * </pre>
       */
      public int getReachedTime() {
        return reachedTime_;
      }
      /**
       * <code>optional int32 reachedTime = 5;</code>
       *
       * <pre>
       *转职的时间,处理排行榜
       * </pre>
       */
      public Builder setReachedTime(int value) {
        bitField0_ |= 0x00000010;
        reachedTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 reachedTime = 5;</code>
       *
       * <pre>
       *转职的时间,处理排行榜
       * </pre>
       */
      public Builder clearReachedTime() {
        bitField0_ = (bitField0_ & ~0x00000010);
        reachedTime_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.ReinModuleObjMinorObj)
    }

    static {
      defaultInstance = new ReinModuleObjMinorObj(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.ReinModuleObjMinorObj)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_ReinModuleObjMinorObj_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_ReinModuleObjMinorObj_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030server/rein_server.proto\022\005proto\032\030clien" +
      "t/rein_client.proto\"\241\001\n\025ReinModuleObjMin" +
      "orObj\022\032\n\022lastCompleteTaskId\030\001 \001(\005\022\026\n\016doi" +
      "ngReinLevel\030\002 \001(\005\022\022\n\ndoingStage\030\003 \001(\005\022+\n" +
      "\013stageStatus\030\004 \001(\0162\026.proto.ReinStageStat" +
      "us\022\023\n\013reachedTime\030\005 \001(\005B)\n\023app.protobuf." +
      "serverB\020ReinMinorContentH\001"
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
          app.protobuf.client.ReinClientContent.getDescriptor(),
        }, assigner);
    internal_static_proto_ReinModuleObjMinorObj_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_ReinModuleObjMinorObj_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_ReinModuleObjMinorObj_descriptor,
        new java.lang.String[] { "LastCompleteTaskId", "DoingReinLevel", "DoingStage", "StageStatus", "ReachedTime", });
    app.protobuf.client.ReinClientContent.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}