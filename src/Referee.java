public class Referee {
    private Player xPlayer;
    private Player oPlayer;
    private Board board;

    /**
     * Referee starts the game, displays the board and
     * calls the play method for the X-Player who is always the first player and subsequently calls the play method of the O player in an infinite loop
     */
    public void runTheGame(){
        board.display();
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);
        while(true){
            xPlayer.play();
            oPlayer.play();
        }

    }

    /**
     * Sets the board
     * @param board
     */
    public void setBoard(Board board){
        this.board = board;

    }

    /**
     * Sets the O player
     * @param oPlayer
     */
    public void setoPlayer(Player oPlayer){
        this.oPlayer = oPlayer;
    }

    /**
     * Sets the X player
     * @param xPlayer
     */
    public void setxPlayer(Player xPlayer){
        this.xPlayer = xPlayer;
    }

}
