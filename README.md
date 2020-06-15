# Java_Team_Project

team2 #final project game_2

Project Name : Omok(오목)

Project Description : 

Game Rule : 

오목 게임은 일반 대중들에게 잘 알려져 있듯, 오목판, 흑돌, 백돌이 있고, 흑부터 시작하여 한 수씩 돌을 두어 먼저 5목을 만드는 쪽이 이기는 2인용 게임이다. 다음과 같이 일반적으로 알려져 있는 룰을 기준으로 구혀하였다.
- 흑, 백 모두 3*3(삼삼)을 금수 처리한다.
- 4*4(사사)는 흑, 백 모두 둘 수 있는 형태이다.
- 장목(6목 이상)은 흑, 백 모두 둘 수는 있되, 승패에 영향은 없다.

Flow Chart :

Omok -> MainFrame -> GameFrame -> ResultDialog
                               -> DrawDialog
            ↓↑          ↓↑
      ReStringDialog  RePutDialog
         
수업에서 배운 것 활용 : 

1. Class
- 객체 지향적인 프로그래밍을 위해 한 프로그램을 비슷한 용도의 패키지로 나누고, 여러 클래스로 세분화하였다. 
각각의 클래스들은 필요한 JFrame, JDialog, Thread 클래스를 상속받았으며, MouseListener, MouseMotionListenr, ActionListenr, WindowListener 인터페이스를 구현하였다.

2. JAVA API
- 삼삼, 사사를 검사하는 소스를 만들기 위해 ArrayList와 HashMap을 사용
- 게임 결과 저정 시, 파일 이름에 현재 날짜를 받아오기 위해 Calender 사용

3. Exception Handling
- 시간 제한 쓰레드에서 시간 제한이 끝나기 전에 돌을 놓았을 때, 쓰레드를 종료하기 위해서 예외처리를 사용하였다.
- 파일 입출력 시 예외처리를 해주었다.

4. File I/O
- 게임 결과를 TXT 파일로 저장

5. GUI
- 플레이어가 오목 게임을 하기 전의 플레이어 이름 적는 창
- 플레이어가 오목 게임을 할 때 볼 오목판과 흑, 백돌, 남은 시간
- 게임 중, 삼삼, 사사, 장목의 경우 알림창
...

6. Mouse Event
- 마우스 클릭 이벤트가 게임을 진행한다. GameFrame 클래스에서 MouseListener 클래스를 구현하였다.

7. Thread
- 돌을 두는 데 시간 제한을 둬야 했기에 이를 Thread를 이용하여 구현하였다. 
1초간 sleep을 하여 while문을 돌고 GUI에는 남은 시간 제한을 표시한다.


Description of Src Code :

1. Class Omok, MainFrame
- 게임 실행
- 사용자 이름을 입력받아 GameFrame에 전달

2. Class ReStringDialog, RePutDialog
- 이름을 다시 입력받아야 하는 상황 발생 시, 메시지 띄워줌

3. Class ResultDialog, DrawDialog
- 게임 결과 뛰워줌
- 게임 결과를 File I/O를 이용하여 txt 파일로 저장

