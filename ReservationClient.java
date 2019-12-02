import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class ReservationClient extends JFrame 
{
    private static String hostname;
    private static int port;
    private static Socket socket;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private static boolean isConnectionEstablished = false;
	
	private JPanel contentPane;
    private static ReservationClient frame = null;
    //Fields in choose flight UI
	String selectedAirLine = null;
	JTextArea airLineDescriptionArea = null;
	JButton wantThisFlightButton = null;
	JButton chooseFlightButton = null;
	JButton differntFlightButton = null;
	boolean confirmationMode = false;
	//Fields in customer Info UI
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField ageTextField;
	private Passenger passenger;
	//Fields in display passenger list Info UI
	JTextArea passengerListtextArea = null;	
	JFrame displayFlightStatusFrame = null;
	JScrollPane scrollableTextArea = null;
	//Fields in flight status info UI
	JButton refreshFlightButton = null;
	JTextArea labelTextAreaFlightStatusUI = null;
	JTextArea customerInfoTextArea = null;
	UUID clientId = UUID.randomUUID();
	
	int passengerCountForSelectedAirLineFlight=0;
	int flightCapacityForSelectedAirLineFlight=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		frame = new ReservationClient();
		frame.setTitle("Purdue University Flight Reservation System");
		frame.setVisible(true);
	}

	public ReservationClient() 
	{
		DataObject dataObject;
    	dataObject = AirlineUtil.showInputDialog(InputType.HOST);
    	hostname = dataObject.getHostName();    	
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

    	port = dataObject.getPort();
        System.out.print("Hostname: "+hostname+" Port:"+port);
            
        showWelcomeUI();
	}
	
	private synchronized void sendMessageToServer(RequestMessage requestMessage) 
	{
		requestMessage.setClientId(clientId);
		print("In sendMessageToServer request message sent:" + requestMessage);
		try 
		{
			out.writeObject(requestMessage);
			out.flush();
		}
		catch (Exception ex) 
		{
			print("In sendMessageToServer..exception while sending the message..");
			ex.printStackTrace();
		}
	}	

	private void sendMessage(RequestType requestType) {
		RequestMessage message = new RequestMessage(requestType);
		message.setAirLine(selectedAirLine);
		sendMessageToServer(message);
	}
	
	private void sendMessage(RequestType requestType,Passenger passenger,String airLine) {
		RequestMessage message = new RequestMessage(requestType);
		if(RequestType.ADDPASSENGER ==requestType)
		{
			message.setPassenger(passenger);
			message.setAirLine(airLine);
		}
		sendMessageToServer(message);
	}
	

	private class ClientHandler extends Thread 
	{
		ResponseMessage message = null;
		
		public void run() 
		{
			try 
			{
				while (true) 
				{
					message = (ResponseMessage) in.readObject();
					
					System.out.println("In ClientHandler respose from server " + message);
					
					if(message.getRequestType().equals(RequestType.EXIT))
						break;
					else
						invokeGUI(message);
				}
			}
			catch (Exception ex) 
			{ 
				ex.printStackTrace();
			}
			finally {
				try {
					System.out.println("Disconnecting from the server");
					socket.close();
					in.close();
					out.close();
					System.exit(-1);
				}catch(Exception e)
				{
					System.out.println("Communication with server ended");
					e.printStackTrace();
				}
			}
		}
	}
	
	//This method will establish connection to the server.
	private void establishConnectionToServer() 
	{
		//Establish connection to the server
		System.out.println("isConnectionEstablished:"+isConnectionEstablished);
		if(!isConnectionEstablished)
		{
			try 
			{
	            socket = new Socket(hostname, port);
	            in = new ObjectInputStream(socket.getInputStream());
	            out = new ObjectOutputStream(socket.getOutputStream());
	            isConnectionEstablished = true;
	            System.out.println("Connection is established with server..");
	            ClientHandler clientHandler = new ClientHandler();
				clientHandler.start();
	            
			}catch(Exception e)
			{
				System.out.println("Connection is not established with server..");
				e.printStackTrace();
			}
		}
	}

	private void closeConnection()
	{
		RequestMessage requestMessage = new RequestMessage(RequestType.EXIT);
		sendMessageToServer(requestMessage);
	}
	
	private void updateUI(ResponseMessage responseMessage) 
	{
		System.out.println("In updateUI..responeMessage:"+responseMessage);
		showPassengerListUI(responseMessage);			
	}
	
	private void invokeGUI(ResponseMessage responseMessage) 
	{
		//Causes run() to be executed asynchronously on the AWT event dispatching thread. 
		//This will happen after all pending AWT events have been processed. This method should be 
		//used when an application thread needs to update the GUI
		SwingUtilities.invokeLater( new Runnable() 
		{
			public void run() 
			{
				System.out.println("In invokeToGUI-invokeLater");
				updateUI(responseMessage);
			}
		});
	}
	
	
	private class ExitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("ExitButtonListener - actionPerformed:"+e);
			JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University Airline Management System!");
			
			if(isConnectionEstablished)
				closeConnection();
			
			frame.dispose();
		}
	}
	
	
	//User interface for showing the welcome screen
	private void showWelcomeUI() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeTOthe = new JLabel("Welcome to the Purdue University Airline Reservation \r\nManagement System !");
		lblWelcomeTOthe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWelcomeTOthe.setBounds(20, 0, 486, 68);
		contentPane.add(lblWelcomeTOthe);
		
		JLabel lblWantToBookAFlight = new JLabel("                             Do you want to book a flight today?");
		lblWantToBookAFlight.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener( new ExitButtonListener());
		exitButton.setBounds(101, 338, 100, 23);
		contentPane.add(exitButton);
		
		
		JButton bookAFlightButton = new JButton("Book a Flight");
		JButton wantTobookAFlightButton = new JButton("Yes, I want to book a Flight");
		wantTobookAFlightButton.setVisible(false);
		
		bookAFlightButton.setBounds(241, 338, 200, 23);
		contentPane.add(bookAFlightButton);
		
		JLabel lblPurdeimage = new JLabel("");
		Image purdueImage = new ImageIcon(this.getClass().getResource("Purdue-image.png")).getImage();
		lblPurdeimage.setIcon(new ImageIcon(purdueImage));		
		lblPurdeimage.setBounds(84, 79, 308, 222);
		contentPane.add(lblPurdeimage);
		
		bookAFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Book a Flight button is clicked");
				//Make the existing label,image and button invisible
				lblWelcomeTOthe.setVisible(false);
				lblPurdeimage.setVisible(false);
				bookAFlightButton.setVisible(false);
				
				//Make the new label and button visible
				wantTobookAFlightButton.setBounds(244, 338, 200, 23);
				contentPane.add(wantTobookAFlightButton);
				wantTobookAFlightButton.setVisible(true);
				
				lblWantToBookAFlight.setBounds(20, 0, 486, 68);
				contentPane.add(lblWantToBookAFlight);
				lblWantToBookAFlight.setVisible(true);
			}
		});

		wantTobookAFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Yes, I want to book a Flight button is clicked");
				frame.setVisible(false);
				showBookingAFlightUI();
			}
		});		
	}

	
	//User Interface for booking a Flight
	public void showBookingAFlightUI() 
	{
		JFrame bookeAFlightFrame = new JFrame();

		bookeAFlightFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bookeAFlightFrame.setBounds(100, 100, 580, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		bookeAFlightFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Choose a flight from the dop down menu");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(79, 11, 408, 22);
		contentPane.add(label);
		
		JComboBox<String> airLineSelectionComboBox = new JComboBox<>();
		selectedAirLine = AirlineUtil.getAirlineList().get(0).getName();
		establishConnectionToServer();
		sendMessage(RequestType.CUSTOMERLIST);					

		airLineSelectionComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedAirLine = airLineSelectionComboBox.getSelectedItem().toString();
				System.out.println("selectedAirLine:"+selectedAirLine);
				if(selectedAirLine.equals("Alaska"))
				{
					airLineDescriptionArea.setText(AirlineUtil.getAirlineList().get(0).getDescription());
					establishConnectionToServer();
					sendMessage(RequestType.CUSTOMERLIST);					
				}
				else if(selectedAirLine.equals("Delta"))
				{
					airLineDescriptionArea.setText(AirlineUtil.getAirlineList().get(1).getDescription());
					establishConnectionToServer();
					sendMessage(RequestType.CUSTOMERLIST);					
				}
				else if(selectedAirLine.equals("Southwest"))
				{
					airLineDescriptionArea.setText(AirlineUtil.getAirlineList().get(2).getDescription());
					establishConnectionToServer();
					sendMessage(RequestType.CUSTOMERLIST);					
				}
				
				if(confirmationMode)
				{
					label.setText("Are you sure you want to book a flight on "+airLineSelectionComboBox.getSelectedItem() +"Airlines?");
				}
				else
				{
					label.setText("Choose a flight from the dop down menu");
				}
			}
		});
		
		airLineSelectionComboBox.setModel(new DefaultComboBoxModel(new String[] {"Alaska", "Delta", "Southwest"}));		
		airLineSelectionComboBox.setBounds(159, 44, 91, 22);
		contentPane.add(airLineSelectionComboBox);
		airLineSelectionComboBox.setSelectedIndex(0);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(29, 229, 89, 23);
		contentPane.add(exitButton);
		exitButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				System.out.println("ExitButtonListener - actionPerformed:"+e);
				JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University Airline Management System!");
				if(isConnectionEstablished)
					closeConnection();
				bookeAFlightFrame.dispose();
				System.exit(-1);
			}
		});
		

		differntFlightButton = new JButton("No, I want different Flight");
		differntFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("No, I want different Flight button is clicked");
				//Change To
				differntFlightButton.setVisible(false);
				wantThisFlightButton.setVisible(false);
				chooseFlightButton.setVisible(true);				
				label.setText("Choose a flight from the dop down menu");
				airLineSelectionComboBox.setVisible(true);
				airLineDescriptionArea.setVisible(true);
				
				confirmationMode = false;				
			}
		});
		
		chooseFlightButton = new JButton("Choose this Flight");
		chooseFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passengerCountForSelectedAirLineFlight<flightCapacityForSelectedAirLineFlight)
				{
					System.out.println("Proceed to book a flight");
					//Change To
					chooseFlightButton.setVisible(false);				
					differntFlightButton.setBounds(128, 229, 174, 23);
					contentPane.add(differntFlightButton);
					differntFlightButton.setVisible(true);
					wantThisFlightButton.setVisible(true);
					label.setText("Are you sure you want to book a flight on "+airLineSelectionComboBox.getSelectedItem() +"Airlines?");
					airLineSelectionComboBox.setVisible(false);
					airLineDescriptionArea.setVisible(false);
					confirmationMode = true;
				}
				else
				{
					print("\nThe airline is already full");
					JOptionPane.showMessageDialog(null, "Airline "+selectedAirLine+" is full.Please select different airline");
				}
			}
		});
		chooseFlightButton.setBounds(128, 229, 174, 23);
		contentPane.add(chooseFlightButton);
		
		airLineDescriptionArea = new JTextArea();
		airLineDescriptionArea.setEnabled(true);
		airLineDescriptionArea.setEditable(false);
		airLineDescriptionArea.setBounds(10, 88, 477, 102);
		airLineDescriptionArea.setBackground(new Color(240,240,240));		
		contentPane.add(airLineDescriptionArea);
		airLineDescriptionArea.setText(AirlineUtil.getAirlineList().get(0).getDescription());
		
		wantThisFlightButton = new JButton("Yes, I want this Flight");
		wantThisFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Yes, I want this Flight is clicked");
				bookeAFlightFrame.dispose();
				
				//Close the CustomerList UI after the passenger decided to purchase through select airline.
				if(displayFlightStatusFrame!=null)
					displayFlightStatusFrame.dispose();
				
				showCustomerInfoUI();
			}
		});
		wantThisFlightButton.setBounds(324, 229, 163, 23);
		contentPane.add(wantThisFlightButton);
		wantThisFlightButton.setVisible(false);
		bookeAFlightFrame.setTitle("Purdue University Flight Reservation System");
		bookeAFlightFrame.setVisible(true);
	}
	
	//Show customer information UI for capturing the customer details
	private void showCustomerInfoUI() {
		JFrame customerInfoFrame = new JFrame();		
		customerInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		customerInfoFrame.setBounds(100, 100, 504, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		customerInfoFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		customerInfoFrame.setTitle("Purdue University Flight Reservation System");
		
		JLabel firstNameLabel = new JLabel("What is your first Name?");
		firstNameLabel.setBounds(34, 51, 404, 19);
		contentPane.add(firstNameLabel);
		
		firstNameTextField = new JTextField();
		firstNameTextField.setBounds(34, 76, 393, 25);
		contentPane.add(firstNameTextField);
		firstNameTextField.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("What is your last Name?");
		lastNameLabel.setBounds(34, 112, 393, 19);
		contentPane.add(lastNameLabel);
		
		lastNameTextField = new JTextField();
		lastNameTextField.setBounds(34, 129, 393, 25);
		contentPane.add(lastNameTextField);
		lastNameTextField.setColumns(10);
		
		JLabel ageLabel = new JLabel("What is your Age?");
		ageLabel.setBounds(34, 165, 393, 19);
		contentPane.add(ageLabel);
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ExitButtonListener - actionPerformed:"+e);
				JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University Airline Management System!");
				if(isConnectionEstablished)
					closeConnection();
				customerInfoFrame.dispose();
				System.exit(-1);
			}
		});		
		exitButton.setBounds(65, 262, 89, 23);
		contentPane.add(exitButton);
		
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int age=0;
				boolean error =false;
    			try {
    				age  = Integer.parseInt(ageTextField.getText());
    			}catch(NumberFormatException nfe)
    			{
    				JOptionPane.showMessageDialog (null, "The specified age is not a valid number!", "Purdue University Flight Reservation System", JOptionPane.ERROR_MESSAGE);
    				ageTextField.requestFocus();    				
    				error = true;    				
    			}
    			if(!AirlineUtil.isStringOnlyAlphabetAndDash(firstNameTextField.getText()+lastNameTextField.getText()))
    			{
    				JOptionPane.showMessageDialog (null, "The name fields should contain only alphabet characters and dash", "Purdue University Flight Reservation System", JOptionPane.ERROR_MESSAGE);
    				error = true;    				
    			}
    			if(!error)
    			{
    				int selectedOption = JOptionPane.showConfirmDialog(null, 
                            "Are all the details you entered correct?. The passenger's name is "+firstNameTextField.getText()+" "+lastNameTextField.getText()+" "+age+".\nIf all the information shown is correct, Select the Yes button below.Otherwise, select No button", 
                            "Customer Info",JOptionPane.YES_NO_OPTION); 
    				if (selectedOption == JOptionPane.YES_OPTION) {
	    				establishConnectionToServer();
	    				passenger = new Passenger(firstNameTextField.getText(),lastNameTextField.getText(),age);
	    				sendMessage(RequestType.ADDPASSENGER, passenger,selectedAirLine);
	    				customerInfoFrame.dispose();
    				}
    				else if(selectedOption == JOptionPane.NO_OPTION) {
    					System.out.println("No option is selected");
        				JOptionPane.showMessageDialog (null, "Click Exit to cancel.Otherwise review and resubmit!", "Purdue University Flight Reservation System", JOptionPane.ERROR_MESSAGE);
        				nextButton.requestFocus();    				
    				}
    			}
			}
		});		
		nextButton.setBounds(180, 262, 89, 23);
		contentPane.add(nextButton);
		
		ageTextField = new JTextField();
		ageTextField.setBounds(34, 195, 393, 31);
		contentPane.add(ageTextField);
		ageTextField.setColumns(10);
		
		JLabel label = new JLabel("Please input your information below");
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(34, 11, 393, 29);
		contentPane.add(label);
		
		customerInfoFrame.setVisible(true);
	}
	
	//This is to show the customer information along with other passengers and gate info	
	private void showPassengerListUI(ResponseMessage responseMessage)
	{
		if(displayFlightStatusFrame!=null)
			displayFlightStatusFrame.dispose();
		
		displayFlightStatusFrame = new JFrame("Purdue University Flight Reservation System");
		displayFlightStatusFrame.setLayout(new BorderLayout());
		
		//Get the list of passengers belong to the selected airline.
	    String passengerList = "";
	    int passengerCount=0;
	    ArrayList<Reservation> reservationList = responseMessage.getReservationList();
	    for(Reservation reservation:reservationList)
	    {
	    	Passenger existingPassenger = reservation.getPassenger();
	    	if(reservation.getAirLine().equals(selectedAirLine))
	    	{
	    		passengerList = passengerList + existingPassenger.getFirstName().substring(0,1)+". "+existingPassenger.getLastName() +", "+existingPassenger.getAge()+"\n";
	    		passengerCount++;
	    	}
	    }
	    
		if(responseMessage.getRequestType().equals(RequestType.ADDPASSENGER) || responseMessage.getRequestType().equals(RequestType.REFRESHFLIGHT))
		{
			if(responseMessage.getRequestType().equals(RequestType.ADDPASSENGER))
				passenger = responseMessage.getPassenger();
			
			passengerList = passengerList+ passenger.getFirstName().substring(0,1)+". "+passenger.getLastName() +", "+passenger.getAge()+"\n";
		}
			
	    passengerCountForSelectedAirLineFlight = passengerCount;
	    flightCapacityForSelectedAirLineFlight = responseMessage.getFlightCapacity();
	    System.out.println("passengerCountForSelectedAirLineFlight="+passengerCountForSelectedAirLineFlight+" flightCapacityForSelectedAirLineFlight="+flightCapacityForSelectedAirLineFlight);
	    // Display the window.  
		displayFlightStatusFrame.setSize(500, 500);  
		displayFlightStatusFrame.setVisible(true);  
		displayFlightStatusFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

		labelTextAreaFlightStatusUI = new JTextArea();
		labelTextAreaFlightStatusUI.setFont(new Font("Arial", Font.BOLD, 15));
		if(responseMessage.getRequestType().equals(RequestType.CUSTOMERLIST))
			labelTextAreaFlightStatusUI.setText("\t\t"+selectedAirLine+" "+passengerCount+":"+responseMessage.getFlightCapacity());
		else
			labelTextAreaFlightStatusUI.setText("\tFlight is now boarding at "+selectedAirLine+" Airlines\r\n\t\tEnjoy your Flight\r\n\tYour Flight is now boarding at Gate "+responseMessage.getGateNumber()+"\n\t\t"+(passengerCount+1)+":"+responseMessage.getFlightCapacity());
	
		labelTextAreaFlightStatusUI.setBackground(new Color(240,240,240));
		displayFlightStatusFrame.getContentPane().add(labelTextAreaFlightStatusUI,BorderLayout.NORTH);
    	
		passengerListtextArea = new JTextArea();
		passengerListtextArea.setBounds(41, 78, 429, 155);
		passengerListtextArea.setBackground(new Color(240,240,240));		
		
	    JScrollPane scrollableTextArea = new JScrollPane(passengerListtextArea);  
	    scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
    	passengerListtextArea.setText(passengerList);
    	displayFlightStatusFrame.add(scrollableTextArea,BorderLayout.CENTER);
    	
		customerInfoTextArea = new JTextArea();
		customerInfoTextArea.setBounds(41, 244, 429, 80);		
		customerInfoTextArea.setBackground(new Color(240,240,240));
		
		if(!responseMessage.getRequestType().equals(RequestType.CUSTOMERLIST))
		{
			String customerInfoStr="Boarding pass for flight "+responseMessage.getFlightNumber()+" with "+responseMessage.getAirLine()+" Airlines\n"+"First name: "+passenger.getFirstName()+"\n"+
			"Last name:"+passenger.getLastName()+"\n"+"Passenger Age:"+passenger.getAge()+"\nYou can now boaring at gate "+responseMessage.getGateNumber();
			customerInfoTextArea.setText(customerInfoStr);
		}
		else
			customerInfoTextArea.setText("");
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(2, 1));
		southPanel.add(customerInfoTextArea);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JButton exitButton = new JButton("Exit");
		buttonPanel.add(exitButton);    	
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ExitButtonListener - actionPerformed:"+e);
				JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University Airline Management System!");
				if(!responseMessage.getRequestType().equals(RequestType.CUSTOMERLIST))
				{
					if(isConnectionEstablished)
						closeConnection();
				}
				displayFlightStatusFrame.dispose();
			}
		});				
		buttonPanel.add(new JLabel(""));
    	JButton refreshFlightButton = new JButton("Refresh Flight Status");
		refreshFlightButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			establishConnectionToServer();
			sendMessage(RequestType.REFRESHFLIGHT);
			}
		});    	
    	buttonPanel.add(refreshFlightButton);
    	southPanel.add(buttonPanel);
		if(responseMessage.getRequestType().equals(RequestType.CUSTOMERLIST))
		{
			refreshFlightButton.setVisible(false);	
		}
    	
		displayFlightStatusFrame.getContentPane().add(southPanel,BorderLayout.SOUTH);	    
		displayFlightStatusFrame.setVisible(true);
	}
	
    private void print(String str)
    {
    	System.out.println(new Date()+" "+str);
    }  
}