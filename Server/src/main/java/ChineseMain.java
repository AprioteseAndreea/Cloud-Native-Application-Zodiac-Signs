import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.ChineseImpl;
import service.ZodiacImpl;

import java.io.IOException;

public class ChineseMain {

    public static void main(String[] args) {
        try {

            Server server = ServerBuilder.forPort(8997).addService(new ChineseImpl()).build();

            server.start();
            System.out.println("Server started at " + server.getPort());
            server.awaitTermination();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
        }
    }
}
