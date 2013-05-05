/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kostas Hatalis
 */
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

public class GUIInterface {

    JenaInterface jena;
    public static String NS = "";

    public GUIInterface(JenaInterface j) {
        jena = j;
        NS = jena.getNS();
    }

    public ResultSet queryEnemyAbilities() {

        // player 1 refers to the enemy

        String specificPlayerQueryString = "PREFIX sc:<" + jena.getNS() + ">"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "SELECT ?s ?p ?o "
                + "WHERE {"
                + //"?player0 rdf:type sc:Player ."+
                "?s sc:hasPlayerId 1 ."
                + "?s ?p ?o ."
                + "}";

        // run the query
        ResultSet results = jena.queryModel(specificPlayerQueryString);

        // make query object
        Query query = QueryFactory.create(specificPlayerQueryString);

        // format results and send to standard out
        //ResultSetFormatter.out(System.out, results, query);

        // Get all units that a player can build and train
       /* while (results.hasNext()) {
         QuerySolution soln = results.nextSolution();
         //System.out.println(soln);
         String matchTrain = "canTrain";
         String matchBuild = "canBuild";
         if (soln.getResource("p").toString().contains(matchTrain.subSequence(0, matchTrain.length())) ||
         soln.getResource("p").toString().contains(matchBuild.subSequence(0, matchBuild.length()))) {
         System.out.println(soln.getResource("o").toString().replace(NS,""));
         }  
         }*/

        return results;
        // System.out.println(results);
    }

    /**
     * Returns a query over all the regions that are contested
     *
     * @return
     */
    public ResultSet queryContestedRegions() {

        String queryString = "PREFIX sc:<" + jena.getNS() + ">"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "SELECT ?s ?centerX ?centerY "
                + "WHERE {"
                + //"?player0 rdf:type sc:Player ."+
                "?s rdf:type sc:ContestedRegion ."
                + "?s sc:hasCenterX ?centerX ."
                + "?s sc:hasCenterY ?centerY ."
                + "}";

        // run the query
        ResultSet results = jena.queryModel(queryString);

        // make query object
        Query query = QueryFactory.create(queryString);

        // format results and send to standard out
        //ResultSetFormatter.out(System.out, results, query);

        // Get all units that a player can build and train
       /* while (results.hasNext()) {
         QuerySolution soln = results.nextSolution();
         //System.out.println(soln);
         String matchTrain = "canTrain";
         String matchBuild = "canBuild";
         if (soln.getResource("p").toString().contains(matchTrain.subSequence(0, matchTrain.length())) ||
         soln.getResource("p").toString().contains(matchBuild.subSequence(0, matchBuild.length()))) {
         System.out.println(soln.getResource("o").toString().replace(NS,""));
         }  
         }*/

        return results;
        // System.out.println(results);
    }

    public ResultSet queryBattleRegions() {
        String queryString = "PREFIX sc:<" + jena.getNS() + ">"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "SELECT ?s ?centerX ?centerY "
                + "WHERE {"
                + //"?player0 rdf:type sc:Player ."+
                "?s rdf:type sc:BattleRegion ."
                + "?s sc:hasCenterX ?centerX ."
                + "?s sc:hasCenterY ?centerY ."
                + "}";

        // run the query
        ResultSet results = jena.queryModel(queryString);

        // make query object
        Query query = QueryFactory.create(queryString);

        // format results and send to standard out
        //ResultSetFormatter.out(System.out, results, query);

        // Get all units that a player can build and train
       /* while (results.hasNext()) {
         QuerySolution soln = results.nextSolution();
         //System.out.println(soln);
         String matchTrain = "canTrain";
         String matchBuild = "canBuild";
         if (soln.getResource("p").toString().contains(matchTrain.subSequence(0, matchTrain.length())) ||
         soln.getResource("p").toString().contains(matchBuild.subSequence(0, matchBuild.length()))) {
         System.out.println(soln.getResource("o").toString().replace(NS,""));
         }  
         }*/

        return results;
        // System.out.println(results);

    }
    
