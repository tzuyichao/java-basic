package com.baeldung.grpc.impl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.greenrivers.grpc.HelloRequest;
import org.greenrivers.grpc.HelloResponse;
import org.greenrivers.grpc.HelloServiceGrpc;

/**
 * jvm_flags = [
 *         # quiet warnings from com.google.protobuf.UnsafeUtil,
 *         # see: https://github.com/google/protobuf/issues/3781
 *         "-XX:+IgnoreUnrecognizedVMOptions",
 *         "--add-opens=java.base/java.nio=ALL-UNNAMED",
 *         "--add-opens=java.base/java.lang=ALL-UNNAMED",
 *     ]
 */
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub = HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Baeldung")
                .setLastName("gRPC")
                .build());
        System.out.println(helloResponse.getGreeting());

        channel.shutdown();
    }
}
