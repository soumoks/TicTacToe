/**
 * The type Smart player.
 */
public class SmartPlayer extends BlockingPlayer {
    /**
     * Instantiates a new Smart player.
     *
     * @param name the name
     * @param mark the mark
     */
    public SmartPlayer(String name, char mark) {
        super(name, mark);
    }

    @Override
    public void makeMove() {
        //Iterate through the board.
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                //Checks if a mark is already present in the position. If present, skip over this position.
                if (!super.getBoard().checkMark(row, col)){
                    continue;
                }
                //Calls the testForBlocking method on every position and adds mark to block the opponent from winning.
                if (testForBlocking(row,col)) {
                    super.getBoard().addMark(row, col, super.getMark());
                    super.getBoard().display();
                    return;
                //if he does not win then he becomes blocking player
                } else if(super.testForBlocking(row, col)){
                    super.getBoard().addMark(row, col, super.getMark());
                    super.getBoard().display();
                    return;
                }
            }
        }
        super.makeMove();
        //super.getBoard().display();
    }

    @Override
    public boolean testForBlocking(int row, int col) {
        //Adds a mark to check if he wins
        super.getBoard().addMark(row,col,super.getMark());
        //super.getBoard().display();
        //This check is required as the blocking player can either be an X or an O
        if(this.getMark() == 'X'){
            if(super.getBoard().xWins()){
                //If he wins, remove the mark and return true
                super.getBoard().removeMark(row,col);
                //super.getBoard().display();
                return true;
            }
            else{
                //If the he does not win, remove the previously added mark.
                super.getBoard().removeMark(row,col);
                //super.getBoard().display();
                return false;
            }
        }
        else if(this.getMark() == 'O'){
            if(super.getBoard().oWins()){
                //If he wins, remove the mark and return true
                super.getBoard().removeMark(row,col);
                //super.getBoard().display();
                return true;
            }
            else{
                //If he does not win, remove the previously added mark.
                super.getBoard().removeMark(row,col);
                //super.getBoard().display();
                return false;
            }
        }
        return false;
    }
}
