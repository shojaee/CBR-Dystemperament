package datatypes;

import jcolibri.cbrcore.Attribute;
import persianmedicine.cbrdystemp.DystempDescription.OL1;
import persianmedicine.cbrdystemp.DystempDescription.OL3;

public class Stool implements jcolibri.cbrcore.CaseComponent{
	public OL3 stoolConsistency;
	public OL1 defecationPeriod;
	public String stoolType;
	public Stool(OL3 StoolConsistency,OL1 DefecationPeriod,String StoolType) {
		stoolConsistency = StoolConsistency;
		defecationPeriod = DefecationPeriod;
		stoolType = StoolType;	
	}
	public OL3 getStoolConsistency() {
		return stoolConsistency;
	}
	public void setStoolConsistency(OL3 stoolConsistency) {
		this.stoolConsistency = stoolConsistency;
	}
	public OL1 getDefecationPeriod() {
		return defecationPeriod;
	}
	public void setDefecationPeriod(OL1 defecationPeriod) {
		this.defecationPeriod = defecationPeriod;
	}
	public String getStoolType() {
		return stoolType;
	}
	public void setStoolType(String stoolType) {
		this.stoolType = stoolType;
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
