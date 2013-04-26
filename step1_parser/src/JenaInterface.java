import java.io.*;
import java.util.*;

import org.mindswap.pellet.jena.PelletReasonerFactory;

import com.hp.hpl.jena.rdf.model.impl.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.*;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.hp.hpl.jena.reasoner.*;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.datatypes.*;

public class JenaInterface{
	private static final String BASE = "http://insyte.cse.lehigh.edu/starcraft_ontology";
	private static final String NS = "http://insyte.cse.lehigh.edu/starcraft_ontology#";
	private OntModel currentModel;
	
	public JenaInterface(String s) throws Exception{
		importOntology(s);
	}
	
	private void importOntology(String path) throws Exception{
		System.out.println("Importing Ontology.");
		OntModel model = ModelFactory.createOntologyModel();
		InputStream in = FileManager.get().open(path);
		model.read(in, BASE);
		in.close();
		currentModel = model;
	}
	
	public String getBase(){
		return BASE;
	}
	
	public String getNS(){
		return NS;
	}
	
	public OntModel getModel(){
		return currentModel;
	}
	
	public void loadGameState(GameState gameState){
		loadRegionList(gameState.getRegions());
		loadPlayersList(gameState.getPlayers());
		loadChokepointsList(gameState.getChokepoints());
	}
	
	private void loadRegionList(ArrayList<Region> regions){	
		for(Region r : regions){
			loadRegion(r);
		}
	}
	
	private void loadPlayersList(ArrayList<Player> players){
		for(Player p : players){
			loadPlayer(p);
		}
	}
	
	private void loadChokepointsList(ArrayList<Chokepoint> chokepoints){
		for(Chokepoint c : chokepoints){
			loadChokepoint(c);
		}
	}
	
	private void loadRegion(Region region){
		int regionId = region.getRegionId();
		int regionCenterX = region.getRegionCenterX();
		int regionCenterY = region.getRegionCenterY();

		Resource regionClass = r("Region");
		Resource regionIndividual = r("region"+regionId);
		Property regionIdProperty = p("hasRegionId");
		Property hasCenterXProperty = p("hasCenterX");
		Property hasCenterYProperty = p("hasCenterY");
		Literal regionIdValue = l(regionId);
		Literal centerXValue = l(regionCenterX);
		Literal centerYValue = l(regionCenterY);

		currentModel.createIndividual(NS+"region"+regionId, regionClass);
		currentModel.addLiteral(regionIndividual, regionIdProperty, regionIdValue);
		currentModel.addLiteral(regionIndividual, hasCenterXProperty, centerXValue);
		currentModel.addLiteral(regionIndividual, hasCenterYProperty, centerYValue);
	}
	
	private void loadChokepoint(Chokepoint chokepoint){
		int chokepointId = chokepoint.getChokepointId();
		int chokepointCenterX = chokepoint.getChokepointCenterX();
		int chokepointCenterY = chokepoint.getChokepointCenterY();
		int connectedToRegionOneId = chokepoint.getConnectedToRegionOneId();
		int connectedToRegionTwoId = chokepoint.getConnectedToRegionTwoId();
		
		Resource chokepointClass = r("Chokepoint");
		Resource chokepointIndividual = r("chokepoint"+chokepointId);
		Property chokepointIdProperty = p("hasChokepointId");
		Property chokepointCenterXProperty = p("hasChokepointCenterX");
		Property chokepointCenterYProperty = p("hasChokepointCenterY");
		Property connectedToRegionOneIdProperty = p("isConnectedToRegionOneId");
		Property connectedToRegionTwoIdProperty = p("isConnectedToRegionTwoId");
		
		Literal chokepointIdValue = l(chokepointId);
		Literal chokepointCenterXValue = l(chokepointCenterX);
		Literal chokepointCenterYValue = l(chokepointCenterY);
		Literal connectedToRegionOneIdValue = l(connectedToRegionOneId);
		Literal connectedToRegionTwoIdValue = l(connectedToRegionTwoId);
		
		currentModel.createIndividual(NS+"chokepoint"+chokepointId, chokepointClass);
		currentModel.addLiteral(chokepointIndividual, chokepointIdProperty, chokepointIdValue);
		currentModel.addLiteral(chokepointIndividual, chokepointCenterXProperty, chokepointCenterXValue);
		currentModel.addLiteral(chokepointIndividual, chokepointCenterYProperty, chokepointCenterYValue);
		currentModel.addLiteral(chokepointIndividual, connectedToRegionOneIdProperty, connectedToRegionOneIdValue);
		currentModel.addLiteral(chokepointIndividual, connectedToRegionTwoIdProperty, connectedToRegionTwoIdValue);
	}
	
	private void loadPlayer(Player player){
		int playerId = player.getPlayerId();
		
		loadUnitsList(player.getMyUnits());
		loadUnitsList(player.getEnemyUnits());
		
		Resource playerClass = r("Player");
		Resource playerIndividual = r("player"+playerId);
		Property playerIdProperty = p("hasPlayerId");
		Property hasUnitProperty = p("hasUnit");
		Property hasEnemyUnitProperty = p("hasEnemyUnit");
		Literal playerIdValue = l(playerId);
		
		currentModel.createIndividual(NS+"player"+playerId, playerClass);
		currentModel.addLiteral(playerIndividual, playerIdProperty, playerIdValue);
		
		for(Unit u : player.getMyUnits()){
			Resource unitIndividual = r("unit"+u.getUnitId());
			currentModel.add(playerIndividual, hasUnitProperty, unitIndividual);
		}
		
		for(Unit u : player.getEnemyUnits()){
			Resource unitIndividual = r("unit"+u.getUnitId());
			currentModel.add(playerIndividual, hasEnemyUnitProperty, unitIndividual);
		}
	}
	
