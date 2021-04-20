import java.util.Scanner;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import proto.Zodiac;
import proto.ZodiacServiceGrpc;


public class Main {

    private static int getDayFromDate(String date) throws NumberFormatException {


        int month;
        int counter = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < date.length(); i++) {
            if(date.charAt(i)=='/') counter++;
            if(counter==1 && date.charAt(i)!='/')sb.append(date.charAt(i));


        }
        month = Integer.parseInt(sb.toString());

        return month;

    }

    private static int getMonthFromDate(String date) throws NumberFormatException {
        int month;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < date.length(); i++) {
            if (date.charAt(i)!='/'){
                sb.append(date.charAt(i)); }
            else break;

        }
        month = Integer.parseInt(sb.toString());


        return month;
    }
    public static boolean isLeapYear(String year){
        boolean isLeap = false;
        int currentYear = Integer.parseInt(year);
        if (currentYear % 4 == 0) {
            if (currentYear % 100 == 0) {
                if (currentYear % 400 == 0)
                    isLeap = true;

            }
        }
        return isLeap;
    }
    public static boolean verifyLeapYear(String date) {
        boolean isOk=true;
        if(getMonthFromDate(date)==2 && isLeapYear(date.substring(date.length() - 4, date.length()))){
            isOk= getDayFromDate(date) <= 29;
        }
        return isOk;
    }

    public static boolean isValidDate(String date) {
        //Regular expression to accept date in MM/DD/YYY format

        String regex = "^[0-2]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        boolean result = date.matches(regex);
        return result && verifyLeapYear(date);

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
            System.out.println(zodiacStub.getZodiacSign(Zodiac.ZodiacRequest.newBuilder().
                    setDate(currentDate).
                    setType(type)
                    .build()));

            }

           channel.shutdown();
        }
    }

