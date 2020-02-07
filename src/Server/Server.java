package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server implements Constants {
    private Socket aSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private Board theBoard;
//    private final Lock gameLock; // to lock game for synchronization
//    private final Condition otherPlayerConnected; // to wait for other player
//    private final Condition otherPlayerTurn; // to wait for other player's turn
    private WriteRecord writeRecord;

    public Server(){
//
//        gameLock = new ReentrantLock(); // create lock for game
//        // condition variable for both players being connected
//        otherPlayerConnected = gameLock.newCondition();
//
//        // condition variable for the other player's turn
//        otherPlayerTurn = gameLock.newCondition();
        try {
            //Server socket accepts the port as a parameter
            serverSocket = new ServerSocket(9090);
            pool = Executors.newFixedThreadPool(10);
            theBoard = new Board();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void runServer(){
        try{
            while(true){
                aSocket = serverSocket.accept();

               // System.out.println("Connection accepted by server");
                socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                socketOut = new PrintWriter(aSocket.getOutputStream(),true);
                Actor cap = new Actor(socketIn,socketOut,theBoard,LETTER_X);

                writeRecord = new WriteRecord(new Message(theBoard,"Updated Board",LETTER_X),aSocket);
                writeRecord.sendObject();

                /*
                Added a new server Socket accept. Need to check if this is required or not. After adding this, we are getting the first player as O
                and the second player as X
                 */
                aSocket = serverSocket.accept();
                socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                socketOut = new PrintWriter(aSocket.getOutputStream(),true);
                Actor cap2 = new Actor(socketIn,socketOut,theBoard,LETTER_O);
                writeRecord = new WriteRecord(new Message(theBoard,"Updated Board",LETTER_O),aSocket);
                writeRecord.sendObject();

                pool.execute(cap);
                pool.execute(cap2);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        //Close the resources. This only happens when the server closes.
        try{
            socketIn.close();
            socketOut.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public static void main(String [] args) throws IOException{
        Server myServer = new Server();
     //   System.out.println("Server is running..");
        myServer.runServer();
    }
}
