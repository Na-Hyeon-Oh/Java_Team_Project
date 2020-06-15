package FrameScreen;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import DialogScreen.*;

/**	
 * @author �輮
 * ������ ���� �Ǿ��� �� �����Ǵ� Ŭ����.
 * ������ ����ȴ�.
 */
public class GameFrame extends JFrame implements MouseListener, MouseMotionListener {

	/**	
	 * player1, player2�� �̸� ����.
	 */
	private String[] playerName = new String[3];
	/**	
	 * true: �� ����, false: �� ����.
	 */
	private boolean whoseTurn = true;
	/**	
	 * GUI ���� �������� �����ϰ� Ŭ���ߴ��� �Ǵ��ϱ� ���� ����.
	 */
	private boolean isClickCorrect = false;
	/**	
	 * ���� ���� ��
	 */
	private int blackCount = 0, whiteCount = 0;
	private int xPos, yPos;
	/**	
	 * ���, ���, ��� �� � ��Ȳ���� �˷��ִ� ����.
	 */
	private int whatDialog;
	/**	
	 * ��, ���� ���� �ð� �迭.
	 */
	private int[] overTimeSec = {0,60,60};
	/**	
	 * ���� ���� �ð�.
	 */
	private int wholeTime = 0;
	/**	
	 * ���� ���� �ð��� ���ڿ� ����.
	 */
	private String wholeTimeString;
	/**	
	 * ���� ���� �ð� ������.
	 */
	private ProgressTimer progressTime;
	/**	
	 * �� ���� �ð� ������.
	 */
	private IsTimeOver blackTimer;
	/**	
	 * �� ���� �ð� ������.
	 */
	private IsTimeOver whiteTimer;
	/**	
	 * ������.
	 */
	private int[][] board = new int[15][15];
	/**	
	 * ���, ���, ����� �˻��ϱ� ���� �ӽ� ������.
	 */
	private int[][] testBoard = new int[15][15];
	/**	
	 * ���, ��縦 �˻��� �� �ʿ��� �迭����Ʈ.
	 */
	private ArrayList<Integer> testArr = new ArrayList<Integer>();
	/**	
	 * GUI �󿡼��� ������ �ȼ� �� �迭.
	 */
	private final int[] EXACT_POS = {53, 77, 102, 127, 151, 177, 202, 228, 252, 276, 301, 326, 351, 376, 401};
	/**	
	 * {1, 0, 1, 1}
	 * ���, ��縦 �˻��� �� ����.
	 */
	private final int[] CHECK_MOK_X = {1, 0, 1, 1};
	/**	
	 * {0, 1, -1, 1}
	 * ���, ��縦 �˻��� �� ����.
	 */
	private final int[] CHECK_MOK_Y = {0, 1, -1, 1};

	private Container contentPane = getContentPane();
	private JLabel[] blackStone = new JLabel[113];
	private JLabel[] whiteStone = new JLabel[112];
	private JLabel playerVs = new JLabel();
	private JLabel timeLabel = new JLabel();
	private JLabel player1Name = new JLabel();
	private JLabel player1Time = new JLabel();
	private JLabel player2Name = new JLabel();
	private JLabel player2Time = new JLabel();
	private JLabel boardLabel;
	private JLabel player1Flag;
	private JLabel player2Flag;
	private JLabel player1Stone;
	private JLabel player2Stone;
	
	/**
	 * ���� �ʱ�ȭ.
	 * ���� �ð� ������ ����, �ʱ�ȭ, ����.
	 * �� ���� �ð� ������ ����.
	 * @param player1 player1�� �̸�
	 * @param player2 player2�� �̸�
	 */
	GameFrame(String player1, String player2) {
		this.playerName[1] = player1;
		this.playerName[2] = player2;
		setTitle("2�ο� ����");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon img = new ImageIcon("image\\icon.png");
		setIconImage(img.getImage());
		setSize(700,485);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setGameScreen();
		progressTime = new ProgressTimer();
		progressTime.start();
		blackTimerStart();
	}
	
