/**
 * Name: William West
 * Filename: Region.java
 * Class: CSE428 - Semantic Web
 * Assignment: Final Project
 * Description:	Class representation of a Region object, containing
 *				the region's ID, x-coordinate, and y-coordinate.
*/

import java.lang.*;
import java.util.*;

public class Region{
	private int regionCenterX = -1;
	private int regionCenterY = -1;
	private int regionId = -1;
	
	public void Region(){
	}
	
	public void setRegionId(int id){
		regionId = id;
	}
	
	public void setRegionCenterX(int x){
		regionCenterX = x;
	}
	
	public void setRegionCenterY(int y){
		regionCenterY = y;
	}
	
	public int getRegionId(){
		return regionId;
	}
	
	public int getRegionCenterX(){
		return regionCenterX;
	}
	
	public int getRegionCenterY(){
		return regionCenterY;
	}
}
