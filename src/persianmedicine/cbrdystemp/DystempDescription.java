package persianmedicine.cbrdystemp;

import datatypes.*;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;
import jcolibri.datatypes.Instance;
import jcolibri.datatypes.Text;
import jcolibri.extensions.textual.IE.opennlp.IETextOpenNLP;
import jcolibri.extensions.textual.IE.representation.IEText;

public class DystempDescription implements CaseComponent{
	
	public Instance MainConcept;
	
	public enum OL1  { —Ê“Ì_ç‰œ_»«—, —Ê“«‰Â, Â—_ç‰œ_—Ê“_Ìò»«—, Â› êÌ};
	public enum OL2  { ŒÌ—, ò„, „ Ê”ÿ, “Ì«œ, ŒÌ·Ì_“Ì«œ};
	public enum OL3  { ¬»òÌ, ‰—„, ‰—„«·, Œ‘ò, ŒÌ·Ì_Œ‘ò};
	
	public FuzzyAge ageFuzzy;
	public FuzzyBMI bmiFuzzy;
	public Double ageNumber,bmiNumber;
	public Integer digestionPowerNumber,foodTransitionSpeedNumber,appetiteLevelNumber,thirstinessLevelNumber,pulseSpeedNumber,pulsePowerNumber,pulseRateNumber,pulseConsistencyNumber;
	public DigestionPowerFuzzyValue digestionPowerFuzzy;
	public FoodTransitionSpeedFuzzyValue foodTransitionSpeedFuzzy;
	public AppetiteLevelFuzzyValue appetiteLevelFuzzy;
	public ThirstinessLevelFuzzyValue thirstinessLevelFuzzy;
	public PulseSpeedFuzzyValue pulseSpeedFuzzy;
	public PulsePowerFuzzyValue pulsePowerFuzzy;
	public PulseRateFuzzyValue pulseRateFuzzy;
	public PulseConsistencyFuzzyValue pulseConsistencyFuzzy;
	public String gender,job,faceColor,thirstinessReliefType,takhazkhoz,stoolType,gastralgiaTime,salivationTime,mouthTaste,tongueColor,qasayanTime,heavinessTime;
	public IETextOpenNLP custodyDisposal,sleepingWakefulness,climate,physicalActivity,food,mentalHealth,tendencyTo,benefitFrom,disadvantageOf,hatredOf,bodyState;
	public OL2 limpness,saliva,salivation,tongueWettish,qasayan,stomachHeaviness,gastricEructation,flatulence,gastralgia,foodPutrefactionFreq
				,heartburn,hiccup,acerbate,eatingMuchTiny;
	public OL3 stoolConsistency;
	public OL1 defecationPeriod;	
	public IEText disease;

	
	public String limpnessStr,salivaStr,salivationStr,tongueWettishStr,qasayanStr,stomachHeavinessStr,gastricEructationStr,flatulenceStr,gastralgiaStr,foodPutrefactionFreqStr
	,heartburnStr,hiccupStr,acerbateStr,eatingMuchTinyStr,stoolConsistencyStr,defecationPeriodStr;	
	

	public Appetite appetite;
	public BDTH bdth;
	public Demographic demographic;
	public GastrointestinalSymptom gastrointestinalSymptom;
	public PhysicalExamination physicalExamination;
	public Principle principle;
	public Pulse pulse;
	public Stool stool;
	public Thirstiness thirstiness;
	
	public String getLimpnessStr() {
		return limpnessStr;
	}


	public void setLimpnessStr(String limpnessStr) {
		this.limpnessStr = limpnessStr;
	}


	public String getSalivaStr() {
		return salivaStr;
	}


	public void setSalivaStr(String salivaStr) {
		this.salivaStr = salivaStr;
	}


	public String getSalivationStr() {
		return salivationStr;
	}


	public void setSalivationStr(String salivationStr) {
		this.salivationStr = salivationStr;
	}


