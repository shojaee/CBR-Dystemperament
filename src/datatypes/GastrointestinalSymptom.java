package datatypes;

import jcolibri.cbrcore.Attribute;
import persianmedicine.cbrdystemp.DystempDescription.OL2;

public class GastrointestinalSymptom implements jcolibri.cbrcore.CaseComponent{
	public Integer digestionPowerNumber,foodTransitionSpeedNumber;
	public DigestionPowerFuzzyValue digestionPowerFuzzy;
	public FoodTransitionSpeedFuzzyValue foodTransitionSpeedFuzzy;
	public String qasayanTime,heavinessTime,gastralgiaTime,takhazkhoz;
	public OL2 qasayan,stomachHeaviness,gastricEructation,flatulence,gastralgia,foodPutrefactionFreq
	,heartburn,hiccup,acerbate;
	public GastrointestinalSymptom(OL2 Acerbate,Integer DigestionPowerNumber,DigestionPowerFuzzyValue DigestionPowerFuzzy,OL2 Flatulence,OL2 FoodPutrefactionFreq,Integer FoodTransitionSpeedNumber,FoodTransitionSpeedFuzzyValue FoodTransitionSpeedFuzzy,OL2 Gastralgia,String GastralgiaTime,OL2 GastricEructation,OL2 Heartburn, String HeavinessTime,OL2 Hiccup,OL2 Qasayan,String QasayanTime,OL2 StomachHeaviness,String Takhazkhoz) {
		acerbate = Acerbate;
		digestionPowerNumber = DigestionPowerNumber;
		digestionPowerFuzzy = DigestionPowerFuzzy;
		flatulence = Flatulence;
		foodPutrefactionFreq = FoodPutrefactionFreq;
		foodTransitionSpeedNumber = FoodTransitionSpeedNumber;
		foodTransitionSpeedFuzzy = FoodTransitionSpeedFuzzy;
		gastralgia = Gastralgia;
		gastralgiaTime = GastralgiaTime;
		gastricEructation = GastricEructation;
		heartburn = Heartburn;
		heavinessTime = HeavinessTime;
		hiccup = Hiccup;
		qasayan = Qasayan;
		qasayanTime = QasayanTime;
		stomachHeaviness = StomachHeaviness;
		takhazkhoz = Takhazkhoz;
	}
	public Integer getDigestionPowerNumber() {
		return digestionPowerNumber;
	}
	public void setDigestionPowerNumber(Integer digestionPowerNumber) {
		this.digestionPowerNumber = digestionPowerNumber;
	}
	public Integer getFoodTransitionSpeedNumber() {
		return foodTransitionSpeedNumber;
	}
	public void setFoodTransitionSpeedNumber(Integer foodTransitionSpeedNumber) {
		this.foodTransitionSpeedNumber = foodTransitionSpeedNumber;
	}
	public DigestionPowerFuzzyValue getDigestionPowerFuzzy() {
		return digestionPowerFuzzy;
	}
	public void setDigestionPowerFuzzy(DigestionPowerFuzzyValue digestionPowerFuzzy) {
		this.digestionPowerFuzzy = digestionPowerFuzzy;
	}
	public FoodTransitionSpeedFuzzyValue getFoodTransitionSpeedFuzzy() {
		return foodTransitionSpeedFuzzy;
	}
	public void setFoodTransitionSpeedFuzzy(FoodTransitionSpeedFuzzyValue foodTransitionSpeedFuzzy) {
		this.foodTransitionSpeedFuzzy = foodTransitionSpeedFuzzy;
	}
	public String getQasayanTime() {
		return qasayanTime;
	}
	public void setQasayanTime(String qasayanTime) {
		this.qasayanTime = qasayanTime;
	}
	public String getHeavinessTime() {
		return heavinessTime;
	}
	public void setHeavinessTime(String heavinessTime) {
		this.heavinessTime = heavinessTime;
	}
	public String getGastralgiaTime() {
		return gastralgiaTime;
	}
	public void setGastralgiaTime(String gastralgiaTime) {
		this.gastralgiaTime = gastralgiaTime;
	}
	public String getTakhazkhoz() {
		return takhazkhoz;
	}
	public void setTakhazkhoz(String takhazkhoz) {
		this.takhazkhoz = takhazkhoz;
	}
	public OL2 getQasayan() {
		return qasayan;
	}
	public void setQasayan(OL2 qasayan) {
		this.qasayan = qasayan;
	}
	public OL2 getStomachHeaviness() {
		return stomachHeaviness;
	}
	public void setStomachHeaviness(OL2 stomachHeaviness) {
		this.stomachHeaviness = stomachHeaviness;
	}
	public OL2 getGastricEructation() {
		return gastricEructation;
	}
	public void setGastricEructation(OL2 gastricEructation) {
		this.gastricEructation = gastricEructation;
	}
	public OL2 getFlatulence() {
		return flatulence;
	}
	public void setFlatulence(OL2 flatulence) {
		this.flatulence = flatulence;
	}
	public OL2 getGastralgia() {
		return gastralgia;
	}
	public void setGastralgia(OL2 gastralgia) {
		this.gastralgia = gastralgia;
	}
	public OL2 getFoodPutrefactionFreq() {
		return foodPutrefactionFreq;
	}
	public void setFoodPutrefactionFreq(OL2 foodPutrefactionFreq) {
		this.foodPutrefactionFreq = foodPutrefactionFreq;
	}
	public OL2 getHeartburn() {
		return heartburn;
	}
	public void setHeartburn(OL2 heartburn) {
		this.heartburn = heartburn;
	}
	public OL2 getHiccup() {
		return hiccup;
	}
	public void setHiccup(OL2 hiccup) {
		this.hiccup = hiccup;
	}
	public OL2 getAcerbate() {
		return acerbate;
	}
	public void setAcerbate(OL2 acerbate) {
		this.acerbate = acerbate;
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
