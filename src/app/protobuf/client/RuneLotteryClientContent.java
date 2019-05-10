// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client/rune_lottery_client.proto

package app.protobuf.client;

public final class RuneLotteryClientContent {
  private RuneLotteryClientContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface RuneLotteryModuleObjClientProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.RuneLotteryModuleObjClientProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 daily_lottery_times = 1;</code>
     *
     * <pre>
     *每日次数（0点重置时清除)
     * </pre>
     */
    boolean hasDailyLotteryTimes();
    /**
     * <code>optional int32 daily_lottery_times = 1;</code>
     *
     * <pre>
     *每日次数（0点重置时清除)
     * </pre>
     */
    int getDailyLotteryTimes();

    /**
     * <code>optional int32 weekly_lottery_times = 2;</code>
     *
     * <pre>
     *每周次数(每周重置时清除)
     * </pre>
     */
    boolean hasWeeklyLotteryTimes();
    /**
     * <code>optional int32 weekly_lottery_times = 2;</code>
     *
     * <pre>
     *每周次数(每周重置时清除)
     * </pre>
     */
    int getWeeklyLotteryTimes();

    /**
     * <code>optional int32 weekly_got_prize_info = 3;</code>
     *
     * <pre>
     *每周累计抽奖 奖励领取令牌（按位计算，每位代表对应奖励ID是否已经领取)
     * </pre>
     */
    boolean hasWeeklyGotPrizeInfo();
    /**
     * <code>optional int32 weekly_got_prize_info = 3;</code>
     *
     * <pre>
     *每周累计抽奖 奖励领取令牌（按位计算，每位代表对应奖励ID是否已经领取)
     * </pre>
     */
    int getWeeklyGotPrizeInfo();

    /**
     * <code>optional int32 next_free_lottery_time = 4;</code>
     *
     * <pre>
     *下一次免费时间点（秒)
     * </pre>
     */
    boolean hasNextFreeLotteryTime();
    /**
     * <code>optional int32 next_free_lottery_time = 4;</code>
     *
     * <pre>
     *下一次免费时间点（秒)
     * </pre>
     */
    int getNextFreeLotteryTime();
  }
  /**
   * Protobuf type {@code proto.RuneLotteryModuleObjClientProto}
   *
   * <pre>
   * </pre>
   */
  public static final class RuneLotteryModuleObjClientProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.RuneLotteryModuleObjClientProto)
      RuneLotteryModuleObjClientProtoOrBuilder {
    // Use RuneLotteryModuleObjClientProto.newBuilder() to construct.
    private RuneLotteryModuleObjClientProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private RuneLotteryModuleObjClientProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final RuneLotteryModuleObjClientProto defaultInstance;
    public static RuneLotteryModuleObjClientProto getDefaultInstance() {
      return defaultInstance;
    }

    public RuneLotteryModuleObjClientProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private RuneLotteryModuleObjClientProto(
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
              dailyLotteryTimes_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              weeklyLotteryTimes_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              weeklyGotPrizeInfo_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              nextFreeLotteryTime_ = input.readInt32();
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
      return app.protobuf.client.RuneLotteryClientContent.internal_static_proto_RuneLotteryModuleObjClientProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.client.RuneLotteryClientContent.internal_static_proto_RuneLotteryModuleObjClientProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto.class, app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto.Builder.class);
    }

    public static com.google.protobuf.Parser<RuneLotteryModuleObjClientProto> PARSER =
        new com.google.protobuf.AbstractParser<RuneLotteryModuleObjClientProto>() {
      public RuneLotteryModuleObjClientProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RuneLotteryModuleObjClientProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<RuneLotteryModuleObjClientProto> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int DAILY_LOTTERY_TIMES_FIELD_NUMBER = 1;
    private int dailyLotteryTimes_;
    /**
     * <code>optional int32 daily_lottery_times = 1;</code>
     *
     * <pre>
     *每日次数（0点重置时清除)
     * </pre>
     */
    public boolean hasDailyLotteryTimes() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 daily_lottery_times = 1;</code>
     *
     * <pre>
     *每日次数（0点重置时清除)
     * </pre>
     */
    public int getDailyLotteryTimes() {
      return dailyLotteryTimes_;
    }

    public static final int WEEKLY_LOTTERY_TIMES_FIELD_NUMBER = 2;
    private int weeklyLotteryTimes_;
    /**
     * <code>optional int32 weekly_lottery_times = 2;</code>
     *
     * <pre>
     *每周次数(每周重置时清除)
     * </pre>
     */
    public boolean hasWeeklyLotteryTimes() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 weekly_lottery_times = 2;</code>
     *
     * <pre>
     *每周次数(每周重置时清除)
     * </pre>
     */
    public int getWeeklyLotteryTimes() {
      return weeklyLotteryTimes_;
    }

    public static final int WEEKLY_GOT_PRIZE_INFO_FIELD_NUMBER = 3;
    private int weeklyGotPrizeInfo_;
    /**
     * <code>optional int32 weekly_got_prize_info = 3;</code>
     *
     * <pre>
     *每周累计抽奖 奖励领取令牌（按位计算，每位代表对应奖励ID是否已经领取)
     * </pre>
     */
    public boolean hasWeeklyGotPrizeInfo() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 weekly_got_prize_info = 3;</code>
     *
     * <pre>
     *每周累计抽奖 奖励领取令牌（按位计算，每位代表对应奖励ID是否已经领取)
     * </pre>
     */
    public int getWeeklyGotPrizeInfo() {
      return weeklyGotPrizeInfo_;
    }

    public static final int NEXT_FREE_LOTTERY_TIME_FIELD_NUMBER = 4;
    private int nextFreeLotteryTime_;
    /**
     * <code>optional int32 next_free_lottery_time = 4;</code>
     *
     * <pre>
     *下一次免费时间点（秒)
     * </pre>
     */
    public boolean hasNextFreeLotteryTime() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>optional int32 next_free_lottery_time = 4;</code>
     *
     * <pre>
     *下一次免费时间点（秒)
     * </pre>
     */
    public int getNextFreeLotteryTime() {
      return nextFreeLotteryTime_;
    }

    private void initFields() {
      dailyLotteryTimes_ = 0;
      weeklyLotteryTimes_ = 0;
      weeklyGotPrizeInfo_ = 0;
      nextFreeLotteryTime_ = 0;
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
        output.writeInt32(1, dailyLotteryTimes_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, weeklyLotteryTimes_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, weeklyGotPrizeInfo_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeInt32(4, nextFreeLotteryTime_);
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
          .computeInt32Size(1, dailyLotteryTimes_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, weeklyLotteryTimes_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, weeklyGotPrizeInfo_);
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, nextFreeLotteryTime_);
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

    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto prototype) {
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
     * Protobuf type {@code proto.RuneLotteryModuleObjClientProto}
     *
     * <pre>
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.RuneLotteryModuleObjClientProto)
        app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.client.RuneLotteryClientContent.internal_static_proto_RuneLotteryModuleObjClientProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.client.RuneLotteryClientContent.internal_static_proto_RuneLotteryModuleObjClientProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto.class, app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto.Builder.class);
      }

      // Construct using app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto.newBuilder()
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
        dailyLotteryTimes_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        weeklyLotteryTimes_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        weeklyGotPrizeInfo_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        nextFreeLotteryTime_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.client.RuneLotteryClientContent.internal_static_proto_RuneLotteryModuleObjClientProto_descriptor;
      }

      public app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto getDefaultInstanceForType() {
        return app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto.getDefaultInstance();
      }

      public app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto build() {
        app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto buildPartial() {
        app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto result = new app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.dailyLotteryTimes_ = dailyLotteryTimes_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.weeklyLotteryTimes_ = weeklyLotteryTimes_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.weeklyGotPrizeInfo_ = weeklyGotPrizeInfo_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.nextFreeLotteryTime_ = nextFreeLotteryTime_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto) {
          return mergeFrom((app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto other) {
        if (other == app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto.getDefaultInstance()) return this;
        if (other.hasDailyLotteryTimes()) {
          setDailyLotteryTimes(other.getDailyLotteryTimes());
        }
        if (other.hasWeeklyLotteryTimes()) {
          setWeeklyLotteryTimes(other.getWeeklyLotteryTimes());
        }
        if (other.hasWeeklyGotPrizeInfo()) {
          setWeeklyGotPrizeInfo(other.getWeeklyGotPrizeInfo());
        }
        if (other.hasNextFreeLotteryTime()) {
          setNextFreeLotteryTime(other.getNextFreeLotteryTime());
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
        app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.client.RuneLotteryClientContent.RuneLotteryModuleObjClientProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int dailyLotteryTimes_ ;
      /**
       * <code>optional int32 daily_lottery_times = 1;</code>
       *
       * <pre>
       *每日次数（0点重置时清除)
       * </pre>
       */
      public boolean hasDailyLotteryTimes() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 daily_lottery_times = 1;</code>
       *
       * <pre>
       *每日次数（0点重置时清除)
       * </pre>
       */
      public int getDailyLotteryTimes() {
        return dailyLotteryTimes_;
      }
      /**
       * <code>optional int32 daily_lottery_times = 1;</code>
       *
       * <pre>
       *每日次数（0点重置时清除)
       * </pre>
       */
      public Builder setDailyLotteryTimes(int value) {
        bitField0_ |= 0x00000001;
        dailyLotteryTimes_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 daily_lottery_times = 1;</code>
       *
       * <pre>
       *每日次数（0点重置时清除)
       * </pre>
       */
      public Builder clearDailyLotteryTimes() {
        bitField0_ = (bitField0_ & ~0x00000001);
        dailyLotteryTimes_ = 0;
        onChanged();
        return this;
      }

      private int weeklyLotteryTimes_ ;
      /**
       * <code>optional int32 weekly_lottery_times = 2;</code>
       *
       * <pre>
       *每周次数(每周重置时清除)
       * </pre>
       */
      public boolean hasWeeklyLotteryTimes() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 weekly_lottery_times = 2;</code>
       *
       * <pre>
       *每周次数(每周重置时清除)
       * </pre>
       */
      public int getWeeklyLotteryTimes() {
        return weeklyLotteryTimes_;
      }
      /**
       * <code>optional int32 weekly_lottery_times = 2;</code>
       *
       * <pre>
       *每周次数(每周重置时清除)
       * </pre>
       */
      public Builder setWeeklyLotteryTimes(int value) {
        bitField0_ |= 0x00000002;
        weeklyLotteryTimes_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 weekly_lottery_times = 2;</code>
       *
       * <pre>
       *每周次数(每周重置时清除)
       * </pre>
       */
      public Builder clearWeeklyLotteryTimes() {
        bitField0_ = (bitField0_ & ~0x00000002);
        weeklyLotteryTimes_ = 0;
        onChanged();
        return this;
      }

      private int weeklyGotPrizeInfo_ ;
      /**
       * <code>optional int32 weekly_got_prize_info = 3;</code>
       *
       * <pre>
       *每周累计抽奖 奖励领取令牌（按位计算，每位代表对应奖励ID是否已经领取)
       * </pre>
       */
      public boolean hasWeeklyGotPrizeInfo() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 weekly_got_prize_info = 3;</code>
       *
       * <pre>
       *每周累计抽奖 奖励领取令牌（按位计算，每位代表对应奖励ID是否已经领取)
       * </pre>
       */
      public int getWeeklyGotPrizeInfo() {
        return weeklyGotPrizeInfo_;
      }
      /**
       * <code>optional int32 weekly_got_prize_info = 3;</code>
       *
       * <pre>
       *每周累计抽奖 奖励领取令牌（按位计算，每位代表对应奖励ID是否已经领取)
       * </pre>
       */
      public Builder setWeeklyGotPrizeInfo(int value) {
        bitField0_ |= 0x00000004;
        weeklyGotPrizeInfo_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 weekly_got_prize_info = 3;</code>
       *
       * <pre>
       *每周累计抽奖 奖励领取令牌（按位计算，每位代表对应奖励ID是否已经领取)
       * </pre>
       */
      public Builder clearWeeklyGotPrizeInfo() {
        bitField0_ = (bitField0_ & ~0x00000004);
        weeklyGotPrizeInfo_ = 0;
        onChanged();
        return this;
      }

      private int nextFreeLotteryTime_ ;
      /**
       * <code>optional int32 next_free_lottery_time = 4;</code>
       *
       * <pre>
       *下一次免费时间点（秒)
       * </pre>
       */
      public boolean hasNextFreeLotteryTime() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      /**
       * <code>optional int32 next_free_lottery_time = 4;</code>
       *
       * <pre>
       *下一次免费时间点（秒)
       * </pre>
       */
      public int getNextFreeLotteryTime() {
        return nextFreeLotteryTime_;
      }
      /**
       * <code>optional int32 next_free_lottery_time = 4;</code>
       *
       * <pre>
       *下一次免费时间点（秒)
       * </pre>
       */
      public Builder setNextFreeLotteryTime(int value) {
        bitField0_ |= 0x00000008;
        nextFreeLotteryTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 next_free_lottery_time = 4;</code>
       *
       * <pre>
       *下一次免费时间点（秒)
       * </pre>
       */
      public Builder clearNextFreeLotteryTime() {
        bitField0_ = (bitField0_ & ~0x00000008);
        nextFreeLotteryTime_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.RuneLotteryModuleObjClientProto)
    }

    static {
      defaultInstance = new RuneLotteryModuleObjClientProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.RuneLotteryModuleObjClientProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_RuneLotteryModuleObjClientProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_RuneLotteryModuleObjClientProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n client/rune_lottery_client.proto\022\005prot" +
      "o\"\233\001\n\037RuneLotteryModuleObjClientProto\022\033\n" +
      "\023daily_lottery_times\030\001 \001(\005\022\034\n\024weekly_lot" +
      "tery_times\030\002 \001(\005\022\035\n\025weekly_got_prize_inf" +
      "o\030\003 \001(\005\022\036\n\026next_free_lottery_time\030\004 \001(\005B" +
      "1\n\023app.protobuf.clientB\030RuneLotteryClien" +
      "tContentH\001"
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
    internal_static_proto_RuneLotteryModuleObjClientProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_RuneLotteryModuleObjClientProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_RuneLotteryModuleObjClientProto_descriptor,
        new java.lang.String[] { "DailyLotteryTimes", "WeeklyLotteryTimes", "WeeklyGotPrizeInfo", "NextFreeLotteryTime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}