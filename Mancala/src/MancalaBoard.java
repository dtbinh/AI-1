import java.util.ArrayList;

public class MancalaBoard {
	Pit [][] pList;
	
	int numPit = 0; 
	int mancalaPerPit = 5;
	int totalHeuristic = 0;
	
	public Pit[][] copyList(){
		Pit[][] newList = new Pit[3][numPit];
		for(int i = 1;i<3;i++){
			for(int j = 0 ; j< numPit;j++){
//				System.out.println("pList[i][j].stones: " + pList[i][j].stones);
				newList[i][j] = new Pit(pList[i][j].stones, pList[i][j].well);
			}
		}
		return newList;
	}
	
	MancalaBoard(int playerPit){

		numPit = playerPit+2;	// doesnt account pit for player
		pList = new Pit[3][numPit];
		
		//    0 1 2 3 		//playerPit =3 so total size is 5
		//   W1 P P P W1
		//   W2 P P P W2
		for  (int  pitNum  =  0;  pitNum  <  numPit; pitNum++){
			if(pitNum == 0){
				pList[1][pitNum] = new Pit(true);
				pList[2][pitNum] = new Pit(true);
						
//				p1List[pitNum]  =  new Pit(mancalaPerPit,true);
//				p2List[pitNum] = new Pit(mancalaPerPit);
			}else if(pitNum == (numPit-1)){
				pList[1][pitNum] = new Pit(true);
				pList[2][pitNum] = new Pit(true);
				
//				p1List[pitNum] = new Pit(mancalaPerPit);
//				p2List[pitNum] =  new Pit(mancalaPerPit,true);
			}else{
				pList[1][pitNum] = new Pit(mancalaPerPit);
				pList[2][pitNum] = new Pit(mancalaPerPit);
				
//				p1List[pitNum] = new Pit(mancalaPerPit);	
//				p2List[pitNum] = new Pit(mancalaPerPit);				
			}

		}
	}
	public boolean isGoal(){
		//ends game if no stone left in one players pit
		int numStone1 = 0; int numStone2 = 0;
		for(int i = 0; i< numPit;i++){
//			System.out.println(pList[1][i].well);
			if(!pList[1][i].well){
				numStone1 = numStone1 + pList[1][i].stones;
			}
			if(!pList[2][i].well){
				numStone2 = numStone2 + pList[2][i].stones;
			}
		}
		if(numStone1==0 || numStone2==0){
			return true;
		}
		
		return false;
	}
	
	public void showBoard(){
		String line1 = "";
		String line2 = "";

		int total1 = pList[1][0].stones + pList[1][(numPit-1)].stones; 
		int total2 = pList[2][0].stones + pList[2][(numPit-1)].stones; 
		
		for(int i = 0; i<numPit;i++){
			if(i==0 || i==(numPit-1)){
				line1 = line1 + "P1 W" + total1 + "  ";
				line2 = line2 + "P2 W" + total2 + "  ";
			}else{
				line1 = line1 + pList[1][i].stones + "  ";
				line2 = line2 + pList[2][i].stones + "  ";
			}
		}
		System.out.println("\n");
		for(int i = 0;i<((numPit+2)*2+13);i++){
			System.out.print("-");
		}
		System.out.println("\n   DisplayBoard");
		System.out.println("     " + line1 + "\n"+ "     " + line2);
		for(int i = 0;i<((numPit+2)*2+13);i++){
			System.out.print("-");
		}
		System.out.print("\n");
	}
	public int countCurrentScore(int PlayerID){
		int total = 0;
		total = pList[PlayerID][numPit-1].stones + pList[PlayerID][0].stones;
		return total;
	}
	
	public int closingGameCount(int PlayerID){
		//adds all the mancala into the players well
		int total = 0;
		for(int i =0;i<numPit;i++){
			total = total + pList[PlayerID][i].stones;
		}
		return total;
	}
	
	int switchPlayerID(int id){
		if(id==1){
			return 2;
		}
		else{
			return 1;
		}
	}
	
