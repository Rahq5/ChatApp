package ChatApp;

import java.util.*;
import java.net.*;
import java.io.*;


public class client {

    
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    

    public client (Socket socket , String username){
        try{

/*
    for what's below f
    "socket.getInputStream()" : establish the network connection for inputting raw bytes
    "new InputStreamReader": a converter from raw bytes to char 
    "new BufferedReader": wraps the inputstream class and adds the buffring functions

    buffering is to add the functionality to store data chunks temporarly to
    improve reading performance and reduce system calls 
 */
        this.socket=socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        this.username = username;     
                }catch(IOException e){
            closeEverything(socket , bufferedWriter,bufferedReader);
        }
        //server will send a response of acceptance or rejection of username

        /*if (serverResponse != null && serverResponse.startsWith("Username")) {
            System.out.println(serverResponse);
            closeEverything(socket, bufferedWriter, bufferedReader);
            return; // Exit the constructor if the username is taken
        }
            System.out.println("username is accepted , joining as: "+username);
        */
    }

    public void sendMessage(){
        try{
            bufferedWriter.write(username); //write the user name
            bufferedWriter.newLine();       //adds a newline
            bufferedWriter.flush();         // force the buffer to send all data saved before doing anything

            Scanner input = new Scanner(System.in);

            while(socket.isConnected()){
                String messageToSend = input.nextLine();
                bufferedWriter.write(username + ": "+messageToSend); 
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        }catch(IOException e){
            closeEverything(socket , bufferedWriter,bufferedReader);
        }
    }
    
    public void ListenToMessage(){
        new Thread(new Runnable() {

            @Override
            public void run(){
                String msgFromGroupChat;
                while(socket.isConnected()){
                    
                    try{

                        msgFromGroupChat = bufferedReader.readLine(); // sent from client 
                       
                        System.out.println(msgFromGroupChat);

                    }catch(IOException e){
                        closeEverything(socket , bufferedWriter,bufferedReader);
                        break;
                    }
                }
            }
        }).start(); // listenToMessage()  start for the thread
    }

    public void closeEverything(Socket socket , BufferedWriter bufferedWriter ,BufferedReader bufferedReader ){
         
        try{
            if(bufferedWriter != null){
                bufferedWriter.close(); //client can no longer read data from server
            }
            if(bufferedReader != null){
                bufferedReader.close(); //client can no longer send data to server
            }
            if(socket != null){ // terminate the connction between client and server
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace(); // if closing error occurs , it shows there's a problem preventing from shutting down 
        }
    }


    public void SetclientUsername(String username){
        this.username = username;
    }
    public String GetClientUsername(){
        return username;
    }

    

    public static void main(String[] args) throws IOException {
        Scanner inpScanner = new Scanner(System.in);

        System.out.println("enter your name for the group chat: ");
        String username = inpScanner.nextLine();
        
            Socket socket = new Socket ("localhost" , 1234);
            client Client = new client(socket, username);
        
            Client.ListenToMessage();
            Client.sendMessage(); 
            
        
    }
}
   

    
        



class memberinfo{
    //name 
    // role
    // creation dateeeeee
    //
}