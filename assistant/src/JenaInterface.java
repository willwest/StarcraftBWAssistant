
/**
 * Author: William West
 * Filename: JenaInterface.java
 * Class: CSE428 - Semantic Web
 * Assignment: Final Project
 * Description:	An object that allows for
 * interfacing with Jena and the model used to represent a GameState object.
 * Contains methods that are used to directly manipulate the model and query its
 * contents.
 */
import com.hp.hpl.jena.datatypes.*;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.reasoner.*;
import com.hp.hpl.jena.util.*;
import java.io.*;
import java.util.*;
import org.mindswap.pellet.jena.PelletReasonerFactory;

public class JenaInterface {

    private static final String BASE = "http://insyte.cse.lehigh.edu/starcraft_ontology";
    private static final String NS = "http://insyte.cse.lehigh.edu/starcraft_ontology#";
    private OntModel currentModel;

    public JenaInterface(String s) throws Exception {
        importOntology(s);
    }

    /**
     * Given a path to a base ontology file in RDF XML format, imports the file
     * and adds the model to currentModel
     */
    private void importOntology(String path) throws Exception {
        System.out.println("Importing Ontology.");
        OntModel model = ModelFactory.createOntologyModel();
        InputStream in = FileManager.get().open(path);
        model.read(in, BASE);
        in.close();
        currentModel = model;
    }

    /**
     * @return BASE	the base URI for the ontology in currentModel
     */
    public String getBase() {
        return BASE;
    }

    /**
     * @return NS	the namespace for the ontology in currentModel
     */
    public String getNS() {
        return NS;
    }

    /**
     * @return currentModel	the ontology model represented by this JenaInterface
     * object
     *
     */
    public OntModel getModel() {
        return currentModel;
    }

    /**
     * Loads a GameState object into currentModel, including all players,
     * regions, and checkpoints.
     *
     * @param gameState	a GameState object to be loaded into the model
     */
    public void loadGameState(GameState gameState) {
        loadRegionList(gameState.getRegions());
        loadPlayersList(gameState.getPlayers());
        loadChokepointsList(gameState.getChokepoints());
    }

    /**
     * Loads an array of Region objects into currentModel.
     *
     * @param regions
     */
    private void loadRegionList(ArrayList<Region> regions) {
        for (Region r : regions) {
            loadRegion(r);
        }
    }

    /**
     * Loads an array of Player objects into currentModel.
     *
     * @param players
     */
    private void loadPlayersList(ArrayList<Player> players) {
        for (Player p : players) {
            loadPlayer(p);
        }
    }

    /**
     * Loads an array of Chokepoints into currentModel.
     *
     * @param chokepoints
     */
    private void loadChokepointsList(ArrayList<Chokepoint> chokepoints) {
        for (Chokepoint c : chokepoints) {
            loadChokepoint(c);
        }
    }

    /**
     * Loads an arary of Unit objects into currentModel
     *
     * @param units	an ArrayList<Unit> of units to be added to the model
     */
    private void loadUnitsList(ArrayList<Unit> units) {
        for (Unit u : units) {
            loadUnit(u);
        }
    }

    /**
     * Loads a Player object and all associated attributes into currentModel
     *
     * @param player	a Player object to be added to the model
     */
    private void loadPlayer(Player player) {
        int playerId = player.getPlayerId();

        if (player.getMyUnits() != null) {
            loadUnitsList(player.getMyUnits());
        }
        Resource playerClass = r("Player");
        Resource playerIndividual = r("player" + playerId);
        Property playerIdProperty = p("hasPlayerId");
        Property hasUnitProperty = p("hasUnit");
        Property hasEnemyUnitProperty = p("hasEnemyUnit");
        Literal playerIdValue = l(playerId);

        currentModel.createIndividual(NS + "player" + playerId, playerClass);
        currentModel.addLiteral(playerIndividual, playerIdProperty, playerIdValue);

        if (player.getMyUnits() != null) {
            for (Unit u : player.getMyUnits()) {
                Resource unitIndividual = r("unit" + u.getUnitId());
                currentModel.add(playerIndividual, hasUnitProperty, unitIndividual);
            }
        }
    }

    /**
     * Loads a Region object and all associated attributes into currentModel
     *
     * @param region	a Region object to be added to the model
     */
    private void loadRegion(Region region) {
        int regionId = region.getRegionId();
        int regionCenterX = region.getRegionCenterX();
        int regionCenterY = region.getRegionCenterY();

        Resource regionClass = r("Region");
        Resource regionIndividual = r("region" + regionId);
        Property regionIdProperty = p("hasRegionId");
        Property hasCenterXProperty = p("hasCenterX");
        Property hasCenterYProperty = p("hasCenterY");
        Literal regionIdValue = l(regionId);
        Literal centerXValue = l(regionCenterX);
        Literal centerYValue = l(regionCenterY);

        currentModel.createIndividual(NS + "region" + regionId, regionClass);
        currentModel.addLiteral(regionIndividual, regionIdProperty, regionIdValue);
        currentModel.addLiteral(regionIndividual, hasCenterXProperty, centerXValue);
        currentModel.addLiteral(regionIndividual, hasCenterYProperty, centerYValue);
    }

