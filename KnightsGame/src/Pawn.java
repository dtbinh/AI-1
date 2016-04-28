import java.util.Random;

public class Pawn {
	public enum DIRECTION {
	    NORTH, SOUTH, EAST, WEST
	}
	DIRECTION pDirection;
	int pawnID;
	int pRow;
	int pCol;
	boolean eaten = false;
	public Pawn(){
		pawnID = 0;
		pRow = 0;
		pCol = 0;
		pDirection = DIRECTION.SOUTH;
	}
	public Pawn(int row, int col,String direction, int pID){
		pRow = row;
		pCol = col;
		if(direction=="NORTH"){
			pDirection = DIRECTION.NORTH;
		}else if(direction == "SOUTH"){
			pDirection = DIRECTION.SOUTH;
		}else if(direction == "EAST"){
			pDirection = DIRECTION.EAST;
		}else if(direction == "WEST"){
			pDirection = DIRECTION.WEST;
		}
		pawnID = pID;
		System.out.println("PAWNID " + pawnID);
	}
	public String toString(){
		return "PID: " + pawnID + " Row: " + pRow + " Col: " + pCol + " Direction: " + pDirection + " IsEaten: " + eaten;
	}
//	public void checkP(int row, int col){
//		if(pRow == row && pCol == col){
//			System.out.println("Eaten PawnID " + pawnID);
//			eaten = true;
//		}
//	}
	public boolean isEatenP(){
		return eaten;
	}
}
