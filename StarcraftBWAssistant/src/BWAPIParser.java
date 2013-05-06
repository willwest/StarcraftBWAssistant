
/**
 * Author: William West Filename: BWAPIParser.java Class: CSE428 - Semantic Web
 * Assignment: Final Project Description:	The driver class that also acts as a
 * JSON parser. From this class, the base model is created, and all GameState,
 * Player, Region, Chokepoint, and Unit objects are created.
 */
import com.google.gson.stream.JsonReader;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BWAPIParser {

    private static final String INPUT_PATH = "C:\\Users\\dustin\\Dropbox\\StarcraftBWAssistantParserBranch\\StarcraftBWAssistant\\gameStateDumps\\log.txt";

    public static void main(String[] args) {
    }

    /**
     * Initiates all operations related to the parser/gui. First fetches input
     * file, then parses JSON file, adds it to the base ontology, and reasons
     * over it using the Pellet reasoner. Calls the GUI and passes all model
     * information to it.
     *
     * @return	the GUIInterface object with the attached model.
     */
    public static GUIInterface initiateParser() {
        GUIInterface gui = null;
        try {
            System.out.println("Getting log file");
            String lastDumpFile = getLastDumpFile();
            System.out.println(lastDumpFile);
            InputStream in = new FileInputStream(lastDumpFile);
            System.out.println("Getting gamestate");
            GameState gameState = readJsonStream(in);
            System.out.println("Getting Base ontology");
            JenaInterface jena = new JenaInterface("base_ontology.owl");
            System.out.println("Loading Gamestate");
            jena.loadGameState(gameState);
            System.out.println("Reasoning over model");
            jena.reasonOverModel();

            gui = new GUIInterface(jena);

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return gui;
    }

    /**
     * The output of the StarCraft API provides a log file with a list of
     * gamestate output file paths. This function returns a String representing
     * the latest gamestate output file.
     *
     * @return	String representing the latest gamestate output file.
     */
    public static String getLastDumpFile() {
        String s = "";
        String lastLine = "";
        try {
            BufferedReader b = new BufferedReader(new FileReader(INPUT_PATH));

            while ((s = b.readLine()) != null) {
                lastLine = s;
            }
            System.out.println(lastLine);
        } catch (IOException ex) {
            Logger.getLogger(BWAPIParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastLine;
    }

    /**
     * This function takes as input an InputStream of a JSON gamestate output
     * file and uses a JsonReader parser to extract the gamestate object (the
     * entire document)
     *
     * @param in	the InputStream of the gamestate output file
     * @return	the GameState object associated with this gamestate document
     */
    public static GameState readJsonStream(InputStream in) throws IOException {
        GameState g = new GameState();
        JsonReader reader = new JsonReader(new InputStreamReader(in));
        try {
            g = readGameState(reader);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            reader.close();
        }
        return g;
    }

    /**
     * Extracts the lists of Player, Region, and Chokepoint objects and adds
     * them to the GameState object associated with this JSON file.
     *
     * @param reader	the JsonReader object containing the Gamestate object JSON.
     * @return gameState	the GameState object containing the Players, Regions,
     * and Checkpoints of the game.
     */
    public static GameState readGameState(JsonReader reader) throws IOException {
        GameState gameState = new GameState();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("players")) {
                gameState.setPlayers(readPlayersArray(reader));
            } else if (name.equals("regions")) {
                gameState.setRegions(readRegionsArray(reader));
            } else if (name.equals("chokepoints")) {
                gameState.setChokepoints(readChokepointsArray(reader));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return gameState;
    }

    /**
     * Reads a Players array in JSON format. The array can contain any number of
     * players, but at its current state it assumes that there are two, one
     * friendly and one enemy.
     *
     * @param reader	a JsonReader object currently pointed to the Player array.
     * @return players	an ArrayList<Player> containing all players in the JSON
     * array.
     */
    public static ArrayList<Player> readPlayersArray(JsonReader reader) throws IOException {
        ArrayList<Player> players = new ArrayList<Player>();
        reader.beginArray();
        while (reader.hasNext()) {
            players.add(readPlayerObject(reader));
        }
        reader.endArray();
        return players;
    }

    /**
     * Reads a Region array in JSON format. The array can contain any number of
     * regions.
     *
     * @param reader	a JsonReader pointed to an array of Regions.
     * @return regions	returns an ArrayList<Region> of Region objects.
     */
    public static ArrayList<Region> readRegionsArray(JsonReader reader) throws IOException {
        ArrayList<Region> regions = new ArrayList<Region>();
        reader.beginArray();
        while (reader.hasNext()) {
            regions.add(readRegionObject(reader));
        }
        reader.endArray();
        return regions;
    }

    /**
     * Reads a Chokepoint array in JSON format. The array can contain any number
     * of chokepoints.
     *
     * @param reader	a JsonReader pointed to an array of Chokepoint objects.
     * @return chokepoints	an ArrayList<Chokepoint> of chokepoint objects.
     */
    public static ArrayList<Chokepoint> readChokepointsArray(JsonReader reader) throws IOException {
        ArrayList<Chokepoint> chokepoints = new ArrayList<Chokepoint>();
        reader.beginArray();
        while (reader.hasNext()) {
            chokepoints.add(readChokepointObject(reader));
        }
        reader.endArray();
        return chokepoints;
    }

    /**
     * Reads a Region object in JSON format.
     *
     * @param reader	a JsonReader pointed to a Region object
     * @return region	a Region object
     */
    public static Region readRegionObject(JsonReader reader) throws IOException {
        Region region = new Region();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("regionCenterX")) {
                region.setRegionCenterX(reader.nextInt());
            } else if (name.equals("regionCenterY")) {
                region.setRegionCenterY(reader.nextInt());
            } else if (name.equals("regionID")) {
                region.setRegionId(reader.nextInt());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return region;
    }

    /**
     * Reads a Chokepiont object in JSON format.
     *
     * @param reader	a JsonReader pointed to a Chokepoint object
     * @return chokepoint	a Chokepoint object
     */
    public static Chokepoint readChokepointObject(JsonReader reader) throws IOException {
        Chokepoint chokepoint = new Chokepoint();
        reader.beginObject();

        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("chokepointID")) {
                chokepoint.setChokepointId(reader.nextInt());
            } else if (name.equals("chokepointCenterX")) {
                chokepoint.setChokepointCenterX(reader.nextInt());
            } else if (name.equals("chokepointCenterY")) {
                chokepoint.setChokepointCenterY(reader.nextInt());
            } else if (name.equals("connectedToRegionOneID")) {
                chokepoint.setConnectedToRegionOne(reader.nextInt());
            } else if (name.equals("connectedToRegionTwoID")) {
                chokepoint.setConnectedToRegionTwo(reader.nextInt());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return chokepoint;
    }

    /**
     * Reads a Player object in a JSON format
     *
     * @param reader	a JsonReader object pointed to a Player object
     * @return player	a Player object.
     */
    public static Player readPlayerObject(JsonReader reader) throws IOException {
        Player player = new Player();
        Player enemyPlayer = new Player();
        reader.beginObject();

        ArrayList<Unit> units = null;
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("playerId")) {
                int playerId = reader.nextInt();
                player.setPlayerId(playerId);
                enemyPlayer.setPlayerId(playerId + 1);
            } else if (name.equals("myUnits")) {
                ArrayList<Unit> unitsArray = readUnitsArray(reader);
                player.setMyUnits(unitsArray);
            } else if (name.equals("enemyUnits")) {
                ArrayList<Unit> enemyUnitsArray = readUnitsArray(reader);
                enemyPlayer.setMyUnits(enemyUnitsArray);
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return player;
    }

    /**
     * Reads an array of Units in JSON format
     *
     * @param	reader	a JsonReader object pointed to an array of Units
     * @return units	an ArrayList<Unit> of Unit objects contained in the array
     */
    public static ArrayList<Unit> readUnitsArray(JsonReader reader) throws IOException {
        ArrayList<Unit> units = new ArrayList<Unit>();

        reader.beginArray();
        while (reader.hasNext()) {
            units.add(readUnit(reader));
        }
        reader.endArray();
        return units;
    }

    /**
     * Reads a Unit object, capturing all attributes in the object.
     *
     * @param reader	a JsonReader object pointed to an array of Units
     * @return units	a Unit object
     */
    public static Unit readUnit(JsonReader reader) throws IOException {
        Unit unit = new Unit();

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("unitID")) {
                unit.setUnitId(reader.nextInt());
            } else if (name.equals("unitType")) {
                unit.setUnitType(reader.nextString());
            } else if (name.equals("currentHitPoints")) {
                unit.setCurrentHitPoints(reader.nextInt());
            } else if (name.equals("maxHitPoints")) {
                unit.setMaxHitPoints(reader.nextInt());
            } else if (name.equals("isBeingAttacked")) {
                int b = reader.nextInt();
                boolean isBeingAttacked = false;
                if (b == 1) {
                    isBeingAttacked = true;
                }
                unit.setIsBeingAttacked(isBeingAttacked);
            } else if (name.equals("x")) {
                unit.setXCoord(reader.nextInt());
            } else if (name.equals("y")) {
                unit.setYCoord(reader.nextInt());
            } else if (name.equals("regionID")) {
                unit.setRegionId(reader.nextInt());
            } else if (name.equals("armor")) {
                unit.setArmor(reader.nextInt());
            } else if (name.equals("mineralCost")) {
                unit.setMineralCost(reader.nextInt());
            } else if (name.equals("gasCost")) {
                unit.setGasCost(reader.nextInt());
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return unit;
    }
}
