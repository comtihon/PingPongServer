// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Pong.proto

package com.pingpong.packet.gen;

public final class Pong {
  private Pong() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface PongPacketOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required string responce = 1;
    /**
     * <code>required string responce = 1;</code>
     */
    boolean hasResponce();
    /**
     * <code>required string responce = 1;</code>
     */
    java.lang.String getResponce();
    /**
     * <code>required string responce = 1;</code>
     */
    com.google.protobuf.ByteString
        getResponceBytes();
  }
  /**
   * Protobuf type {@code com.pingpong.packet.gen.PongPacket}
   */
  public static final class PongPacket extends
      com.google.protobuf.GeneratedMessage
      implements PongPacketOrBuilder {
    // Use PongPacket.newBuilder() to construct.
    private PongPacket(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private PongPacket(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final PongPacket defaultInstance;
    public static PongPacket getDefaultInstance() {
      return defaultInstance;
    }

    public PongPacket getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private PongPacket(
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
              bitField0_ |= 0x00000001;
              responce_ = input.readBytes();
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
      return com.pingpong.packet.gen.Pong.internal_static_com_pingpong_packet_gen_PongPacket_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.pingpong.packet.gen.Pong.internal_static_com_pingpong_packet_gen_PongPacket_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.pingpong.packet.gen.Pong.PongPacket.class, com.pingpong.packet.gen.Pong.PongPacket.Builder.class);
    }

    public static com.google.protobuf.Parser<PongPacket> PARSER =
        new com.google.protobuf.AbstractParser<PongPacket>() {
      public PongPacket parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new PongPacket(input, extensionRegistry);
      }
    };

    @java.lang.Override
    public com.google.protobuf.Parser<PongPacket> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required string responce = 1;
    public static final int RESPONCE_FIELD_NUMBER = 1;
    private java.lang.Object responce_;
    /**
     * <code>required string responce = 1;</code>
     */
    public boolean hasResponce() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required string responce = 1;</code>
     */
    public java.lang.String getResponce() {
      java.lang.Object ref = responce_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          responce_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string responce = 1;</code>
     */
    public com.google.protobuf.ByteString
        getResponceBytes() {
      java.lang.Object ref = responce_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        responce_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private void initFields() {
      responce_ = "";
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasResponce()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getResponceBytes());
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
          .computeBytesSize(1, getResponceBytes());
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

    public static com.pingpong.packet.gen.Pong.PongPacket parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pingpong.packet.gen.Pong.PongPacket parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pingpong.packet.gen.Pong.PongPacket parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.pingpong.packet.gen.Pong.PongPacket parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.pingpong.packet.gen.Pong.PongPacket parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pingpong.packet.gen.Pong.PongPacket parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static com.pingpong.packet.gen.Pong.PongPacket parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static com.pingpong.packet.gen.Pong.PongPacket parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static com.pingpong.packet.gen.Pong.PongPacket parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static com.pingpong.packet.gen.Pong.PongPacket parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(com.pingpong.packet.gen.Pong.PongPacket prototype) {
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
     * Protobuf type {@code com.pingpong.packet.gen.PongPacket}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements com.pingpong.packet.gen.Pong.PongPacketOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.pingpong.packet.gen.Pong.internal_static_com_pingpong_packet_gen_PongPacket_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.pingpong.packet.gen.Pong.internal_static_com_pingpong_packet_gen_PongPacket_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.pingpong.packet.gen.Pong.PongPacket.class, com.pingpong.packet.gen.Pong.PongPacket.Builder.class);
      }

      // Construct using com.pingpong.packet.gen.Pong.PongPacket.newBuilder()
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
        responce_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.pingpong.packet.gen.Pong.internal_static_com_pingpong_packet_gen_PongPacket_descriptor;
      }

      public com.pingpong.packet.gen.Pong.PongPacket getDefaultInstanceForType() {
        return com.pingpong.packet.gen.Pong.PongPacket.getDefaultInstance();
      }

      public com.pingpong.packet.gen.Pong.PongPacket build() {
        com.pingpong.packet.gen.Pong.PongPacket result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public com.pingpong.packet.gen.Pong.PongPacket buildPartial() {
        com.pingpong.packet.gen.Pong.PongPacket result = new com.pingpong.packet.gen.Pong.PongPacket(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.responce_ = responce_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.pingpong.packet.gen.Pong.PongPacket) {
          return mergeFrom((com.pingpong.packet.gen.Pong.PongPacket)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.pingpong.packet.gen.Pong.PongPacket other) {
        if (other == com.pingpong.packet.gen.Pong.PongPacket.getDefaultInstance()) return this;
        if (other.hasResponce()) {
          bitField0_ |= 0x00000001;
          responce_ = other.responce_;
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasResponce()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.pingpong.packet.gen.Pong.PongPacket parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.pingpong.packet.gen.Pong.PongPacket) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required string responce = 1;
      private java.lang.Object responce_ = "";
      /**
       * <code>required string responce = 1;</code>
       */
      public boolean hasResponce() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required string responce = 1;</code>
       */
      public java.lang.String getResponce() {
        java.lang.Object ref = responce_;
        if (!(ref instanceof java.lang.String)) {
          java.lang.String s = ((com.google.protobuf.ByteString) ref)
              .toStringUtf8();
          responce_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string responce = 1;</code>
       */
      public com.google.protobuf.ByteString
          getResponceBytes() {
        java.lang.Object ref = responce_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          responce_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string responce = 1;</code>
       */
      public Builder setResponce(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        responce_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string responce = 1;</code>
       */
      public Builder clearResponce() {
        bitField0_ = (bitField0_ & ~0x00000001);
        responce_ = getDefaultInstance().getResponce();
        onChanged();
        return this;
      }
      /**
       * <code>required string responce = 1;</code>
       */
      public Builder setResponceBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        responce_ = value;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:com.pingpong.packet.gen.PongPacket)
    }

    static {
      defaultInstance = new PongPacket(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:com.pingpong.packet.gen.PongPacket)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_com_pingpong_packet_gen_PongPacket_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_com_pingpong_packet_gen_PongPacket_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nPong.proto\022\027com.pingpong.packet.gen\"\036\n" +
      "\nPongPacket\022\020\n\010responce\030\001 \002(\t"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_com_pingpong_packet_gen_PongPacket_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_com_pingpong_packet_gen_PongPacket_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_com_pingpong_packet_gen_PongPacket_descriptor,
              new java.lang.String[] { "Responce", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}