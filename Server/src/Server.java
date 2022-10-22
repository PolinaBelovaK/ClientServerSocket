import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //Socket creation:
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Server started");

            while (true)
            try(
            //Waiting for a response from the user:
            Socket socket = serverSocket.accept();
            //Create a writer:
            BufferedWriter writer = new BufferedWriter
                    (new OutputStreamWriter
                            (socket.getOutputStream()));
            //Create a reader:
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(
                            socket.getInputStream()))
            ) {
                //Create new thread for waiting answer:
                new Thread(() -> {
                    String request = null;
                    try {
                        request = reader.readLine();
                    } catch (IOException e) { }
                    System.out.println("Request: " + request);
                    String response = (int) (Math.random() * 30 - 10) + "";
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) { }
                    System.out.println("Response :" + response);
                    try {
                        writer.newLine();
                    } catch (IOException e) { }
                    //Write to file from buffer:
                    try {
                        writer.flush();
                    } catch (IOException e) { }
                }).start();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
