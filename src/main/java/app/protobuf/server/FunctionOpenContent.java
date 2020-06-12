// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: server/function_open_server.proto

package app.protobuf.server;

public final class FunctionOpenContent {
  private FunctionOpenContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface FunctionOpenMinorProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.FunctionOpenMinorProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    java.util.List<app.protobuf.client.UtilContent.Int32PairProto> 
        getFuncOpenTimesList();
    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    app.protobuf.client.UtilContent.Int32PairProto getFuncOpenTimes(int index);
    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    int getFuncOpenTimesCount();
    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    java.util.List<? extends app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> 
        getFuncOpenTimesOrBuilderList();
    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    app.protobuf.client.UtilContent.Int32PairProtoOrBuilder getFuncOpenTimesOrBuilder(
        int index);

    /**
     * <code>repeated int32 openPrizeCollects = 2;</code>
     *
     * <pre>
     * 已领取的开启奖励id
     * </pre>
     */
    java.util.List<java.lang.Integer> getOpenPrizeCollectsList();
    /**
     * <code>repeated int32 openPrizeCollects = 2;</code>
     *
     * <pre>
     * 已领取的开启奖励id
     * </pre>
     */
    int getOpenPrizeCollectsCount();
    /**
     * <code>repeated int32 openPrizeCollects = 2;</code>
     *
     * <pre>
     * 已领取的开启奖励id
     * </pre>
     */
    int getOpenPrizeCollects(int index);
  }
  /**
   * Protobuf type {@code proto.FunctionOpenMinorProto}
   *
   * <pre>
   * </pre>
   */
  public static final class FunctionOpenMinorProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.FunctionOpenMinorProto)
      FunctionOpenMinorProtoOrBuilder {
    // Use FunctionOpenMinorProto.newBuilder() to construct.
    private FunctionOpenMinorProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private FunctionOpenMinorProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final FunctionOpenMinorProto defaultInstance;
    public static FunctionOpenMinorProto getDefaultInstance() {
      return defaultInstance;
    }

    public FunctionOpenMinorProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private FunctionOpenMinorProto(
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
                funcOpenTimes_ = new java.util.ArrayList<app.protobuf.client.UtilContent.Int32PairProto>();
                mutable_bitField0_ |= 0x00000001;
              }
              funcOpenTimes_.add(input.readMessage(app.protobuf.client.UtilContent.Int32PairProto.PARSER, extensionRegistry));
              break;
            }
            case 16: {
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                openPrizeCollects_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000002;
              }
              openPrizeCollects_.add(input.readInt32());
              break;
            }
            case 18: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002) && input.getBytesUntilLimit() > 0) {
                openPrizeCollects_ = new java.util.ArrayList<java.lang.Integer>();
                mutable_bitField0_ |= 0x00000002;
              }
              while (input.getBytesUntilLimit() > 0) {
                openPrizeCollects_.add(input.readInt32());
              }
              input.popLimit(limit);
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
          funcOpenTimes_ = java.util.Collections.unmodifiableList(funcOpenTimes_);
        }
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          openPrizeCollects_ = java.util.Collections.unmodifiableList(openPrizeCollects_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return app.protobuf.server.FunctionOpenContent.internal_static_proto_FunctionOpenMinorProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.server.FunctionOpenContent.internal_static_proto_FunctionOpenMinorProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto.class, app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto.Builder.class);
    }

    public static com.google.protobuf.Parser<FunctionOpenMinorProto> PARSER =
        new com.google.protobuf.AbstractParser<FunctionOpenMinorProto>() {
      public FunctionOpenMinorProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new FunctionOpenMinorProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<FunctionOpenMinorProto> getParserForType() {
      return PARSER;
    }

    public static final int FUNCOPENTIMES_FIELD_NUMBER = 1;
    private java.util.List<app.protobuf.client.UtilContent.Int32PairProto> funcOpenTimes_;
    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    public java.util.List<app.protobuf.client.UtilContent.Int32PairProto> getFuncOpenTimesList() {
      return funcOpenTimes_;
    }
    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    public java.util.List<? extends app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> 
        getFuncOpenTimesOrBuilderList() {
      return funcOpenTimes_;
    }
    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    public int getFuncOpenTimesCount() {
      return funcOpenTimes_.size();
    }
    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    public app.protobuf.client.UtilContent.Int32PairProto getFuncOpenTimes(int index) {
      return funcOpenTimes_.get(index);
    }
    /**
     * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
     *
     * <pre>
     *记录功能开放时间（用来处理资源找回)
     * </pre>
     */
    public app.protobuf.client.UtilContent.Int32PairProtoOrBuilder getFuncOpenTimesOrBuilder(
        int index) {
      return funcOpenTimes_.get(index);
    }

    public static final int OPENPRIZECOLLECTS_FIELD_NUMBER = 2;
    private java.util.List<java.lang.Integer> openPrizeCollects_;
    /**
     * <code>repeated int32 openPrizeCollects = 2;</code>
     *
     * <pre>
     * 已领取的开启奖励id
     * </pre>
     */
    public java.util.List<java.lang.Integer>
        getOpenPrizeCollectsList() {
      return openPrizeCollects_;
    }
    /**
     * <code>repeated int32 openPrizeCollects = 2;</code>
     *
     * <pre>
     * 已领取的开启奖励id
     * </pre>
     */
    public int getOpenPrizeCollectsCount() {
      return openPrizeCollects_.size();
    }
    /**
     * <code>repeated int32 openPrizeCollects = 2;</code>
     *
     * <pre>
     * 已领取的开启奖励id
     * </pre>
     */
    public int getOpenPrizeCollects(int index) {
      return openPrizeCollects_.get(index);
    }

    private void initFields() {
      funcOpenTimes_ = java.util.Collections.emptyList();
      openPrizeCollects_ = java.util.Collections.emptyList();
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
      for (int i = 0; i < funcOpenTimes_.size(); i++) {
        output.writeMessage(1, funcOpenTimes_.get(i));
      }
      for (int i = 0; i < openPrizeCollects_.size(); i++) {
        output.writeInt32(2, openPrizeCollects_.get(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < funcOpenTimes_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, funcOpenTimes_.get(i));
      }
      {
        int dataSize = 0;
        for (int i = 0; i < openPrizeCollects_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(openPrizeCollects_.get(i));
        }
        size += dataSize;
        size += 1 * getOpenPrizeCollectsList().size();
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

    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto prototype) {
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
     * Protobuf type {@code proto.FunctionOpenMinorProto}
     *
     * <pre>
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.FunctionOpenMinorProto)
        app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.server.FunctionOpenContent.internal_static_proto_FunctionOpenMinorProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.server.FunctionOpenContent.internal_static_proto_FunctionOpenMinorProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto.class, app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto.Builder.class);
      }

      // Construct using app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto.newBuilder()
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
          getFuncOpenTimesFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (funcOpenTimesBuilder_ == null) {
          funcOpenTimes_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          funcOpenTimesBuilder_.clear();
        }
        openPrizeCollects_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.server.FunctionOpenContent.internal_static_proto_FunctionOpenMinorProto_descriptor;
      }

      public app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto getDefaultInstanceForType() {
        return app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto.getDefaultInstance();
      }

      public app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto build() {
        app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto buildPartial() {
        app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto result = new app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto(this);
        int from_bitField0_ = bitField0_;
        if (funcOpenTimesBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001)) {
            funcOpenTimes_ = java.util.Collections.unmodifiableList(funcOpenTimes_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.funcOpenTimes_ = funcOpenTimes_;
        } else {
          result.funcOpenTimes_ = funcOpenTimesBuilder_.build();
        }
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          openPrizeCollects_ = java.util.Collections.unmodifiableList(openPrizeCollects_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.openPrizeCollects_ = openPrizeCollects_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto) {
          return mergeFrom((app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto other) {
        if (other == app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto.getDefaultInstance()) return this;
        if (funcOpenTimesBuilder_ == null) {
          if (!other.funcOpenTimes_.isEmpty()) {
            if (funcOpenTimes_.isEmpty()) {
              funcOpenTimes_ = other.funcOpenTimes_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureFuncOpenTimesIsMutable();
              funcOpenTimes_.addAll(other.funcOpenTimes_);
            }
            onChanged();
          }
        } else {
          if (!other.funcOpenTimes_.isEmpty()) {
            if (funcOpenTimesBuilder_.isEmpty()) {
              funcOpenTimesBuilder_.dispose();
              funcOpenTimesBuilder_ = null;
              funcOpenTimes_ = other.funcOpenTimes_;
              bitField0_ = (bitField0_ & ~0x00000001);
              funcOpenTimesBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getFuncOpenTimesFieldBuilder() : null;
            } else {
              funcOpenTimesBuilder_.addAllMessages(other.funcOpenTimes_);
            }
          }
        }
        if (!other.openPrizeCollects_.isEmpty()) {
          if (openPrizeCollects_.isEmpty()) {
            openPrizeCollects_ = other.openPrizeCollects_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureOpenPrizeCollectsIsMutable();
            openPrizeCollects_.addAll(other.openPrizeCollects_);
          }
          onChanged();
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
        app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.server.FunctionOpenContent.FunctionOpenMinorProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<app.protobuf.client.UtilContent.Int32PairProto> funcOpenTimes_ =
        java.util.Collections.emptyList();
      private void ensureFuncOpenTimesIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          funcOpenTimes_ = new java.util.ArrayList<app.protobuf.client.UtilContent.Int32PairProto>(funcOpenTimes_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          app.protobuf.client.UtilContent.Int32PairProto, app.protobuf.client.UtilContent.Int32PairProto.Builder, app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> funcOpenTimesBuilder_;

      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public java.util.List<app.protobuf.client.UtilContent.Int32PairProto> getFuncOpenTimesList() {
        if (funcOpenTimesBuilder_ == null) {
          return java.util.Collections.unmodifiableList(funcOpenTimes_);
        } else {
          return funcOpenTimesBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public int getFuncOpenTimesCount() {
        if (funcOpenTimesBuilder_ == null) {
          return funcOpenTimes_.size();
        } else {
          return funcOpenTimesBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProto getFuncOpenTimes(int index) {
        if (funcOpenTimesBuilder_ == null) {
          return funcOpenTimes_.get(index);
        } else {
          return funcOpenTimesBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public Builder setFuncOpenTimes(
          int index, app.protobuf.client.UtilContent.Int32PairProto value) {
        if (funcOpenTimesBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureFuncOpenTimesIsMutable();
          funcOpenTimes_.set(index, value);
          onChanged();
        } else {
          funcOpenTimesBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public Builder setFuncOpenTimes(
          int index, app.protobuf.client.UtilContent.Int32PairProto.Builder builderForValue) {
        if (funcOpenTimesBuilder_ == null) {
          ensureFuncOpenTimesIsMutable();
          funcOpenTimes_.set(index, builderForValue.build());
          onChanged();
        } else {
          funcOpenTimesBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public Builder addFuncOpenTimes(app.protobuf.client.UtilContent.Int32PairProto value) {
        if (funcOpenTimesBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureFuncOpenTimesIsMutable();
          funcOpenTimes_.add(value);
          onChanged();
        } else {
          funcOpenTimesBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public Builder addFuncOpenTimes(
          int index, app.protobuf.client.UtilContent.Int32PairProto value) {
        if (funcOpenTimesBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureFuncOpenTimesIsMutable();
          funcOpenTimes_.add(index, value);
          onChanged();
        } else {
          funcOpenTimesBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public Builder addFuncOpenTimes(
          app.protobuf.client.UtilContent.Int32PairProto.Builder builderForValue) {
        if (funcOpenTimesBuilder_ == null) {
          ensureFuncOpenTimesIsMutable();
          funcOpenTimes_.add(builderForValue.build());
          onChanged();
        } else {
          funcOpenTimesBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public Builder addFuncOpenTimes(
          int index, app.protobuf.client.UtilContent.Int32PairProto.Builder builderForValue) {
        if (funcOpenTimesBuilder_ == null) {
          ensureFuncOpenTimesIsMutable();
          funcOpenTimes_.add(index, builderForValue.build());
          onChanged();
        } else {
          funcOpenTimesBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public Builder addAllFuncOpenTimes(
          java.lang.Iterable<? extends app.protobuf.client.UtilContent.Int32PairProto> values) {
        if (funcOpenTimesBuilder_ == null) {
          ensureFuncOpenTimesIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, funcOpenTimes_);
          onChanged();
        } else {
          funcOpenTimesBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public Builder clearFuncOpenTimes() {
        if (funcOpenTimesBuilder_ == null) {
          funcOpenTimes_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          funcOpenTimesBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public Builder removeFuncOpenTimes(int index) {
        if (funcOpenTimesBuilder_ == null) {
          ensureFuncOpenTimesIsMutable();
          funcOpenTimes_.remove(index);
          onChanged();
        } else {
          funcOpenTimesBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProto.Builder getFuncOpenTimesBuilder(
          int index) {
        return getFuncOpenTimesFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProtoOrBuilder getFuncOpenTimesOrBuilder(
          int index) {
        if (funcOpenTimesBuilder_ == null) {
          return funcOpenTimes_.get(index);  } else {
          return funcOpenTimesBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public java.util.List<? extends app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> 
           getFuncOpenTimesOrBuilderList() {
        if (funcOpenTimesBuilder_ != null) {
          return funcOpenTimesBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(funcOpenTimes_);
        }
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProto.Builder addFuncOpenTimesBuilder() {
        return getFuncOpenTimesFieldBuilder().addBuilder(
            app.protobuf.client.UtilContent.Int32PairProto.getDefaultInstance());
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public app.protobuf.client.UtilContent.Int32PairProto.Builder addFuncOpenTimesBuilder(
          int index) {
        return getFuncOpenTimesFieldBuilder().addBuilder(
            index, app.protobuf.client.UtilContent.Int32PairProto.getDefaultInstance());
      }
      /**
       * <code>repeated .proto.Int32PairProto funcOpenTimes = 1;</code>
       *
       * <pre>
       *记录功能开放时间（用来处理资源找回)
       * </pre>
       */
      public java.util.List<app.protobuf.client.UtilContent.Int32PairProto.Builder> 
           getFuncOpenTimesBuilderList() {
        return getFuncOpenTimesFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          app.protobuf.client.UtilContent.Int32PairProto, app.protobuf.client.UtilContent.Int32PairProto.Builder, app.protobuf.client.UtilContent.Int32PairProtoOrBuilder> 
          getFuncOpenTimesFieldBuilder() {
        if (funcOpenTimesBuilder_ == null) {
          funcOpenTimesBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              app.protobuf.client.UtilContent.Int32PairProto, app.protobuf.client.UtilContent.Int32PairProto.Builder, app.protobuf.client.UtilContent.Int32PairProtoOrBuilder>(
                  funcOpenTimes_,
                  ((bitField0_ & 0x00000001) == 0x00000001),
                  getParentForChildren(),
                  isClean());
          funcOpenTimes_ = null;
        }
        return funcOpenTimesBuilder_;
      }

      private java.util.List<java.lang.Integer> openPrizeCollects_ = java.util.Collections.emptyList();
      private void ensureOpenPrizeCollectsIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          openPrizeCollects_ = new java.util.ArrayList<java.lang.Integer>(openPrizeCollects_);
          bitField0_ |= 0x00000002;
         }
      }
      /**
       * <code>repeated int32 openPrizeCollects = 2;</code>
       *
       * <pre>
       * 已领取的开启奖励id
       * </pre>
       */
      public java.util.List<java.lang.Integer>
          getOpenPrizeCollectsList() {
        return java.util.Collections.unmodifiableList(openPrizeCollects_);
      }
      /**
       * <code>repeated int32 openPrizeCollects = 2;</code>
       *
       * <pre>
       * 已领取的开启奖励id
       * </pre>
       */
      public int getOpenPrizeCollectsCount() {
        return openPrizeCollects_.size();
      }
      /**
       * <code>repeated int32 openPrizeCollects = 2;</code>
       *
       * <pre>
       * 已领取的开启奖励id
       * </pre>
       */
      public int getOpenPrizeCollects(int index) {
        return openPrizeCollects_.get(index);
      }
      /**
       * <code>repeated int32 openPrizeCollects = 2;</code>
       *
       * <pre>
       * 已领取的开启奖励id
       * </pre>
       */
      public Builder setOpenPrizeCollects(
          int index, int value) {
        ensureOpenPrizeCollectsIsMutable();
        openPrizeCollects_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 openPrizeCollects = 2;</code>
       *
       * <pre>
       * 已领取的开启奖励id
       * </pre>
       */
      public Builder addOpenPrizeCollects(int value) {
        ensureOpenPrizeCollectsIsMutable();
        openPrizeCollects_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 openPrizeCollects = 2;</code>
       *
       * <pre>
       * 已领取的开启奖励id
       * </pre>
       */
      public Builder addAllOpenPrizeCollects(
          java.lang.Iterable<? extends java.lang.Integer> values) {
        ensureOpenPrizeCollectsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, openPrizeCollects_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 openPrizeCollects = 2;</code>
       *
       * <pre>
       * 已领取的开启奖励id
       * </pre>
       */
      public Builder clearOpenPrizeCollects() {
        openPrizeCollects_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.FunctionOpenMinorProto)
    }

    static {
      defaultInstance = new FunctionOpenMinorProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.FunctionOpenMinorProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_FunctionOpenMinorProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_FunctionOpenMinorProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n!server/function_open_server.proto\022\005pro" +
      "to\032\021client/util.proto\"a\n\026FunctionOpenMin" +
      "orProto\022,\n\rfuncOpenTimes\030\001 \003(\0132\025.proto.I" +
      "nt32PairProto\022\031\n\021openPrizeCollects\030\002 \003(\005" +
      "B,\n\023app.protobuf.serverB\023FunctionOpenCon" +
      "tentH\001"
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
    internal_static_proto_FunctionOpenMinorProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_FunctionOpenMinorProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_FunctionOpenMinorProto_descriptor,
        new java.lang.String[] { "FuncOpenTimes", "OpenPrizeCollects", });
    app.protobuf.client.UtilContent.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
