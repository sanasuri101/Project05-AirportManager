import java.util.ArrayList;
import java.util.List;

public class Flight {
	private String flightNumber;
	private List<Passenger> passengerList;
	private int capacity;
	private boolean isFull;
	private int passengerCount;
	private String airLineName;

	public Flight(String flightNumber, int capacity,String airLineName) {
		this.flightNumber = flightNumber;
		this.capacity = capacity;
		this.airLineName = airLineName;
	}
	
	public void addPassenger(Passenger passenger) throws FlightException
	{
		if(passengerList==null)
		{
			passengerList = new ArrayList<Passenger>();
		}
		
		if(!isFull)
		{
			if(passengerList.size()<capacity)
			{
				passengerList.add(passenger);
				passengerCount++;
				if(passengerCount == capacity)
					isFull = true;
			}
		}
		else
		{
			throw new FlightException("Flight is full.Can not add more passengers");
		}
	}

	public void deletePassenger(Passenger passenger) throws FlightException
	{
		if(passengerList!=null && passengerList.size()>0)
		{
			if(!passengerList.remove(passenger))
			{
				throw new FlightException("Passenger="+passenger+" did not book in this flight");
			}
			
			passengerCount--;
			if(passengerList.size()<capacity)
				isFull = false;
		}
		else
		{
			throw new FlightException("Flight is empty.Can not remove passenger="+passenger.getFirstName()+" "+passenger.getLastName());
		}
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	public int getCapacity() {
		return capacity;
	}
	public boolean isFull() {
		return isFull;
	}
	public int getPassengerCount() {
		return passengerCount;
	}
	public String getAirLineName() {
		return airLineName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Flight))
			return false;
		Flight other = (Flight) obj;
		if (airLineName == null) {
			if (other.airLineName != null)
				return false;
		} else if (!airLineName.equals(other.airLineName))
			return false;
		if (capacity != other.capacity)
			return false;
		if (flightNumber == null) {
			if (other.flightNumber != null)
				return false;
		} else if (!flightNumber.equals(other.flightNumber))
			return false;
		if (isFull != other.isFull)
			return false;
		if (passengerCount != other.passengerCount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Flight [airLineName="+airLineName+" flightNumber=" + flightNumber + ", passengerList=" + passengerList + ", capacity=" + capacity
				+ ", isFull=" + isFull + ", passengerCount=" + passengerCount + "]";
	}
}
