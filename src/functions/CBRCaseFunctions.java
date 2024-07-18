package functions;

import java.io.File;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import datatypes.*;
import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.datatypes.Instance;
import jcolibri.datatypes.Text;
import jcolibri.extensions.textual.IE.opennlp.OpennlpSplitter;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumCyclicDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Table;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntCosine;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeep;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeepBasic;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDetail;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.CosineCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.DiceCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.JaccardCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.OverlapCoefficient;
import persianmedicine.cbrdystemp.DystempDescription;
import persianmedicine.cbrdystemp.DystempDescription.OL1;
import persianmedicine.cbrdystemp.DystempDescription.OL2;
import persianmedicine.cbrdystemp.DystempDescription.OL3;

public class CBRCaseFunctions {
	
	public static String GetValueFromInstance(Instance ins,String start) {
		String str="";
		str = ins.toString();
		str = str.substring(start.length());
		return str;
		
	}	
	
	public static void InitDescriptionOfCase(DystempDescription desc,Fuzzification f) {
		
	//	System.out.println(desc.MainConcept);
			
		if (desc.ageNumber != null) desc.ageFuzzy = new FuzzyAge(desc.ageNumber,f);
		if (desc.bmiNumber != null) desc.bmiFuzzy = new FuzzyBMI(desc.bmiNumber,f);
		
		if (desc.limpnessStr != null) desc.limpness = OL2.valueOf(desc.limpnessStr);
		if (desc.salivaStr != null) desc.saliva = OL2.valueOf(desc.salivaStr);
		if (desc.salivationStr != null) desc.salivation = OL2.valueOf(desc.salivationStr);
		if (desc.tongueWettishStr != null) desc.tongueWettish = OL2.valueOf(desc.tongueWettishStr);
		if (desc.qasayanStr != null) desc.qasayan = OL2.valueOf(desc.qasayanStr);
		if (desc.stomachHeavinessStr != null) desc.stomachHeaviness = OL2.valueOf(desc.stomachHeavinessStr);
		if (desc.digestionPowerNumber != null) desc.digestionPowerFuzzy = new DigestionPowerFuzzyValue(desc.digestionPowerNumber,f);
		if (desc.gastricEructationStr != null) desc.gastricEructation = OL2.valueOf(desc.gastricEructationStr);
		if (desc.flatulenceStr != null) desc.flatulence = OL2.valueOf(desc.flatulenceStr);
		if (desc.gastralgiaStr != null) desc.gastralgia = OL2.valueOf(desc.gastralgiaStr);
		if (desc.foodTransitionSpeedNumber != null) desc.foodTransitionSpeedFuzzy = new FoodTransitionSpeedFuzzyValue(desc.foodTransitionSpeedNumber,f);
		if (desc.foodPutrefactionFreqStr != null) desc.foodPutrefactionFreq = OL2.valueOf(desc.foodPutrefactionFreqStr);
		if (desc.heartburnStr != null) desc.heartburn = OL2.valueOf(desc.heartburnStr);
		if (desc.hiccupStr != null) desc.hiccup = OL2.valueOf(desc.hiccupStr);
		if (desc.acerbateStr != null) desc.acerbate = OL2.valueOf(desc.acerbateStr);
		if (desc.thirstinessLevelNumber != null) desc.thirstinessLevelFuzzy = new ThirstinessLevelFuzzyValue(desc.thirstinessLevelNumber,f);
		if (desc.appetiteLevelNumber != null) desc.appetiteLevelFuzzy = new AppetiteLevelFuzzyValue(desc.appetiteLevelNumber,f);
		if (desc.eatingMuchTinyStr != null) desc.eatingMuchTiny = OL2.valueOf(desc.eatingMuchTinyStr);
				
		if (desc.pulseSpeedNumber != null) desc.pulseSpeedFuzzy = new PulseSpeedFuzzyValue(desc.pulseSpeedNumber,f);
		if (desc.pulsePowerNumber != null) desc.pulsePowerFuzzy = new PulsePowerFuzzyValue(desc.pulsePowerNumber,f);
		if (desc.pulseRateNumber != null) desc.pulseRateFuzzy = new PulseRateFuzzyValue(desc.pulseRateNumber,f);
		if (desc.pulseConsistencyNumber != null) desc.pulseConsistencyFuzzy = new PulseConsistencyFuzzyValue(desc.pulseConsistencyNumber,f);
		
		if (desc.stoolConsistencyStr != null) desc.stoolConsistency = OL3.valueOf(desc.stoolConsistencyStr);
		if (desc.defecationPeriodStr != null) desc.defecationPeriod = OL1.valueOf(desc.defecationPeriodStr);	
		
		//Appetite
		if (desc.appetiteLevelNumber!=null || desc.eatingMuchTiny!=null || desc.appetiteLevelFuzzy!=null)
		  desc.appetite = new Appetite(desc.appetiteLevelNumber,desc.eatingMuchTiny,desc.appetiteLevelFuzzy);
		
		//DBTH
		if(desc.tendencyTo!=null || desc.benefitFrom!=null || desc.disadvantageOf!=null || desc.hatredOf!=null)
			desc.bdth = new BDTH(desc.tendencyTo, desc.benefitFrom, desc.disadvantageOf, desc.hatredOf);
		
		//Demographic
		if(desc.ageFuzzy!=null || desc.bmiFuzzy!=null || desc.ageNumber!=null || desc.bmiNumber!=null || desc.gender!=null || desc.job!=null)
			desc.demographic = new Demographic(desc.ageFuzzy, desc.bmiFuzzy, desc.ageNumber, desc.bmiNumber, desc.gender, desc.job);	
				
		//GastrointestinalSymptom
		if(desc.acerbate!=null || desc.digestionPowerNumber!=null || desc.digestionPowerFuzzy!=null || desc.flatulence!=null || desc.foodPutrefactionFreq!=null || desc.foodTransitionSpeedNumber!=null || desc.foodTransitionSpeedFuzzy!=null || desc.gastralgia!=null || desc.gastralgiaTime!=null || desc.gastricEructation!=null || desc.heartburn!=null || desc.heavinessTime!=null || desc.hiccup!=null || desc.qasayan!=null || desc.qasayanTime!=null || desc.stomachHeaviness!=null || desc.takhazkhoz!=null)
			desc.gastrointestinalSymptom = new GastrointestinalSymptom(desc.acerbate, desc.digestionPowerNumber, desc.digestionPowerFuzzy, desc.flatulence, desc.foodPutrefactionFreq, desc.foodTransitionSpeedNumber, desc.foodTransitionSpeedFuzzy, desc.gastralgia, desc.gastralgiaTime, desc.gastricEructation, desc.heartburn, desc.heavinessTime, desc.hiccup, desc.qasayan, desc.qasayanTime, desc.stomachHeaviness, desc.takhazkhoz);
		
		//PhysicalExamination
		if(desc.bodyState!=null || desc.faceColor!=null || desc.limpness!=null || desc.mouthTaste!=null || desc.saliva!=null || desc.salivation!=null || desc.salivationTime!=null || desc.tongueColor!=null || desc.tongueWettish!=null)
			desc.physicalExamination = new PhysicalExamination(desc.bodyState, desc.faceColor, desc.limpness, desc.mouthTaste, desc.saliva, desc.salivation, desc.salivationTime, desc.tongueColor, desc.tongueWettish);
		
		
		//Principle
		if(desc.custodyDisposal!=null || desc.sleepingWakefulness!=null || desc.climate!=null || desc.physicalActivity!=null || desc.food!=null || desc.mentalHealth!=null)
			desc.principle = new Principle(desc.custodyDisposal, desc.sleepingWakefulness, desc.climate, desc.physicalActivity, desc.food, desc.mentalHealth);
		
		//Pulse
		if(desc.pulseSpeedNumber!=null || desc.pulsePowerNumber!=null || desc.pulseRateNumber!=null || desc.pulseConsistencyNumber!=null || desc.pulseSpeedFuzzy!=null || desc.pulsePowerFuzzy!=null || desc.pulseRateFuzzy!=null || desc.pulseConsistencyFuzzy!=null)
			desc.pulse = new Pulse(desc.pulseSpeedNumber, desc.pulsePowerNumber, desc.pulseRateNumber, desc.pulseConsistencyNumber, desc.pulseSpeedFuzzy, desc.pulsePowerFuzzy, desc.pulseRateFuzzy, desc.pulseConsistencyFuzzy);
		
		//Stool
		if(desc.stoolConsistency!=null || desc.defecationPeriod!=null || desc.stoolType!=null)
			desc.stool = new Stool(desc.stoolConsistency, desc.defecationPeriod, desc.stoolType);
		
		//Thirstiness
		if(desc.thirstinessLevelNumber!=null || desc.thirstinessLevelFuzzy!=null || desc.thirstinessReliefType!=null)
			desc.thirstiness = new Thirstiness(desc.thirstinessLevelNumber, desc.thirstinessLevelFuzzy, desc.thirstinessReliefType);
	}
	
	
	public static void InitSimilarityConfig(SimilarityConfig sim,String numFunc,String enumFunc,String textFunc,String ontoFunc,String strFunc,boolean ontStruct, int k) {
		sim.Age.Function = sim.BMI.Function = sim.DigestionPower.Function = sim.FoodTransitionSpeed.Function = sim.AppetiteLevel.Function = sim.ThirstinessLevel.Function = sim.PulseSpeed.Function = sim.PulsePower.Function = sim.PulseRate.Function = sim.PulseConsistency.Function = numFunc;
		sim.Limpness.Function = sim.Saliva.Function = sim.Salivation.Function = sim.TongueWettish.Function = sim.Qasayan.Function = sim.StomachHeaviness.Function = sim.GastricEructation.Function = sim.Flatulence.Function = sim.Gastralgia.Function = sim.FoodPutrefactionFreq.Function = sim.Heartburn.Function = sim.Hiccup.Function = sim.Acerbate.Function = sim.EatingMuchTiny.Function = sim.StoolConsistency.Function = sim.DefecationPeriod.Function = enumFunc;
		sim.CustodyDisposal.Function = sim.SleepingWakefulness.Function = sim.Climate.Function = sim.PhysicalActivity.Function = sim.Food.Function = sim.MentalHealth.Function = sim.TendencyTo.Function = sim.BenefitFrom.Function = sim.DisadvantageOf.Function = sim.HatredOf.Function = sim.BodyState.Function = textFunc;
		sim.Gender.Function = sim.Job.Function = sim.FaceColor.Function = sim.ThirstinessReliefType.Function = sim.Takhazkhoz.Function = sim.StoolType.Function = sim.GastralgiaTime.Function = sim.SalivationTime.Function = sim.MouthTaste.Function = sim.TongueColor.Function = sim.QasayanTime.Function = sim.HeavinessTime.Function = strFunc;	
		if (ontoFunc.indexOf("Text")>=0) sim.Disease.Function = textFunc;
		else sim.Disease.Function = ontoFunc;
		if (ontStruct) {
			sim.OntologicalStructure.Function = sim.Appetite.Function = sim.Bdth.Function = sim.Demographic.Function = sim.GastrointestinalSymptom.Function = 
					sim.PhysicalExamination.Function = sim.Principle.Function = sim.Pulse.Function = sim.Stool.Function = sim.Thirstiness.Function = "OntologicalStructure";
		}
		else {
			sim.OntologicalStructure.Function = "Attribute-Value";
		}
		
	}
	
