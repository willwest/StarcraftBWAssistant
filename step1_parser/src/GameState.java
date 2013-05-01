/////////////////////////////////////////////////////////////////////////////////////////
// Name: William West                                                                  //
// Filename: GameState.java                                                            //
// Class: CSE428 - Semantic Web                                                        //
// Assignment: Final Project                                                           //
// Description:                                                                        //
/////////////////////////////////////////////////////////////////////////////////////////

import java.lang.*;
import java.util.*;

public class GameState{
	private ArrayList<Player> players = null;
	private ArrayList<Region> regions = null;
	private ArrayList<Chokepoint> chokepoints = null;
	
	public void GameState(){
	}
	
	public void setPlayers(ArrayList<Player> p){
		players = p;
	}
	
	public void setRegions(ArrayList<Region> r){
		regions = r;
	}
	
	public void setChokepoints(ArrayList<Chokepoint> c){
		chokepoints = c;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public ArrayList<Region> getRegions(){
		return regions;
	}
	
	public ArrayList<Chokepoint> getChokepoints(){
		return chokepoints;
	}
	
	public String toString(){
		String s = "Players: "+players+",\nRegions: "+regions+"]";
		return s;
	}
}
