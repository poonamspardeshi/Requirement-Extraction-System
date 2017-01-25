package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.DefineBusinessProcessController;
import composite.Phrase;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;


public class CreateActionDialog extends JDialog {
	private JLabel lblVerb;
	private JLabel lblNoun;
	private JTextField txtVerb;
	private JTextField txtNoun;
	private JLabel lblSentance;
	private JTextField txtSentance;
	private JLabel lblSequenceNumber;
	private JComboBox cbSequenceNumber;
	private JLabel lblSequenceNo;
	private JLabel lblBusinessProcess;
	private JComboBox<String> cbBusinessProcess;
	private JLabel lblStep;
	private JComboBox<String> cbStep;


	/**
	 * Create the dialog.
	 */
	public CreateActionDialog(RETGUI parent,ArrayList<String> phrase) {
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		{
			{
				{
					DefineBusinessProcessController dbpController= new DefineBusinessProcessController();
					
					txtVerb = new JTextField(phrase.get(0));
					txtVerb.setBounds(171, 44, 86, 22);
					getContentPane().add(txtVerb);
					lblVerb = new JLabel("Verb : ");
					lblVerb.setBounds(108, 43, 51, 25);
					getContentPane().add(lblVerb);
				
					txtNoun = new JTextField(phrase.get(1));
					txtNoun.setBounds(171, 79, 86, 22);
					getContentPane().add(txtNoun);
					lblNoun = new JLabel("Noun : ");
					lblNoun.setBounds(104, 79, 64, 22);
					getContentPane().add(lblNoun);
					

					txtSentance = new JTextField(dbpController.getSentance(phrase.get(0), phrase.get(1)));
					
					//txtSentance = new JTextField("");
					txtSentance.setBounds(171, 114, 216, 22);
					getContentPane().add(txtSentance);

					lblSentance=new JLabel("Sentence : ");
					lblSentance.setBounds(80, 114, 71, 22);
					getContentPane().add(lblSentance);

					cbSequenceNumber = new JComboBox();
					cbSequenceNumber.setBounds(171, 146, 86, 22);
					getContentPane().add(cbSequenceNumber);

					lblSequenceNo = new JLabel("Sequence No : ");
					lblSequenceNo.setBounds(57, 146, 93, 16);
					getContentPane().add(lblSequenceNo);

					lblBusinessProcess = new JLabel("Business Process :");
					lblBusinessProcess.setBounds(34, 178, 121, 28);
					getContentPane().add(lblBusinessProcess);

					cbBusinessProcess = new JComboBox<String>();
					cbBusinessProcess.setBounds(171, 178, 216, 22);
					getContentPane().add(cbBusinessProcess);			

					lblStep = new JLabel("Step :");
					lblStep.setBounds(108, 207, 51, 26);
					getContentPane().add(lblStep);

					cbStep = new JComboBox<String>();
					cbStep.setBounds(171, 210, 216, 22);
					getContentPane().add(cbStep);					


					JButton createButton = new JButton("Create Action");
					createButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						}
					});
					createButton.setBounds(164, 242, 121, 22);
					getContentPane().add(createButton);
					createButton.setActionCommand("OK");
					getRootPane().setDefaultButton(createButton);


					

					List<String> businessProcesses=dbpController.getBusinessProcesses();

					for(int i=0; i < businessProcesses.size(); i++)
						cbBusinessProcess.addItem(i+"");		
					
					
		             for(int i=0; i < businessProcesses.size(); i++)
                     {
                            if(dbpController.getSteps(businessProcesses.get(i)).size()>0)
                                   {
                                          cbBusinessProcess.setSelectedItem(i+"");
                                         break;
                                   }                                             
                     }

					List<String> steps = dbpController.getSteps((String)cbBusinessProcess.getSelectedItem()+".-1.-1");

					for(int i=0; i < steps.size(); i++)
						cbStep.addItem(i+"");	
					
					

					List<String> actions = dbpController.getActions((String)cbBusinessProcess.getSelectedItem()+"."+
																	(String)cbStep.getSelectedItem()+".-1");
					
					for(int i = 1; i <= actions.size()+1; i++)
						cbSequenceNumber.addItem(i);

					cbSequenceNumber.setSelectedIndex(actions.size());					

					createButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							
							
							if(isBoxValid())
							{
							

								DefineBusinessProcessController dbpController= new DefineBusinessProcessController();

								dbpController.addRequirementComponent(txtVerb.getText(), txtNoun.getText(), txtSentance.getText(), cbBusinessProcess.getSelectedItem() +"."+(String)cbStep.getSelectedItem()+".-1", cbSequenceNumber.getSelectedIndex());

								parent.refreshTree();

								dispose();
							}
							else
							{
								
								JOptionPane.showMessageDialog(new JFrame(), "Please add step before adding Action. ", "Dialog",
								        JOptionPane.ERROR_MESSAGE);
								
							}


						}
					});

					cbBusinessProcess.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {

							String selectedBusinessProcess =(String) cbBusinessProcess.getSelectedItem();

							List<String> steps = dbpController.getSteps(selectedBusinessProcess+".-1.-1");

							cbStep.removeAllItems();

							for(int i = 0; i < steps.size(); i++)
								cbStep.addItem(i+"");															
						}
					});		

					cbStep.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {

							if(cbStep.getItemCount() >=1)
							{
								String selectedStep = (String)cbStep.getSelectedItem();
								
								List<String> actions = dbpController.getActions(cbBusinessProcess.getSelectedItem()+"."+selectedStep+".-1");


								cbSequenceNumber.removeAllItems();

								for(int i = 1; i <= actions.size()+1; i++)
									cbSequenceNumber.addItem(i);

								cbSequenceNumber.setSelectedIndex(actions.size());	
							}
						}
					});

				}
			}
		}
	}
	
	private boolean isBoxValid(){
		if(cbStep==null||cbStep.getSelectedItem()==null)
			return false;
		
		if(txtVerb.getText()==null || txtVerb.getText().equals(""))
			return false;
		
		if(txtNoun.getText()==null || txtNoun.getText().equals(""))
			return false;
		
		
		
		if(cbSequenceNumber==null||cbSequenceNumber.getItemCount()==0)
			return false;
		
		return true;
		
	}
}