	public String getTongueWettishStr() {
		return tongueWettishStr;
	}


	public void setTongueWettishStr(String tongueWettishStr) {
		this.tongueWettishStr = tongueWettishStr;
	}


	public String getQasayanStr() {
		return qasayanStr;
	}


	public void setQasayanStr(String qasayanStr) {
		this.qasayanStr = qasayanStr;
	}


	public String getStomachHeavinessStr() {
		return stomachHeavinessStr;
	}


	public void setStomachHeavinessStr(String stomachHeavinessStr) {
		this.stomachHeavinessStr = stomachHeavinessStr;
	}


	public String getGastricEructationStr() {
		return gastricEructationStr;
	}


	public void setGastricEructationStr(String gastricEructationStr) {
		this.gastricEructationStr = gastricEructationStr;
	}


	public String getFlatulenceStr() {
		return flatulenceStr;
	}


	public void setFlatulenceStr(String flatulenceStr) {
		this.flatulenceStr = flatulenceStr;
	}


	public String getGastralgiaStr() {
		return gastralgiaStr;
	}


	public void setGastralgiaStr(String gastralgiaStr) {
		this.gastralgiaStr = gastralgiaStr;
	}


	public String getFoodPutrefactionFreqStr() {
		return foodPutrefactionFreqStr;
	}


	public void setFoodPutrefactionFreqStr(String foodPutrefactionFreqStr) {
		this.foodPutrefactionFreqStr = foodPutrefactionFreqStr;
	}


	public String getHeartburnStr() {
		return heartburnStr;
	}


	public void setHeartburnStr(String heartburnStr) {
		this.heartburnStr = heartburnStr;
	}


	public String getHiccupStr() {
		return hiccupStr;
	}


	public void setHiccupStr(String hiccupStr) {
		this.hiccupStr = hiccupStr;
	}


	public String getAcerbateStr() {
		return acerbateStr;
	}


	public void setAcerbateStr(String acerbateStr) {
		this.acerbateStr = acerbateStr;
	}


	public String getEatingMuchTinyStr() {
		return eatingMuchTinyStr;
	}


	public void setEatingMuchTinyStr(String eatingMuchTinyStr) {
		this.eatingMuchTinyStr = eatingMuchTinyStr;
	}


	public String getStoolConsistencyStr() {
		return stoolConsistencyStr;
	}


	public void setStoolConsistencyStr(String stoolConsistencyStr) {
		this.stoolConsistencyStr = stoolConsistencyStr;
	}


	public String getDefecationPeriodStr() {
		return defecationPeriodStr;
	}


	public void setDefecationPeriodStr(String defecationPeriodStr) {
		this.defecationPeriodStr = defecationPeriodStr;
	}


	public Instance getMainConcept() {
		return MainConcept;
	}


	public void setMainConcept(Instance mainConcept) {
		MainConcept = mainConcept;
	}


	public FuzzyAge getAgeFuzzy() {
		return ageFuzzy;
	}


	public void setAgeFuzzy(FuzzyAge ageFuzzy) {
		this.ageFuzzy = ageFuzzy;
	}


	public FuzzyBMI getBmiFuzzy() {
		return bmiFuzzy;
	}


	public void setBmiFuzzy(FuzzyBMI bmiFuzzy) {
		this.bmiFuzzy = bmiFuzzy;
	}


	public Double getAgeNumber() {
		return ageNumber;
	}


	public void setAgeNumber(Double ageNumber) {
		this.ageNumber = ageNumber;
	}


	public Double getBmiNumber() {
		return bmiNumber;
	}


