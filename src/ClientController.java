import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class that ties together the model and View
 */
public class ClientController implements Constants {
    private GameView theView;
    private Client aClient;

    /**
     * Controller class Constructer that sets the starting player as the X player with Name
     * @param v
     */
    public ClientController(GameView v,Client c){
        theView = v;
        theView.setPlayerName(theView.getNameX());
        //theView.setPlayerSymbol(LETTER_X);
        //theView.setMessageArea(theView.getNameX() + ", Please make your move");
        theView.addButtonListener(new TestButtonListener());
        aClient = c;
    }

    class TestButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for(int row = 0;row< 3;row++){
                for(int col = 0;col<3;col++){
                    JButton temp[][] = theView.getButton();
                    if(temp[row][col] == e.getSource()) {
                        System.out.println("Row:" + row);
                        System.out.println("Column:" + col);

                        //TODO
                        /*
                        This logic should be taken away from the client. Instead, we should set whatever is being sent by the
                        server in the GameView.
                        This would ensure that we don't set conflicting marks on different client screens.
                        Implementation of this method would be done as part of the Client class.

                         */
                        aClient.sendButtonPress(row,col);
//                        if(theView.getPlayerSymbol() == LETTER_X){
//                            aClient.sendButtonPress(row,col);
//                            temp[row][col].setText("x");
//                        }
//                        else if(theView.getPlayerSymbol() == LETTER_O){
//                            aClient.sendButtonPress(row,col);
//                            temp[row][col].setText("o");
//                        }

                    }
                }
            }
        }
    }

    /**
     * Listener inner class that is responsible for updating the board.
     */
//    class ButtonListener implements ActionListener{
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            if(!checkWinner()){
//                for (int row = 0; row < 3; row++) {
//                    for (int col = 0; col < 3; col++) {
//                        JButton temp[][] = theView.getButton();
//                        //System.out.println(theView.getButton());
//                        if(temp[row][col] == e.getSource()){
//                            System.out.println("Row:" + row);
//                            System.out.println("Column:" + col);
//                            if(theView.getPlayerSymbol() == LETTER_X){
//                                //If the player is X. Mark the board with x and also update the GUI
//                                if(theGame.getTheBoard().checkMark(row,col)){
//                                    theGame.getTheBoard().addMark(row,col,Constants.LETTER_X);
//                                    temp[row][col].setText("x");
//                                    theView.setPlayerName(theView.getNameO());
//                                    theView.setPlayerSymbol(LETTER_O);
//                                    theView.setMessageArea(theView.getNameO() + ", Please make your move");
//                                    theGame.getTheBoard().display();
//                                }
//                                else{
//                                    theView.setMessageArea("Invalid move, try again!");
//                                }
//                                //Check Winner after every move
//                                checkWinner();
//                                //Change the Player to O and wait for an action event.
//                            }
//                            else if(theView.getPlayerSymbol() == LETTER_O){
//
//                                //If the player is O. Mark the board with O and also update the GUI
//                                if(theGame.getTheBoard().checkMark(row,col)) {
//                                    theGame.getTheBoard().addMark(row, col, Constants.LETTER_O);
//                                    temp[row][col].setText("o");
//                                    theView.setPlayerSymbol(LETTER_X);
//                                    theView.setPlayerName(theView.getNameX());
//                                    theView.setMessageArea(theView.getNameX() + ", Please make your move");
//                                    theGame.getTheBoard().display();
//                                }
//                                else{
//                                    theView.setMessageArea("Invalid move, try again!");
//                                }
//                                //Check winner after every move
//                                checkWinner();
//                                //Change the player to X and wait for an action event.
//                            }
//                        }
//                    }
//                }
//            }
//        }

        /**
         * Checks winner based on the board details.
         * Returns True if any of the possible scenarios occur : XWins, OWins or Game ends in a Tie.
         * Otherwise returns false
         * @return
         */


//        public boolean checkWinner(){
//            Boolean gameEnd = false;
//            if(theGame.getTheBoard().xWins()){
//                gameEnd = true;
//                theView.setMessageArea("Game Over!, " + theView.getNameX() + " is the winner!");
//                System.out.println("Game Over!, " + theView.getNameX() + " is the winner!");
//                return gameEnd;
//            }
//            else if(theGame.getTheBoard().oWins()){
//                gameEnd = true;
//                theView.setMessageArea("Game Over!, " + theView.getNameO() + " is the winner!");
//                System.out.println("Game Over!, " + theView.getNameO() + " is the winner!");
//                return gameEnd;
//            }
//            else if(theGame.getTheBoard().isFull()){
//                gameEnd = true;
//                theView.setMessageArea("Game ends in Tie");
//                System.out.println("Game ends in Tie");
//                return gameEnd;
//            }
//            return gameEnd;
//        }
//    }
}
