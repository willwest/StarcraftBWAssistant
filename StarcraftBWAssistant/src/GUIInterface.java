
/**
 * Author: Kostas Hatalis 
 * Filename: GUIInterface.java 
 * Class: CSE428 - Semantic Web 
 * Assignment: Final Project 
 * Description:	Class representation of code that
 * retrieves data to be processed and later displayed by the gui class. Contains
 * all of the SPARQL queries.
 */
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;

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

        return results;
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

        return results;
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

        return results;
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

        return results;
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

        return results;
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

        return results;
    }

    public boolean isContestedRegion(int regionId) {
        return true;
    }
}
