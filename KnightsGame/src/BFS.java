import java.util.ArrayList;
import java.util.Random;

public class BFS {
	/*
	 Create a variable called NODE-LIST and set it to S
	2. Until a Goal state is found or NODE-LIST is empty do:
	– Remove the first element from NODE-LIST and call it E;
	 If NODE-LIST was empty: Quit
	– For each way that each rule can match the state E do:
	 Apply the rule to generate a new state
	 If new state is a Goal state: Quit and return this state
	 Else add the new state to the end of NODE-LIST	 
	 */
	int totalVisitedNode = 0;
	public enum DIRECTION {
	    NORTH, SOUTH, EAST, WEST
	}
	int row; int col;int numPawn;
	//ArrayList<Pawn> pList;
	public BFS(int numP, int r, int c){
		numPawn = numP;
		row = r; col = c;
		//pList =  new ArrayList<Pawn>(numPawn);
	}
	
	public Node2 BFSearch(ArrayList<Pawn> pList, Knight aKnight, int turn){
		//initial node/state
		
		Node2 rootNode = new Node2(numPawn,row,col,turn, null, pList, aKnight);
//		rootNode.printlist();
		
//		rootNode.printMap(rootNode.knight);
		Node2 currNode = rootNode;
		ArrayList<Node2> fringeList = new ArrayList<Node2>();
		ArrayList<Node2> childList;
		fringeList.add(rootNode);
		Node2 parentNode = null;
		ArrayList<Node2> visitedNode = new ArrayList<Node2>();
		
//		System.out.println("pList.get(0).toString(): "+pList.get(0).toString());
//		System.out.println("pList.get(1).toString(): "+pList.get(1).toString());

		while(!currNode.isAllEaten() || fringeList.isEmpty()){
			currNode = fringeList.get(0);
//			System.out.println("\n Turn: " + currNode.nTurn);
			visitedNode.add(currNode);
			fringeList.remove(0);
			totalVisitedNode++;
			childList = new ArrayList<Node2>();
			childList.addAll(childNodes(currNode.nTurn,currNode.pList,currNode.knight, currNode));

//			currNode.printMap(currNode.knight);
//			currNode.printlist();
//			System.out.println("Knight: " + currNode.knight.toString());
//			System.out.println("fringelist.size(): " + fringeList.size());	
			
			for(int i =0 ; i <childList.size();i++){
//				childList.get(i).printlist();
//				System.out.println("i " + i);
				if(childList.get(i).isAllEaten()){
//					System.out.println("childList.get("+i+").toString(): " + childList.get(i).toString());
//					System.out.println("foundNode");
					return childList.get(i);
				}
				else if(!isVisitedNode(visitedNode,childList.get(i))){
					fringeList.add(childList.get(i));
				}
			}
			if(fringeList.isEmpty()){
				break;
			}
		}
		
		return null;
	}
	public ArrayList<Pawn> noRef( ArrayList<Pawn> aList){
		ArrayList<Pawn> tList = new ArrayList<Pawn>();
		for(int ii = 0; ii<aList.size();ii++){
			Pawn temp = new Pawn();
			temp.eaten = aList.get(ii).eaten;
			temp.pCol = aList.get(ii).pCol;
			temp.pRow = aList.get(ii).pRow;
			temp.pDirection = aList.get(ii).pDirection;
			temp.pawnID = aList.get(ii).pawnID;
			tList.add(temp);
		}
		return tList;
	}
	public ArrayList<Node2> childNodes(int turn, ArrayList<Pawn> aList, Knight tKnight, Node2 parentNode){
		ArrayList<Pawn> tpList = new ArrayList<Pawn>();
		tpList = noRef(aList);
		
		Knight aKnight = new Knight(0,0);
		ArrayList<Node2> aNodeList = new ArrayList<Node2>();
		Node2 aNode;
		aKnight = tKnight;
		
		if(checkBound(-1,-2, aKnight.kRow, aKnight.kCol)){

//			System.out.println("in checkBound ");
			aKnight = updatePosition(-1,-2,aKnight);
			tpList = checkEat(aKnight,tpList);
			tpList = new ArrayList<Pawn>(updatePawn(turn, tpList));
			tpList = checkEat(aKnight,tpList);
			int tTurn = turn + 1;
			aNode = new Node2(tpList.size(),row,col,tTurn,parentNode,tpList,aKnight);
//			aNode.printlist();
			aNodeList.add(aNode);
//			System.out.println("aNode.toString(): " + aNode.toString());
		}
		if(checkBound(-2,-1, tKnight.kRow,tKnight.kCol)){
			tpList = new ArrayList<Pawn>(); tpList = noRef(aList);
			
//			System.out.println("aList.size(): " + aList.size()+"\n");
//			System.out.println("aList.get(0).toString(): " + aList.get(0).toString()+"\n");
//			System.out.println("in checkBound ");
			aKnight = updatePosition(-2,-1,tKnight);
			tpList = checkEat(aKnight,tpList);
			tpList = updatePawn(turn, tpList);
			tpList = checkEat(aKnight,tpList);
			int tTurn = turn + 1;
			aNode = new Node2(tpList.size(),row,col,tTurn,parentNode,tpList,aKnight);
//			aNode.printlist();
			aNodeList.add(aNode);
			
//			System.out.println("aNode.toString(): " + aNode.toString());
		}
		if(checkBound(-2,1, tKnight.kRow,tKnight.kCol)){
			tpList = new ArrayList<Pawn>(); tpList = noRef(aList);
//			System.out.println("aList.get(0).toString(): " + aList.get(0).toString()+"\n");
			aKnight = updatePosition(-2,1, tKnight);
			tpList = checkEat(aKnight,tpList);
			tpList = updatePawn(turn, tpList);
			tpList = checkEat(aKnight,tpList);
			int tTurn = turn + 1;
			aNode = new Node2(tpList.size(),row,col,tTurn,parentNode,tpList,aKnight);
			aNodeList.add(aNode);
			//aNode.printlist();
//			System.out.println("aNode.toString(): " + aNode.toString());
		}
		if(checkBound(-1,2, tKnight.kRow,tKnight.kCol)){
			tpList = new ArrayList<Pawn>(); tpList = noRef(aList);
			aKnight = updatePosition(-1,2, tKnight);
			tpList = checkEat(aKnight,tpList);
			tpList = updatePawn(turn, tpList);
			tpList = checkEat(aKnight,tpList);
			int tTurn = turn + 1;
			aNode = new Node2(tpList.size(),row,col,tTurn,parentNode,tpList,aKnight);
			aNodeList.add(aNode);
			//aNode.printlist();
//			System.out.println("aNode.toString(): " + aNode.toString());
		}
		if(checkBound(1,-2, tKnight.kRow,tKnight.kCol)){
			tpList = new ArrayList<Pawn>(); tpList = noRef(aList);
			aKnight = updatePosition(1,-2, tKnight);
			tpList = checkEat(aKnight,tpList);
			tpList = updatePawn(turn, tpList);
			tpList = checkEat(aKnight,tpList);
			int tTurn = turn + 1;
			aNode = new Node2(tpList.size(),row,col,tTurn,parentNode,tpList,aKnight);
			aNodeList.add(aNode);
			//aNode.printlist();
//			System.out.println("aNode.toString(): " + aNode.toString());
		}
		if(checkBound(2,-1, tKnight.kRow,tKnight.kCol)){
			tpList = new ArrayList<Pawn>(); tpList = noRef(aList);
			aKnight = updatePosition(2,-1, tKnight);
			tpList = checkEat(aKnight,tpList);
			tpList = updatePawn(turn, tpList);
			tpList = checkEat(aKnight,tpList);
			int tTurn = turn + 1;
			aNode = new Node2(tpList.size(),row,col,tTurn,parentNode,tpList,aKnight);
			aNodeList.add(aNode);
			//aNode.printlist();
//			System.out.println("aNode.toString(): " + aNode.toString());
		}
		
		if(checkBound(2,1, tKnight.kRow,tKnight.kCol)){
			tpList = new ArrayList<Pawn>(); tpList = noRef(aList);
			aKnight = updatePosition(2,1, tKnight);				
			tpList = checkEat(aKnight,tpList);
			tpList = updatePawn(turn, tpList);
			tpList = checkEat(aKnight,tpList);
			int tTurn = turn + 1;
			aNode = new Node2(tpList.size(),row,col,tTurn,parentNode,tpList,aKnight);
			aNodeList.add(aNode);
			//aNode.printlist();
//			System.out.println("aNode.toString(): " + aNode.toString());
		}
		if(checkBound(1,2, tKnight.kRow,tKnight.kCol)){
			tpList = new ArrayList<Pawn>(); tpList = noRef(aList);
			aKnight = updatePosition(1,2, tKnight);
			tpList = checkEat(aKnight,tpList);
			tpList = updatePawn(turn, tpList);
			tpList = checkEat(aKnight,tpList);
			int tTurn = turn + 1;
			aNode = new Node2(tpList.size(),row,col,tTurn,parentNode,tpList,aKnight);
			aNodeList.add(aNode);
			//aNode.printlist();
//			System.out.println("aNode.toString(): " + aNode.toString());
		}
		return aNodeList;
	}

