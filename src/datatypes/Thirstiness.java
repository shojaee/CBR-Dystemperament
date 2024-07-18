package datatypes;

import jcolibri.cbrcore.Attribute;

public class Thirstiness implements jcolibri.cbrcore.CaseComponent {
  public Integer thirstinessLevelNumber;
  public ThirstinessLevelFuzzyValue thirstinessLevelFuzzy;
  public String thirstinessReliefType;
  public Thirstiness(Integer ThirstinessLevelNumber,ThirstinessLevelFuzzyValue ThirstinessLevelFuzzy,String ThirstinessReliefType) {
	  thirstinessLevelNumber = ThirstinessLevelNumber;
	  thirstinessLevelFuzzy = ThirstinessLevelFuzzy;
	  thirstinessReliefType = ThirstinessReliefType;	  
  }
public Integer getThirstinessLevelNumber() {
	return thirstinessLevelNumber;
}
public void setThirstinessLevelNumber(Integer thirstinessLevelNumber) {
	this.thirstinessLevelNumber = thirstinessLevelNumber;
}
public ThirstinessLevelFuzzyValue getThirstinessLevelFuzzy() {
	return thirstinessLevelFuzzy;
}
public void setThirstinessLevelFuzzy(ThirstinessLevelFuzzyValue thirstinessLevelFuzzy) {
	this.thirstinessLevelFuzzy = thirstinessLevelFuzzy;
}
public String getThirstinessReliefType() {
	return thirstinessReliefType;
}
public void setThirstinessReliefType(String thirstinessReliefType) {
	this.thirstinessReliefType = thirstinessReliefType;
}
@Override
public Attribute getIdAttribute() {
	// TODO Auto-generated method stub
	return null;
}

}