	/**
	 * ���� ���� �ð� ������ ����, �ʱ�ȭ, ����.
	 */
	public void blackTimerStart() {
		blackTimer = new IsTimeOver();
		blackTimer.setDaemon(true);
		blackTimer.start();
	}
	
	/**
	 * ���� ���� �ð� ������ ����, �ʱ�ȭ, ����.
	 */
	public void whiteTimerStart() {
		whiteTimer = new IsTimeOver();
		whiteTimer.setDaemon(true);
		whiteTimer.start();
	}
	
	/**
	 * x�� xPos�� ����.
	 * @param x xPos�� ������ ��.
	 */
	public void setxPos(int x) {
		this.xPos = x;
	}

	/**
	 * y�� xPos�� ����.
	 * @param y yPos�� ������ ��.
	 */
	public void setyPos(int y) {
		this.yPos = y;
	}

	/**
	 * board�� ���� �޾ƿ�.
	 * GUI �ȼ��� ����, ���ο� ������ �迭�� ��, ���� �ݴ뿩�� ����.
	 * @param x board�� �� ��° �ε��� ��.
	 * @param y board�� ù ��° �ε��� ��.
	 * @return board[y][x].
	 */
	public int getBoard(int x, int y) {
		return board[y][x];
	}
	
	/**
	 * board�� ���� ����.
	 * @param x board�� �� ��° �ε��� ��.
	 * @param y board�� ù ��° �ε��� ��.
	 * @param stone ���̸� 1, ���̸� 2.
	 */
	public void setBoard(int x, int y, int stone) {
		board[y][x] = stone;
	}
	
	/**
	 * testBoard �ʱ�ȭ.
	 */
	public void initTestBoard() {
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				testBoard[i][j] = 0;
	}
	
	/**
	 * testBoard�� ���� �޾ƿ�.
	 * @param x testBoard�� �� ��° �ε��� ��.
	 * @param y testBoard�� ù ��° �ε��� ��.
	 * @return testBoard[y][x].
	 */
	public int getTestBoard(int x, int y) {
		return testBoard[y][x];
	}
	
	/**
	 * testBoard�� 1(��)�� ����.
	 * testBoard�� ���� ���, ���, ����� �˻��ϱ� ����.
	 * @param x testBoard�� �� ��° �ε��� ��.
	 * @param y testBoard�� ù ��° �ε��� ��.
	 */
	public void setTestBoard(int x, int y) {
		testBoard[y][x] = 1;
	}
	
	/**
	 * testArr�� ������ �� ����
	 * @param idx testArr�� �ε��� ��.
	 * @param x testBoard�� �� ��° �ε��� ��.
	 * @param y testBoard�� ù ��° �ε��� ��.
	 */
	public void setTestArr(int idx, int x, int y) {
		testArr.add(idx,getTestBoard(x,y));
	}
	
	/**
	 * whatDialog�� ������ �� ����.
	 * @param a whatDialog�� ������ ��.
	 */
	public void setWhatDialog(int a) {
		whatDialog = a;
	}
	
	/**
	 * @param a a
	 * @param b b
	 * @return a�� b�� ������ 0, �ٸ��� 1 return.
	 */
	public int isEqual(int a, int b) {
		if (a == b)
			return 0;
		else
			return 1;
	}
	
