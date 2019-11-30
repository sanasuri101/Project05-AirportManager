import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;
import java.net.Socket;

public final class ReservationServer {

    private ServerSocket serverSocket;
    private Socket clientSocket;

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

            handlerThread = new Thread(new ClientHandler(clientSocket));

            handlerThread.start();

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
        ServerSocket server = null;

        //ReservationServer server;

       /* try {
            //server = new ReservationServer(new File("/Users/bhaviksardar/IdeaProjects/Project/reservations"));
        } catch (Exception e) {
            e.printStackTrace();

            return;
        } //end try catch*/

        try {
            server = new ServerSocket(3200);
            server.setReuseAddress(true);
            while (true){
                Socket client = server.accept();

                System.out.println("New Client Accepted " +client.getInetAddress().getHostAddress());

                ClientHandler clientsock = new ClientHandler(client);

                new Thread(clientsock).start();
            }
        } catch (Exception e) {
            e.printStackTrace();

            return;
        } //end try catch*/

        //server.serveClients();
    } //main

    private static class ClientHandler implements Runnable {
        Socket clientSocket;

        public ClientHandler(Socket clientSocket) throws NullPointerException {
            this.clientSocket = clientSocket;
        } //CensoringRequestHandler

        @Override
        public void run() {
            File f = new File("reservations.txt");
            try {
               // FileOutputStream fos = new FileOutputStream(f);
                FileOutputStream fos = new FileOutputStream(f);
                PrintWriter pw = new PrintWriter(fos);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                /*for (int i = 0; i < temp.size(); i++){
                    pw.write(temp.get(i));
                }*/

                String line = in.readLine();
                if (line != null){
                    //System.out.printf("From Client: ", line);
                    pw.write(line);
                    //writer.write(line);
                    writer.newLine();
                    writer.flush();
                }
                pw.close();
                writer.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } //run
    }
}
