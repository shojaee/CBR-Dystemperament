package functions;

import java.util.Arrays;
import java.util.List;

import jcolibri.datatypes.Instance;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.exception.OntologyAccessException;
import jcolibri.extensions.textual.IE.representation.IEText;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeep;

public class OntoSimMax implements LocalSimilarityFunction{
/*
		ontofunctions.add("OntCosine");
		ontofunctions.add("OntDeep");
		ontofunctions.add("OntDeepBasic");
		ontofunctions.add("OntDetail");
		ontofunctions.add("OntDetail");
  */
	
	@Override
	public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException {
		if ((caseObject == null) || (queryObject == null))
			return 0;
		if (!(caseObject instanceof IEText))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		if (!(queryObject instanceof IEText))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject.getClass());

		double r=0;

		IEText caseText = (IEText) caseObject;
		IEText queryText = (IEText) queryObject;
		
		List<String> caseList = Arrays.asList(caseText.toString().split(",[\\s]*"));
		List<String> queryList = Arrays.asList(queryText.toString().split(",[\\s]*"));
		OntWuPalmer sim1 = new OntWuPalmer();
		OntLin sim2 = new OntLin();
		
		try {
		double[] values = new double[caseList.size()*queryList.size()];
		int ivalue=0;
		for(int i=0;i<caseList.size();i++)
			for(int j=0;j<queryList.size();j++)
				values[ivalue++] = ((sim1.compute(new Instance(caseList.get(i)), new Instance(queryList.get(j))))+(sim2.compute(new Instance(caseList.get(i)), new Instance(queryList.get(j)))))/2;
		
		for(int i=0;i<values.length;i++)
			r = r + values[i];
		
		r = r / values.length;
		}
		catch(OntologyAccessException e) {
			System.out.println(e.getMessage());
		}
		return r;
	}
	

	@Override
	public boolean isApplicable(Object caseObject, Object queryObject) {
		return true;
	}

}
