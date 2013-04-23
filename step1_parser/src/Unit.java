import java.lang.*;
import java.util.*;

public class Unit{
	private int unitId = -1;
	private String unitType = null;
	private int currentHitPoints = -1;
	private int xCoord = -1;
	private int yCoord = -1;
	private int regionId = -1;
	private int armor = -1;
	private int mineralCost = -1;
	private int gasCost = -1;
	
	public void Unit(){
	}
	
	public void setUnitId(int id){
		unitId = id;
	}
	
	public void setUnitType(String type){
		unitType = type;
	}
	
	public void setCurrentHitPoints(int currHP){
		currentHitPoints = currHP;
	}
	
	public void setXCoord(int x){
		xCoord = x;
	}
	
	public void setYCoord(int y){
		yCoord = y;
	}
	
	public void setRegionId(int rId){
		regionId = rId;
	}
	
	public void setArmor(int a){
		armor = a;
	}
	
	public void setMineralCost(int mCost){
		mineralCost = mCost;
	}
	
	public void setGasCost(int gCost){
		gasCost = gCost;
	}
	
	public String toString(){
		String s = "["+unitId+", "+unitType+", "+currentHitPoints+", "+xCoord+", "+yCoord+", "+regionId+", "+armor+", "+mineralCost+", "+gasCost+"]";
		return s;
	}
}
