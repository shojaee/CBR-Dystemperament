package persianmedicine.cbrdystemp;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import datatypes.*;
import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;
import functions.CBRCaseFunctions;
import functions.SimilarityConfig;
import gui.Fuzzification;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.OntologyConnector;
import jcolibri.datatypes.Instance;
import jcolibri.datatypes.Text;
import jcolibri.util.FileIO;
import jcolibri.cbrcore.CBRCase;
import persianmedicine.cbrdystemp.DystempDescription.OL1;
import persianmedicine.cbrdystemp.DystempDescription.OL2;
import persianmedicine.cbrdystemp.DystempDescription.OL3;
import persianmedicine.cbrdystemp.gui.*;

public class CBRDystemp implements StandardCBRApplication{

	Connector _connector;
	CBRCaseBase _caseBase;	
	WorkWithCBROntology cbronto;
	
	public SimilarityDialog2 similarityDialog2;
	ResultDialog resultDialog;
//	AutoAdaptationDialog autoAdaptDialog;
	Revise revisionDialog;
	RetainDialog retainDialog;	
	
	private static CBRDystemp _instance = null;
	public  static CBRDystemp getInstance()
	{
		if(_instance == null)
		   _instance = new CBRDystemp();
		return _instance;
	}	

	private CBRDystemp() {
		// TODO Auto-generated constructor stub
	}
	

	
	public void InitDescriptionOfCases() {
		try {
			Fuzzification f = new Fuzzification();
			f.InitFuzzyDataTypes();
			DystempDescription desc;
			java.util.Collection<CBRCase> cases = _caseBase.getCases();
			for(CBRCase c: cases) 
			{
				desc= (DystempDescription) c.getDescription();
				CBRCaseFunctions.InitDescriptionOfCase(desc, f);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	public void configure() throws ExecutionException {
	//	try {
			cbronto = new WorkWithCBROntology();
		
			_connector = new OntologyConnector();
			
			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile("persianmedicine/cbrdystemp/ontologyconfig.xml"));
			_caseBase  = new LinealCaseBase();

		//	_caseBase.init(_connector);
		//	_connector.close();
		

			
			// Create the dialogs
			similarityDialog2 = new SimilarityDialog2(main);
			resultDialog     = new ResultDialog(main);
	//		autoAdaptDialog  = new AutoAdaptationDialog(main);
			revisionDialog   = new Revise(main);
			retainDialog     = new RetainDialog(main);
		//	
	//	} catch (Exception e) {
	//		throw new ExecutionException(e);
	//	}
	}
	
	public void CreateCBRCase(CBRCase newcase,DystempDescription desc,DystempSolution sol) {
		newcase.setDescription(desc);
		newcase.setSolution(sol);
	}

	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		// Obtain configuration for KNN
		//similarityDialog.setVisible(true);
		SimilarityConfig sim = CBRCaseFunctions.ReadSimilarityFromFile();
		NNConfig simConfig = CBRCaseFunctions.getSimilarityConfig(sim);
		
		// Execute NN
		Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
		
		// Select k cases
		Collection<CBRCase> selectedcases = SelectCases.selectTopK(eval, sim.K);
		
	/*	System.out.println("Query Description:");
		System.out.println(query.getDescription());
		System.out.println();
		
		System.out.println("Retrieved cases:");
		for(CBRCase nse: selectedcases)
			System.out.println(nse);
		*/
		// Show result
		resultDialog.showCases(eval, selectedcases);
		resultDialog.setVisible(true);
		
		RetrievalResult rr =resultDialog.cases.get(resultDialog.currentCase);
		CBRCase newcase = new CBRCase();
		DystempDescription desc = (DystempDescription)(query.getDescription());
	    DystempSolution sol =  (DystempSolution)rr.get_case().getSolution();
		
		CreateCBRCase(newcase, desc, sol );
		
		// Show adaptation dialog
		/*	autoAdaptDialog.setVisible(true);
		
		// Adapt depending on user selection
		if(autoAdaptDialog.adapt_Duration_Price())
		{
			// Compute a direct proportion between the "Duration" and "Price" attributes.
			NumericDirectProportionMethod.directProportion(	new Attribute("Duration",TravelDescription.class), 
				 											new Attribute("price",TravelSolution.class), 
				 											query, selectedcases);
		}
		
		if(autoAdaptDialog.adapt_NumberOfPersons_Price())
		{
			// Compute a direct proportion between the "Duration" and "Price" attributes.
			NumericDirectProportionMethod.directProportion(	new Attribute("NumberOfPersons",TravelDescription.class), 
				 											new Attribute("price",TravelSolution.class), 
				 											query, selectedcases);
		}
		*/
		// Revise
		revisionDialog.showCase(newcase);
		revisionDialog.setVisible(true);
		
		// Retain
		retainDialog.showCase(revisionDialog._case);
		retainDialog.setVisible(true);
		Collection<CBRCase> casesToRetain = retainDialog.getCasestoRetain();
		_caseBase.learnCases(casesToRetain);		
		
		
	}

	@Override
	public void postCycle() throws ExecutionException {
		this._caseBase.close();
	}

	
	
	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		_caseBase.init(_connector);	
		InitDescriptionOfCases();
		return _caseBase;	
	}
	public void InitComboBoxes(QueryDialog qf) {
		InitComboBox("Sleeping_Wakefulness", qf.cbSleepingWakefulness);	
		InitComboBox("Custody_Disposal", qf.cbCustodyDisposal);	
		InitComboBox("Food", qf.cbFood);	
		InitComboBox("Mental_Health", qf.cbMentalHealth);	
		InitComboBox("Climate", qf.cbClimate);	
		InitComboBox("Physical_Activity", qf.cbPhysicalActivity);	
		InitComboBoxDBTH(qf.cbBenefitFrom);
		InitComboBoxDBTH(qf.cbDisadvantageOf);
		InitComboBoxDBTH(qf.cbHatredOf);
		InitComboBoxDBTH(qf.cbTendencyTo);	
		InitComboBoxOL2(qf.cbQasayan);
		InitComboBoxOL2(qf.cbLimpness);
		InitComboBoxOL2(qf.cbSaliva);
		InitComboBoxOL2(qf.cbSalivation);
		InitComboBoxOL2(qf.cbTongueWettish);
		InitComboBoxOL2(qf.cbStomachHeaviness);
		InitComboBoxOL2(qf.cbGastricEructation);
		InitComboBoxOL2(qf.cbFlatulence);
		InitComboBoxOL2(qf.cbGastralgia);
		InitComboBoxOL2(qf.cbFoodPutrefactionFreq);
		InitComboBoxOL2(qf.cbHeartburn);
		InitComboBoxOL2(qf.cbHiccup);
		InitComboBoxOL2(qf.cbAcerbate);
		InitComboBoxOL2(qf.cbEatingMuchTiny);
		InitComboBoxMinusFirst("Tongue_Color", qf.cbTongueColor, "رنگ_زبان_");
		InitComboBoxOL1(qf.cbDefecationPeriod);
		InitComboBoxOL3(qf.cbStoolConsistency);
		
	}
	private void InitComboBoxDBTH(JComboBox cb) {
		cb.removeAllItems();
		cb.addItem(" ");
		cb.addItem("گرميجات");
		cb.addItem("سرديجات");
		cb.addItem("رطوبات");
		cb.addItem("اغذيه_خشک");
		cb.addItem("اغذيه_چرب");
		cb.addItem("غذاي_کم");
		cb.addItem("طعم_تند");
		cb.addItem("طعم_شيرين");
		cb.addItem("طعم_ترش");
		cb.addItem("طعم_شور");
		cb.addItem("طعم_ملس");
		cb.addItem("طعم_تلخ");
		cb.addItem("گرما");
		cb.addItem("سرما");
		cb.addItem("رطوبت");
		cb.addItem("خشکي");		
	}

