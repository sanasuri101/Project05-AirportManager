import java.io.Serializable;
import java.util.ArrayList;

public interface Airline extends Serializable{
	String getName();
	String getDescription();
	public void addFlight(Flight flight);
	public void deleteFlight(Flight flight);
}
