import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {
    private Socket aSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private ServerSocket serverSocket;

    public Server(){
        try {
            //Server socket accepts the port as a parameter
            serverSocket = new ServerSocket(9090);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Function just prints whatever is present in the socket.
     */
    public void printInput(){
        String line = "";
        while(true){
            try{
                line = socketIn.readLine();
                if(line != null){
                    socketOut.println(line);

                    //Print whatever is in the socket to console.
                    System.out.println(line);
                }

            }catch (IOException e){
                e.printStackTrace();
                break;
            }
        }
    }

    public static void main(String [] args) throws IOException{
        Server myServer = new Server();
        try{
            //Accepting the connection
            myServer.aSocket = myServer.serverSocket.accept();
            System.out.println("Connection accepted by server");
            //This part will differ once we have multiple threads.
            myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
            myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(),true);

            myServer.aSocket = myServer.serverSocket.accept();
            System.out.println("Connection accepted by server");
            //This part will differ once we have multiple threads.
            myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
            myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(),true);

            myServer.printInput();

            myServer.socketIn.close();
            myServer.socketOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
