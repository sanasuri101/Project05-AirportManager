import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.Objects;

public final class ReservationServerClientHandler implements Runnable {
    /**
     * The client socket of this request handler.
     */
    private Socket clientSocket;
    private ObjectOutputStream out =null;
    private ObjectInputStream in = null;
    private Flight flight = null;
    private Gate gate = null;
    
    
    public ReservationServerClientHandler(Socket clientSocket) throws NullPointerException {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");
        this.clientSocket = clientSocket;
    } 

    /**
     * Handles the requests sent by the client connected to this request handler's client socket.
     */
    @Override
    public void run()
    {
    	print("Inside ReservationServerClientHandler - run");
    	try
    	{
    		out = new ObjectOutputStream(clientSocket.getOutputStream());
    		in = new ObjectInputStream(clientSocket.getInputStream());
    		RequestMessage requestMessage = null;
    		ResponseMessage responseMessage = null;
    		
			while(true)
			{
				print("Inside while..");
				requestMessage = (RequestMessage)in.readObject();
				print("Client sent:"+requestMessage);
				responseMessage = new ResponseMessage(requestMessage.getRequestType());
				
				if(requestMessage.getRequestType().equals(RequestType.EXIT))
				{
					responseMessage.setRequestType(requestMessage.getRequestType());
					print("Server sent:"+responseMessage+"\n");
					out.writeObject(responseMessage);
	                out.flush();	
					print("Client has exited!!");
					break;
				}
				
				setResponse(requestMessage,responseMessage);
				print("Server sent:"+responseMessage+" Number of reservations:"+responseMessage.getReservationList().size() +"\n");
				out.writeObject(responseMessage);
                out.flush();	
        		requestMessage = null;
        		responseMessage = null;                
			}
		}catch(Exception ex)
    	{
			System.out.println(ex);
		}finally
    	{
			print("closing client connection..");
			try {
				in.close();
				out.close();
				clientSocket.close();
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
    	print("exiting run method - client connection is terminated");
    }

	private void setResponse(RequestMessage requestMessage, ResponseMessage responseMessage) throws IOException
	{
		responseMessage.setClientId(requestMessage.getClientId());		
		if(requestMessage.getRequestType().equals(RequestType.ADDPASSENGER))
		{
			print("Client sent passenger details"+requestMessage.getPassenger());
			ReservationServer.reservationModel.add(new Reservation(requestMessage.getPassenger(),requestMessage.getAirLine()));
			responseMessage.setPassenger(requestMessage.getPassenger());
			responseMessage.setMessage("Passenger is added successfully");
			AirlineUtil.serialize(ReservationServer.reservationModel);
		}
		if(flight == null)
			flight = getFlight(requestMessage.getAirLine());
		
		if(requestMessage.getRequestType().equals(RequestType.ADDPASSENGER) || 
				requestMessage.getRequestType().equals(RequestType.REFRESHFLIGHT))
		{
			if(gate == null)
				gate = getGate(flight);

			responseMessage.setGateNumber(gate.getTerminal()+""+gate.getGateNumber());
		}
		
		responseMessage.setFlightNumber(flight.getFlightNumber());
		responseMessage.setFlightCapacity(flight.getCapacity());			
		responseMessage.setReservationList(ReservationServer.reservationModel.getReservationList());		
		responseMessage.setAirLine(requestMessage.getAirLine());
		System.out.print("Before Return");
	}
    
    private void print(String str)
    {
    	System.out.println(new Date()+" "+str);
    }

    private Flight getFlight(String airLine)
    {
    	return AirlineUtil.getFlight(airLine);
    }
	private Gate getGate(Flight flight)
	{
		return GateHandler.getGate(flight);
	}
}