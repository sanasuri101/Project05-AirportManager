import java.util.ArrayList;
import java.util.List;

public class Driver {
	public static void main(String[] args) {
		List<Airline> airLineList = new ArrayList<Airline>();
		//Add airlines
		Airline delta = new Delta();
		Airline alaska = new Alaska();
		Airline southWest = new Southwest();
		
		airLineList.add(delta);
		airLineList.add(alaska);
		airLineList.add(southWest);
		System.out.println(airLineList);
		//Assumptions
		//Different airlines(Delta,Alaska and Southwest are assumed to be assigned to different terminals A,B and C respectively.
		//Assume the flights 18000 belongs to Delta, 19000 belongs to Alaska and 15000 belongs to Southwest for simplicity.
		//The capacity of flight is assumed to be 100 by default for each airline and that can be changed.  
		
		//Add flights to airlines.
		//Flight - 18000 belongs to Delta and Terminal A
		
		Flight flight  =new Flight("18000",3,"Delta");
		//Add passengers to Delta airline's flight
		Passenger passenger1 = new Passenger("Sriram","Anasuri",18);
		Passenger passenger2 = new Passenger("Annie","Taylor",21);		
		Passenger passenger3 = new Passenger("Jeff","Peter",35);
		Passenger passenger4 = new Passenger("John","Parker",45);		
		try
		{
			flight.addPassenger(passenger1);
			flight.addPassenger(passenger2);		
			flight.addPassenger(passenger3);
			
			//Add 4th passenger beyond capacity
			flight.addPassenger(passenger4);
		}
		catch(FlightException fe)
		{
			System.out.println(fe);
		}
		System.out.println("After adding passengers to flight");		
		//Add flight to delta airlines
		delta.addFlight(flight);
		System.out.println("\nAfter adding passengers and flight to Delta airline\n");
		System.out.println(airLineList);	
		//Delete an existing passenger from Delta airline's flight
		try
		{
			flight.deletePassenger(passenger3);
			
			//Delete a passenger4 not in the flight
			flight.deletePassenger(passenger4);
		}
		catch(FlightException fe)
		{
			System.out.println(fe);
		}
		System.out.println("\nAfter deleting passenger="+passenger3+ " from Delta airline's flight\n");
		System.out.println("\nFlight details="+flight);
		
		//Assign gate to an Airline flight
		Gate gate = GateHandler.getGate(flight);
		System.out.println("\nAllocated gate for Delta flight="+gate.getFlight().getFlightNumber()+" at "+gate.getTerminal()+""+gate.getGateNumber());
		System.out.println("\nPassenger="+passenger1 +" is verified at gate="+gate.getGateNumber());		
		gate.incrementTicketCount();
		if(gate.isGateOpen())
		{
			System.out.println("\nTickets for Airline:"+delta.getName()+", Flight Number:"+gate.getFlight().getFlightNumber() +" passengers are verified at the gate="+gate.getGateNumber());
		}
		else
		{
			System.out.println("\nFlight is full and the gate="+gate +" is closed");			
		}
		//Checkout another passenger. Check if all passengers are verified.
		System.out.println("\nPassenger="+passenger2 +" is verified at gate="+gate.getGateNumber());
		gate.incrementTicketCount();
		//gate.incrementTicketCount();
		if(gate.isGateOpen())
		{
			System.out.println("\nTickets for Airline:"+delta.getName()+", Flight Number:"+gate.getFlight().getFlightNumber() +" passengers are verified at the gate="+gate.getGateNumber());
		}
		else
		{
			System.out.println("\nFlight is full and the gate="+gate +" is closed");			
		}
		
		//Create Boarding pass to the passenger
		BoardingPass boardingPass = new BoardingPass(passenger1, "Delta", gate);
		System.out.println("\nPrint the boarding pass for the passenger="+passenger1+" boardingPass="+boardingPass);
		
		//Allocate gate to Alaska flight
		flight  = AirlineUtil.getFlight("Alaska");
		gate = GateHandler.getGate(flight);
		System.out.println("\nAllocated gate for Alaska flight="+gate.getFlight().getFlightNumber()+" at "+gate.getTerminal()+""+gate.getGateNumber());		
		
		//Allocate gate to Southwest flight
		flight  = AirlineUtil.getFlight("Southwest");
		gate = GateHandler.getGate(flight);
		System.out.println("\nAllocated gate for Southwest flight="+gate.getFlight().getFlightNumber()+" at "+gate.getTerminal()+""+gate.getGateNumber());
	}
}