	/**
	 * ���콺�� Ŭ���Ǿ��� ��, ���콺�� ��ǥ ���� �迭�� �ε��� ������ ��ȯ.
	 * ������ ���� Ŭ�� �Ǿ��� ��, ���� ���� �� �ִ� �ڸ����� Ȯ��.
	 * ���� ���� �� �ִٸ�, ���� ���´�.
	 * ���� ���� �� ������ ����������� Ȯ��.
	 * ���ʸ� �ѱ��.
	 */
	public void mouseClicked(MouseEvent e) {
		setExactPos(e.getX(), e.getY());
		if (isClickCorrect) {
			if (canIgnorePutImpossible(xPos, yPos)) {
				drawBoard(xPos, yPos, blackCount);
				isFin();
			}
			if (putPossible(xPos, yPos)) {
				if (whoseTurn) {
					blackTimer.interrupt();
					blackCount++;
					drawBoard(xPos, yPos, blackCount);
				} else {
					whiteTimer.interrupt();
					whiteCount++;
					drawBoard(xPos, yPos, whiteCount);
				}
				isFin();
				turnNext();
			}
		}
	}
	
	/**
	 * ������ �ȿ� �����ϰ� Ŭ���Ǿ����� �˻�.
	 * ������ Ŭ�� �� ���� �迭�� �ε��� ������ ��ȯ.
	 * @param x Ŭ���� ���콺�� x��ǥ
	 * @param y Ŭ���� ���콺�� y��ǥ
	 */
	public void setExactPos(int x, int y) {
		boolean xClickCorrect = false, yClickCorrect = false;
		for (int i = 0; i < 15; i++) {
			if (x >= EXACT_POS[i] - 12 && x <= EXACT_POS[i] + 12) {
				setxPos(i);
				xClickCorrect = true;
			}
			if (y >= EXACT_POS[i] - 12 && y <= EXACT_POS[i] + 12) {
				setyPos(i);
				yClickCorrect = true;
			}
		}
		if (xClickCorrect && yClickCorrect)
			isClickCorrect = true;
	}
	
	/**
	 * �̹� ���� ������ �ִ��� �˻�.
	 * ���� ������ ��, ���, ���, ����� �߻���Ű���� �˻�.
	 * @param x ������ �迭�� �ε��� ��.
	 * @param y ������ �迭�� �ε��� ��.
	 * @return ���� ���� �� �ִٸ� true, ���ٸ� false return.
	 */
	public boolean putPossible(int x, int y) {
		if (getBoard(x,y) != 0)
			return false;
		if (isSixmok(x,y) || isThreeThree(x,y) || isFourFour(x,y)) {
			new RePutDialog(whatDialog);
			return false;
		}
		return true;
	}
	
	/**
	 * board�� 1(��) �Ǵ� 2(��)�� ����.
	 * GUI�� ���� ��ġ ǥ��.
	 * @param x ������ �迭�� �ε��� ��.
	 * @param y ������ �迭�� �ε��� ��.
	 * @param count ���� ���� �� ��° �������� �˷��ִ� ����.
	 */
	public void drawBoard(int x, int y, int count) {
		if (whoseTurn) {
			setBoard(x,y,1);
			blackStone[count].setLocation(EXACT_POS[x] - 13, EXACT_POS[y] - 13);
			blackStone[count].setVisible(true);
		} else {
			setBoard(x,y,2);
			whiteStone[count].setLocation(EXACT_POS[x] - 13, EXACT_POS[y] - 13);
			whiteStone[count].setVisible(true);
		}
	}
	
	/**
	 * ������ ��������� �˻�.
	 * �������� ��� ������ ���� ���������� �˻�.
	 */
	public void isFin() {
		if (isOmok(xPos, yPos)) {
			progressTime.interrupt();
			setVisible(false);
			new ResultDialog(whoseTurn, playerName, board, wholeTimeString);
		}
		if (blackCount == 113) {
			progressTime.interrupt();
			setVisible(false);
			new DrawDialog(playerName, board, wholeTimeString);
		}
	}
	
	/**
	 * ��� GUI ����.
	 * ���ʸ� �ٲ���.
	 * ���� ���� ����� ���� �ð� ������ ����.
	 */
	public void turnNext() {
		if (whoseTurn) {
			player1Flag.setVisible(false);
			player2Flag.setVisible(true);
			whoseTurn = !whoseTurn;
			whiteTimerStart();
		} else {
			player1Flag.setVisible(true);
			player2Flag.setVisible(false);
			whoseTurn = !whoseTurn;
			blackTimerStart();
		}
		isClickCorrect = false;
	}

