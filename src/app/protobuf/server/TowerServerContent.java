// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: server/tower_server.proto

package app.protobuf.server;

public final class TowerServerContent {
  private TowerServerContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface TowerServerModuleObjProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.TowerServerModuleObjProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 last_success_level = 1;</code>
     *
     * <pre>
     *最后一次通关时的等级
     * </pre>
     */
    boolean hasLastSuccessLevel();
    /**
     * <code>optional int32 last_success_level = 1;</code>
     *
     * <pre>
     *最后一次通关时的等级
     * </pre>
     */
    int getLastSuccessLevel();

    /**
     * <code>optional int32 reachedTime = 2;</code>
     *
     * <pre>
     *最后一次通关时的时间
     * </pre>
     */
    boolean hasReachedTime();
    /**
     * <code>optional int32 reachedTime = 2;</code>
     *
     * <pre>
     *最后一次通关时的时间
     * </pre>
     */
    int getReachedTime();
  }
  /**
   * Protobuf type {@code proto.TowerServerModuleObjProto}
   */
  public static final class TowerServerModuleObjProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.TowerServerModuleObjProto)
      TowerServerModuleObjProtoOrBuilder {
    // Use TowerServerModuleObjProto.newBuilder() to construct.
    private TowerServerModuleObjProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private TowerServerModuleObjProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final TowerServerModuleObjProto defaultInstance;
    public static TowerServerModuleObjProto getDefaultInstance() {
      return defaultInstance;
    }

    public TowerServerModuleObjProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private TowerServerModuleObjProto(
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
              lastSuccessLevel_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
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
      return app.protobuf.server.TowerServerContent.internal_static_proto_TowerServerModuleObjProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.server.TowerServerContent.internal_static_proto_TowerServerModuleObjProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.server.TowerServerContent.TowerServerModuleObjProto.class, app.protobuf.server.TowerServerContent.TowerServerModuleObjProto.Builder.class);
    }

    public static com.google.protobuf.Parser<TowerServerModuleObjProto> PARSER =
        new com.google.protobuf.AbstractParser<TowerServerModuleObjProto>() {
      public TowerServerModuleObjProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new TowerServerModuleObjProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<TowerServerModuleObjProto> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int LAST_SUCCESS_LEVEL_FIELD_NUMBER = 1;
    private int lastSuccessLevel_;
    /**
     * <code>optional int32 last_success_level = 1;</code>
     *
     * <pre>
     *最后一次通关时的等级
     * </pre>
     */
    public boolean hasLastSuccessLevel() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 last_success_level = 1;</code>
     *
     * <pre>
     *最后一次通关时的等级
     * </pre>
     */
    public int getLastSuccessLevel() {
      return lastSuccessLevel_;
    }

    public static final int REACHEDTIME_FIELD_NUMBER = 2;
    private int reachedTime_;
    /**
     * <code>optional int32 reachedTime = 2;</code>
     *
     * <pre>
     *最后一次通关时的时间
     * </pre>
     */
    public boolean hasReachedTime() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>optional int32 reachedTime = 2;</code>
     *
     * <pre>
     *最后一次通关时的时间
     * </pre>
     */
    public int getReachedTime() {
      return reachedTime_;
    }

    private void initFields() {
      lastSuccessLevel_ = 0;
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
        output.writeInt32(1, lastSuccessLevel_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, reachedTime_);
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
          .computeInt32Size(1, lastSuccessLevel_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, reachedTime_);
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

    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.server.TowerServerContent.TowerServerModuleObjProto prototype) {
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
     * Protobuf type {@code proto.TowerServerModuleObjProto}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.TowerServerModuleObjProto)
        app.protobuf.server.TowerServerContent.TowerServerModuleObjProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.server.TowerServerContent.internal_static_proto_TowerServerModuleObjProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.server.TowerServerContent.internal_static_proto_TowerServerModuleObjProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.server.TowerServerContent.TowerServerModuleObjProto.class, app.protobuf.server.TowerServerContent.TowerServerModuleObjProto.Builder.class);
      }

      // Construct using app.protobuf.server.TowerServerContent.TowerServerModuleObjProto.newBuilder()
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
        lastSuccessLevel_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        reachedTime_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.server.TowerServerContent.internal_static_proto_TowerServerModuleObjProto_descriptor;
      }

      public app.protobuf.server.TowerServerContent.TowerServerModuleObjProto getDefaultInstanceForType() {
        return app.protobuf.server.TowerServerContent.TowerServerModuleObjProto.getDefaultInstance();
      }

      public app.protobuf.server.TowerServerContent.TowerServerModuleObjProto build() {
        app.protobuf.server.TowerServerContent.TowerServerModuleObjProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.server.TowerServerContent.TowerServerModuleObjProto buildPartial() {
        app.protobuf.server.TowerServerContent.TowerServerModuleObjProto result = new app.protobuf.server.TowerServerContent.TowerServerModuleObjProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.lastSuccessLevel_ = lastSuccessLevel_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.reachedTime_ = reachedTime_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.server.TowerServerContent.TowerServerModuleObjProto) {
          return mergeFrom((app.protobuf.server.TowerServerContent.TowerServerModuleObjProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.server.TowerServerContent.TowerServerModuleObjProto other) {
        if (other == app.protobuf.server.TowerServerContent.TowerServerModuleObjProto.getDefaultInstance()) return this;
        if (other.hasLastSuccessLevel()) {
          setLastSuccessLevel(other.getLastSuccessLevel());
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
        app.protobuf.server.TowerServerContent.TowerServerModuleObjProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.server.TowerServerContent.TowerServerModuleObjProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int lastSuccessLevel_ ;
      /**
       * <code>optional int32 last_success_level = 1;</code>
       *
       * <pre>
       *最后一次通关时的等级
       * </pre>
       */
      public boolean hasLastSuccessLevel() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 last_success_level = 1;</code>
       *
       * <pre>
       *最后一次通关时的等级
       * </pre>
       */
      public int getLastSuccessLevel() {
        return lastSuccessLevel_;
      }
      /**
       * <code>optional int32 last_success_level = 1;</code>
       *
       * <pre>
       *最后一次通关时的等级
       * </pre>
       */
      public Builder setLastSuccessLevel(int value) {
        bitField0_ |= 0x00000001;
        lastSuccessLevel_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 last_success_level = 1;</code>
       *
       * <pre>
       *最后一次通关时的等级
       * </pre>
       */
      public Builder clearLastSuccessLevel() {
        bitField0_ = (bitField0_ & ~0x00000001);
        lastSuccessLevel_ = 0;
        onChanged();
        return this;
      }

      private int reachedTime_ ;
      /**
       * <code>optional int32 reachedTime = 2;</code>
       *
       * <pre>
       *最后一次通关时的时间
       * </pre>
       */
      public boolean hasReachedTime() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>optional int32 reachedTime = 2;</code>
       *
       * <pre>
       *最后一次通关时的时间
       * </pre>
       */
      public int getReachedTime() {
        return reachedTime_;
      }
      /**
       * <code>optional int32 reachedTime = 2;</code>
       *
       * <pre>
       *最后一次通关时的时间
       * </pre>
       */
      public Builder setReachedTime(int value) {
        bitField0_ |= 0x00000002;
        reachedTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 reachedTime = 2;</code>
       *
       * <pre>
       *最后一次通关时的时间
       * </pre>
       */
      public Builder clearReachedTime() {
        bitField0_ = (bitField0_ & ~0x00000002);
        reachedTime_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.TowerServerModuleObjProto)
    }

    static {
      defaultInstance = new TowerServerModuleObjProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.TowerServerModuleObjProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_TowerServerModuleObjProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_TowerServerModuleObjProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031server/tower_server.proto\022\005proto\"L\n\031To" +
      "werServerModuleObjProto\022\032\n\022last_success_" +
      "level\030\001 \001(\005\022\023\n\013reachedTime\030\002 \001(\005B+\n\023app." +
      "protobuf.serverB\022TowerServerContentH\001"
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
    internal_static_proto_TowerServerModuleObjProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_TowerServerModuleObjProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_TowerServerModuleObjProto_descriptor,
        new java.lang.String[] { "LastSuccessLevel", "ReachedTime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
