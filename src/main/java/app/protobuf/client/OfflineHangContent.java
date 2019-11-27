// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client/offline_hang_client.proto

package app.protobuf.client;

public final class OfflineHangContent {
  private OfflineHangContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface OfflineHangModuleObjClientProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.OfflineHangModuleObjClientProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional bool canJoinTeam = 1;</code>
     *
     * <pre>
     * 离线挂机时是否自动组队
     * </pre>
     */
    boolean hasCanJoinTeam();
    /**
     * <code>optional bool canJoinTeam = 1;</code>
     *
     * <pre>
     * 离线挂机时是否自动组队
     * </pre>
     */
    boolean getCanJoinTeam();

    /**
     * <code>optional bool canAutoPayRelive = 2;</code>
     *
     * <pre>
     * 离线挂机时是否自动买活
     * </pre>
     */
    boolean hasCanAutoPayRelive();
    /**
     * <code>optional bool canAutoPayRelive = 2;</code>
     *
     * <pre>
     * 离线挂机时是否自动买活
     * </pre>
     */
    boolean getCanAutoPayRelive();

    /**
     * <code>optional int32 offlineTime = 3;</code>
     *
     * <pre>
     * 离线时长
     * </pre>
     */
    boolean hasOfflineTime();
    /**
     * <code>optional int32 offlineTime = 3;</code>
     *
     * <pre>
     * 离线时长
     * </pre>
     */
    int getOfflineTime();
  }
  /**
   * Protobuf type {@code proto.OfflineHangModuleObjClientProto}
   */
  public static final class OfflineHangModuleObjClientProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.OfflineHangModuleObjClientProto)
      OfflineHangModuleObjClientProtoOrBuilder {
    // Use OfflineHangModuleObjClientProto.newBuilder() to construct.
    private OfflineHangModuleObjClientProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private OfflineHangModuleObjClientProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final OfflineHangModuleObjClientProto defaultInstance;
    public static OfflineHangModuleObjClientProto getDefaultInstance() {
      return defaultInstance;
    }

    public OfflineHangModuleObjClientProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private OfflineHangModuleObjClientProto(
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
              canJoinTeam_ = input.readBool();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              canAutoPayRelive_ = input.readBool();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              offlineTime_ = input.readInt32();
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
      return app.protobuf.client.OfflineHangContent.internal_static_proto_OfflineHangModuleObjClientProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.client.OfflineHangContent.internal_static_proto_OfflineHangModuleObjClientProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto.class, app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto.Builder.class);
    }

    public static com.google.protobuf.Parser<OfflineHangModuleObjClientProto> PARSER =
        new com.google.protobuf.AbstractParser<OfflineHangModuleObjClientProto>() {
      public OfflineHangModuleObjClientProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new OfflineHangModuleObjClientProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<OfflineHangModuleObjClientProto> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int CANJOINTEAM_FIELD_NUMBER = 1;
    private boolean canJoinTeam_;
    /**
     * <code>optional bool canJoinTeam = 1;</code>
     *
     * <pre>
     * 离线挂机时是否自动组队
     * </pre>
     */
    public boolean hasCanJoinTeam() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional bool canJoinTeam = 1;</code>
     *
     * <pre>
     * 离线挂机时是否自动组队
     * </pre>
     */
    public boolean getCanJoinTeam() {
      return canJoinTeam_;
    }

    public static final int CANAUTOPAYRELIVE_FIELD_NUMBER = 2;
    private boolean canAutoPayRelive_;
    /**
     * <code>optional bool canAutoPayRelive = 2;</code>
     *
     * <pre>
     * 离线挂机时是否自动买活
     * </pre>
     */
    public boolean hasCanAutoPayRelive() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional bool canAutoPayRelive = 2;</code>
     *
     * <pre>
     * 离线挂机时是否自动买活
     * </pre>
     */
    public boolean getCanAutoPayRelive() {
      return canAutoPayRelive_;
    }

    public static final int OFFLINETIME_FIELD_NUMBER = 3;
    private int offlineTime_;
    /**
     * <code>optional int32 offlineTime = 3;</code>
     *
     * <pre>
     * 离线时长
     * </pre>
     */
    public boolean hasOfflineTime() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>optional int32 offlineTime = 3;</code>
     *
     * <pre>
     * 离线时长
     * </pre>
     */
    public int getOfflineTime() {
      return offlineTime_;
    }

    private void initFields() {
      canJoinTeam_ = false;
      canAutoPayRelive_ = false;
      offlineTime_ = 0;
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
        output.writeBool(1, canJoinTeam_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBool(2, canAutoPayRelive_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeInt32(3, offlineTime_);
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
          .computeBoolSize(1, canJoinTeam_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(2, canAutoPayRelive_);
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, offlineTime_);
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

    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto prototype) {
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
     * Protobuf type {@code proto.OfflineHangModuleObjClientProto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.OfflineHangModuleObjClientProto)
        app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.client.OfflineHangContent.internal_static_proto_OfflineHangModuleObjClientProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.client.OfflineHangContent.internal_static_proto_OfflineHangModuleObjClientProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto.class, app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto.Builder.class);
      }

      // Construct using app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto.newBuilder()
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
        canJoinTeam_ = false;
        bitField0_ = (bitField0_ & ~0x00000001);
        canAutoPayRelive_ = false;
        bitField0_ = (bitField0_ & ~0x00000002);
        offlineTime_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.client.OfflineHangContent.internal_static_proto_OfflineHangModuleObjClientProto_descriptor;
      }

      public app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto getDefaultInstanceForType() {
        return app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto.getDefaultInstance();
      }

      public app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto build() {
        app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto buildPartial() {
        app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto result = new app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.canJoinTeam_ = canJoinTeam_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.canAutoPayRelive_ = canAutoPayRelive_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.offlineTime_ = offlineTime_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto) {
          return mergeFrom((app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto other) {
        if (other == app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto.getDefaultInstance()) return this;
        if (other.hasCanJoinTeam()) {
          setCanJoinTeam(other.getCanJoinTeam());
        }
        if (other.hasCanAutoPayRelive()) {
          setCanAutoPayRelive(other.getCanAutoPayRelive());
        }
        if (other.hasOfflineTime()) {
          setOfflineTime(other.getOfflineTime());
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
        app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.client.OfflineHangContent.OfflineHangModuleObjClientProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private boolean canJoinTeam_ ;
      /**
       * <code>optional bool canJoinTeam = 1;</code>
       *
       * <pre>
       * 离线挂机时是否自动组队
       * </pre>
       */
      public boolean hasCanJoinTeam() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional bool canJoinTeam = 1;</code>
       *
       * <pre>
       * 离线挂机时是否自动组队
       * </pre>
       */
      public boolean getCanJoinTeam() {
        return canJoinTeam_;
      }
      /**
       * <code>optional bool canJoinTeam = 1;</code>
       *
       * <pre>
       * 离线挂机时是否自动组队
       * </pre>
       */
      public Builder setCanJoinTeam(boolean value) {
        bitField0_ |= 0x00000001;
        canJoinTeam_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bool canJoinTeam = 1;</code>
       *
       * <pre>
       * 离线挂机时是否自动组队
       * </pre>
       */
      public Builder clearCanJoinTeam() {
        bitField0_ = (bitField0_ & ~0x00000001);
        canJoinTeam_ = false;
        onChanged();
        return this;
      }

      private boolean canAutoPayRelive_ ;
      /**
       * <code>optional bool canAutoPayRelive = 2;</code>
       *
       * <pre>
       * 离线挂机时是否自动买活
       * </pre>
       */
      public boolean hasCanAutoPayRelive() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional bool canAutoPayRelive = 2;</code>
       *
       * <pre>
       * 离线挂机时是否自动买活
       * </pre>
       */
      public boolean getCanAutoPayRelive() {
        return canAutoPayRelive_;
      }
      /**
       * <code>optional bool canAutoPayRelive = 2;</code>
       *
       * <pre>
       * 离线挂机时是否自动买活
       * </pre>
       */
      public Builder setCanAutoPayRelive(boolean value) {
        bitField0_ |= 0x00000002;
        canAutoPayRelive_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional bool canAutoPayRelive = 2;</code>
       *
       * <pre>
       * 离线挂机时是否自动买活
       * </pre>
       */
      public Builder clearCanAutoPayRelive() {
        bitField0_ = (bitField0_ & ~0x00000002);
        canAutoPayRelive_ = false;
        onChanged();
        return this;
      }

      private int offlineTime_ ;
      /**
       * <code>optional int32 offlineTime = 3;</code>
       *
       * <pre>
       * 离线时长
       * </pre>
       */
      public boolean hasOfflineTime() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      /**
       * <code>optional int32 offlineTime = 3;</code>
       *
       * <pre>
       * 离线时长
       * </pre>
       */
      public int getOfflineTime() {
        return offlineTime_;
      }
      /**
       * <code>optional int32 offlineTime = 3;</code>
       *
       * <pre>
       * 离线时长
       * </pre>
       */
      public Builder setOfflineTime(int value) {
        bitField0_ |= 0x00000004;
        offlineTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 offlineTime = 3;</code>
       *
       * <pre>
       * 离线时长
       * </pre>
       */
      public Builder clearOfflineTime() {
        bitField0_ = (bitField0_ & ~0x00000004);
        offlineTime_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.OfflineHangModuleObjClientProto)
    }

    static {
      defaultInstance = new OfflineHangModuleObjClientProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.OfflineHangModuleObjClientProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_OfflineHangModuleObjClientProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_OfflineHangModuleObjClientProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n client/offline_hang_client.proto\022\005prot" +
      "o\032\022client/goods.proto\"e\n\037OfflineHangModu" +
      "leObjClientProto\022\023\n\013canJoinTeam\030\001 \001(\010\022\030\n" +
      "\020canAutoPayRelive\030\002 \001(\010\022\023\n\013offlineTime\030\003" +
      " \001(\005B+\n\023app.protobuf.clientB\022OfflineHang" +
      "ContentH\001"
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
    internal_static_proto_OfflineHangModuleObjClientProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_OfflineHangModuleObjClientProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_OfflineHangModuleObjClientProto_descriptor,
        new java.lang.String[] { "CanJoinTeam", "CanAutoPayRelive", "OfflineTime", });
    app.protobuf.client.GoodsContent.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}