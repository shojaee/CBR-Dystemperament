package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class ThirstinessLevelFuzzyValue implements jcolibri.cbrcore.CaseComponent{
	double ThirstinessLevelNumericalValue,ThirstinessLevelLowValue,ThirstinessLevelMiddleValue,ThirstinessLevelHighValue;
	String ThirstinessLevelFuzzyLabel,ThirstinessLevelId;
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("ThirstinessLevelId", this.getClass());
	}
	public ThirstinessLevelFuzzyValue(double x,Fuzzification f) {
		String str = Double.toString(x);
		ThirstinessLevelNumericalValue = x;
		ThirstinessLevelId = "value_" + String.valueOf(x);
		ThirstinessLevelLowValue = f.getFuzzyMembershipValue(str,"LowValue");
		ThirstinessLevelMiddleValue = f.getFuzzyMembershipValue(str,"MiddleValue");
		ThirstinessLevelHighValue = f.getFuzzyMembershipValue(str,"HighValue");
		ThirstinessLevelFuzzyLabel = f.ValueFuzzyLabel(str);		
	}
	public double getThirstinessLevelNumericalValue() {
		return ThirstinessLevelNumericalValue;
	}
	public void setThirstinessLevelNumericalValue(double thirstinessLevelNumericalValue) {
		ThirstinessLevelNumericalValue = thirstinessLevelNumericalValue;
	}
	public double getThirstinessLevelLowValue() {
		return ThirstinessLevelLowValue;
	}
	public void setThirstinessLevelLowValue(double thirstinessLevelLowValue) {
		ThirstinessLevelLowValue = thirstinessLevelLowValue;
	}
	public double getThirstinessLevelMiddleValue() {
		return ThirstinessLevelMiddleValue;
	}
	public void setThirstinessLevelMiddleValue(double thirstinessLevelMiddleValue) {
		ThirstinessLevelMiddleValue = thirstinessLevelMiddleValue;
	}
	public double getThirstinessLevelHighValue() {
		return ThirstinessLevelHighValue;
	}
	public void setThirstinessLevelHighValue(double thirstinessLevelHighValue) {
		ThirstinessLevelHighValue = thirstinessLevelHighValue;
	}
	public String getThirstinessLevelFuzzyLabel() {
		return ThirstinessLevelFuzzyLabel;
	}
	public void setThirstinessLevelFuzzyLabel(String thirstinessLevelFuzzyLabel) {
		ThirstinessLevelFuzzyLabel = thirstinessLevelFuzzyLabel;
	}
	public String getThirstinessLevelId() {
		return ThirstinessLevelId;
	}
	public void setThirstinessLevelId(String thirstinessLevelId) {
		ThirstinessLevelId = thirstinessLevelId;
	}
}
