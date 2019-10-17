
import java.io.*;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 


public class Game implements Constants {

	private Board theBoard;
	private Referee theRef;

	/**
	 * Composition relationship with Board.
	 * Constructing a new Board object within the Game Constructor
	 */
    public Game( ) {
        theBoard  = new Board();
	}

	/**
	 * Appoints a referee r which is assigned to the Member variable and we track this referee throughtout
	 * lifecycle of an individual game.
	 * @param r
	 * @throws IOException
	 */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        System.out.println("Referee started the game");
    	theRef.runTheGame();
    }

	/**
	 * Game is the class with the main method. We create objects of the players in this main method.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Referee theRef;
		Player xPlayer, oPlayer;
		BufferedReader stdin;
		//Creates a new object of the Game which in turn creates a Board object as defined in the constructor
		Game theGame = new Game();
		stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nPlease enter the name of the \'X\' player: ");
		String name= stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		xPlayer = new Player(name, LETTER_X);
		xPlayer.setBoard(theGame.theBoard);
		
		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}
		
		oPlayer = new Player(name, LETTER_O);
		oPlayer.setBoard(theGame.theBoard);
		
		theRef = new Referee();
		theRef.setBoard(theGame.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
        
        theGame.appointReferee(theRef);
	}
	

}
