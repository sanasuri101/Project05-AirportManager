import java.io.*;
import java.util.ArrayList;

public class Alaska implements Airline {

    private ArrayList<String> passengers;
    private File AlaskaList;
    private Gate gate;

    public Alaska() {
        this.passengers = new ArrayList<>();
        gate = new Gate();
    }

    public ArrayList<String> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger.toString());
    }

    public String getGatetoString(){
        return gate.toString();
    }

    /*public  void writeFile(String filename) {
        //To Do
        AlaskaList = new File(filename);
        try {
            FileOutputStream fos = new FileOutputStream(AlaskaList);
            PrintWriter pw = new PrintWriter(fos);
            for (int i = 0; i < passengers.size(); i++) {
                pw.write(passengers.get(i));
                pw.write("\n");
            }
            pw.close();
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }


    }*/

}
