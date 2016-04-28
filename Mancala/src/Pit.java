
public class Pit {
	boolean well = false;
	int stones = 0;
	Pit(){
		this.stones = 0;
	}
	Pit (int num){
		this.stones = num;
	}
	Pit(boolean isWell){
		this.well = isWell;
		this.stones = 0;
	}
	Pit(int num, boolean isWell){
		this.well = isWell;
		this.stones = num;
	}
	public void addStones(int numStones){
		this.stones = this.stones + numStones;
	}
	public boolean isEmpty(){
		if(this.stones==0)
			return true;
		else
			return false;
	}
	public int removeStones(){
		int temp = this.stones;
		this.stones = 0;
		return temp;
	}
	
//	public Pit clone(){
//		Pit tempPit = new Pit();
//		tempPit.well = this.well;
//		tempPit.stones = this.stones;
//		return tempPit;
//	}
}
