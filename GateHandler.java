import java.util.HashMap;
import java.util.Random;

public class GateHandler {
	//Different airlines are assumed to be assigned to different terminals as below
	Terminal terminalForDelta = Terminal.A;
	Terminal terminalForAlaska = Terminal.B;	
	Terminal terminalForSouthwest = Terminal.C;
	private static HashMap<Integer,Gate> gateMap = new HashMap<Integer,Gate>();
	
	public static Gate getGate(Flight flight)
	{
		Gate gate = null;
		Random random = new Random();
		int gateNumber = 1+random.nextInt(18);
		
		//Check if gate number is assigned to an airline flight
		while(true)
		{
			if(gateMap.get(gateNumber)==null)
			{
				System.out.println("Gate:"+gateNumber+" is not yet allocated");
				gate = allocateGate(gateNumber,flight);
				gateMap.put(gateNumber, gate);
				break;
			}
			//Check if gate is already full then the gate can be deleted for the airline flight and re-use it for another flight.
			else	
			{
				gate = gateMap.get(gateNumber);
				if(!gate.isGateOpen()) //If Gate is full
				{
					System.out.println("Gate:"+gateNumber+"is allocated and flight is full.Re-using the gate");					
					gateMap.remove(gateNumber);
					gate = allocateGate(gateNumber,flight);
					gateMap.put(gateNumber, gate);					
					break;
				}
				else //gate is still open and the gate number is already assigned to another flight
				{
					//Get another gate number and check if that is used.
					System.out.println("gateNumber:"+gateNumber +" is already in use.Trying to get another gate");
					random = new Random();
					gateNumber = 1+random.nextInt(18);
				}
			}
		}
		
		return gate;
	}
	
	private static Gate allocateGate(int gateNumber,Flight flight)
	{
		Gate gate = null;
		
		if(flight!=null && flight.getAirLineName().equals("Delta"))
			gate = new Gate(Terminal.A, gateNumber, flight); 
		else if(flight!=null && flight.getAirLineName().equals("Alaska"))
			gate = new Gate(Terminal.B, gateNumber, flight); 
		else if(flight!=null && flight.getAirLineName().equals("Southwest"))
			gate = new Gate(Terminal.C, gateNumber, flight); 
	
		return gate;
	}
}