    public ResultSet queryMyUnits() {
        String queryString = "PREFIX sc:<" + jena.getNS() + ">"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "SELECT ?u ?x ?y "
                + "WHERE {"
                + "?s sc:hasPlayerId 0 ." // human is always 0
                + "?s sc:hasUnit ?u ."
                + "?u rdf:type sc:Unit ."
                + "?u sc:hasXCoord ?x ."
                + "?u sc:hasYCoord ?y ."
                + "}";

        // run the query
        ResultSet results = jena.queryModel(queryString);

        // make query object
        Query query = QueryFactory.create(queryString);

        // format results and send to standard out
        //ResultSetFormatter.out(System.out, results, query);

        // Get all units that a player can build and train
       /* while (results.hasNext()) {
         QuerySolution soln = results.nextSolution();
         //System.out.println(soln);
         String matchTrain = "canTrain";
         String matchBuild = "canBuild";
         if (soln.getResource("p").toString().contains(matchTrain.subSequence(0, matchTrain.length())) ||
         soln.getResource("p").toString().contains(matchBuild.subSequence(0, matchBuild.length()))) {
         System.out.println(soln.getResource("o").toString().replace(NS,""));
         }  
         }*/

        return results;
        // System.out.println(results);
    }
    
    public ResultSet queryEnemyUnits() {
        String queryString = "PREFIX sc:<" + jena.getNS() + ">"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "SELECT ?u ?x ?y "
                + "WHERE {"
                + "?s sc:hasPlayerId 1 ." // human is always 0
                + "?s sc:hasUnit ?u ."
                + "?u rdf:type sc:Unit ."
                + "?u sc:hasXCoord ?x ."
                + "?u sc:hasYCoord ?y ."
                + "}";

        // run the query
        ResultSet results = jena.queryModel(queryString);

        // make query object
        Query query = QueryFactory.create(queryString);

        // format results and send to standard out
        //ResultSetFormatter.out(System.out, results, query);

        // Get all units that a player can build and train
       /* while (results.hasNext()) {
         QuerySolution soln = results.nextSolution();
         //System.out.println(soln);
         String matchTrain = "canTrain";
         String matchBuild = "canBuild";
         if (soln.getResource("p").toString().contains(matchTrain.subSequence(0, matchTrain.length())) ||
         soln.getResource("p").toString().contains(matchBuild.subSequence(0, matchBuild.length()))) {
         System.out.println(soln.getResource("o").toString().replace(NS,""));
         }  
         }*/

        return results;
        // System.out.println(results);
    }
    
    public ResultSet queryArmyHealth() {
        String queryString = "PREFIX sc:<" + jena.getNS() + ">"
                + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                + "SELECT ?s ?curr ?max "
                + "WHERE {"
                + //"?player0 rdf:type sc:Player ."+
                "?s rdf:type sc:Unit ."
                + "?s sc:hasCurrentHitPoints ?curr ."
                + "?s sc:hasMaxHitPoints ?max ."
                + "?s sc:isOwnedBy sc:player0 ."
                + "}";

        // run the query
        ResultSet results = jena.queryModel(queryString);

        // make query object
        Query query = QueryFactory.create(queryString);

        // format results and send to standard out
        //ResultSetFormatter.out(System.out, results, query);

        // Get all units that a player can build and train
       /* while (results.hasNext()) {
         QuerySolution soln = results.nextSolution();
         //System.out.println(soln);
         String matchTrain = "canTrain";
         String matchBuild = "canBuild";
         if (soln.getResource("p").toString().contains(matchTrain.subSequence(0, matchTrain.length())) ||
         soln.getResource("p").toString().contains(matchBuild.subSequence(0, matchBuild.length()))) {
         System.out.println(soln.getResource("o").toString().replace(NS,""));
         }  
         }*/

        return results;
    }
    
    /* sample query
     * /System.out.println(gameState);
     String queryString = "PREFIX sc:<"+jena.getNS()+">" +
     "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
     "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
     "SELECT ?s ?p ?o " +
     "WHERE {"+
     "?s rdf:type sc:Player ."+
     "?s ?p ?o ."+
     "}";
     */
    public void FormatResults(ResultSet results) {

        // find all triples where the player is player


        String s = "something";

    }

    public boolean isContestedRegion(int regionId) {
        return true;
    }

    
}
