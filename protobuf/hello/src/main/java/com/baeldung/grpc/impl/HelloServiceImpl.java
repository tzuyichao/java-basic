package com.baeldung.grpc.impl;

import io.grpc.stub.StreamObserver;
import org.greenrivers.grpc.HelloRequest;
import org.greenrivers.grpc.HelloResponse;
import org.greenrivers.grpc.HelloServiceGrpc;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseStreamObserver) {
        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();
        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }
}
