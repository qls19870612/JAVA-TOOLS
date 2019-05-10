// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: client/realm_client.proto

package app.protobuf.client;

public final class RealmClientContent {
  private RealmClientContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface RealmModuleObjClientProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.RealmModuleObjClientProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>optional int32 realmLevel = 1;</code>
     */
    boolean hasRealmLevel();
    /**
     * <code>optional int32 realmLevel = 1;</code>
     */
    int getRealmLevel();
  }
  /**
   * Protobuf type {@code proto.RealmModuleObjClientProto}
   *
   * <pre>
   * </pre>
   */
  public static final class RealmModuleObjClientProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.RealmModuleObjClientProto)
      RealmModuleObjClientProtoOrBuilder {
    // Use RealmModuleObjClientProto.newBuilder() to construct.
    private RealmModuleObjClientProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private RealmModuleObjClientProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final RealmModuleObjClientProto defaultInstance;
    public static RealmModuleObjClientProto getDefaultInstance() {
      return defaultInstance;
    }

    public RealmModuleObjClientProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private RealmModuleObjClientProto(
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
              realmLevel_ = input.readInt32();
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
      return app.protobuf.client.RealmClientContent.internal_static_proto_RealmModuleObjClientProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.client.RealmClientContent.internal_static_proto_RealmModuleObjClientProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.client.RealmClientContent.RealmModuleObjClientProto.class, app.protobuf.client.RealmClientContent.RealmModuleObjClientProto.Builder.class);
    }

    public static com.google.protobuf.Parser<RealmModuleObjClientProto> PARSER =
        new com.google.protobuf.AbstractParser<RealmModuleObjClientProto>() {
      public RealmModuleObjClientProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new RealmModuleObjClientProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<RealmModuleObjClientProto> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    public static final int REALMLEVEL_FIELD_NUMBER = 1;
    private int realmLevel_;
    /**
     * <code>optional int32 realmLevel = 1;</code>
     */
    public boolean hasRealmLevel() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>optional int32 realmLevel = 1;</code>
     */
    public int getRealmLevel() {
      return realmLevel_;
    }

    private void initFields() {
      realmLevel_ = 0;
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
        output.writeInt32(1, realmLevel_);
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
          .computeInt32Size(1, realmLevel_);
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

    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.client.RealmClientContent.RealmModuleObjClientProto prototype) {
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
     * Protobuf type {@code proto.RealmModuleObjClientProto}
     *
     * <pre>
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.RealmModuleObjClientProto)
        app.protobuf.client.RealmClientContent.RealmModuleObjClientProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.client.RealmClientContent.internal_static_proto_RealmModuleObjClientProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.client.RealmClientContent.internal_static_proto_RealmModuleObjClientProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.client.RealmClientContent.RealmModuleObjClientProto.class, app.protobuf.client.RealmClientContent.RealmModuleObjClientProto.Builder.class);
      }

      // Construct using app.protobuf.client.RealmClientContent.RealmModuleObjClientProto.newBuilder()
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
        realmLevel_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.client.RealmClientContent.internal_static_proto_RealmModuleObjClientProto_descriptor;
      }

      public app.protobuf.client.RealmClientContent.RealmModuleObjClientProto getDefaultInstanceForType() {
        return app.protobuf.client.RealmClientContent.RealmModuleObjClientProto.getDefaultInstance();
      }

      public app.protobuf.client.RealmClientContent.RealmModuleObjClientProto build() {
        app.protobuf.client.RealmClientContent.RealmModuleObjClientProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.client.RealmClientContent.RealmModuleObjClientProto buildPartial() {
        app.protobuf.client.RealmClientContent.RealmModuleObjClientProto result = new app.protobuf.client.RealmClientContent.RealmModuleObjClientProto(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.realmLevel_ = realmLevel_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.client.RealmClientContent.RealmModuleObjClientProto) {
          return mergeFrom((app.protobuf.client.RealmClientContent.RealmModuleObjClientProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.client.RealmClientContent.RealmModuleObjClientProto other) {
        if (other == app.protobuf.client.RealmClientContent.RealmModuleObjClientProto.getDefaultInstance()) return this;
        if (other.hasRealmLevel()) {
          setRealmLevel(other.getRealmLevel());
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
        app.protobuf.client.RealmClientContent.RealmModuleObjClientProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.client.RealmClientContent.RealmModuleObjClientProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private int realmLevel_ ;
      /**
       * <code>optional int32 realmLevel = 1;</code>
       */
      public boolean hasRealmLevel() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>optional int32 realmLevel = 1;</code>
       */
      public int getRealmLevel() {
        return realmLevel_;
      }
      /**
       * <code>optional int32 realmLevel = 1;</code>
       */
      public Builder setRealmLevel(int value) {
        bitField0_ |= 0x00000001;
        realmLevel_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>optional int32 realmLevel = 1;</code>
       */
      public Builder clearRealmLevel() {
        bitField0_ = (bitField0_ & ~0x00000001);
        realmLevel_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.RealmModuleObjClientProto)
    }

    static {
      defaultInstance = new RealmModuleObjClientProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.RealmModuleObjClientProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_RealmModuleObjClientProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_RealmModuleObjClientProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\031client/realm_client.proto\022\005proto\"/\n\031Re" +
      "almModuleObjClientProto\022\022\n\nrealmLevel\030\001 " +
      "\001(\005B+\n\023app.protobuf.clientB\022RealmClientC" +
      "ontentH\001"
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
    internal_static_proto_RealmModuleObjClientProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_RealmModuleObjClientProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_RealmModuleObjClientProto_descriptor,
        new java.lang.String[] { "RealmLevel", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
