import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        
        // port number 5000
        // port number must be between 0 and 65535
        // some of them are reserved for specific purposes
        // 0 - 1023 are reserved for well-known ports
        try (ServerSocket serverSocket  = new ServerSocket(5000)) {
            // read from client
            while (true) {

                Socket socket = serverSocket.accept();
                Echoer echoer = new Echoer(socket);
                echoer.start();
            }

        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}