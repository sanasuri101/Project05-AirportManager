import java.io.Serializable;

public class Reservation implements Serializable{
	private static final long serialVersionUID = 6958251168499373617L;
	Passenger passenger;
	String airLine;
	
	public Reservation(Passenger passenger, String airLine) {
		this.passenger = passenger;
		this.airLine = airLine;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}
	public String getAirLine() {
		return airLine;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	@Override
	public String toString() {
		return "Reservation [passenger=" + passenger + ", airLine=" + airLine + "]";
	}
}
