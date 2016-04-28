import java.util.ArrayList;
import java.util.Random;

public class Node {
	//Info
	int turn;
	
	ArrayList<Pawn> pList;
	public enum DIRECTION {
	    NORTH, SOUTH, EAST, WEST
	}
	
	static int numP;
	int kRow;
	int kCol;
	static boolean eaten = false;
	
	//state
	int row = 5;
	int col = 5;
	String [ ] [ ] grid; //row | column
	
	public Node(int currTurn, int pNum, int gridR, int gridC){
		pList = new ArrayList<Pawn>(pNum);
		turn = currTurn;
		numP = pNum;
		row = gridR;
		col = gridC;
		grid  = new String [ row ] [ col ] ;
		genPawn(numP);
		printPawnCord();
		Random rn = new Random();
		int rowNum;int colNum;
		while(true){
			rowNum = rn.nextInt(row);
			colNum = rn.nextInt(col);
			if(!existPawnCord(rowNum,colNum)){
				break;
			}
		}
		kRow = rowNum;
		kCol = colNum;
		grid[kRow][kCol]="K";
	}
	
	public void genPawn(int num){
		Random rn = new Random();int rowNum;int colNum;int direct;
		for(int i =0; i<num;i++){
			//gen Pawn location
			rowNum = rn.nextInt(row);
			colNum = rn.nextInt(col);
			while(existPawnCord(rowNum, colNum)){
				//System.out.println("gen rowNum: " + rowNum);
				rowNum = rn.nextInt(row);
				colNum = rn.nextInt(col);
			}
			String p = "";boolean corrDir = false;
			while(corrDir == false){
				direct = rn.nextInt(4);
				switch(direct){
					case 0: p = DIRECTION.NORTH.toString();
							corrDir = checkBound(0,-1,rowNum,colNum);
						break;
					case 1: p = DIRECTION.EAST.toString();
							corrDir = checkBound(1,0,rowNum,colNum);
						break;
					case 2:	p = DIRECTION.SOUTH.toString();
							corrDir = checkBound(0,1,rowNum,colNum);
						break;
					case 3:p=DIRECTION.WEST.toString();
							corrDir = checkBound(-1,0,rowNum,colNum);
						break;
				}
			}
//			System.out.println("i " + i);
			Pawn aPawn = new Pawn(rowNum,colNum, p,i);
			pList.add(aPawn);
//			System.out.println("aPawn.pRow: " + aPawn.pRow + "aPawn.pCol: " + aPawn.pCol );
		}
//		System.out.println("0p " + pList.get(0).pawnID + " " + pList.get(1).pawnID);
	}
	
