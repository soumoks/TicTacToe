import javax.swing.*;
import java.io.IOException;

/**
 * Main app class that initialises the frame and players
 */
public class GameApp implements Constants {
    public static void main(String[] args) throws IOException {

//        Referee theRef;
//        Player xPlayer, oPlayer;
        Game theGame = new Game();

        //Initialise the Frame Object
        GameView theFrame = new GameView();
        GameController theController = new GameController(theFrame,theGame);

        //Set the visibility of the frame to True
        theFrame.setVisible(true);
        theFrame.pack();

//        theRef = new Referee();
//        theRef.setBoard(theGame.getTheBoard());
//
//        xPlayer = theGame.create_player (theFrame.getNameX(), LETTER_X, theGame.getTheBoard());
//        oPlayer = theGame.create_player (theFrame.getNameO(), LETTER_O, theGame.getTheBoard());

        //theRef.setoPlayer(oPlayer);
        //theRef.setxPlayer(xPlayer);

        //theGame.appointReferee(theRef);
    }
}
