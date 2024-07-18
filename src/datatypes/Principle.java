package datatypes;

import jcolibri.cbrcore.Attribute;
import jcolibri.extensions.textual.IE.opennlp.IETextOpenNLP;

public class Principle implements jcolibri.cbrcore.CaseComponent{
	public IETextOpenNLP custodyDisposal,sleepingWakefulness,climate,physicalActivity,food,mentalHealth;
	
	public Principle(IETextOpenNLP CustodyDisposal,IETextOpenNLP SleepingWakefulness,IETextOpenNLP Climate,IETextOpenNLP PhysicalActivity,IETextOpenNLP Food,IETextOpenNLP MentalHealth) {
		custodyDisposal = CustodyDisposal;
		sleepingWakefulness = SleepingWakefulness;
		climate = Climate;
		physicalActivity = PhysicalActivity;
		food = Food;
		mentalHealth = MentalHealth;
	}

	public IETextOpenNLP getCustodyDisposal() {
		return custodyDisposal;
	}

	public void setCustodyDisposal(IETextOpenNLP custodyDisposal) {
		this.custodyDisposal = custodyDisposal;
	}

	public IETextOpenNLP getSleepingWakefulness() {
		return sleepingWakefulness;
	}

	public void setSleepingWakefulness(IETextOpenNLP sleepingWakefulness) {
		this.sleepingWakefulness = sleepingWakefulness;
	}

	public IETextOpenNLP getClimate() {
		return climate;
	}

	public void setClimate(IETextOpenNLP climate) {
		this.climate = climate;
	}

	public IETextOpenNLP getPhysicalActivity() {
		return physicalActivity;
	}

	public void setPhysicalActivity(IETextOpenNLP physicalActivity) {
		this.physicalActivity = physicalActivity;
	}

	public IETextOpenNLP getFood() {
		return food;
	}

	public void setFood(IETextOpenNLP food) {
		this.food = food;
	}

	public IETextOpenNLP getMentalHealth() {
		return mentalHealth;
	}

	public void setMentalHealth(IETextOpenNLP mentalHealth) {
		this.mentalHealth = mentalHealth;
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