	private static LocalSimilarityFunction localSimilFactory(String name)
	{
		if(name.equals("Equal"))
			return new Equal();
		else if(name.equals("EnumCyclicDistance"))
			return new EnumCyclicDistance();
		else if(name.equals("EnumDistance"))
			return new EnumDistance();
		else if(name.equals("OntCosine"))
			return new OntCosine();
		else if(name.equals("OntDeep"))
			return new OntDeep();
		else if(name.equals("OntDeepBasic"))
			return new OntDeepBasic();
		else if(name.equals("OntDetail"))
			return new OntDetail();
		else if(name.equals("OntoSimMax"))
			return new OntoSimMax();
		else if(name.equals("CosineCoefficient"))
			return new CosineCoefficient();
		else if(name.equals("DiceCoefficient"))
			return new DiceCoefficient();
		else if(name.equals("JaccardCoefficient"))
			return new JaccardCoefficient();
		else if(name.equals("OverlapCoefficient"))
			return new OverlapCoefficient();
		else 
		{
			//org.apache.commons.logging.LogFactory.getLog(this.getClass()).error("Simil Function not found");
			return null;
		}
	}	
	
	public static SimilarityConfig ReadSimilarityFromFile() {
        
        File xmlFile = new File("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\simconfig.xml");
        if (xmlFile.exists()) { 
	        JAXBContext jaxbContext;
	        try
	        {
	            jaxbContext = JAXBContext.newInstance(SimilarityConfig.class);
	            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	            SimilarityConfig sim = (SimilarityConfig) jaxbUnmarshaller.unmarshal(xmlFile);
	            return sim; 
	        }
	        catch (JAXBException e) 
	        {
	            e.printStackTrace();
	        }
        }
        return new SimilarityConfig();
    }	
	
