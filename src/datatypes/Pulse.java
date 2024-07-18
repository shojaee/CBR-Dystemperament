package datatypes;

import jcolibri.cbrcore.Attribute;

public class Pulse implements jcolibri.cbrcore.CaseComponent{
	public PulseSpeedFuzzyValue pulseSpeedFuzzy;
	public PulsePowerFuzzyValue pulsePowerFuzzy;
	public PulseRateFuzzyValue pulseRateFuzzy;
	public PulseConsistencyFuzzyValue pulseConsistencyFuzzy;
	public Integer pulseSpeedNumber,pulsePowerNumber,pulseRateNumber,pulseConsistencyNumber;
	public Pulse(Integer PulseSpeedNumber,Integer PulsePowerNumber, Integer PulseRateNumber,Integer PulseConsistencyNumber,PulseSpeedFuzzyValue	PulseSpeedFuzzy,PulsePowerFuzzyValue PulsePowerFuzzy,PulseRateFuzzyValue PulseRateFuzzy,PulseConsistencyFuzzyValue PulseConsistencyFuzzy) {
		pulseSpeedNumber = PulseSpeedNumber;
		pulsePowerNumber = PulsePowerNumber;
		pulseRateNumber = PulseRateNumber;
		pulseConsistencyNumber = PulseConsistencyNumber;
		pulseSpeedFuzzy = PulseSpeedFuzzy;
		pulsePowerFuzzy = PulsePowerFuzzy;
		pulseRateFuzzy = PulseRateFuzzy;
		pulseConsistencyFuzzy = PulseConsistencyFuzzy;		
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
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}

}
