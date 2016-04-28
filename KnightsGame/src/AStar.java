import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;

public class AStar {
	public enum DIRECTION {
	    NORTH, SOUTH, EAST, WEST
	}
	int totalVisitedNode = 0;
	int row; int col;int numPawn;
	public AStar(int numP, int r, int c){
		numPawn = numP;
		row = r; col = c;
	}
	
	//want to update all currNode's child with turn nd auto gen the heuristic cost
	public ArrayList<Node2> recurChild(Node2 currNode, ArrayList<Node2> visitedList){
		if(currNode.child.isEmpty()){
			return visitedList;
		}

		//look thru currNode's child if they exist in visitedList
		for(int i =0; i<currNode.child.size(); i++){
			for(int j = 0; j<visitedList.size();j++){
//				System.out.println("visitedList.size(): " + visitedList.size());
//				System.out.println("visitedList.size(): " + visitedList.size());
				
				//update the visitedList's turn and then regenerating the heuristic
//				System.out.println("Not Null visitedList.get(j): " + visitedList.get(j).hCost);
//				System.out.println("Not Null currNode.child.get(i): " + currNode.child.get(i).hCost);
				if(isSameNode(visitedList.get(j),currNode.child.get(i))){
					visitedList.get(j).nTurn = currNode.nTurn + 1;
					int tempH = visitedList.get(j).genHeuristic();
					visitedList.get(j).setHCost(tempH);
					
					visitedList = recurChild(visitedList.get(j),visitedList);
				}
			}
		}
		return visitedList;
		
	}
	public Node2 ASearch(ArrayList<Pawn> pList, Knight aKnight, int turn){
		Node2 rootNode = new Node2(numPawn,row,col,turn, null, pList, aKnight);
//		rootNode.printlist();
//		System.out.println("start");
//		rootNode.printMap(rootNode.knight);
		Node2 currNode = rootNode;
		//http://javapapers.com/java/java-priorityqueue/
		Comparator<Node2> queueComparator = new NodeComparator();
		PriorityQueue<Node2> fringeList = new PriorityQueue<Node2>(queueComparator);
		ArrayList<Node2> childList;
		fringeList.offer(rootNode);
		Node2 parentNode = null;
		ArrayList<Node2> visitedNode = new ArrayList<Node2>();
		while(!currNode.isAllEaten() || fringeList.isEmpty()){
			//pickinf best heuristic: f(n) = g(n) + h(n) //move cost plus heuristic
			//sort it in order as the child nodes are created
			//so just take the top node of the list
			currNode = fringeList.poll();
//			System.out.println("Picked currNode.hcost: " + currNode.hCost);
			visitedNode.add(currNode);
			totalVisitedNode++;
			childList = new ArrayList<Node2>();
			childList.addAll(childNodes(currNode.nTurn,currNode.pList,currNode.knight,currNode));
			
//			//Debugging
//			System.out.println("\n Turn: " + currNode.nTurn);
//			currNode.printMap(currNode.knight);
//			currNode.printlist();
//			printlistPQ(fringeList);
//			System.out.println("Knight: " + currNode.knight.toString());
//			System.out.println("currNode.hcost: " + currNode.hCost);
//			System.out.println("fringelist.size(): " + fringeList.size());	
	
			
			for(int i =0 ; i <childList.size();i++){
				if(childList.get(i).isAllEaten()){
					return childList.get(i);
				}
				else{
					ArrayList<Node2> tList = new ArrayList<Node2>(fringeList);
					if(!isVisitedNode(visitedNode,childList.get(i))&& !isVisitedNode(tList,childList.get(i))){
//						System.out.println("\n\nchildList.get(i).hCost: " + childList.get(i).hCost);
						fringeList.offer(childList.get(i));	
//						fringeList = printlistPQ(fringeList);
						currNode.child.add(childList.get(i));
//						System.out.println(" hCost: " + fringeList.peek().hCost);
//						System.out.println(" fringeList.size(): " + fringeList.size());
					}
					else{
						Node2 tNode = findNode(visitedNode,childList.get(i));
						
						//node has child of its own potentially
						if(tNode != null && (childList.get(i).hCost< tNode.hCost)){
							//means found same node in visited before
							//need to change parentNode if hCost is lower
							//need to remove all child of this node out of 
							visitedNode = updateNode(visitedNode,childList.get(i));
//							System.out.println("\n Could be null visitedNode " + visitedNode);

							tNode = findNode(visitedNode,childList.get(i));
//							System.out.println("\n Could be null " + tNode.hCost);
							currNode.child.add(tNode);
							visitedNode = recurChild(tNode, visitedNode);
						}						
						tNode = findNode(tList,childList.get(i));
						if(tNode != null && isBetter(childList.get(i),tNode)){
							currNode.child.add(tNode);
							fringeList = rearrangeNode(fringeList,tNode);
						}
					}
				}
			}
//			if(fringeList.size()>1){
//				fringeList = sortList(fringeList);
//			}
//			//Debugging
//			System.out.println("\n\n\n--------------- Turn: " + currNode.nTurn);
//			currNode.printMap(currNode.knight);
//			currNode.printlist();
//			printlistPQ(fringeList);
//			System.out.println("Knight: " + currNode.knight.toString());
//			System.out.println("currNode.hcost: " + currNode.hCost);
//			System.out.println("fringelist.size(): " + fringeList.size());
			if(fringeList.isEmpty()){
				break;
			}

		}
		
		return null;
	}
	public PriorityQueue<Node2> printlistPQ(PriorityQueue<Node2> pList){
		int num = pList.size();
//		System.out.println("\nSTART Print PQ List");
		Comparator<Node2> queueComparator = new NodeComparator();
		PriorityQueue<Node2> tList = new PriorityQueue<Node2>(queueComparator);
		for(int i =0; i<num;i++){
			Node2 currNode = pList.peek();
//			System.out.println("\n" + currNode.toString() + " HCost: " + currNode.hCost);
//			System.out.println(tList.offer(currNode));
			pList.remove();
		}
//		System.out.println("END Print PQList \n");
		pList = tList;
		return pList;
	}
	
