/**
 * The type Blocking player.
 */
public class BlockingPlayer extends RandomPlayer {
    /**
     * Instantiates a new Blocking player.
     *
     * @param name the name
     * @param mark the mark
     */
    public BlockingPlayer(String name, char mark) {
        super(name, mark);
    }

    public char getMark() {
        return super.getMark();
    }

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
                }
            }
        }
        //If the player is not able to block opponent, become a random player.
        
        super.makeMove();
        //super.getBoard().display();
    }

    /**
     * Returns true if the opponent wins
     *
     * @param row the row
     * @param col the col
     * @return boolean
     */
    public boolean testForBlocking(int row,int col){
        //Adds a mark of the opponent to check if the opponent wins
        super.getBoard().addMark(row,col,super.getOpponent().getMark());
        //super.getBoard().display();

        //This check is required as the blocking player can either be an X or an O
        if(this.getMark() == 'X'){
            if(super.getBoard().oWins()){
                //If the opponent wins, remove the mark and return true
                super.getBoard().removeMark(row,col);
                //super.getBoard().display();
                return true;
            }
            else{
                //If the opponent does not win, remove the previously added mark.
                super.getBoard().removeMark(row,col);
                //super.getBoard().display();
                return false;
            }
        }
        else if(this.getMark() == 'O'){
            if(super.getBoard().xWins()){
                //If the opponent wins, remove the mark and return true
                super.getBoard().removeMark(row,col);
                //super.getBoard().display();
                return true;
            }
            else{
                //If the opponent does not win, remove the previously added mark.
                super.getBoard().removeMark(row,col);
                //super.getBoard().display();
                return false;
            }
        }
        return false;
    }
}
