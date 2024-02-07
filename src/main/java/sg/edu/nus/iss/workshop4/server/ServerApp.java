package sg.edu.nus.iss.workshop4.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    
    public static void main(String[] args) {
        System.out.println("ServerApp.main()");
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            String serverPort = args[0];
            String cookieFile = args[1];
            System.out.println("" + serverPort + " " + cookieFile);
        
            // Create a server
            ServerSocket serverSocket = 
                    new ServerSocket(Integer.parseInt(serverPort));
            System.out.println("Cookie Server started on " +serverPort);
            while(true){
                System.out.println("Waiting for client...");
                // accept connections
                socket = serverSocket.accept();
                // get data client program as input in bytes
                is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                os = socket.getOutputStream();
                DataOutputStream dos =new DataOutputStream(os);
                while(true){
                    System.out.println("received command from client");
                    try{
                        String dataFromClient =  dis.readUTF();
                        if(dataFromClient.equals("get-cookie")){
                            String cookieName = Cookie.getRandomCookie(cookieFile);
                            dos.writeUTF("cookie-text_" + cookieName);
                        }else{
                            dos.writeUTF("Invalid command");
                        }
                    }catch(EOFException e){
                        System.out.println("Client disconnected");
                        socket.close();
                        break;
                    }
                    
                }

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }   
}
