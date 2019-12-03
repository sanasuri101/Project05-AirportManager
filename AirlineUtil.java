import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * A handler for requests made to a countdown server.
 *
 * @author Sriram Anasuri sanasuri
 * @version 11/11/19 1.0
 */
public class AirlineUtil {

    private static String fileName = "Reservations.txt";

    public static void main(String[] args) {
        /*
        Passenger p = new Passenger("Sriram","Anasuri",18);
        Passenger p1 = null;
        try {
        	serialize(p);
        	p1 = (Passenger)deSerialize();
        	System.out.println("After deSerialize passenger="+p1);
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }*/
        String str = "*he-llo-";
        System.out.println(isStringOnlyAlphabetAndDash(str));
    }

    /**
     * Determines whether or not the specified {@code String} is parsable as a non-negative {@code int}.
     */
    private static boolean isParsable(String string) {
        return string.chars()
                .mapToObj(Character::isDigit)
                .reduce(Boolean::logicalAnd)
                .orElse(Boolean.FALSE);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    //This method used to display input dialog based on input request type for host and port
    public static DataObject showInputDialog(InputType inputType) {
        Integer choice = 0;
        DataObject dataObject = new DataObject(inputType);
        if (inputType != null && inputType.equals(InputType.HOST)) {
            while (true) {
                String hostName = JOptionPane.showInputDialog(null,
                        "What is the host name you'd like to connect to?", "Hostname?",
                        JOptionPane.QUESTION_MESSAGE);

                System.out.println("Host:" + hostName);
                if (hostName == null) {
                    choice = JOptionPane.CANCEL_OPTION;
                    dataObject.setResult(choice);
                    return dataObject;
                } else if (hostName.equals("")) {
                    choice = JOptionPane.OK_OPTION;
                    dataObject.setResult(choice);
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid host name", "Hostname?", JOptionPane.ERROR_MESSAGE);
                } else {
                    dataObject.setResult(choice);
                    dataObject.setHostName(hostName);
                    break;
                }
            }
        } else if (inputType != null && inputType.equals(InputType.PORT)) {
            while (true) {
                String portString = JOptionPane.showInputDialog(null,
                        "What is the port name you'd like to connect to?", "Port?",
                        JOptionPane.QUESTION_MESSAGE);
                System.out.println("Port:" + portString);

                if (portString == null) {
                    choice = JOptionPane.CANCEL_OPTION;
                    dataObject.setResult(choice);
                    return dataObject;
                } else if (portString.equals("")) {
                    choice = JOptionPane.OK_OPTION;
                    dataObject.setResult(choice);
                    JOptionPane.showMessageDialog(null,
                            "Please enter a valid port name", "Port?", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        if (isParsable(portString)) {
                            int port = Integer.parseInt(portString);
                            dataObject.setResult(choice);
                            dataObject.setPort(port);
                            break;
                        } else
                            JOptionPane.showMessageDialog(null,
                                    "Please enter a valid port name",
                                    "Port?", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                "Please enter a valid port name", "Port?", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
        return dataObject;
    }

    //This method is used to check if the string contains alphabets and dash only.
    public static boolean isStringOnlyAlphabetAndDash(String s) {
        return (s != null) && s.matches("^[a-zA-Z-]*$");
    }

    //To return the list of airline objects
    public static ArrayList<Airline> getAirlineList() {
        ArrayList<Airline> airLineList = new ArrayList<Airline>();
        airLineList.add(new Alaska());
        airLineList.add(new Delta());
        airLineList.add(new Southwest());
        return airLineList;
    }

    //This method is used to read the objects from Reservations.txt file
    public static Object deSerialize() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return object;
    }

    //This method is used to write the objects to Reservations.txt file
    public static synchronized void serialize(Object object) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    //Assumptions
    //Flight 18000 belongs to Delta, 19000 belongs to Alaska and 15000 belongs to Southwest for simplicity.
    //The capacity of flight is assumed to be 100 by default for each airline and can be changed.

    public static Flight getFlight(String airline) {
        Flight flight = null;

        if (!isNullOrEmpty(airline) && airline.equals("Delta")) {
            flight = new Flight("18000", 30, "Delta");
        } else if (!isNullOrEmpty(airline) && airline.equals("Alaska")) {
            flight = new Flight("19000", 30, "Alaska");
        } else if (!isNullOrEmpty(airline) && airline.equals("Southwest")) {
            flight = new Flight("15000", 30, "Southwest");
        }

        return flight;
    }
}