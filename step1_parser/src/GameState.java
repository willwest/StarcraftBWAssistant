import java.lang.*;
import java.util.*;

public class GameState{
	private ArrayList<Player> players = null;
	private ArrayList<Region> regions = null;
	
	public void GameState(){
	}
	
	public void setPlayers(ArrayList<Player> p){
		players = p;
	}
	
	public void setRegions(ArrayList<Region> r){
		regions = r;
	}
	
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	public ArrayList<Region> getRegions(){
		return regions;
	}
	
	public String toString(){
		String s = "Players: "+players+",\nRegions: "+regions+"]";
		return s;
	}
}
