#ChatApp 
this is a chat app java-based project that uses synchronized client server to communicate using java sockets

#Featuers:
- **real time messaging** : all clients joined can read\send messages instantly
- **local network level**: any pc connected to a LAN will be able to join as client, sign their names and start chatting

#How To Run?:
1- Requiers:
  - Java downloaded on your pc
        -To check: press WIN+R and type "CMD" then a CLI appears. type the prompt "Java --version". then you should see the version of your java (it's a way to verify             existant of java , version doesn't affect running)
    
2.2- Download all files 
    - Click "Code" ---> "Download ZIP file"

2.3- Extract ZIP folder 
    - Extract the ZIP folder you just downloaded to a directory of your choice
    - It's better to memorize the directory path for the next steps

2.4- Open Command Prompt
    - Press WIN+R, type "cmd" and press Enter
    - Navigate to the directory where you extracted the files
    2.4.1- Use "cd" to enter directory (e.g., "cd C:\Users\YourName\Downloads\ChatApp")
    - Use "dir" to view all folders and files at your current location

2.5- Compile and Run the Server First
    - Type "javac Server.java" to compile the server file
    - Type "java Server" to run the server
    - Important: The server must be running before any clients can connect

2.6- Open Additional Command Prompts for Clients
    - Open two more Command Prompt windows (you'll need 3 total - 1 for server, 2 for clients)
    - In each new Command Prompt window, navigate to the same directory as in step 2.4

2.7- Compile and Run Client(s)
    - In each client Command Prompt window, type "javac Client.java" to compile
    - Then type "java Client" to run
    - It will ask for your name, which means the client is running successfully

Note: In total, you should have 3 Command Prompt windows running:
    1. One running the Server
    2. One running Client 1
    3. One running Client 2

2.8-Start chatting , all messages will appear to you and other clients screens + you can add more than 2 clients 
