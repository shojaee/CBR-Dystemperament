package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class PulseRateFuzzyValue implements jcolibri.cbrcore.CaseComponent{
	double PulseRateNumericalValue,PulseRateLowValue,PulseRateMiddleValue,PulseRateHighValue;
	String PulseRateFuzzyLabel,PulseRateId;
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("PulseRateId", this.getClass());
	}
	public PulseRateFuzzyValue(double x,Fuzzification f) {
		String str = Double.toString(x);
		PulseRateNumericalValue = x;
		PulseRateId = "value_" + String.valueOf(x);
		PulseRateLowValue = f.getFuzzyMembershipValue(str,"LowValue");
		PulseRateMiddleValue = f.getFuzzyMembershipValue(str,"MiddleValue");
		PulseRateHighValue = f.getFuzzyMembershipValue(str,"HighValue");
		PulseRateFuzzyLabel = f.ValueFuzzyLabel(str);		
	}
	public double getPulseRateNumericalValue() {
		return PulseRateNumericalValue;
	}
	public void setPulseRateNumericalValue(double pulseRateNumericalValue) {
		PulseRateNumericalValue = pulseRateNumericalValue;
	}
	public double getPulseRateLowValue() {
		return PulseRateLowValue;
	}
	public void setPulseRateLowValue(double pulseRateLowValue) {
		PulseRateLowValue = pulseRateLowValue;
	}
	public double getPulseRateMiddleValue() {
		return PulseRateMiddleValue;
	}
	public void setPulseRateMiddleValue(double pulseRateMiddleValue) {
		PulseRateMiddleValue = pulseRateMiddleValue;
	}
	public double getPulseRateHighValue() {
		return PulseRateHighValue;
	}
	public void setPulseRateHighValue(double pulseRateHighValue) {
		PulseRateHighValue = pulseRateHighValue;
	}
	public String getPulseRateFuzzyLabel() {
		return PulseRateFuzzyLabel;
	}
	public void setPulseRateFuzzyLabel(String pulseRateFuzzyLabel) {
		PulseRateFuzzyLabel = pulseRateFuzzyLabel;
	}
	public String getPulseRateId() {
		return PulseRateId;
	}
	public void setPulseRateId(String pulseRateId) {
		PulseRateId = pulseRateId;
	}
}
