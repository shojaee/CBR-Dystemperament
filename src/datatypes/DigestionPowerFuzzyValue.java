package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class DigestionPowerFuzzyValue implements jcolibri.cbrcore.CaseComponent{
	double DigestionPowerNumericalValue,DigestionPowerLowValue,DigestionPowerMiddleValue,DigestionPowerHighValue;
	String DigestionPowerFuzzyLabel,DigestionPowerId;
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("DigestionPowerId", this.getClass());
	}
	
	public DigestionPowerFuzzyValue(double x,Fuzzification f) {
		String str = Double.toString(x);
		DigestionPowerNumericalValue = x;
		DigestionPowerId = "value_" + String.valueOf(x);
		DigestionPowerLowValue = f.getFuzzyMembershipValue(str,"LowValue");
		DigestionPowerMiddleValue = f.getFuzzyMembershipValue(str,"MiddleValue");
		DigestionPowerHighValue = f.getFuzzyMembershipValue(str,"HighValue");
		DigestionPowerFuzzyLabel = f.ValueFuzzyLabel(str);		
	}

	public double getDigestionPowerNumericalValue() {
		return DigestionPowerNumericalValue;
	}

	public void setDigestionPowerNumericalValue(double digestionPowerNumericalValue) {
		DigestionPowerNumericalValue = digestionPowerNumericalValue;
	}

	public double getDigestionPowerLowValue() {
		return DigestionPowerLowValue;
	}

	public void setDigestionPowerLowValue(double digestionPowerLowValue) {
		DigestionPowerLowValue = digestionPowerLowValue;
	}

	public double getDigestionPowerMiddleValue() {
		return DigestionPowerMiddleValue;
	}

	public void setDigestionPowerMiddleValue(double digestionPowerMiddleValue) {
		DigestionPowerMiddleValue = digestionPowerMiddleValue;
	}

	public double getDigestionPowerHighValue() {
		return DigestionPowerHighValue;
	}

	public void setDigestionPowerHighValue(double digestionPowerHighValue) {
		DigestionPowerHighValue = digestionPowerHighValue;
	}

	public String getDigestionPowerFuzzyLabel() {
		return DigestionPowerFuzzyLabel;
	}

	public void setDigestionPowerFuzzyLabel(String digestionPowerFuzzyLabel) {
		DigestionPowerFuzzyLabel = digestionPowerFuzzyLabel;
	}

	public String getDigestionPowerId() {
		return DigestionPowerId;
	}

	public void setDigestionPowerId(String digestionPowerId) {
		DigestionPowerId = digestionPowerId;
	}

}