	public void setBmiNumber(Double bmiNumber) {
		this.bmiNumber = bmiNumber;
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


	public Integer getAppetiteLevelNumber() {
		return appetiteLevelNumber;
	}


	public void setAppetiteLevelNumber(Integer appetiteLevelNumber) {
		this.appetiteLevelNumber = appetiteLevelNumber;
	}


	public Integer getThirstinessLevelNumber() {
		return thirstinessLevelNumber;
	}


	public void setThirstinessLevelNumber(Integer thirstinessLevelNumber) {
		this.thirstinessLevelNumber = thirstinessLevelNumber;
	}


	public Integer getPulseSpeedNumber() {
		return pulseSpeedNumber;
	}


	public void setPulseSpeedNumber(Integer pulseSpeedNumber) {
		this.pulseSpeedNumber = pulseSpeedNumber;
	}


	public Integer getPulsePowerNumber() {
		return pulsePowerNumber;
	}


	public void setPulsePowerNumber(Integer pulsePowerNumber) {
		this.pulsePowerNumber = pulsePowerNumber;
	}


	public Integer getPulseRateNumber() {
		return pulseRateNumber;
	}


	public void setPulseRateNumber(Integer pulseRateNumber) {
		this.pulseRateNumber = pulseRateNumber;
	}


	public Integer getPulseConsistencyNumber() {
		return pulseConsistencyNumber;
	}


	public void setPulseConsistencyNumber(Integer pulseConsistencyNumber) {
		this.pulseConsistencyNumber = pulseConsistencyNumber;
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


	public AppetiteLevelFuzzyValue getAppetiteLevelFuzzy() {
		return appetiteLevelFuzzy;
	}


	public void setAppetiteLevelFuzzy(AppetiteLevelFuzzyValue appetiteLevelFuzzy) {
		this.appetiteLevelFuzzy = appetiteLevelFuzzy;
	}


	public ThirstinessLevelFuzzyValue getThirstinessLevelFuzzy() {
		return thirstinessLevelFuzzy;
	}


	public void setThirstinessLevelFuzzy(ThirstinessLevelFuzzyValue thirstinessLevelFuzzy) {
		this.thirstinessLevelFuzzy = thirstinessLevelFuzzy;
	}


	public PulseSpeedFuzzyValue getPulseSpeedFuzzy() {
		return pulseSpeedFuzzy;
	}


	public void setPulseSpeedFuzzy(PulseSpeedFuzzyValue pulseSpeedFuzzy) {
		this.pulseSpeedFuzzy = pulseSpeedFuzzy;
	}


	public PulsePowerFuzzyValue getPulsePowerFuzzy() {
		return pulsePowerFuzzy;
	}


	public void setPulsePowerFuzzy(PulsePowerFuzzyValue pulsePowerFuzzy) {
		this.pulsePowerFuzzy = pulsePowerFuzzy;
	}


	public PulseRateFuzzyValue getPulseRateFuzzy() {
		return pulseRateFuzzy;
	}


	public void setPulseRateFuzzy(PulseRateFuzzyValue pulseRateFuzzy) {
		this.pulseRateFuzzy = pulseRateFuzzy;
	}


	public PulseConsistencyFuzzyValue getPulseConsistencyFuzzy() {
		return pulseConsistencyFuzzy;
	}


	public void setPulseConsistencyFuzzy(PulseConsistencyFuzzyValue pulseConsistencyFuzzy) {
		this.pulseConsistencyFuzzy = pulseConsistencyFuzzy;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}


	public String getFaceColor() {
		return faceColor;
	}


	public void setFaceColor(String faceColor) {
		this.faceColor = faceColor;
	}


	public String getThirstinessReliefType() {
		return thirstinessReliefType;
	}


	public void setThirstinessReliefType(String thirstinessReliefType) {
		this.thirstinessReliefType = thirstinessReliefType;
	}


	public String getTakhazkhoz() {
		return takhazkhoz;
	}


	public void setTakhazkhoz(String takhazkhoz) {
		this.takhazkhoz = takhazkhoz;
	}


	public String getStoolType() {
		return stoolType;
	}


	public void setStoolType(String stoolType) {
		this.stoolType = stoolType;
	}


	public String getGastralgiaTime() {
		return gastralgiaTime;
	}


	public void setGastralgiaTime(String gastralgiaTime) {
		this.gastralgiaTime = gastralgiaTime;
	}


	public String getSalivationTime() {
		return salivationTime;
	}


	public void setSalivationTime(String salivationTime) {
		this.salivationTime = salivationTime;
	}


	public String getMouthTaste() {
		return mouthTaste;
	}


	public void setMouthTaste(String mouthTaste) {
		this.mouthTaste = mouthTaste;
	}


	public String getTongueColor() {
		return tongueColor;
	}


	public void setTongueColor(String tongueColor) {
		this.tongueColor = tongueColor;
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


	public IETextOpenNLP getCustodyDisposal() {
		return custodyDisposal;
	}


	public void setCustodyDisposal(IETextOpenNLP custodyDisposal) {
		this.custodyDisposal = custodyDisposal;
	}


	public IETextOpenNLP getSleepingWakefulness() {
		return sleepingWakefulness;
	}


	public void setSleepingWakefulness(IETextOpenNLP sleepingWakefulness) {
		this.sleepingWakefulness = sleepingWakefulness;
	}


	public IETextOpenNLP getClimate() {
		return climate;
	}


	public void setClimate(IETextOpenNLP climate) {
		this.climate = climate;
	}


	public IETextOpenNLP getPhysicalActivity() {
		return physicalActivity;
	}


	public void setPhysicalActivity(IETextOpenNLP physicalActivity) {
		this.physicalActivity = physicalActivity;
	}


	public IETextOpenNLP getFood() {
		return food;
	}


	public void setFood(IETextOpenNLP food) {
		this.food = food;
	}


	public IETextOpenNLP getMentalHealth() {
		return mentalHealth;
	}


	public void setMentalHealth(IETextOpenNLP mentalHealth) {
		this.mentalHealth = mentalHealth;
	}


	public IETextOpenNLP getTendencyTo() {
		return tendencyTo;
	}


	public void setTendencyTo(IETextOpenNLP tendencyTo) {
		this.tendencyTo = tendencyTo;
	}


	public IETextOpenNLP getBenefitFrom() {
		return benefitFrom;
	}


	public void setBenefitFrom(IETextOpenNLP benefitFrom) {
		this.benefitFrom = benefitFrom;
	}


	public IETextOpenNLP getDisadvantageOf() {
		return disadvantageOf;
	}


	public void setDisadvantageOf(IETextOpenNLP disadvantageOf) {
		this.disadvantageOf = disadvantageOf;
	}


	public IETextOpenNLP getHatredOf() {
		return hatredOf;
	}


	public void setHatredOf(IETextOpenNLP hatredOf) {
		this.hatredOf = hatredOf;
	}


	public IETextOpenNLP getBodyState() {
		return bodyState;
	}


	public void setBodyState(IETextOpenNLP bodyState) {
		this.bodyState = bodyState;
	}


	public OL2 getLimpness() {
		return limpness;
	}


	public void setLimpness(OL2 limpness) {
		this.limpness = limpness;
	}


	public OL2 getSaliva() {
		return saliva;
	}


	public void setSaliva(OL2 saliva) {
		this.saliva = saliva;
	}


	public OL2 getSalivation() {
		return salivation;
	}


	public void setSalivation(OL2 salivation) {
		this.salivation = salivation;
	}


	public OL2 getTongueWettish() {
		return tongueWettish;
	}


	public void setTongueWettish(OL2 tongueWettish) {
		this.tongueWettish = tongueWettish;
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


	public OL2 getEatingMuchTiny() {
		return eatingMuchTiny;
	}


	public void setEatingMuchTiny(OL2 eatingMuchTiny) {
		this.eatingMuchTiny = eatingMuchTiny;
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


	public IEText getDisease() {
		return disease;
	}


	public void setDisease(IEText disease) {
		this.disease = disease;
	}


	@Override
	public Attribute getIdAttribute() {
		return new Attribute("MainConcept", this.getClass());
	}

}
