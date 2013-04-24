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
		
		//System.out.println("Loading "+regionIndividual+" of class type="+regionClass+" into ontology.");
		
		currentModel.createIndividual(NS+"region"+regionId, regionClass);
		currentModel.addLiteral(regionIndividual, regionIdProperty, regionIdValue);
		currentModel.addLiteral(regionIndividual, hasCenterXProperty, centerXValue);
		currentModel.addLiteral(regionIndividual, hasCenterYProperty, centerYValue);
	}
	
	private void loadPlayer(Player player){
		int playerId = player.getPlayerId();
		
		loadUnitsList(player.getUnits());
		
		Resource playerClass = r("Player");
		Resource playerIndividual = r("player"+playerId);
		Property playerIdProperty = p("hasPlayerId");
		Property hasUnitProperty = p("hasUnit");
		Literal playerIdValue = l(playerId);
		
		currentModel.createIndividual(NS+"player"+playerId, playerClass);
		currentModel.addLiteral(playerIndividual, playerIdProperty, playerIdValue);
		
		for(Unit u : player.getUnits()){
			Resource unitIndividual = r("unit"+u.getUnitId());
			currentModel.add(playerIndividual, hasUnitProperty, unitIndividual);
		}
	}
	
	private void loadUnitsList(ArrayList<Unit> units){
	
	}
	
	private void loadUnit(Unit unit){
	
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
