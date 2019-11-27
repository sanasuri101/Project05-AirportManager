import java.util.ArrayList;

import javax.swing.JOptionPane;

public class AirlineUtil {
	
	/**
     * Determines whether or not the specified {@code String} is parsable as a non-negative {@code int}.
     */
    private static boolean isParsable(String string) {
        return string.chars()
                     .mapToObj(Character::isDigit)
                     .reduce(Boolean::logicalAnd)
                     .orElse(Boolean.FALSE);
    }
    public static boolean isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return false;
        return true;
    }     
    public static DataObject showInputDialog(InputType inputType)
    {
    	Integer choice = 0;
    	DataObject dataObject = new DataObject();
    	if(inputType!=null && inputType.equals(InputType.HOST))
    	{
    		while(true)
    		{
            	String hostName = JOptionPane.showInputDialog(null, "What is the host name you'd like to connect to?", "Hostname?",
        		        JOptionPane.QUESTION_MESSAGE);

        		System.out.println("Host:"+hostName);
        	    if (hostName == null) 
        	    {
        	    	choice = JOptionPane.CANCEL_OPTION;
        	    	dataObject.setResult(choice);
        	    	return dataObject;
        	    }        		
        	    else if(hostName.equals(""))
        		{
        			choice = JOptionPane.OK_OPTION;
        	    	dataObject.setResult(choice);        			
        			JOptionPane.showMessageDialog (null, "Please enter a valid host name", "Hostname?", JOptionPane.ERROR_MESSAGE);			
        		}
        		else
        		{
        	    	dataObject.setResult(choice);
        	    	dataObject.setHostName(hostName);
        			break;        			
        		}
    		}
    	}
    	else if(inputType!=null && inputType.equals(InputType.PORT))
    	{
    		while(true)
    		{
        		String portString = JOptionPane.showInputDialog(null, "What is the port name you'd like to connect to?", "Port?",
        		        JOptionPane.QUESTION_MESSAGE);
        		System.out.println("Port:"+portString);

        		if(portString == null)
        		{
        			choice = JOptionPane.CANCEL_OPTION;
        	    	dataObject.setResult(choice);
        	    	return dataObject;        	    	
        		}
        		else if(portString.equals(""))
        		{
        			choice = JOptionPane.OK_OPTION;
        	    	dataObject.setResult(choice);        			
        			JOptionPane.showMessageDialog (null, "Please enter a valid port name", "Port?", JOptionPane.ERROR_MESSAGE);			
        		}
        		else
        		{
        			try {
        				if(isParsable(portString))
        				{
            				int port = Integer.parseInt(portString);
                	    	dataObject.setResult(choice);   
                	    	dataObject.setPort(port);
                			break;        				
        				}
        				else
                			JOptionPane.showMessageDialog (null, "Please enter a valid port name", "Port?", JOptionPane.ERROR_MESSAGE);        					
        			}catch(Exception e)
        			{
            			JOptionPane.showMessageDialog (null, "Please enter a valid port name", "Port?", JOptionPane.ERROR_MESSAGE);
        			}
        		}
    		}
    	}
    	return dataObject;
    }
    
    public static ArrayList<Airline> getAirlineList()
    {
    	ArrayList<Airline> airLineList = new ArrayList<Airline>();
    	airLineList.add(new Alaska());
    	airLineList.add(new Delta());
    	airLineList.add(new Southwest());    	
    	return airLineList;
    }
}
