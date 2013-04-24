import com.google.gson.stream.JsonReader;
import java.util.*;
import java.lang.*;
import java.io.*;

public class BWAPIParser{
	public static void main(String[] args){
		String inputFile = args[0];
		try{
			InputStream in = new FileInputStream(inputFile);
			GameState gameState = readJsonStream(in);
			JenaInterface jena = new JenaInterface("starcraft_ontology_no_individuals.owl");
			jena.loadGameState(gameState);
			jena.reasonOverModel();
			System.out.println(gameState);
			String queryString = "PREFIX sc:<"+jena.getNS()+">" +
			"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
			"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
			"SELECT ?s ?p ?o " +
			"WHERE {"+
			"?s rdf:type sc:Player ."+
			"?s ?p ?o ."+
			"}";
			jena.queryModel(queryString);
			
		}catch(Exception e){
			System.out.println("Error: "+e);
		}
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
	
	public static Player readPlayerObject(JsonReader reader) throws IOException{
		Player player = new Player();
		reader.beginObject();
		
		ArrayList<Unit> units = null;
		while(reader.hasNext()){
			String name = reader.nextName();
			if(name.equals("playerId")){
				player.setPlayerId(reader.nextInt());
			}
			else if(name.equals("units")){
				player.setUnits(readUnitsArray(reader));
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