    public static void SaveSimilarityToFile(SimilarityConfig sim) 
    {
        try
        {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(SimilarityConfig.class);
             
            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
 
           //Store XML to File
            File file = new File("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\simconfig.xml");
             
            //Writes XML file to file-system
            jaxbMarshaller.marshal(sim, file); 
        } 
        catch (JAXBException e) 
        {
            e.printStackTrace();
        }
    }	
    
	public static NNConfig getSimilarityConfig(SimilarityConfig sim)
	{
		if(sim.OntologicalStructure.Function.indexOf("OntologicalStructure")>=0) return getSimilarityConfigForOntologicalStructure(sim);
		else return getSimilarityConfigForAttributeValueStructure(sim);	
	}

    public static NNConfig getSimilarityConfigForOntologicalStructure(SimilarityConfig sim) {
		NNConfig config = new NNConfig();
		Attribute attribute;
		LocalSimilarityFunction function;
		
		config.setDescriptionSimFunction(new OntologicalCBRAverage());
		
		//Appetite
		config.addMapping(attribute = new Attribute("appetite",   DystempDescription.class), new Average());
		config.setWeight(attribute, sim.Appetite.Weight);
		if (sim.AppetiteLevel.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("appetiteLevelFuzzy",   Appetite.class), new FuzzyAverage());
			config.setWeight(attribute, sim.AppetiteLevel.Weight);
			config.addMapping(attribute = new Attribute("AppetiteLevelLowValue",   AppetiteLevelFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AppetiteLevelMiddleValue",     AppetiteLevelFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AppetiteLevelHighValue",  AppetiteLevelFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.AppetiteLevel.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("appetiteLevelNumber",   Appetite.class), new Interval(10));
			config.setWeight(attribute, sim.AppetiteLevel.Weight);
		}	
		
		attribute = new Attribute("eatingMuchTiny",Appetite.class);
		if (sim.EatingMuchTiny.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.EatingMuchTiny.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.EatingMuchTiny.Weight);		
		
		//BDTH
		config.addMapping(attribute = new Attribute("bdth",   DystempDescription.class), new Average());
		config.setWeight(attribute, sim.Bdth.Weight);
		attribute = new Attribute("tendencyTo",BDTH.class);
		function = localSimilFactory(sim.TendencyTo.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.TendencyTo.Weight);

		attribute = new Attribute("benefitFrom",BDTH.class);
		function = localSimilFactory(sim.BenefitFrom.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.BenefitFrom.Weight);

		attribute = new Attribute("disadvantageOf",BDTH.class);
		function = localSimilFactory(sim.DisadvantageOf.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.DisadvantageOf.Weight);

		attribute = new Attribute("hatredOf",BDTH.class);
		function = localSimilFactory(sim.HatredOf.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.HatredOf.Weight);		
		
		
		//Demographic
		config.addMapping(attribute = new Attribute("demographic",   DystempDescription.class), new Average());
		config.setWeight(attribute, sim.Demographic.Weight);
		if (sim.Age.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("ageFuzzy",   Demographic.class), new FuzzyAverage());
			config.setWeight(attribute, sim.Age.Weight);
			config.addMapping(attribute = new Attribute("AgeGrowth",   FuzzyAge.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AgeYoung",     FuzzyAge.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AgeMiddle",  FuzzyAge.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AgeOld", FuzzyAge.class), new Interval(1));			
			config.setWeight(attribute, 1.0);
		}
		else if (sim.Age.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("ageNumber",   Demographic.class), new Interval(100));
			config.setWeight(attribute, sim.Age.Weight);
		}
		
		if (sim.BMI.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("bmiFuzzy",   Demographic.class), new FuzzyAverage());
			config.setWeight(attribute, sim.BMI.Weight);
			config.addMapping(attribute = new Attribute("BMIUnderWeight",   FuzzyBMI.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("BMINormal",     FuzzyBMI.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("BMIOverWeight",  FuzzyBMI.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("Obese", FuzzyBMI.class), new Interval(1));			
			config.setWeight(attribute, 1.0);
		}
		else if (sim.BMI.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("bmiNumber",   Demographic.class), new Interval(50));
			config.setWeight(attribute, sim.BMI.Weight);
		}			
		attribute = new Attribute("gender",Demographic.class);
		function = localSimilFactory(sim.Gender.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Gender.Weight);
		
		attribute = new Attribute("job",Demographic.class);
		function = localSimilFactory(sim.Job.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Job.Weight);
		
		//Gastrointestinal Symptom
		config.addMapping(attribute = new Attribute("gastrointestinalSymptom",   DystempDescription.class), new Average());
		config.setWeight(attribute, sim.GastrointestinalSymptom.Weight);
		if (sim.DigestionPower.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("digestionPowerFuzzy",   GastrointestinalSymptom.class), new FuzzyAverage());
			config.setWeight(attribute, sim.DigestionPower.Weight);
			config.addMapping(attribute = new Attribute("DigestionPowerLowValue",   DigestionPowerFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("DigestionPowerMiddleValue",     DigestionPowerFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("DigestionPowerHighValue",  DigestionPowerFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.DigestionPower.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("digestionPowerNumber",   GastrointestinalSymptom.class), new Interval(10));
			config.setWeight(attribute, sim.DigestionPower.Weight);
		}	

		if (sim.FoodTransitionSpeed.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("foodTransitionSpeedFuzzy",   GastrointestinalSymptom.class), new FuzzyAverage());
			config.setWeight(attribute, sim.FoodTransitionSpeed.Weight);
			config.addMapping(attribute = new Attribute("FoodTransitionSpeedLowValue",   FoodTransitionSpeedFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("FoodTransitionSpeedMiddleValue",     FoodTransitionSpeedFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("FoodTransitionSpeedHighValue",  FoodTransitionSpeedFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.FoodTransitionSpeed.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("foodTransitionSpeedNumber",   GastrointestinalSymptom.class), new Interval(10));
			config.setWeight(attribute, sim.FoodTransitionSpeed.Weight);
		}
		attribute = new Attribute("takhazkhoz",GastrointestinalSymptom.class);
		function = localSimilFactory(sim.Takhazkhoz.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Takhazkhoz.Weight);		
		
		attribute = new Attribute("gastralgiaTime",GastrointestinalSymptom.class);
		function = localSimilFactory(sim.GastralgiaTime.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.GastralgiaTime.Weight);

		attribute = new Attribute("qasayanTime",GastrointestinalSymptom.class);
		function = localSimilFactory(sim.QasayanTime.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.QasayanTime.Weight);

		attribute = new Attribute("heavinessTime",GastrointestinalSymptom.class);
		function = localSimilFactory(sim.HeavinessTime.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.HeavinessTime.Weight);

		attribute = new Attribute("qasayan",GastrointestinalSymptom.class);
		if (sim.Qasayan.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.Qasayan.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Qasayan.Weight);

		attribute = new Attribute("stomachHeaviness",GastrointestinalSymptom.class);
		if (sim.StomachHeaviness.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.StomachHeaviness.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.StomachHeaviness.Weight);

		attribute = new Attribute("gastricEructation",GastrointestinalSymptom.class);
		if (sim.GastricEructation.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.GastricEructation.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.GastricEructation.Weight);

		attribute = new Attribute("flatulence",GastrointestinalSymptom.class);
		if (sim.Flatulence.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.Flatulence.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Flatulence.Weight);

		attribute = new Attribute("gastralgia",GastrointestinalSymptom.class);
		if (sim.Gastralgia.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.Gastralgia.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Gastralgia.Weight);

		attribute = new Attribute("foodPutrefactionFreq",GastrointestinalSymptom.class);
		if (sim.FoodPutrefactionFreq.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.FoodPutrefactionFreq.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.FoodPutrefactionFreq.Weight);

		attribute = new Attribute("heartburn",GastrointestinalSymptom.class);
		if (sim.Heartburn.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.Heartburn.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Heartburn.Weight);

		attribute = new Attribute("hiccup",GastrointestinalSymptom.class);
		if (sim.Hiccup.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.Hiccup.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Hiccup.Weight);

		attribute = new Attribute("acerbate",GastrointestinalSymptom.class);
		if (sim.Acerbate.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.Acerbate.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Acerbate.Weight);		
		
		//Physical Examination
		config.addMapping(attribute = new Attribute("physicalExamination",   DystempDescription.class), new Average());
		config.setWeight(attribute, sim.PhysicalExamination.Weight);

		attribute = new Attribute("tongueColor",PhysicalExamination.class);
		function = localSimilFactory(sim.TongueColor.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.TongueColor.Weight);

		attribute = new Attribute("faceColor",PhysicalExamination.class);
		function = localSimilFactory(sim.FaceColor.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.FaceColor.Weight);		
		
		attribute = new Attribute("mouthTaste",PhysicalExamination.class);
		function = localSimilFactory(sim.MouthTaste.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.MouthTaste.Weight);
		
		attribute = new Attribute("salivationTime",PhysicalExamination.class);
		function = localSimilFactory(sim.SalivationTime.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.SalivationTime.Weight);		
		
		attribute = new Attribute("bodyState",PhysicalExamination.class);
		function = localSimilFactory(sim.BodyState.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.BodyState.Weight);

		attribute = new Attribute("limpness",PhysicalExamination.class);
		if (sim.Limpness.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.Limpness.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Limpness.Weight);

		attribute = new Attribute("saliva",PhysicalExamination.class);
		function = localSimilFactory(sim.Saliva.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Saliva.Weight);

		attribute = new Attribute("salivation",PhysicalExamination.class);
		if (sim.Salivation.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.Salivation.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Salivation.Weight);

		attribute = new Attribute("tongueWettish",PhysicalExamination.class);
		if (sim.TongueWettish.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL1.txt");
		else function = localSimilFactory(sim.TongueWettish.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.TongueWettish.Weight);		
		
		//Principle
		config.addMapping(attribute = new Attribute("principle",   DystempDescription.class), new Average());
		config.setWeight(attribute, sim.Principle.Weight);

		attribute = new Attribute("custodyDisposal",Principle.class);
		function = localSimilFactory(sim.CustodyDisposal.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.CustodyDisposal.Weight);

		attribute = new Attribute("sleepingWakefulness",Principle.class);
		function = localSimilFactory(sim.SleepingWakefulness.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.SleepingWakefulness.Weight);

		attribute = new Attribute("climate",Principle.class);
		function = localSimilFactory(sim.Climate.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Climate.Weight);

		attribute = new Attribute("physicalActivity",Principle.class);
		function = localSimilFactory(sim.PhysicalActivity.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.PhysicalActivity.Weight);

		attribute = new Attribute("food",Principle.class);
		function = localSimilFactory(sim.Food.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Food.Weight);

		attribute = new Attribute("mentalHealth",Principle.class);
		function = localSimilFactory(sim.MentalHealth.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.MentalHealth.Weight);		
		
		
		//Pulse
		config.addMapping(attribute = new Attribute("pulse",   DystempDescription.class), new Average());	
		config.setWeight(attribute, sim.Pulse.Weight);
		if (sim.PulseSpeed.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("pulseSpeedFuzzy",   Pulse.class), new FuzzyAverage());
			config.setWeight(attribute, sim.PulseSpeed.Weight);
			config.addMapping(attribute = new Attribute("PulseSpeedLowValue",   PulseSpeedFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseSpeedMiddleValue",     PulseSpeedFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseSpeedHighValue",  PulseSpeedFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.PulseSpeed.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("pulseSpeedNumber",   Pulse.class), new Interval(10));
			config.setWeight(attribute, sim.PulseSpeed.Weight);
		}	
	
		if (sim.PulsePower.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("pulsePowerFuzzy",   Pulse.class), new FuzzyAverage());
			config.setWeight(attribute, sim.PulsePower.Weight);
			config.addMapping(attribute = new Attribute("PulsePowerLowValue",   PulsePowerFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulsePowerMiddleValue",     PulsePowerFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulsePowerHighValue",  PulsePowerFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.PulsePower.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("pulsePowerNumber",   Pulse.class), new Interval(10));
			config.setWeight(attribute, sim.PulsePower.Weight);
		}	
	
		if (sim.PulseRate.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("pulseRateFuzzy",   Pulse.class), new FuzzyAverage());
			config.setWeight(attribute, sim.PulseRate.Weight);
			config.addMapping(attribute = new Attribute("PulseRateLowValue",   PulseRateFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseRateMiddleValue",     PulseRateFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseRateHighValue",  PulseRateFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.PulseRate.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("pulseRateNumber",   Pulse.class), new Interval(10));
			config.setWeight(attribute, sim.PulseRate.Weight);
		}	
	
		if (sim.PulseConsistency.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("pulseConsistencyFuzzy",   Pulse.class), new FuzzyAverage());
			config.setWeight(attribute, sim.PulseConsistency.Weight);
			config.addMapping(attribute = new Attribute("PulseConsistencyLowValue",   PulseConsistencyFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseConsistencyMiddleValue",     PulseConsistencyFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseConsistencyHighValue",  PulseConsistencyFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.PulseConsistency.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("pulseConsistencyNumber",   Pulse.class), new Interval(10));
			config.setWeight(attribute, sim.PulseConsistency.Weight);
		}	
	
		
		
		//Stool
		config.addMapping(attribute = new Attribute("stool",   DystempDescription.class), new Average());
		config.setWeight(attribute, sim.Stool.Weight);
		attribute = new Attribute("stoolType",Stool.class);
		function = localSimilFactory(sim.StoolType.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.StoolType.Weight);
		
		attribute = new Attribute("stoolConsistency",Stool.class);
		if (sim.StoolConsistency.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL2.txt");
		else function = localSimilFactory(sim.StoolConsistency.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.StoolConsistency.Weight);

		attribute = new Attribute("defecationPeriod",Stool.class);
		if (sim.DefecationPeriod.Function.indexOf("Table")>=0) function = new Table("persianmedicine/cbrdystemp/OL3.txt");
		else function = localSimilFactory(sim.DefecationPeriod.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.DefecationPeriod.Weight);		
		
		//Thirstiness
		config.addMapping(attribute = new Attribute("thirstiness",   DystempDescription.class), new Average());
		config.setWeight(attribute, sim.Thirstiness.Weight);
		if (sim.ThirstinessLevel.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("thirstinessLevelFuzzy",   Thirstiness.class), new FuzzyAverage());
			config.setWeight(attribute, sim.ThirstinessLevel.Weight);
			config.addMapping(attribute = new Attribute("ThirstinessLevelLowValue",   ThirstinessLevelFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("ThirstinessLevelMiddleValue",     ThirstinessLevelFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("ThirstinessLevelHighValue",  ThirstinessLevelFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.ThirstinessLevel.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("thirstinessLevelNumber",   Thirstiness.class), new Interval(10));
			config.setWeight(attribute, sim.ThirstinessLevel.Weight);
		}	
		attribute = new Attribute("thirstinessReliefType",Thirstiness.class);
		function = localSimilFactory(sim.ThirstinessReliefType.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.ThirstinessReliefType.Weight);
		
		attribute = new Attribute("disease",DystempDescription.class);
		function = localSimilFactory(sim.Disease.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Disease.Weight);
		
		return config;
    }
    	
	public static NNConfig getSimilarityConfigForAttributeValueStructure(SimilarityConfig sim)
	{
		NNConfig config = new NNConfig();
		Attribute attribute;
		LocalSimilarityFunction function;
		
		config.setDescriptionSimFunction(new CBRAverage());
		
		if (sim.Age.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("ageFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.Age.Weight);
			config.addMapping(attribute = new Attribute("AgeGrowth",   FuzzyAge.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AgeYoung",     FuzzyAge.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AgeMiddle",  FuzzyAge.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AgeOld", FuzzyAge.class), new Interval(1));			
			config.setWeight(attribute, 1.0);
		}
		else if (sim.Age.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("ageNumber",   DystempDescription.class), new Interval(100));
			config.setWeight(attribute, sim.Age.Weight);
		}
		
		if (sim.BMI.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("bmiFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.BMI.Weight);
			config.addMapping(attribute = new Attribute("BMIUnderWeight",   FuzzyBMI.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("BMINormal",     FuzzyBMI.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("BMIOverWeight",  FuzzyBMI.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("Obese", FuzzyBMI.class), new Interval(1));			
			config.setWeight(attribute, 1.0);
		}
		else if (sim.BMI.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("bmiNumber",   DystempDescription.class), new Interval(50));
			config.setWeight(attribute, sim.BMI.Weight);
		}	
			
		if (sim.DigestionPower.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("digestionPowerFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.DigestionPower.Weight);
			config.addMapping(attribute = new Attribute("DigestionPowerLowValue",   DigestionPowerFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("DigestionPowerMiddleValue",     DigestionPowerFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("DigestionPowerHighValue",  DigestionPowerFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.DigestionPower.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("digestionPowerNumber",   DystempDescription.class), new Interval(10));
			config.setWeight(attribute, sim.DigestionPower.Weight);
		}	

		if (sim.FoodTransitionSpeed.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("foodTransitionSpeedFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.FoodTransitionSpeed.Weight);
			config.addMapping(attribute = new Attribute("FoodTransitionSpeedLowValue",   FoodTransitionSpeedFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("FoodTransitionSpeedMiddleValue",     FoodTransitionSpeedFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("FoodTransitionSpeedHighValue",  FoodTransitionSpeedFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.FoodTransitionSpeed.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("foodTransitionSpeedNumber",   DystempDescription.class), new Interval(10));
			config.setWeight(attribute, sim.FoodTransitionSpeed.Weight);
		}	
		
		if (sim.AppetiteLevel.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("appetiteLevelFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.AppetiteLevel.Weight);
			config.addMapping(attribute = new Attribute("AppetiteLevelLowValue",   AppetiteLevelFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AppetiteLevelMiddleValue",     AppetiteLevelFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("AppetiteLevelHighValue",  AppetiteLevelFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.AppetiteLevel.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("appetiteLevelNumber",   DystempDescription.class), new Interval(10));
			config.setWeight(attribute, sim.AppetiteLevel.Weight);
		}	
	
		if (sim.ThirstinessLevel.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("thirstinessLevelFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.ThirstinessLevel.Weight);
			config.addMapping(attribute = new Attribute("ThirstinessLevelLowValue",   ThirstinessLevelFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("ThirstinessLevelMiddleValue",     ThirstinessLevelFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("ThirstinessLevelHighValue",  ThirstinessLevelFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.ThirstinessLevel.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("thirstinessLevelNumber",   DystempDescription.class), new Interval(10));
			config.setWeight(attribute, sim.ThirstinessLevel.Weight);
		}	
	
		if (sim.PulseSpeed.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("pulseSpeedFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.PulseSpeed.Weight);
			config.addMapping(attribute = new Attribute("PulseSpeedLowValue",   PulseSpeedFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseSpeedMiddleValue",     PulseSpeedFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseSpeedHighValue",  PulseSpeedFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.PulseSpeed.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("pulseSpeedNumber",   DystempDescription.class), new Interval(10));
			config.setWeight(attribute, sim.PulseSpeed.Weight);
		}	
	
		if (sim.PulsePower.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("pulsePowerFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.PulsePower.Weight);
			config.addMapping(attribute = new Attribute("PulsePowerLowValue",   PulsePowerFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulsePowerMiddleValue",     PulsePowerFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulsePowerHighValue",  PulsePowerFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.PulsePower.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("pulsePowerNumber",   DystempDescription.class), new Interval(10));
			config.setWeight(attribute, sim.PulsePower.Weight);
		}	
	
		if (sim.PulseRate.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("pulseRateFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.PulseRate.Weight);
			config.addMapping(attribute = new Attribute("PulseRateLowValue",   PulseRateFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseRateMiddleValue",     PulseRateFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseRateHighValue",  PulseRateFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.PulseRate.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("pulseRateNumber",   DystempDescription.class), new Interval(10));
			config.setWeight(attribute, sim.PulseRate.Weight);
		}	
	
		if (sim.PulseConsistency.Function.indexOf("Fuzzy")>=0) {
			config.addMapping(attribute = new Attribute("pulseConsistencyFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.setWeight(attribute, sim.PulseConsistency.Weight);
			config.addMapping(attribute = new Attribute("PulseConsistencyLowValue",   PulseConsistencyFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseConsistencyMiddleValue",     PulseConsistencyFuzzyValue.class), new Interval(1));
			config.setWeight(attribute, 1.0);
			config.addMapping(attribute = new Attribute("PulseConsistencyHighValue",  PulseConsistencyFuzzyValue.class), new Interval(1));		
			config.setWeight(attribute, 1.0);
		}
		else if (sim.PulseConsistency.Function.indexOf("Numerical")>=0) {
			config.addMapping(attribute = new Attribute("pulseConsistencyNumber",   DystempDescription.class), new Interval(10));
			config.setWeight(attribute, sim.PulseConsistency.Weight);
		}	
	
		attribute = new Attribute("gender",DystempDescription.class);
		function = localSimilFactory(sim.Gender.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Gender.Weight);
		
		attribute = new Attribute("job",DystempDescription.class);
		function = localSimilFactory(sim.Job.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Job.Weight);

		attribute = new Attribute("tongueColor",DystempDescription.class);
		function = localSimilFactory(sim.TongueColor.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.TongueColor.Weight);

		attribute = new Attribute("faceColor",DystempDescription.class);
		function = localSimilFactory(sim.FaceColor.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.FaceColor.Weight);

		attribute = new Attribute("thirstinessReliefType",DystempDescription.class);
		function = localSimilFactory(sim.ThirstinessReliefType.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.ThirstinessReliefType.Weight);

		attribute = new Attribute("takhazkhoz",DystempDescription.class);
		function = localSimilFactory(sim.Takhazkhoz.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Takhazkhoz.Weight);

		attribute = new Attribute("stoolType",DystempDescription.class);
		function = localSimilFactory(sim.StoolType.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.StoolType.Weight);

		attribute = new Attribute("gastralgiaTime",DystempDescription.class);
		function = localSimilFactory(sim.GastralgiaTime.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.GastralgiaTime.Weight);

		attribute = new Attribute("salivationTime",DystempDescription.class);
		function = localSimilFactory(sim.SalivationTime.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.SalivationTime.Weight);

		attribute = new Attribute("mouthTaste",DystempDescription.class);
		function = localSimilFactory(sim.MouthTaste.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.MouthTaste.Weight);

		attribute = new Attribute("qasayanTime",DystempDescription.class);
		function = localSimilFactory(sim.QasayanTime.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.QasayanTime.Weight);

		attribute = new Attribute("heavinessTime",DystempDescription.class);
		function = localSimilFactory(sim.HeavinessTime.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.HeavinessTime.Weight);

		attribute = new Attribute("custodyDisposal",DystempDescription.class);
		function = localSimilFactory(sim.CustodyDisposal.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.CustodyDisposal.Weight);

		attribute = new Attribute("sleepingWakefulness",DystempDescription.class);
		function = localSimilFactory(sim.SleepingWakefulness.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.SleepingWakefulness.Weight);

		attribute = new Attribute("climate",DystempDescription.class);
		function = localSimilFactory(sim.Climate.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Climate.Weight);

		attribute = new Attribute("physicalActivity",DystempDescription.class);
		function = localSimilFactory(sim.PhysicalActivity.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.PhysicalActivity.Weight);

		attribute = new Attribute("food",DystempDescription.class);
		function = localSimilFactory(sim.Food.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Food.Weight);

		attribute = new Attribute("mentalHealth",DystempDescription.class);
		function = localSimilFactory(sim.MentalHealth.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.MentalHealth.Weight);

		attribute = new Attribute("tendencyTo",DystempDescription.class);
		function = localSimilFactory(sim.TendencyTo.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.TendencyTo.Weight);

		attribute = new Attribute("benefitFrom",DystempDescription.class);
		function = localSimilFactory(sim.BenefitFrom.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.BenefitFrom.Weight);

		attribute = new Attribute("disadvantageOf",DystempDescription.class);
		function = localSimilFactory(sim.DisadvantageOf.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.DisadvantageOf.Weight);

		attribute = new Attribute("hatredOf",DystempDescription.class);
		function = localSimilFactory(sim.HatredOf.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.HatredOf.Weight);

		attribute = new Attribute("bodyState",DystempDescription.class);
		function = localSimilFactory(sim.BodyState.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.BodyState.Weight);

		attribute = new Attribute("limpness",DystempDescription.class);
		function = localSimilFactory(sim.Limpness.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Limpness.Weight);

		attribute = new Attribute("saliva",DystempDescription.class);
		function = localSimilFactory(sim.Saliva.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Saliva.Weight);

		attribute = new Attribute("salivation",DystempDescription.class);
		function = localSimilFactory(sim.Salivation.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Salivation.Weight);

		attribute = new Attribute("tongueWettish",DystempDescription.class);
		function = localSimilFactory(sim.TongueWettish.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.TongueWettish.Weight);

		attribute = new Attribute("qasayan",DystempDescription.class);
		function = localSimilFactory(sim.Qasayan.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Qasayan.Weight);

		attribute = new Attribute("stomachHeaviness",DystempDescription.class);
		function = localSimilFactory(sim.StomachHeaviness.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.StomachHeaviness.Weight);

		attribute = new Attribute("gastricEructation",DystempDescription.class);
		function = localSimilFactory(sim.GastricEructation.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.GastricEructation.Weight);

		attribute = new Attribute("flatulence",DystempDescription.class);
		function = localSimilFactory(sim.Flatulence.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Flatulence.Weight);

		attribute = new Attribute("gastralgia",DystempDescription.class);
		function = localSimilFactory(sim.Gastralgia.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Gastralgia.Weight);

		attribute = new Attribute("foodPutrefactionFreq",DystempDescription.class);
		function = localSimilFactory(sim.FoodPutrefactionFreq.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.FoodPutrefactionFreq.Weight);

		attribute = new Attribute("heartburn",DystempDescription.class);
		function = localSimilFactory(sim.Heartburn.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Heartburn.Weight);

		attribute = new Attribute("hiccup",DystempDescription.class);
		function = localSimilFactory(sim.Hiccup.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Hiccup.Weight);

		attribute = new Attribute("acerbate",DystempDescription.class);
		function = localSimilFactory(sim.Acerbate.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Acerbate.Weight);

		attribute = new Attribute("eatingMuchTiny",DystempDescription.class);
		function = localSimilFactory(sim.EatingMuchTiny.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.EatingMuchTiny.Weight);

		attribute = new Attribute("stoolConsistency",DystempDescription.class);
		function = localSimilFactory(sim.StoolConsistency.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.StoolConsistency.Weight);

		attribute = new Attribute("defecationPeriod",DystempDescription.class);
		function = localSimilFactory(sim.DefecationPeriod.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.DefecationPeriod.Weight);
		
		attribute = new Attribute("disease",DystempDescription.class);
		function = localSimilFactory(sim.Disease.Function); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, sim.Disease.Weight);
		
		return config;
	}

}
