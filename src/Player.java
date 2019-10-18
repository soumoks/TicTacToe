import java.util.Scanner;

/**
 * The type Player.
 */
public abstract class Player implements Constants {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Board.
     */
    protected Board board;
    /**
     * The Opponent.
     */
    protected Player opponent;
    /**
     * The Mark.
     */
    protected char mark;

    /**
     * Gets opponent.
     *
     * @return the opponent
     */
    public Player getOpponent() {
        return opponent;
    }

    /**
     * Gets mark.
     *
     * @return the mark
     */
    public char getMark() {
        return mark;
    }

    /**
     * Instantiates a new Player.
     *
     * @param name   the name
     * @param letter the letter
     */
    protected Player(String name, char letter){
        this.name = name;
        this.mark = letter;
    }

    /**
     * Instantiates a new Player.
     */
    public Player(){};

    /**
     * Gets board.
     *
     * @return the board
     */
    protected Board getBoard() {
        return board;
    }

    /**
     * This method calls makeMove() and checks if any of the possible scenarios occur.
     * If X wins the game, display the name of the winner and exit the program.
     * If O wins the game, display the name of the winner and exit the program.
     * If the game ends in a tie(markCount = 9) display that the game ends in a Tie and exit the game.
     */
    protected void play() {
        while (true) {
            makeMove();
            checkWinner();
            opponent.makeMove();
            checkWinner();
        }
    }

    /**
     * Check winner.
     */
    public void checkWinner(){
        if(this.board.xWins()){
            System.out.println("Game Over!, " + this.name + " is the winner!");
            System.exit(0);
        }
        else if(this.board.oWins()){
            System.out.println("Game Over!, " + this.opponent.name + " is the winner!");
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
//This is an abstract method as the child classes implement their own methods.
    protected abstract void makeMove();

    /**
     * Connects the other player to this player
     *
     * @param opponent the opponent
     */
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    /**
     * Sets the board.
     *
     * @param theBoard the the board
     */
    public void setBoard(Board theBoard){
        this.board = theBoard;
    }
}
