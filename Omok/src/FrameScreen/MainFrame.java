package FrameScreen;
import javax.swing.*;
import DialogScreen.*;

import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener {
	
	private JLabel mainLabel = new JLabel();
	private JLabel player1Label = new JLabel();
	private JLabel player2Label = new JLabel();
	private JButton gameStartButton = new JButton();
	private JTextField text1 = new JTextField();
	private JTextField text2 = new JTextField();
	private Container contentPane = getContentPane();
	
	public MainFrame() {
		setTitle(" 2¿ŒøÎ ø¿∏Ò ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("image\\icon.png");
		setIconImage(img.getImage());
		setSize(325,293);
		setResizable(false);
		setLocationRelativeTo(null);		
		setVisible(true);
		setMainScreen();
	}
	
	public void setMainScreen() {
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		
		mainLabel.setText("2¿ŒøÎ ø¿∏Ò");
		mainLabel.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 40));
		mainLabel.setSize(200, 60);
		mainLabel.setLocation(53, 25);
		contentPane.add(mainLabel);
		
		player1Label.setText("Player1 ¿Ã∏ß :");
		player1Label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 20));
		player1Label.setSize(150, 30);
		player1Label.setLocation(40, 110);
		contentPane.add(player1Label);
		
		player2Label.setText("Player2 ¿Ã∏ß : ");
		player2Label.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 20));
		player2Label.setSize(150, 30);
		player2Label.setLocation(40, 150);
		contentPane.add(player2Label);
		
		gameStartButton.setText("∞‘¿” Ω√¿€");
		gameStartButton.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 15));
		gameStartButton.setSize(120, 35);
		gameStartButton.setLocation(96, 200);
		gameStartButton.setContentAreaFilled(false);
		gameStartButton.setFocusPainted(false);
		gameStartButton.addActionListener(this);
		contentPane.add(gameStartButton);
		
		text1.setText("");
		text1.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 20));
		text1.setSize(100, 30);
		text1.setLocation(175, 110);
		contentPane.add(text1);
		
		text2.setText("");
		text2.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 20));
		text2.setSize(100, 30);
		text2.setLocation(175, 150);
		contentPane.add(text2);
	}

	public void actionPerformed(ActionEvent e) {
		if (text1.getText().equals("") || text2.getText().equals("") || text1.getText().equals(text2.getText())) {
			new ReStringDialog();
			return;
		}
		setVisible(false);
		new GameFrame(text1.getText(), text2.getText());
	}
}
