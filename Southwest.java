import java.util.ArrayList;
import java.util.List;

public class Southwest implements Airline{
	private static final String name = "Southwest";
	private static final String description = 
			"Southwest Airlines is proud to offer flights to Purdue University.\n"+
			"We are happy to offer free in flight wifi, as well as our amazing snacks.\n"+
			"In addition, we offer flights for much cheaper than other airlines,and offer\n"+
			"two free checked bags.We hope you choose Southwest for your next flight.\n";

	
	private List<Flight> flightList; 
	
	public Southwest() {
	}

	public Southwest(List<Flight> flightList) {
		this.flightList = flightList;
	}
	
	public List<Flight> getFlightList() {
		return flightList;
	}

	@Override
	public void addFlight(Flight flight)
	{
		if(flightList==null)
		{
			flightList = new ArrayList<Flight>();
		}
		flightList.add(flight);		
	}
	@Override
	public void deleteFlight(Flight flight)
	{
		if(flightList!=null && flightList.size()>0)
		{
			flightList.remove(flight);
		}		
	}
	@Override
	public String getName() {
		return name;
	}	
	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return "Southwest [description=" + description+", flightList="+flightList+"]";
	}	
}
