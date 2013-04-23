import java.io.*;
import java.util.*;

import com.hp.hpl.jena.rdf.model.impl.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.util.*;
import com.hp.hpl.jena.vocabulary.RDF;
import com.hp.hpl.jena.ontology.*;

public class JenaInterface{
	
	public JenaInterface(String s){
		importOntology(s);
	}
	
	private void importOntology(String path){
		//org.openjena.atlas.logging.Log.setLog4j("jena-log4j.properties");
		OntModel model = ModelFactory.createOntologyModel();
		InputStream in = FileManager.get().open(path);
		model.read(in, null);
	}
}
