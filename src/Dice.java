//--------------------------------------------------------------------------------------------
//Assignment 4
//Written by: TengQi Song student ID: 40054235
//For COMP 248 W - Winter 2018
//Date: April 17th
//--------------------------------------------------------------------------------------------


public class Dice {//two variable
     public String colour;
     public int currentSide;
	//Default constructor
	
     
	
     public Dice (String colour) {
		this.colour = colour;
		this.currentSide = rollDice();
	}
	
     public void setColour(String colour) {
    	 this.colour = colour;
	}
	
     public String getColour() {
		return this.colour;
	}
	
     public int getCurrentSide() {
		return this.currentSide;
	}
	
     public int rollDice() {
		this.currentSide = Math.max(1,(int)(Math.random()*7));//make sure the currentvalue is between 1 and 6
		return this.currentSide;
	}
	
     public String toString() {
		return this.colour+" dice: "+this.currentSide+" | ";
	}
	
   
	
}
