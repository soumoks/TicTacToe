import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket aSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private GameView theView;

    public Client(String serverName,int portNumber){
        theView = new GameView();
        try{
            aSocket = new Socket(serverName,portNumber);



            //Socket input Stream
            socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));

            //Socket output Stream
            socketOut = new PrintWriter(aSocket.getOutputStream(),true);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Function to send over button press row and columns over to the server. This function must be called on every button press.
     */
    public void sendButtonPress(int row, int col){
        String response = "";

        //Convert row and columns into a single string
        String line = "";
        line = row + "," + col;

        //int [] buttonPress = new int[2];
//        if(row != 0 && col != 0){
//            buttonPress[0] = row;
//            buttonPress[1] = col;
//        }

        /*
        Check if this line is required or can be replaced with something beter
         */
        if(line != null && !line.isEmpty()){
            try{
                System.out.println("Sending row,col values to server..");
                socketOut.println(line);
                response = socketIn.readLine();
                System.out.println("Server response is: " + response);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }

}
