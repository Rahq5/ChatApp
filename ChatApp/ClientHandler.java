package ChatApp;

import java.util.*;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {

    //attributes --------------
    public static HashMap<String , member_info> ClientHandleerss = new HashMap<>();
    

    private Socket socket;
    
    private member_info member;

    private BufferedReader bufferedreader;
    // will be used to read data
    
    private BufferedWriter bufferedWriter;
    //will be used to write and send data

    private String clientUsername;
    //here's your username

    public ClientHandler(Socket socket){
        try{
            this.socket=socket; //client object is passed here 

            this.bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //BufferedWriter object used to get output to the client (reciver)
           
            this.bufferedreader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //BufferedReadr object used to get input to the client (sender)

            this.clientUsername = bufferedreader.readLine();//reader class will read name of client
            ClientHandleerss.put(clientUsername , member); //the client will be added in the array

            //u may pass the socket to the Client_info class too 

            broadcastMessage("Server: "+clientUsername+" has enterd the chat!");
        }catch(IOException e){closeEverything(socket , bufferedWriter , bufferedreader);}
    }
    
 
    @Override
    public void run() {
        String messageFromClient; 

        while (socket.isConnected()){ 
            try{
                messageFromClient = bufferedreader.readLine();

                /*if(messageFromClient == null) {
                    // If client disconnects, messageFromClient will be null
                    closeEverything(socket, bufferedWriter, bufferedreader);
                    break;
                }*/
                broadcastMessage(messageFromClient);
            } catch(IOException e) {
                closeEverything(socket, bufferedWriter, bufferedreader);
                break;
            }
        }
    }
    
   /* public void broadcastMessage(String messageToSend){
        
        for(ClientHandler clientHandler : ClientHandleerss){ 
            try{
                //send message to everyone 
                //note: message will be also sent to the sender 
                //why?: in case of two clients have the same name , the server will treat them as one client
                //because it sends depending by name
                if(!clientHandler.clientUsername.equals(clientUsername)){ 
                    clientHandler.bufferedWriter.write(messageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush(); 
                }
            }catch(IOException e){
                closeEverything(socket,bufferedWriter,bufferedreader);
            }
        }
    }*/ 

    /*public void removeClienthandler(){ //just remove from the static array 
        ClientHandleerss.remove(this);
        //broadcastMessage("Server: "+ clientUsername +" has left from the chat !\n or a client trying to join with an invalid name");
    }*/

    
    /*public void closeEverything(Socket socket , BufferedWriter bufferedWriter ,BufferedReader bufferedReader ){
        removeClienthandler(); // remove the clienthanlder from the array 
        try{
            if(bufferedWriter != null){
                bufferedWriter.close(); //client can no longer send data to server 
            }
            if(bufferedreader != null){
                bufferedreader.close();//client can no longer read data from server
            }
            if(socket != null){ // terminate the connction between client and server
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace(); // if closing error occurs , it shows there's a problem preventing from shutting down 
        }
    }*/

    

    
}
