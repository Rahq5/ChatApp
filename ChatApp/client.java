package ChatApp;

import java.util.*;

import ChatApp.Server.ChatSYS;

import java.net.*;
import java.io.*;


public class client {

    private static ChatSYS currentroom; 
    private static Chatroom chatroom;
    private static member_info member;

    private ClientHandler ClientHand ; 
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    

    public client (Socket socket , String username){
        try{
/*
    for what's below 
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
        
    }

    public static void main(String[] args) throws IOException {
        Scanner inpScanner = new Scanner(System.in);

        System.out.println("enter your name for the group chat: ");
        String username = inpScanner.nextLine();

        while(true){ //name validation
            try {
                if(ChatSYS.IsNameValid(username)){
                    ChatSYS.signUser(username);
                    break;
                }else{
                    System.out.println("Error: try pick another username");
                    username=inpScanner.nextLine();
                }
            }catch(IOException e){
               e.printStackTrace();
            }
        }
        
            Socket socket = new Socket ("localhost" , 1234);
            client Client = new client(socket , username);
            member_info member = new member_info(username);
        
            Client.ListenToMessage();
            Client.sendMessage(); 

            System.out.println("""
        Commands: 

        /J  <roomname> : Join an exist room
        /C  <roomname> : Create a new room and be the leader of your own room
        /Re  <NewName> : rename the current room 
        /P  <username> : promote a user 
        /RM <username> : remove a user from room
        /Ro            : list current rooms
        /W             : list room members
        """);

    while(true){ //command picking
        try{
           String prompt;
            if(currentroom!=null){
                prompt="["+currentroom.getroomname()+"] <"+member.getname()+"> "; //[roomname] <membername>:
            }else{
                prompt="[Main]"+ "< "+member.getname()+"> "; //[main] <membername>
           }
           System.out.println(prompt);

           String msg = inpScanner.nextLine();
           if(msg.startsWith("/")){

                handlecommand(msg.split("\\s+", 2)); //this will seperate the msg into string arrays

           }else if(currentroom!=null){

                currentroom.broadcastMessage(msg , member);

           }else{
                System.out.println("you're not in a room. create a new room or join another");
            }
            } catch (IOException e){
                System.err.println("Error: "+ e.getMessage());
                System.out.println("entercant be: \n -empty contains spaces only \n -start/end with spaces)");
                
            }
        }   
        
    }

    public static void handlecommand(String[] command){
       
            if(command[0].toLowerCase().equals("/C")){
             //body code   
            }
            if(command[0].toLowerCase().equals("/J")){
                //body code   
            }
            if(command[0].toLowerCase().equals("/RE")){
                //body code   
            }
            if(command[0].toLowerCase().equals("/P")){
                //body code   
            }
            if(command[0].toLowerCase().equals("/RM")){
                //body code   
            }
            if(command[0].toLowerCase().equals("/W")){
                //body code   
            }
            if(command[0].toLowerCase().equals("/Ro")){
                //body code   
            }
            
        
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

    

   
}
   

class member_info{
    private final String name;
    private String role = "member";

    public member_info(String name){
        this.name = name;
    }

    public String getname(){
        return name;
    }
    public String getRole(){
        return role;
    }
    public void SetRole(String role){
        if(role != "member" || role != "leader"){
            System.out.println("cant assign role for client");
        }else{
            this.role = role ;
        }

    }

    public void receive(String msg){   //this where the client read the msg 
        System.out.println("  !  "+ msg);
    }
}