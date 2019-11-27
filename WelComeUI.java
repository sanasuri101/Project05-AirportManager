import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WelComeUI {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new WelComeUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	

	public WelComeUI() 
	{
		JFrame welComeUIFrame = new JFrame();
		welComeUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		welComeUIFrame.setBounds(100, 100, 585, 418);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		welComeUIFrame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeTOthe = new JLabel("Welcome to the Purdue University Airline Reservation \r\nManagement System !");
		lblWelcomeTOthe.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWelcomeTOthe.setBounds(20, 0, 486, 68);
		contentPane.add(lblWelcomeTOthe);
		
		JLabel lblWantToBookAFlight = new JLabel("                             Do you want to book a flight today?");
		lblWantToBookAFlight.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("ExitButtonListener - actionPerformed:"+e);
				JOptionPane.showMessageDialog(null, "Thank you for using the Purdue University Airline Management System!");
				welComeUIFrame.dispose();
			}			
		});
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
		welComeUIFrame.setVisible(true);
		
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
			}
		});		
	}
}
