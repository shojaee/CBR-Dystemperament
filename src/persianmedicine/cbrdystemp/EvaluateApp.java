package persianmedicine.cbrdystemp;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;

import functions.CBRCaseFunctions;
import functions.SimilarityConfig;
import gui.Fuzzification;
import jcolibri.casebase.CachedLinealCaseBase;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.connector.OntologyConnector;
import jcolibri.evaluation.Evaluator;
import jcolibri.exception.ExecutionException;
import jcolibri.extensions.textual.IE.common.StopWordsDetector;
import jcolibri.extensions.textual.IE.common.TextStemmer;
import jcolibri.extensions.textual.IE.opennlp.OpennlpSplitter;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;

public class EvaluateApp implements StandardCBRApplication{
	Connector _connector;
	CBRCaseBase _caseBase;
	
	private Log log;
	
	int[][] A;
	
	static final String L0 ="”Ê¡_„“«Ã_‰œ«—œ",L1="”Ê¡_„“«Ã_”Êœ«ÊÌ",L2="”Ê¡_„“«Ã_»·€„Ì";

	@Override
	public void configure() throws ExecutionException {
		try{
			A = new int[3][3];
			_connector = new OntologyConnector();
			
			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile("persianmedicine/cbrdystemp/ontologyconfig.xml"));
			_caseBase  = new CachedLinealCaseBase();
		} catch (Exception e){
			throw new ExecutionException(e);
		}
		log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
		
	}
	
	public void InitDescriptionOfCases() {
		try {
			Fuzzification f = new Fuzzification();
			f.InitFuzzyDataTypes();
			DystempDescription desc;
			java.util.Collection<CBRCase> cases = _caseBase.getCases();
			for(CBRCase c: cases) 
			{
				desc= (DystempDescription) c.getDescription();
				CBRCaseFunctions.InitDescriptionOfCase(desc, f);
			}
			OpennlpSplitter.split(cases);	
			StopWordsDetector.detectStopWords(cases);
			TextStemmer.stem(cases);

			
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}
	

	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);		
		InitDescriptionOfCases();
		return _caseBase;
	}

	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		//String q = ((DystempDescription)((CBRCase)query).getDescription()).MainConcept.toString();	
		//if (q!="„Ê—œ_028") return;
		
		SimilarityConfig sim = CBRCaseFunctions.ReadSimilarityFromFile();
		NNConfig simConfig = CBRCaseFunctions.getSimilarityConfig(sim);
		
	//	log.info("Query: "+ query.getDescription());

		
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		
		// Now we add the similarity of the most similar case to the serie "Similarity".
		
		Iterator<RetrievalResult> it = eval.iterator();
		RetrievalResult r = it.next();
		String queryDystemp = ((DystempSolution)((CBRCase)query).getSolution()).dystemperament;
		String queryStr = ((DystempDescription)((CBRCase)query).getDescription()).MainConcept.toString();
		
		String evalDystemp1 = ((DystempSolution)r.get_case().getSolution()).dystemperament;
		String evalStr1 = ((DystempDescription)r.get_case().getDescription()).MainConcept.toString();
		Double r1 = new Double(r.getEval());
		
		r = it.next();
		String evalDystemp2 = ((DystempSolution)r.get_case().getSolution()).dystemperament;
		String evalStr2 = ((DystempDescription)r.get_case().getDescription()).MainConcept.toString();
		Double r2 = new Double(r.getEval());

		r = it.next();
		String evalDystemp3 = ((DystempSolution)r.get_case().getSolution()).dystemperament;
		String evalStr3 = ((DystempDescription)r.get_case().getDescription()).MainConcept.toString();
		Double r3 = new Double(r.getEval());

		Evaluator.getEvaluationReport().addDataToSeries("Similarity", r1);
		
		System.out.println(queryStr + ","+queryDystemp+","+evalStr1+","+evalDystemp1+","+r1+","+evalStr2+","+evalDystemp2+","+r2+","+evalStr3+","+evalDystemp3+","+r3);
		
		
		switch (queryDystemp) {
		case L0: 
			switch (evalDystemp1) {
			case L0:
				A[0][0]++;
				break;
			case L1:
				A[0][1]++;
				break;
			case L2:
				A[0][2]++;
				break;
			}			
			break;
		case L1:
			switch (evalDystemp1) {
			case L0: 
				A[1][0]++;
				break;
			case L1:
				A[1][1]++;
				break;
			case L2:
				A[1][2]++;
				break;
			}			
			break;
		case L2:
			switch (evalDystemp1) {
			case L0: 
				A[2][0]++;
				break;
			case L1:
				A[2][1]++;
				break;
			case L2:
				A[2][2]++;
				break;
			}			
			break;
		}
		
	}

	@Override
	public void postCycle() throws ExecutionException {
		Evaluator.getEvaluationReport().putOtherData(" "," \t\t"+ L0 +"\t"+L1+"\t"+ L2 + "\n"+
									L0 + "\t"+ A[0][0] +"\t\t"+ A[0][1]+"\t\t"+ A[0][2] + "\n" +
									L1 + "\t"+ A[1][0] +"\t\t"+ A[1][1]+"\t\t"+ A[1][2] + "\n" +
									L2 + "\t"+ A[2][0] +"\t\t"+ A[2][1]+"\t\t"+ A[2][2] + "\n");
		int num = 0;
		for (int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				num = num + A[i][j];
		double acc = (double)(A[0][0]+A[1][1]+A[2][2])/num;
		Evaluator.getEvaluationReport().putOtherData("Accuracy","\t"+acc);
		this._caseBase.close();
		
	}
}
