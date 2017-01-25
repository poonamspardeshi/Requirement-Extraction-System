package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import Controller.DefineBusinessProcessController;
import Controller.EditBusinessProcessesController;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class EditStepDialog extends JDialog {
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
	private EditBusinessProcessesController editBPController;
	private DefineBusinessProcessController dbpController;

	
	
	/**
	 * Create the dialog.
	 */
	public EditStepDialog(RETGUI parent,String stepId) {
		setBounds(100, 100, 450, 324);
		getContentPane().setLayout(null);
		{
			{
				
				
				{
					txtVerb = new JTextField();
					txtVerb.setBounds(171, 44, 86, 22);
					getContentPane().add(txtVerb);
					lblVerb = new JLabel("Verb : ");
					lblVerb.setBounds(104, 41, 71, 28);
					getContentPane().add(lblVerb);
				
					txtNoun = new JTextField();
					txtNoun.setBounds(171, 79, 86, 22);
					getContentPane().add(txtNoun);
					lblNoun = new JLabel("Noun : ");
					lblNoun.setBounds(104, 82, 64, 22);
					getContentPane().add(lblNoun);
					txtSentance = new JTextField();
					txtSentance.setBounds(171, 114, 216, 22);
					getContentPane().add(txtSentance);
					
					lblSentance=new JLabel("Sentence : ");
					lblSentance.setBounds(80, 114, 71, 22);
					getContentPane().add(lblSentance);
					
					cbSequenceNumber = new JComboBox();
					cbSequenceNumber.setBounds(171, 146, 86, 22);
					getContentPane().add(cbSequenceNumber);
					
					lblSequenceNo = new JLabel("Sequence No : ");
					lblSequenceNo.setBounds(57, 149, 93, 16);
					getContentPane().add(lblSequenceNo);
					
					lblBusinessProcess = new JLabel("Business Process :");
					lblBusinessProcess.setBounds(34, 178, 121, 28);
					getContentPane().add(lblBusinessProcess);
					
					cbBusinessProcess = new JComboBox<String>();
					cbBusinessProcess.setBounds(171, 181, 216, 22);
					getContentPane().add(cbBusinessProcess);					
				
						JButton saveButton = new JButton("Save");
						saveButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
							}
						});
						saveButton.setBounds(171, 216, 93, 22);
						getContentPane().add(saveButton);
						saveButton.setActionCommand("OK");
						getRootPane().setDefaultButton(saveButton);
					
									
					dbpController= new DefineBusinessProcessController();
					editBPController=new EditBusinessProcessesController();
					
					String[] phraseInfo = dbpController.getComponentPhraseInfo(stepId);
					txtVerb.setText(phraseInfo[0]);
					txtNoun.setText(phraseInfo[1]);
					txtSentance.setText(phraseInfo[2]);
										
					List<String> businessProcesses=dbpController.getBusinessProcesses();
					for(int i=0; i < (businessProcesses.size()); i++)
						cbBusinessProcess.addItem(""+i);
					
					String[] stepIdArr = stepId.split("\\.");
					int parentBpIndex = Integer.parseInt(stepIdArr[0]);
					int stepIndex =  Integer.parseInt(stepIdArr[1]);
					
					cbBusinessProcess.setSelectedIndex(parentBpIndex);
					
			/*		BusinessProcess parentBP=(BusinessProcess) step.getParent();
					int tempIndexBp=Repository.getInstance().getBusinessProcessList().indexOf(parentBP);	
					cbBusinessProcess.setSelectedItem(tempIndexBp);		*/
					
					for(int i = 1; i <= dbpController.getSteps(parentBpIndex+".-1.-1").size(); i++)
						cbSequenceNumber.addItem(i);
					
					cbSequenceNumber.setSelectedIndex(stepIndex);
					
			/*		int tempIndexStep=((BusinessProcess)(step.getParent())).getStepsList().indexOf(step);
					cbSequenceNumber.setSelectedIndex(tempIndexStep);	*/
						
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
																					
							editBPController.editCompositeComponent(stepId, cbBusinessProcess.getSelectedIndex()+".-1.-1", txtVerb.getText(), txtNoun.getText(), txtSentance.getText(), cbSequenceNumber.getSelectedIndex());
						//	editBPController.editStep(step, (BusinessProcess)cbBusinessProcess.getSelectedItem(), txtVerb.getText(), txtNoun.getText(), txtSentance.getText(), cbSequenceNumber.getSelectedIndex());

							parent.refreshTree();

							dispose();
							
						}
					});
					
					
					cbBusinessProcess.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							
						/*	BusinessProcess selectedBusinessProcess = (BusinessProcess)cbBusinessProcess.getSelectedItem();							
							cbSequenceNumber.removeAllItems();							
							for(int i = 1; i <= selectedBusinessProcess.size()+1; i++)
								cbSequenceNumber.addItem(i);							
							cbSequenceNumber.setSelectedIndex(selectedBusinessProcess.size());			*/
							
							List<String> steps = dbpController.getSteps(cbBusinessProcess.getSelectedIndex()+".-1.-1");
							cbSequenceNumber.removeAllItems();
							for(int i = 1; i <= steps.size()+1; i++)
								cbSequenceNumber.addItem(i);
							cbSequenceNumber.setSelectedIndex(steps.size());
							
						}
					});										
					
				}
			}
		}
	}
}