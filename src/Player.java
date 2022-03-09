//--------------------------------------------------------------------------------------------
//Assignment 4
//Written by: TengQi Song student ID: 40054235
//For COMP 248 W - Winter 2018
//Date: April 17th
//--------------------------------------------------------------------------------------------


public class Player {

	public String name;//player's name
	String[][] gameBoard= new String [4][11];//the gameboard is 4 lines and 11 rows
	int RedLast, YellowLast, GreenLast, BlueLast;//record the most right-hand value for each line
	int red,yellow,green,blue;//record how many "X" each line has
	int negativePoints;//record how many negative points player got because of passing
	int totalPoints;//give the final total points for every players
	
	public Player(String name){
		this.name = name;
		gameBoard=initializeGameboard();
		RedLast=1;
		YellowLast=1;
		GreenLast=13;
		BlueLast=13;
		red=0;
		yellow=0;
		green=0;
		blue=0;
		negativePoints=0;
		totalPoints=0;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setYellow() {
		yellow++;
	}
	
	public int getYellow() {
		return yellow;
	}
	
	public void setGreen() {
		green++;
	}
	
	public int getGreen() {
		return green;
	}
	
	public void setBlue() {
		blue++;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public void setRed() {
		red++;
	}
	
	public int getRed() {
		return red;
	}
	
	public void setRedLast(int red) {
		this.RedLast=red;
	}
		
	public int getRedLast() {
		return RedLast;
	}
	
	public void setYellowLast(int yellow) {
		this.YellowLast=yellow;
	}
	
	public int getYellowLast() {
		return YellowLast;
	}
	
	public void setGreenLast(int green) {
		this.GreenLast=green;
	}
	
	public int getGreenLast() {
		return GreenLast;
	}
	
	public void setBlueLast(int blue) {
		this.BlueLast=blue;
	}
	
	public int getBlueLast() {
		return BlueLast;
	}
	
	public void addNegativePoints() {
		this.negativePoints-=5;
		}
	
	public int getNegativePoints() {
		return negativePoints;
	}
	
	public String[][] initializeGameboard() {//initialize the gameboard
		for(int i=0,a=2;i<11;i++,a++) {
			String p = a+" ";
			
			gameBoard[0][i]=p;
			gameBoard[1][i]=p;
			
		}
		for(int i=0,a=12; i<11; i++,a--) {
			String p = a+" ";
			
			gameBoard[2][i]=p;
			gameBoard[3][i]=p;
			
		}
	return gameBoard;
	}
	
	public void printGameBoard() {
		
				System.out.println(getName()+"'s Gameboard:");
		
		System.out.print("     Red: ");
	    	for(int j=0;j<gameBoard[0].length;j++) {
	    		System.out.print(gameBoard[0][j]+" ");
	    	}System.out.println();
	    System.out.print("  Yellow: ");
	    	for(int j=0;j<gameBoard[1].length;j++) {
	    		System.out.print(gameBoard[1][j]+" ");
	    	}System.out.println();
	    System.out.print("   Green: ");
	    	for(int j=0;j<gameBoard[2].length;j++) {
	    		System.out.print(gameBoard[2][j]+" ");
	    	}System.out.println();	
	    System.out.print("    Blue: ");
	    	for(int j=0;j<gameBoard[3].length;j++) {
	    		System.out.print(gameBoard[3][j]+" ");
	    	}System.out.println();
	
	}
	
	public void makeMove(Move m) {// the number will became "X" because of opperation given by player
	    int i,j;
		i =Move.convertColourtoNum(m.getColour());
		j =m.getNumber();
		if(i == 0 || i == 1) {
			this.gameBoard [i][j-2] = "X ";
		}else {
			this.gameBoard [i][12-j] = "X ";
		}
	}
	
	public void calculateBoardTotalMethod() {//calcaulate the final mark for each players
	   int totalPointsRed=0, totalPointsYellow=0, totalPointsGreen=0, totalPointsBlue=0;
	    
		for(int k=0;k<=getRed();k++) {
			totalPointsRed+=k;
		}
		for(int k=0;k<=getYellow();k++) {
			totalPointsYellow+=k;
		}
		for(int k=0;k<=getGreen();k++) {
			totalPointsGreen+=k;
		}
		for(int k=0;k<=getBlue();k++) {
			totalPointsBlue+=k;
		}
	    
		this.totalPoints = totalPointsRed+totalPointsYellow+totalPointsGreen+totalPointsBlue+getNegativePoints();
		}
	
	public int getBoardTotalMethod() {
		return this.totalPoints;
	}
	
	
	
	
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
