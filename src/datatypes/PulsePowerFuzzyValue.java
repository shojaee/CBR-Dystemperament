package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class PulsePowerFuzzyValue implements jcolibri.cbrcore.CaseComponent{
	double PulsePowerNumericalValue,PulsePowerLowValue,PulsePowerMiddleValue,PulsePowerHighValue;
	String PulsePowerFuzzyLabel,PulsePowerId;
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("PulsePowerId", this.getClass());
	}
	public PulsePowerFuzzyValue(double x,Fuzzification f) {
		String str = Double.toString(x);
		PulsePowerNumericalValue = x;
		PulsePowerId = "value_" + String.valueOf(x);
		PulsePowerLowValue = f.getFuzzyMembershipValue(str,"LowValue");
		PulsePowerMiddleValue = f.getFuzzyMembershipValue(str,"MiddleValue");
		PulsePowerHighValue = f.getFuzzyMembershipValue(str,"HighValue");
		PulsePowerFuzzyLabel = f.ValueFuzzyLabel(str);		
	}
	public double getPulsePowerNumericalValue() {
		return PulsePowerNumericalValue;
	}
	public void setPulsePowerNumericalValue(double pulsePowerNumericalValue) {
		PulsePowerNumericalValue = pulsePowerNumericalValue;
	}
	public double getPulsePowerLowValue() {
		return PulsePowerLowValue;
	}
	public void setPulsePowerLowValue(double pulsePowerLowValue) {
		PulsePowerLowValue = pulsePowerLowValue;
	}
	public double getPulsePowerMiddleValue() {
		return PulsePowerMiddleValue;
	}
	public void setPulsePowerMiddleValue(double pulsePowerMiddleValue) {
		PulsePowerMiddleValue = pulsePowerMiddleValue;
	}
	public double getPulsePowerHighValue() {
		return PulsePowerHighValue;
	}
	public void setPulsePowerHighValue(double pulsePowerHighValue) {
		PulsePowerHighValue = pulsePowerHighValue;
	}
	public String getPulsePowerFuzzyLabel() {
		return PulsePowerFuzzyLabel;
	}
	public void setPulsePowerFuzzyLabel(String pulsePowerFuzzyLabel) {
		PulsePowerFuzzyLabel = pulsePowerFuzzyLabel;
	}
	public String getPulsePowerId() {
		return PulsePowerId;
	}
	public void setPulsePowerId(String pulsePowerId) {
		PulsePowerId = pulsePowerId;
	}
}
