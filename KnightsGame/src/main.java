import java.io.Console;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		int type = 3;
		int numP = 4; int row = 30; int col = 40;
		if(type==0){
			Node aNode = new Node(0,numP,row,col);
			
			int c = 0; Scanner reader = new Scanner(System.in); 
			while(!aNode.areEaten()){
				//	1		5
				//2				6
				//		K
				//3				7
				//	4		8
				System.out.println("\nTo move the knight please press the corresponding number");
				System.out.println("	1		5	");
				System.out.println("2				6");
				System.out.println("		k		");			
				System.out.println("3				7");			
				System.out.println("	4		8	");
				aNode.printMap();
				System.out.println();
				
				c = reader.nextInt(); 
				
				System.out.println("user select "  + c);
				aNode.userMoveK(c);
			}
		}else if(type==1){
			//BFS
//			int numP = 5; int row = 10; int col = 10;
			BFS bfs = new BFS(numP,row,col);
			
			Node2 initial = new Node2(numP, row,col);
			ArrayList<Pawn> pList= new ArrayList<Pawn>();
			pList = initial.genPawn(numP);

//			Pawn aPawn = new Pawn(1,0,"EAST",0);pList.add(aPawn);
//			Pawn bPawn = new Pawn(4,2,"WEST",1);pList.add(bPawn);

//			Knight aKnight = new Knight(2,2);
			Knight aKnight = initial.genKnight();
			int turn = 0;
			
//			System.out.println("main pList.get(0).toString(): "+pList.get(0).toString());
//			System.out.println("main pList.get(1).toString(): "+pList.get(1).toString());
			
			Node2 goalNode = bfs.BFSearch(pList, aKnight, turn);
			
			Node2 currNode = goalNode;
			Deque<Node2> stack = new ArrayDeque<Node2>();
			Deque<Node2> stack2 = new ArrayDeque<Node2>();
			while(true){
				
				//currNode.printMap(currNode.knight);
				stack.push(currNode);
				stack2.push(currNode);
				//if(bfs.isSameNode(currNode, currNode.parent)){
				if(currNode.parent==null){
					break;
				}else{
					currNode = currNode.parent;
				}
			}
			System.out.println("\n    SOLUTION\n");
			while(!stack.isEmpty()){
				currNode = stack.pop();
				System.out.println("totalVisitedNode: " + bfs.totalVisitedNode);
				System.out.println("Turn: " + currNode.nTurn);
				currNode.printMap(currNode.knight);
				System.out.println("\n");
			}
		}else if(type ==2){
			//DFS
//			int numP = 2; int row = 5; int col = 5;
			DFS dfs = new DFS(numP,row,col);
			
			Node2 initial = new Node2(numP, row,col);
			ArrayList<Pawn> pList= new ArrayList<Pawn>();
			pList = initial.genPawn(numP);

//			Pawn aPawn = new Pawn(1,0,"EAST",0);pList.add(aPawn);
//			Pawn bPawn = new Pawn(4,2,"WEST",1);pList.add(bPawn);

//			Knight aKnight = new Knight(2,2);
			Knight aKnight = initial.genKnight();
			int turn = 0;
			
//			System.out.println("main pList.get(0).toString(): "+pList.get(0).toString());
//			System.out.println("main pList.get(1).toString(): "+pList.get(1).toString());
			
			Node2 goalNode = dfs.DFSearch(pList, aKnight, turn);
			
			Node2 currNode = goalNode;
			Deque<Node2> stack = new ArrayDeque<Node2>();
			Deque<Node2> stack2 = new ArrayDeque<Node2>();
			while(true){
				
				//currNode.printMap(currNode.knight);
				stack.push(currNode);
				stack2.push(currNode);
				//if(bfs.isSameNode(currNode, currNode.parent)){
				if(currNode.parent==null){
					break;
				}else{
					currNode = currNode.parent;
				}
			}
			System.out.println("\n    SOLUTION\n");
			while(!stack.isEmpty()){
				currNode = stack.pop();
				System.out.println("totalVisitedNode: " + dfs.totalVisitedNode);
				System.out.println("Turn: " + currNode.nTurn);
				currNode.printMap(currNode.knight);
				System.out.println("\n");
			}
		}else if(type ==3){
			//AStart
//			int numP = 2; int row = 10; int col = 10;
			AStar astar = new AStar(numP,row,col);
			
			Node2 initial = new Node2(numP, row,col);
			ArrayList<Pawn> pList= new ArrayList<Pawn>();
			pList = initial.genPawn(numP);

//			Pawn aPawn = new Pawn(1,0,"EAST",0);pList.add(aPawn);
//			Pawn bPawn = new Pawn(4,2,"WEST",1);pList.add(bPawn);

//			Knight aKnight = new Knight(2,2);
			Knight aKnight = initial.genKnight();
			int turn = 0;
			
//			System.out.println("main pList.get(0).toString(): "+pList.get(0).toString());
//			System.out.println("main pList.get(1).toString(): "+pList.get(1).toString());
			
			Node2 goalNode = astar.ASearch(pList, aKnight, turn);
			
			Node2 currNode = goalNode;
			Deque<Node2> stack = new ArrayDeque<Node2>();
			Deque<Node2> stack2 = new ArrayDeque<Node2>();
			while(true){
				
				//currNode.printMap(currNode.knight);
				stack.push(currNode);
				stack2.push(currNode);
				//if(bfs.isSameNode(currNode, currNode.parent)){
				if(currNode.parent==null){
					break;
				}else{
					currNode = currNode.parent;
				}
			}
			System.out.println("\n    SOLUTION\n");
			while(!stack.isEmpty()){
				currNode = stack.pop();
				System.out.println("totalVisitedNode: " + astar.totalVisitedNode);
				System.out.println("Turn: " + currNode.nTurn);
//				System.out.println("HCost: " + currNode.hCost);
				currNode.printMap(currNode.knight);
				System.out.println("\n");
			}
		}
	}
}
