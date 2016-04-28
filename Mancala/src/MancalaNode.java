
public class MancalaNode {
	int num =7;	//pitnum
	
	MancalaBoard board;
	Player player1 = new Player("Player1", 1);
	Player player2 = new Player("Player2", 2);
	int currPlayer= 0;
	int pitNumMove = Integer.MIN_VALUE;
	String direction = "";
	int currCost;
	boolean goAgain = false;
	
	int parentPitMove= Integer.MIN_VALUE;
	String parentDirection= "";

	MancalaNode(){
		board  =  new MancalaBoard(this.num);
	}
	MancalaNode(Player p1, Player p2){
		board  =  new MancalaBoard(this.num);
		player1 = copyPlayer(p1);
		player2 = copyPlayer(p2);
	}
	MancalaNode(MancalaBoard newBoard, Player p1, Player p2){
		board  =  new MancalaBoard(this.num);
		board = copyBoard(newBoard);
		player1 = copyPlayer(p1);
		player2 = copyPlayer(p2);
	}
	
	public MancalaBoard copyBoard(MancalaBoard copyBoard){
		MancalaBoard tempBoard = new MancalaBoard(this.num);
		tempBoard.numPit = copyBoard.numPit;
		tempBoard.pList = copyBoard.copyList();
		tempBoard.mancalaPerPit = copyBoard.mancalaPerPit;
		return tempBoard;
	}
	public Player copyPlayer(Player copyP){
		Player tempPlayer = new Player(copyP.name,copyP.playerNum);
		tempPlayer.isAI = copyP.isAI;
		tempPlayer.name = copyP.name;
		tempPlayer.playerNum = copyP.playerNum;
		tempPlayer.turn = copyP.turn;
		tempPlayer.heuristicType = copyP.heuristicType;
		
		return tempPlayer;
	}
}
