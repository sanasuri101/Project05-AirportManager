import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class ResponseMessage implements Serializable {
	private static final long serialVersionUID = 8454242096443255325L;
	private RequestType requestType;
	private String message;
	private ArrayList<String> airLineList = null;
	private ArrayList<Reservation> reservationList = null;
	private String airLine = null;
	private String flightNumber = null;
	private int flightCapacity=0;
	private String gateNumber = null;
	private Passenger passenger;	

	private UUID clientId;
	
	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}	
	
	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	}

	public void setReservationList(ArrayList<Reservation> reservationList) {
		this.reservationList = reservationList;
	}

	public String getAirLine() {
		return airLine;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	public ResponseMessage(RequestType requestType) {
		this.requestType = requestType;
	}

	public RequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}


	public String getMessage() {
		return message;
	}

	public ArrayList<String> getAirLineList() {
		return airLineList;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAirLineList(ArrayList<String> airLineList) {
		this.airLineList = airLineList;
	}
	
	public String getFlightNumber() {
		return flightNumber;
	}

	public String getGateNumber() {
		return gateNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public void setGateNumber(String gateNumber) {
		this.gateNumber = gateNumber;
	}
	
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	public int getFlightCapacity() {
		return flightCapacity;
	}

	public void setFlightCapacity(int flightCapacity) {
		this.flightCapacity = flightCapacity;
	}

	@Override
	public String toString() {
		return "ResponseMessage [requestType=" + requestType + ", message=" + message + ", airLineList=" + airLineList
				+ ", reservationList=" + "" + ", airLine=" + airLine + ", flightNumber=" + flightNumber
				+ ", flightCapacity=" + flightCapacity + ", gateNumber=" + gateNumber + ", passenger=" + passenger
				+ ", clientId=" + clientId + "]";
	}	
}
