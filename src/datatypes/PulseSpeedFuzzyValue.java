package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class PulseSpeedFuzzyValue implements jcolibri.cbrcore.CaseComponent{
	double PulseSpeedNumericalValue,PulseSpeedLowValue,PulseSpeedMiddleValue,PulseSpeedHighValue;
	String PulseSpeedFuzzyLabel,PulseSpeedId;
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("PulseSpeedId", this.getClass());
	}
	public double getPulseSpeedNumericalValue() {
		return PulseSpeedNumericalValue;
	}
	public void setPulseSpeedNumericalValue(double pulseSpeedNumericalValue) {
		PulseSpeedNumericalValue = pulseSpeedNumericalValue;
	}
	public double getPulseSpeedLowValue() {
		return PulseSpeedLowValue;
	}
	public void setPulseSpeedLowValue(double pulseSpeedLowValue) {
		PulseSpeedLowValue = pulseSpeedLowValue;
	}
	public double getPulseSpeedMiddleValue() {
		return PulseSpeedMiddleValue;
	}
	public void setPulseSpeedMiddleValue(double pulseSpeedMiddleValue) {
		PulseSpeedMiddleValue = pulseSpeedMiddleValue;
	}
	public double getPulseSpeedHighValue() {
		return PulseSpeedHighValue;
	}
	public void setPulseSpeedHighValue(double pulseSpeedHighValue) {
		PulseSpeedHighValue = pulseSpeedHighValue;
	}
	public String getPulseSpeedFuzzyLabel() {
		return PulseSpeedFuzzyLabel;
	}
	public void setPulseSpeedFuzzyLabel(String pulseSpeedFuzzyLabel) {
		PulseSpeedFuzzyLabel = pulseSpeedFuzzyLabel;
	}
	public String getPulseSpeedId() {
		return PulseSpeedId;
	}
	public void setPulseSpeedId(String pulseSpeedId) {
		PulseSpeedId = pulseSpeedId;
	}
	public PulseSpeedFuzzyValue(double x,Fuzzification f) {
		String str = Double.toString(x);
		PulseSpeedNumericalValue = x;
		PulseSpeedId = "value_" + String.valueOf(x);
		PulseSpeedLowValue = f.getFuzzyMembershipValue(str,"LowValue");
		PulseSpeedMiddleValue = f.getFuzzyMembershipValue(str,"MiddleValue");
		PulseSpeedHighValue = f.getFuzzyMembershipValue(str,"HighValue");
		PulseSpeedFuzzyLabel = f.ValueFuzzyLabel(str);		
	}
}