4. Class GameFrame
String[] playerName boolean whoseTurn // true: 흑 차례, false: 백 차례
boolean isClickCorrect // GUI 상의 오목판을 적절하게 클릭했는지 판단하기 위한 변수
int blackCount, whiteCount // 놓은 돌의 수
int xPos, yPos int whatDialog // 삼삼, 사사, 장목 중 어떤 상황인지 알려주는 변수
int[] overTimeSec // 흑, 백의 제한 시간 배열
int wholeTime // 게임 진행 시간
String wholeTimeString // 게임 진행 시간의 문자열 형태
ProgressTimer progressTime // 게임 진행 시간 쓰레드
IsTimeOver blackTimer // 흑 제한 시간 쓰레드
IsTimeOver whiteTimer // 백 제한 시간 쓰레드
int[][] board // 오목판
int[][] testBoard // 삼삼, 사사, 장목을 검사하기 위한 임시 오목판
ArrayList<Integer> testArr // 삼삼, 사사를 검사할 때 필요한 배열리스트
final int[] EXACT_POS // GUI 상에서의 적절한 픽셀 값 배열
final int[] CHECK_MOK_X // {1, 0, 1, 1} 삼삼, 사사를 검사할 때 쓰임
final int[] CHECK_MOK_Y // {0, 1, -1, 1} 삼삼, 사사를 검사할 때 쓰임
기타 GUI 변수들 
GameFrame(String player1, String player2)
- 변수 초기화
- GUI 초기화
- 진행 시간 쓰레드 생성, 초기화, 실행
- 흑 제한 시간 쓰레드 실행 
void blackTimerStart()  //흑의 제한 시간 쓰레드 생성, 초기화, 실행
void whiteTimerStart()  //백의 제한 시간 쓰레드 생성, 초기화, 실행
void setxPos(int x)  -x 를 xPos에 저장
void setyPos(int y)  -y를 xPos에 저장
int getBoard(int x, int y)
- board 의 값을 받아옴
- GUI 픽셀의 가로, 세로와 이차원 배열의 행, 열이 반대여서 만듬
(return board[y][x]) void setBoard(int x, int y, int stone) 
- board 에 값을 저장
- GUI 픽셀의 가로, 세로와 이차원 배열의 행, 열이 반대여서 만듬
(board[y][x] = stone) void initTestBoard()   // testBoard 초기화
int getTestBoard(int x, int y)  //testBoard 의 값을 받아옴
void setTestBoard(int x, int y) 
- testBoard 에 1(흑)을 저장
- testBoard 는 흑의 삼삼, 사사, 장목을 검사하기 때문
void setTestArr(int idx, int x, int y)  //testArr에 적절한 값 저장
void setWhatDialog(int a)  //whatDialog 에 적절한 값 저장
int isEqual(int a, int b)  //a와 b가 같으면 0, 다르면 1 return
void mouseClicked(MouseEvent e) {   setExactPos(e.getX(), e.getY());   // 마우스의 좌표 값을 배열의 인덱스 값으로 변환
if (isClickCorrect) {   // 적절한 곳이 클릭 되었을 때, 
if (canIgnorePutImpossible(xPos, yPos)) {    // 비록 금수 자리여도 오목이 만들어져서 금수를 무시할 수 있을 때,   
drawBoard(xPos, yPos, blackCount);     // 돌을 놓는다
isFin();     // 오목이 만들어진 경우이기 때문에 종료   }
if (putPossible(xPos, yPos)) {    // 돌을 놓을 수 있을 때,  
if (whoseTurn) {     // 흑의 차례일 때,   
blackTimer.interrupt();      // 흑의 시간 제한 쓰레드 종료
blackCount++;      drawBoard(xPos, yPos, blackCount);      // 돌을 그린다     }
else {      whiteTimer.interrupt();      // 백의 시간 제한 쓰레드 종료      
whiteCount++;      
drawBoard(xPos, yPos, whiteCount);      // 돌을 그린다     }   
isFin();     // 끝날 상황인지 검사
turnNext();     // 차례를 바꾼다    }   }  } 
void setExactPos(int x, int y)
  -x, y는 클릭된 마우스의 좌표
  -오목판 안에 적절하게 클릭되었는지 검사  
  -적절히 클릭 된 값을 배열의 인덱스 값으로 변환
boolean putPossible(int x, int y)
  -x, y는 배열의 인덱스 값
  -이미 돌이 놓아져 있는지 검사
  -돌을 놓았을 때, 삼삼, 사사, 장목을 발생시키는지 검사-> 
new RePutDialog(whatDialog) void drawBoard(int x, int y, int count)
  -board 에 1(흑) 또는 2(백)를 저장
  -GUI 에 돌의 위치 표시
void isFin() 
 -오목을 만들었는지 검사-> 
new ResultDialog(whoseTurn, playerName, board, wholeTimeString)  
  -오목판의 모든 공간에 돌이 놓아졌는지 검사.  -> 
new DrawDialog(playerName, board, wholeTimeString) void turnNext() 
  -깃발 GUI 변경
  -차례를 바꿔줌
  -다음 차례 상대의 제한 시간 쓰레드 실행
boolean canIgnorePutImpossible(int x, int y)  
  -x, y는 배열의 인덱스 값
  -흑의 차례일 때, 돌을 놓았을 때 금수(돌을 놓는 것이 금지된 자리)를 무시하고 오목을   만드는지 검사
  -돌을 놓지 못하는 자리라도 돌을 놓았을 때 오목이 만들어진다면 무시할 수 있다
  -금수를 무시할 수 있으면 true, 없으면 false return.
boolean isOmok(int x, int y)  
  -x, y는 배열의 인덱스 값 
  -오목이 만들어지는지 검사
  -오목이 만들어지면 true, 아니면 false return
boolean isSixmok(int x, int y)  
  -x, y는 배열의 인덱스 값
  -장목이 만들어지는지 검사
  -장목이 만들어지면 true, 아니면 false return.
boolean isThreeThree(int x, int y)
  -x, y는 배열의 인덱스 값 
  -삼삼이 만들어지는지 검사
  -삼삼이 만들어지면 true, 아니면 false return. 
boolean isFourFour(int x, int y)  
  -x, y는 배열의 인덱스 값  
  -사사가 만들어지는지 검사
  -사사가 만들어지면 true, 아니면 false return.
int checkThree()  
  -삼삼을 검사할 때 호출되는 함수
int checkFour()  
  -사사를 검사할 때 호출되는 함수
void mouseMoved(MouseEvent E)  
  -오목판 위 적절한 위치에 커서가 있으면 손 모양 커서로 변경
void setGameScreen()  
  -변수 설정
  -GUI 설정

5. Class IsTimeOver extends Thread
-GameFrame 클래스의 내부 클래스
-60초의 시간 제한 생성
-남은 시간 제한 GUI 에 표시
-interrupt 당했을 때, 차례를 넘기고 종료
 
6. Class ProgressTimer extends Thread  
-GameFrame 클래스의 내부 클래스 
-게임 진행 시간 증가
-게임 진행 시간 GUI 에 표시
