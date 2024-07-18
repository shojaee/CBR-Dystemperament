package functions;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import jcolibri.datatypes.Instance;
import jcolibri.exception.NoApplicableSimilarityFunctionException;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

public class OntWuPalmer implements LocalSimilarityFunction{

	@Override
	public double compute(Object caseObject, Object queryObject) throws NoApplicableSimilarityFunctionException {
		if ((caseObject == null) || (queryObject == null))
		    return 0;
		if (!(caseObject instanceof Instance))
		    throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), caseObject.getClass());
		if (!(queryObject instanceof Instance))
		    throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), queryObject
			    .getClass());

		Instance i1 = (Instance) caseObject;
		Instance i2 = (Instance) queryObject;

		if (i1.equals(i2))
		    return 1;

		OntoBridge ob = jcolibri.util.OntoBridgeSingleton.getOntoBridge();

		double up = 2 * (ob.maxProfLCS(i1.toString(), i2.toString())-3);
		double down;

		int prof1 = ob.profInstance(i1.toString())-3;
		int prof2 = ob.profInstance(i2.toString())-3;
		down = prof1+prof2;

		return up / down;
	}

	@Override
	public boolean isApplicable(Object o1, Object o2) {
		if ((o1 == null) && (o2 == null))
		    return true;
		else if (o1 == null)
		    return o2 instanceof Instance;
		else if (o2 == null)
		    return o1 instanceof Instance;
		else
		    return (o1 instanceof Instance) && (o2 instanceof Instance);
	}

}