    /**
     * Loads a Chokepoint object and all associated attributes into currentModel
     *
     * @param chokepoint	a Chokepoint object to be added to the model
     */
    private void loadChokepoint(Chokepoint chokepoint) {
        int chokepointId = chokepoint.getChokepointId();
        int chokepointCenterX = chokepoint.getChokepointCenterX();
        int chokepointCenterY = chokepoint.getChokepointCenterY();
        int connectedToRegionOne = chokepoint.getConnectedToRegionOne();
        int connectedToRegionTwo = chokepoint.getConnectedToRegionTwo();

        Resource chokepointClass = r("Chokepoint");
        Resource chokepointIndividual = r("chokepoint" + chokepointId);
        Property chokepointIdProperty = p("hasChokepointId");
        Property chokepointCenterXProperty = p("hasChokepointCenterX");
        Property chokepointCenterYProperty = p("hasChokepointCenterY");
        Property connectedToRegionOneProperty = p("isConnectedToRegionOne");
        Property connectedToRegionTwoProperty = p("isConnectedToRegionTwo");

        Resource connectedToRegionOneValue = r("region" + connectedToRegionOne);
        Resource connectedToRegionTwoValue = r("region" + connectedToRegionTwo);
        Literal chokepointIdValue = l(chokepointId);
        Literal chokepointCenterXValue = l(chokepointCenterX);
        Literal chokepointCenterYValue = l(chokepointCenterY);

        currentModel.createIndividual(NS + "chokepoint" + chokepointId, chokepointClass);
        currentModel.addLiteral(chokepointIndividual, chokepointIdProperty, chokepointIdValue);
        currentModel.addLiteral(chokepointIndividual, chokepointCenterXProperty, chokepointCenterXValue);
        currentModel.addLiteral(chokepointIndividual, chokepointCenterYProperty, chokepointCenterYValue);
        currentModel.add(chokepointIndividual, connectedToRegionOneProperty, connectedToRegionOneValue);
        currentModel.add(chokepointIndividual, connectedToRegionTwoProperty, connectedToRegionTwoValue);
    }

    /**
     * Loads a Unit object and all associated attributes into currentModel
     *
     * @param unit	a Unit object to be added to the model
     */
    private void loadUnit(Unit unit) {
        int unitId = unit.getUnitId();
        String unitType = unit.getUnitType();
        int currentHitPoints = unit.getCurrentHitPoints();
        int maxHitPoints = unit.getMaxHitPoints();
        boolean isBeingAttacked = unit.getIsBeingAttacked();
        int xCoord = unit.getXCoord();
        int yCoord = unit.getYCoord();
        int regionId = unit.getRegionId();
        int armor = unit.getArmor();
        int mineralCost = unit.getMineralCost();
        int gasCost = unit.getGasCost();

        unitType = processUnitTypeString(unitType);
        System.out.println(unitType);
        Resource unitSubClass = r(unitType);

        Resource isInRegionValue = r("region" + regionId);
        Literal unitIdValue = l(unitId);
        Literal currentHitPointsValue = l(currentHitPoints);
        Literal maxHitPointsValue = l(maxHitPoints);
        Literal isBeingAttackedValue = l(isBeingAttacked);
        Literal xCoordValue = l(xCoord);
        Literal yCoordValue = l(yCoord);
        Literal regionIdValue = l(regionId);
        Literal armorValue = l(armor);
        Literal mineralCostValue = l(mineralCost);
        Literal gasCostValue = l(gasCost);

        Resource unitClass = r("Unit");
        Resource unitIndividual = r("unit" + unitId);

        Property unitIdProperty = p("hasUnitId");
        Property currentHitPointsProperty = p("hasCurrentHitPoints");
        Property maxHitPointsProperty = p("hasMaxHitPoints");
        Property isBeingAttackedProperty = p("isBeingAttacked");
        Property xCoordProperty = p("hasXCoord");
        Property yCoordProperty = p("hasYCoord");
        Property isInRegionProperty = p("isInRegion");
        Property armorProperty = p("hasArmor");
        Property mineralCostProperty = p("hasMineralCost");
        Property gasCostProperty = p("hasGasCost");

        currentModel.createIndividual(NS + "unit" + unitId, unitSubClass);

        currentModel.addLiteral(unitIndividual, unitIdProperty, unitIdValue);
        currentModel.addLiteral(unitIndividual, currentHitPointsProperty, currentHitPointsValue);
        currentModel.addLiteral(unitIndividual, maxHitPointsProperty, maxHitPointsValue);
        currentModel.addLiteral(unitIndividual, isBeingAttackedProperty, isBeingAttackedValue);
        currentModel.addLiteral(unitIndividual, xCoordProperty, xCoordValue);
        currentModel.addLiteral(unitIndividual, yCoordProperty, yCoordValue);
        currentModel.add(unitIndividual, isInRegionProperty, isInRegionValue);
        currentModel.addLiteral(unitIndividual, armorProperty, armorValue);
        currentModel.addLiteral(unitIndividual, mineralCostProperty, mineralCostValue);
        currentModel.addLiteral(unitIndividual, gasCostProperty, gasCostValue);
    }

