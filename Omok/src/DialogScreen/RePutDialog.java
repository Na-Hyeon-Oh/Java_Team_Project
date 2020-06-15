package DialogScreen;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class RePutDialog extends JDialog implements ActionListener, WindowListener {
	
		private String[] reason = new String[7];
		
		private Container contentPane = getContentPane();
		private JLabel whyNot = new JLabel();
		private JLabel cantString = new JLabel();
		private JButton exitButton = new JButton();
		
		public RePutDialog(int whatDialog) {
			reason[3] = "����Դϴ�.";
			reason[4] = "����Դϴ�.";
			reason[6] = "����Դϴ�.";
			switch (whatDialog)
			{
			case 3:
				whyNot.setText(new String(reason[3]));
				break;
			case 4:
				whyNot.setText(new String(reason[4]));
				break;
			case 6:
				whyNot.setText(new String(reason[6]));
				break;		
			}
			
			setTitle("2�ο� ����");
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			ImageIcon img = new ImageIcon("image\\icon.png");
			setIconImage(img.getImage());
			setSize(220,150);
			contentPane.setLayout(null);
			contentPane.setBackground(Color.white);
			setLocationRelativeTo(null);
			setVisible(true);
			addWindowListener(this);
			whyNot.setFont(new Font("���� ���", Font.PLAIN, 15));
			whyNot.setSize(160, 20);
			whyNot.setLocation(25, 15);
			whyNot.setHorizontalAlignment(whyNot.CENTER);
			contentPane.add(whyNot);
			cantString.setText("���� ���� �� �����ϴ�.");
			cantString.setFont(new Font("���� ���", Font.PLAIN, 15));
			cantString.setSize(280, 20);
			cantString.setLocation(26, 40);
			contentPane.add(cantString);
			exitButton.setText("Ȯ��");
			exitButton.setFont(new Font("���� ���", Font.BOLD, 10));
			exitButton.setSize(60, 30);
			exitButton.setLocation(73,70);
			exitButton.setContentAreaFilled(false);
			exitButton.setFocusPainted(false);
			exitButton.addActionListener(this);
			contentPane.add(exitButton);
			}
		
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
		public void windowClosing(WindowEvent e) {
			dispose();
		}

		public void windowClosed(WindowEvent e) {}
		public void windowOpened(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
			
		}