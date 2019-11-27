// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: server/transform_server.proto

package app.protobuf.server;

public final class TransformServerContent {
  private TransformServerContent() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface TransformModuleObjMinorProtoOrBuilder extends
      // @@protoc_insertion_point(interface_extends:proto.TransformModuleObjMinorProto)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    java.util.List<app.protobuf.client.TransformClientContent.TransformTypeInfoClient> 
        getTransformTypeInfoList();
    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    app.protobuf.client.TransformClientContent.TransformTypeInfoClient getTransformTypeInfo(int index);
    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    int getTransformTypeInfoCount();
    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    java.util.List<? extends app.protobuf.client.TransformClientContent.TransformTypeInfoClientOrBuilder> 
        getTransformTypeInfoOrBuilderList();
    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    app.protobuf.client.TransformClientContent.TransformTypeInfoClientOrBuilder getTransformTypeInfoOrBuilder(
        int index);
  }
  /**
   * Protobuf type {@code proto.TransformModuleObjMinorProto}
   *
   * <pre>
   * </pre>
   */
  public static final class TransformModuleObjMinorProto extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:proto.TransformModuleObjMinorProto)
      TransformModuleObjMinorProtoOrBuilder {
    // Use TransformModuleObjMinorProto.newBuilder() to construct.
    private TransformModuleObjMinorProto(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private TransformModuleObjMinorProto(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final TransformModuleObjMinorProto defaultInstance;
    public static TransformModuleObjMinorProto getDefaultInstance() {
      return defaultInstance;
    }

    public TransformModuleObjMinorProto getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private TransformModuleObjMinorProto(
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
                transformTypeInfo_ = new java.util.ArrayList<app.protobuf.client.TransformClientContent.TransformTypeInfoClient>();
                mutable_bitField0_ |= 0x00000001;
              }
              transformTypeInfo_.add(input.readMessage(app.protobuf.client.TransformClientContent.TransformTypeInfoClient.PARSER, extensionRegistry));
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
          transformTypeInfo_ = java.util.Collections.unmodifiableList(transformTypeInfo_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return app.protobuf.server.TransformServerContent.internal_static_proto_TransformModuleObjMinorProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return app.protobuf.server.TransformServerContent.internal_static_proto_TransformModuleObjMinorProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto.class, app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto.Builder.class);
    }

    public static com.google.protobuf.Parser<TransformModuleObjMinorProto> PARSER =
        new com.google.protobuf.AbstractParser<TransformModuleObjMinorProto>() {
      public TransformModuleObjMinorProto parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new TransformModuleObjMinorProto(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<TransformModuleObjMinorProto> getParserForType() {
      return PARSER;
    }

    public static final int TRANSFORMTYPEINFO_FIELD_NUMBER = 1;
    private java.util.List<app.protobuf.client.TransformClientContent.TransformTypeInfoClient> transformTypeInfo_;
    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    public java.util.List<app.protobuf.client.TransformClientContent.TransformTypeInfoClient> getTransformTypeInfoList() {
      return transformTypeInfo_;
    }
    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    public java.util.List<? extends app.protobuf.client.TransformClientContent.TransformTypeInfoClientOrBuilder> 
        getTransformTypeInfoOrBuilderList() {
      return transformTypeInfo_;
    }
    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    public int getTransformTypeInfoCount() {
      return transformTypeInfo_.size();
    }
    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    public app.protobuf.client.TransformClientContent.TransformTypeInfoClient getTransformTypeInfo(int index) {
      return transformTypeInfo_.get(index);
    }
    /**
     * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
     */
    public app.protobuf.client.TransformClientContent.TransformTypeInfoClientOrBuilder getTransformTypeInfoOrBuilder(
        int index) {
      return transformTypeInfo_.get(index);
    }

    private void initFields() {
      transformTypeInfo_ = java.util.Collections.emptyList();
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
      for (int i = 0; i < transformTypeInfo_.size(); i++) {
        output.writeMessage(1, transformTypeInfo_.get(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      for (int i = 0; i < transformTypeInfo_.size(); i++) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, transformTypeInfo_.get(i));
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

    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto prototype) {
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
     * Protobuf type {@code proto.TransformModuleObjMinorProto}
     *
     * <pre>
     * </pre>
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:proto.TransformModuleObjMinorProto)
        app.protobuf.server.TransformServerContent.TransformModuleObjMinorProtoOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return app.protobuf.server.TransformServerContent.internal_static_proto_TransformModuleObjMinorProto_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return app.protobuf.server.TransformServerContent.internal_static_proto_TransformModuleObjMinorProto_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto.class, app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto.Builder.class);
      }

      // Construct using app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto.newBuilder()
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
          getTransformTypeInfoFieldBuilder();
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        if (transformTypeInfoBuilder_ == null) {
          transformTypeInfo_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          transformTypeInfoBuilder_.clear();
        }
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return app.protobuf.server.TransformServerContent.internal_static_proto_TransformModuleObjMinorProto_descriptor;
      }

      public app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto getDefaultInstanceForType() {
        return app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto.getDefaultInstance();
      }

      public app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto build() {
        app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto buildPartial() {
        app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto result = new app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto(this);
        int from_bitField0_ = bitField0_;
        if (transformTypeInfoBuilder_ == null) {
          if (((bitField0_ & 0x00000001) == 0x00000001)) {
            transformTypeInfo_ = java.util.Collections.unmodifiableList(transformTypeInfo_);
            bitField0_ = (bitField0_ & ~0x00000001);
          }
          result.transformTypeInfo_ = transformTypeInfo_;
        } else {
          result.transformTypeInfo_ = transformTypeInfoBuilder_.build();
        }
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto) {
          return mergeFrom((app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto other) {
        if (other == app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto.getDefaultInstance()) return this;
        if (transformTypeInfoBuilder_ == null) {
          if (!other.transformTypeInfo_.isEmpty()) {
            if (transformTypeInfo_.isEmpty()) {
              transformTypeInfo_ = other.transformTypeInfo_;
              bitField0_ = (bitField0_ & ~0x00000001);
            } else {
              ensureTransformTypeInfoIsMutable();
              transformTypeInfo_.addAll(other.transformTypeInfo_);
            }
            onChanged();
          }
        } else {
          if (!other.transformTypeInfo_.isEmpty()) {
            if (transformTypeInfoBuilder_.isEmpty()) {
              transformTypeInfoBuilder_.dispose();
              transformTypeInfoBuilder_ = null;
              transformTypeInfo_ = other.transformTypeInfo_;
              bitField0_ = (bitField0_ & ~0x00000001);
              transformTypeInfoBuilder_ = 
                com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                   getTransformTypeInfoFieldBuilder() : null;
            } else {
              transformTypeInfoBuilder_.addAllMessages(other.transformTypeInfo_);
            }
          }
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
        app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (app.protobuf.server.TransformServerContent.TransformModuleObjMinorProto) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.util.List<app.protobuf.client.TransformClientContent.TransformTypeInfoClient> transformTypeInfo_ =
        java.util.Collections.emptyList();
      private void ensureTransformTypeInfoIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          transformTypeInfo_ = new java.util.ArrayList<app.protobuf.client.TransformClientContent.TransformTypeInfoClient>(transformTypeInfo_);
          bitField0_ |= 0x00000001;
         }
      }

      private com.google.protobuf.RepeatedFieldBuilder<
          app.protobuf.client.TransformClientContent.TransformTypeInfoClient, app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder, app.protobuf.client.TransformClientContent.TransformTypeInfoClientOrBuilder> transformTypeInfoBuilder_;

      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public java.util.List<app.protobuf.client.TransformClientContent.TransformTypeInfoClient> getTransformTypeInfoList() {
        if (transformTypeInfoBuilder_ == null) {
          return java.util.Collections.unmodifiableList(transformTypeInfo_);
        } else {
          return transformTypeInfoBuilder_.getMessageList();
        }
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public int getTransformTypeInfoCount() {
        if (transformTypeInfoBuilder_ == null) {
          return transformTypeInfo_.size();
        } else {
          return transformTypeInfoBuilder_.getCount();
        }
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public app.protobuf.client.TransformClientContent.TransformTypeInfoClient getTransformTypeInfo(int index) {
        if (transformTypeInfoBuilder_ == null) {
          return transformTypeInfo_.get(index);
        } else {
          return transformTypeInfoBuilder_.getMessage(index);
        }
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public Builder setTransformTypeInfo(
          int index, app.protobuf.client.TransformClientContent.TransformTypeInfoClient value) {
        if (transformTypeInfoBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureTransformTypeInfoIsMutable();
          transformTypeInfo_.set(index, value);
          onChanged();
        } else {
          transformTypeInfoBuilder_.setMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public Builder setTransformTypeInfo(
          int index, app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder builderForValue) {
        if (transformTypeInfoBuilder_ == null) {
          ensureTransformTypeInfoIsMutable();
          transformTypeInfo_.set(index, builderForValue.build());
          onChanged();
        } else {
          transformTypeInfoBuilder_.setMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public Builder addTransformTypeInfo(app.protobuf.client.TransformClientContent.TransformTypeInfoClient value) {
        if (transformTypeInfoBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureTransformTypeInfoIsMutable();
          transformTypeInfo_.add(value);
          onChanged();
        } else {
          transformTypeInfoBuilder_.addMessage(value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public Builder addTransformTypeInfo(
          int index, app.protobuf.client.TransformClientContent.TransformTypeInfoClient value) {
        if (transformTypeInfoBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          ensureTransformTypeInfoIsMutable();
          transformTypeInfo_.add(index, value);
          onChanged();
        } else {
          transformTypeInfoBuilder_.addMessage(index, value);
        }
        return this;
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public Builder addTransformTypeInfo(
          app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder builderForValue) {
        if (transformTypeInfoBuilder_ == null) {
          ensureTransformTypeInfoIsMutable();
          transformTypeInfo_.add(builderForValue.build());
          onChanged();
        } else {
          transformTypeInfoBuilder_.addMessage(builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public Builder addTransformTypeInfo(
          int index, app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder builderForValue) {
        if (transformTypeInfoBuilder_ == null) {
          ensureTransformTypeInfoIsMutable();
          transformTypeInfo_.add(index, builderForValue.build());
          onChanged();
        } else {
          transformTypeInfoBuilder_.addMessage(index, builderForValue.build());
        }
        return this;
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public Builder addAllTransformTypeInfo(
          java.lang.Iterable<? extends app.protobuf.client.TransformClientContent.TransformTypeInfoClient> values) {
        if (transformTypeInfoBuilder_ == null) {
          ensureTransformTypeInfoIsMutable();
          com.google.protobuf.AbstractMessageLite.Builder.addAll(
              values, transformTypeInfo_);
          onChanged();
        } else {
          transformTypeInfoBuilder_.addAllMessages(values);
        }
        return this;
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public Builder clearTransformTypeInfo() {
        if (transformTypeInfoBuilder_ == null) {
          transformTypeInfo_ = java.util.Collections.emptyList();
          bitField0_ = (bitField0_ & ~0x00000001);
          onChanged();
        } else {
          transformTypeInfoBuilder_.clear();
        }
        return this;
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public Builder removeTransformTypeInfo(int index) {
        if (transformTypeInfoBuilder_ == null) {
          ensureTransformTypeInfoIsMutable();
          transformTypeInfo_.remove(index);
          onChanged();
        } else {
          transformTypeInfoBuilder_.remove(index);
        }
        return this;
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder getTransformTypeInfoBuilder(
          int index) {
        return getTransformTypeInfoFieldBuilder().getBuilder(index);
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public app.protobuf.client.TransformClientContent.TransformTypeInfoClientOrBuilder getTransformTypeInfoOrBuilder(
          int index) {
        if (transformTypeInfoBuilder_ == null) {
          return transformTypeInfo_.get(index);  } else {
          return transformTypeInfoBuilder_.getMessageOrBuilder(index);
        }
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public java.util.List<? extends app.protobuf.client.TransformClientContent.TransformTypeInfoClientOrBuilder> 
           getTransformTypeInfoOrBuilderList() {
        if (transformTypeInfoBuilder_ != null) {
          return transformTypeInfoBuilder_.getMessageOrBuilderList();
        } else {
          return java.util.Collections.unmodifiableList(transformTypeInfo_);
        }
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder addTransformTypeInfoBuilder() {
        return getTransformTypeInfoFieldBuilder().addBuilder(
            app.protobuf.client.TransformClientContent.TransformTypeInfoClient.getDefaultInstance());
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder addTransformTypeInfoBuilder(
          int index) {
        return getTransformTypeInfoFieldBuilder().addBuilder(
            index, app.protobuf.client.TransformClientContent.TransformTypeInfoClient.getDefaultInstance());
      }
      /**
       * <code>repeated .proto.TransformTypeInfoClient transformTypeInfo = 1;</code>
       */
      public java.util.List<app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder> 
           getTransformTypeInfoBuilderList() {
        return getTransformTypeInfoFieldBuilder().getBuilderList();
      }
      private com.google.protobuf.RepeatedFieldBuilder<
          app.protobuf.client.TransformClientContent.TransformTypeInfoClient, app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder, app.protobuf.client.TransformClientContent.TransformTypeInfoClientOrBuilder> 
          getTransformTypeInfoFieldBuilder() {
        if (transformTypeInfoBuilder_ == null) {
          transformTypeInfoBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
              app.protobuf.client.TransformClientContent.TransformTypeInfoClient, app.protobuf.client.TransformClientContent.TransformTypeInfoClient.Builder, app.protobuf.client.TransformClientContent.TransformTypeInfoClientOrBuilder>(
                  transformTypeInfo_,
                  ((bitField0_ & 0x00000001) == 0x00000001),
                  getParentForChildren(),
                  isClean());
          transformTypeInfo_ = null;
        }
        return transformTypeInfoBuilder_;
      }

      // @@protoc_insertion_point(builder_scope:proto.TransformModuleObjMinorProto)
    }

    static {
      defaultInstance = new TransformModuleObjMinorProto(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.TransformModuleObjMinorProto)
  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_proto_TransformModuleObjMinorProto_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_proto_TransformModuleObjMinorProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035server/transform_server.proto\022\005proto\032\035" +
      "client/transform_client.proto\"Y\n\034Transfo" +
      "rmModuleObjMinorProto\0229\n\021transformTypeIn" +
      "fo\030\001 \003(\0132\036.proto.TransformTypeInfoClient" +
      "B/\n\023app.protobuf.serverB\026TransformServer" +
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
          app.protobuf.client.TransformClientContent.getDescriptor(),
        }, assigner);
    internal_static_proto_TransformModuleObjMinorProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_proto_TransformModuleObjMinorProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_proto_TransformModuleObjMinorProto_descriptor,
        new java.lang.String[] { "TransformTypeInfo", });
    app.protobuf.client.TransformClientContent.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}