	public void updatePawn(int turn){
		//start in turn 0 when actually move change to 1
		//mod 0 is move in the directed position
		//mod 1 is reverse
		for(int i = 0;i<pList.size();i++){
			if(turn%2==0){
				switch(pList.get(i).pDirection){
					case NORTH:pList.get(i).pRow = pList.get(i).pRow + 1;
						break;
					case SOUTH:pList.get(i).pRow = pList.get(i).pRow - 1;
						break;
					case EAST:pList.get(i).pCol = pList.get(i).pCol + 1;
						break;
					case WEST:pList.get(i).pCol = pList.get(i).pCol - 1;
						break;
				}
			}else{
				switch(pList.get(i).pDirection){
					case NORTH:pList.get(i).pRow = pList.get(i).pRow - 1;
						break;
					case SOUTH:pList.get(i).pRow = pList.get(i).pRow + 1;
						break;
					case EAST:pList.get(i).pCol = pList.get(i).pCol - 1;
						break;
					case WEST:pList.get(i).pCol = pList.get(i).pCol + 1;
						break;
				}
			}
		}
	}
	public void updatePosition(int length, int height){
		if((kRow + height >=0 && kRow + height < row) && 
				(kCol + length >=0 && kCol + length< col)){
			kRow = kRow + height;
			kCol = kCol + length;
			System.out.println("updated kRow " + kRow);
			System.out.println("updated kCol " + kCol);
		}
	}
	//	1		5
	//2				6
	//		K
	//3				7
	//	4		8
	public void userMoveK(int moveNum){
		System.out.println("user selected " + moveNum);
		switch(moveNum){
		case 1:		System.out.println("in case "+ moveNum );
			if(checkBound(-1,-2, kRow,kCol)){
				//move success
				System.out.println("in checkBound ");
				updatePosition(-1,-2);
				checkEat();
				updatePawn(turn);
				increaseTurn();
				checkEat();
			}
			break;
		case 2:System.out.println("in case "+ moveNum );
			if(checkBound(-2,-1, kRow,kCol)){
				//move success
				System.out.println("in checkBound ");
				updatePosition(-2,-1);
				checkEat();
				updatePawn(turn);
				increaseTurn();
				checkEat();
			}
			break;
		case 3:System.out.println("in case "+ moveNum );
			if(checkBound(-2,1, kRow,kCol)){
				//move success
				updatePosition(-2,1);
				checkEat();
				updatePawn(turn);
				increaseTurn();
				checkEat();
			}
			break;
		case 4:System.out.println("in case "+ moveNum );
			if(checkBound(-1,2, kRow,kCol)){
				//move success
				updatePosition(-1,2);				
				checkEat();
				updatePawn(turn);
				increaseTurn();
				checkEat();
			}
			break;
		case 5:
			if(checkBound(1,-2, kRow,kCol)){
				//move success
				updatePosition(1,-2);				
				checkEat();
				updatePawn(turn);
				increaseTurn();
				checkEat();				
			}
			break;
		case 6:
			if(checkBound(2,-1, kRow,kCol)){
				//move success
				updatePosition(2,-1);				
				checkEat();
				updatePawn(turn);
				increaseTurn();
				checkEat();
			}
			break;
		case 7:
			if(checkBound(2,1, kRow,kCol)){
				//move success
				updatePosition(2,1);				
				checkEat();
				updatePawn(turn);
				increaseTurn();
				checkEat();				
			}
			break;
		case 8:
			if(checkBound(1,2, kRow,kCol)){
				//move success
				updatePosition(1,2);
				checkEat();
				updatePawn(turn);
				increaseTurn();
				checkEat();
			}
			break;
		default:
			break;
			
		}
		printPawnCord();
		//printMap();
	}
	public void printPawnCord(){
		String returnString = "";
		for(int j = 0;j<pList.size();j++){ //System.out.println("J" + j);
			returnString = returnString + "\n"+ pList.get(j).toString(); 
		}
		System.out.println(returnString);
	}

	public boolean existPawnCord(int row, int col){
		for(int j = 0;j<pList.size();j++){
			if(pList.get(j).pRow == row && pList.get(j).pCol == col){
				return true;
			}
		}
		return false;
	}
	
	
	public boolean isEaten(int row, int col){
		for(int ii = 0;ii<pList.size();ii++){
			if(pList.get(ii).pRow == row && pList.get(ii).pCol == col){
				if(pList.get(ii).isEatenP()){
					return true;
				}
			}
		}
		return false;
	}
	
	//			1
	//		 4__|__	2
	//			|	
	//			3
	public boolean checkBound(int length, int height, int pieceRow, int pieceCol){
		
		if(((pieceRow + height) >=0 && (pieceRow + height)< row && (pieceRow + height) != row) && 
				((pieceCol + length) >=0 && (pieceCol + length) < col && (pieceCol + length) != col)){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean areEaten(){
		for(int j = 0;j<pList.size();j++){
			if(pList.get(j).isEatenP() == false){
				return false;
			}
		}
		return true;
	}
	public void checkEat(){
		for(int i = 0;i<pList.size();i++){
			if(kRow == pList.get(i).pRow && kCol == pList.get(i).pCol){
				pList.get(i).eaten = true;
				System.out.println("eaten");
			}
		}
	}
	public void increaseTurn(){
		turn++;
	}
	public void printMap(){
		for(int i =0; i<row;i++){
			System.out.println();
			for(int j =0; j<col;j++){
				
				if(i == kRow && j == kCol ){
					grid[i][j]= "K";
				}else if(existPawnCord(i,j)){
					if(isEaten(i,j)){
						grid[i][j]="N";
					}else{
						grid[i][j]="P";
					}
				}else{
					grid[i][j] = "N";
				}
				System.out.print(" " + grid[i][j]);
			}
		}
	}

}
