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
		char key = (char)keyVal;
		
		for (int i = 0; i < message.length(); i++) {
			char input = message.charAt(i);
			if (input >= 'A' && input <= 'Z') {
				input += key;
				if (input > 'Z') {
					input -= 26;
				}
				if (input < 'A') {
					input += 26;
				}
			}
			else if (input >= 'a' && input <= 'z') {
				input += key;
				if (input > 'z') {
					input -= 26;
				}
				if (input < 'a') {
					input += 26;
				}
			}
			else if (input >= '0' && input <= '9') {
				input += (keyVal % 10);
				if (input > '9') {
					input -= 10;
				}
				if (input < '0') {
					input += 10;
				}
			}
			output += input;
		}
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
		
		JButton btnEncodeDecode = new JButton("Encode/Decode");
		btnEncodeDecode.setBounds(285, 162, 113, 23);
		getContentPane().add(btnEncodeDecode);
	}

	public static void main(String[] args) {

	}
	

}
