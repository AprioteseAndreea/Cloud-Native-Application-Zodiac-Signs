import java.util.Scanner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.Zodiac;
import proto.ZodiacServiceGrpc;


public class Main {

    public static boolean isValidDate(String date) {
        return true;

    }

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8999).usePlaintext().build();

        ZodiacServiceGrpc.ZodiacServiceBlockingStub zodiacStub = ZodiacServiceGrpc.newBlockingStub(channel);

        boolean isRunning = true;
        while (isRunning) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the date: ");
            String currentDate = sc.next();
            System.out.println("Enter the zodiac type: ");
            String type = sc.next();


            if (isValidDate(currentDate)) {

                Zodiac.ConfirmationResponse sign = zodiacStub.getZodiacSign(Zodiac.ZodiacRequest.newBuilder().
                        setDate(currentDate).
                        setType(type)
                        .build());
                  }

            }

           channel.shutdown();
        }
    }

