/////////////////////////////////////////////////////////////////////////////////////////
// Name: William West                                                                  //
// Filename: BWAPIParser.java                                                          //
// Class: CSE428 - Semantic Web                                                        //
// Assignment: Final Project                                                           //
// Description:                                                                        //
/////////////////////////////////////////////////////////////////////////////////////////

import com.google.gson.stream.JsonReader;
import java.util.*;
import java.lang.*;
import java.io.*;

public class BWAPIParser{

    public static GUIInterface initiateParser() {
        GUIInterface gui = null;
        try {
            InputStream in = new FileInputStream(getLastDumpFile());
            GameState gameState = readJsonStream(in);
            JenaInterface jena = new JenaInterface("base_ontology.owl");
            jena.loadGameState(gameState);
            jena.reasonOverModel();

            gui = new GUIInterface(jena);
            
            //gui.FormatResults(gui.queryTest());
            //jena.outputToFile();

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return gui;
    }
	
	private static String getLastDumpFile(){
		BufferedReader b = new FileReader("SOMETHING log.txt");
		String s = "";
		while((s = b.readLine())!=null){}
		return s;
	}
		
	public static GameState readJsonStream(InputStream in) throws IOException{
		GameState g = new GameState();
		JsonReader reader = new JsonReader(new InputStreamReader(in));
		try{
			g = readGameState(reader);
		}
		catch(Exception e){
			System.out.println("Error: "+e);
		}
		finally{
			reader.close();
		}
		return g;
	}
	
	public static GameState readGameState(JsonReader reader) throws IOException{
		GameState gameState = new GameState();
		reader.beginObject();
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals("players")){
				gameState.setPlayers(readPlayersArray(reader));
			}
			else if(name.equals("regions")){
				gameState.setRegions(readRegionsArray(reader));
			}
			else if(name.equals("chokepoints")){
				gameState.setChokepoints(readChokepointsArray(reader));
			}
			else{
				reader.skipValue();
			}
		}
		reader.endObject();
		return gameState;
	}
	
	public static ArrayList<Player> readPlayersArray(JsonReader reader) throws IOException{
		ArrayList<Player> players = new ArrayList<Player>();
		reader.beginArray();
		while(reader.hasNext()){
			players.add(readPlayerObject(reader));
		}
		reader.endArray();
		return players;
	}
	
	public static ArrayList<Region> readRegionsArray(JsonReader reader) throws IOException{
		ArrayList<Region> regions = new ArrayList<Region>();
		reader.beginArray();
		while(reader.hasNext()){
			regions.add(readRegionObject(reader));
		}
		reader.endArray();
		return regions;
	}
	
	public static ArrayList<Chokepoint> readChokepointsArray(JsonReader reader) throws IOException{
		ArrayList<Chokepoint> chokepoints = new ArrayList<Chokepoint>();
		reader.beginArray();
		while(reader.hasNext()){
			chokepoints.add(readChokepointObject(reader));
		}
		reader.endArray();
		return chokepoints;
	}
	
	public static Region readRegionObject(JsonReader reader) throws IOException{
		Region region = new Region();
		reader.beginObject();

		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals("regionCenterX")){
				region.setRegionCenterX(reader.nextInt());
			}
			else if(name.equals("regionCenterY")){
				region.setRegionCenterY(reader.nextInt());
			}
			else if(name.equals("regionID")){
				region.setRegionId(reader.nextInt());
			}
			else{
				reader.skipValue();
			}
		}
		reader.endObject();
		return region;
	}
	
	//{"chokepointID":"0","chokepointCenterX":"2460","chokepointCenterY":"676","connectedToRegionOneID":"9","connectedToRegionTwoID":"14"}
	public static Chokepoint readChokepointObject(JsonReader reader) throws IOException{
		Chokepoint chokepoint = new Chokepoint();
		reader.beginObject();

		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals("chokepointID")){
				chokepoint.setChokepointId(reader.nextInt());
			}
			else if(name.equals("chokepointCenterX")){
				chokepoint.setChokepointCenterX(reader.nextInt());
			}
			else if(name.equals("chokepointCenterY")){
				chokepoint.setChokepointCenterY(reader.nextInt());
			}
			else if(name.equals("connectedToRegionOneID")){
				chokepoint.setConnectedToRegionOne(reader.nextInt());
			}
			else if(name.equals("connectedToRegionTwoID")){
				chokepoint.setConnectedToRegionTwo(reader.nextInt());
			}
			else{
				reader.skipValue();
			}
		}
		reader.endObject();
		return chokepoint;
	}
	
	public static Player readPlayerObject(JsonReader reader) throws IOException{
		Player player = new Player();
		Player enemyPlayer = new Player();
		reader.beginObject();
		
		ArrayList<Unit> units = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals("playerId")){
				int playerId = reader.nextInt();
				player.setPlayerId(playerId);
				enemyPlayer.setPlayerId(playerId+1);
			}
			else if(name.equals("myUnits")){
				ArrayList<Unit> unitsArray = readUnitsArray(reader);
				player.setMyUnits(unitsArray);
				//enemyPlayer.setEnemyUnits(unitsArray);
			}
			else if(name.equals("enemyUnits")){
				ArrayList<Unit> enemyUnitsArray = readUnitsArray(reader);
				//player.setEnemyUnits(enemyUnitsArray);
				enemyPlayer.setMyUnits(enemyUnitsArray);
			}
			else{
				reader.skipValue();
			}
		}
		reader.endObject();
		return player;
	}
	
	public static ArrayList<Unit> readUnitsArray(JsonReader reader) throws IOException{
		ArrayList<Unit> units = new ArrayList<Unit>();
		
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
			else if(name.equals("maxHitPoints")){
				unit.setMaxHitPoints(reader.nextInt());
			}
			else if(name.equals("isBeingAttacked")){
				int b =reader.nextInt();
				boolean isBeingAttacked = false;
				if(b==1)
					isBeingAttacked = true;
				unit.setIsBeingAttacked(isBeingAttacked);
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
