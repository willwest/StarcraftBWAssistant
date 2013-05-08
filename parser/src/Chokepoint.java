/////////////////////////////////////////////////////////////////////////////////////////
// Name: William West                                                                  //
// Filename: Chokepoint.java                                                           //
// Class: CSE428 - Semantic Web                                                        //
// Assignment: Final Project                                                           //
// Description:                                                                        //
/////////////////////////////////////////////////////////////////////////////////////////

import java.lang.*;
import java.util.*;

//{"chokepointID":"0","chokepointCenterX":"2460","chokepointCenterY":"676","connectedToRegionOneID":"9","connectedToRegionTwoID":"14"}

public class Chokepoint{
	private int chokepointId = -1;
	private int chokepointCenterX = -1;
	private int chokepointCenterY = -1;
	private int connectedToRegionOne = -1;
	private int connectedToRegionTwo = -1;
	
	public void Chokepoint(){
	}
	
	public void setChokepointId(int id){
		chokepointId = id;
	}
	
	public void setChokepointCenterX(int x){
		chokepointCenterX = x;
	}
	
	public void setChokepointCenterY(int y){
		chokepointCenterY = y;
	}
	
	public void setConnectedToRegionOne(int regionOne){
		connectedToRegionOne = regionOne;
	}
	
	public void setConnectedToRegionTwo(int regionTwo){
		connectedToRegionTwo = regionTwo;
	}
	
	public int getChokepointId(){
		return chokepointId;
	}
	
	public int getChokepointCenterX(){
		return chokepointCenterX;
	}
	
	public int getChokepointCenterY(){
		return chokepointCenterY;
	}
	
	public int getConnectedToRegionOne(){
		return connectedToRegionOne;
	}
	
	public int getConnectedToRegionTwo(){
		return connectedToRegionTwo;
	}
}
