import io.grpc.Server;
import io.grpc.ServerBuilder;
import service.EuropeanImpl;
import service.ZodiacImpl;

import java.io.IOException;

public class EuropeanMain {

    public static void main(String[] args) {
        try {

            Server server = ServerBuilder.forPort(8998).addService(new EuropeanImpl()).build();

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
