package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class FuzzyBMI implements jcolibri.cbrcore.CaseComponent{
	
	
	double BMINumericalValue,BMIUnderWeight,BMINormal,Obese,BMIOverWeight;
	String BMIFuzzyLabel,FuzzyBMIId;
	
	public FuzzyBMI(double x,Fuzzification f) {
		String str = Double.toString(x);
		BMINumericalValue = x;
		FuzzyBMIId = "bmi_" + String.valueOf(x);
		BMIUnderWeight = f.getFuzzyMembershipValue(str,"UnderWeight");
		BMINormal = f.getFuzzyMembershipValue(str,"NormalWeight");
		BMIOverWeight = f.getFuzzyMembershipValue(str,"OverWeight");
		Obese = f.getFuzzyMembershipValue(str,"Obese");
		BMIFuzzyLabel = f.BMIFuzzyLabel(str);		
	}
	public double getBMINumericalValue() {
		return BMINumericalValue;
	}
	public void setBMINumericalValue(double numericalValue) {
		BMINumericalValue = numericalValue;
	}
	public double getBMIUnderWeight() {
		return BMIUnderWeight;
	}
	public void setBMIUnderWeight(double bMIUnderWeight) {
		BMIUnderWeight = bMIUnderWeight;
	}
	public double getBMINormal() {
		return BMINormal;
	}
	public void setBMINormal(double bMINormal) {
		BMINormal = bMINormal;
	}
	public double getObese() {
		return Obese;
	}
	public void setObese(double obese) {
		Obese = obese;
	}
	public double getBMIOverWeight() {
		return BMIOverWeight;
	}
	public void setBMIOverWeight(double bMIOverWeight) {
		BMIOverWeight = bMIOverWeight;
	}
	public String getBMIFuzzyLabel() {
		return BMIFuzzyLabel;
	}
	public void setBMIFuzzyLabel(String bMIFuzzyLabel) {
		BMIFuzzyLabel = bMIFuzzyLabel;
	}
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("FuzzyBMIId", this.getClass());
	}

}
