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

public class ReStringDialog extends JDialog implements ActionListener, WindowListener {
	
		private Container contentPane = getContentPane();
		private JLabel noString = new JLabel();
		private JButton exitButton = new JButton();
		
		public ReStringDialog() {
			setTitle("2인용 오목");
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			ImageIcon img = new ImageIcon("image\\icon.png");
			setIconImage(img.getImage());
			setSize(300,130);
			setLocationRelativeTo(null);
			setVisible(true);
			addWindowListener(this);
			contentPane.setLayout(null);
			contentPane.setBackground(Color.white);
			noString.setText("이름을 다시 입력해 주세요.");
			noString.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			noString.setSize(280, 20);
			noString.setLocation(0, 20);
			noString.setHorizontalAlignment(noString.CENTER);
			contentPane.add(noString);
			exitButton.setText("확인");
			exitButton.setFont(new Font("맑은 고딕", Font.BOLD, 10));
			exitButton.setSize(60, 30);
			exitButton.setLocation(109,50);
			exitButton.setContentAreaFilled(false);
			exitButton.setFocusPainted(false);
			exitButton.addActionListener(this);
			contentPane.add(exitButton);}
		
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