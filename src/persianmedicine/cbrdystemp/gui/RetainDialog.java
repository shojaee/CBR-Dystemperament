package persianmedicine.cbrdystemp.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import jcolibri.cbrcore.CBRCase;
import jcolibri.datatypes.Instance;
import jcolibri.datatypes.Text;
/*import jcolibri.examples.TravelRecommender.TravelDescription;
import jcolibri.examples.TravelRecommender.TravelSolution;
*/
import persianmedicine.cbrdystemp.DystempDescription;
import persianmedicine.cbrdystemp.DystempSolution;

public class RetainDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	public CBRCase _case;
	JPanel panel1,panel2;
	
	JLabel lblDiagnosis,lblTreatments;
	ArrayList<CBRCase> casesToRetain;
	JTextField idEditor;
	JButton setId;
	JCheckBox saveCheck;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RetainDialog dialog = new RetainDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RetainDialog(JFrame main) {
		super(main,true);	
		
		getContentPane().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setBounds(100, 100, 1015, 745);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(null);
					
		panel2 = new JPanel();
		panel2.setBounds(26, 10, 470, 275);
		panel2.setLayout(new GridLayout(11, 1, 0, 5));	
		contentPanel.add(panel2);
		
		panel1 = new JPanel();
		panel1.setBounds(506, 10, 470, 550);
		contentPanel.add(panel1);
		panel1.setLayout(new GridLayout(22, 1, 0, 5));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u062A\u0634\u062E\u06CC\u0635 \u0648 \u062A\u062F\u0627\u0628\u06CC\u0631", TitledBorder.RIGHT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), new Color(0, 0, 0)));
		panel.setBounds(26, 296, 470, 208);
		contentPanel.add(panel);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("تشخیص");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1.setBounds(371, 26, 86, 14);
		panel.add(lblNewLabel_1_2_1);
		
		lblDiagnosis = new JLabel("New label");
		lblDiagnosis.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblDiagnosis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDiagnosis.setBounds(188, 26, 184, 14);
		panel.add(lblDiagnosis);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("تدابیر");
		lblNewLabel_1_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_2_1_1.setBounds(371, 61, 86, 14);
		panel.add(lblNewLabel_1_2_1_1);
		
		lblTreatments = new JLabel("");
		lblTreatments.setVerticalAlignment(SwingConstants.TOP);
		lblTreatments.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTreatments.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		lblTreatments.setBounds(10, 86, 447, 111);
		panel.add(lblTreatments);
		
		JPanel defineIdsPanel = new JPanel();
		defineIdsPanel.setBounds(26, 505, 470, 55);
		contentPanel.add(defineIdsPanel);
		defineIdsPanel.setLayout(null);
		
		saveCheck = new JCheckBox("ذخیره مورد با عنوان:");
		saveCheck.setFont(new Font("Tahoma", Font.PLAIN, 11));
		saveCheck.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		saveCheck.setLocation(310, 17);
		saveCheck.setSize(131, 20);
		defineIdsPanel.add(saveCheck);
		saveCheck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				enableSaveCase();
			}
		});
		idEditor = new JTextField(20);
		idEditor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		idEditor.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		idEditor.setLocation(160, 17);
		idEditor.setSize(144, 20);
		defineIdsPanel.add(idEditor);
		setId = new JButton("تایید");
		setId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		setId.setLocation(90, 17);
		setId.setSize(60, 20);
		defineIdsPanel.add(setId);
		
		setId.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setId();
			}
		});		
		enableSaveCase();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("  ادامه  ");
				okButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						next();
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
	
	void enableSaveCase()
	{
		idEditor.setEnabled(saveCheck.isSelected());
		setId.setEnabled(saveCheck.isSelected());
	}
	
	void setId()
	{	
		try {
		DystempDescription desc = (DystempDescription) _case.getDescription();
		desc.setMainConcept( new Instance(idEditor.getText()));
		DystempSolution sol = (DystempSolution) _case.getSolution();
		sol.setMainConcept(new Instance(idEditor.getText()));
		casesToRetain.add(_case);
		
		enableSaveCase();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void showCase(CBRCase newcase)
	{
						
		panel1.removeAll();
		panel1.revalidate();
		panel1.repaint();
		
		panel2.removeAll();
		panel2.revalidate();
		panel2.repaint();
		
		_case = newcase;
		
		DystempDescription desc = (DystempDescription) _case.getDescription();
		
		int i=0;
		JPanel pnl;
		
		if (i<22) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("اطلاعات فردی",Color.black));i++;
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.ageNumber!=null) {pnl.add(new ResultPanel("سن:",desc.ageNumber.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.bmiNumber!=null) {pnl.add(new ResultPanel("شاخص توده بدنی:",desc.bmiNumber.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.job!=null) {pnl.add(new ResultPanel("شغل:",desc.job));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.gender!=null) {pnl.add(new ResultPanel("جنس:",desc.gender));i++;}

		if (i<22) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("سته ضروری",Color.black));i++;
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.sleepingWakefulness!=null) {pnl.add(new ResultPanel("خواب و بیداری:",desc.sleepingWakefulness.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.custodyDisposal!=null) {pnl.add(new ResultPanel("احتباس و استفراغ:",desc.custodyDisposal.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.food!=null) {pnl.add(new ResultPanel("غذا و نوشیدنی:",desc.food.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.climate!=null) {pnl.add(new ResultPanel("هوا و سکونت:",desc.climate.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.physicalActivity!=null) {pnl.add(new ResultPanel("حرکت و سکون:",desc.physicalActivity.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.mentalHealth!=null) {pnl.add(new ResultPanel("اعراض نفسانی:",desc.mentalHealth.toString()));i++;}
		
		if (i<22) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("معاینات ظاهری",Color.black));i++;
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.faceColor !=null) {pnl.add(new ResultPanel("رنگ صورت:",desc.faceColor.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.limpness!=null) {pnl.add(new ResultPanel("ترهل بدن:",desc.limpness.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.bodyState!=null) {pnl.add(new ResultPanel("وضعیت بدن:",desc.bodyState.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.saliva!=null) {pnl.add(new ResultPanel("آب دهان:",desc.saliva.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.salivation!=null) {pnl.add(new ResultPanel("اهراق ریق:",desc.salivation.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.salivationTime!=null) {pnl.add(new ResultPanel("زمان اهراق ریق:",desc.salivationTime.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.mouthTaste!=null) {pnl.add(new ResultPanel("طعم دهان:",desc.mouthTaste.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.tongueColor!=null) {pnl.add(new ResultPanel("رنگ زبان:",desc.tongueColor.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.tongueWettish!=null) {pnl.add(new ResultPanel("رطوبت زبان:",desc.tongueWettish.toString()));i++;}
		
		if (i<22) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("علائم گوارشی",Color.black));i++;
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.qasayan !=null) {pnl.add(new ResultPanel("غثیان:",desc.qasayan.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.qasayanTime!=null) {pnl.add(new ResultPanel("زمان غثیان:",desc.qasayanTime.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.stomachHeaviness!=null) {pnl.add(new ResultPanel("ثقل معده:",desc.stomachHeaviness.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.heavinessTime!=null) {pnl.add(new ResultPanel("زمان ثقل معده:",desc.heavinessTime.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.gastricEructation!=null) {pnl.add(new ResultPanel("جشاء:",desc.gastricEructation.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.flatulence!=null) {pnl.add(new ResultPanel("نفخ:",desc.flatulence.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.gastralgia!=null) {pnl.add(new ResultPanel("وجع معده:",desc.gastralgia.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.gastralgiaTime!=null) {pnl.add(new ResultPanel("زمان وجع معده:",desc.gastralgiaTime.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.foodPutrefactionFreq!=null) {pnl.add(new ResultPanel("دفعات نموست غذا:",desc.foodPutrefactionFreq.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.heartburn!=null) {pnl.add(new ResultPanel("حرقت معده:",desc.heartburn.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.hiccup!=null) {pnl.add(new ResultPanel("فواق:",desc.hiccup.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.acerbate!=null) {pnl.add(new ResultPanel("جشاء حامض:",desc.acerbate.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.takhazkhoz!=null) {pnl.add(new ResultPanel("تخضخض:",desc.takhazkhoz.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.digestionPowerNumber!=null) {pnl.add(new ResultPanel("قدرت هضم(0-10):",desc.digestionPowerNumber.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.foodTransitionSpeedNumber!=null) {pnl.add(new ResultPanel("سرعت انحدار(0-10):",desc.foodTransitionSpeedNumber.toString()));i++;}
		
		if (i<22) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("تمایل/انتفاع/تضرر/تنفر",Color.black));i++;
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.tendencyTo !=null) {pnl.add(new ResultPanel("تمایل به:",desc.tendencyTo.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.benefitFrom!=null) {pnl.add(new ResultPanel("انتفاع از:",desc.benefitFrom.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.disadvantageOf!=null) {pnl.add(new ResultPanel("تضرر از:",desc.disadvantageOf.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.hatredOf!=null) {pnl.add(new ResultPanel("تنفر از:",desc.hatredOf.toString()));i++;}

		if (i<22) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("وضعیت دفع",Color.black));i++;
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.stoolConsistency !=null) {pnl.add(new ResultPanel("قوام مدفوع:",desc.stoolConsistency.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.defecationPeriod!=null) {pnl.add(new ResultPanel("اجابت مزاج:",desc.defecationPeriod.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.stoolType!=null) {pnl.add(new ResultPanel("نوع:",desc.stoolType.toString()));i++;}
		
		if (i<22) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("اشتها و عطش",Color.black));i++;
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.thirstinessLevelNumber !=null) {pnl.add(new ResultPanel("میزان عطش(0-10):",desc.thirstinessLevelNumber.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.appetiteLevelNumber!=null) {pnl.add(new ResultPanel("میزان اشتها(0-10):",desc.appetiteLevelNumber.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.thirstinessReliefType!=null) {pnl.add(new ResultPanel("نحوه تسکین تشنگی:",desc.thirstinessReliefType.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.eatingMuchTiny!=null) {pnl.add(new ResultPanel("ریزه خواری:",desc.eatingMuchTiny.toString()));i++;}		
		
		if (i<22) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("وضعیت نبض",Color.black));i++;
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.pulseRateNumber !=null) {pnl.add(new ResultPanel("تواتر نبض(0-10):",desc.pulseRateNumber.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.pulsePowerNumber!=null) {pnl.add(new ResultPanel("قدرت نبض(0-10):",desc.pulsePowerNumber.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.pulseSpeedNumber!=null) {pnl.add(new ResultPanel("سرعت نبض(0-10):",desc.pulseSpeedNumber.toString()));i++;}
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.pulseConsistencyNumber!=null) {pnl.add(new ResultPanel("قوام نبض(0-10):",desc.pulseConsistencyNumber.toString()));i++;}		
		
		if (i<22) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("بیماری  مشارک",Color.black));i++;
		if (i<22) pnl = panel1; else pnl = panel2;
		if(desc.disease !=null) {pnl.add(new ResultPanel("بیماری:",desc.disease.toString()));i++;}
		
		DystempSolution sol = (DystempSolution) _case.getSolution();
		if(sol.dystemperament !=null) {lblDiagnosis.setText(sol.dystemperament.toString());}	
		if(sol.treatments !=null) {lblTreatments.setText("<html>"+sol.treatments.toString()+"</html>");}
		else lblTreatments.setText("");
	}
	public Collection<CBRCase> getCasestoRetain()
	{
		return casesToRetain;
	}
		
	void next()
	{
							
		this.setVisible(false);
	}	
	private class ResultPanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		
		JLabel result;
		
		public ResultPanel(String l,Color c)
		{
			this.setLayout(null);
			this.setSize(470, 20);
				
			JLabel lbl = new JLabel(l);
			lbl.setFont(new Font("Tahoma", Font.BOLD, 11));
			lbl.setForeground(c);
			lbl.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl.setBounds(370, 0, 95, 20);
			this.add(lbl);
		}			
		
		public ResultPanel(String l,String r)
		{

			this.setLayout(null);
			this.setSize(470, 20);
				
			JLabel lbl = new JLabel(l);
			lbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lbl.setHorizontalAlignment(SwingConstants.RIGHT);
			lbl.setBounds(370, 0, 95, 20);
			this.add(lbl);

			result = new JLabel(r);
			if (l.indexOf("تدابیر")>=0)  result.setBounds(0, 0, 365, 100);
			else result.setBounds(0, 0, 365, 20);
			result.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			result.setFont(new Font("Tahoma", Font.PLAIN, 11));
			this.add(result);
		}		
	}
}
