import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public final class ReservationClient {

    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    } //isParsable

    public static void main(String[] args) {
        BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
        String hostname;
        String portString;
        int port;
        Socket socket;
        BufferedWriter socketWriter = null;
        BufferedReader socketReader = null;
        final String TITLE = "Purdue University Reservation Management System";

        try {
            hostname = (String) JOptionPane.showInputDialog(null, "Enter the sever's hostname: ", "HOSTNAME", JOptionPane.QUESTION_MESSAGE);

            if (hostname == null) {
                System.out.println();

                System.out.println("Goodbye!");
            } else {

                portString = (String) JOptionPane.showInputDialog(null, "Enter the sever's port: ", "HOSTNAME", JOptionPane.QUESTION_MESSAGE);

                if (portString == null) {
                    System.out.println();

                    System.out.println("Goodbye!");
                } else if (!isParsable(portString)) {
                    System.out.println();

                    System.out.println("The specified port must be a non-negative integer! Goodbye!");
                } else {
                    port = Integer.parseInt(portString);

                    socket = new Socket(hostname, port);

                    socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String[] button = {"Exit", "Book a Flight"};
                    int loop = JOptionPane.showOptionDialog(null, "Welcome to the Purdue University Airline Reservation Management System!", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, button, button[0]);
                    if (loop == 1){
                        String[] button2 = {"Exit", "Yes, I want to book a flight."};
                        int confirm = JOptionPane.showOptionDialog(null, "Do you want to book a flight today?", TITLE, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, button2, button2[0]);
                        if (confirm == 1){
                            String[] flights = {"Delta", "Southwest", "Alaska"};
                            String flight = (String) JOptionPane.showInputDialog(null, "Choose a flight from the drop down menu.", TITLE, JOptionPane.QUESTION_MESSAGE, null, flights, flights[0]);
                            JFrame frame = new JFrame();
                            JPanel panel = new JPanel();
                            JButton jbutton = new JButton();
                            JTextField jt;
                            if (flight.equals("Delta")){
                                Delta airline = new Delta();
                                frame.setSize(300,200);
                                frame.setVisible(true);
                                jt = new JTextField("Name", 6);
                                jt.setVisible(true);
                                panel.add(jt);
                            }
                            else if (flight.equals("Alaska")){
                                Alaska airline = new Alaska();
                            }
                            else if (flight.equals("Southwest")){
                                Southwest airline = new Southwest();
                            }
                            //Passenger passenger = new Passenger("Bhavik", "Sardar", "18", airline);
                        }
                    }

                } //end if
            } //end if
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                userInputReader.close();

                if (socketWriter != null) {
                    socketWriter.close();
                } //end if

                if (socketReader != null) {
                    socketReader.close();
                } //end if
            } catch (IOException e) {
                e.printStackTrace();
            } //end try catch
        } //end try catch finally
    } //main
}