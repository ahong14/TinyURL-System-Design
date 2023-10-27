package com.tinyurl_system_design.key_generator_service.services;

import com.tinyurl_system_design.key_generator_service.models.ConsumedGeneratedKey;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.examples.lib.GeneratedKeyResponse;
import net.devh.boot.grpc.examples.lib.GetGeneratedKeyRequest;
import net.devh.boot.grpc.examples.lib.TinyURLProtoServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class GenerateKeyServiceProtoImpl extends TinyURLProtoServiceGrpc.TinyURLProtoServiceImplBase {
    final private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GenerateKeyServiceImpl generateKeyService;

    /**
     *
     * @param request gRPC request for GetGeneratedKeyRequest
     * @param responseObserver return gRPC response observer
     */
    @Override
    public void getGeneratedKey(GetGeneratedKeyRequest request, StreamObserver<GeneratedKeyResponse> responseObserver) {
        logger.info("Received gRPC request: " + request);
        // get generated key from database
        ConsumedGeneratedKey generatedKey = this.generateKeyService.getGeneratedKey();
        String generatedKeyString = generatedKey.getKey();
        logger.info("Generated Key: " + generatedKeyString);

        // build gRPC response
        GeneratedKeyResponse reply = GeneratedKeyResponse.newBuilder()
                .setGeneratedKey(generatedKeyString)
                .build();
        responseObserver.onNext(reply);

        // complete response observable
        responseObserver.onCompleted();
    }
}
