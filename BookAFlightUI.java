import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class BookAFlightUI {

	private JPanel contentPane;
	String selectedAirLine = null;
	JTextArea airLineDescriptionArea = null;
	JButton wantThisFlightButton = null;
	JButton chooseFlightButton = null;
	JButton differntFlightButton = null;
	boolean confirmationMode = false;	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new BookAFlightUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public BookAFlightUI() {
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
		airLineSelectionComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				selectedAirLine = airLineSelectionComboBox.getSelectedItem().toString();
				System.out.println("selectedAirLine:"+selectedAirLine);
				if(selectedAirLine.equals("Alaska"))
				{
					airLineDescriptionArea.setText(AirlineUtil.getAirlineList().get(0).getDescription());
				}
				else if(selectedAirLine.equals("Delta"))
				{
					airLineDescriptionArea.setText(AirlineUtil.getAirlineList().get(1).getDescription());
				}
				else if(selectedAirLine.equals("Southwest"))
				{
					airLineDescriptionArea.setText(AirlineUtil.getAirlineList().get(2).getDescription());
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

		differntFlightButton = new JButton("No, I want different Flight");
		differntFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("No, I want different Flight button is clicked");
				//Change To
				differntFlightButton.setVisible(false);
				wantThisFlightButton.setVisible(false);
				chooseFlightButton.setVisible(true);				
				label.setText("Choose a flight from the dop down menu");
				confirmationMode = false;				
			}
		});
		
		chooseFlightButton = new JButton("Choose this Flight");
		chooseFlightButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Change To
				chooseFlightButton.setVisible(false);				
				differntFlightButton.setBounds(128, 229, 174, 23);
				contentPane.add(differntFlightButton);
				differntFlightButton.setVisible(true);
				wantThisFlightButton.setVisible(true);
				label.setText("Are you sure you want to book a flight on "+airLineSelectionComboBox.getSelectedItem() +"Airlines?");
				confirmationMode = true;
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
				System.out.println("Proceed to book a flight");
			}
		});
		wantThisFlightButton.setBounds(324, 229, 163, 23);
		contentPane.add(wantThisFlightButton);
		wantThisFlightButton.setVisible(false);
		bookeAFlightFrame.setTitle("Purdue University Flight Reservation System");
		bookeAFlightFrame.setVisible(true);
	}
}
