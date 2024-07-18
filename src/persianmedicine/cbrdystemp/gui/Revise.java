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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import jcolibri.cbrcore.CBRCase;
import jcolibri.datatypes.Text;
import jcolibri.method.retrieve.RetrievalResult;
import persianmedicine.cbrdystemp.DystempDescription;
import persianmedicine.cbrdystemp.DystempSolution;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListModel;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;

public class Revise extends JDialog {

	
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	
	public CBRCase _case;

	JLabel lblHeader;
	JPanel panel1,panel2;
	
	JComboBox cbDiagnosis ;
	
	JList listTreatments,listAllTreatments;
	DefaultListModel<String> modelTreatments,modelAllTreatments;	
	
	ArrayList<RetrievalResult> cases;
	int currentCase;	
	private JTextField txtTreatment;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Revise dialog = new Revise(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isExistsInTreatments(String s) {
		boolean b=false;
        for(int i = 0; i< modelTreatments.getSize();i++){
            if(modelTreatments.getElementAt(i).equals(s)) {
            	b=true;
            	break;
            }
        }		
        return b;
	}
	
	public void AddToTreatmentsList(String s) {
		if (isExistsInTreatments(s)) listTreatments.setSelectedValue(s,true);
		else modelTreatments.addElement(s);
	}

	/**
	 * Create the dialog.
	 */
	public void InitListAllTreatments(String dystemperament) {
		modelTreatments.removeAllElements();
		modelAllTreatments.removeAllElements();
		if (dystemperament.indexOf("سوء_مزاج_سوداوي")>=0) {
			modelAllTreatments.addElement("گوشت_مرغ_و_نخوداب");
			modelAllTreatments.addElement("نخوداب_مغز_قرطم");
			modelAllTreatments.addElement("مرباي_به");
			modelAllTreatments.addElement("طبيخ_فودنج_و_عسل");
			modelAllTreatments.addElement("سکنجبين_سفرجلي");
			modelAllTreatments.addElement("شربت_بادرنجبويه(صبح_ها)");
			modelAllTreatments.addElement("گلقند_با_انيسون_و_باديان");
			modelAllTreatments.addElement("ماءالجبن_و_افتيمون");
			modelAllTreatments.addElement("حمام_مرطب(اگر_سودا_غليظ_باشد)");
			modelAllTreatments.addElement("منضج_سودا_و_مسهل_سودا");
			modelAllTreatments.addElement("مسهل_مطبوخ_افتيمون");
			modelAllTreatments.addElement("فصد_وريد_اسليم_و_باسليق_از_دست_چپ");
			modelAllTreatments.addElement("تنقيه_به_مسهلات_سپس_اشربه_و_اغذيه_و_ادهان_موافقه");
		}
		if (dystemperament.indexOf("سوء_مزاج_بلغمي")>=0) {
			
		}
		if (dystemperament.indexOf("سوء_مزاج_گرم_و_تر_مادي")>=0) {
			
		}
		if (dystemperament.indexOf("سوء_مزاج_صفراوي")>=0) {
			
		}
		if (dystemperament.indexOf("سوء_مزاج_سرد_ساده")>=0) {
			
		}
		
		if (dystemperament.indexOf("سوء_مزاج_گرم_ساده")>=0) {
			
		}
		if (dystemperament.indexOf("سوء_مزاج_خشک_ساده")>=0) {
			
		}
		if (dystemperament.indexOf("سوء_مزاج_تر_ساده")>=0) {
			
		}
		if (dystemperament.indexOf("سوء_مزاج_گرم_و_تر_ساده")>=0) {
			
		}
		if (dystemperament.indexOf("سوء_مزاج_گرم_و_خشک_ساده")>=0) {
			
		}
		if (dystemperament.indexOf("سوء_مزاج_سرد_و_تر_ساده")>=0) {
			
		}
		if (dystemperament.indexOf("سوء_مزاج_سرد_و_خشک_ساده")>=0) {
			
		}
	}
	
	public Revise(JFrame main) {
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
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u062A\u0634\u062E\u06CC\u0635 \u0648 \u062A\u062F\u0627\u0628\u06CC\u0631", TitledBorder.RIGHT, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), new Color(0, 0, 0)));
		panel.setBounds(26, 296, 467, 264);
		panel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(null);
		scrollPane_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane_1.setBounds(5, 82, 212, 182);
		panel.add(scrollPane_1);
		
		modelTreatments = new DefaultListModel<String>();
		modelAllTreatments = new DefaultListModel<String>();
		
		listTreatments = new JList(modelTreatments);
		listTreatments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTreatments.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listTreatments.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		listTreatments.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u062A\u062F\u0627\u0628\u06CC\u0631 \u0627\u0646\u062A\u062E\u0627\u0628 \u0634\u062F\u0647", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), new Color(0, 0, 0)));
		listTreatments.setBackground(SystemColor.menu);
		scrollPane_1.setViewportView(listTreatments);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setViewportBorder(null);
		scrollPane_1_1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		scrollPane_1_1.setBounds(250, 82, 212, 182);
		panel.add(scrollPane_1_1);
		
		listAllTreatments = new JList(modelAllTreatments);
		listAllTreatments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listAllTreatments.setFont(new Font("Tahoma", Font.PLAIN, 11));
		listAllTreatments.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		listAllTreatments.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u0641\u0647\u0631\u0633\u062A \u062A\u062F\u0627\u0628\u06CC\u0631", TitledBorder.LEADING, TitledBorder.TOP, new Font("Tahoma", Font.PLAIN, 11), new Color(0, 0, 0)));
		listAllTreatments.setBackground(SystemColor.menu);
		scrollPane_1_1.setViewportView(listAllTreatments);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("تشخیص");
		lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2_1.setForeground(Color.BLACK);
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_2_1.setBounds(371, 26, 86, 14);
		panel.add(lblNewLabel_1_2_1);
		
		cbDiagnosis = new JComboBox();
		cbDiagnosis.setModel(new DefaultComboBoxModel(new String[] {"سوء_مزاج_ندارد", "سوء_مزاج_سوداوي", "سوء_مزاج_بلغمي", "سوء_مزاج_گرم_و_تر_مادي", "سوء_مزاج_صفراوي", "سوء_مزاج_سرد_ساده", "سوء_مزاج_گرم_ساده", "سوء_مزاج_خشک_ساده", "سوء_مزاج_تر_ساده", "سوء_مزاج_گرم_و_تر_ساده", "سوء_مزاج_گرم_و_خشک_ساده", "سوء_مزاج_سرد_و_تر_ساده", "سوء_مزاج_سرد_و_خشک_ساده"}));
		cbDiagnosis.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cbDiagnosis.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		cbDiagnosis.setBounds(197, 23, 164, 20);
		cbDiagnosis.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        InitListAllTreatments(cbDiagnosis.getSelectedItem().toString());
		    }
		});
		panel.add(cbDiagnosis);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddToTreatmentsList(listAllTreatments.getSelectedValue().toString());
			}
		});
		btnNewButton.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton.setBounds(222, 106, 20, 20);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listTreatments.getSelectedIndex();
			    if (index != -1) {
			    	listTreatments.remove(index);			
			    }				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\remove.png"));
		btnNewButton_1.setBounds(222, 137, 20, 20);
		panel.add(btnNewButton_1);
		
		txtTreatment = new JTextField();
		txtTreatment.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		txtTreatment.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtTreatment.setBounds(28, 60, 189, 20);
		panel.add(txtTreatment);
		txtTreatment.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = txtTreatment.getText().trim();
				str = str.replace(" ", "_");
				AddToTreatmentsList(str);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon("D:\\PHD\\Thesis\\eclipse-workspace\\CBRDystemp\\files\\add.png"));
		btnNewButton_2.setBounds(5, 60, 20, 20);
		panel.add(btnNewButton_2);

		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton(" تایید  ");
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
		cbDiagnosis.setSelectedItem(sol.dystemperament);
		
	}
	
	public String GetFromTreatmentsList() {
		String s = "";
        for(int i = 0; i< modelTreatments.getSize();i++){
            	if (s.length()>0) s = s + ",";
            	s = s + modelTreatments.getElementAt(i).toString();
        }			
		return s;
	}
	
	void next()
	{
		((DystempSolution)_case.getSolution()).setDystemperament(cbDiagnosis.getSelectedItem().toString());
		String s =GetFromTreatmentsList();
		if (s.length()>0) ((DystempSolution)_case.getSolution()).setTreatments(new Text(s));									
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
			result.setBounds(0, 0, 365, 20);
			result.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			result.setFont(new Font("Tahoma", Font.PLAIN, 11));
			this.add(result);
		}		
	}
}