	public boolean move(int playerNum, int num, String direction){
		
		int totalStone= 0;
		totalStone = pList[playerNum][num].removeStones();
		int pitNum = num;
		
		//can only be 1 or 2
		int playerPit = playerNum;
		
		while(totalStone!=0){
//			System.out.println("currentPlayerNum = " + playerPit);
//			System.out.println("DIRECTION: " + direction);
//			System.out.println("PitNum: " + pitNum);
//			System.out.println(direction=="RIGHT");
			
//			System.out.println("\nShow current Progress: ");
//			showBoard();
			
			if(direction.equals("LEFT")){pitNum--;}
			else if(direction.equals("RIGHT")){ pitNum++;}
			
			if(pitNum<0){
				direction = "RIGHT";
				pitNum = 1;
				playerPit = switchPlayerID(playerPit);
			}else if(pitNum >=numPit){
				direction = "LEFT";
				pitNum = numPit-2;
				playerPit = switchPlayerID(playerPit);
			}
			
			totalStone--;
			
			if(pitNum ==0 || pitNum == (numPit-1)){
				pList[playerNum][pitNum].addStones(1);	// the wells
				if(totalStone == 0)
					return true;
			}else{
				//remove stone from opponent and add it
				if(playerPit == playerNum && pList[playerPit][pitNum].stones <= 0 && totalStone == 0){
					int tempPlayerID = switchPlayerID(playerPit);
					pList[playerPit][pitNum].addStones(pList[tempPlayerID ][pitNum].removeStones());
				}
				pList[playerPit][pitNum].addStones(1);
			}
		}
//		System.out.println("\nShow current Progress: ");
//		showBoard();
		if(pitNum ==0 || pitNum == (numPit-1)){
			return true;	//go again
		}

		return false;
	}
	
	public MancalaBoard copyBoard(MancalaBoard copyBoard){
		int num = 0; num = copyBoard.numPit-2;
		MancalaBoard board = new MancalaBoard(num);
		board.numPit = copyBoard.numPit;
		board.pList = copyBoard.pList.clone();
		board.mancalaPerPit = copyBoard.mancalaPerPit;
		return board;
	}
	
	public int genHeuristicOpponent(String direction, int pitNum,int PlayerID){
		
		return 0;
		
	}
	
	public int genHeuristic(int PlayerID,int heuristicType){
		// 1) get into own well
		//		- for every mancala placed into opponents pit increase heuristic
		// 		- 
		// 2) tries to grab other person's mancala (Max)
		//		- for every mancala taken minus heuristic
		// decide if can go again
		
		
		int totalHeuristic = 0; int stonesPit = 0; int stonesWell = 0; int stoneOtherPit = 0; int stoneOtherWell = 0;
		int otherPlayerID = switchPlayerID(PlayerID);
		for(int i = 0; i<numPit;i++){
			//more stone in pit vs opponent and stones in the well
			if(pList[PlayerID][i].well){
				stonesWell = stonesWell + pList[PlayerID][i].stones;
				stoneOtherWell = stoneOtherWell +  pList[otherPlayerID][i].stones;
			}else{
				stonesPit = stonesPit + pList[PlayerID][i].stones;
				stoneOtherPit = stoneOtherPit +  pList[otherPlayerID][i].stones;				
			}
		}
		if(heuristicType ==2){
			//always choose one that gives you more stone in your pit and well than opponents
			//add weights on having more stones in own side of pit than opponents
//			totalHeuristic = (int) (((stonesWell - stoneOtherWell)*0.4 + (stonesPit - stoneOtherPit)*0.6)*100);
			//focus on increasing Pits
			if((stonesPit - stoneOtherPit) >= 0)	// you have more stone in pit than opponent, time to put it in the well
				totalHeuristic = (int) (((stonesWell*0.7) +( stonesPit*0.3))*100);
			else
				//else focus on increase stone in own pit
				totalHeuristic = (int) (((stonesWell*0.4) + (stonesPit*0.6))*100);
			return totalHeuristic;
		}else if(heuristicType == 1 ){
			if((stonesPit- stoneOtherPit) <= 0)	// you have less stones in pit than opponent, time to try and steal
				totalHeuristic = (int) (((stonesWell*0.3) + (stonesPit *0.7))*100);	//just considers maxing stones in own pit and well
			else
				//else focus on putting it into your well
				totalHeuristic = (int) (((stonesWell*0.6) + (stonesPit *0.4))*100);	
			return totalHeuristic;
		}
		System.out.println("ISSUE");
		return totalHeuristic;
	}

}
