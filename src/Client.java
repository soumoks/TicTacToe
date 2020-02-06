import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Constants{
    private Socket aSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private GameView theView;

    public Client(String serverName,int portNumber,GameView theView){
        this.theView = theView;
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

        /*
        Check if this line is required or can be replaced with something beter
         */
        if(line != null && !line.isEmpty()){
            System.out.println("Sending row,col values to server..");
            socketOut.println(line);
                //response = socketIn.readLine();
                //System.out.println("Server response is: " + response);

        }

    }

    public void getServerResponse(){
        String response = "";
        try{
            while(true){
                response = socketIn.readLine();
                if(response != null){
                    System.out.println("Server response is: "  + response);
                }
                setPlayer(response);
                validateServerResponse(response);
                setMark(response);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void validateServerResponse(String response){
        //If valid move
        if(response.equals("V")){
            theView.setMessageArea("Valid Move");
        }
        // If X player wins
        else if(response.equals("XWINS")){
            theView.setMessageArea("X Wins");
        }

        // If O Player wins
        else if(response.equals("OWINS")){
            theView.setMessageArea("O WINS");
        }
    }

    public void setPlayer(String response){
        //Checking if the string actually consists of the player Mark
        if(response.charAt(0) == LETTER_X || response.charAt(0) == LETTER_O){
            if(response.charAt(0) == LETTER_X){
                theView.setPlayerSymbol(LETTER_X);
            }
            else if(response.charAt(0) == LETTER_O){
                theView.setPlayerSymbol(LETTER_O);
            }
        }
    }

    public void setMark(String response){
        // We are assuming the response here consists of the row and column of the mark.
        //Parse the response to separate the row and column
        //Place the mark at the correct position on the board.
        int row,col = 0;
        String mark;
        String [] temp = new String [3];

        //Checking to see if the server response is anything other than the string containing row,col and mark
        if(response != null && response.charAt(0) != LETTER_X && response.charAt(0) != LETTER_O){
            temp = response.split(",");

            //Add an array size check here
            row = Integer.parseInt(temp[0]);
            col = Integer.parseInt(temp[1]);
            mark = temp[2];
            JButton button[][] = theView.getButton();

            if((row >= 0 && row <= 2) && (col >=0 && col <= 2)){

                //Changing mark to lower case letters so that they are visibile on the GUI
                if(mark.charAt(0) == LETTER_X){
                    button[row][col].setText("x");
                }
                else{
                    System.out.println("Mark is not equal to X");
                }
                if(mark.charAt(0) == LETTER_O){
                    button[row][col].setText("o");
                }
                else{
                    System.out.println("Mark is not equal to O");
                }
            }
            else{
                System.out.println("Row and column not in limits!");
            }
        }
    }

}
