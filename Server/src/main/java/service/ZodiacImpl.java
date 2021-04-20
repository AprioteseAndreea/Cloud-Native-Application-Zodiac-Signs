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

        if(request.getType().equals("European")){

            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8998).usePlaintext().build();

            EuropeanServiceGrpc.EuropeanServiceBlockingStub europeanStub = EuropeanServiceGrpc.newBlockingStub(channel);
            Zodiac.EuropeanZodiacResponse europeanSign = europeanStub.getEuropeanSign(Zodiac.DateRequest.newBuilder().setDate(request.getDate())
                    .build());
            System.out.println("Your sign is: " + europeanSign.getSign());
            Zodiac.ConfirmationResponse response =  Zodiac.ConfirmationResponse.newBuilder().setMessage(europeanSign.getSign().toString()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();


        }else if(request.getType().equals("Chinese")){
            ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8997).usePlaintext().build();

            ChineseServiceGrpc.ChineseServiceBlockingStub chineseStub = ChineseServiceGrpc.newBlockingStub(channel);
            Zodiac.ChineseZodiacResponse chineseSign = chineseStub.getChineseSign(Zodiac.DateRequest.newBuilder().setDate(request.getDate().toString()).build());
             System.out.println("Your sign is: " + chineseSign.getSign());
            Zodiac.ConfirmationResponse response =  Zodiac.ConfirmationResponse.newBuilder().setMessage(chineseSign.getSign().toString()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();


        }





    }
}
