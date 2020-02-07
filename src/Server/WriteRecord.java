package Server;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WriteRecord {
    private ObjectOutputStream objectOut = null;
    private Message theMessage;
    private Scanner stdin = null;
    private Scanner textFileIn = null;
    private Socket theClient = null;

    /**
     * Creates an blank MusicRecord object
     */
    public WriteRecord(Message theMessage,Socket theClient) {
        this.theMessage = theMessage;
        this.theClient = theClient;
        try{
            objectOut = new ObjectOutputStream(theClient.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Sends the Board object to the client.
     */
    public void sendObject() {
        try{
            objectOut.writeObject(theMessage);

                //Reset the buffer everytime an object is written.
            objectOut.reset();
        }catch (IOException e){
            System.out.println("Error writing object!");
        }catch (NoSuchElementException e){
            System.out.println("Invalid input!. Please try again!");
        }

    }
}
