//--------------------------------------------------------------------------------------------
//Assignment 4
//Written by: TengQi Song student ID: 40054235
//For COMP 248 W - Winter 2018
//Date: April 17th
//--------------------------------------------------------------------------------------------

import java.util.Scanner;

public class Qwixx {
	Dice[] dice;//there are total 6 dices 
	Player[] player;//there are between 2-5 players which decided by players
	public int redLock, yellowLock, greenLock, blueLock;//they record the most right-hand number 
	public boolean whetherLock;//decided whether the colour finish
	public boolean whetherFinished;//decide whether game finish
	public int WhiteDiceTotal;//the total number of two white dices
	public  boolean whetherValid;//decide the number inputed by player is valid or not
	
	public Qwixx(int playernumber) {
		this.dice = setDice();
		this.player = new Player[playernumber];
		this.WhiteDiceTotal = 0;
		this.whetherLock=true;
		this.whetherFinished=true;
		this.redLock=2;
		this.yellowLock=2;
		this.greenLock=12;
		this.blueLock=12;
	}

	public void setRedLock(int number) {
		this.redLock=number;
	}
	
	public int getRedLock() {
		return this.redLock;
	}
	
	public void setYellowLock(int number) {
		this.yellowLock=number;
	}
	
	public int getYellowLock() {
		return this.yellowLock;
	}
	
	public void setGreenLock(int number) {
		this.greenLock=number;
	}
	
	public int getGreenLock() {
		return this.greenLock;
	}
	
	public void setBlueLock(int number) {
		this.blueLock=number;
	}
	
	public int getBlueLock() {
		return this.blueLock;
	}
	
	public void printRolledDice() {
	    System.out.print(dice[0].toString());
	    System.out.print(dice[1].toString());
	    System.out.print(dice[2].toString());
	    System.out.print(dice[3].toString());
	    System.out.print(dice[4].toString());
	    System.out.print(dice[5].toString());
	    }
	
	public int getWhiteDiceTotal() {
		int WhiteDiceTotal=dice[4].getCurrentSide()+dice[5].getCurrentSide();
		return WhiteDiceTotal;
	}
	
	public boolean checkValidMove(Player p, Move m) {
		switch(Move.convertColourtoNum(m.getColour())) {
		case 0:
			if(m.getNumber()>p.getRedLast()) {
				whetherValid=true;
				}else {
				whetherValid=false;
				}
			break;
		case 1:
			if(m.getNumber()>p.getYellowLast()) {
				whetherValid=true;
			}else {
				whetherValid=false;
			}
			break;
		case 2:
			if(m.getNumber()<p.getGreenLast()) {
				whetherValid=true;
				}else {
				whetherValid=false;
				}
			break;
		case 3:
			if(m.getNumber()<p.getBlueLast()) {
				whetherValid=true;
				}else {
				whetherValid=false;
				}
		}return whetherValid;
	}
	
	public boolean checkColourFinished(char colour) {
		switch(Move.convertColourtoNum(colour)) {
		case 0:
			if(getRedLock()==12) {
				whetherLock=false;
			}else {
				whetherLock=true;
			}
			break;
		case 1:
			if(getYellowLock()==12) {
				whetherLock=false;
			}else {
				whetherLock=true;
			}
			break;
		case 2:
			if(getGreenLock()==2) {
				whetherLock=false;
			}else {
				whetherLock=true;
			}
			break;
		case 3:
			if(getBlueLock()==2) {
				whetherLock=false;
			}else {
				whetherLock=true;
			}
		}return whetherLock;
	}

