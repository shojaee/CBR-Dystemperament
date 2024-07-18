package datatypes;

import jcolibri.cbrcore.Attribute;

public class Demographic implements jcolibri.cbrcore.CaseComponent{
	public FuzzyAge ageFuzzy;
	public FuzzyBMI bmiFuzzy;
	public Double ageNumber,bmiNumber;
	public String gender,job;
	public Demographic(	FuzzyAge AgeFuzzy,FuzzyBMI BmiFuzzy,Double AgeNumber,Double BmiNumber,String Gender,String Job) {
		ageFuzzy = AgeFuzzy;
		bmiFuzzy = BmiFuzzy;
		bmiNumber = BmiNumber;
		ageNumber = AgeNumber;
		gender = Gender;
		job = Job;
	}
	public FuzzyAge getAgeFuzzy() {
		return ageFuzzy;
	}
	public void setAgeFuzzy(FuzzyAge ageFuzzy) {
		this.ageFuzzy = ageFuzzy;
	}
	public FuzzyBMI getBmiFuzzy() {
		return bmiFuzzy;
	}
	public void setBmiFuzzy(FuzzyBMI bmiFuzzy) {
		this.bmiFuzzy = bmiFuzzy;
	}
	public Double getAgeNumber() {
		return ageNumber;
	}
	public void setAgeNumber(Double ageNumber) {
		this.ageNumber = ageNumber;
	}
	public Double getBmiNumber() {
		return bmiNumber;
	}
	public void setBmiNumber(Double bmiNumber) {
		this.bmiNumber = bmiNumber;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return null;
	}
}
