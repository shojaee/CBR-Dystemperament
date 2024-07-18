package persianmedicine.cbrdystemp.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import net.miginfocom.swing.MigLayout;
import persianmedicine.cbrdystemp.CBRDystemp;
import persianmedicine.cbrdystemp.DystempDescription;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.border.LineBorder;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;
import functions.CBRCaseFunctions;
import gui.Fuzzification;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.datatypes.Instance;
import jcolibri.datatypes.Text;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.OntologyAccessException;
import jcolibri.extensions.textual.IE.opennlp.IETextOpenNLP;
import jcolibri.extensions.textual.IE.representation.IEText;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.Component;
import java.awt.SystemColor;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.Position;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;

public class QueryDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;	
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tAge;
	private JTextField tBMI;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JTextField tJob;
	
	JDialog ontoDialog;
	PnlSelectInstance ontoPanel;
	String selected;	
	
	JRadioButton rMale,rFemale;

	public JComboBox cbSleepingWakefulness,cbCustodyDisposal,cbFood,cbPhysicalActivity,cbMentalHealth,cbClimate
						,cbQasayan,cbQasayanTime,cbStomachHeaviness,cbHeavinessTime,cbGastricEructation,cbFlatulence
						,cbGastralgia,cbGastralgiaTime,cbFaceColor,cbLimpness,cbBodyState,cbSaliva,cbSalivation
						,cbSalivationTime,cbMouthTaste,cbTongueColor,cbTongueWettish,cbFoodPutrefactionFreq,cbHeartburn
						,cbHiccup,cbAcerbate,cbThirstinessReliefType,cbEatingMuchTiny,cbTendencyTo,cbBenefitFrom
						,cbDisadvantageOf,cbHatredOf,cbStoolConsistency,cbDefecationPeriod,cbStoolType;
	
	public JCheckBox cTakhazkhoz;
	
	public JSlider sDigestionPower,sPulseRate,sPulseConsistency,sPulseSpeed,sPulsePower,sThirstinessLevel,sAppetiteLevel,sFoodTransitionSpeed;

	private JLabel lblNewLabel_1_2_1;
	JList listPrinciple;
	DefaultListModel<String> modelPrinciple;
	DefaultListModel<String> modelPrinciple2;
	
	JList listDBTH;
	DefaultListModel<String> modelDBTH;
	DefaultListModel<String> modelDBTH2;
		
	
	JList listDiseases;
	DefaultListModel<String> modelDiseases;
	
	OntoBridge ob;
	public CBRDystemp recommender;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			QueryDialog dialog = new QueryDialog();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	public boolean isDiseaseSelected;
	
	void InitDiseaseSelector() {
		
		ob = jcolibri.util.OntoBridgeSingleton.getOntoBridge();
		
/*		ob = new OntoBridge();
		
		ob.initWithPelletReasoner();

		OntologyDocument mainOnto = new OntologyDocument("http://www.semanticweb.org/hsh/ontologies/2019/10/diseases","file:files/diseases.owl");
		ArrayList<OntologyDocument> subOntologies = new ArrayList<OntologyDocument>();

		ob.loadOntology(mainOnto, subOntologies, false);	
		*/
		
		ontoDialog = new JDialog(this, true);
		ontoDialog.setPreferredSize(new Dimension(400, 500));
		
		ontoPanel = new PnlSelectInstance(ob);
		Container main = ontoDialog.getContentPane();
		main.setLayout(new BorderLayout());
		main.add(ontoPanel, BorderLayout.CENTER);
		
		JButton select = new JButton("انتخاب بيماري");
		select.setFont(new Font("Tahoma", Font.PLAIN, 11));
		select.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ontoDialog.setVisible(false);	
				isDiseaseSelected = true;
			}
		});
		main.add(select,BorderLayout.SOUTH);
		
		ontoDialog.pack();
		
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		ontoDialog.setBounds((screenSize.width - ontoDialog.getWidth()) / 2,
			(screenSize.height - ontoDialog.getHeight()) / 2, 
			ontoDialog.getWidth(),
			ontoDialog.getHeight());		
	}
	void OpenDiseaseSelector() 
	{
		isDiseaseSelected = false;
		ontoDialog.setVisible(true);
		selected = ontoPanel.getSelectedInstance();
		if(selected!=null && isDiseaseSelected)
		{
			AddToDiseasesList(selected);
		}
	}
		
	
	void setQuery()
	{
		getQuery();
		this.setVisible(false);
	}
	
	public CBRQuery getQuery()
	{
		DystempDescription desc = new DystempDescription();

		String s;
			
		s = tAge.getText().trim();
		if (s.length()>0) desc.setAgeNumber(Double.valueOf(s));
		
		s = tBMI.getText().trim();
		if (s.length()>0) desc.setBmiNumber(Double.valueOf(s));
		
		s = tJob.getText().trim();
		if (s.length()>0) desc.setJob(s);
		
		if (rMale.isSelected()) desc.setGender("مرد");
		else desc.setGender("زن");
		
		s =GetFromPrincipleList("1");
		if (s.length()>0) desc.setSleepingWakefulness(new IETextOpenNLP(s));
		
		s =GetFromPrincipleList("2");
		if (s.length()>0) desc.setCustodyDisposal(new IETextOpenNLP(s));

		s =GetFromPrincipleList("3");
		if (s.length()>0) desc.setFood(new IETextOpenNLP(s));

		s =GetFromPrincipleList("4");
		if (s.length()>0) desc.setClimate(new IETextOpenNLP(s));

		s =GetFromPrincipleList("5");
		if (s.length()>0) desc.setPhysicalActivity(new IETextOpenNLP(s));

		s =GetFromPrincipleList("6");
		if (s.length()>0) desc.setMentalHealth(new IETextOpenNLP(s));
		
		s =GetFromDBTHList("1");
		if (s.length()>0) desc.setTendencyTo(new IETextOpenNLP(s));
		
		s =GetFromDBTHList("2");
		if (s.length()>0) desc.setBenefitFrom(new IETextOpenNLP(s));

		s =GetFromDBTHList("3");
		if (s.length()>0) desc.setDisadvantageOf(new IETextOpenNLP(s));

		s =GetFromDBTHList("4");
		if (s.length()>0) desc.setHatredOf(new IETextOpenNLP(s));
		
		
		s = cbFaceColor.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setFaceColor(s);			
		
		s = cbLimpness.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setLimpnessStr(s);	

		s = cbBodyState.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setBodyState(new IETextOpenNLP(s));			
		
		s = cbSaliva.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setSalivaStr(s);	
		
		s = cbSalivation.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setSalivationStr(s);
		
		s = cbSalivationTime.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setSalivationTime(s);	
		
		s = cbTongueColor.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setTongueColor(s);	
		
		s = cbTongueWettish.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setTongueWettishStr(s);	
		
		s = cbMouthTaste.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setMouthTaste(s);	
		
		s = cbStoolConsistency.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setStoolConsistencyStr(s);	
		
		s = cbStoolType.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setStoolType(s);	
		
		s = cbDefecationPeriod.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setDefecationPeriodStr(s);	
		
		s = cbAcerbate.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setAcerbateStr(s);	
		
		s = Integer.toString(sDigestionPower.getValue());
		if (s.length()>0) desc.setDigestionPowerNumber(Integer.valueOf(s));	
		
		s = cbFlatulence.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setFlatulenceStr(s);	
		
		s = cbFoodPutrefactionFreq.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setFoodPutrefactionFreqStr(s);	
		
		s = Integer.toString(sFoodTransitionSpeed.getValue());
		if (s.length()>0) desc.setFoodTransitionSpeedNumber(Integer.valueOf(s));	
		
		s = cbGastralgia.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setGastralgiaStr(s);	
		
		s = cbGastralgiaTime.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setGastralgiaTime(s);	
		
		s = cbGastricEructation.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setGastricEructationStr(s);	
		
		s = cbHeartburn.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setHeartburnStr(s);	
		
		s = cbHeavinessTime.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setHeavinessTime(s);	
		
		s = cbHiccup.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setHiccupStr(s);	
		
		s = cbQasayan.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setQasayanStr(s);	
		
		s = cbQasayanTime.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setQasayanTime(s);	
		
		s = cbStomachHeaviness.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setStomachHeavinessStr(s);	
		
		if (cTakhazkhoz.isSelected()) {
			s = "بلي";
			if (s.length()>0) desc.setTakhazkhoz(s);	
		}
		
		s = cbThirstinessReliefType.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setThirstinessReliefType(s);	
		
		s = Integer.toString(sThirstinessLevel.getValue());
		if (s.length()>0) desc.setThirstinessLevelNumber(Integer.valueOf(s));	
		
		s = Integer.toString(sAppetiteLevel.getValue());
		if (s.length()>0) desc.setAppetiteLevelNumber(Integer.valueOf(s));	
		
		s = cbEatingMuchTiny.getSelectedItem().toString().trim();
		if (s.length()>0) desc.setEatingMuchTinyStr(s);	
		
		s = Integer.toString(sPulsePower.getValue());
		if (s.length()>0) desc.setPulsePowerNumber(Integer.valueOf(s));	
		
		s = Integer.toString(sPulseSpeed.getValue());
		if (s.length()>0) desc.setPulseSpeedNumber(Integer.valueOf(s));	
		
		s = Integer.toString(sPulseConsistency.getValue());
		if (s.length()>0) desc.setPulseConsistencyNumber(Integer.valueOf(s));	
		
		s = Integer.toString(sPulseRate.getValue());
		if (s.length()>0) desc.setPulseRateNumber(Integer.valueOf(s));	
		
		s =GetFromDiseasesList();
		if (s.length()>0) desc.setDisease(new IEText(s));	
		
		Fuzzification f = new Fuzzification();
		f.InitFuzzyDataTypes();
		CBRCaseFunctions.InitDescriptionOfCase(desc, f);
		CBRQuery query = new CBRQuery();
		query.setDescription(desc);
		
		return query;
	}
	
	public boolean isExistsInDiseases(String s) {
		boolean b=false;
        for(int i = 0; i< modelDiseases.getSize();i++){
            if(modelDiseases.getElementAt(i).equals(s)) {
            	b=true;
            	break;
            }
        }		
        return b;
	}
		
	public boolean isExistsInPrinciples(String s) {
		boolean b=false;
        for(int i = 0; i< modelPrinciple.getSize();i++){
            if(modelPrinciple.getElementAt(i).equals(s)) {
            	b=true;
            	break;
            }
        }		
        return b;
	}
	
	public String GetFromDiseasesList() {
		String s = "";
        for(int i = 0; i< modelDiseases.getSize();i++){
            	if (s.length()>0) s = s + ",";
            	s = s + modelDiseases.getElementAt(i).toString();
        }			
		return s;
	}
	
	
	public String GetFromPrincipleList(String s2) {
		String s = "";
        for(int i = 0; i< modelPrinciple2.getSize();i++){
            if(modelPrinciple2.getElementAt(i).equals(s2)) {
            	if (s.length()>0) s = s + ",";
            	s = s + modelPrinciple.getElementAt(i).toString();
            }
        }			
		return s;
	}
	
	public String GetFromDBTHList(String s2) {
		String s = "";
        for(int i = 0; i< modelDBTH2.getSize();i++){
            if(modelDBTH2.getElementAt(i).equals(s2)) {
            	if (s.length()>0) s = s + ",";
            	s = s + modelDBTH.getElementAt(i).toString();
            }
        }			
		return s;
	}	
	
	public void AddToPrincipleList(String s,String s2) {
		if (isExistsInPrinciples(s)) listPrinciple.setSelectedValue(s,true);
		else {
			modelPrinciple.addElement(s);
			modelPrinciple2.addElement(s2);
		}
	}
	
	public void AddToDiseasesList(String s) {
		if (isExistsInDiseases(s)) listDiseases.setSelectedValue(s,true);
		else {
			modelDiseases.addElement(s);
		}
	}
	
	public boolean isExistsInDBTH(String s) {
		boolean b=false;
        for(int i = 0; i< modelDBTH.getSize();i++){
            if(modelDBTH.getElementAt(i).equals(s)) {
            	b=true;
            	break;
            }
        }		
        return b;
	}
	
	
	public void AddToDBTHList(String s,String s2) {
		if (isExistsInDBTH(s)) listDBTH.setSelectedValue(s,true);
		else {
			modelDBTH.addElement(s);
			modelDBTH2.addElement(s2);
		}
	}	
	
	/**
	 * Create the dialog.
	 */
	
	public QueryDialog(JFrame parent) {

		super(parent,true);		
		getContentPane().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setBounds(100, 100, 1015, 745);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBounds(764, 5, 225, 180);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(239, 5, 523, 180);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(764, 191, 225, 259);
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblNewLabel_1_2 = new JLabel("\u0645\u0639\u0627\u06CC\u0646\u0627\u062A \u0638\u0627\u0647\u0631\u06CC");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setForeground(Color.RED);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2.setBounds(67, 5, 103, 14);
		panel_2.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("\u0631\u0646\u06AF \u0635\u0648\u0631\u062A");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1.setBounds(136, 29, 68, 14);
		panel_2.add(lblNewLabel_2_1);
		
		JLabel lblBmi_1 = new JLabel("\u062A\u0631\u0647\u0644 \u0628\u062F\u0646");
		lblBmi_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1.setBounds(123, 54, 81, 14);
		panel_2.add(lblBmi_1);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBounds(10, 5, 225, 445);
		panel_2_1.setLayout(null);
		panel_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblNewLabel_1_2_2 = new JLabel("\u0639\u0644\u0627\u0626\u0645 \u06AF\u0648\u0627\u0631\u0634\u06CC");
		lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_2.setForeground(Color.RED);
		lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2.setBounds(69, 5, 103, 14);
		panel_2_1.add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("\u063A\u062B\u06CC\u0627\u0646");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1.setBounds(147, 33, 68, 14);
		panel_2_1.add(lblNewLabel_2_1_1);
		
		JLabel lblBmi_1_8 = new JLabel("\u0632\u0645\u0627\u0646 \u063A\u062B\u06CC\u0627\u0646");
		lblBmi_1_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_8.setBounds(134, 58, 81, 14);
		panel_2_1.add(lblBmi_1_8);
		
		cbQasayan = new JComboBox();
		cbQasayan.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbQasayan.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbQasayan.setBounds(10, 30, 91, 20);
		panel_2_1.add(cbQasayan);
		
		JLabel lblBmi_1_1_1 = new JLabel("\u062B\u0642\u0644 \u0645\u0639\u062F\u0647");
		lblBmi_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_1_1.setBounds(134, 83, 81, 14);
		panel_2_1.add(lblBmi_1_1_1);
		
		JLabel lblBmi_1_2_1 = new JLabel("\u0632\u0645\u0627\u0646 \u062B\u0642\u0644 \u0645\u0639\u062F\u0647");
		lblBmi_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_2_1.setBounds(134, 108, 81, 14);
		panel_2_1.add(lblBmi_1_2_1);
		
		JLabel lblBmi_1_3_1 = new JLabel("\u0642\u062F\u0631\u062A \u0647\u0636\u0645");
		lblBmi_1_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_3_1.setBounds(134, 363, 81, 14);
		panel_2_1.add(lblBmi_1_3_1);
		
		JLabel lblBmi_1_4_1 = new JLabel("\u062C\u0634\u0627\u0621");
		lblBmi_1_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_4_1.setBounds(134, 133, 81, 14);
		panel_2_1.add(lblBmi_1_4_1);
		
		JLabel lblBmi_1_5_1 = new JLabel("\u0646\u0641\u062E");
		lblBmi_1_5_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_5_1.setBounds(134, 158, 81, 14);
		panel_2_1.add(lblBmi_1_5_1);
		
		JLabel lblBmi_1_6_1 = new JLabel("\u0648\u062C\u0639 \u0645\u0639\u062F\u0647");
		lblBmi_1_6_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_6_1.setBounds(134, 183, 81, 14);
		panel_2_1.add(lblBmi_1_6_1);
		
		JLabel lblBmi_1_7_1 = new JLabel("\u0632\u0645\u0627\u0646 \u0648\u062C\u0639 \u0645\u0639\u062F\u0647");
		lblBmi_1_7_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1.setBounds(134, 208, 81, 14);
		panel_2_1.add(lblBmi_1_7_1);
		
		cbQasayanTime = new JComboBox();
		cbQasayanTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbQasayanTime.setModel(new DefaultComboBoxModel(new String[] {" ", "\u062F\u0627\u0626\u0645\u064A", "\u0628\u0639\u062F_\u063A\u0630\u0627"}));
		cbQasayanTime.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbQasayanTime.setBounds(10, 55, 91, 20);
		panel_2_1.add(cbQasayanTime);
		
		cbStomachHeaviness = new JComboBox();
		cbStomachHeaviness.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbStomachHeaviness.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbStomachHeaviness.setBounds(10, 80, 91, 20);
		panel_2_1.add(cbStomachHeaviness);
		
		cbHeavinessTime = new JComboBox();
		cbHeavinessTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbHeavinessTime.setModel(new DefaultComboBoxModel(new String[] {" ", "\u062F\u0627\u0626\u0645\u064A", "\u0628\u0639\u062F_\u063A\u0630\u0627"}));
		cbHeavinessTime.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbHeavinessTime.setBounds(10, 105, 91, 20);
		panel_2_1.add(cbHeavinessTime);
		
		cbGastricEructation = new JComboBox();
		cbGastricEructation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbGastricEructation.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbGastricEructation.setBounds(10, 130, 91, 20);
		panel_2_1.add(cbGastricEructation);
		
		cbFlatulence = new JComboBox();
		cbFlatulence.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbFlatulence.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbFlatulence.setBounds(10, 155, 91, 20);
		panel_2_1.add(cbFlatulence);
		
		cbGastralgia = new JComboBox();
		cbGastralgia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbGastralgia.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbGastralgia.setBounds(10, 180, 91, 20);
		panel_2_1.add(cbGastralgia);
		
		cbGastralgiaTime = new JComboBox();
		cbGastralgiaTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbGastralgiaTime.setModel(new DefaultComboBoxModel(new String[] {" ", "\u06AF\u0631\u0633\u0646\u06AF\u064A", "\u0628\u0644\u0627\u0641\u0627\u0635\u0644\u0647_\u0628\u0639\u062F_\u063A\u0630\u0627", "2\u0633\u0627\u0639\u062A_\u0628\u0639\u062F_\u063A\u0630\u0627", "4\u0633\u0627\u0639\u062A_\u0628\u0639\u062F_\u063A\u0630\u0627"}));
		cbGastralgiaTime.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbGastralgiaTime.setBounds(10, 205, 91, 20);
		panel_2_1.add(cbGastralgiaTime);
		
		JPanel panel_2_1_1 = new JPanel();
		panel_2_1_1.setBounds(10, 455, 474, 110);
		panel_2_1_1.setLayout(null);
		panel_2_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblNewLabel_1_2_2_1 = new JLabel("\u0646\u0628\u0636");
		lblNewLabel_1_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_2_1.setForeground(Color.RED);
		lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_2_1.setBounds(171, 5, 103, 14);
		panel_2_1_1.add(lblNewLabel_1_2_2_1);
		
		JLabel lblBmi_1_3_1_1 = new JLabel("\u0642\u062F\u0631\u062A \u0646\u0628\u0636");
		lblBmi_1_3_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_3_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_3_1_1.setBounds(137, 32, 81, 14);
		panel_2_1_1.add(lblBmi_1_3_1_1);
		
		JLabel lblBmi_1_7_1_1 = new JLabel("\u0633\u0631\u0639\u062A \u0646\u0628\u0636");
		lblBmi_1_7_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1.setBounds(137, 65, 81, 14);
		panel_2_1_1.add(lblBmi_1_7_1_1);
		
		JLabel lblBmi_1_7_1_2 = new JLabel("\u062F\u0641\u0639\u0627\u062A \u0646\u0645\u0648\u0633\u062A \u063A\u0630\u0627");
		lblBmi_1_7_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_2.setBounds(112, 233, 103, 14);
		panel_2_1.add(lblBmi_1_7_1_2);
		
		JLabel lblBmi_1_7_1_1_1 = new JLabel("\u062D\u0631\u0642\u062A \u0645\u0639\u062F\u0647");
		lblBmi_1_7_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_1.setBounds(134, 258, 81, 14);
		panel_2_1.add(lblBmi_1_7_1_1_1);
		
		cbFaceColor = new JComboBox();
		cbFaceColor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbFaceColor.setEditable(true);
		cbFaceColor.setModel(new DefaultComboBoxModel(new String[] {" ", "\u0633\u0641\u064A\u062F", "\u06AF\u0646\u062F\u0645\u06AF\u0648\u0646"}));
		cbFaceColor.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbFaceColor.setBounds(22, 23, 91, 20);
		panel_2.add(cbFaceColor);
		
		JLabel lblBmi_1_1 = new JLabel("\u0648\u0636\u0639\u06CC\u062A \u0628\u062F\u0646");
		lblBmi_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_1.setBounds(123, 79, 81, 14);
		panel_2.add(lblBmi_1_1);
		
		JLabel lblBmi_1_2 = new JLabel("\u0622\u0628 \u062F\u0647\u0627\u0646");
		lblBmi_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_2.setBounds(123, 104, 81, 14);
		panel_2.add(lblBmi_1_2);
		
		JLabel lblBmi_1_3 = new JLabel("\u0627\u0647\u0631\u0627\u0642 \u0631\u06CC\u0642");
		lblBmi_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_3.setBounds(123, 129, 81, 14);
		panel_2.add(lblBmi_1_3);
		
		JLabel lblBmi_1_4 = new JLabel("\u0632\u0645\u0627\u0646 \u0627\u0647\u0631\u0627\u0642 \u0631\u06CC\u0642");
		lblBmi_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_4.setBounds(123, 154, 81, 14);
		panel_2.add(lblBmi_1_4);
		
		JLabel lblBmi_1_5 = new JLabel("\u0637\u0639\u0645 \u062F\u0647\u0627\u0646");
		lblBmi_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_5.setBounds(123, 179, 81, 14);
		panel_2.add(lblBmi_1_5);
		
		JLabel lblBmi_1_6 = new JLabel("\u0631\u0646\u06AF \u0632\u0628\u0627\u0646");
		lblBmi_1_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_6.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_6.setBounds(123, 204, 81, 14);
		panel_2.add(lblBmi_1_6);
		
		JLabel lblBmi_1_7 = new JLabel("\u0631\u0637\u0648\u0628\u062A \u0632\u0628\u0627\u0646");
		lblBmi_1_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7.setBounds(123, 229, 81, 14);
		panel_2.add(lblBmi_1_7);
		
		cbLimpness = new JComboBox();
		cbLimpness.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbLimpness.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbLimpness.setBounds(22, 48, 91, 20);
		panel_2.add(cbLimpness);
		
		cbBodyState = new JComboBox();
		cbBodyState.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbBodyState.setModel(new DefaultComboBoxModel(new String[] {" ", "\u062A\u0647\u0628\u062C_\u0635\u0648\u0631\u062A", "\u0648\u0631\u0645_\u0634\u06A9\u0645\u064A", "\u0636\u0639\u0641_\u0633\u0633\u062A\u064A", "\u06A9\u0633\u0644_\u062F\u0631_\u062D\u0631\u06A9\u0627\u062A", "\u0686\u0627\u0642", "\u0644\u0627\u063A\u0631"}));
		cbBodyState.setEditable(true);
		cbBodyState.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbBodyState.setBounds(22, 73, 91, 20);
		panel_2.add(cbBodyState);
		
		cbSaliva = new JComboBox();
		cbSaliva.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbSaliva.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbSaliva.setBounds(22, 98, 91, 20);
		panel_2.add(cbSaliva);
		
		cbSalivation = new JComboBox();
		cbSalivation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbSalivation.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbSalivation.setBounds(22, 123, 91, 20);
		panel_2.add(cbSalivation);
		
		cbSalivationTime = new JComboBox();
		cbSalivationTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbSalivationTime.setModel(new DefaultComboBoxModel(new String[] {" ", "\u062E\u0648\u0627\u0628", "\u06AF\u0631\u0633\u0646\u06AF\u064A"}));
		cbSalivationTime.setEditable(true);
		cbSalivationTime.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbSalivationTime.setBounds(22, 148, 91, 20);
		panel_2.add(cbSalivationTime);
		
		cbMouthTaste = new JComboBox();
		cbMouthTaste.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbMouthTaste.setModel(new DefaultComboBoxModel(new String[] {" ", "\u0628\u064A_\u0645\u0632\u0647", "\u062A\u0631\u0634", "\u062A\u0644\u062E", "\u0634\u0648\u0631"}));
		cbMouthTaste.setEditable(true);
		cbMouthTaste.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbMouthTaste.setBounds(22, 173, 91, 20);
		panel_2.add(cbMouthTaste);
		
		cbTongueColor = new JComboBox();
		cbTongueColor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbTongueColor.setEditable(true);
		cbTongueColor.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbTongueColor.setBounds(22, 198, 91, 20);
		panel_2.add(cbTongueColor);
		
		cbTongueWettish = new JComboBox();
		cbTongueWettish.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbTongueWettish.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbTongueWettish.setBounds(22, 223, 91, 20);
		panel_2.add(cbTongueWettish);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u0633\u062A\u0647 \u0636\u0631\u0648\u0631\u06CC");
		lblNewLabel_1_1.setBounds(202, 5, 64, 14);
		lblNewLabel_1_1.setForeground(Color.RED);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		panel_1.setLayout(null);
		panel_1.add(lblNewLabel_1_1);
		
		lblNewLabel_1_2_1 = new JLabel("\u062E\u0648\u0627\u0628 \u0648 \u0628\u06CC\u062F\u0627\u0631\u06CC");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1.setBounds(425, 26, 86, 14);
		panel_1.add(lblNewLabel_1_2_1);
		
		cbSleepingWakefulness = new JComboBox();
		cbSleepingWakefulness.setEditable(true);
		cbSleepingWakefulness.setBounds(251, 23, 164, 20);
		panel_1.add(cbSleepingWakefulness);
		cbSleepingWakefulness.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbSleepingWakefulness.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		modelPrinciple = new DefaultListModel<String>();
		modelPrinciple2 = new DefaultListModel<String>();
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToPrincipleList(cbSleepingWakefulness.getSelectedItem().toString(),"1");
			}
		});
		btnNewButton.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton.setBounds(232, 23, 20, 20);
		panel_1.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane.setBounds(10, 23, 212, 133);
		panel_1.add(scrollPane);
		
		listPrinciple = new JList(modelPrinciple);
		listPrinciple.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPrinciple.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listPrinciple.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		listPrinciple.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0645\u0642\u0627\u062F\u06CC\u0631", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), new Color(0, 0, 0)));
		listPrinciple.setBackground(SystemColor.menu);
		scrollPane.setViewportView(listPrinciple);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("\u0627\u062D\u062A\u0628\u0627\u0633 \u0648 \u0627\u0633\u062A\u0641\u0631\u0627\u063A");
		lblNewLabel_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1_1.setBounds(407, 51, 104, 14);
		panel_1.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("\u063A\u0630\u0627 \u0648 \u0646\u0648\u0634\u06CC\u062F\u0646\u06CC");
		lblNewLabel_1_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1_1_1.setBounds(407, 76, 104, 14);
		panel_1.add(lblNewLabel_1_2_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_2 = new JLabel("\u0647\u0648\u0627 \u0648 \u0633\u06A9\u0648\u0646\u062A");
		lblNewLabel_1_2_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1_1_2.setBounds(407, 101, 104, 14);
		panel_1.add(lblNewLabel_1_2_1_1_2);
		
		JLabel lblNewLabel_1_2_1_1_3 = new JLabel("\u062D\u0631\u06A9\u062A \u0648 \u0633\u06A9\u0648\u0646");
		lblNewLabel_1_2_1_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1_3.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1_1_3.setBounds(407, 126, 104, 14);
		panel_1.add(lblNewLabel_1_2_1_1_3);
		
		JLabel lblNewLabel_1_2_1_1_4 = new JLabel("\u0627\u0639\u0631\u0627\u0636 \u0646\u0641\u0633\u0627\u0646\u06CC");
		lblNewLabel_1_2_1_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1_4.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1_1_4.setBounds(407, 151, 104, 14);
		panel_1.add(lblNewLabel_1_2_1_1_4);
		
		cbCustodyDisposal = new JComboBox();
		cbCustodyDisposal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbCustodyDisposal.setEditable(true);
		cbCustodyDisposal.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbCustodyDisposal.setBounds(251, 48, 164, 20);
		panel_1.add(cbCustodyDisposal);
		
		cbFood = new JComboBox();
		cbFood.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbFood.setEditable(true);
		cbFood.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbFood.setBounds(251, 73, 164, 20);
		panel_1.add(cbFood);
		
		cbClimate = new JComboBox();
		cbClimate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbClimate.setEditable(true);
		cbClimate.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbClimate.setBounds(251, 98, 164, 20);
		panel_1.add(cbClimate);
		
		cbPhysicalActivity = new JComboBox();
		cbPhysicalActivity.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbPhysicalActivity.setEditable(true);
		cbPhysicalActivity.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbPhysicalActivity.setBounds(251, 123, 164, 20);
		panel_1.add(cbPhysicalActivity);
		
		cbMentalHealth = new JComboBox();
		cbMentalHealth.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbMentalHealth.setEditable(true);
		cbMentalHealth.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbMentalHealth.setBounds(251, 148, 164, 20);
		panel_1.add(cbMentalHealth);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToPrincipleList(cbCustodyDisposal.getSelectedItem().toString(),"2");
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_1.setBounds(232, 48, 20, 20);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToPrincipleList(cbFood.getSelectedItem().toString(),"3");
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_2.setBounds(232, 73, 20, 20);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToPrincipleList(cbClimate.getSelectedItem().toString(),"4");
			}
		});
		btnNewButton_3.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_3.setBounds(232, 98, 20, 20);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToPrincipleList(cbPhysicalActivity.getSelectedItem().toString(),"5");
			}
		});
		btnNewButton_4.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_4.setBounds(232, 123, 20, 20);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToPrincipleList(cbMentalHealth.getSelectedItem().toString(),"6");
			}
		});
		btnNewButton_5.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_5.setBounds(232, 148, 20, 20);
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listPrinciple.getSelectedIndex();
			    if (index != -1) {
			    	modelPrinciple.remove(index);
			    	modelPrinciple2.remove(index);
			    }
			}
		});
		btnNewButton_6.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\remove.png"));
		btnNewButton_6.setBounds(10, 156, 20, 20);
		panel_1.add(btnNewButton_6);
		
		JLabel lblNewLabel_1 = new JLabel("\u0627\u0637\u0644\u0627\u0639\u0627\u062A \u0641\u0631\u062F\u06CC");
		lblNewLabel_1.setBounds(85, 5, 74, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.RED);
		
		JLabel lblNewLabel_2 = new JLabel("\u0633\u0646 (\u0633\u0627\u0644)");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(119, 44, 68, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		tAge = new JTextField();
		tAge.setBounds(10, 44, 86, 20);
		tAge.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tAge.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		tAge.setColumns(10);
		
		JLabel label = new JLabel("\u062C\u0646\u0633");
		label.setBounds(162, 119, 25, 14);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		rMale = new JRadioButton("\u0645\u0631\u062F");
		rMale.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rMale.setBounds(28, 121, 41, 23);
		rMale.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		buttonGroup.add(rMale);
		
		rFemale = new JRadioButton("\u0632\u0646");
		rFemale.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rFemale.setBounds(67, 121, 37, 23);
		rFemale.setSelected(true);
		rFemale.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		buttonGroup.add(rFemale);
		
		JLabel lblBmi = new JLabel("\u0634\u0627\u062E\u0635 \u062A\u0648\u062F\u0647 \u0628\u062F\u0646\u06CC");
		lblBmi.setBounds(106, 69, 81, 14);
		lblBmi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		tBMI = new JTextField();
		tBMI.setBounds(10, 69, 86, 20);
		tBMI.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tBMI.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		tBMI.setColumns(10);
		
		JLabel label_2 = new JLabel("\u0634\u063A\u0644");
		label_2.setBounds(162, 94, 25, 14);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		tJob = new JTextField();
		tJob.setBounds(10, 94, 86, 20);
		tJob.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tJob.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		tJob.setColumns(10);
		panel.setLayout(null);
		panel.add(lblNewLabel_1);
		panel.add(tJob);
		panel.add(rMale);
		panel.add(rFemale);
		panel.add(tAge);
		panel.add(tBMI);
		panel.add(lblNewLabel_2);
		panel.add(label);
		panel.add(label_2);
		panel.add(lblBmi);
		contentPanel.setLayout(null);
		contentPanel.add(panel_1);
		contentPanel.add(panel);
		contentPanel.add(panel_2_1_1);
		
		JLabel lblBmi_1_7_1_1_2 = new JLabel("\u062A\u0648\u0627\u062A\u0631 \u0646\u0628\u0636");
		lblBmi_1_7_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_2.setBounds(356, 32, 81, 14);
		panel_2_1_1.add(lblBmi_1_7_1_1_2);
		
		JLabel lblBmi_1_7_1_1_2_1 = new JLabel("\u0642\u0648\u0627\u0645 \u0646\u0628\u0636");
		lblBmi_1_7_1_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_2_1.setBounds(356, 63, 81, 14);
		panel_2_1_1.add(lblBmi_1_7_1_1_2_1);
		
		sPulsePower = new JSlider();
		sPulsePower.setValue(5);
		sPulsePower.setSnapToTicks(true);
		sPulsePower.setPaintTicks(true);
		sPulsePower.setMinorTickSpacing(1);
		sPulsePower.setMaximum(10);
		sPulsePower.setMajorTickSpacing(2);
		sPulsePower.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sPulsePower.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		sPulsePower.setBounds(35, 34, 91, 20);
		panel_2_1_1.add(sPulsePower);
		
		sPulseRate = new JSlider();
		sPulseRate.setValue(5);
		sPulseRate.setSnapToTicks(true);
		sPulseRate.setPaintTicks(true);
		sPulseRate.setMinorTickSpacing(1);
		sPulseRate.setMaximum(10);
		sPulseRate.setMajorTickSpacing(2);
		sPulseRate.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sPulseRate.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		sPulseRate.setBounds(254, 32, 91, 20);
		panel_2_1_1.add(sPulseRate);
		
		sPulseConsistency = new JSlider();
		sPulseConsistency.setValue(5);
		sPulseConsistency.setSnapToTicks(true);
		sPulseConsistency.setPaintTicks(true);
		sPulseConsistency.setPaintLabels(true);
		sPulseConsistency.setMinorTickSpacing(1);
		sPulseConsistency.setMaximum(10);
		sPulseConsistency.setMajorTickSpacing(2);
		sPulseConsistency.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sPulseConsistency.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		sPulseConsistency.setBounds(254, 63, 91, 36);
		panel_2_1_1.add(sPulseConsistency);
		
		sPulseSpeed = new JSlider();
		sPulseSpeed.setValue(5);
		sPulseSpeed.setSnapToTicks(true);
		sPulseSpeed.setPaintTicks(true);
		sPulseSpeed.setPaintLabels(true);
		sPulseSpeed.setMinorTickSpacing(1);
		sPulseSpeed.setMaximum(10);
		sPulseSpeed.setMajorTickSpacing(2);
		sPulseSpeed.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sPulseSpeed.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		sPulseSpeed.setBounds(35, 63, 91, 36);
		panel_2_1_1.add(sPulseSpeed);
		contentPanel.add(panel_2_1);
		
		JLabel lblBmi_1_3_1_2 = new JLabel("\u0633\u0631\u0639\u062A \u0627\u0646\u062D\u062F\u0627\u0631");
		lblBmi_1_3_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_3_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_3_1_2.setBounds(134, 398, 81, 14);
		panel_2_1.add(lblBmi_1_3_1_2);
		
		sDigestionPower = new JSlider();
		sDigestionPower.setValue(5);
		sDigestionPower.setSnapToTicks(true);
		sDigestionPower.setPaintTicks(true);
		sDigestionPower.setMinorTickSpacing(1);
		sDigestionPower.setMaximum(10);
		sDigestionPower.setMajorTickSpacing(2);
		sDigestionPower.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sDigestionPower.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		sDigestionPower.setBounds(10, 363, 91, 20);
		panel_2_1.add(sDigestionPower);
		
		sFoodTransitionSpeed = new JSlider();
		sFoodTransitionSpeed.setValue(5);
		sFoodTransitionSpeed.setSnapToTicks(true);
		sFoodTransitionSpeed.setPaintTicks(true);
		sFoodTransitionSpeed.setPaintLabels(true);
		sFoodTransitionSpeed.setMinorTickSpacing(1);
		sFoodTransitionSpeed.setMaximum(10);
		sFoodTransitionSpeed.setMajorTickSpacing(2);
		sFoodTransitionSpeed.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sFoodTransitionSpeed.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		sFoodTransitionSpeed.setBounds(10, 398, 91, 36);
		panel_2_1.add(sFoodTransitionSpeed);
		
		JLabel lblBmi_1_7_1_1_1_1 = new JLabel("\u0641\u0648\u0627\u0642");
		lblBmi_1_7_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_1_1.setBounds(134, 283, 81, 14);
		panel_2_1.add(lblBmi_1_7_1_1_1_1);
		
		JLabel lblBmi_1_7_1_1_1_2 = new JLabel("\u062C\u0634\u0627\u0621 \u062D\u0627\u0645\u0636");
		lblBmi_1_7_1_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_1_2.setBounds(134, 308, 81, 14);
		panel_2_1.add(lblBmi_1_7_1_1_1_2);
		
		cbFoodPutrefactionFreq = new JComboBox();
		cbFoodPutrefactionFreq.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbFoodPutrefactionFreq.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbFoodPutrefactionFreq.setBounds(10, 230, 91, 20);
		panel_2_1.add(cbFoodPutrefactionFreq);
		
		cbHeartburn = new JComboBox();
		cbHeartburn.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbHeartburn.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbHeartburn.setBounds(10, 255, 91, 20);
		panel_2_1.add(cbHeartburn);
		
		cbHiccup = new JComboBox();
		cbHiccup.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbHiccup.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbHiccup.setBounds(10, 280, 91, 20);
		panel_2_1.add(cbHiccup);
		
		cbAcerbate = new JComboBox();
		cbAcerbate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbAcerbate.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbAcerbate.setBounds(10, 305, 91, 20);
		panel_2_1.add(cbAcerbate);
		
		JLabel lblBmi_1_7_1_1_1_2_1 = new JLabel("\u062A\u062E\u0636\u062E\u0636");
		lblBmi_1_7_1_1_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_1_2_1.setBounds(134, 333, 81, 14);
		panel_2_1.add(lblBmi_1_7_1_1_1_2_1);
		
		cTakhazkhoz = new JCheckBox("");
		cTakhazkhoz.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cTakhazkhoz.setBounds(4, 332, 97, 23);
		panel_2_1.add(cTakhazkhoz);
		contentPanel.add(panel_2);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setLayout(null);
		panel_2_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2_2.setBounds(494, 455, 495, 110);
		contentPanel.add(panel_2_2);
		
		JLabel lblNewLabel_1_2_3 = new JLabel("\u0627\u0634\u062A\u0647\u0627 \u0648 \u0639\u0637\u0634");
		lblNewLabel_1_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_3.setForeground(Color.RED);
		lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_3.setBounds(200, 5, 103, 14);
		panel_2_2.add(lblNewLabel_1_2_3);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("\u0646\u062D\u0648\u0647 \u062A\u0633\u06A9\u06CC\u0646 \u062A\u0634\u0646\u06AF\u06CC");
		lblNewLabel_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_2.setBounds(135, 44, 103, 14);
		panel_2_2.add(lblNewLabel_2_1_2);
		
		JLabel lblBmi_1_9 = new JLabel("\u0631\u06CC\u0632\u0647 \u062E\u0648\u0627\u0631\u06CC");
		lblBmi_1_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_9.setBounds(157, 69, 81, 14);
		panel_2_2.add(lblBmi_1_9);
		
		cbThirstinessReliefType = new JComboBox();
		cbThirstinessReliefType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbThirstinessReliefType.setEditable(true);
		cbThirstinessReliefType.setModel(new DefaultComboBoxModel(new String[] {" ", "\u0622\u0628_\u0633\u0631\u062F", "\u0622\u0628_\u06AF\u0631\u0645", "\u06A9\u0645\u064A_\u0622\u0628"}));
		cbThirstinessReliefType.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbThirstinessReliefType.setBounds(21, 38, 91, 20);
		panel_2_2.add(cbThirstinessReliefType);
		
		cbEatingMuchTiny = new JComboBox();
		cbEatingMuchTiny.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbEatingMuchTiny.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbEatingMuchTiny.setBounds(21, 63, 91, 20);
		panel_2_2.add(cbEatingMuchTiny);
		
		JLabel lblBmi_1_7_1_1_2_2 = new JLabel("\u0645\u06CC\u0632\u0627\u0646 \u0639\u0637\u0634");
		lblBmi_1_7_1_1_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_2_2.setBounds(391, 32, 81, 14);
		panel_2_2.add(lblBmi_1_7_1_1_2_2);
		
		sThirstinessLevel = new JSlider();
		sThirstinessLevel.setValue(5);
		sThirstinessLevel.setSnapToTicks(true);
		sThirstinessLevel.setPaintTicks(true);
		sThirstinessLevel.setMinorTickSpacing(1);
		sThirstinessLevel.setMaximum(10);
		sThirstinessLevel.setMajorTickSpacing(2);
		sThirstinessLevel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sThirstinessLevel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		sThirstinessLevel.setBounds(289, 32, 91, 20);
		panel_2_2.add(sThirstinessLevel);
		
		sAppetiteLevel = new JSlider();
		sAppetiteLevel.setValue(5);
		sAppetiteLevel.setSnapToTicks(true);
		sAppetiteLevel.setPaintTicks(true);
		sAppetiteLevel.setPaintLabels(true);
		sAppetiteLevel.setMinorTickSpacing(1);
		sAppetiteLevel.setMaximum(10);
		sAppetiteLevel.setMajorTickSpacing(2);
		sAppetiteLevel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		sAppetiteLevel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		sAppetiteLevel.setBounds(289, 63, 91, 36);
		panel_2_2.add(sAppetiteLevel);
		
		JLabel lblBmi_1_7_1_1_2_1_1 = new JLabel("\u0645\u06CC\u0632\u0627\u0646 \u0627\u0634\u062A\u0647\u0627");
		lblBmi_1_7_1_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_2_1_1.setBounds(391, 63, 81, 14);
		panel_2_2.add(lblBmi_1_7_1_1_2_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1_1.setBounds(239, 191, 523, 143);
		contentPanel.add(panel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("تمایل/انتفاع/تضرر/تنفر");
		lblNewLabel_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_1_1.setBounds(200, 5, 164, 14);
		panel_1_1.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("\u062A\u0645\u0627\u06CC\u0644 \u0628\u0647");
		lblNewLabel_1_2_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1_2.setBounds(425, 28, 86, 14);
		panel_1_1.add(lblNewLabel_1_2_1_2);
		
		cbTendencyTo = new JComboBox();
		cbTendencyTo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbTendencyTo.setEditable(true);
		cbTendencyTo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbTendencyTo.setBounds(251, 25, 164, 20);
		panel_1_1.add(cbTendencyTo);
		
		JButton btnNewButton_7 = new JButton("");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToDBTHList("تمايل_به_"+cbTendencyTo.getSelectedItem().toString(),"1");
			}
		});
		btnNewButton_7.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_7.setBounds(232, 25, 20, 20);
		panel_1_1.add(btnNewButton_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane_1.setBounds(10, 25, 212, 92);
		panel_1_1.add(scrollPane_1);
		
		modelDBTH = new DefaultListModel<String>();
		modelDBTH2 = new DefaultListModel<String>();
		modelDiseases = new DefaultListModel<String>();
		
		listDBTH = new JList(modelDBTH);
		listDBTH.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDBTH.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listDBTH.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		listDBTH.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0645\u0642\u0627\u062F\u06CC\u0631", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), new Color(0, 0, 0)));
		listDBTH.setBackground(SystemColor.menu);
		scrollPane_1.setViewportView(listDBTH);
		
		JLabel lblNewLabel_1_2_1_1_5 = new JLabel("\u0627\u0646\u062A\u0641\u0627\u0639 \u0627\u0632");
		lblNewLabel_1_2_1_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1_5.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1_1_5.setBounds(407, 53, 104, 14);
		panel_1_1.add(lblNewLabel_1_2_1_1_5);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("\u062A\u0636\u0631\u0631 \u0627\u0632");
		lblNewLabel_1_2_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1_1_1_1.setBounds(407, 78, 104, 14);
		panel_1_1.add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_2_1 = new JLabel("\u062A\u0646\u0641\u0631 \u0627\u0632");
		lblNewLabel_1_2_1_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1_1_2_1.setBounds(407, 103, 104, 14);
		panel_1_1.add(lblNewLabel_1_2_1_1_2_1);
		
		cbBenefitFrom = new JComboBox();
		cbBenefitFrom.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbBenefitFrom.setEditable(true);
		cbBenefitFrom.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbBenefitFrom.setBounds(251, 50, 164, 20);
		panel_1_1.add(cbBenefitFrom);
		
		cbDisadvantageOf = new JComboBox();
		cbDisadvantageOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbDisadvantageOf.setEditable(true);
		cbDisadvantageOf.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbDisadvantageOf.setBounds(251, 75, 164, 20);
		panel_1_1.add(cbDisadvantageOf);
		
		cbHatredOf = new JComboBox();
		cbHatredOf.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbHatredOf.setEditable(true);
		cbHatredOf.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbHatredOf.setBounds(251, 100, 164, 20);
		panel_1_1.add(cbHatredOf);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToDBTHList("انتفاع_از_"+cbBenefitFrom.getSelectedItem().toString(),"2");
			}
		});
		btnNewButton_1_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_1_1.setBounds(232, 50, 20, 20);
		panel_1_1.add(btnNewButton_1_1);
		
		JButton btnNewButton_2_1 = new JButton("");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToDBTHList("تضرر_از_"+cbDisadvantageOf.getSelectedItem().toString(),"3");				
			}
		});
		btnNewButton_2_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_2_1.setBounds(232, 75, 20, 20);
		panel_1_1.add(btnNewButton_2_1);
		
		JButton btnNewButton_3_1 = new JButton("");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToDBTHList("تنفر_از_"+cbHatredOf.getSelectedItem().toString(),"4");			
			}
		});
		btnNewButton_3_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_3_1.setBounds(232, 100, 20, 20);
		panel_1_1.add(btnNewButton_3_1);
		
		JButton btnNewButton_6_1 = new JButton("");
		btnNewButton_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listDBTH.getSelectedIndex();
			    if (index != -1) {
			    	modelDBTH.remove(index);			
			    	modelDBTH2.remove(index);			
			    }
			}
		});
		btnNewButton_6_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\remove.png"));
		btnNewButton_6_1.setBounds(10, 118, 20, 20);
		panel_1_1.add(btnNewButton_6_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(494, 336, 268, 114);
		contentPanel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1_2_3_1 = new JLabel("\u0648\u0636\u0639\u06CC\u062A \u062F\u0641\u0639");
		lblNewLabel_1_2_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_3_1.setForeground(Color.RED);
		lblNewLabel_1_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_3_1.setBounds(90, 5, 103, 14);
		panel_3.add(lblNewLabel_1_2_3_1);
		
		JLabel lblBmi_1_7_1_1_2_2_1 = new JLabel("\u0642\u0648\u0627\u0645 \u0645\u062F\u0641\u0648\u0639");
		lblBmi_1_7_1_1_2_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_2_2_1.setBounds(161, 33, 81, 14);
		panel_3.add(lblBmi_1_7_1_1_2_2_1);
		
		cbStoolConsistency = new JComboBox();
		cbStoolConsistency.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbStoolConsistency.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbStoolConsistency.setBounds(46, 30, 91, 20);
		panel_3.add(cbStoolConsistency);
		
		JLabel lblBmi_1_7_1_1_2_2_1_1 = new JLabel("\u0627\u062C\u0627\u0628\u062A \u0645\u0632\u0627\u062C");
		lblBmi_1_7_1_1_2_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_2_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_2_2_1_1.setBounds(161, 58, 81, 14);
		panel_3.add(lblBmi_1_7_1_1_2_2_1_1);
		
		cbDefecationPeriod = new JComboBox();
		cbDefecationPeriod.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbDefecationPeriod.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbDefecationPeriod.setBounds(46, 55, 91, 20);
		panel_3.add(cbDefecationPeriod);
		
		JLabel lblBmi_1_7_1_1_2_2_1_1_1 = new JLabel("\u0646\u0648\u0639");
		lblBmi_1_7_1_1_2_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBmi_1_7_1_1_2_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBmi_1_7_1_1_2_2_1_1_1.setBounds(195, 83, 47, 14);
		panel_3.add(lblBmi_1_7_1_1_2_2_1_1_1);
		
		cbStoolType = new JComboBox();
		cbStoolType.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbStoolType.setEditable(true);
		cbStoolType.setModel(new DefaultComboBoxModel(new String[] {" ", "\u0647\u0636\u0645_\u0646\u0634\u062F\u0647", "\u0645\u0646\u062A\u0641\u062E"}));
		cbStoolType.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbStoolType.setBounds(46, 80, 91, 20);
		panel_3.add(cbStoolType);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(239, 336, 247, 110);
		contentPanel.add(panel_3_1);
		panel_3_1.setLayout(null);
		panel_3_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblNewLabel_1_2_3_1_1 = new JLabel("\u0628\u06CC\u0645\u0627\u0631\u06CC \u0647\u0627\u06CC \u0645\u0634\u0627\u0631\u06A9");
		lblNewLabel_1_2_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2_3_1_1.setForeground(Color.RED);
		lblNewLabel_1_2_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_3_1_1.setBounds(75, 5, 126, 14);
		panel_3_1.add(lblNewLabel_1_2_3_1_1);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setViewportBorder(null);
		scrollPane_1_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane_1_1.setBounds(40, 23, 186, 80);
		panel_3_1.add(scrollPane_1_1);
		
		listDiseases = new JList(modelDiseases);
		listDiseases.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		listDiseases.setFont(new Font("Tahoma", Font.PLAIN, 11));
		scrollPane_1_1.setViewportView(listDiseases);
		listDiseases.setBackground(SystemColor.menu);
		
		JButton btnNewButton_3_1_1 = new JButton("");
		btnNewButton_3_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenDiseaseSelector();
			}
		});
		btnNewButton_3_1_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_3_1_1.setBounds(18, 23, 20, 20);
		panel_3_1.add(btnNewButton_3_1_1);
		
		JButton btnNewButton_6_1_1 = new JButton("");
		btnNewButton_6_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listDiseases.getSelectedIndex();
			    if (index != -1) {
			    	modelDiseases.remove(index);			
			    }				
			}
			
		});
		btnNewButton_6_1_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\remove.png"));
		btnNewButton_6_1_1.setBounds(16, 83, 20, 20);
		panel_3_1.add(btnNewButton_6_1_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("  \u062E\u0631\u0648\u062C  ");
				cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							CBRDystemp.getInstance().postCycle();
						} catch (Exception ex) {
							org.apache.commons.logging.LogFactory.getLog(CBRDystemp.class).error(ex);
						}
						System.exit(-1);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("   \u062C\u0633\u062A\u062C\u0648   ");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setQuery();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
	/*		JButton okButton = new JButton("\u062A\u0646\u0638\u06CC\u0645\u0627\u062A \u062A\u0627\u0628\u0639 \u0645\u0634\u0627\u0628\u0647\u062A");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					recommender.similarityDialog2.setVisible(true);
				}
				
			});
			okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);*/
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
		
		InitDiseaseSelector();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QueryDialog qf = new QueryDialog(null);
		qf.setVisible(true);
		System.out.println("Bye");
	}		
}
