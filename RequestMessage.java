import java.io.Serializable;
import java.util.UUID;

public class RequestMessage implements Serializable 
{
	private static final long serialVersionUID = -5684318806309778028L;
	private RequestType requestType;
	private String message;
	private Passenger passenger; 
	private String airLine;
	private UUID clientId;
	
	public UUID getClientId() {
		return clientId;
	}

	public void setClientId(UUID clientId) {
		this.clientId = clientId;
	}

	public RequestMessage(RequestType requestType) {
		this.requestType = requestType;
	}
	
	public RequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public String getMessage() {
		return message;
	}

	public String getAirLine() {
		return airLine;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAirLine(String airLine) {
		this.airLine = airLine;
	}

	@Override
	public String toString() {
		return "RequestMessage [requestType=" + requestType + ", message=" + message + ", passenger=" + passenger
				+ ", airLine=" + airLine + ", clientId=" + clientId + "]";
	}
}
