import java.lang.*;
import java.util.*;

public class Unit{
	private int unitId = -1;
	private int unitTypeId = -1;
	private int currentHitPoints = -1;
	private int maxHitPoints = -1;
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
	
	public void setUnitTypeId(int type){
		unitTypeId = type;
	}
	
	public void setCurrentHitPoints(int currHP){
		currentHitPoints = currHP;
	}
	
	public void setMaxHitPoints(int maxHP){
		maxHitPoints = maxHP;
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
	
	public int getUnitId(){
		return unitId;
	}
	
	public int getUnitTypeId(){
		return unitTypeId;
	}
	
	public int getCurrentHitPoints(){
		return currentHitPoints;
	}
	
	public int getMaxHitPoints(){
		return maxHitPoints;
	}
	
	public int getXCoord(){
		return xCoord;
	}
	
	public int getYCoord(){
		return yCoord;
	}
	
	public int getRegionId(){
		return regionId;
	}
	
	public int getArmor(){
		return armor;
	}
	
	public int getMineralCost(){
		return mineralCost;
	}
	
	public int getGasCost(){
		return gasCost;
	}
	
	public String toString(){
		String s = "["+unitId+", "+unitTypeId+", "+currentHitPoints+", "+xCoord+", "+yCoord+", "+regionId+", "+armor+", "+mineralCost+", "+gasCost+"]";
		return s;
	}
}
