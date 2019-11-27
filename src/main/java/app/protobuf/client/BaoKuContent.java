// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client/bao_ku_client.proto

package app.protobuf.client;

public final class BaoKuContent {
  private BaoKuContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface BaoKuClientModuleObjProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.BaoKuClientModuleObjProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    java.util.List<app.protobuf.client.UtilContent.Int32PairProto> 
        getDungeonStarsList();
    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    app.protobuf.client.UtilContent.Int32PairProto getDungeonStars(int index);
    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    int getDungeonStarsCount();
    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    java.util.List<? extends app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> 
        getDungeonStarsOrBuilderList();
    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    app.protobuf.client.UtilContent.Int32PairProtoOrBuilder getDungeonStarsOrBuilder(
        int index);

    /**
     * <code>optional int32 buyTimes = 2;</code>
     *
     * <pre>
     * 购买次数
     * </pre>
     */
    boolean hasBuyTimes();
    /**
     * <code>optional int32 buyTimes = 2;</code>
     *
     * <pre>
     * 购买次数
     * </pre>
     */
    int getBuyTimes();

    /**
     * <code>optional int32 enterTimes = 3;</code>
     *
     * <pre>
     * 进入次数
     * </pre>
     */
    boolean hasEnterTimes();
    /**
     * <code>optional int32 enterTimes = 3;</code>
     *
     * <pre>
     * 进入次数
     * </pre>
     */
    int getEnterTimes();
  }
  /**
   * Protobuf type {@code proto.BaoKuClientModuleObjProto}
   */
  public static final class BaoKuClientModuleObjProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.BaoKuClientModuleObjProto)
      BaoKuClientModuleObjProtoOrBuilder {
    // Use BaoKuClientModuleObjProto.newBuilder() to construct.
    private BaoKuClientModuleObjProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private BaoKuClientModuleObjProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final BaoKuClientModuleObjProto defaultInstance;
    public static BaoKuClientModuleObjProto getDefaultInstance() {
      return defaultInstance;
    }

    public BaoKuClientModuleObjProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private BaoKuClientModuleObjProto(
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
              if (!((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
                dungeonStars_ = new java.util.ArrayList<app.protobuf.client.UtilContent.Int32PairProto>();
                mutable_bitField0_ |= 0x00000001;
              }
              dungeonStars_.add(input.readMessage(app.protobuf.client.UtilContent.Int32PairProto.PARSER, extensionRegistry));
              break;
            }
            case 16: {
              bitField0_ |= 0x00000001;
              buyTimes_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000002;
              enterTimes_ = input.readInt32();
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
        if (((mutable_bitField0_ & 0x00000001) == 0x00000001)) {
          dungeonStars_ = java.util.Collections.unmodifiableList(dungeonStars_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return app.protobuf.client.BaoKuContent.internal_static_proto_BaoKuClientModuleObjProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.client.BaoKuContent.internal_static_proto_BaoKuClientModuleObjProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto.class, app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto.Builder.class);
    }

    public static com.google.protobuf.Parser<BaoKuClientModuleObjProto> PARSER =
        new com.google.protobuf.AbstractParser<BaoKuClientModuleObjProto>() {
      public BaoKuClientModuleObjProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new BaoKuClientModuleObjProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<BaoKuClientModuleObjProto> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int DUNGEONSTARS_FIELD_NUMBER = 1;
    private java.util.List<app.protobuf.client.UtilContent.Int32PairProto> dungeonStars_;
    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    public java.util.List<app.protobuf.client.UtilContent.Int32PairProto> getDungeonStarsList() {
      return dungeonStars_;
    }
    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    public java.util.List<? extends app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> 
        getDungeonStarsOrBuilderList() {
      return dungeonStars_;
    }
    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    public int getDungeonStarsCount() {
      return dungeonStars_.size();
    }
    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    public app.protobuf.client.UtilContent.Int32PairProto getDungeonStars(int index) {
      return dungeonStars_.get(index);
    }
    /**
     * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
     *
     * <pre>
     * 副本通关情况 key:副本ID,value:通关星级
     * </pre>
     */
    public app.protobuf.client.UtilContent.Int32PairProtoOrBuilder getDungeonStarsOrBuilder(
        int index) {
      return dungeonStars_.get(index);
    }

    public static final int BUYTIMES_FIELD_NUMBER = 2;
    private int buyTimes_;
    /**
     * <code>optional int32 buyTimes = 2;</code>
     *
     * <pre>
     * 购买次数
     * </pre>
     */
    public boolean hasBuyTimes() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 buyTimes = 2;</code>
     *
     * <pre>
     * 购买次数
     * </pre>
     */
    public int getBuyTimes() {
      return buyTimes_;
    }

    public static final int ENTERTIMES_FIELD_NUMBER = 3;
    private int enterTimes_;
    /**
     * <code>optional int32 enterTimes = 3;</code>
     *
     * <pre>
     * 进入次数
     * </pre>
     */
    public boolean hasEnterTimes() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 enterTimes = 3;</code>
     *
     * <pre>
     * 进入次数
     * </pre>
     */
    public int getEnterTimes() {
      return enterTimes_;
    }

    private void initFields() {
      dungeonStars_ = java.util.Collections.emptyList();
      buyTimes_ = 0;
      enterTimes_ = 0;
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
      for (int i = 0; i < dungeonStars_.size(); i++) {
        output.writeMessage(1, dungeonStars_.get(i));
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(2, buyTimes_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(3, enterTimes_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < dungeonStars_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, dungeonStars_.get(i));
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, buyTimes_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, enterTimes_);
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

    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto prototype) {
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
     * Protobuf type {@code proto.BaoKuClientModuleObjProto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.BaoKuClientModuleObjProto)
        app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.client.BaoKuContent.internal_static_proto_BaoKuClientModuleObjProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.client.BaoKuContent.internal_static_proto_BaoKuClientModuleObjProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto.class, app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto.Builder.class);
      }

      // Construct using app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto.newBuilder()
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
          getDungeonStarsFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (dungeonStarsBuilder_ == null) {
          dungeonStars_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          dungeonStarsBuilder_.clear();
        }
        buyTimes_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        enterTimes_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.client.BaoKuContent.internal_static_proto_BaoKuClientModuleObjProto_descriptor;
      }

      public app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto getDefaultInstanceForType() {
        return app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto.getDefaultInstance();
      }

      public app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto build() {
        app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto buildPartial() {
        app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto result = new app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (dungeonStarsBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001)) {
            dungeonStars_ = java.util.Collections.unmodifiableList(dungeonStars_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.dungeonStars_ = dungeonStars_;
        } else {
          result.dungeonStars_ = dungeonStarsBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000001;
        }
        result.buyTimes_ = buyTimes_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000002;
        }
        result.enterTimes_ = enterTimes_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto) {
          return mergeFrom((app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto other) {
        if (other == app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto.getDefaultInstance()) return this;
        if (dungeonStarsBuilder_ == null) {
          if (!other.dungeonStars_.isEmpty()) {
            if (dungeonStars_.isEmpty()) {
              dungeonStars_ = other.dungeonStars_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureDungeonStarsIsMutable();
              dungeonStars_.addAll(other.dungeonStars_);
            }
            onChanged();
          }
        } else {
          if (!other.dungeonStars_.isEmpty()) {
            if (dungeonStarsBuilder_.isEmpty()) {
              dungeonStarsBuilder_.dispose();
              dungeonStarsBuilder_ = null;
              dungeonStars_ = other.dungeonStars_;
              bitField0_ = (bitField0_ & ~0x00000001);
              dungeonStarsBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getDungeonStarsFieldBuilder() : null;
            } else {
              dungeonStarsBuilder_.addAllMessages(other.dungeonStars_);
            }
          }
        }
        if (other.hasBuyTimes()) {
          setBuyTimes(other.getBuyTimes());
        }
        if (other.hasEnterTimes()) {
          setEnterTimes(other.getEnterTimes());
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
        app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.client.BaoKuContent.BaoKuClientModuleObjProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<app.protobuf.client.UtilContent.Int32PairProto> dungeonStars_ =
        java.util.Collections.emptyList();
      private void ensureDungeonStarsIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          dungeonStars_ = new java.util.ArrayList<app.protobuf.client.UtilContent.Int32PairProto>(dungeonStars_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          app.protobuf.client.UtilContent.Int32PairProto, app.protobuf.client.UtilContent.Int32PairProto.Builder, app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> dungeonStarsBuilder_;

      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public java.util.List<app.protobuf.client.UtilContent.Int32PairProto> getDungeonStarsList() {
        if (dungeonStarsBuilder_ == null) {
          return java.util.Collections.unmodifiableList(dungeonStars_);
        } else {
          return dungeonStarsBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public int getDungeonStarsCount() {
        if (dungeonStarsBuilder_ == null) {
          return dungeonStars_.size();
        } else {
          return dungeonStarsBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProto getDungeonStars(int index) {
        if (dungeonStarsBuilder_ == null) {
          return dungeonStars_.get(index);
        } else {
          return dungeonStarsBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public Builder setDungeonStars(
          int index, app.protobuf.client.UtilContent.Int32PairProto value) {
        if (dungeonStarsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureDungeonStarsIsMutable();
          dungeonStars_.set(index, value);
          onChanged();
        } else {
          dungeonStarsBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public Builder setDungeonStars(
          int index, app.protobuf.client.UtilContent.Int32PairProto.Builder builderForValue) {
        if (dungeonStarsBuilder_ == null) {
          ensureDungeonStarsIsMutable();
          dungeonStars_.set(index, builderForValue.build());
          onChanged();
        } else {
          dungeonStarsBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public Builder addDungeonStars(app.protobuf.client.UtilContent.Int32PairProto value) {
        if (dungeonStarsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureDungeonStarsIsMutable();
          dungeonStars_.add(value);
          onChanged();
        } else {
          dungeonStarsBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public Builder addDungeonStars(
          int index, app.protobuf.client.UtilContent.Int32PairProto value) {
        if (dungeonStarsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureDungeonStarsIsMutable();
          dungeonStars_.add(index, value);
          onChanged();
        } else {
          dungeonStarsBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public Builder addDungeonStars(
          app.protobuf.client.UtilContent.Int32PairProto.Builder builderForValue) {
        if (dungeonStarsBuilder_ == null) {
          ensureDungeonStarsIsMutable();
          dungeonStars_.add(builderForValue.build());
          onChanged();
        } else {
          dungeonStarsBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public Builder addDungeonStars(
          int index, app.protobuf.client.UtilContent.Int32PairProto.Builder builderForValue) {
        if (dungeonStarsBuilder_ == null) {
          ensureDungeonStarsIsMutable();
          dungeonStars_.add(index, builderForValue.build());
          onChanged();
        } else {
          dungeonStarsBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public Builder addAllDungeonStars(
          java.lang.Iterable<? extends app.protobuf.client.UtilContent.Int32PairProto> values) {
        if (dungeonStarsBuilder_ == null) {
          ensureDungeonStarsIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, dungeonStars_);
          onChanged();
        } else {
          dungeonStarsBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public Builder clearDungeonStars() {
        if (dungeonStarsBuilder_ == null) {
          dungeonStars_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          dungeonStarsBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public Builder removeDungeonStars(int index) {
        if (dungeonStarsBuilder_ == null) {
          ensureDungeonStarsIsMutable();
          dungeonStars_.remove(index);
          onChanged();
        } else {
          dungeonStarsBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProto.Builder getDungeonStarsBuilder(
          int index) {
        return getDungeonStarsFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProtoOrBuilder getDungeonStarsOrBuilder(
          int index) {
        if (dungeonStarsBuilder_ == null) {
          return dungeonStars_.get(index);  } else {
          return dungeonStarsBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public java.util.List<? extends app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> 
           getDungeonStarsOrBuilderList() {
        if (dungeonStarsBuilder_ != null) {
          return dungeonStarsBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(dungeonStars_);
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProto.Builder addDungeonStarsBuilder() {
        return getDungeonStarsFieldBuilder().addBuilder(
            app.protobuf.client.UtilContent.Int32PairProto.getDefaultInstance());
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProto.Builder addDungeonStarsBuilder(
          int index) {
        return getDungeonStarsFieldBuilder().addBuilder(
            index, app.protobuf.client.UtilContent.Int32PairProto.getDefaultInstance());
      }
      /**
       * <code>repeated .proto.Int32PairProto dungeonStars = 1;</code>
       *
       * <pre>
       * 副本通关情况 key:副本ID,value:通关星级
       * </pre>
       */
      public java.util.List<app.protobuf.client.UtilContent.Int32PairProto.Builder> 
           getDungeonStarsBuilderList() {
        return getDungeonStarsFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          app.protobuf.client.UtilContent.Int32PairProto, app.protobuf.client.UtilContent.Int32PairProto.Builder, app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> 
          getDungeonStarsFieldBuilder() {
        if (dungeonStarsBuilder_ == null) {
          dungeonStarsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              app.protobuf.client.UtilContent.Int32PairProto, app.protobuf.client.UtilContent.Int32PairProto.Builder, app.protobuf.client.UtilContent.Int32PairProtoOrBuilder>(
                  dungeonStars_,
                  ((bitField0_ & 0x00000001) == 0x00000001),
                  getParentForChildren(),
                  isClean());
          dungeonStars_ = null;
        }
        return dungeonStarsBuilder_;
      }

      private int buyTimes_ ;
      /**
       * <code>optional int32 buyTimes = 2;</code>
       *
       * <pre>
       * 购买次数
       * </pre>
       */
      public boolean hasBuyTimes() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 buyTimes = 2;</code>
       *
       * <pre>
       * 购买次数
       * </pre>
       */
      public int getBuyTimes() {
        return buyTimes_;
      }
      /**
       * <code>optional int32 buyTimes = 2;</code>
       *
       * <pre>
       * 购买次数
       * </pre>
       */
      public Builder setBuyTimes(int value) {
        bitField0_ |= 0x00000002;
        buyTimes_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 buyTimes = 2;</code>
       *
       * <pre>
       * 购买次数
       * </pre>
       */
      public Builder clearBuyTimes() {
        bitField0_ = (bitField0_ & ~0x00000002);
        buyTimes_ = 0;
        onChanged();
        return this;
      }

      private int enterTimes_ ;
      /**
       * <code>optional int32 enterTimes = 3;</code>
       *
       * <pre>
       * 进入次数
       * </pre>
       */
      public boolean hasEnterTimes() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 enterTimes = 3;</code>
       *
       * <pre>
       * 进入次数
       * </pre>
       */
      public int getEnterTimes() {
        return enterTimes_;
      }
      /**
       * <code>optional int32 enterTimes = 3;</code>
       *
       * <pre>
       * 进入次数
       * </pre>
       */
      public Builder setEnterTimes(int value) {
        bitField0_ |= 0x00000004;
        enterTimes_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 enterTimes = 3;</code>
       *
       * <pre>
       * 进入次数
       * </pre>
       */
      public Builder clearEnterTimes() {
        bitField0_ = (bitField0_ & ~0x00000004);
        enterTimes_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.BaoKuClientModuleObjProto)
    }

    static {
      defaultInstance = new BaoKuClientModuleObjProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.BaoKuClientModuleObjProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_BaoKuClientModuleObjProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_BaoKuClientModuleObjProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032client/bao_ku_client.proto\022\005proto\032\021cli" +
      "ent/util.proto\"n\n\031BaoKuClientModuleObjPr" +
      "oto\022+\n\014dungeonStars\030\001 \003(\0132\025.proto.Int32P" +
      "airProto\022\020\n\010buyTimes\030\002 \001(\005\022\022\n\nenterTimes" +
      "\030\003 \001(\005B%\n\023app.protobuf.clientB\014BaoKuCont" +
      "entH\001"
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
    internal_static_proto_BaoKuClientModuleObjProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_BaoKuClientModuleObjProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_BaoKuClientModuleObjProto_descriptor,
        new java.lang.String[] { "DungeonStars", "BuyTimes", "EnterTimes", });
    app.protobuf.client.UtilContent.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}