package datatypes;

import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;

public class FoodTransitionSpeedFuzzyValue implements jcolibri.cbrcore.CaseComponent{
	double FoodTransitionSpeedNumericalValue,FoodTransitionSpeedLowValue,FoodTransitionSpeedMiddleValue,FoodTransitionSpeedHighValue;
	String FoodTransitionSpeedFuzzyLabel,FoodTransitionSpeedId;
	@Override
	public Attribute getIdAttribute() {
		return new Attribute("FoodTransitionSpeedId", this.getClass());
	}
	public FoodTransitionSpeedFuzzyValue(double x,Fuzzification f) {
		String str = Double.toString(x);
		FoodTransitionSpeedNumericalValue = x;
		FoodTransitionSpeedId = "value_" + String.valueOf(x);
		FoodTransitionSpeedLowValue = f.getFuzzyMembershipValue(str,"LowValue");
		FoodTransitionSpeedMiddleValue = f.getFuzzyMembershipValue(str,"MiddleValue");
		FoodTransitionSpeedHighValue = f.getFuzzyMembershipValue(str,"HighValue");
		FoodTransitionSpeedFuzzyLabel = f.ValueFuzzyLabel(str);		
	}
	public double getFoodTransitionSpeedNumericalValue() {
		return FoodTransitionSpeedNumericalValue;
	}
	public void setFoodTransitionSpeedNumericalValue(double foodTransitionSpeedNumericalValue) {
		FoodTransitionSpeedNumericalValue = foodTransitionSpeedNumericalValue;
	}
	public double getFoodTransitionSpeedLowValue() {
		return FoodTransitionSpeedLowValue;
	}
	public void setFoodTransitionSpeedLowValue(double foodTransitionSpeedLowValue) {
		FoodTransitionSpeedLowValue = foodTransitionSpeedLowValue;
	}
	public double getFoodTransitionSpeedMiddleValue() {
		return FoodTransitionSpeedMiddleValue;
	}
	public void setFoodTransitionSpeedMiddleValue(double foodTransitionSpeedMiddleValue) {
		FoodTransitionSpeedMiddleValue = foodTransitionSpeedMiddleValue;
	}
	public double getFoodTransitionSpeedHighValue() {
		return FoodTransitionSpeedHighValue;
	}
	public void setFoodTransitionSpeedHighValue(double foodTransitionSpeedHighValue) {
		FoodTransitionSpeedHighValue = foodTransitionSpeedHighValue;
	}
	public String getFoodTransitionSpeedFuzzyLabel() {
		return FoodTransitionSpeedFuzzyLabel;
	}
	public void setFoodTransitionSpeedFuzzyLabel(String foodTransitionSpeedFuzzyLabel) {
		FoodTransitionSpeedFuzzyLabel = foodTransitionSpeedFuzzyLabel;
	}
	public String getFoodTransitionSpeedId() {
		return FoodTransitionSpeedId;
	}
	public void setFoodTransitionSpeedId(String foodTransitionSpeedId) {
		FoodTransitionSpeedId = foodTransitionSpeedId;
	}	

}
