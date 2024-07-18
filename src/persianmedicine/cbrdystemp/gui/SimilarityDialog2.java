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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.border.MatteBorder;

import datatypes.FuzzyAge;
import datatypes.FuzzyBMI;
import datatypes.*;
import functions.CBRCaseFunctions;
import functions.FuzzyAverage;
import functions.SimilarityConfig;
import jcolibri.cbrcore.Attribute;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumCyclicDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntCosine;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeep;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDeepBasic;
import jcolibri.method.retrieve.NNretrieval.similarity.local.ontology.OntDetail;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.CosineCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.DiceCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.JaccardCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.OverlapCoefficient;
import persianmedicine.cbrdystemp.DystempDescription;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class SimilarityDialog2 extends JDialog {
	
	public SimilarityConfig sim;
	
	private static final long serialVersionUID = 1L;
	
	SpinnerNumberModel k;	
	
	JComboBox strCombo,numberCombo,enumCombo,textCombo,ontoCombo;
	JCheckBox cbOntologyStrucutre;

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SimilarityDialog2 dialog = new SimilarityDialog2(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SimilarityDialog2(JFrame parent) {
		
		super(parent,true);				
		getContentPane().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setBounds(100, 100, 598, 565);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
		Vector<String> stringfunctions = new Vector<String>();
		stringfunctions.add("Equal");
		
		Vector<String> numberfunctions = new Vector<String>();
		numberfunctions.add("Fuzzy");
		numberfunctions.add("Numerical");
		
		Vector<String> enumfunctions = new Vector<String>();
		enumfunctions.add("EnumCyclicDistance");
		enumfunctions.add("EnumDistance");
		enumfunctions.add("Table");
		enumfunctions.add("Equal");
		
		Vector<String> ontofunctions = new Vector<String>();
		ontofunctions.add("OntoSimMax");
		ontofunctions.add("Text");		
		
		Vector<String> textfunctions = new Vector<String>();
		textfunctions.add("CosineCoefficient");
		textfunctions.add("DiceCoefficient");
		textfunctions.add("JaccardCoefficient");
	//	textfunctions.add("LuceneTextSimilarity");
		textfunctions.add("OverlapCoefficient");		

		
		
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(125, 60, 320, 150);
		contentPanel.add(panel);
	//	panel.setBorder(new LineBorder(Color.GRAY));		
		panel.setLayout(new GridLayout(6, 1, 0, 5));
		
		{
			JPanel panel1 = new JPanel();
			panel1.setLayout(null);
			panel1.setSize(320, 20);
				
			numberCombo = new JComboBox(numberfunctions);
			numberCombo.setFont(new Font("verdana", Font.PLAIN, 9));
			numberCombo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			numberCombo.setBounds(20, 0, 170, 20);
			panel1.add(numberCombo);			
	
			JLabel lbl = new JLabel("عددی");
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setBounds(200, 0, 110, 20);
			panel1.add(lbl);
			
			panel.add(panel1);
		}	
		
		{
			JPanel panel2 = new JPanel();
			panel2.setLayout(null);
			panel2.setSize(320, 20);
				
			enumCombo = new JComboBox(enumfunctions);
			enumCombo.setFont(new Font("verdana", Font.PLAIN, 9));
			enumCombo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			enumCombo.setBounds(20, 0, 170, 20);
			panel2.add(enumCombo);			
	
			JLabel lbl = new JLabel("شمارشی");
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setBounds(200, 0, 110, 20);
			panel2.add(lbl);
			
			panel.add(panel2);
		}	

		{
			JPanel panel3 = new JPanel();
			panel3.setLayout(null);
			panel3.setSize(320, 20);
				
			textCombo = new JComboBox(textfunctions);
			textCombo.setFont(new Font("verdana", Font.PLAIN, 9));
			textCombo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			textCombo.setBounds(20, 0, 170, 20);
			panel3.add(textCombo);			
	
			JLabel lbl = new JLabel("متنی");
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setBounds(200, 0, 110, 20);
			panel3.add(lbl);
			
			panel.add(panel3);
		}	
		
		{
			JPanel panel4 = new JPanel();
			panel4.setLayout(null);
			panel4.setSize(320, 20);
				
			ontoCombo = new JComboBox(ontofunctions);
			ontoCombo.setFont(new Font("verdana", Font.PLAIN, 9));
			ontoCombo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			ontoCombo.setBounds(20, 0, 170, 20);
			panel4.add(ontoCombo);			
	
			JLabel lbl = new JLabel("نمونه هستی شناسی");
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setBounds(200, 0, 110, 20);
			panel4.add(lbl);
			
			panel.add(panel4);
		}	
		
		{
			JPanel panel5 = new JPanel();
			panel5.setLayout(null);
			panel5.setSize(320, 20);
				
			strCombo = new JComboBox(stringfunctions);
			strCombo.setFont(new Font("verdana", Font.PLAIN, 9));
			strCombo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			strCombo.setBounds(20, 0, 170, 20);
			panel5.add(strCombo);			
	
			JLabel lbl = new JLabel("رشته ای");
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			lbl.setBounds(200, 0, 110, 20);
			panel5.add(lbl);
			
			panel.add(panel5);
		}			
		
		{
			JPanel panel6 = new JPanel();
			panel6.setLayout(null);
			panel6.setSize(320, 20);
			
			panel.add(panel6);
			
			cbOntologyStrucutre = new JCheckBox("در نظر گرفتن ساختار هستی شناسی پایگاه مورد");
			cbOntologyStrucutre.setFont(new Font("Tahoma", Font.PLAIN, 11));
			cbOntologyStrucutre.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			cbOntologyStrucutre.setHorizontalAlignment(SwingConstants.RIGHT);
			cbOntologyStrucutre.setBounds(6, 0, 308, 23);
			panel6.add(cbOntologyStrucutre);
		}	
		
		{
			JPanel pnlMain_3_1 = new JPanel();
			pnlMain_3_1.setLayout(null);
			pnlMain_3_1.setBorder(new LineBorder(Color.GRAY));
			pnlMain_3_1.setBounds(125, 250, 320, 75);
			contentPanel.add(pnlMain_3_1);
			{
				JLabel lblNewLabel_3 = new JLabel("K");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel_3.setBounds(116, 45, 29, 14);
				pnlMain_3_1.add(lblNewLabel_3);
			}
			{
				JSpinner spinner = new JSpinner(k = new SpinnerNumberModel(3,1,100,1));
				spinner.setBounds(144, 42, 51, 20);
				pnlMain_3_1.add(spinner);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("تعداد نزدیک ترین همسایه");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel_4.setBounds(96, 11, 135, 14);
				pnlMain_3_1.add(lblNewLabel_4);
			}
		}
		{
			JPanel panel_1_1 = new JPanel();
			panel_1_1.setLayout(null);
			panel_1_1.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.DARK_GRAY));
			panel_1_1.setBounds(125, 29, 320, 20);
			contentPanel.add(panel_1_1);
			{
				JLabel lblNewLabel_1_3_1 = new JLabel("تابع مشابهت");
				lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel_1_3_1.setBounds(34, 0, 135, 20);
				panel_1_1.add(lblNewLabel_1_3_1);
			}
			{
				JLabel lblNewLabel_2_1 = new JLabel("ویژگی");
				lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
				lblNewLabel_2_1.setBounds(200, 0, 115, 20);
				panel_1_1.add(lblNewLabel_2_1);
			}
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("  انصراف  ");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelSimilarity();
					}
				});
				okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			
			JButton okButton = new JButton("  ذخیره  ");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					saveSimilarity();
				}
			});
			okButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
		}
		{
			JPanel topPane = new JPanel();
			topPane.setBorder(new LineBorder(new Color(0, 0, 0)));
			getContentPane().add(topPane, BorderLayout.NORTH);
			topPane.setLayout(new BorderLayout(0, 0));
			
			JLabel label_1 = new JLabel("");
			label_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			label_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\banner.jpg"));
			topPane.add(label_1);
		}
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - this.getWidth()) / 2,
			(screenSize.height - this.getHeight()) / 2, 
			getWidth(),
			getHeight());	
		
		sim = CBRCaseFunctions.ReadSimilarityFromFile();
		
		InitDialogWithSimilarity();
		
	}
	
	void InitDialogWithSimilarity() {
		numberCombo.setSelectedItem(sim.Age.Function);
		enumCombo.setSelectedItem(sim.Limpness.Function);
		textCombo.setSelectedItem(sim.DisadvantageOf.Function);
		strCombo.setSelectedItem(sim.Gender.Function);
		if(sim.Disease.Function.indexOf("Ont")>=0) ontoCombo.setSelectedItem(sim.Disease.Function);
		else ontoCombo.setSelectedItem("Text");
		if(sim.OntologicalStructure.Function.indexOf("OntologicalStructure")>=0) cbOntologyStrucutre.setSelected(true);
		else cbOntologyStrucutre.setSelected(false);		
		setK(sim.K);
	}
	
	void cancelSimilarity()
	{
		this.setVisible(false);
	}
	
	void saveSimilarity()
	{
		CBRCaseFunctions.InitSimilarityConfig(sim, numberCombo.getSelectedItem().toString(), enumCombo.getSelectedItem().toString(), textCombo.getSelectedItem().toString(), ontoCombo.getSelectedItem().toString(), strCombo.getSelectedItem().toString(),cbOntologyStrucutre.isSelected(), getK());
		CBRCaseFunctions.SaveSimilarityToFile(sim);
		this.setVisible(false);
	}	
	
	public int getK()
	{
		return k.getNumber().intValue();
	}	
	
	public void setK(int k) {
		Number newValue = new Integer(k);
		this.k.setValue(newValue);
	}
}

