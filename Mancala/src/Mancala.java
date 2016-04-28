import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Mancala {
	//this is assuming only two player is allowed
	//number of pit is set dynamically
	MancalaBoard board;
	MancalaNode rootNode;
	Player player1 = new Player("Player1", 1, true,1);
	Player player2 = new Player("Player2", 2, true,2);
	
	Mancala(){
		rootNode = new MancalaNode(player1,player2);
		board  =  new MancalaBoard(2);
	}
	
	public static void  main(String[]  args) {
		//regular game
		int gameType = 1;
		if(gameType ==0){
			Mancala game  =  new  Mancala();
			try {
				game.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}else if(gameType ==1){
			Mancala game  =  new  Mancala();
			game.startAI();
			
		}
	}
	
	public void startAI(){
		Random rn = new Random(); int pitNum = 0; 
//		rootNode.currPlayer = 2;
		rootNode.currPlayer = rn.nextInt(2) + 1;
		
		System.out.println("Starting board");
		rootNode.board.showBoard();
		int s = 1;
		int debug = 0;
		while(!rootNode.board.isGoal()){
			if(rootNode.currPlayer == 1){
				rootNode.currPlayer = 2;
			}else{
				rootNode.currPlayer = 1;
			}
			System.out.println("\n"+ "****************************************************************************************");
            System.out.println("\n Player Number " + rootNode.currPlayer + " turn start: \n");
			System.out.println("\n"+ "****************************************************************************************");
			String input = "";
			
			Scanner scan = new Scanner(System.in);
			if(s==1){
				System.out.println("Type 1 to contine: 2 to see when player can go again: 3 to skip to end");
				s = scan.nextInt();
			}
			debug++;
			boolean goAgain = false;
			
			int debug2 =0;
			outerloop:
			while(true){
				debug2++;boolean fuc = false;
				if(debug2==3&&debug==3){
					fuc = true;
				}
				MancalaNode tempNode = new MancalaNode(rootNode.board,rootNode.player1,rootNode.player2);
				tempNode.currPlayer = rootNode.currPlayer;
//				if(fuc){
//					tempNode.player1.debug=true;
//					tempNode.player2.debug=true;
//				}
				if(rootNode.currPlayer == 1){
					input = tempNode.player1.decideMove(tempNode);					
				}else{
					input = tempNode.player2.decideMove(tempNode);
				}

				int pNum = 0;
				String[] move = null;
				if(input!=""){
					move = input.split(" "); 
					pNum = Integer.parseInt(move[1]);
					System.out.println("user " + tempNode.currPlayer +" choose pit number " + pNum + " direction: " + move[0]);
				}else{
					System.out.println("no input");
				}
				int digit = rootNode.currPlayer;
//				System.out.println("SHOULD NOT BE CHANGED");
				System.out.print("\n\nBefore Move");
				rootNode.board.showBoard();
				goAgain = rootNode.board.move(digit,pNum,move[0]);
				System.out.print("\n After");
				rootNode.board.showBoard();
				System.out.println("goAgain " + goAgain);
//				if(rootNode.currPlayer == 1)
//					System.out.println("rootNode.player1.heuristicType"+ rootNode.player1.heuristicType);
//				else
//					System.out.println("rootNode.player2.heuristicType: "+ rootNode.player2.heuristicType);
				scan = new Scanner(System.in);
				if(s==2){
					System.out.println("Type 1 to contine: 2 to see when player can go again: 3 to skip to end");
					s = scan.nextInt();
				}
				
				if(rootNode.board.isGoal()||!goAgain){
					break outerloop;
				}
			}
			
		}
		System.out.println("Game over");
		
		rootNode.player1.score = rootNode.board.closingGameCount(rootNode.player1.playerNum);
		rootNode.player2.score = rootNode.board.closingGameCount(rootNode.player2.playerNum);
		
		System.out.println("Total score \n");
		System.out.println("Player1: " + rootNode.player1.score);
		System.out.println("Player2: " + rootNode.player2.score);
		
		if(rootNode.player2.score> rootNode.player1.score){
			System.out.println("Player2 wins!");
		}else if(rootNode.player2.score< rootNode.player1.score){
			System.out.println("Player1 wins!");
		}else{
			System.out.println("Its a tie!");
		}
	}
	
	public void start() throws IOException{
		//choose randomly who goes first
		Random rn = new Random(); int playerTurn = 0; int pitNum = 0; 
		
//		playerTurn = rn.nextInt(2) + 1;
		playerTurn = 1;
		board.showBoard();
		
		//*************************************
		//rootNode
		MancalaNode currNode = new MancalaNode(board,player1,player2);
		
		
		//*************************************
		
		while(!board.isGoal()){
			//normal play
			Player currPlayer;
			playerTurn++;
			if(playerTurn%2 == 1){
				currPlayer = player1;
			}else{
				currPlayer = player2;
			}
			
			//AI
			if(playerTurn%2 == 1){
				currNode.currPlayer = 1;
			}else{
				currNode.currPlayer = 2;
			}
			
            System.out.println("\n" + currPlayer.name + " : Player Number" + currPlayer.playerNum + " turn start: \n");

			String input = "";
			
			boolean goAgain = false;
			while(true){
				
				input = currPlayer.decideMove(currNode);
				int pNum = 0;
				String[] move = null;
				if(input!=""){
					move = input.split(" "); 
					if(move!= null){
						pNum = Integer.parseInt(move[1]);
						System.out.println("user choose pit number " + pNum + " direction: " + move[0]);
					}else{
						System.out.println("No moves found.");
					}
				}
				int digit = currPlayer.playerNum;
				goAgain = board.move(digit,pNum,move[0]);
				board.showBoard();
				System.out.println("goAgain " + goAgain);
				if(board.isGoal()||!goAgain){
					break;
				}
			}
			
		}
		System.out.println("Game over");
		
		player1.score = board.closingGameCount(player1.playerNum);
		player2.score = board.closingGameCount(player2.playerNum);
		
		System.out.println("Total score \n");
		System.out.println("Player1: " + player1.score);
		System.out.println("Player2: " + player2.score);
		
		if(player2.score> player1.score){
			System.out.println("Player2 wins!");
		}else if(player2.score< player1.score){
			System.out.println("Player1 wins!");
		}else{
			System.out.println("Its a tie!");
		}
	}

	
//	public void showPlayer(Player){
//		
//	}
	
}
