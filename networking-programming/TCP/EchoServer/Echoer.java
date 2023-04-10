import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Echoer extends Thread {
    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println("A client connected");
            BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    String echoString = input.readLine();
                    System.out.println("Received client input: " + echoString);

                    if(echoString.equals("exit")){
                        break;
                    }

                    // try {
                    //     Thread.sleep(1500);
                    // } catch (Exception e) {
                    //     System.out.println("Thread interrupted");
                    // }

                    output.println("Echo from server: " + echoString);
                }
        } catch (IOException e) {
            System.out.println("Oops: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("An error occored while closing socket!");
            }
        }
    }

}
