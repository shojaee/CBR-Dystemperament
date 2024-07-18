package persianmedicine.cbrdystemp;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;
import jcolibri.datatypes.*;

public class DystempSolution implements CaseComponent {

	Instance MainConcept;
	public String dystemperament;
	public Text treatments;
	public Text getTreatments() {
		return treatments;
	}

	public void setTreatments(Text treatments) {
		this.treatments = treatments;
	}

	@Override
	
	public String toString()
	{
		return "("+MainConcept+";"+dystemperament+")";
	}
	
	public Attribute getIdAttribute() {
		return new Attribute("MainConcept", this.getClass());
	}

	public Instance getMainConcept() {
		return MainConcept;
	}

	public void setMainConcept(Instance mainConcept) {
		MainConcept = mainConcept;
	}

	public String getDystemperament() {
		return dystemperament;
	}

	public void setDystemperament(String dystemperament) {
		this.dystemperament = dystemperament;
	}

	
}