	public void InitComboBox(String className,JComboBox cb) {
		cb.removeAllItems();
		cb.addItem(" ");
		String[] items = cbronto.ListInstancesOfClass(className);
		for(String s : items) {
			cb.addItem(s);
		}		
	}
	
	public void InitComboBoxMinusFirst(String className,JComboBox cb,String first) {
		int i=first.length();
		cb.removeAllItems();
		cb.addItem(" ");
		String[] items = cbronto.ListInstancesOfClass(className);
		for(String s : items) {
			cb.addItem(s.substring(i));
		}		
	}
	
	public void InitComboBoxOL1(JComboBox cb) {
		cb.removeAllItems();
		cb.addItem(" ");
		cb.addItem("روزي_چند_بار");
		cb.addItem("روزانه");
		cb.addItem("هر_چند_روز_يکبار");
		cb.addItem("هفتگي");
	}	
	
	public void InitComboBoxOL2(JComboBox cb) {
		cb.removeAllItems();
		cb.addItem(" ");
		cb.addItem("خير");
		cb.addItem("کم");
		cb.addItem("متوسط");
		cb.addItem("زياد");
		cb.addItem("خيلي_زياد");
	}
		
	public void InitComboBoxOL3(JComboBox cb) {
		cb.removeAllItems();
		cb.addItem(" ");
		cb.addItem("آبکي");
		cb.addItem("نرم");
		cb.addItem("نرمال");
		cb.addItem("خشک");
		cb.addItem("خيلي_خشک");
	}
	
	static JFrame main;
	void showMainFrame()
	{
		main = new JFrame("Travel Recommender");
		main.setResizable(false);
		main.setUndecorated(true);
		JLabel label = new JLabel(new ImageIcon(jcolibri.util.FileIO.findFile("/persianmedicine/cbrdystemp/logo.jpg")));
		main.getContentPane().add(label);
		main.pack();
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		main.setBounds((screenSize.width - main.getWidth()) / 2,
			(screenSize.height - main.getHeight()) / 2, 
			main.getWidth(),
			main.getHeight());
		main.setVisible(true);
	}	
	
	public static void main(String[] args) {
		CBRDystemp recommender = getInstance();	
		recommender.showMainFrame();
		try {
				recommender.configure();
				recommender.preCycle();
	
				QueryDialog qf = new QueryDialog(main);
				recommender.InitComboBoxes(qf);
				qf.recommender = recommender;
				boolean cont = true;
				while(cont)
				{
					qf.setVisible(true);
					CBRQuery query = qf.getQuery();
				
					recommender.cycle(query);
					int ans = javax.swing.JOptionPane.showConfirmDialog(null, "آیا پرس و جوی دیگری دارید؟", "پایان اجرای پرس و جو", javax.swing.JOptionPane.YES_NO_OPTION);
					cont = (ans == javax.swing.JOptionPane.YES_OPTION);
				}
				recommender.postCycle();			
		} 
		catch (Exception e) {
				javax.swing.JOptionPane.showMessageDialog(null, e.getMessage());
		}
		System.exit(0);
	}	

}
