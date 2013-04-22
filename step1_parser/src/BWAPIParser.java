import com.google.gson.stream.JsonReader;
import java.util.*;
import java.lang.*;
import java.io.*;

public class BWAPIParser{
	public static void main(String[] args){
		String inputFile = args[0];
		try{
			InputStream in = new FileInputStream(inputFile);
			readJsonStream(in);
		}catch(Exception e){
			System.out.println("Error: "+e);
		}
	}
	
	public static void readJsonStream(InputStream in) throws IOException{
		JsonReader reader = new JsonReader(new InputStreamReader(in));
		try{
			readPlayerObject(reader);
		}
		catch(Exception e){
			System.out.println("Error: "+e);
		}
		finally{
			reader.close();
		}
	}
	
	public static void readPlayerObject(JsonReader reader) throws IOException{
		int playerId = -1;
		reader.beginObject();
		List<Unit> units = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals("playerId")){
				playerId = reader.nextInt();
			}
			else if(name.equals("units")){
				units = readUnitsArray(reader);
			}
			else{
				reader.skipValue();
			}
		}
		System.out.println(units);
	}
	
	public static List<Unit> readUnitsArray(JsonReader reader) throws IOException{
		List<Unit> units = new ArrayList<Unit>();
		
		reader.beginArray();
		while(reader.hasNext()){
			units.add(readUnit(reader));
		}
		reader.endArray();
		return units;
	}
	
	public static Unit readUnit(JsonReader reader) throws IOException{
		Unit unit = new Unit();
		
		reader.beginObject();
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals("unitID")){
				unit.setUnitId(reader.nextInt());
			}
			else if(name.equals("unitType")){
				unit.setUnitType(reader.nextString());
			}
			else if(name.equals("currentHitPoints")){
				unit.setCurrentHitPoints(reader.nextInt());
			}
			else if(name.equals("x")){
				unit.setXCoord(reader.nextInt());
			}
			else if(name.equals("y")){
				unit.setYCoord(reader.nextInt());
			}
			else if(name.equals("regionID")){
				unit.setRegionId(reader.nextInt());
			}
			else if(name.equals("armor")){
				unit.setArmor(reader.nextInt());
			}
			else if(name.equals("mineralCost")){
				unit.setMineralCost(reader.nextInt());
			}
			else if(name.equals("gasCost")){
				unit.setGasCost(reader.nextInt());
			}
			else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return unit;
	}
}