	public ArrayList<Pawn> updatePawn(int turn,ArrayList<Pawn> pList){
		//start in turn 0 when actually move change to 1
		//mod 0 is move in the directed position
		//mod 1 is reverse
		for(int i = 0;i<pList.size();i++){
			if(pList.get(i).isEatenP()){//means knight can go back and be still in a differnt node/state
				pList.get(i).pRow = -1;
				pList.get(i).pCol = -1;
			}else if(turn%2==0){
				switch(pList.get(i).pDirection){
					case NORTH:pList.get(i).pRow = pList.get(i).pRow - 1;
						break;
					case SOUTH:pList.get(i).pRow = pList.get(i).pRow + 1;
						break;
					case EAST:pList.get(i).pCol = pList.get(i).pCol + 1;
						break;
					case WEST:pList.get(i).pCol = pList.get(i).pCol - 1;
						break;
				}
			}else{
				switch(pList.get(i).pDirection){
					case NORTH:pList.get(i).pRow = pList.get(i).pRow + 1;
						break;
					case SOUTH:pList.get(i).pRow = pList.get(i).pRow - 1;
						break;
					case EAST:pList.get(i).pCol = pList.get(i).pCol - 1;
						break;
					case WEST:pList.get(i).pCol = pList.get(i).pCol + 1;
						break;
				}
			}
		}
		return pList;
	}
	
