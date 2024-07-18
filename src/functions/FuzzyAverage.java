package functions;

import jcolibri.method.retrieve.NNretrieval.similarity.StandardGlobalSimilarityFunction;


public class FuzzyAverage extends StandardGlobalSimilarityFunction {


	public double computeSimilarity(double[] values, double[] weigths, int ivalue)
	{
		double acum = 0;
		double weigthsAcum = 0;
		for(int i=0; i<ivalue; i++)
		{
			acum += values[i] * weigths[i];
			weigthsAcum += weigths[i];
		}
		return acum/weigthsAcum;
	}


}
