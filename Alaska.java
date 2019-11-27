import java.util.ArrayList;
import java.util.List;

public class Alaska implements Airline {

	private static final String name = "Alaska";
	private static final String description = 
			"Alaska Airlines is proud to serve the strong and knowledgeable Boilermakers\n"+
			"from Purdue University.We primarily fly westward, and often have stops in \n"+
			"Alaska and California.We have first class amenities, even in coach class.\n"+
			"We also have comfortable seats, and free WiFi.\n"+
			"We hope you choose Alaska Airlines for your next itinerary!";	

	private List<Flight> flightList; 
	
	public Alaska() {
	}

	public Alaska(List<Flight> flightList) {
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
		return "Alaska [description=" + description+", flightList="+flightList+"]";
	}	
}