	public void printNodeList(ArrayList<Node2> nList){
		for(int i = 0 ; i<nList.size();i++){
//			System.out.println(nList.get(i).hCostString() + "\n");
		}
	}
	
	public ArrayList<Node2> sortList(ArrayList<Node2> nList){
		//http://javahungry.blogspot.com/2013/06/java-sorting-program-code-insertion-sort.html
//		System.out.println(nList.size() + "BEFORE \n\n");
		for(int i = 1; i<nList.size();i++){
			Node2 next = nList.get(i);
			int j = i;
			while(j> 0 && nList.get(j-1).hCost > next.hCost){
				nList.set(j, nList.get(j-1));
				j--;
			}
			nList.set(j, next);
		}
//		System.out.println(nList.size());
		
		return nList;
	}
	public boolean isBetter(Node2 aNode, Node2 bNode){
		if(aNode.hCost<bNode.hCost){
			return true;
		}
		return false;
	}
	public Node2 findNode(ArrayList<Node2> aList, Node2 aNode){
		for(int i = 0;i<aList.size();i++){
			if(isSameNode(aList.get(i),aNode)){
				return aList.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Node2> updateNode(ArrayList<Node2> aList, Node2 aNode){
		for(int i = 0;i<aList.size();i++){
			if(isSameNode(aList.get(i),aNode)){
				Node2 currNode = aList.get(i); 
//				Node2 parentNode = currNode.parent;
//				System.out.println("parent child contain true: " + parentNode.child.contains(currNode));
				aList.get(i).parent.child.remove(currNode);
//				System.out.println("parent child contain false: " + parentNode.child.contains(currNode));
//				System.out.println("parent should be null: " + aList.get(i).parent);
				aList.get(i).parent = aNode.parent;
				aList.get(i).nTurn = aNode.nTurn;
				aList.get(i).setHCost(aNode.hCost);
				aList.get(i).parent.child.add(currNode);
				return aList;
			}
		}
		return aList;
	}

	//no child
	public PriorityQueue<Node2> rearrangeNode(PriorityQueue<Node2> aList, Node2 aNode){
		Comparator<Node2> queueComparator = new NodeComparator();
		PriorityQueue<Node2> tList = new PriorityQueue<Node2>(queueComparator);
		Node2 currNode;
		int num = aList.size();
		for(int i = 0;i<num;i++){
			currNode = aList.peek();
			if(isSameNode(currNode,aNode)){
				//still need to remove aNOde from child from prev parent node
				currNode.parent.child.remove(currNode);
				tList.offer(aNode);
				aList.remove();
			}else{
				tList.offer(currNode);
				aList.remove();
			}
		}
		return tList;
	}
	public Node2 bestHeuristic(ArrayList<Node2> aList){
		Node2 bestNode = null;
		for(int i = 0; i<aList.size();i++){
			if(i>0){
				if(aList.get(i-1).hCost >= aList.get(i).hCost){
					bestNode = aList.get(i-1);
				}
			}
			
		}
		return bestNode;
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
			//calculate heuristic cost here
			
			tpList = new ArrayList<Pawn>(updatePawn(turn, tpList));
			tpList = checkEat(aKnight,tpList);
			int tTurn = turn + 1;
			aNode = new Node2(tpList.size(),row,col,tTurn,parentNode,tpList,aKnight);
			
			int tempH = aNode.genHeuristic();
			aNode.setHCost(tempH);
			
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
			
			int tempH = aNode.genHeuristic();
			aNode.setHCost(tempH);
			
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

			int tempH = aNode.genHeuristic();
			aNode.setHCost(tempH);
			
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

			int tempH = aNode.genHeuristic();
			aNode.setHCost(tempH);
			
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

			int tempH = aNode.genHeuristic();
			aNode.setHCost(tempH);
			
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

			int tempH = aNode.genHeuristic();
			aNode.setHCost(tempH);
			
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

			int tempH = aNode.genHeuristic();
			aNode.setHCost(tempH);
			
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

			int tempH = aNode.genHeuristic();
			aNode.setHCost(tempH);
			
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
	public PriorityQueue<Node2> deRef(PriorityQueue<Node2> aList){
		Comparator<Node2> queueComparator = new NodeComparator();
		PriorityQueue<Node2> tList = new PriorityQueue<Node2>(queueComparator);
		int num = aList.size();
		for(int i = 0; i<num;i++){
			tList.offer(aList.peek());
			aList.remove();
		}
		
		return tList;
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
	public boolean checkBound(int length, int height, int pieceRow, int pieceCol){	
		if(((pieceRow + height) >=0 && (pieceRow + height)< row && (pieceRow + height) != row) && 
				((pieceCol + length) >=0 && (pieceCol + length) < col && (pieceCol + length) != col)){
			return true;
		}else{
			return false;
		}
	}
	public ArrayList<Node2> removeNode(ArrayList<Node2> nList, Node2 aNode){
		for(int i =0;i<nList.size();i++){
			if(isSameNode(nList.get(i), aNode)){
				nList.remove(i);
				return nList;
			}
		}
		return nList;
	}
	public boolean isVisitedNode(ArrayList<Node2> nodeList,Node2 aNode){
		for(int i =0; i<nodeList.size();i++){
			if(isSameNode(nodeList.get(i),aNode)){
				return true;
			}
		}
		return false;
	}
	public ArrayList<Node2> PQtoAL(PriorityQueue<Node2> pq){
		int num = pq.size();
		Comparator<Node2> queueComparator = new NodeComparator();
		PriorityQueue<Node2> tList = new PriorityQueue<Node2>(queueComparator);
		ArrayList<Node2> aList = new ArrayList<Node2>();
		for(int i =0; i<num;i++){
			aList.add(pq.peek());
			tList.offer(pq.peek());
			pq.remove();
		}
		pq = tList;
		return aList;
		
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
