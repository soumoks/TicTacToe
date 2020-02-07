package Client;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private Board theBoard;
    private char mark;
    private String theMessage;

    public Message(Board theBoard,String theMessage,char mark){
        this.theBoard = theBoard;
        this.theMessage = theMessage;
        this.mark = mark;
    }

    public char getMark() {
        return mark;
    }

    public void setMark(char mark) {
        this.mark = mark;
    }

    public Board getTheBoard() {
        return theBoard;
    }

    public void setTheBoard(Board theBoard) {
        this.theBoard = theBoard;
    }

    public String getTheMessage() {
        return theMessage;
    }

    public void setTheMessage(String theMessage) {
        this.theMessage = theMessage;
    }
}
