package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class FuzzyAge implements jcolibri.cbrcore.CaseComponent{	
	double AgeNumericalValue,AgeGrowth,AgeYoung,AgeMiddle,AgeOld;
	String AgeFuzzyLabel,AgeId;
	
	public FuzzyAge(double x, Fuzzification f) {
		String str = Double.toString(x);
		AgeNumericalValue = x;
		AgeId = "age_" + String.valueOf(x);
		AgeGrowth = f.getFuzzyMembershipValue(str,"GrowthAged");
		AgeYoung = f.getFuzzyMembershipValue(str,"Young");
		AgeMiddle = f.getFuzzyMembershipValue(str,"MiddleAged");
		AgeOld = f.getFuzzyMembershipValue(str,"Old");
		AgeFuzzyLabel = f.AgeFuzzyLabel(str);
	}
	public double getAgeNumericalValue() {
		return AgeNumericalValue;
	}
	public void setAgeNumericalValue(double numericalValue) {
		AgeNumericalValue = numericalValue;
	}
	public double getAgeGrowth() {
		return AgeGrowth;
	}
	public void setAgeGrowth(double ageGrowth) {
		AgeGrowth = ageGrowth;
	}
	public double getAgeYoung() {
		return AgeYoung;
	}
	public void setAgeYoung(double ageYoung) {
		AgeYoung = ageYoung;
	}
	public double getAgeMiddle() {
		return AgeMiddle;
	}
	public void setAgeMiddle(double ageMiddle) {
		AgeMiddle = ageMiddle;
	}
	public double getAgeOld() {
		return AgeOld;
	}
	public void setAgeOld(double ageOld) {
		AgeOld = ageOld;
	}
	public String getAgeFuzzyLabel() {
		return AgeFuzzyLabel;
	}
	public void setAgeFuzzyLabel(String ageFuzzyLabel) {
		AgeFuzzyLabel = ageFuzzyLabel;
	}
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("AgeId", this.getClass());
	}

}
