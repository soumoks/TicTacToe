import java.util.Scanner;

/**
 * The type Human player.
 */
public class HumanPlayer extends Player {
    /**
     * Instantiates a new Human player.
     *
     * @param name   the name
     * @param letter the letter
     */
    public HumanPlayer(String name, char letter){
        super(name,letter);
    }

    @Override
    protected void makeMove() {
        Scanner in = new Scanner(System.in);
        //in.hasNext()
        System.out.println(this.name + ", What row should your next " + this.mark + " be placed in?");
        int row = row = in.nextInt();
        System.out.println(this.name + ", What column should your next " + this.mark + " be placed in?");
        int column = in.nextInt();
        if(board.checkMark(row,column)){
            board.addMark(row,column,this.mark);
        }
        else{
            System.out.println("Please enter a valid row and column");
            makeMove();
        }
        board.display();
    }
}