    /**
     * Allows for SPARQL queries over the model by external objects
     *
     * @param queryString	a query to be submitted to the model
     * @return resuls	a ResultSet obtained by querying the model
     */
    public ResultSet queryModel(String queryString) {
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, currentModel);
        ResultSet results = qe.execSelect();
        return results;
    }

    /**
     * Uses a Pellet reasonser to obtain inferences over currentModel. The
     * reasoner used was obtained from: http://clarkparsia.com/pellet/
     *
     * Some code was used directly and indirectly from the following tutorial:
     * http://allthingssemantic.blogspot.com/2012/04/configuring-pellet-reasoner-and-jena.html
     */
    public void reasonOverModel() {
        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();

        //bind the reasoner to the ontology model
        reasoner = reasoner.bindSchema(currentModel);

        //Bind the reasoner to the data model into a new Inferred model
        System.out.println("Reasoning using OWL Reasoner.");
        InfModel infModel = ModelFactory.createInfModel(reasoner, currentModel);

        //Create pellet reasoner and bind to inferred model
        System.out.println("Reasoning using Pellet Reasoner.");
        Reasoner pelletReasoner = PelletReasonerFactory.theInstance().create();
        InfModel pelletInfModel = ModelFactory.createInfModel(pelletReasoner, currentModel);

        OntModel finalModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, pelletInfModel);
        currentModel = finalModel;
    }

    /**
     * Utility used to clean unit type data obtained from the BWAPI.
     *
     * @param rawString	an unprocessed string obtained by the BWAPI
     * @return rawString	the processed string to be added to the model
     */
    private String processUnitTypeString(String rawString) {
        rawString = rawString.replaceAll("\\s", "");
        rawString = rawString.replace("Terran", "");
        rawString = rawString.replace("SiegeMode", "");
        rawString = rawString.replace("TankMode", "");
        rawString = rawString.replace("SpiderMine", "");
        return rawString;
    }

    /**
     * Writes currentModel to a file in RDF/XML format. Note that some OWL
     * axioms are not supported by Jena and will not be printed properly. Refer
     * to reported errors for any issues
     */
    public void outputToFile() throws Exception {
        FileOutputStream fout = new FileOutputStream("output_model.owl");
        currentModel.write(fout);
    }

    /**
     * Utility to take a resource string and return a Resource object. Obtained
     * from:
     * https://github.com/castagna/jena-examples/blob/master/src/main/java/org/apache/jena/examples/ExampleDataTypes_01.java
     *
     * @param localname	the string representation of a Resource in currentModel
     * @return Resource	a resource object
     */
    private static Resource r(String localname) {
        return ResourceFactory.createResource(NS + localname);
    }

    /**
     * Utility to take a property string and return a Property object. Obtained
     * from:
     * https://github.com/castagna/jena-examples/blob/master/src/main/java/org/apache/jena/examples/ExampleDataTypes_01.java
     *
     * @param localname	the string representation of a Property in currentModel
     * @return Property	a property object
     */
    private static Property p(String localname) {
        return ResourceFactory.createProperty(NS, localname);
    }

    /**
     * Utility to take a literal value and return a Literal object. Obtained
     * from:
     * https://github.com/castagna/jena-examples/blob/master/src/main/java/org/apache/jena/examples/ExampleDataTypes_01.java
     *
     * @param value	the value of a Literal to be added to currentModel
     * @return Literal	a literal object
     */
    private static Literal l(Object value) {
        return ResourceFactory.createTypedLiteral(value);
    }

    /**
     * Utility to take a literal string and return a Literal object. Obtained
     * from:
     * https://github.com/castagna/jena-examples/blob/master/src/main/java/org/apache/jena/examples/ExampleDataTypes_01.java
     *
     * @param lexicalform	the string representation of a Literal in currentModel
     * @param datatype	the datatype of the String
     * @return Resource	a literal object
     */
    private static Literal l(String lexicalform, RDFDatatype datatype) {
        return ResourceFactory.createTypedLiteral(lexicalform, datatype);
    }
}
