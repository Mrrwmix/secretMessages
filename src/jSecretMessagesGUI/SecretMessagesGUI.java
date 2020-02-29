package jSecretMessagesGUI;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class SecretMessagesGUI extends JFrame {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	
	public String encode(String message, int keyVal) {
		String output = "";
		
		return output;
	}
	
	public SecretMessagesGUI() {
		getContentPane().setLayout(null);
		
		txtIn = new JTextArea();
		txtIn.setBounds(10, 11, 564, 140);
		getContentPane().add(txtIn);
		
		txtOut = new JTextArea();
		txtOut.setBounds(10, 210, 564, 140);
		getContentPane().add(txtOut);
		
		txtKey = new JTextField();
		txtKey.setBounds(224, 163, 51, 20);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Key:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(173, 166, 48, 14);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Encode/Decode");
		btnNewButton.setBounds(285, 162, 113, 23);
		getContentPane().add(btnNewButton);
	}

	public static void main(String[] args) {

	}
	

}