	public void playColourMoves(Player p) {// the move in colour turn
		Scanner In=new Scanner(System.in);
		String answer, colour;
		int white,colourTotal;
		
		System.out.println(p.getName()+" it's your turn");
		System.out.println("*****Move on any colour dice****");
		p.printGameBoard();
		System.out.println();
		printRolledDice();
		System.out.println();
		System.out.println();
		char colourChar='k';
	    Move m = new Move(colourChar,getWhiteDiceTotal());
		do{
			System.out.print("Would you like to cross off a number on the game board using one of the coloured dice and a white dice?(anything other than 'yes' is taken to mean no ): ");
		
		answer=In.next();
		if(answer.equalsIgnoreCase("yes")) {
			System.out.print("Which white dice would you like to use? (White1 = 1, White2 = 2): ");
			white = In.nextInt();
			System.out.print("What colour would you like to cross out? (R=red, Y=yellow, G=green, B=blue): ");
			colour=In.next();
			colourChar=colour.charAt(0);
			colourTotal=dice[Move.convertColourtoNum( colourChar)].getCurrentSide()+dice[(white+3)].getCurrentSide();
		    m.setColour(colourChar);
		    m.setNumber(colourTotal);
		    if(checkValidMove(p,m)==false) {
		    	System.out.println("Invalid move! Plese enter an number that is in the right of the last number you choosed :)");
		    }
		    if(checkColourFinished(colourChar)==false) {
		    	System.out.println("Sorry "+colour+" line is locked. Please choose an other line");
		    }
		  
		    if(checkValidMove(p,m)==false || checkColourFinished(colourChar)==false) 
	    		 continue;
		    p.makeMove(m);
		    p.printGameBoard();
		}else {
			p.addNegativePoints();
			System.out.println("For passing you get -5 points. You now have " +p.getNegativePoints()+" points");
			System.out.println();
		}
	
		}while(checkValidMove(p,m)==false || checkColourFinished(colourChar)==false);
			if(answer.equalsIgnoreCase("yes")) {
				switch(Move.convertColourtoNum(m.getColour())) {//record the most right-hand position for check whether valid move
				case 0:
					p.setRedLast(m.getNumber());
					break;
				case 1:
					p.setYellowLast(m.getNumber());
					break;
				case 2:
					p.setGreenLast(m.getNumber());
					break;
				case 3:
					p.setBlueLast(m.getNumber());
				}
				switch(Move.convertColourtoNum(m.getColour())) {//record the most right-hand position for check whether colour lock
				case 0:
					setRedLock(m.getNumber());
					break;
				case 1:
					setYellowLock(m.getNumber());
					break;
				case 2:
					setGreenLock(m.getNumber());
					break;
				case 3:
					setBlueLock(m.getNumber());
				}
				switch(Move.convertColourtoNum(m.getColour())) {//record the most right-hand position for calculate the number of "x" for each line
				case 0:
					p.setRed();
					break;
				case 1:
					p.setYellow();
					break;
				case 2:
					p.setGreen();
					break;
				case 3:
					p.setBlue();
				}
			}
		}
	
	public void playWhiteDiceMoves(Player p) {//move in white turn
		  Scanner In=new Scanner(System.in);char colourChar='k';String answer;
		  printRolledDice();
		  System.out.println();
		  System.out.println("****Move on white dice****");
	      System.out.println("The total for the white dice is "+getWhiteDiceTotal());
	      System.out.println();
		  System.out.println(p.getName()+" it's your turn...");
	      p.printGameBoard();
	      System.out.println();
	      System.out.println();
	    
	      Move m = new Move(colourChar,getWhiteDiceTotal());
	      
	   do {
	      System.out.print("Would you like to cross off a number on the game board using the white dice total? (anything other than 'yes' is taken to mean no): ");
	      answer = In.next();
	      if(answer.equalsIgnoreCase("yes")) {
	    	  System.out.print("What colour would you like to cross out? (R=red, Y=yellow, G=green, B=Blue): ");
	          String colour =In.next();
	          colourChar=colour.charAt(0);
	    	  m.setColour(colourChar);
	    	  if(checkValidMove(p,m)==false) {
			    	System.out.println("Invalid move! Plese enter an number that is in the right of the last number you choosed :)");
			    }
			  if(checkColourFinished(colourChar)==false) {
			    	System.out.println("Sorry "+colour+" line is locked. Please choose an other line");
			    }
			  if(checkValidMove(p,m)==false || checkColourFinished(colourChar)==false) 
	    		 continue;
	    	  p.makeMove(m);
	    	  p.printGameBoard();
	    	}
	   }while(checkValidMove(p,m)==false || checkColourFinished(colourChar)==false);
	   		if(answer.equalsIgnoreCase("yes")) {
	   			switch(Move.convertColourtoNum(m.getColour())) {//record the most right-hand position for check whether valid move
	   				case 0:
	   					p.setRedLast(getWhiteDiceTotal());
	   					break;
	   				case 1:
	   					p.setYellowLast(getWhiteDiceTotal());
	   					break;
	   				case 2:
	   					p.setGreenLast(getWhiteDiceTotal());
	   					break;
	   				case 3:
	   					p.setBlueLast(getWhiteDiceTotal());
	   			}
    
	   			switch(Move.convertColourtoNum(m.getColour())) {//record the most right-hand position for check whether colour lock
	   			case 0:
	   				setRedLock(getWhiteDiceTotal());
	   				break;
	   			case 1:
	   				setYellowLock(getWhiteDiceTotal());
	   				break;
	   			case 2:
	   				setGreenLock(getWhiteDiceTotal());
	   				break;
	   			case 3:
	   				setBlueLock(getWhiteDiceTotal());
	   			}
	   			switch(Move.convertColourtoNum(m.getColour())) {//record the most right-hand position for calculate the number of "x" for each line
	   			case 0:
	   				p.setRed();
	   				break;
	   			case 1:
	   				p.setYellow();
	   				break;
	   			case 2:
	   				p.setGreen();
	   				break;
	   			case 3:
	   				p.setBlue();
	   			}
	   		}   
	}
	
