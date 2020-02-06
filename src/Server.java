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
    private final Lock gameLock; // to lock game for synchronization
    private final Condition otherPlayerConnected; // to wait for other player
    private final Condition otherPlayerTurn; // to wait for other player's turn

    public Server(){
        gameLock = new ReentrantLock(); // create lock for game
        // condition variable for both players being connected
        otherPlayerConnected = gameLock.newCondition();

        // condition variable for the other player's turn
        otherPlayerTurn = gameLock.newCondition();
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
                System.out.println("Connection accepted by server");
                socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                socketOut = new PrintWriter(aSocket.getOutputStream(),true);

                Actor cap = new Actor(socketIn,socketOut,theBoard,LETTER_X);
                pool.execute(cap);

                /*
                Added a new server Socket accept. Need to check if this is required or not. After adding this, we are getting the first player as O
                and the second player as X
                 */
                aSocket = serverSocket.accept();
                socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                socketOut = new PrintWriter(aSocket.getOutputStream(),true);
                Actor cap2 = new Actor(socketIn,socketOut,theBoard,LETTER_O);
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
        System.out.println("Server is running..");
        myServer.runServer();
//        try{
//            //Accepting the connection
//            myServer.aSocket = myServer.serverSocket.accept();
//            System.out.println("Connection accepted by server");
//            //This part will differ once we have multiple threads.
//            myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
//            myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(),true);
//
//            myServer.aSocket = myServer.serverSocket.accept();
//            System.out.println("Connection accepted by server");
//            //This part will differ once we have multiple threads.
//            myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
//            myServer.socketOut = new PrintWriter(myServer.aSocket.getOutputStream(),true);
//
//            myServer.printInput();
//
//            myServer.socketIn.close();
//            myServer.socketOut.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }

    }
}
