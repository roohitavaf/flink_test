import java.io.*;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class implements java Socket server
 * @author pankaj
 *
 */
public class SourceServer {

    //static ServerSocket variable
    private static ServerSocket server;
    //socket server port on which it will listen
    private static int port = 9999;

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        //create the socket server object
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
//        while(true){
            System.out.println("Waiting for the client request");
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
//            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
//            //convert ObjectInputStream object to String
//            String message = (String) ois.readObject();
//            System.out.println("Message Received: " + message);
            //create ObjectOutputStream object
        PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        Scanner sc = new Scanner(System.in);
        String line = null;

        while (!"exit".equals(line)) {
            line = sc.nextLine();
            serverPrintOut.println(line);
        }
        //write object to Socket

            //close resources
//            ois.close();
        serverPrintOut.close();
            socket.close();
            //terminate the server if client sends exit request
//            if(message.equalsIgnoreCase("exit")) break;
//        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }

}