// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client/sprite_client.proto

package app.protobuf.client;

public final class SpriteClientContent {
  private SpriteClientContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface SpriteModuleObjClientProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.SpriteModuleObjClientProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    java.util.List<app.protobuf.client.GoodsContent.GoodsProto> 
        getSpriteGoodsList();
    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    app.protobuf.client.GoodsContent.GoodsProto getSpriteGoods(int index);
    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    int getSpriteGoodsCount();
    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    java.util.List<? extends app.protobuf.client.GoodsContent.GoodsProtoOrBuilder> 
        getSpriteGoodsOrBuilderList();
    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    app.protobuf.client.GoodsContent.GoodsProtoOrBuilder getSpriteGoodsOrBuilder(
        int index);

    /**
     * <code>optional int64 cache_sprite_addition_exp = 2;</code>
     *
     * <pre>
     *没有购买小鬼怪时，在经验副本中缓存的经验
     * </pre>
     */
    boolean hasCacheSpriteAdditionExp();
    /**
     * <code>optional int64 cache_sprite_addition_exp = 2;</code>
     *
     * <pre>
     *没有购买小鬼怪时，在经验副本中缓存的经验
     * </pre>
     */
    long getCacheSpriteAdditionExp();

    /**
     * <code>optional bool is_brought_sprite_goods = 3;</code>
     *
     * <pre>
     *是否购买过小鬼怪
     * </pre>
     */
    boolean hasIsBroughtSpriteGoods();
    /**
     * <code>optional bool is_brought_sprite_goods = 3;</code>
     *
     * <pre>
     *是否购买过小鬼怪
     * </pre>
     */
    boolean getIsBroughtSpriteGoods();
  }
  /**
   * Protobuf type {@code proto.SpriteModuleObjClientProto}
   */
  public static final class SpriteModuleObjClientProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.SpriteModuleObjClientProto)
      SpriteModuleObjClientProtoOrBuilder {
    // Use SpriteModuleObjClientProto.newBuilder() to construct.
    private SpriteModuleObjClientProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private SpriteModuleObjClientProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final SpriteModuleObjClientProto defaultInstance;
    public static SpriteModuleObjClientProto getDefaultInstance() {
      return defaultInstance;
    }

    public SpriteModuleObjClientProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private SpriteModuleObjClientProto(
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
                spriteGoods_ = new java.util.ArrayList<app.protobuf.client.GoodsContent.GoodsProto>();
                mutable_bitField0_ |= 0x00000001;
              }
              spriteGoods_.add(input.readMessage(app.protobuf.client.GoodsContent.GoodsProto.PARSER, extensionRegistry));
              break;
            }
            case 16: {
              bitField0_ |= 0x00000001;
              cacheSpriteAdditionExp_ = input.readInt64();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000002;
              isBroughtSpriteGoods_ = input.readBool();
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
          spriteGoods_ = java.util.Collections.unmodifiableList(spriteGoods_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return app.protobuf.client.SpriteClientContent.internal_static_proto_SpriteModuleObjClientProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.client.SpriteClientContent.internal_static_proto_SpriteModuleObjClientProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto.class, app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto.Builder.class);
    }

    public static com.google.protobuf.Parser<SpriteModuleObjClientProto> PARSER =
        new com.google.protobuf.AbstractParser<SpriteModuleObjClientProto>() {
      public SpriteModuleObjClientProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new SpriteModuleObjClientProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<SpriteModuleObjClientProto> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int SPRITEGOODS_FIELD_NUMBER = 1;
    private java.util.List<app.protobuf.client.GoodsContent.GoodsProto> spriteGoods_;
    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    public java.util.List<app.protobuf.client.GoodsContent.GoodsProto> getSpriteGoodsList() {
      return spriteGoods_;
    }
    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    public java.util.List<? extends app.protobuf.client.GoodsContent.GoodsProtoOrBuilder> 
        getSpriteGoodsOrBuilderList() {
      return spriteGoods_;
    }
    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    public int getSpriteGoodsCount() {
      return spriteGoods_.size();
    }
    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    public app.protobuf.client.GoodsContent.GoodsProto getSpriteGoods(int index) {
      return spriteGoods_.get(index);
    }
    /**
     * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
     */
    public app.protobuf.client.GoodsContent.GoodsProtoOrBuilder getSpriteGoodsOrBuilder(
        int index) {
      return spriteGoods_.get(index);
    }

    public static final int CACHE_SPRITE_ADDITION_EXP_FIELD_NUMBER = 2;
    private long cacheSpriteAdditionExp_;
    /**
     * <code>optional int64 cache_sprite_addition_exp = 2;</code>
     *
     * <pre>
     *没有购买小鬼怪时，在经验副本中缓存的经验
     * </pre>
     */
    public boolean hasCacheSpriteAdditionExp() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int64 cache_sprite_addition_exp = 2;</code>
     *
     * <pre>
     *没有购买小鬼怪时，在经验副本中缓存的经验
     * </pre>
     */
    public long getCacheSpriteAdditionExp() {
      return cacheSpriteAdditionExp_;
    }

    public static final int IS_BROUGHT_SPRITE_GOODS_FIELD_NUMBER = 3;
    private boolean isBroughtSpriteGoods_;
    /**
     * <code>optional bool is_brought_sprite_goods = 3;</code>
     *
     * <pre>
     *是否购买过小鬼怪
     * </pre>
     */
    public boolean hasIsBroughtSpriteGoods() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional bool is_brought_sprite_goods = 3;</code>
     *
     * <pre>
     *是否购买过小鬼怪
     * </pre>
     */
    public boolean getIsBroughtSpriteGoods() {
      return isBroughtSpriteGoods_;
    }

    private void initFields() {
      spriteGoods_ = java.util.Collections.emptyList();
      cacheSpriteAdditionExp_ = 0L;
      isBroughtSpriteGoods_ = false;
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
      for (int i = 0; i < spriteGoods_.size(); i++) {
        output.writeMessage(1, spriteGoods_.get(i));
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt64(2, cacheSpriteAdditionExp_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBool(3, isBroughtSpriteGoods_);
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < spriteGoods_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, spriteGoods_.get(i));
      }
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, cacheSpriteAdditionExp_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(3, isBroughtSpriteGoods_);
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

    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto prototype) {
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
     * Protobuf type {@code proto.SpriteModuleObjClientProto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.SpriteModuleObjClientProto)
        app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.client.SpriteClientContent.internal_static_proto_SpriteModuleObjClientProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.client.SpriteClientContent.internal_static_proto_SpriteModuleObjClientProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto.class, app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto.Builder.class);
      }

      // Construct using app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto.newBuilder()
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
          getSpriteGoodsFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (spriteGoodsBuilder_ == null) {
          spriteGoods_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          spriteGoodsBuilder_.clear();
        }
        cacheSpriteAdditionExp_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        isBroughtSpriteGoods_ = false;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.client.SpriteClientContent.internal_static_proto_SpriteModuleObjClientProto_descriptor;
      }

      public app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto getDefaultInstanceForType() {
        return app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto.getDefaultInstance();
      }

      public app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto build() {
        app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto buildPartial() {
        app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto result = new app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (spriteGoodsBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001)) {
            spriteGoods_ = java.util.Collections.unmodifiableList(spriteGoods_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.spriteGoods_ = spriteGoods_;
        } else {
          result.spriteGoods_ = spriteGoodsBuilder_.build();
        }
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000001;
        }
        result.cacheSpriteAdditionExp_ = cacheSpriteAdditionExp_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000002;
        }
        result.isBroughtSpriteGoods_ = isBroughtSpriteGoods_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto) {
          return mergeFrom((app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto other) {
        if (other == app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto.getDefaultInstance()) return this;
        if (spriteGoodsBuilder_ == null) {
          if (!other.spriteGoods_.isEmpty()) {
            if (spriteGoods_.isEmpty()) {
              spriteGoods_ = other.spriteGoods_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureSpriteGoodsIsMutable();
              spriteGoods_.addAll(other.spriteGoods_);
            }
            onChanged();
          }
        } else {
          if (!other.spriteGoods_.isEmpty()) {
            if (spriteGoodsBuilder_.isEmpty()) {
              spriteGoodsBuilder_.dispose();
              spriteGoodsBuilder_ = null;
              spriteGoods_ = other.spriteGoods_;
              bitField0_ = (bitField0_ & ~0x00000001);
              spriteGoodsBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getSpriteGoodsFieldBuilder() : null;
            } else {
              spriteGoodsBuilder_.addAllMessages(other.spriteGoods_);
            }
          }
        }
        if (other.hasCacheSpriteAdditionExp()) {
          setCacheSpriteAdditionExp(other.getCacheSpriteAdditionExp());
        }
        if (other.hasIsBroughtSpriteGoods()) {
          setIsBroughtSpriteGoods(other.getIsBroughtSpriteGoods());
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
        app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.client.SpriteClientContent.SpriteModuleObjClientProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<app.protobuf.client.GoodsContent.GoodsProto> spriteGoods_ =
        java.util.Collections.emptyList();
      private void ensureSpriteGoodsIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          spriteGoods_ = new java.util.ArrayList<app.protobuf.client.GoodsContent.GoodsProto>(spriteGoods_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          app.protobuf.client.GoodsContent.GoodsProto, app.protobuf.client.GoodsContent.GoodsProto.Builder, app.protobuf.client.GoodsContent.GoodsProtoOrBuilder> spriteGoodsBuilder_;

      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public java.util.List<app.protobuf.client.GoodsContent.GoodsProto> getSpriteGoodsList() {
        if (spriteGoodsBuilder_ == null) {
          return java.util.Collections.unmodifiableList(spriteGoods_);
        } else {
          return spriteGoodsBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public int getSpriteGoodsCount() {
        if (spriteGoodsBuilder_ == null) {
          return spriteGoods_.size();
        } else {
          return spriteGoodsBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public app.protobuf.client.GoodsContent.GoodsProto getSpriteGoods(int index) {
        if (spriteGoodsBuilder_ == null) {
          return spriteGoods_.get(index);
        } else {
          return spriteGoodsBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public Builder setSpriteGoods(
          int index, app.protobuf.client.GoodsContent.GoodsProto value) {
        if (spriteGoodsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureSpriteGoodsIsMutable();
          spriteGoods_.set(index, value);
          onChanged();
        } else {
          spriteGoodsBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public Builder setSpriteGoods(
          int index, app.protobuf.client.GoodsContent.GoodsProto.Builder builderForValue) {
        if (spriteGoodsBuilder_ == null) {
          ensureSpriteGoodsIsMutable();
          spriteGoods_.set(index, builderForValue.build());
          onChanged();
        } else {
          spriteGoodsBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public Builder addSpriteGoods(app.protobuf.client.GoodsContent.GoodsProto value) {
        if (spriteGoodsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureSpriteGoodsIsMutable();
          spriteGoods_.add(value);
          onChanged();
        } else {
          spriteGoodsBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public Builder addSpriteGoods(
          int index, app.protobuf.client.GoodsContent.GoodsProto value) {
        if (spriteGoodsBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureSpriteGoodsIsMutable();
          spriteGoods_.add(index, value);
          onChanged();
        } else {
          spriteGoodsBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public Builder addSpriteGoods(
          app.protobuf.client.GoodsContent.GoodsProto.Builder builderForValue) {
        if (spriteGoodsBuilder_ == null) {
          ensureSpriteGoodsIsMutable();
          spriteGoods_.add(builderForValue.build());
          onChanged();
        } else {
          spriteGoodsBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public Builder addSpriteGoods(
          int index, app.protobuf.client.GoodsContent.GoodsProto.Builder builderForValue) {
        if (spriteGoodsBuilder_ == null) {
          ensureSpriteGoodsIsMutable();
          spriteGoods_.add(index, builderForValue.build());
          onChanged();
        } else {
          spriteGoodsBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public Builder addAllSpriteGoods(
          java.lang.Iterable<? extends app.protobuf.client.GoodsContent.GoodsProto> values) {
        if (spriteGoodsBuilder_ == null) {
          ensureSpriteGoodsIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, spriteGoods_);
          onChanged();
        } else {
          spriteGoodsBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public Builder clearSpriteGoods() {
        if (spriteGoodsBuilder_ == null) {
          spriteGoods_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          spriteGoodsBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public Builder removeSpriteGoods(int index) {
        if (spriteGoodsBuilder_ == null) {
          ensureSpriteGoodsIsMutable();
          spriteGoods_.remove(index);
          onChanged();
        } else {
          spriteGoodsBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public app.protobuf.client.GoodsContent.GoodsProto.Builder getSpriteGoodsBuilder(
          int index) {
        return getSpriteGoodsFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public app.protobuf.client.GoodsContent.GoodsProtoOrBuilder getSpriteGoodsOrBuilder(
          int index) {
        if (spriteGoodsBuilder_ == null) {
          return spriteGoods_.get(index);  } else {
          return spriteGoodsBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public java.util.List<? extends app.protobuf.client.GoodsContent.GoodsProtoOrBuilder> 
           getSpriteGoodsOrBuilderList() {
        if (spriteGoodsBuilder_ != null) {
          return spriteGoodsBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(spriteGoods_);
        }
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public app.protobuf.client.GoodsContent.GoodsProto.Builder addSpriteGoodsBuilder() {
        return getSpriteGoodsFieldBuilder().addBuilder(
            app.protobuf.client.GoodsContent.GoodsProto.getDefaultInstance());
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public app.protobuf.client.GoodsContent.GoodsProto.Builder addSpriteGoodsBuilder(
          int index) {
        return getSpriteGoodsFieldBuilder().addBuilder(
            index, app.protobuf.client.GoodsContent.GoodsProto.getDefaultInstance());
      }
      /**
       * <code>repeated .proto.GoodsProto spriteGoods = 1;</code>
       */
      public java.util.List<app.protobuf.client.GoodsContent.GoodsProto.Builder> 
           getSpriteGoodsBuilderList() {
        return getSpriteGoodsFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          app.protobuf.client.GoodsContent.GoodsProto, app.protobuf.client.GoodsContent.GoodsProto.Builder, app.protobuf.client.GoodsContent.GoodsProtoOrBuilder> 
          getSpriteGoodsFieldBuilder() {
        if (spriteGoodsBuilder_ == null) {
          spriteGoodsBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              app.protobuf.client.GoodsContent.GoodsProto, app.protobuf.client.GoodsContent.GoodsProto.Builder, app.protobuf.client.GoodsContent.GoodsProtoOrBuilder>(
                  spriteGoods_,
                  ((bitField0_ & 0x00000001) == 0x00000001),
                  getParentForChildren(),
                  isClean());
          spriteGoods_ = null;
        }
        return spriteGoodsBuilder_;
      }

      private long cacheSpriteAdditionExp_ ;
      /**
       * <code>optional int64 cache_sprite_addition_exp = 2;</code>
       *
       * <pre>
       *没有购买小鬼怪时，在经验副本中缓存的经验
       * </pre>
       */
      public boolean hasCacheSpriteAdditionExp() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int64 cache_sprite_addition_exp = 2;</code>
       *
       * <pre>
       *没有购买小鬼怪时，在经验副本中缓存的经验
       * </pre>
       */
      public long getCacheSpriteAdditionExp() {
        return cacheSpriteAdditionExp_;
      }
      /**
       * <code>optional int64 cache_sprite_addition_exp = 2;</code>
       *
       * <pre>
       *没有购买小鬼怪时，在经验副本中缓存的经验
       * </pre>
       */
      public Builder setCacheSpriteAdditionExp(long value) {
        bitField0_ |= 0x00000002;
        cacheSpriteAdditionExp_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int64 cache_sprite_addition_exp = 2;</code>
       *
       * <pre>
       *没有购买小鬼怪时，在经验副本中缓存的经验
       * </pre>
       */
      public Builder clearCacheSpriteAdditionExp() {
        bitField0_ = (bitField0_ & ~0x00000002);
        cacheSpriteAdditionExp_ = 0L;
        onChanged();
        return this;
      }

      private boolean isBroughtSpriteGoods_ ;
      /**
       * <code>optional bool is_brought_sprite_goods = 3;</code>
       *
       * <pre>
       *是否购买过小鬼怪
       * </pre>
       */
      public boolean hasIsBroughtSpriteGoods() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional bool is_brought_sprite_goods = 3;</code>
       *
       * <pre>
       *是否购买过小鬼怪
       * </pre>
       */
      public boolean getIsBroughtSpriteGoods() {
        return isBroughtSpriteGoods_;
      }
      /**
       * <code>optional bool is_brought_sprite_goods = 3;</code>
       *
       * <pre>
       *是否购买过小鬼怪
       * </pre>
       */
      public Builder setIsBroughtSpriteGoods(boolean value) {
        bitField0_ |= 0x00000004;
        isBroughtSpriteGoods_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bool is_brought_sprite_goods = 3;</code>
       *
       * <pre>
       *是否购买过小鬼怪
       * </pre>
       */
      public Builder clearIsBroughtSpriteGoods() {
        bitField0_ = (bitField0_ & ~0x00000004);
        isBroughtSpriteGoods_ = false;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.SpriteModuleObjClientProto)
    }

    static {
      defaultInstance = new SpriteModuleObjClientProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.SpriteModuleObjClientProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_SpriteModuleObjClientProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_SpriteModuleObjClientProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032client/sprite_client.proto\022\005proto\032\022cli" +
      "ent/goods.proto\"\210\001\n\032SpriteModuleObjClien" +
      "tProto\022&\n\013spriteGoods\030\001 \003(\0132\021.proto.Good" +
      "sProto\022!\n\031cache_sprite_addition_exp\030\002 \001(" +
      "\003\022\037\n\027is_brought_sprite_goods\030\003 \001(\010B,\n\023ap" +
      "p.protobuf.clientB\023SpriteClientContentH\001"
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
          app.protobuf.client.GoodsContent.getDescriptor(),
        }, assigner);
    internal_static_proto_SpriteModuleObjClientProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_SpriteModuleObjClientProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_SpriteModuleObjClientProto_descriptor,
        new java.lang.String[] { "SpriteGoods", "CacheSpriteAdditionExp", "IsBroughtSpriteGoods", });
    app.protobuf.client.GoodsContent.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
