import javax.swing.*;
import java.io.*;

/**
 * The type Game.
 */
public class Game implements Constants {

	public Board getTheBoard() {
		return theBoard;
	}

	public Referee getTheRef() {
		return theRef;
	}

	private Board theBoard;
	private Referee theRef;

	/**
	 * creates a board for the game
	 */
	public Game( ) {
        theBoard  = new Board();

	}

	/**
	 * calls the referee method runTheGame
	 *
	 * @param r refers to the appointed referee for the game
	 * @throws IOException the io exception
	 */
	public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }


	/**
	 * Creates the specified type of player indicated by the user.
	 *
	 * @param name  player's name
	 * @param mark  player's mark (X or O)
	 * @param board refers to the game board
	 * @return a newly created player
	 */
	static public Player  create_player(String name, char mark, Board board){

		Player result = null;
		result = new HumanPlayer(name,mark);
		result.setBoard(board);
		return result;
	}
}
