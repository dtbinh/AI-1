import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player {
	int deply = 6;
	boolean minMax = true;
	boolean debug = false;
	String name = "Player";
	int playerNum = 0;
	boolean isAI = false;
	int turn = 0;
	int score = 0;
	int nodeVisited = 0;
	int heuristicType = 1;
	public void setHeuristicType(int num){
		this.heuristicType = num;
	}
	public void setDepth(int num){
		this.deply = num;
	}
	Player(String pName, int num){
		this.name = pName;
		this.playerNum = num;
	}
	Player(String pName, int num, boolean AI, int hType){
		this.name = pName;
		this.playerNum = num;
		isAI = AI;
		heuristicType = hType;
	}
	public void setAI(){
		isAI = true;
	}
	public String decideMove(MancalaNode node){
		String output = "";
		if(!isAI){		//Let Player decide
			Scanner scan = new Scanner(System.in);
			System.out.println("Hello "+ name + " please choose which pit you want to move starting with 1:");
			int s = scan.nextInt();
			System.out.println("Which direction do you want to go 0(Left) or 1 (Right): ");
			int s2 = scan.nextInt();
			
			switch(s2){
				case 0:
					return "LEFT " + s;
				case 1:
					return "RIGHT " + s;
			}
		}else{
			nodeVisited = 0;
			int num = 0; num = node.board.numPit-2;
			
			System.out.println("node.currPlayer: " + node.currPlayer);
			System.out.println("Show Node board");	
//			node.board.showBoard();
			System.out.println("\n");
			MancalaNode tempNode = new MancalaNode(node.board, node.player1,node.player2);
			tempNode.currPlayer = node.currPlayer;
			if(minMax)
				return MinMaxDecision(deply,tempNode);
			else
				return AlphaBetaDecision(deply,tempNode,Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
		return "";
	}

/******************************************************************************************/
//	
//
//									Alpha Beta Bound
//	
//	
/******************************************************************************************/
	
	public String AlphaBetaDecision(int depth, MancalaNode currNode, int alpha,int beta){
		MancalaNode temp;
		//switch player for min value
		int nextPlayer = currNode.board.switchPlayerID(currNode.currPlayer);
		currNode.currPlayer = nextPlayer;
		temp = MaxAplhaValue(depth,currNode, alpha, beta);
		if(temp == null){
			return "";
		}else{
			System.out.println("MOVE FOUND: " + temp.parentDirection + " " + temp.parentPitMove);
			System.out.println("MinMax Total Visited Node : " + this.nodeVisited);
			return temp.parentDirection + " " + temp.parentPitMove;			
		}
	}
	
	//currentPlayer
	public MancalaNode MaxAplhaValue(int depth, MancalaNode currNode, int alpha,int beta){
//		System.out.println("\nMAX depth = " + depth);
		if(depth ==0){	//ends deptht max do the actual heuristicCost of the move
			MancalaNode tempNode = new MancalaNode();
			tempNode = new MancalaNode(currNode.board,currNode.player1,currNode.player2);
			tempNode.direction = currNode.direction;
			tempNode.pitNumMove = currNode.pitNumMove;
			tempNode.currPlayer = currNode.currPlayer;
			tempNode.parentDirection = currNode.parentDirection;
			tempNode.parentPitMove = currNode.parentPitMove;
			tempNode.currCost= currNode.board.genHeuristic(tempNode.currPlayer,this.heuristicType);
			return tempNode;
		}else{
			int size = currNode.board.numPit; boolean goAgain = false;
			//loop through all possible child - which are the number of pit user can choose and left or right
			ArrayList<MancalaNode> childList = new ArrayList<MancalaNode>();
			int nextPlayer = currNode.board.switchPlayerID(currNode.currPlayer);
			int v = Integer.MIN_VALUE;
			
			for(int i = 1; i< size;i++){	//only want to go through the pit not the well and numWell +2
											//	0	1	2	3  4    = 5
											//  W  Pit Pit Pit W
				//want to look through all possible pit number and direction
				if(!currNode.board.pList[nextPlayer][i].well && currNode.board.pList[nextPlayer][i].stones >0){	
					for(int j = 0; j<2;j++){
						MancalaNode futureNode = new MancalaNode();
						futureNode = new MancalaNode(currNode.board,currNode.player1,currNode.player2);
						futureNode.currPlayer= nextPlayer;
						futureNode.pitNumMove = i;
						if(j==0)
							futureNode.direction = "LEFT";
						else
							futureNode.direction = "RIGHT";
						
						this.nodeVisited = this.nodeVisited + 1;
						
						goAgain = futureNode.board.move(futureNode.currPlayer, futureNode.pitNumMove, futureNode.direction);

						if(currNode.pitNumMove == Integer.MIN_VALUE && currNode.direction.equals("")){
							futureNode.parentPitMove = futureNode.pitNumMove;
							futureNode.parentDirection = futureNode.direction;
						}else{
							futureNode.parentPitMove = currNode.parentPitMove;
							futureNode.parentDirection = currNode.parentDirection;
						}
						
						if(goAgain)
							futureNode.goAgain = true;
						if(goAgain || futureNode.board.isGoal()){
							childList.add(MaxAplhaValue(0,futureNode,alpha,beta));
						}else{
							int tempDepth = 0; tempDepth = depth -1;
							childList.add(MinBetaValue(tempDepth, futureNode,alpha,beta));
						}
						
						int newAlpha = childList.get(childList.size()-1).currCost;
						if(v<newAlpha){
							v = newAlpha;
						}
						if(v>= beta){
							return childList.get(childList.size()-1);
						}
						if(alpha < v )
							alpha = v;
					}
				}
			}

			return findMax(childList);
		}
	}
	
	//opposingPlayer
	public MancalaNode MinBetaValue(int depth, MancalaNode currNode, int alpha,int beta){
//		System.out.println("\nMin depth = " + depth);

		if(depth ==0){	//ends depth at max
			MancalaNode tempNode = new MancalaNode();
			tempNode = new MancalaNode(currNode.board,currNode.player1,currNode.player2);
			tempNode.direction = currNode.direction;
			tempNode.pitNumMove = currNode.pitNumMove;
			tempNode.currPlayer = currNode.currPlayer;
			tempNode.parentDirection = currNode.parentDirection;
			tempNode.parentPitMove = currNode.parentPitMove;
			//generate heuristic in Max Player point of view based on opponent's move 
			// so see the heuristic gen for player from opponents move and pick the move that gives smallest
			int maxPlayerView = tempNode.board.switchPlayerID(currNode.currPlayer);
			tempNode.currCost= currNode.board.genHeuristic(maxPlayerView,this.heuristicType);
			return tempNode;
			// loops through all possible child and returns the min value
			//does not recurse into maxvalue again
		}else{
			int size = currNode.board.numPit; boolean goAgain = false;
			//loop through all possible child - which are the number of pit user can choose and left or right
			ArrayList<MancalaNode> childList = new ArrayList<MancalaNode>();
			int nextPlayer = currNode.board.switchPlayerID(currNode.currPlayer);
			int v =Integer.MAX_VALUE;
			for(int i = 1; i< size;i++){	//only want to go through the pit not the well and numWell +2
											//	0	1	2	3
											//  W  Pit Pit	W
				//want to look through all possible pit number and direction
				if(!currNode.board.pList[nextPlayer][i].well && currNode.board.pList[nextPlayer][i].stones >0){
					for(int j = 0; j<2;j++){
						MancalaNode futureNode = new MancalaNode();
						futureNode = new MancalaNode(currNode.board,currNode.player1,currNode.player2);
						futureNode.currPlayer= nextPlayer;
						futureNode.pitNumMove = i;
						
						if(j==0)
							futureNode.direction = "LEFT";
						else
							futureNode.direction = "RIGHT";
						
						this.nodeVisited = this.nodeVisited + 1;
						goAgain = futureNode.board.move(futureNode.currPlayer, futureNode.pitNumMove, futureNode.direction);
						
						futureNode.parentPitMove = currNode.parentPitMove;
						futureNode.parentDirection = currNode.parentDirection;
						
						if(goAgain)
							futureNode.goAgain = true;
						if(goAgain || futureNode.board.isGoal()){
							childList.add(MinBetaValue(0,futureNode,alpha,beta));
						}else{
							int tempDepth = 0; tempDepth = depth -1;
							childList.add(MaxAplhaValue(tempDepth, futureNode,alpha,beta));
						}
						int newBeta = childList.get(childList.size()-1).currCost;
						if(v>newBeta){
							v = newBeta;
						}
						if(v<= alpha){
							return childList.get(childList.size()-1);
						}
						if(beta > v )
							beta = v;
					}
				}
			}
			return findMin(childList);
		}
	}
	
/******************************************************************************************/
//	
//
//									MIN MAX ALGORTHM
//	
//	
/******************************************************************************************/
	//ex start with player 2 AI
	public String MinMaxDecision(int depth, MancalaNode currNode){
		MancalaNode temp;
		//switch player for min value
		int nextPlayer = currNode.board.switchPlayerID(currNode.currPlayer);
		currNode.currPlayer = nextPlayer;
		temp = MaxValue(depth,currNode);
		if(temp == null){
			return "";
		}else{
//			System.out.println("MOVE FOUND: " + temp.direction + " " + temp.pitNumMove);
			System.out.println("MOVE FOUND: " + temp.parentDirection + " " + temp.parentPitMove);
			System.out.println("MinMax Total Visited Node : " + this.nodeVisited);
			return temp.parentDirection + " " + temp.parentPitMove;			
		}
	}
	
	//currentPlayer
	public MancalaNode MaxValue(int depth, MancalaNode currNode){
		if(depth ==0){	//ends deptht max do the actual heuristicCost of the move
			MancalaNode tempNode;
			tempNode = new MancalaNode(currNode.board,currNode.player1,currNode.player2);
			tempNode.direction = currNode.direction;
			tempNode.pitNumMove = currNode.pitNumMove;
			tempNode.currPlayer = currNode.currPlayer;
			
			tempNode.parentDirection = currNode.parentDirection;
			tempNode.parentPitMove = currNode.parentPitMove;
			tempNode.currCost= currNode.board.genHeuristic(tempNode.currPlayer,this.heuristicType);
//			if(currNode.currPlayer==1){
//				tempNode.currCost= currNode.board.genHeuristic(tempNode.currPlayer,currNode.player1.heuristicType);				
//			}else{
//				tempNode.currCost= currNode.board.genHeuristic(tempNode.currPlayer,currNode.player2.heuristicType);
//			}
			return tempNode;
		}else{
			int size = currNode.board.numPit; boolean goAgain = false;
			//loop through all possible child - which are the number of pit user can choose and left or right
			ArrayList<MancalaNode> childList = new ArrayList<MancalaNode>();
			int nextPlayer = currNode.board.switchPlayerID(currNode.currPlayer);
			for(int i = 1; i< size;i++){	//only want to go through the pit not the well and numWell +2
											//	0	1	2	3  4    = 5
											//  W  Pit Pit Pit W
				//want to look through all possible pit number and direction
				if(!currNode.board.pList[nextPlayer][i].well && currNode.board.pList[nextPlayer][i].stones >0){	
//					System.out.println("currNode.board.pList[" + nextPlayer+"][i].stones " + currNode.board.pList[nextPlayer][i].stones);
					for(int j = 0; j<2;j++){
						MancalaNode futureNode;
						futureNode = new MancalaNode(currNode.board,currNode.player1,currNode.player2);
						futureNode.currPlayer= nextPlayer;
						futureNode.pitNumMove = i;
						if(j==0)
							futureNode.direction = "LEFT";
						else
							futureNode.direction = "RIGHT";
						
						nodeVisited = nodeVisited + 1;
						goAgain = futureNode.board.move(futureNode.currPlayer, futureNode.pitNumMove, futureNode.direction);
						if(currNode.pitNumMove == Integer.MIN_VALUE && currNode.direction.equals("")){
//							System.out.println("in i = " + i);
							futureNode.parentPitMove = futureNode.pitNumMove;
							futureNode.parentDirection = futureNode.direction;
							if(debug)
								System.out.println("CORRECT SETTING futureNode.parentPitMove " + futureNode.parentPitMove);
						}else{
//							System.out.println("currNode.pitNumMove " + currNode.pitNumMove);
//							System.out.println("currNode.direction.equals('') " + currNode.direction.equals(""));							
							futureNode.parentPitMove = currNode.parentPitMove;
							futureNode.parentDirection = currNode.parentDirection;
							if(debug)
								System.out.println("SETTING futureNode.parentPitMove MAX" + futureNode.parentPitMove);
						}
						if(goAgain)
							futureNode.goAgain = true;
						if(goAgain || futureNode.board.isGoal()|| (depth-1)==0){
							childList.add(MaxValue(0,futureNode));
						}else{
							int tempDepth = 0; tempDepth = depth -1;
							childList.add(MinValue(tempDepth, futureNode));
						}
					}
				}
			}
			
			return findMax(childList);
		}
		
	}
	
	//opposingPlayer
	public MancalaNode MinValue(int depth, MancalaNode currNode){
//		System.out.println("\nMin depth = " + depth);

		if(depth ==0){	//ends depth at max
			MancalaNode tempNode;
			tempNode = new MancalaNode(currNode.board,currNode.player1,currNode.player2);
			tempNode.direction = currNode.direction;
			tempNode.pitNumMove = currNode.pitNumMove;
			tempNode.currPlayer = currNode.currPlayer;
			tempNode.parentDirection = currNode.parentDirection;
			tempNode.parentPitMove = currNode.parentPitMove;
			
			//generate heuristic in Max Player point of view based on opponent's move 
			// so see the heuristic gen for player from opponents move and pick the move that gives smallest
			int maxPlayerView = tempNode.board.switchPlayerID(currNode.currPlayer);
			tempNode.currCost= currNode.board.genHeuristic(maxPlayerView,this.heuristicType);
//			if(currNode.currPlayer==1){
//				tempNode.currCost= currNode.board.genHeuristic(maxPlayerView,currNode.player1.heuristicType);				
//			}else{
//				tempNode.currCost= currNode.board.genHeuristic(maxPlayerView,currNode.player2.heuristicType);
//			}
			return tempNode;
			// loops through all possible child and returns the min value
			//does not recurse into maxvalue again
		}else{
			int size = currNode.board.numPit; boolean goAgain = false;
			//loop through all possible child - which are the number of pit user can choose and left or right
			ArrayList<MancalaNode> childList = new ArrayList<MancalaNode>();
			int nextPlayer = currNode.board.switchPlayerID(currNode.currPlayer);
			for(int i = 1; i< size;i++){	//only want to go through the pit not the well and numWell +2
											//	0	1	2	3
											//  W  Pit Pit	W
				//want to look through all possible pit number and direction
//				currNode.board.showBoard();
//				System.out.println(currNode.board.pList[2][1].stones);

				if(!currNode.board.pList[nextPlayer][i].well && currNode.board.pList[nextPlayer][i].stones >0){
//					System.out.println("currNode.board.pList[" + nextPlayer+"][i].stones " + currNode.board.pList[nextPlayer][i].stones);
					for(int j = 0; j<2;j++){
						
						MancalaNode futureNode = new MancalaNode();
						futureNode = new MancalaNode(currNode.board,currNode.player1,currNode.player2);
						futureNode.currPlayer= nextPlayer;
						futureNode.pitNumMove = i;
						if(j==0)
							futureNode.direction = "LEFT";
						else
							futureNode.direction = "RIGHT";
						nodeVisited = nodeVisited + 1;
						goAgain = futureNode.board.move(futureNode.currPlayer, futureNode.pitNumMove, futureNode.direction);
						
						futureNode.parentPitMove = currNode.parentPitMove;
						futureNode.parentDirection = currNode.parentDirection;
						if(debug){
							System.out.println("SETTING currNode.parentPitMove MIN " + currNode.parentPitMove);
							System.out.println("SETTING futureNode.parentPitMove MIN " + futureNode.parentPitMove);
						}
						if(goAgain)
							futureNode.goAgain = true;
						if(goAgain || futureNode.board.isGoal()||(depth-1)==0){
							childList.add(MinValue(0,futureNode));
						}else{
							int tempDepth = 0; tempDepth = depth -1;
							childList.add(MaxValue(tempDepth, futureNode));
						}
					}
				}
			}
			return findMin(childList);
		}
	}

	public MancalaNode findMin(ArrayList<MancalaNode> list){
		MancalaNode tempNode = new MancalaNode();
		if(!list.isEmpty())
			tempNode = list.get(0);
		else
			return null;
		
		for(int i =0; i<list.size();i++){
			if(list.get(i).currCost<tempNode.currCost)
				tempNode = list.get(i);
		}
		//dont care about the child's pit num and direction only their heuristic cost at end
		//so we pend the parent direction to it
//		System.out.println("Chosen Min ");
//		System.out.println("tempNode.currPlayer: " + tempNode.currPlayer);
//		System.out.println("tempNode.pitNumMove: " + tempNode.pitNumMove);
//		System.out.println("tempNode.direction: " + tempNode.direction);
//		System.out.println("tempNode.currCost: " + tempNode.currCost);
		return tempNode;
	}
	
	public MancalaNode findMax(ArrayList<MancalaNode> list){
		MancalaNode tempNode = new MancalaNode();
		if(!list.isEmpty())
			tempNode = list.get(0);
		else
			return null;
		
		for(int i =0; i<list.size();i++){
//			list.get(i).board.showBoard();
//			System.out.println("list.get(i).currPlayer: " + list.get(i).currPlayer);
//			System.out.println("list.get(i).pitNumMove: " + list.get(i).pitNumMove);
//			System.out.println("list.get(i).direction: " + list.get(i).direction);
//			System.out.println("list.get(i).currCost: " + list.get(i).currCost);
			if(list.get(i).currCost>tempNode.currCost)
				tempNode = list.get(i);
		}
//		System.out.println("Chosen Max ");
//		System.out.println("tempNode.currPlayer: " + tempNode.currPlayer);
//		System.out.println("tempNode.pitNumMove: " + tempNode.pitNumMove);
//		System.out.println("tempNode.direction: " + tempNode.direction);
//		System.out.println("tempNode.currCost: " + tempNode.currCost);
		return tempNode;
	}
	
	
}
