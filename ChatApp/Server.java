package ChatApp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;


public class Server {
    private ServerSocket serverSocket;
    static final HashMap<String , Chatroom> rooms = new HashMap<>();
    static final ArrayList<String> usedUsernames = new ArrayList();

    public Server(ServerSocket serverSocket){
        this.serverSocket=serverSocket;
    }
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234); 
        //this will declare a new server socket with this port
        
        Server server = new Server(serverSocket);
        server.startServer();
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

   


class ChatSYS{
    
    public Chatroom createRoom(String RoomName , member_info member){

       //follow with some naming validiation
       
        Chatroom chatroom = new Chatroom(RoomName, member);
        rooms.put(RoomName , chatroom);
        return chatroom;
    }

    //find room
    //list room
    //delete room
    //username avilable
    //regester name
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