package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class AppetiteLevelFuzzyValue implements jcolibri.cbrcore.CaseComponent{
	double AppetiteLevelNumericalValue,AppetiteLevelLowValue,AppetiteLevelMiddleValue,AppetiteLevelHighValue;
	String AppetiteLevelFuzzyLabel,AppetiteLevelId;
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("AppetiteLevelId", this.getClass());
	}
	public AppetiteLevelFuzzyValue(double x,Fuzzification f) {
		String str = Double.toString(x);
		AppetiteLevelNumericalValue = x;
		AppetiteLevelId = "value_" + String.valueOf(x);
		AppetiteLevelLowValue = f.getFuzzyMembershipValue(str,"LowValue");
		AppetiteLevelMiddleValue = f.getFuzzyMembershipValue(str,"MiddleValue");
		AppetiteLevelHighValue = f.getFuzzyMembershipValue(str,"HighValue");
		AppetiteLevelFuzzyLabel = f.ValueFuzzyLabel(str);		
	}
	public double getAppetiteLevelNumericalValue() {
		return AppetiteLevelNumericalValue;
	}
	public void setAppetiteLevelNumericalValue(double appetiteLevelNumericalValue) {
		AppetiteLevelNumericalValue = appetiteLevelNumericalValue;
	}
	public double getAppetiteLevelLowValue() {
		return AppetiteLevelLowValue;
	}
	public void setAppetiteLevelLowValue(double appetiteLevelLowValue) {
		AppetiteLevelLowValue = appetiteLevelLowValue;
	}
	public double getAppetiteLevelMiddleValue() {
		return AppetiteLevelMiddleValue;
	}
	public void setAppetiteLevelMiddleValue(double appetiteLevelMiddleValue) {
		AppetiteLevelMiddleValue = appetiteLevelMiddleValue;
	}
	public double getAppetiteLevelHighValue() {
		return AppetiteLevelHighValue;
	}
	public void setAppetiteLevelHighValue(double appetiteLevelHighValue) {
		AppetiteLevelHighValue = appetiteLevelHighValue;
	}
	public String getAppetiteLevelFuzzyLabel() {
		return AppetiteLevelFuzzyLabel;
	}
	public void setAppetiteLevelFuzzyLabel(String appetiteLevelFuzzyLabel) {
		AppetiteLevelFuzzyLabel = appetiteLevelFuzzyLabel;
	}
	public String getAppetiteLevelId() {
		return AppetiteLevelId;
	}
	public void setAppetiteLevelId(String appetiteLevelId) {
		AppetiteLevelId = appetiteLevelId;
	}
}
