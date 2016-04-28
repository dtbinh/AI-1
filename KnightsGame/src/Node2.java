import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Node2{
	/*
	 	its parent node (how it got spawned)
 		the action required to get to this node
	 	path cost
	 	"state" object, where this state object has the actual representation of the pawns and knights
	 
	 */
	Node2 parent;
	ArrayList<Node2> child = new ArrayList<Node2> ();
	//state
	int pNum;
	int nTurn = 0;
	int row;
	int col;
	//A*
	int hCost =0;
	public enum DIRECTION {
	    NORTH, SOUTH, EAST, WEST
	}
	boolean allEaten = false;
	String [ ] [ ] grid; //row | column
	ArrayList<Pawn> pList;
	Knight knight;
	public Node2(int numP, int gRow, int gCol){
		grid  = new String [ row ] [ col ] ;
		pList = new ArrayList<Pawn>();
		row = gRow;
		col = gCol;
	}
	public Node2(int numP, int gRow, int gCol, int turn, Node2 aNode, ArrayList<Pawn> aList, Knight aKnight){
		pNum = numP;row = gRow;col = gCol;nTurn = turn;
		grid  = new String [ row ] [ col ] ;
		pList = new ArrayList<Pawn>(pNum);
		pList = aList;knight = aKnight;parent= aNode;
	}
	public Node2(int numP, int gRow, int gCol, int turn, Node2 aNode, ArrayList<Pawn> aList, Knight aKnight, int cost){
		pNum = numP;row = gRow;col = gCol;nTurn = turn;
		grid  = new String [ row ] [ col ] ;
		pList = new ArrayList<Pawn>(pNum);
		pList = aList;knight = aKnight;parent= aNode;hCost = cost;
	}
	public void setHCost(int num){
		hCost = num;
	}
	
	//for a node
	//heuristic will be the total distance knight is from all the pawn
	public int genHeuristic(){
		//heurisic
		int total = 0; int secHeurCost = 0;
		double tempH = 0;
		for(int i = 0;i<pList.size();i++){
			//only add the cost of getting pawn that are not already eaten
			if(pList.get(i).eaten==false){
				secHeurCost = secHeurCost +1;		//second heuristic total num of pawn that is eaten or not
				tempH = tempH + (distanceFormula(pList.get(i).pCol,pList.get(i).pRow,knight.kCol,knight.kRow)/3);
			}else if(pList.get(i).eaten==true){
				secHeurCost = secHeurCost -1;
			}
		}

		total = (int) (Math.round(tempH) + secHeurCost)/2;
		total = total + nTurn;
//		
//		total = (int) (nTurn + Math.round(tempH)) + secHeurCost;
		return total;
	}
	
	public double distanceFormula(int col1,int row1,int col2,int row2){
		double distance = 0;
		distance = Math.sqrt(((col2-col1)*(col2-col1))+(row2-row1)*(row2-row1));
		//Math.round(double) 
		return distance;
	}
	
	public void setPawnList(ArrayList<Pawn> aList){
		pList = aList;
	}
	public void printlist(){
		System.out.println("\n START PrintList");
		for(int i =0; i<pList.size();i++){
			System.out.println(pList.get(i).toString());
		}
		System.out.println("END PrintList \n");
	}
	
	public boolean isAllEaten(){
		boolean temp = true;
		for(int j = 0;j<pList.size();j++){
//			System.out.println("check isAllEaten: "+pList.get(j).toString());
			if(pList.get(j).isEatenP() == false){
				temp = false;
			}
		}
		return temp;
	}
	public void checkAllEaten(){
		boolean temp = true;
		for(int j = 0;j<pList.size();j++){
			if(pList.get(j).isEatenP() == false){
				temp = false;
			}
		}
		allEaten = temp;
	}
	
	public void printMap(Knight aKnight){
		for(int i =0; i<row;i++){
			System.out.println();
			for(int j =0; j<col;j++){
				
				if(i == aKnight.kRow && j == aKnight.kCol ){
					grid[i][j]= "K";
				}else if(existPawnCord(i,j)){
					if(isEaten(i,j)){
						grid[i][j]="_";
					}else{
						grid[i][j]="P";
					}
				}else{
					grid[i][j] = "_";
				}
				System.out.print(" " + grid[i][j]);
			}
		}
	}
	
	public Knight genKnight(){
		Knight aknight;
		//gen Knight coord
		Random rn = new Random();
		int rowNum;int colNum;
		while(true){
			rowNum = rn.nextInt(row);
			colNum = rn.nextInt(col);
			if(!existPawnCord(rowNum,colNum)){
				break;
			}
		}
		aknight = new Knight(rowNum, colNum);

		return aknight;
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
	
	public ArrayList<Pawn> genPawn(int num){
		ArrayList<Pawn> pList = new ArrayList<Pawn>();
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
			Pawn aPawn = new Pawn(rowNum,colNum, p,i);
			pList.add(aPawn);
//			System.out.println("aPawn.toString(): " + aPawn.toString());
		}
//		System.out.println("0p " + pList.get(0).pawnID + " " + pList.get(1).pawnID);
		return pList;
	}
	
	public boolean checkBound(int length, int height, int pieceRow, int pieceCol){	
		if(((pieceRow + height) >=0 && (pieceRow + height)< row && (pieceRow + height) != row) && 
				((pieceCol + length) >=0 && (pieceCol + length) < col && (pieceCol + length) != col)){
			return true;
		}else{
			return false;
		}
	}
	public String hCostString(){
		return " nTurn: " + nTurn + " hCost: " + hCost;
	}
	public String toString(){
		String temp = "";
		for(int i =0; i <pList.size();i++)
			temp = pList.get(i).toString() + "\n";
		return "allEaten: " + allEaten + " nTurn: " + nTurn + " hCost: " + hCost + knight.toString()+ "\n" + temp;
	}
	
	
	
}
