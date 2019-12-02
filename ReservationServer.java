import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The server can handle multiple clients simultaneously. It will track and record ticket sales by writing to reservations.txt. 
 * A sample reservations file can be found in the Starter Code folder. Your program will need to be able to create and write to 
 * a file in a similar format.Example: Client 1 and Client 2 are both connected to the server and booking a Southwest flight. 
 * If Client 1 books the last Southwest ticket, Client 2 should no longer be able to book it.
 */
public final class ReservationServer {
    /**
     * The server socket of this server.
     */
    private ServerSocket serverSocket;
    public static ReservationModel reservationModel = new ReservationModel();    

    public ReservationServer() throws IOException {
        this.serverSocket = new ServerSocket(0);
    } 

    /**
     * Serves the clients that connect to this server.
     */
    public void serveClients() {
        Socket clientSocket;
        ReservationServerClientHandler handler;
        Thread handlerThread;
        int connectionCount = 0;

        System.out.printf("<Now serving clients on port %d...>%n", this.serverSocket.getLocalPort());

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();

                break;
            } //end try catch

            handler = new ReservationServerClientHandler(clientSocket);

            handlerThread = new Thread(handler);

            handlerThread.start();

            System.out.printf("<Client %d connected...>%n", connectionCount);

            connectionCount++;
        } //end while
    } //serveClients
    
    public static void main(String[] args) {
    	ReservationServer server;

        try {
            server = new ReservationServer();
            try {
                File file = new File("Reservations.txt");
                if(file.exists() && file.canRead())
                	server.reservationModel = (ReservationModel)AirlineUtil.deSerialize();
            }catch(IOException | ClassNotFoundException e) {
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } //end try catch

        server.serveClients();
    } //main
}

