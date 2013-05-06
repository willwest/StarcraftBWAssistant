/**
 * Author: William West
 * Filename: Unit.java
 * Class: CSE428 - Semantic Web
 * Assignment: Final Project
 * Description:	Class representation of a Unit object, containing
 *				the unit's ID, type, current HP, max HP, whether it is
 *				currently being attacked, it's x-coordinate, y-coordinate,
 *				the region ID in which it is located, the amount of armor
 *				it has, it's mineral cost, and gas cost.
*/

public class Unit{
	private int unitId = -1;
	private String unitType = null;
	private int currentHitPoints = -1;
	private int maxHitPoints = -1;
	private boolean isBeingAttacked = false;
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
	
	public void setMaxHitPoints(int maxHP){
		maxHitPoints = maxHP;
	}
	
	public void setIsBeingAttacked(boolean i){
		isBeingAttacked = i;
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
	
	public String getUnitType(){
		return unitType;
	}
	
	public int getCurrentHitPoints(){
		return currentHitPoints;
	}
	
	public int getMaxHitPoints(){
		return maxHitPoints;
	}
	
	public boolean getIsBeingAttacked(){
		return isBeingAttacked;
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
}
