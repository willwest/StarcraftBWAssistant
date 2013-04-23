import java.lang.*;
import java.util.*;

public class Player{
	private int playerId = -1;
	private ArrayList<Unit> units = null;
	
	public void Player(){
	}
	
	public void setPlayerId(int id){
		playerId = id;
	}
	
	public void setUnits(ArrayList<Unit> u){
		units = u;
	}
	
	public int getPlayerId(){
		return playerId;
	}
	
	public ArrayList<Unit> getUnits(){
		return units;
	}
	
	public String toString(){
		String s = "["+playerId+", Units: "+units+"]";
		return s;
	}
}
