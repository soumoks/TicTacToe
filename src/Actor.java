import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Actor implements Runnable{

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
    }

    @Override
    public void run() {
        String line = "";
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
                    System.out.println("Split string is: " + temp);
                    theBoard.addMark(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),mark);
                    theBoard.display();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