	public void checkGameFinished(Player p) {//if one player passed four times and two lines are locked, it will return false and game over
		if(p.getNegativePoints()==-20 || (redLock==12&&(yellowLock==12||greenLock==2||blueLock==2)) || (yellowLock==12&&(greenLock==2||blueLock==2)) || (greenLock==2&&blueLock==2)) {
			whetherFinished=false;
		}else {
			whetherFinished=true;
		}
		
	}
	
	public boolean getGameFinished() {
		return whetherFinished;
	}


	public Dice[] setDice() {//set six dices
		dice = new Dice[6];
		dice[0]=new Dice("Red");
		dice[1]=new Dice("Yellow");
		dice[2]=new Dice("Green");
		dice[3]=new Dice("Blue");
		dice[4]=new Dice("White1");
		dice[5]=new Dice("White2");
		return this.dice;
	}
	
	public void play(int playersNumber) {//the method where game run
		int totalpointsOfWinner=-21;//the initial value should lower than the possible lowest points-- -20
		do {
			  for(int k=0;k<playersNumber;k++) {
				  System.out.println("-------New Round-------");
				  setDice();
				  printRolledDice();
				  for(int i=0;i<playersNumber;i++) {
					 playWhiteDiceMoves(player[i]);
					 System.out.println();
					 System.out.println();
					 System.out.println();
					 checkGameFinished(player[i]);
					   if(getGameFinished()==false)
						   break;
				  }
				  if(getGameFinished()==false)
					   break;
				  playColourMoves(player[k]);
				  System.out.println();
				  System.out.println();
				  System.out.println();
				  checkGameFinished(player[k]);
				  if(getGameFinished()==false)
					  break;
			    }
		}while(getGameFinished()==true);
		     for(int i=0; i<playersNumber;i++) {//output player's points and determine who is winner
		    	 player[i].calculateBoardTotalMethod();
		    	 System.out.println(player[i].getName()+" has a total of: "+player[i].getBoardTotalMethod());
		    	   totalpointsOfWinner=Math.max(totalpointsOfWinner,player[i].getBoardTotalMethod());
		     }
		    for(int i=0; i<playersNumber;i++) {//output who is winner
		    	 if(player[i].getBoardTotalMethod()==totalpointsOfWinner) {
		    		 System.out.println("That's all folks! \n"+player[i].getName()+" wins the game");
		    	 }
		     }
	}
	
	public static void main(String[] args) {//main mission is to record the players' number and creat class "Player" for every player
		Scanner In = new Scanner(System.in);
		int playersNumber;  String name;
		System.out.println("Nice to meet you guys!!"+"\n"+"Welcome to this very intersting and excited game -- Qwixx!!");
		do {
			System.out.print("Please enter the number of players(2-5): ");
			playersNumber= In.nextInt();
		}while(playersNumber<2||playersNumber>5);
		Qwixx q = new Qwixx(playersNumber);
		for(int i=0;i<playersNumber;i++) {
			System.out.print("Please enter the name of player"+(i+1)+" :");
			name=In.next();
			q.player[i]=new Player(name);
			
	    	q.player[i].printGameBoard();
	    	System.out.println();
		     }
	     q.play(playersNumber);
	}
	
}
