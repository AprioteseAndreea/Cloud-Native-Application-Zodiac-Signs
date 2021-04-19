package service;

import utils.Sign;
import io.grpc.stub.StreamObserver;
import proto.EuropeanServiceGrpc;
import proto.Zodiac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EuropeanImpl extends EuropeanServiceGrpc.EuropeanServiceImplBase {


    ArrayList<Sign> signs = new ArrayList<Sign>();

    public void readFromFile(String path) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(path));

        for (int i = 0; i < 12; i++) {
            String signName = scanner.next();
            String begin = scanner.next();
            String end = scanner.next();
            Sign currentSign = new Sign(signName, begin, end);
            signs.add(currentSign);
        }


        scanner.close();
    }

    private int getDayFromDate(String date) throws NumberFormatException {


        int month = 0;
        int contor = 0;
        StringBuilder sb = new StringBuilder();

            for (int i = 0; i < date.length(); i++) {
               if(date.charAt(i)=='/') contor++;
               if(contor==1 && date.charAt(i)!='/')sb.append(date.charAt(i));


            }
            month = Integer.parseInt(sb.toString());

        return month;

    }

    private int getMonthFromDate(String date) throws NumberFormatException {
        int month = 0;
        StringBuilder sb = new StringBuilder();

            for (int i = 0; i < date.length(); i++) {
                if (date.charAt(i)!='/'){
                 sb.append(date.charAt(i)); }
                else break;

            }
            month = Integer.parseInt(sb.toString());


        return month;
    }

    public String getSign(String date) throws FileNotFoundException {
        String sign = null;
        int day = getDayFromDate(date);
        int month = getMonthFromDate(date);
        try {
            readFromFile("src/main/resources/europeansign.txt");
            for (Sign s : signs) {
                int startMonth = s.getStartMonth();
                int endMonth = s.getEndMonth();
                int startDay = s.getStartDay();
                int endDay = s.getEndDay();
                if ((month == startMonth || month == endMonth) && (day >= startDay || day <= endDay)) {
                    sign = s.getZodiacSignName();
                    break;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        }
        return sign;

    }

    @Override
    public void getEuropeanSign(Zodiac.DateRequest request, StreamObserver<Zodiac.EuropeanZodiacResponse> responseObserver) {
        try {
            Zodiac.EuropeanZodiacResponse response = Zodiac.EuropeanZodiacResponse.newBuilder().setSign(getSign(request.getDate())).build();
            System.out.println("Your european sign is : " + response.getSign());

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }
}
