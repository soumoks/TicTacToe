import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Actor implements Runnable,Constants{

    private PrintWriter socketOut;
    private BufferedReader socketIn;
    private Board theBoard;
    private char mark;

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
    public synchronized void run() {
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
                    socketOut.println(line);
                    System.out.println(line);
                    String [] temp = new String [3];
                    temp = line.split(",");
                    theBoard.addMark(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),mark);

                    //Send this response over to the clients. Since we have this method synchronised, the output is being sent over
                    //to only one client at any given point of time.
                    response = temp[0] + "," + temp[1] + "," + mark;
                    socketOut.println(response);
                    theBoard.display();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createPlayer(char mark){
        if(mark == LETTER_X){
            socketOut.println(LETTER_X);
        }
        else if(mark == LETTER_O){
            socketOut.println(LETTER_O);
        }
    }
}
