
//--------------------------------------------------------------------------------------------
//Assignment 4
//Written by: TengQi Song student ID: 40054235
//For COMP 248 W - Winter 2018
//Date: April 17th
//--------------------------------------------------------------------------------------------package assignment4;


public class Move {
    public char colour;
    public int number;
   
    public Move(char colour, int number) {
    	this.colour = colour;
    	this.number = number;
    }
   
    public void setColour(char colour) {
    	this.colour = colour;
    }
    
    public char getColour() {
    	return this.colour;
    }
    
    public void setNumber(int number) {
    	this.number = number;
    }
    
    public int getNumber() {
    	return this.number;
    }
    
    public static int convertColourtoNum (char colour) {
	    switch (colour) {
	    case 'R':
	    	return 0;
	    	
	    case 'Y':
	    	return 1;
	    
	    case 'G':
	    	return 2;
	    case 'B':
	    	return 3;
	    default:
	    	return 100;
	    }
	}

}
