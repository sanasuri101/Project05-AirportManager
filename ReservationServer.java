import java.net.ServerSocket;
import java.util.Set;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.HashSet;
import java.io.FileReader;
import java.util.Objects;
import java.net.Socket;

public final class ReservationServer {

    private ServerSocket serverSocket;

    public ReservationServer(File file) throws NullPointerException, IOException {
        BufferedReader reader;
        String line;

        Objects.requireNonNull(file, "the specified file is null");

        this.serverSocket = new ServerSocket(0);

        reader = new BufferedReader(new FileReader(file));

        line = reader.readLine();

        while (line != null) {

            line = reader.readLine();
        } //end while

        reader.close();
    } //CensoringServer

    public void serveClients() {
        Socket clientSocket;
        Thread handlerThread;
        int clientCount = 0;

        System.out.printf("<Now serving clients on port %d...>%n", this.serverSocket.getLocalPort());

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();

                return;
            } //end try catch

            //handlerThread = new Thread(new CensoringRequestHandler(clientSocket, this.badWords));

            //handlerThread.start();

            System.out.printf("<Client %d connected...>%n", clientCount);

            clientCount++;
        } //end while
    } //serveClients


    @Override
    public int hashCode() {
        int result = 23;

        result = 31 * result + Objects.hashCode(this.serverSocket);

        return result;
    } //hashCode

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else if (object instanceof ReservationServer) {
            boolean equal;

            equal = Objects.equals(this.serverSocket, ((ReservationServer) object).serverSocket);

            return equal;
        } else {
            return false;
        } //end if
    } //equals

    @Override
    public String toString() {
        String format = "ReservationServer[%s]";

        return String.format(format, this.serverSocket);
    } //toString

    public static void main(String[] args) {
        ReservationServer server;

        try {
            server = new ReservationServer(new File("/Users/bhaviksardar/IdeaProjects/Project/project5/reservations.txt"));
        } catch (Exception e) {
            e.printStackTrace();

            return;
        } //end try catch

        server.serveClients();
    } //main
}