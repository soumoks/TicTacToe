public class ClientApp {
    public static void main(String [] args){
        Client theClient = new Client("localhost",9090);
        GameView theView = new GameView();
        ClientController theController = new ClientController(theView,theClient);
        theView.setVisible(true);
        theView.pack();
        //Client object is initiated in ClientController class.

    }
}
