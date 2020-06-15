package DialogScreen;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class DrawDialog extends JDialog implements ActionListener, WindowListener {
	
	private String[] playerName;
	private int[][] board;
    private char[][] resultBoard = new char[15][15];
    
	private Container contentPane = getContentPane();
	private JLabel gameEnd = new JLabel();
	private JLabel gameInfo = new JLabel();
	private JLabel resultTime = new JLabel();
	private JButton exitButton = new JButton();
	
    private OutputStream fos;
    private OutputStreamWriter osw;
    private BufferedWriter bw;
    
    public DrawDialog(String[] playerName, int[][] board, String wholeTime) {
        this.playerName = playerName;
        this.board = board;
		try {
			Calendar cal = Calendar.getInstance();
			String todayDate = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + " ";
			String todayTime = todayDate + cal.get(Calendar.HOUR_OF_DAY) + "-" + cal.get(Calendar.SECOND);
			fos = new FileOutputStream (todayTime + " " + playerName[1] + "vs" + playerName[2] + ".txt");
			osw = new OutputStreamWriter (fos);
        	bw  = new BufferedWriter (osw);
		} catch (IOException e) {
			System.out.println("File I/O Error");
		}

		setTitle("2�ο� ����");
		ImageIcon img = new ImageIcon("image\\icon.png");
		setIconImage(img.getImage());
		setSize(300,260);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		addWindowListener(this);
		
		setMainScreen(wholeTime);
		setResult(wholeTime);
	}
	
	public void setMainScreen(String wholeTime)	{
	contentPane.setLayout(null);
	contentPane.setBackground(Color.white);
	
	gameEnd.setText("���� ����");
	gameEnd.setFont(new Font("���� ���", Font.PLAIN, 40));
	gameEnd.setSize(200, 60);
	gameEnd.setLocation(54, 20);
	contentPane.add(gameEnd);
	
	gameInfo.setText("���º�");
	gameInfo.setFont(new Font("���� ���", Font.PLAIN, 25));
	gameInfo.setSize(200, 60);
	gameInfo.setLocation(105, 70);
	contentPane.add(gameInfo);

	resultTime.setText("���� �ð� : " + wholeTime);
	resultTime.setFont(new Font("���� ���", Font.PLAIN, 25));
	resultTime.setSize(300, 60);
	resultTime.setLocation(37, 110);
	contentPane.add(resultTime);
	
	exitButton.setText("Ȯ��");
	exitButton.setFont(new Font("���� ���", Font.BOLD, 10));
	exitButton.setSize(60, 30);
	exitButton.setLocation(109,175);
	exitButton.setContentAreaFilled(false);
	exitButton.setFocusPainted(false);
	exitButton.addActionListener(this);
	contentPane.add(exitButton);
	}
	
	public void setResult(String wholeTime) {
        for (int i = 0; i < 15; i++)
        	for (int j = 0; j < 15; j++)
        		resultBoard[i][j] = '��';
        		resultBoard[0][0] = '��';
        		resultBoard[0][14] = '��';
        		resultBoard[14][0] = '��';
        		resultBoard[14][14] = '��';
        for (int i = 1; i < 14; i++) {
        	resultBoard[0][i] = '��';
        	resultBoard[i][0] = '��';
        	resultBoard[14][i] = '��';
        	resultBoard[i][14] = '��';
        }
        for (int i = 0; i < 15; i++)
        	for (int j = 0; j < 15; j++) {
        		if (board[i][j] == 1)
        			resultBoard[i][j] = '��';
        		else if (board[i][j] == 2)
        			resultBoard[i][j] = '��';
        	}
        
        try {
            for(int i = 0; i < 15; i++) {
           	 for (int j = 0; j < 15; j++)
                    bw.write(resultBoard[i][j]);
                bw.newLine();
            }
            bw.write(" ���º�");
            bw.newLine();
            bw.write(" �� : " + playerName[1]);
            bw.newLine();
            bw.write(" �� : " + playerName[2]);
            bw.newLine();
            bw.write(" ���� �ð� : " + wholeTime);
            bw.close(); osw.close(); fos.close();
        } catch (IOException e) {
			System.out.println("File I/O Error");
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	
	public void windowClosed(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowActivated(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
}