	public Knight updatePosition(int length, int height,Knight aKnight){
		Knight knight = new Knight(0,0);
		if((aKnight.kRow + height >=0 && aKnight.kRow + height < row) && 
				(aKnight.kCol + length >=0 && aKnight.kCol + length< col)){
			knight.kRow = aKnight.kRow + height;
			knight.kCol = aKnight.kCol + length;
			return knight;
//			System.out.println("updated kRow " + aKnight.kRow);
//			System.out.println("updated kCol " + aKnight.kCol);
		}
		return null;
	}
	
	public ArrayList<Pawn> checkEat(Knight aKnight, ArrayList<Pawn> pList){
//		System.out.println("pList.size() " + pList.size());
		for(int i = 0;i<pList.size();i++){
//			System.out.println("aKnight.kRow " + aKnight.kRow + " aKnight.kCol " + aKnight.kCol);
//			System.out.println("pList.get(i).pRow " + pList.get(i).pRow + " pList.get(i).pCol " + pList.get(i).pCol);			
			if(aKnight.kRow == pList.get(i).pRow && aKnight.kCol == pList.get(i).pCol){
				pList.get(i).eaten = true;
//				System.out.println("eaten");
			}
		}
		return pList;
	}

	
	public boolean existPawnCord(int row, int col, ArrayList<Pawn> pList){
		for(int j = 0;j<pList.size();j++){
			if(pList.get(j).pRow == row && pList.get(j).pCol == col){
				return true;
			}
		}
		return false;
	}
	
	public boolean checkBound(int length, int height, int pieceRow, int pieceCol){
		
		if(((pieceRow + height) >=0 && (pieceRow + height)< row && (pieceRow + height) != row) && 
				((pieceCol + length) >=0 && (pieceCol + length) < col && (pieceCol + length) != col)){
			return true;
		}else{
			return false;
		}
	}
	public boolean isVisitedNode(ArrayList<Node2> nodeList,Node2 aNode){
		for(int i =0; i<nodeList.size();i++){
			if(isSameNode(nodeList.get(i),aNode)){
				return true;
			}
		}
		return false;
	}
	public boolean isSameNode(Node2 node1, Node2 node2){
		if(node1.knight.kCol == node2.knight.kCol && node1.knight.kRow == node2.knight.kRow && node1.pNum == node2.pNum && node1.allEaten == node2.allEaten){
			for(int i = 0; i< node1.pNum; i ++){
				if(node1.pList.get(i).pCol != node2.pList.get(i).pCol || node1.pList.get(i).pRow != node2.pList.get(i).pRow 
						|| node1.pList.get(i).isEatenP() != node2.pList.get(i).isEatenP()){
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
