import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Server implements Constants {
    private Socket aSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private ServerSocket serverSocket;
    private ExecutorService pool;
    private Board theBoard;

    public Server(){
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

                socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
                socketOut = new PrintWriter(aSocket.getOutputStream(),true);
                Actor cap2 = new Actor(socketIn,socketOut,theBoard,LETTER_O);
                pool.execute(cap2);

                //pool.shutdown();
                /*
                https://stackoverflow.com/questions/20495414/thread-join-equivalent-in-executor
                Await termination of all threads before further compute.
                Requires import java.util.concurrent.TimeUnit;
                */
//                while (!pool.awaitTermination(24L, TimeUnit.HOURS)) {
//                    System.out.println("Not yet. Still waiting for termination");
//                }
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
