package ChatApp;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;


public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }

    public void startServer(){

        try{

            while(!serverSocket.isClosed()){  //while server is running
                Socket socket = serverSocket.accept(); // listining for incoming clients (block method)
                System.out.println("new client joined !"); // when client is connected it prints the massage

                ClientHandler clienthandler = new ClientHandler(socket); //this is defined in another file , not in the libraries
                
                //the clienthandler supposed to handle the communications between server and client

                Thread thread = new Thread(clienthandler); // without threads , u cant add more clients to a server 
                thread.start();
            }
        }catch(IOException e)
        {
            //some code needed here in case of I\O errors
        }
    }

    public void CloseServerSocket(){
        try {
            if (serverSocket != null){ // if the serversocket isn't null , it will shutdown
                serverSocket.close();
            }
        }catch(IOException e){ 
            // if serversocket gives a null , it may give an NullPointerException so to prevent this 
            //IOException will deal with it 
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234); 
        //this will declare a new server socket with this port
        
        Server server = new Server(serverSocket);
        server.startServer();
    }
}



/*public static void main(String[] args) throws IOException{
        ServerSocket ss = new ServerSocket(4999);
        Socket s = ss.accept();

        System.out.println("client connected");

        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);

        String str = bf.readLine();
        System.out.println("client: "+str);
    } */ 