	/**
	 * ���� ������ ��, ���� ������ �� �ݼ�(���� ���� ���� ������ �ڸ�)�� �����ϰ� ������  ������� �˻�.
	 * ���� ���� ���ϴ� �ڸ��� ���� ������ �� ������ ��������ٸ� ������ �� �ִ�.
	 * @param x ������ �迭�� �ε��� ��.
	 * @param y ������ �迭�� �ε��� ��.
	 * @return �ݼ��� ������ �� ������ true, ������ false return.
	 */
	public boolean canIgnorePutImpossible(int x, int y) {
		if (whoseTurn && !isSixmok(x,y)) {
			initTestBoard();
			for (int i = 0; i < 15; i++)
				for (int j = 0; j < 15; j++)
					testBoard[i][j] = board[i][j];
			setTestBoard(x,y);
			
			int omok = 0;
			int curX = 0, curY = 0;

			for (int i = 0; i < 4; i++) {
				omok = 0;
				for (int j = -4; j < 5; j++) {
					curX = x + CHECK_MOK_X[i] * j;
					curY = y + CHECK_MOK_Y[i] * j;
					
					if (curX < 0 || curY < 0 || curX > 14 || curY > 14)
						continue;
					
					if (getTestBoard(curX,curY) == 1)
						omok++;
					else
						omok = 0;
					
					if (omok == 5)
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * ������ ����������� �˻�.
	 * @param x ������ �迭�� �ε��� ��.
	 * @param y ������ �迭�� �ε��� ��.
	 * @return ������ ��������� true, �ƴϸ� false return.
	 */
	public boolean isOmok(int x, int y) {
		int omok = 0;
		int curX = 0, curY = 0;
		int myStone;
		if (whoseTurn)
			myStone = 1;
		else
			myStone = 2;
		
		for (int i = 0; i < 4; i++) {
			omok = 0;
			for (int j = -4; j < 5; j++) {
				curX = x + CHECK_MOK_X[i] * j;
				curY = y + CHECK_MOK_Y[i] * j;
				
				if (curX < 0 || curY < 0 || curX > 14 || curY > 14)
					continue;
				
				if (getBoard(curX, curY) == myStone)
					omok++;
				else
					omok = 0;
				
				if (omok == 5)
					return true;
			}
		}
		return false;
	}

	/**
	 * ����� ����������� �˻�.
	 * @param x ������ �迭�� �ε��� ��.
	 * @param y ������ �迭�� �ε��� ��.
	 * @return ����� ��������� true, �ƴϸ� false return.
	 */
	public boolean isSixmok(int x, int y) {
		if (whoseTurn) {
			initTestBoard();
			for (int i = 0; i < 15; i++)
				for (int j = 0; j < 15; j++)
					testBoard[i][j] = board[i][j];
			setTestBoard(x,y);
			
			int sixMok = 0;
			int curX = 0, curY = 0;

			for (int i = 0; i < 4; i++) {
				sixMok = 0;
				for (int j = -4; j < 5; j++) {
					curX = x + CHECK_MOK_X[i] * j;
					curY = y + CHECK_MOK_Y[i] * j;
					
					if (curX < 0 || curY < 0 || curX > 14 || curY > 14)
						continue;
					
					if (getTestBoard(curX,curY) == 1)
						sixMok++;
					else
						sixMok = 0;
					
					if (sixMok > 5) {
						setWhatDialog(6);
						return true;
					}
				}
			}			
		}
		return false;
	}
	
	/**
	 * ����� ����������� �˻�.
	 * @param x ������ �迭�� �ε��� ��.
	 * @param y ������ �迭�� �ε��� ��.
	 * @return ����� ��������� true, �ƴϸ� false return.
	 */
	public boolean isThreeThree(int x, int y) {
		if (whoseTurn) {
			initTestBoard();
			for (int i = 0; i < 15; i++)
				for (int j = 0; j < 15; j++)
					testBoard[i][j] = board[i][j];
			setTestBoard(x,y);
			
			int numOfThree = 0;
			int curX = 0, curY = 0;

			for (int i = 0; i < 4; i++) {
				for (int j = -4; j < 5; j++) {
					curX = x + CHECK_MOK_X[i] * j;
					curY = y + CHECK_MOK_Y[i] * j;
					
					if (curX < 0 || curY < 0 || curX > 14 || curY > 14) {
						testArr.add(j + 4, 2);
						continue;
					}
					setTestArr(j + 4,curX,curY);
				}
				numOfThree += checkThree();
				
				if (numOfThree > 1) {
					setWhatDialog(3);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * ��簡 ����������� �˻�.
	 * @param x ������ �迭�� �ε��� ��.
	 * @param y ������ �迭�� �ε��� ��.
	 * @return ��簡 ��������� true, �ƴϸ� false return.
	 */
	public boolean isFourFour(int x, int y) {
		if (whoseTurn) {
			initTestBoard();
			for (int i = 0; i < 15; i++)
				for (int j = 0; j < 15; j++)
					testBoard[i][j] = board[i][j];
			setTestBoard(x,y);
			
			int numOfFour = 0;
			int curX = 0, curY = 0;

			for (int i = 0; i < 4; i++) {
				for (int j = -4; j < 5; j++) {
					curX = x + CHECK_MOK_X[i] * j;
					curY = y + CHECK_MOK_Y[i] * j;
					
					if (curX < 0 || curY < 0 || curX > 14 || curY > 14) {
						testArr.add(j + 4, 2);
						continue;
					}
					setTestArr(j + 4,curX,curY);
				}
				numOfFour += checkFour();
				
				if (numOfFour > 1) {
					setWhatDialog(4);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * ����� �˻��� �� ȣ��Ǵ� �Լ�.
	 * @return ���� ����������� 1, �ƴϸ� 0 return.
	 */
	public int checkThree() {
		int tempCount = 0;
		int[][] compArr = {{0,1,1,1,0,0}, {0,1,1,0,1,0}, {0,1,0,1,1,0}, {0,0,1,1,1,0}};
		
	    HashMap<Integer, ArrayList<Integer>> testMap = new HashMap<Integer, ArrayList<Integer>>();
	    for (int i = 0; i < 4; i++)
		    testMap.put(i, new ArrayList<Integer>(testArr.subList(i, i+6)));
	    
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				if (i != j) {
					for (int k = 0; k < 6; k++)
						tempCount += isEqual(testMap.get(i).get(k), compArr[j][k]);
					if (tempCount == 0)
						return 1;
					tempCount = 0;
				}
			}
		return 0;
	}
	

	/**
	 * ��縦 �˻��� �� ȣ��Ǵ� �Լ�.
	 * @return �簡 ����������� 1, �ƴϸ� 0 return.
	 */
	public int checkFour() {
		int tempCount = 0;
		int[][] compArr1 = {{1,1,1,0,1}, {1,1,0,1,1}, {1,0,1,1,1}};
		int[] compArr2 = {1,1,1,1};
		
	    HashMap<Integer, ArrayList<Integer>> testMap1 = new HashMap<Integer, ArrayList<Integer>>();
	    HashMap<Integer, ArrayList<Integer>> testMap2 = new HashMap<Integer, ArrayList<Integer>>();
	    for (int i = 0; i < 5; i++)
		    testMap1.put(i, new ArrayList<Integer>(testArr.subList(i, i+5)));
	    for (int i = 1; i < 5; i++)
	    	testMap2.put(i, new ArrayList<Integer>(testArr.subList(i, i+4)));
	    
	    for (int i = 0; i < 5; i++)
			for (int j = 0; j < 3; j++) {
				if (i-1 != j) {
					for (int k = 0; k < 5; k++)
						tempCount += isEqual(testMap1.get(i).get(k), compArr1[j][k]);
					if (tempCount == 0)
						return 1;
					tempCount = 0;
				}
			}
	    for (int i = 1; i < 5; i++) {
	    	for (int j = 0; j < 4; j++) {
	    		tempCount += isEqual(testMap2.get(i).get(j), compArr2[j]);
	    	}
			if (tempCount == 0)
				return 1;
			tempCount = 0;
	    }
		return 0;
	}
	
	/**
	 * @author �輮
	 * GameFrame Ŭ������ ���� Ŭ����.
	 * 60���� �ð� ���� ����.
	 * ���� �ð� ���� GUI�� ǥ��.
	 * interrupt ������ ��, ���ʸ� �ѱ�� ����.
	 */
	class IsTimeOver extends Thread {
		int turnIndex;
		public IsTimeOver() {
			if (whoseTurn)
				turnIndex = 1;
			else
				turnIndex = 2;
			overTimeSec[turnIndex] = 60;
		}
		
		public void run() {
			while (overTimeSec[turnIndex] > -1 && !isInterrupted()) {
				try {
					if (overTimeSec[1] >= 10)
						player1Time.setText("00 : " + overTimeSec[1]);
					else
						player1Time.setText("00 : 0" + overTimeSec[1]);
					
					if (overTimeSec[2] >= 10)
						player2Time.setText("00 : " + overTimeSec[2]);
					else
						player2Time.setText("00 : 0" + overTimeSec[2]);
					
					Thread.sleep(1000);
					overTimeSec[turnIndex]--;
					
					if (overTimeSec[turnIndex] < 0)
					{
						overTimeSec[turnIndex] = 60;
						turnNext();
						return;
					}
					
				} catch (InterruptedException e) {
					overTimeSec[turnIndex] = 60;
					return;
				}
			}
		}
	}
	
	/**
	 * @author �輮
	 * GameFrame Ŭ������ ���� Ŭ����
	 * ���� ���� �ð� ����.
	 * ���� ���� �ð� GUI�� ǥ��.
	 */
	class ProgressTimer extends Thread {
		public void run() {
			while(true) {
				try {
					Thread.sleep(1000);
					wholeTime++;
					int min, sec;
					min = wholeTime / 60;
					sec = wholeTime - 60 * min;
					if (min >= 10)
						if (sec >= 10)
							wholeTimeString = new String(min + " : " + sec);
						else
							wholeTimeString = new String(min + " : 0" + sec);
					else
						if (sec >= 10)
							wholeTimeString = new String("0" + min + " : " + sec);
						else
							wholeTimeString = new String("0" + min + " : 0" + sec);
					
					timeLabel.setText(wholeTimeString);
					
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}

	/**
	 * ������ �� ������ ��ġ�� Ŀ���� ������ �� ��� Ŀ���� ����.
	 */
	public void mouseMoved(MouseEvent e) {
		if (e.getX() > 40 && e.getX() < 410 && e.getY() > 40 && e.getY() < 410)
			contentPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
		else
			contentPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	/**
	 * ���� ����.
	 * GUI ����.
	 */
	public void setGameScreen() {
		contentPane.setLayout(null);
		contentPane.setBackground(Color.white);
		contentPane.addMouseListener(this);
		contentPane.addMouseMotionListener(this);
		
		for (int i = 0; i < 15; i++)
			for (int j = 0; j < 15; j++)
				board[i][j] = 0;

		ImageIcon blackStoneImage = new ImageIcon("image\\black.png");
		for (int i = 0; i < 113; i++) {
			blackStone[i] = new JLabel(blackStoneImage);
			blackStone[i].setSize(23, 23);
			contentPane.add(blackStone[i]);
			blackStone[i].setVisible(false);
			blackStone[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		ImageIcon whiteStoneImage = new ImageIcon("image\\white.png");
		for (int i = 0; i < 112; i++) {
			whiteStone[i] = new JLabel(whiteStoneImage);
			whiteStone[i].setSize(23, 23);
			contentPane.add(whiteStone[i]);
			whiteStone[i].setVisible(false);
			whiteStone[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
				
		playerVs.setText(playerName[1] + " vs " + playerName[2]);
		playerVs.setFont(new Font("���� ���", Font.PLAIN, 25));
		playerVs.setSize(250, 40);
		playerVs.setLocation(435, 30);
		playerVs.setHorizontalAlignment(playerVs.CENTER);
		contentPane.add(playerVs);
		
		timeLabel.setText("00 : 00");
		timeLabel.setFont(new Font("���� ���", Font.PLAIN, 25));
		timeLabel.setSize(250, 40);
		timeLabel.setLocation(435, 65);
		timeLabel.setHorizontalAlignment(playerVs.CENTER);
		contentPane.add(timeLabel);

		ImageIcon player1StoneImage = new ImageIcon("image\\blackbig.png");
		player1Stone = new JLabel(player1StoneImage);
		player1Stone.setSize(50, 50);
		player1Stone.setLocation(480, 130);
		contentPane.add(player1Stone);
		
		player1Name.setText(playerName[1]);
		player1Name.setFont(new Font("���� ���", Font.PLAIN, 25));
		player1Name.setSize(100, 40);
		player1Name.setLocation(540, 130);
		player1Name.setHorizontalAlignment(player1Name.CENTER);
		contentPane.add(player1Name);
		
		player1Time.setText("00 : " + overTimeSec[1]);
		player1Time.setFont(new Font("���� ���", Font.PLAIN, 25));
		player1Time.setSize(100, 40);
		player1Time.setLocation(475, 195);
		player1Time.setHorizontalAlignment(player1Time.CENTER);
		contentPane.add(player1Time);
		
		ImageIcon player2StoneImage = new ImageIcon("image\\whitebig.png");
		player2Stone = new JLabel(player2StoneImage);
		player2Stone.setSize(50, 50);
		player2Stone.setLocation(480, 270);
		player2Stone.setVisible(true);
		contentPane.add(player2Stone);
		
		player2Name.setText(playerName[2]);
		player2Name.setFont(new Font("���� ���", Font.PLAIN, 25));
		player2Name.setSize(100, 40);
		player2Name.setLocation(540, 270);
		player2Name.setHorizontalAlignment(player2Name.CENTER);
		contentPane.add(player2Name);
		
		player2Time.setText("00 : " + overTimeSec[2]);
		player2Time.setFont(new Font("���� ���", Font.PLAIN, 25));
		player2Time.setSize(100, 40);
		player2Time.setLocation(475, 335);
		player2Time.setHorizontalAlignment(player2Time.CENTER);
		contentPane.add(player2Time);
		
		ImageIcon boardImage = new ImageIcon("image\\board.png");
		boardLabel = new JLabel(boardImage);
		boardLabel.setSize(430, 430);
		boardLabel.setLocation(10, 10);
		contentPane.add(boardLabel);
		
		ImageIcon playerFlagImage = new ImageIcon("image\\flag.png");
		player1Flag = new JLabel(playerFlagImage);
		player1Flag.setSize(30, 61);
		player1Flag.setLocation(613, 188);
		player1Flag.setVisible(true);
		contentPane.add(player1Flag);

		player2Flag = new JLabel(playerFlagImage);
		player2Flag.setSize(30, 61);
		player2Flag.setLocation(613, 328);
		player2Flag.setVisible(false);
		contentPane.add(player2Flag);
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
}
