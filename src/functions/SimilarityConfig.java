package functions;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimilarityConfig {
	
	private static final long serialVersionUID = 1L;
	
	public ConfigField Age,BMI,DigestionPower,FoodTransitionSpeed,AppetiteLevel,ThirstinessLevel,PulseSpeed,PulsePower,PulseRate,PulseConsistency,
					Gender,Job,FaceColor,ThirstinessReliefType,Takhazkhoz,StoolType,GastralgiaTime,SalivationTime,MouthTaste,TongueColor,
					QasayanTime,HeavinessTime,CustodyDisposal,SleepingWakefulness,Climate,PhysicalActivity,Food,MentalHealth,TendencyTo,
					BenefitFrom,DisadvantageOf,HatredOf,BodyState,Limpness,Saliva,Salivation,TongueWettish,Qasayan,StomachHeaviness,
					GastricEructation,Flatulence,Gastralgia,FoodPutrefactionFreq,Heartburn,Hiccup,Acerbate,EatingMuchTiny,StoolConsistency,
					DefecationPeriod,Disease,OntologicalStructure,Appetite,Bdth,Demographic,GastrointestinalSymptom,PhysicalExamination,Principle,Pulse,Stool,Thirstiness;
	public int K;
  
	public SimilarityConfig() {
		Age = new ConfigField(); BMI = new ConfigField(); DigestionPower = new ConfigField(); FoodTransitionSpeed = new ConfigField(); 
		AppetiteLevel = new ConfigField(); ThirstinessLevel = new ConfigField(); PulseSpeed = new ConfigField(); PulsePower = new ConfigField(); 
		PulseRate = new ConfigField(); PulseConsistency = new ConfigField(); 
		Gender = new ConfigField(); Job = new ConfigField(); FaceColor = new ConfigField(); ThirstinessReliefType = new ConfigField(); 
		Takhazkhoz = new ConfigField(); StoolType = new ConfigField(); GastralgiaTime = new ConfigField(); SalivationTime = new ConfigField(); 
		MouthTaste = new ConfigField(); TongueColor = new ConfigField(); 
		QasayanTime = new ConfigField(); HeavinessTime = new ConfigField(); CustodyDisposal = new ConfigField(); 
		SleepingWakefulness = new ConfigField(); Climate = new ConfigField(); PhysicalActivity = new ConfigField(); Food = new ConfigField(); 
		MentalHealth = new ConfigField(); TendencyTo = new ConfigField(); 
		BenefitFrom = new ConfigField(); DisadvantageOf = new ConfigField(); HatredOf = new ConfigField(); BodyState = new ConfigField(); 
		Limpness = new ConfigField(); Saliva = new ConfigField(); Salivation = new ConfigField(); TongueWettish = new ConfigField(); 
		Qasayan = new ConfigField(); StomachHeaviness = new ConfigField(); 
		GastricEructation = new ConfigField(); Flatulence = new ConfigField(); Gastralgia = new ConfigField(); 
		FoodPutrefactionFreq = new ConfigField(); Heartburn = new ConfigField(); Hiccup = new ConfigField(); Acerbate = new ConfigField(); 
		EatingMuchTiny = new ConfigField(); StoolConsistency = new ConfigField(); 
		DefecationPeriod = new ConfigField(); Disease = new ConfigField(); OntologicalStructure = new ConfigField();
		Appetite = new ConfigField(); Bdth = new ConfigField(); Demographic = new ConfigField(); GastrointestinalSymptom = new ConfigField(); 
		PhysicalExamination = new ConfigField(); Principle = new ConfigField(); Pulse = new ConfigField(); Stool = new ConfigField(); Thirstiness = new ConfigField(); 		
		K = 3;
	}
	
}
