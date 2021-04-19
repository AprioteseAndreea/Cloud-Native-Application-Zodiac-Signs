package service;

import utils.ChineseSign;
import io.grpc.stub.StreamObserver;
import proto.ChineseServiceGrpc;
import proto.Zodiac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChineseImpl extends ChineseServiceGrpc.ChineseServiceImplBase {
    ArrayList<ChineseSign> signs = new ArrayList<ChineseSign>();

    public void readFromFile(String path) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File(path));
        for (int i = 0; i < 12; i++) {
            String signName = scanner.next();
            ArrayList<String> years = new ArrayList<String>();
            for (int j = 0; j < 10; j++) {
                String year = scanner.next();
                years.add(year);
            }
            ChineseSign currentSign = new ChineseSign(signName, years);
            signs.add(currentSign);

        }


        scanner.close();
    }

    public String getSign(String date) throws FileNotFoundException {
        String sign=null;
        String birthYear = date.substring(date.length() - 4, date.length());
        try {
            readFromFile("src/main/resources/chinesesign.txt");
            for(ChineseSign cs : signs){
                if(cs.getYears().contains(birthYear)){
                    sign=cs.getName();
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        }
        return sign;

    }

    @Override
    public void getChineseSign(Zodiac.DateRequest request, StreamObserver<Zodiac.ChineseZodiacResponse> responseObserver) {
        try {
            Zodiac.ChineseZodiacResponse response = Zodiac.ChineseZodiacResponse.newBuilder().setSign(getSign(request.getDate())).build();
            System.out.println("Your chinese sign is : " + response.getSign());

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());

        }
    }

}
