import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerInfoUI{

	private JPanel contentPane;
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField ageTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CustomerInfoUI();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerInfoUI() {
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
			}
		});
		exitButton.setBounds(65, 262, 89, 23);
		contentPane.add(exitButton);
		
		JButton nextButton = new JButton("Next");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
	
		JLabel serverRespLabel = new JLabel("");
		serverRespLabel.setBounds(145, 237, 312, 14);
		contentPane.add(serverRespLabel);
		customerInfoFrame.setVisible(true);
	}
}
