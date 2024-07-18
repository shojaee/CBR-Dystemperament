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
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import datatypes.FuzzyAge;
import datatypes.FuzzyBMI;
import functions.CBRCaseFunctions;
import jcolibri.cbrcore.CBRCase;
import jcolibri.datatypes.Text;
import jcolibri.method.retrieve.RetrievalResult;
import persianmedicine.cbrdystemp.DystempDescription;
import persianmedicine.cbrdystemp.DystempDescription.OL1;
import persianmedicine.cbrdystemp.DystempDescription.OL2;
import persianmedicine.cbrdystemp.DystempDescription.OL3;
import persianmedicine.cbrdystemp.DystempSolution;

import javax.swing.JScrollPane;


public class ResultDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	
	JLabel lblHeader;
	JPanel panel1,panel2;
	
	public ArrayList<RetrievalResult> cases;
	public int currentCase;	

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		try {
			ResultDialog dialog = new ResultDialog(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public ResultDialog(JFrame main) {
		super(main,true);	
		
		getContentPane().setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setBounds(100, 100, 1015, 745);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(262, 11, 463, 28);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("<< \u0642\u0628\u0644\u06CC ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentCase = (currentCase+cases.size()-1) % cases.size();
				showCase();			
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setBounds(375, 0, 85, 25);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton(" بعدی >>");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentCase = (currentCase+1) % cases.size();
				showCase();				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton_1.setBounds(0, 0, 85, 28);
		panel.add(btnNewButton_1);
		
		lblHeader = new JLabel("New label");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblHeader.setBounds(90, 7, 284, 14);
		panel.add(lblHeader);
		
		panel2 = new JPanel();
		panel2.setBounds(26, 60, 470, 500);
		panel2.setLayout(new GridLayout(20, 1, 0, 5));	
		contentPanel.add(panel2);
		
		panel1 = new JPanel();
		panel1.setBounds(506, 60, 470, 500);
		contentPanel.add(panel1);
		panel1.setLayout(new GridLayout(20, 1, 0, 5));

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(" بازگشت به پرونده بیمار ");
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
	
	void next()
	{
		this.setVisible(false);
	}
	
	
	public void showCases(Collection<RetrievalResult> eval, Collection<CBRCase> selected)
	{
		cases = new ArrayList<RetrievalResult>();
		for(RetrievalResult rr: eval)
			if(selected.contains(rr.get_case()))
				cases.add(rr);
		currentCase = 0;
		showCase();
	}
	
	void showCase()
	{
						
		panel1.removeAll();
		panel1.revalidate();
		panel1.repaint();
		
		panel2.removeAll();
		panel2.revalidate();
		panel2.repaint();
		
		RetrievalResult rr = cases.get(currentCase);
		double sim = (double)Math.round(rr.getEval()*1000)/1000;
		
		CBRCase _case = rr.get_case();
		this.lblHeader.setText(_case.getID().toString()+" -> "+sim+" ("+(currentCase+1)+"/"+cases.size()+")");
		
		DystempDescription desc = (DystempDescription) _case.getDescription();
		
		int i=0;
		JPanel pnl;
		
		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("اطلاعات فردی",Color.black));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.ageNumber!=null) {pnl.add(new ResultPanel("سن:",desc.ageNumber.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.bmiNumber!=null) {pnl.add(new ResultPanel("شاخص توده بدنی:",desc.bmiNumber.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.job!=null) {pnl.add(new ResultPanel("شغل:",desc.job));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.gender!=null) {pnl.add(new ResultPanel("جنس:",desc.gender));i++;}

		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("سته ضروری",Color.black));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.sleepingWakefulness!=null) {pnl.add(new ResultPanel("خواب و بیداری:",desc.sleepingWakefulness.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.custodyDisposal!=null) {pnl.add(new ResultPanel("احتباس و استفراغ:",desc.custodyDisposal.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.food!=null) {pnl.add(new ResultPanel("غذا و نوشیدنی:",desc.food.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.climate!=null) {pnl.add(new ResultPanel("هوا و سکونت:",desc.climate.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.physicalActivity!=null) {pnl.add(new ResultPanel("حرکت و سکون:",desc.physicalActivity.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.mentalHealth!=null) {pnl.add(new ResultPanel("اعراض نفسانی:",desc.mentalHealth.toString()));i++;}
		
		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("معاینات ظاهری",Color.black));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.faceColor !=null) {pnl.add(new ResultPanel("رنگ صورت:",desc.faceColor.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.limpness!=null) {pnl.add(new ResultPanel("ترهل بدن:",desc.limpness.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.bodyState!=null) {pnl.add(new ResultPanel("وضعیت بدن:",desc.bodyState.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.saliva!=null) {pnl.add(new ResultPanel("آب دهان:",desc.saliva.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.salivation!=null) {pnl.add(new ResultPanel("اهراق ریق:",desc.salivation.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.salivationTime!=null) {pnl.add(new ResultPanel("زمان اهراق ریق:",desc.salivationTime.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.mouthTaste!=null) {pnl.add(new ResultPanel("طعم دهان:",desc.mouthTaste.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.tongueColor!=null) {pnl.add(new ResultPanel("رنگ زبان:",desc.tongueColor.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.tongueWettish!=null) {pnl.add(new ResultPanel("رطوبت زبان:",desc.tongueWettish.toString()));i++;}
		
		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("علائم گوارشی",Color.black));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.qasayan !=null) {pnl.add(new ResultPanel("غثیان:",desc.qasayan.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.qasayanTime!=null) {pnl.add(new ResultPanel("زمان غثیان:",desc.qasayanTime.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.stomachHeaviness!=null) {pnl.add(new ResultPanel("ثقل معده:",desc.stomachHeaviness.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.heavinessTime!=null) {pnl.add(new ResultPanel("زمان ثقل معده:",desc.heavinessTime.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.gastricEructation!=null) {pnl.add(new ResultPanel("جشاء:",desc.gastricEructation.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.flatulence!=null) {pnl.add(new ResultPanel("نفخ:",desc.flatulence.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.gastralgia!=null) {pnl.add(new ResultPanel("وجع معده:",desc.gastralgia.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.gastralgiaTime!=null) {pnl.add(new ResultPanel("زمان وجع معده:",desc.gastralgiaTime.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.foodPutrefactionFreq!=null) {pnl.add(new ResultPanel("دفعات نموست غذا:",desc.foodPutrefactionFreq.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.heartburn!=null) {pnl.add(new ResultPanel("حرقت معده:",desc.heartburn.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.hiccup!=null) {pnl.add(new ResultPanel("فواق:",desc.hiccup.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.acerbate!=null) {pnl.add(new ResultPanel("جشاء حامض:",desc.acerbate.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.takhazkhoz!=null) {pnl.add(new ResultPanel("تخضخض:",desc.takhazkhoz.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.digestionPowerNumber!=null) {pnl.add(new ResultPanel("قدرت هضم(0-10):",desc.digestionPowerNumber.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.foodTransitionSpeedNumber!=null) {pnl.add(new ResultPanel("سرعت انحدار(0-10):",desc.foodTransitionSpeedNumber.toString()));i++;}
		
		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("تمایل/انتفاع/تضرر/تنفر",Color.black));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.tendencyTo !=null) {pnl.add(new ResultPanel("تمایل به:",desc.tendencyTo.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.benefitFrom!=null) {pnl.add(new ResultPanel("انتفاع از:",desc.benefitFrom.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.disadvantageOf!=null) {pnl.add(new ResultPanel("تضرر از:",desc.disadvantageOf.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.hatredOf!=null) {pnl.add(new ResultPanel("تنفر از:",desc.hatredOf.toString()));i++;}

		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("وضعیت دفع",Color.black));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.stoolConsistency !=null) {pnl.add(new ResultPanel("قوام مدفوع:",desc.stoolConsistency.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.defecationPeriod!=null) {pnl.add(new ResultPanel("اجابت مزاج:",desc.defecationPeriod.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.stoolType!=null) {pnl.add(new ResultPanel("نوع:",desc.stoolType.toString()));i++;}
		
		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("اشتها و عطش",Color.black));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.thirstinessLevelNumber !=null) {pnl.add(new ResultPanel("میزان عطش(0-10):",desc.thirstinessLevelNumber.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.appetiteLevelNumber!=null) {pnl.add(new ResultPanel("میزان اشتها(0-10):",desc.appetiteLevelNumber.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.thirstinessReliefType!=null) {pnl.add(new ResultPanel("نحوه تسکین تشنگی:",desc.thirstinessReliefType.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.eatingMuchTiny!=null) {pnl.add(new ResultPanel("ریزه خواری:",desc.eatingMuchTiny.toString()));i++;}		
		
		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("وضعیت نبض",Color.black));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.pulseRateNumber !=null) {pnl.add(new ResultPanel("تواتر نبض(0-10):",desc.pulseRateNumber.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.pulsePowerNumber!=null) {pnl.add(new ResultPanel("قدرت نبض(0-10):",desc.pulsePowerNumber.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.pulseSpeedNumber!=null) {pnl.add(new ResultPanel("سرعت نبض(0-10):",desc.pulseSpeedNumber.toString()));i++;}
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.pulseConsistencyNumber!=null) {pnl.add(new ResultPanel("قوام نبض(0-10):",desc.pulseConsistencyNumber.toString()));i++;}		
		
		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("بیماری  مشارک",Color.black));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(desc.disease !=null) {pnl.add(new ResultPanel("بیماری:",desc.disease.toString()));i++;}
		
		DystempSolution sol = (DystempSolution) _case.getSolution();
		
		if (i<20) pnl = panel1; else pnl = panel2;
		pnl.add(new ResultPanel("تشخیص",	Color.RED));i++;
		if (i<20) pnl = panel1; else pnl = panel2;
		if(sol.dystemperament !=null) {pnl.add(new ResultPanel("تشخیص:",sol.dystemperament.toString()));i++;}		
		
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
			result.setBounds(0, 0, 365, 20);
			result.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			result.setFont(new Font("Tahoma", Font.PLAIN, 11));
			this.add(result);
		}		
	}
}
