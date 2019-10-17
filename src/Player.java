import java.util.Scanner;
public class Player implements Constants {
    private String name;
    private Board board;
    private Player opponent;
    private char mark;

    public Player(String name, char letter){
        this.name = name;
        this.mark = letter;
    }
    public Player(){};

    /**
     * This method calls makeMove() and checks if any of the possible scenarios occur.
     * If X wins the game, display the name of the winner and exit the program.
     * If O wins the game, display the name of the winner and exit the program.
     * If the game ends in a tie(markCount = 9) display that the game ends in a Tie and exit the game.
     *
     */
    public void play(){
        makeMove();
        if(this.board.xWins()){
            System.out.println("Game Over!, " + this.name + " is the winner!");
            System.exit(0);
        }
        else if(this.board.oWins()){
            System.out.println("Game Over!, " + this.name + " is the winner!");
            System.exit(0);
        }
        else if(this.board.isFull()){
            System.out.println("Game ends in Tie");
            System.exit(0);
        }
    }

    /**
     * Asks the player to make a move by entering the row and column.
     * Marks either an X or an O in the position indicated by the player by calling method addMark part of the Board class.
     * The add mark internally calls the checkmark method which results in the player entering the row and column in case of an invalid value.
     */
    public void makeMove(){
        Scanner in = new Scanner(System.in);
        //TODO
        // Add a check if the user has entered the input
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


    /**
     * Connects the other player to this player
     * @param opponent
     */
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    /**
     * Sets the board.
     * @param theBoard
     */
    public void setBoard(Board theBoard){
        this.board = theBoard;
    }


}