	private void loadUnitsList(ArrayList<Unit> units){
		for(Unit u : units){
			loadUnit(u);
		}
	}
	
	private void loadUnit(Unit unit){
		int unitId = unit.getUnitId();
		int unitTypeId = unit.getUnitTypeId();
		int currentHitPoints = unit.getCurrentHitPoints();
		int maxHitPoints = unit.getMaxHitPoints();
		int xCoord = unit.getXCoord();
		int yCoord = unit.getYCoord();
		int regionId = unit.getRegionId();
		int armor = unit.getArmor();
		int mineralCost = unit.getMineralCost();
		int gasCost = unit.getGasCost();
		
		Literal unitIdValue = l(unitId);
		Literal unitTypeIdValue = l(unitTypeId);
		Literal currentHitPointsValue = l(currentHitPoints);
		Literal maxHitPointsValue = l(maxHitPoints);
		Literal xCoordValue = l(xCoord);
		Literal yCoordValue = l(yCoord);
		Literal regionIdValue = l(regionId);
		Literal armorValue = l(armor);
		Literal mineralCostValue = l(mineralCost);
		Literal gasCostValue = l(gasCost);
		
		Resource unitClass = r("Unit");
		Resource unitIndividual = r("unit"+unitId);
		
		Property unitIdProperty = p("hasUnitIdProperty");
		Property unitTypeIdProperty = p("hasUnitTypeId");
		Property currentHitPointsProperty = p("hasCurrentHitPoints");
		Property maxHitPointsProperty = p("hasMaxHitPoints");
		Property xCoordProperty = p("hasXCoord");
		Property yCoordProperty = p("hasYCoord");
		//Bug[WW]: this should be changed to not conflict with the property of the same name in the Region class
		Property regionIdProperty = p("hasRegionId"); 
		//END NOTE
		Property armorProperty = p("hasArmor");
		Property mineralCostProperty = p("hasMineralCost");
		Property gasCostProperty = p("hasGasCost");
		
		currentModel.createIndividual(NS+"unit"+unitId, unitClass);
		
		currentModel.addLiteral(unitIndividual, unitIdProperty, unitIdValue);
		currentModel.addLiteral(unitIndividual, unitTypeIdProperty, unitTypeIdValue);
		currentModel.addLiteral(unitIndividual, currentHitPointsProperty, currentHitPointsValue);
		currentModel.addLiteral(unitIndividual, maxHitPointsProperty, maxHitPointsValue);
		currentModel.addLiteral(unitIndividual, xCoordProperty, xCoordValue);
		currentModel.addLiteral(unitIndividual, yCoordProperty, yCoordValue);
		currentModel.addLiteral(unitIndividual, regionIdProperty, regionIdValue);
		currentModel.addLiteral(unitIndividual, armorProperty, armorValue);
		currentModel.addLiteral(unitIndividual, mineralCostProperty, mineralCostValue);
		currentModel.addLiteral(unitIndividual, gasCostProperty, gasCostValue);	
	}
	
	public void queryModel(String queryString){
		Query query = QueryFactory.create(queryString);
		QueryExecution qe = QueryExecutionFactory.create(query, currentModel);
		ResultSet results = qe.execSelect();
		ResultSetFormatter.out(System.out, results, query);
		qe.close();
	}
	
	public void reasonOverModel(){
		Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
			
		//PELLET CITATION: http://allthingssemantic.blogspot.com/2012/04/configuring-pellet-reasoner-and-jena.html
		//				   blog post, helped a lot with integrating pellet with Jena
		
		//bind the reasoner to the ontology model
		reasoner = reasoner.bindSchema(currentModel);

		System.out.println("Reasoning using OWL Reasoner.");
		//Bind the reasoner to the data model into a new Inferred model
		InfModel infModel = ModelFactory.createInfModel(reasoner,currentModel);
		
		System.out.println("Reasoning using Pellet Reasoner.");
		//Create pellet reasoner and bind to inferred model
		Reasoner pelletReasoner = PelletReasonerFactory.theInstance().create();
		InfModel pelletInfModel = ModelFactory.createInfModel(pelletReasoner,currentModel);
		
		OntModel finalModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM, pelletInfModel);
		currentModel = finalModel;
	}
	
	//BEGIN CITATION: 
	//URL: https://github.com/castagna/jena-examples/blob/master/src/main/java/org/apache/jena/examples/ExampleDataTypes_01.java
	
	private static Resource r ( String localname ) {
        return ResourceFactory.createResource ( NS + localname );
    }
    
    private static Property p ( String localname ) {
        return ResourceFactory.createProperty ( NS, localname );
    }

    private static Literal l ( Object value ) {
        return ResourceFactory.createTypedLiteral ( value );
    }

    private static Literal l ( String lexicalform, RDFDatatype datatype ) {
        return ResourceFactory.createTypedLiteral ( lexicalform, datatype );
    }
    
    //END CITATION
}
