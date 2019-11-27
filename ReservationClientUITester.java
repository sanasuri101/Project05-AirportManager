import javax.swing.JOptionPane;

public class ReservationClientUITester {
	
	public static void main(String[] args) 
	{
		new ReservationClientUITester();
	}	
	
	public ReservationClientUITester() 
	{
		DataObject dataObject;
    	dataObject = AirlineUtil.showInputDialog(InputType.HOST);
    	String hostname = dataObject.getHostName();    	
    	if(dataObject.getResult() == JOptionPane.CANCEL_OPTION)
    	{
            System.out.println("Goodbye!");
            JOptionPane.showMessageDialog(null, "Goodbye!");                
            System.exit(-1);
    	}
    	else
    	{
    		dataObject = AirlineUtil.showInputDialog(InputType.PORT);        		
        	if(dataObject.getResult() == JOptionPane.CANCEL_OPTION)
        	{
                System.out.println("Goodbye!");
                JOptionPane.showMessageDialog(null, "Goodbye!");
                System.exit(-1);
        	}
    	}

    	int port = dataObject.getPort();
        System.out.print("Hostname: "+hostname+" Port:"+port);
	}

}
