import java.util.ArrayList;
import java.util.List;

/**
 * A handler for requests made to a countdown server.
 *
 * @author Sriram Anasuri sanasuri
 * @version 11/11/19 1.0
 */
public class Delta implements Airline {
    private static final String NAME = "Delta";
    private static final String DESCRIPTION =
            "Delta Airlines is proud to be one of the five premier Airlines at \n" +
                    "Purdue University.We are extremely offer exceptional services, with free\n" +
                    "limited WiFi for all customers.Passengers who use T-Mobile as a cell\n" +
                    "phone carrier get additional benefits.We are also happy to power outlets\n" +
                    "in each seat for passenger use.We hope you choose to fly Delta as your next Airline.";

    private List<Flight> flightList;

    public Delta() {

    }

    public Delta(List<Flight> flightList) {
        this.flightList = flightList;
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    @Override
    public void addFlight(Flight flight) {
        if (flightList == null) {
            flightList = new ArrayList<Flight>();
        }
        flightList.add(flight);
    }

    @Override
    public void deleteFlight(Flight flight) {
        if (flightList != null && flightList.size() > 0) {
            flightList.remove(flight);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String toString() {
        return "Delta [description=" + DESCRIPTION + ", flightList=" + flightList + "]";
    }
}