package Server;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Actor implements Runnable,Constants{

    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private Board theBoard;
    private char mark;
    private WriteRecord writeRecord;

    /**
     * Constructor
     */
    public Actor(BufferedReader in, PrintWriter out, Board theBoard,char mark){
        socketIn = in;
        socketOut = out;
        this.theBoard = theBoard;
        this.mark = mark;
        createPlayer(mark);
    }

    @Override
    public void run() {
        String line = "";
        String response = "";
        while(true){
            try {
                line = socketIn.readLine();
                if(line.equals("QUIT")){
                    line = "Good Bye!";
                    socketOut.println(line);
                    break;
                }
                else{
                    //line = line.toUpperCase();
                    //socketOut.println(line);
                    System.out.println(line);
                    String [] temp = new String [3];
                    temp = line.split(",");
                    theBoard.addMark(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),mark);

                    //Send this response over to the clients. Since we have this method synchronised, the output is being sent over
                    //to only one client at any given point of time.

                    // This also creates the issue of the clients not sharing the board.
                    //The board is being updated on the server but since the board is individually updated on the client side, the two clients are
                    // are seeing separate boards.

                    //Possible solution could be to serialise the board object. Send it to the client after every move and update
                    //the board on both the clients.

                    response = temp[0] + "," + temp[1] + "," + mark;
                    socketOut.println(new Message(theBoard,response,mark));

                    theBoard.display();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This creates the player and sets up the GUI with client X and Y
     * @param mark
     */
    public void createPlayer(char mark){
        if(mark == LETTER_X){
            socketOut.println(new Message(theBoard,"Setting player",mark));
        }
        else if(mark == LETTER_O){
            socketOut.println(new Message(theBoard,"Setting player",mark));
        }
    }
}
