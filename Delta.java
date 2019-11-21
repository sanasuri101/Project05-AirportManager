import java.io.File;
import java.util.ArrayList;

public class Delta implements Airline {


    private ArrayList<String> passengers;
    private File DeltaList;
    private Gate gate;

    public Delta() {
        this.passengers = new ArrayList<>();

    }

    public ArrayList<String> getPassengers() {
        return passengers;
    }

    public void addPassenger(Passenger passenger){
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
