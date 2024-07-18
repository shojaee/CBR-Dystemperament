package datatypes;

import jcolibri.cbrcore.Attribute;
import persianmedicine.cbrdystemp.DystempDescription.OL2;

public class Appetite implements jcolibri.cbrcore.CaseComponent{
	OL2 eatingMuchTiny;
	public Integer appetiteLevelNumber;
	public AppetiteLevelFuzzyValue appetiteLevelFuzzy;

	public OL2 getEatingMuchTiny() {
		return eatingMuchTiny;
	}

	public void setEatingMuchTiny(OL2 eatingMuchTiny) {
		this.eatingMuchTiny = eatingMuchTiny;
	}

	public Integer getAppetiteLevelNumber() {
		return appetiteLevelNumber;
	}

	public void setAppetiteLevelNumber(Integer appetiteLevelNumber) {
		this.appetiteLevelNumber = appetiteLevelNumber;
	}

	public AppetiteLevelFuzzyValue getAppetiteLevelFuzzy() {
		return appetiteLevelFuzzy;
	}

	public void setAppetiteLevelFuzzy(AppetiteLevelFuzzyValue appetiteLevelFuzzy) {
		this.appetiteLevelFuzzy = appetiteLevelFuzzy;
	}

	public Appetite(Integer AppetiteLevelNumber,OL2 EatingMuchTiny,AppetiteLevelFuzzyValue AppetiteLevelFuzzy) {
		appetiteLevelNumber = AppetiteLevelNumber;
		eatingMuchTiny = EatingMuchTiny;
		appetiteLevelFuzzy = AppetiteLevelFuzzy;
		
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
