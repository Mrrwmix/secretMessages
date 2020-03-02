package jSecretMessagesGUI;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSlider;

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
		getContentPane().setBackground(new Color(0, 128, 128));
		getContentPane().setLayout(null);
		
		txtIn = new JTextArea();
		txtIn.setWrapStyleWord(true);
		txtIn.setLineWrap(true);
		txtIn.setFont(new Font("Georgia", Font.PLAIN, 18));
		txtIn.setBounds(10, 11, 564, 140);
		getContentPane().add(txtIn);
		
		txtOut = new JTextArea();
		txtOut.setWrapStyleWord(true);
		txtOut.setLineWrap(true);
		txtOut.setFont(new Font("Georgia", Font.PLAIN, 18));
		txtOut.setBounds(10, 210, 564, 140);
		getContentPane().add(txtOut);
		
		txtKey = new JTextField();
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("13");
		txtKey.setBounds(224, 163, 51, 20);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Key:");
		lblNewLabel.setFont(new Font("Lucida Console", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(173, 216, 230));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(173, 166, 48, 14);
		getContentPane().add(lblNewLabel);
		
		JButton btnEncodeDecode = new JButton("Encode/Decode");
		btnEncodeDecode.setForeground(new Color(0, 0, 0));
		btnEncodeDecode.setBackground(new Color(173, 216, 230));
		btnEncodeDecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = txtIn.getText();
					int keyVal = Integer.parseInt(txtKey.getText());
					txtOut.setText(encode(message, keyVal));					
				} catch (Exception err) {
					JOptionPane.showMessageDialog(null, "Please enter a whole number for the encryption key.");
					txtKey.requestFocus();
					txtKey.selectAll();
				}
			}
		});
		btnEncodeDecode.setBounds(285, 162, 134, 23);
		getContentPane().add(btnEncodeDecode);
		
		JSlider slider = new JSlider();
		slider.setValue(13);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setMinimum(-26);
		slider.setMaximum(26);
		slider.setPaintLabels(true);
		slider.setForeground(new Color(0, 0, 0));
		slider.setBackground(new Color(176, 224, 230));
		slider.setBounds(10, 154, 173, 54);
		getContentPane().add(slider);
	}

	public static void main(String[] args) {
		SecretMessagesGUI theApp = new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(600,400));
		theApp.setVisible(true);
	}
}
