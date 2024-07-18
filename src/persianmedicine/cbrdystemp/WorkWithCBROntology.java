package persianmedicine.cbrdystemp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;

public class WorkWithCBROntology {
	public OntoBridge ob;
	String firstURI = "http://www.semanticweb.org/hsh/ontologies/2019/7/CBRDystempOnto#";
	int firstURILen;
	public WorkWithCBROntology() {
		ob = new OntoBridge();
		ob.initWithPelletReasoner();
		
		OntologyDocument mainOnto = new OntologyDocument("http://www.semanticweb.org/hsh/ontologies/2019/7/CBRDystempOnto","file:src/persianmedicine/cbrdystemp/dystemponto.owl");		
		ArrayList<OntologyDocument> subOntologies = new ArrayList<OntologyDocument>();
		ob.loadOntology(mainOnto, subOntologies, false);		
		
		firstURILen = firstURI.length();
	}
	public String[] ListInstancesOfClass(String className) {
		Iterator<String> iter = ob.listInstances(className); 
		List<String> myList = IteratorUtils.toList(iter);
	    String[] itemsArray = new String[myList.size()];
	    itemsArray = myList.toArray(itemsArray);
	    for (int i = 0; i < itemsArray.length; i++) {
			itemsArray[i]=itemsArray[i].substring(firstURILen);
		}	    
	    return itemsArray;
	}

}
