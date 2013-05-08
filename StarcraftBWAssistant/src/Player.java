/**
 * Author: William West
 * Filename: Player.java
 * Class: CSE428 - Semantic Web
 * Assignment: Final Project
 * Description:	Class representation of a Player object, containing
 *				an array of that player's units and their ID.
*/

import java.util.*;

public class Player{
	private int playerId = -1;
	private ArrayList<Unit> myUnits = null;
	private ArrayList<Unit> enemyUnits = null;
	
	public void Player(){
	}
	
	public void setPlayerId(int id){
		playerId = id;
	}
	
	public void setMyUnits(ArrayList<Unit> u){
		myUnits = u;
	}
	
	public int getPlayerId(){
		return playerId;
	}
	
	public ArrayList<Unit> getMyUnits(){
		return myUnits;
	}
}
