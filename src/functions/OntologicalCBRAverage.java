package functions;

import jcolibri.method.retrieve.NNretrieval.similarity.StandardGlobalSimilarityFunction;

public class OntologicalCBRAverage extends StandardGlobalSimilarityFunction{

	@Override
	public double computeSimilarity(double[] values, double[] weigths, int numberOfvalues) {
		double acum = 0;
		double weigthsAcum = 0;
		for(int i=0; i<numberOfvalues; i++)
		{
			acum += values[i] * weigths[i];
			weigthsAcum += weigths[i];
		}
		if (weigthsAcum>0) {
			//System.out.println(weigthsAcum+":"+acum/weigthsAcum);
			return acum/weigthsAcum;
		}
		return 0;
	}

}
