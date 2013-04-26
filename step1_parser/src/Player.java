import java.lang.*;
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
	
	public void setEnemyUnits(ArrayList<Unit> u){
		enemyUnits = u;
	}
	
	public int getPlayerId(){
		return playerId;
	}
	
	public ArrayList<Unit> getMyUnits(){
		return myUnits;
	}
	
	public ArrayList<Unit> getEnemyUnits(){
		return enemyUnits;
	}
	
	public String toString(){
		String s = "["+playerId+", Units: "+myUnits+"]";
		return s;
	}
}
