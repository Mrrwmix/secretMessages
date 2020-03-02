package jSecretMessagesGUI;

import javax.swing.JFrame;
import javax.swing.JTextArea;
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
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SecretMessagesGUI extends JFrame {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
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
		setTitle("Matthew's Secret Message App");
		getContentPane().setBackground(new Color(0, 128, 128));
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 564, 140);
		getContentPane().add(scrollPane);
		
		txtIn = new JTextArea();
		scrollPane.setViewportView(txtIn);
		txtIn.setWrapStyleWord(true);
		txtIn.setLineWrap(true);
		txtIn.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 210, 564, 140);
		getContentPane().add(scrollPane_1);
		
		txtOut = new JTextArea();
		scrollPane_1.setViewportView(txtOut);
		txtOut.setWrapStyleWord(true);
		txtOut.setLineWrap(true);
		txtOut.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		txtKey = new JTextField();
		txtKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int keyVal = Integer.parseInt(txtKey.getText());
					slider.setValue(keyVal);
				} catch (Exception err) {
					if (!txtKey.getText().startsWith("-")) {
						JOptionPane.showMessageDialog(null, "Please enter a whole number between -26 and 26.");
						txtKey.requestFocus();
						txtKey.selectAll();						
					}
				}
			}
		});
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("13");
		txtKey.setBounds(235, 162, 51, 32);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Key:");
		lblNewLabel.setFont(new Font("Lucida Console", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(173, 216, 230));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(184, 168, 48, 23);
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
		btnEncodeDecode.setBounds(296, 162, 134, 34);
		getContentPane().add(btnEncodeDecode);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				txtKey.setText("" + slider.getValue());
				String message = txtIn.getText();
				int keyVal = slider.getValue();
				txtOut.setText(encode(message, keyVal));
			}
		});
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
		
		JButton btnSwitch = new JButton("\u2191 Switch \u2193");
		btnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIn.setText(txtOut.getText());
				
				int opposite;
				
				if (Integer.parseInt(txtKey.getText()) > 0) {
					opposite = 0 - Integer.parseInt(txtKey.getText());
				}
				else {
					opposite = Math.abs(Integer.parseInt(txtKey.getText()));
				}
				txtKey.setText("" + opposite);
				slider.setValue(opposite);
				
				txtOut.setText(encode(txtIn.getText(), opposite));
			}
		});
		btnSwitch.setForeground(Color.BLACK);
		btnSwitch.setBackground(new Color(173, 216, 230));
		btnSwitch.setBounds(440, 162, 134, 34);
		getContentPane().add(btnSwitch);
	}

	public static void main(String[] args) {
		SecretMessagesGUI theApp = new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(600,400));
		theApp.setVisible(true);
	}
}
