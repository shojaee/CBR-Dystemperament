package persianmedicine.cbrdystemp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import persianmedicine.cbrdystemp.CBRDystemp;
import persianmedicine.cbrdystemp.DystempDescription;
import persianmedicine.cbrdystemp.DystempDescription.OL1;
import persianmedicine.cbrdystemp.DystempDescription.OL2;
import persianmedicine.cbrdystemp.DystempDescription.OL3;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

import com.sun.javafx.tk.ScreenConfigurationAccessor;

import datatypes.*;
import functions.FuzzyAverage;
import jcolibri.cbrcore.Attribute;
import jcolibri.datatypes.Text;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumCyclicDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Threshold;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntCosine;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeep;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeepBasic;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDetail;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.CosineCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.DiceCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.JaccardCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.LuceneTextSimilarity;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.OverlapCoefficient;
import jcolibri.test.test5.Region;
import javax.swing.SpinnerModel;

public class SimilarityDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	SimilConfigPanel scAge,scBMI,scJob,scGender,scSleepingWakefulness,scCustodyDisposal,scFood,scPhysicalActivity,scMentalHealth,scClimate
						,scQasayan,scQasayanTime,scStomachHeaviness,scHeavinessTime,scGastricEructation,scFlatulence
						,scGastralgia,scGastralgiaTime,scFaceColor,scLimpness,scBodyState,scSaliva,scSalivation
						,scSalivationTime,scMouthTaste,scTongueColor,scTongueWettish,scFoodPutrefactionFreq,scHeartburn
						,scHiccup,scAcerbate,scThirstinessReliefType,scEatingMuchTiny,scTendencyTo,scBenefitFrom
						,scDisadvantageOf,scHatredOf,scStoolConsistency,scDefecationPeriod,scStoolType,scTakhazkhoz
						,scDigestionPower,scPulseRate,scPulseConsistency,scPulseSpeed,scPulsePower,scThirstinessLevel,scAppetiteLevel
						,scFoodTransitionSpeed,scDisease;

	SpinnerNumberModel k;	
	
	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public SimilarityDialog(JFrame parent) {
		
		super(parent,true);		
		getContentPane().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setBounds(100, 100, 1015, 745);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		

		Vector<String> stringfunctions = new Vector<String>();
		stringfunctions.add("Equal");
		
		Vector<String> numberfunctions = new Vector<String>();
		numberfunctions.add("Threshold");
		numberfunctions.add("Interval");
		numberfunctions.add("Equal");
		
		Vector<String> enumfunctions = new Vector<String>();
		enumfunctions.add("EnumCyclicDistance");
		enumfunctions.add("EnumDistance");
		enumfunctions.add("Equal");
		
		Vector<String> ontofunctions = new Vector<String>();
		ontofunctions.add("OntCosine");
		ontofunctions.add("OntDeep");
		ontofunctions.add("OntDeepBasic");
		ontofunctions.add("OntDetail");
		ontofunctions.add("Equal");		
		
		Vector<String> textfunctions = new Vector<String>();
		textfunctions.add("CosineCoefficient");
		textfunctions.add("DiceCoefficient");
		textfunctions.add("JaccardCoefficient");
	//	textfunctions.add("LuceneTextSimilarity");
		textfunctions.add("OverlapCoefficient");		

		Vector<String> agefunctions = new Vector<String>();
		agefunctions.add("FuzzyAge");
		agefunctions.add("Numerical");
		
		Vector<String> bmifunctions = new Vector<String>();
		bmifunctions.add("FuzzyBMI");
		bmifunctions.add("Numerical");
		
		Vector<String> valuefunctions = new Vector<String>();
		valuefunctions.add("FuzzyValue");
		valuefunctions.add("Numerical");

		contentPanel.setLayout(null);
		
		
		JPanel pnlMain_1 = new JPanel();
		pnlMain_1.setBorder(new LineBorder(Color.GRAY));
		pnlMain_1.setBounds(666, 31, 320, 529);
		contentPanel.add(pnlMain_1);
		pnlMain_1.setLayout(new GridLayout(20, 1, 0, 5));		
		
		JPanel pnlMain_2 = new JPanel();
		pnlMain_2.setBorder(new LineBorder(Color.GRAY, 1, true));
		pnlMain_2.setBounds(342, 31, 320, 529);
		contentPanel.add(pnlMain_2);
		pnlMain_2.setLayout(new GridLayout(20, 1, 0, 5));		
		
		JPanel pnlMain_3 = new JPanel();
		pnlMain_3.setBorder(new LineBorder(Color.GRAY));
		pnlMain_3.setBounds(12, 31, 320, 260);
		contentPanel.add(pnlMain_3);
		pnlMain_3.setLayout(new GridLayout(10, 1, 0, 5));		

		pnlMain_1.add(scAge = new SimilConfigPanel(agefunctions,"سن"));	
		pnlMain_1.add(scBMI = new SimilConfigPanel(bmifunctions,"شاخص توده بدنی"));
		pnlMain_1.add(scJob = new SimilConfigPanel(stringfunctions,"شغل"));
		pnlMain_1.add(scGender = new SimilConfigPanel(stringfunctions,"جنس"));
		pnlMain_1.add(scFaceColor = new SimilConfigPanel(stringfunctions,"رنگ صورت"));
		pnlMain_1.add(scLimpness = new SimilConfigPanel(enumfunctions,"ترهل بدن"));
		pnlMain_1.add(scBodyState = new SimilConfigPanel(textfunctions,"وضعیت بدن"));
		pnlMain_1.add(scSaliva = new SimilConfigPanel(enumfunctions,"آب دهان"));
		pnlMain_1.add(scSalivation = new SimilConfigPanel(enumfunctions,"اهراق ریق"));
		pnlMain_1.add(scSalivationTime = new SimilConfigPanel(stringfunctions,"زمان اهراق ریق"));
		pnlMain_1.add(scMouthTaste = new SimilConfigPanel(stringfunctions,"طعم دهان"));
		pnlMain_1.add(scTongueColor = new SimilConfigPanel(stringfunctions,"رنگ زبان"));
		pnlMain_1.add(scTongueWettish = new SimilConfigPanel(enumfunctions,"رطوبت زبان"));
		pnlMain_1.add(scSleepingWakefulness = new SimilConfigPanel(textfunctions,"خواب و بیداری"));
		pnlMain_1.add(scCustodyDisposal = new SimilConfigPanel(textfunctions,"احتباس و استفراغ"));
		pnlMain_1.add(scFood = new SimilConfigPanel(textfunctions,"غذا و نوشیدنی"));
		pnlMain_1.add(scClimate = new SimilConfigPanel(textfunctions,"هوا و سکونت"));
		pnlMain_1.add(scPhysicalActivity = new SimilConfigPanel(textfunctions,"حرکت و سکون"));
		pnlMain_1.add(scMentalHealth = new SimilConfigPanel(textfunctions,"اعراض نفسانی"));
		pnlMain_1.add(scTendencyTo = new SimilConfigPanel(textfunctions,"تمایل به"));

		pnlMain_2.add(scBenefitFrom = new SimilConfigPanel(textfunctions,"انتفاع از"));
		pnlMain_2.add(scDisadvantageOf = new SimilConfigPanel(textfunctions,"تضرر از"));				
		pnlMain_2.add(scHatredOf = new SimilConfigPanel(textfunctions,"تنفر از"));
		pnlMain_2.add(scStoolConsistency = new SimilConfigPanel(enumfunctions,"قوام مدفوع"));
		pnlMain_2.add(scDefecationPeriod = new SimilConfigPanel(enumfunctions,"اجابت مزاج"));
		pnlMain_2.add(scStoolType = new SimilConfigPanel(stringfunctions,"نوع مدفوع"));
		pnlMain_2.add(scQasayan = new SimilConfigPanel(enumfunctions,"غثیان"));
		pnlMain_2.add(scQasayanTime = new SimilConfigPanel(stringfunctions,"زمان غثیان"));
		pnlMain_2.add(scStomachHeaviness = new SimilConfigPanel(enumfunctions,"ثقل معده"));
		pnlMain_2.add(scHeavinessTime = new SimilConfigPanel(stringfunctions,"زمان ثقل معده"));
		pnlMain_2.add(scGastricEructation = new SimilConfigPanel(enumfunctions,"جشا"));
		pnlMain_2.add(scFlatulence = new SimilConfigPanel(enumfunctions,"نفخ"));
		pnlMain_2.add(scGastralgia = new SimilConfigPanel(enumfunctions,"وجع معده"));
		pnlMain_2.add(scGastralgiaTime = new SimilConfigPanel(stringfunctions,"زمان وجع معده"));
		pnlMain_2.add(scFoodPutrefactionFreq = new SimilConfigPanel(enumfunctions,"دفعات نموست غذا"));
		pnlMain_2.add(scHeartburn = new SimilConfigPanel(enumfunctions,"حرقت معده"));
		pnlMain_2.add(scHiccup = new SimilConfigPanel(enumfunctions,"فواق"));
		pnlMain_2.add(scAcerbate = new SimilConfigPanel(enumfunctions,"جشاء حامض"));
		pnlMain_2.add(scTakhazkhoz = new SimilConfigPanel(stringfunctions,"تخضخض"));
		pnlMain_2.add(scDigestionPower = new SimilConfigPanel(valuefunctions,"قدرت هضم"));
		
		pnlMain_3.add(scFoodTransitionSpeed = new SimilConfigPanel(valuefunctions,"سرعت انحدار"));
		pnlMain_3.add(scThirstinessLevel = new SimilConfigPanel(valuefunctions,"میزان عطش"));
		pnlMain_3.add(scAppetiteLevel = new SimilConfigPanel(valuefunctions,"میزان اشتها"));
		pnlMain_3.add(scThirstinessReliefType = new SimilConfigPanel(stringfunctions,"نحوه تسکین عطش"));
		pnlMain_3.add(scEatingMuchTiny = new SimilConfigPanel(enumfunctions,"ریزه خواری"));
		pnlMain_3.add(scPulseRate = new SimilConfigPanel(valuefunctions,"تواتر نبض"));
		pnlMain_3.add(scPulseConsistency = new SimilConfigPanel(valuefunctions,"قوام نبض"));
		pnlMain_3.add(scPulsePower = new SimilConfigPanel(valuefunctions,"قدرت نبض"));
		pnlMain_3.add(scPulseSpeed = new SimilConfigPanel(valuefunctions,"سرعت نبض"));
		pnlMain_3.add(scDisease = new SimilConfigPanel(ontofunctions,"بیماری مشارک"));
		

		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_1_1.setBounds(12, 11, 320, 20);
		contentPanel.add(panel_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("وزن");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1_1.setBounds(5, 0, 75, 20);
		panel_1_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("تابع مشابهت");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_3_1.setBounds(80, 0, 135, 20);
		panel_1_1.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("ویژگی");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(225, 0, 90, 20);
		panel_1_1.add(lblNewLabel_2_1);
		
		JPanel pnlMain_3_1 = new JPanel();
		pnlMain_3_1.setBorder(new LineBorder(Color.GRAY));
		pnlMain_3_1.setBounds(12, 371, 320, 75);
		contentPanel.add(pnlMain_3_1);
		pnlMain_3_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("K");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(116, 45, 29, 14);
		pnlMain_3_1.add(lblNewLabel_3);
		
		JSpinner spinner = new JSpinner(k = new SpinnerNumberModel(3,1,100,1));
		spinner.setBounds(144, 42, 51, 20);
		pnlMain_3_1.add(spinner);
		
		JLabel lblNewLabel_4 = new JLabel("تعداد نزدیک ترین همسایه");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(88, 11, 135, 14);
		pnlMain_3_1.add(lblNewLabel_4);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_1_1_1.setBounds(342, 11, 320, 20);
		contentPanel.add(panel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("وزن");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1_1_1.setBounds(5, 0, 75, 20);
		panel_1_1_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("تابع مشابهت");
		lblNewLabel_1_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_3_1_1.setBounds(80, 0, 135, 20);
		panel_1_1_1.add(lblNewLabel_1_3_1_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("ویژگی");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1.setBounds(225, 0, 90, 20);
		panel_1_1_1.add(lblNewLabel_2_1_1);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		panel_1_1_2.setBounds(666, 11, 320, 20);
		contentPanel.add(panel_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("وزن");
		lblNewLabel_1_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1_1_1_2.setBounds(5, 0, 75, 20);
		panel_1_1_2.add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblNewLabel_1_3_1_2 = new JLabel("تابع مشابهت");
		lblNewLabel_1_3_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_3_1_2.setBounds(80, 0, 135, 20);
		panel_1_1_2.add(lblNewLabel_1_3_1_2);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("ویژگی");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_2.setBounds(225, 0, 90, 20);
		panel_1_1_2.add(lblNewLabel_2_1_2);
		


		
	

	/*	JSpinner spinner = new JSpinner(k = new SpinnerNumberModel(3,1,100,1));
		spinner.setBounds(2, 42, 140, 20);
		pnlMain.add(spinner);
		*/
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("  برگشت  ");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setSimilarity();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		{
			JPanel topPane = new JPanel();
			topPane.setBorder(new LineBorder(new Color(0, 0, 0)));
			getContentPane().add(topPane, BorderLayout.NORTH);
			topPane.setLayout(new BorderLayout(0, 0));
			
			JLabel label_1 = new JLabel("");
			label_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\banner.jpg"));
			topPane.add(label_1);
		}
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - this.getWidth()) / 2,
			(screenSize.height - this.getHeight()) / 2, 
			getWidth(),
			getHeight());	
	}
	
	public int getK()
	{
		return k.getNumber().intValue();
	}	
	
	public NNConfig getSimilarityConfig()
	{
		NNConfig config = new NNConfig();
		Attribute attribute;
		SimilConfigPanel similConfig;
		LocalSimilarityFunction function;

		similConfig = scAge;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("ageFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("AgeGrowth",   FuzzyAge.class), new Interval(1));
			config.addMapping(new Attribute("AgeYoung",     FuzzyAge.class), new Interval(1));
			config.addMapping(new Attribute("AgeMiddle",  FuzzyAge.class), new Interval(1));
			config.addMapping(new Attribute("AgeOld", FuzzyAge.class), new Interval(1));			
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("ageNumber",   DystempDescription.class), new Interval(100));
		}
		
		similConfig = scBMI;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("bmiFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("BMIUnderWeight",   FuzzyBMI.class), new Interval(1));
			config.addMapping(new Attribute("BMINormal",     FuzzyBMI.class), new Interval(1));
			config.addMapping(new Attribute("BMIOverWeight",  FuzzyBMI.class), new Interval(1));
			config.addMapping(new Attribute("Obese", FuzzyBMI.class), new Interval(1));			
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("bmiNumber",   DystempDescription.class), new Interval(50));
		}	
			
		similConfig = scDigestionPower;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("digestionPowerFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("DigestionPowerLowValue",   DigestionPowerFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("DigestionPowerMiddleValue",     DigestionPowerFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("DigestionPowerHighValue",  DigestionPowerFuzzyValue.class), new Interval(1));		
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("digestionPowerNumber",   DystempDescription.class), new Interval(10));
		}	


		similConfig = scFoodTransitionSpeed;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("foodTransitionSpeedFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("FoodTransitionSpeedLowValue",   FoodTransitionSpeedFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("FoodTransitionSpeedMiddleValue",     FoodTransitionSpeedFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("FoodTransitionSpeedHighValue",  FoodTransitionSpeedFuzzyValue.class), new Interval(1));		
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("foodTransitionSpeedNumber",   DystempDescription.class), new Interval(10));
		}	
		
		similConfig = scAppetiteLevel;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("appetiteLevelFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("AppetiteLevelLowValue",   AppetiteLevelFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("AppetiteLevelMiddleValue",     AppetiteLevelFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("AppetiteLevelHighValue",  AppetiteLevelFuzzyValue.class), new Interval(1));		
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("appetiteLevelNumber",   DystempDescription.class), new Interval(10));
		}	
	
		similConfig = scThirstinessLevel;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("thirstinessLevelFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("ThirstinessLevelLowValue",   ThirstinessLevelFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("ThirstinessLevelMiddleValue",     ThirstinessLevelFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("ThirstinessLevelHighValue",  ThirstinessLevelFuzzyValue.class), new Interval(1));		
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("thirstinessLevelNumber",   DystempDescription.class), new Interval(10));
		}	
	
		similConfig = scPulseSpeed;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("pulseSpeedFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("PulseSpeedLowValue",   PulseSpeedFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("PulseSpeedMiddleValue",     PulseSpeedFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("PulseSpeedHighValue",  PulseSpeedFuzzyValue.class), new Interval(1));		
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("pulseSpeedNumber",   DystempDescription.class), new Interval(10));
		}	
	
		
		similConfig = scPulsePower;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("pulsePowerFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("PulsePowerLowValue",   PulsePowerFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("PulsePowerMiddleValue",     PulsePowerFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("PulsePowerHighValue",  PulsePowerFuzzyValue.class), new Interval(1));		
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("pulsePowerNumber",   DystempDescription.class), new Interval(10));
		}	
	
		
		similConfig = scPulseRate;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("pulseRateFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("PulseRateLowValue",   PulseRateFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("PulseRateMiddleValue",     PulseRateFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("PulseRateHighValue",  PulseRateFuzzyValue.class), new Interval(1));		
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("pulseRateNumber",   DystempDescription.class), new Interval(10));
		}	
	
		
		similConfig = scPulseConsistency;
		if (similConfig.getSimilFuntion().indexOf("Fuzzy")>=0) {
			config.addMapping(new Attribute("pulseConsistencyFuzzy",   DystempDescription.class), new FuzzyAverage());
			config.addMapping(new Attribute("PulseConsistencyLowValue",   PulseConsistencyFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("PulseConsistencyMiddleValue",     PulseConsistencyFuzzyValue.class), new Interval(1));
			config.addMapping(new Attribute("PulseConsistencyHighValue",  PulseConsistencyFuzzyValue.class), new Interval(1));		
		}
		else if (similConfig.getSimilFuntion().indexOf("Numerical")>=0) {
			config.addMapping(new Attribute("pulseConsistencyNumber",   DystempDescription.class), new Interval(10));
		}	
	
		similConfig = scGender;
		attribute = new Attribute("gender",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		similConfig = scJob;
		attribute = new Attribute("job",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scTongueColor;
		attribute = new Attribute("tongueColor",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scFaceColor;
		attribute = new Attribute("faceColor",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scThirstinessReliefType;
		attribute = new Attribute("thirstinessReliefType",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scTakhazkhoz;
		attribute = new Attribute("takhazkhoz",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scStoolType;
		attribute = new Attribute("stoolType",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scGastralgiaTime;
		attribute = new Attribute("gastralgiaTime",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scSalivationTime;
		attribute = new Attribute("salivationTime",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scMouthTaste;
		attribute = new Attribute("mouthTaste",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scQasayanTime;
		attribute = new Attribute("qasayanTime",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scHeavinessTime;
		attribute = new Attribute("heavinessTime",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scCustodyDisposal;
		attribute = new Attribute("custodyDisposal",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scSleepingWakefulness;
		attribute = new Attribute("sleepingWakefulness",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scClimate;
		attribute = new Attribute("climate",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scPhysicalActivity;
		attribute = new Attribute("physicalActivity",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scFood;
		attribute = new Attribute("food",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scMentalHealth;
		attribute = new Attribute("mentalHealth",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scTendencyTo;
		attribute = new Attribute("tendencyTo",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scBenefitFrom;
		attribute = new Attribute("benefitFrom",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scDisadvantageOf;
		attribute = new Attribute("disadvantageOf",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scHatredOf;
		attribute = new Attribute("hatredOf",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scBodyState;
		attribute = new Attribute("bodyState",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scLimpness;
		attribute = new Attribute("limpness",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scSaliva;
		attribute = new Attribute("saliva",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scSalivation;
		attribute = new Attribute("salivation",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scTongueWettish;
		attribute = new Attribute("tongueWettish",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scQasayan;
		attribute = new Attribute("qasayan",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scStomachHeaviness;
		attribute = new Attribute("stomachHeaviness",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());


		similConfig = scGastricEructation;
		attribute = new Attribute("gastricEructation",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());


		similConfig = scFlatulence;
		attribute = new Attribute("flatulence",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());


		similConfig = scGastralgia;
		attribute = new Attribute("gastralgia",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());


		similConfig = scFoodPutrefactionFreq;
		attribute = new Attribute("foodPutrefactionFreq",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scHeartburn;
		attribute = new Attribute("heartburn",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scHiccup;
		attribute = new Attribute("hiccup",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scAcerbate;
		attribute = new Attribute("acerbate",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());


		similConfig = scEatingMuchTiny;
		attribute = new Attribute("eatingMuchTiny",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());


		similConfig = scStoolConsistency;
		attribute = new Attribute("stoolConsistency",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());

		similConfig = scDefecationPeriod;
		attribute = new Attribute("defecationPeriod",DystempDescription.class);
		function = localSimilFactory(similConfig.getSimilFuntion()); 
		config.addMapping(attribute, function);
		config.setWeight(attribute, similConfig.getWeight());
		
		return config;
	}
		
	private LocalSimilarityFunction localSimilFactory(String name)
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
	
	void setSimilarity()
	{
		this.setVisible(false);
	}
		
	
	private class SimilConfigPanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		
		Vector<String> functions;
		public JComboBox functionCombo;
		SpinnerNumberModel param;
		JSpinner paramSpinner;
		JSlider weight;
		
		public SimilConfigPanel(Vector<String> functions,String label)
		{
			this.functions = new Vector<String>(functions);
			
//			this.setLayout(new GridLayout(1, 3, 10, 10));
			
			this.setLayout(null);
			this.setSize(320, 20);
				
			functionCombo = new JComboBox(functions);
			functionCombo.setFont(new Font("verdana", Font.PLAIN, 9));
			functionCombo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			functionCombo.setBounds(80, 0, 135, 20);
			this.add(functionCombo);			

			
			JLabel lbl = new JLabel(label);
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lbl.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl.setBounds(225, 0, 90, 20);
			this.add(lbl);

			
			weight = new JSlider(0,10,10);
			weight.setPaintLabels(false);
			weight.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			weight.setBounds(5, 0, 75, 20);
			this.add(weight);

					
			
		/*	paramSpinner = new JSpinner(param = new SpinnerNumberModel());
			paramSpinner.setBounds(0, 0, 35, 20);
			paramSpinner.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			this.add(paramSpinner);
			
			functionCombo.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					updateParam();
				}
				
			});
			updateParam();*/
		}
		
	/*	void updateParam()
		{
			int sel = functionCombo.getSelectedIndex();
			if(sel == -1)
			{
				paramSpinner.setVisible(false);
				return;
			}
			String f = functions.elementAt(sel);
			paramSpinner.setVisible(f.endsWith("Interval") || f.endsWith("Threshold") || f.endsWith("Numerical"));
		}
		
				
		public int getParam()
		{
			return param.getNumber().intValue();
		}
		*/
		
		public double getWeight()
		{
			return ((double)weight.getValue()) /10;
		}

		
		public String getSimilFuntion()
		{
			return (String)functionCombo.getSelectedItem();
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimilarityDialog qf = new SimilarityDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}	
}
