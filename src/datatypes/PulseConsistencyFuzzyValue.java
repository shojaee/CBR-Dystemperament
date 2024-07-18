package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class PulseConsistencyFuzzyValue implements jcolibri.cbrcore.CaseComponent{
	double PulseConsistencyNumericalValue,PulseConsistencyLowValue,PulseConsistencyMiddleValue,PulseConsistencyHighValue;
	String PulseConsistencyFuzzyLabel,PulseConsistencyId;
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("PulseConsistencyId", this.getClass());
	}
	public double getPulseConsistencyNumericalValue() {
		return PulseConsistencyNumericalValue;
	}
	public void setPulseConsistencyNumericalValue(double pulseConsistencyNumericalValue) {
		PulseConsistencyNumericalValue = pulseConsistencyNumericalValue;
	}
	public double getPulseConsistencyLowValue() {
		return PulseConsistencyLowValue;
	}
	public void setPulseConsistencyLowValue(double pulseConsistencyLowValue) {
		PulseConsistencyLowValue = pulseConsistencyLowValue;
	}
	public double getPulseConsistencyMiddleValue() {
		return PulseConsistencyMiddleValue;
	}
	public void setPulseConsistencyMiddleValue(double pulseConsistencyMiddleValue) {
		PulseConsistencyMiddleValue = pulseConsistencyMiddleValue;
	}
	public double getPulseConsistencyHighValue() {
		return PulseConsistencyHighValue;
	}
	public void setPulseConsistencyHighValue(double pulseConsistencyHighValue) {
		PulseConsistencyHighValue = pulseConsistencyHighValue;
	}
	public String getPulseConsistencyFuzzyLabel() {
		return PulseConsistencyFuzzyLabel;
	}
	public void setPulseConsistencyFuzzyLabel(String pulseConsistencyFuzzyLabel) {
		PulseConsistencyFuzzyLabel = pulseConsistencyFuzzyLabel;
	}
	public String getPulseConsistencyId() {
		return PulseConsistencyId;
	}
	public void setPulseConsistencyId(String pulseConsistencyId) {
		PulseConsistencyId = pulseConsistencyId;
	}
	public PulseConsistencyFuzzyValue(double x,Fuzzification f) {
		String str = Double.toString(x);
		PulseConsistencyNumericalValue = x;
		PulseConsistencyId = "value_" + String.valueOf(x);
		PulseConsistencyLowValue = f.getFuzzyMembershipValue(str,"LowValue");
		PulseConsistencyMiddleValue = f.getFuzzyMembershipValue(str,"MiddleValue");
		PulseConsistencyHighValue = f.getFuzzyMembershipValue(str,"HighValue");
		PulseConsistencyFuzzyLabel = f.ValueFuzzyLabel(str);		
	}
}
