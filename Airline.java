import java.io.Serializable;
import java.util.ArrayList;

/**
 * A handler for requests made to a countdown server.
 *
 * @author Sriram Anasuri sanasuri
 * @version 11/11/19 1.0
 */
public interface Airline extends Serializable {
    String getName();

    String getDescription();

    void addFlight(Flight flight);

    void deleteFlight(Flight flight);
}