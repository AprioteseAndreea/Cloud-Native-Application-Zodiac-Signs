package service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import proto.ChineseServiceGrpc;
import proto.EuropeanServiceGrpc;
import proto.Zodiac;
import proto.ZodiacServiceGrpc;



public class ZodiacImpl extends ZodiacServiceGrpc.ZodiacServiceImplBase {


    @Override
    public void getZodiacSign(Zodiac.ZodiacRequest request, StreamObserver<Zodiac.ConfirmationResponse> responseObserver) {
        Zodiac.ConfirmationResponse response =  Zodiac.ConfirmationResponse.newBuilder().setMessage("Data was transferred succesfully!").build();

        System.out.println(response.getMessage());
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        if(request.getType().equals("European")){

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8998).usePlaintext().build();

            EuropeanServiceGrpc.EuropeanServiceBlockingStub europeanStub = EuropeanServiceGrpc.newBlockingStub(channel);
            Zodiac.EuropeanZodiacResponse europeanSign = europeanStub.getEuropeanSign(Zodiac.DateRequest.newBuilder().setDate(request.getDate())
                    .build());


        }else if(request.getType().equals("Chinese")){
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8997).usePlaintext().build();

            ChineseServiceGrpc.ChineseServiceBlockingStub chineseStub = ChineseServiceGrpc.newBlockingStub(channel);
            Zodiac.ChineseZodiacResponse chineseSign = chineseStub.getChineseSign(Zodiac.DateRequest.newBuilder().setDate(request.getDate()).build());
            // System.out.println("Your sign is: " + chineseSign.getSign());


        }


    }
}
