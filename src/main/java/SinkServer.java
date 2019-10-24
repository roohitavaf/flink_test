import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class implements java Socket server
 *
 * @author pankaj
 */
public class SinkServer {

    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9998;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        server = new ServerSocket(port);
        System.out.println("Waiting for the client request");
        //creating socket and waiting for client connection
        Socket socket = server.accept();
        System.out.println("Client accepted.");

        String message = null;
        while (!"exit".equals(message)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            message = br.readLine();
            System.out.println("Message Received: " + message);
        }
        socket.close();
        System.out.println("Shutting down Socket server!!");
        server.close();
    }

}