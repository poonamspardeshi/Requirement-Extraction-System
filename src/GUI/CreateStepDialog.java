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
import composite.Phrase;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class CreateStepDialog extends JDialog {
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


	/**
	 * Create the dialog.
	 */
	public CreateStepDialog(RETGUI parent,ArrayList<String> phrase) {
		setBounds(100, 100, 450, 324);
		getContentPane().setLayout(null);
		{
			{
				
				
				{
					DefineBusinessProcessController dbpController= new DefineBusinessProcessController();
					
					txtVerb = new JTextField(phrase.get(0));
					txtVerb.setBounds(171, 44, 86, 22);
					getContentPane().add(txtVerb);
					lblVerb = new JLabel("Verb : ");
					lblVerb.setBounds(104, 41, 71, 28);
					getContentPane().add(lblVerb);
				
					txtNoun = new JTextField(phrase.get(1));
					txtNoun.setBounds(171, 79, 86, 22);
					getContentPane().add(txtNoun);
					lblNoun = new JLabel("Noun : ");
					lblNoun.setBounds(104, 82, 64, 22);
					getContentPane().add(lblNoun);
					
					
					
					txtSentance = new JTextField(dbpController.getSentance(phrase.get(0), phrase.get(1)));
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
				
						JButton createButton = new JButton("Create Step");
						createButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
							}
						});
						createButton.setBounds(164, 223, 93, 22);
						getContentPane().add(createButton);
						createButton.setActionCommand("OK");
						getRootPane().setDefaultButton(createButton);
					
					// filling BPs ComboBox
					//DefineBusinessProcessController dbpController= new DefineBusinessProcessController();
					List<String> businessProcesses=dbpController.getBusinessProcesses();
					for(int i=0; i < businessProcesses.size(); i++)
						cbBusinessProcess.addItem(""+i);
					
					
			//		 cbBusinessProcess.setSelectedItem(i);
					
					//setting default BP
		             for(int i=0; i < businessProcesses.size(); i++)
                    {
                           if(dbpController.getSteps(businessProcesses.get(i)).size()>0)
                                  {
                                         cbBusinessProcess.setSelectedItem(i);
                                        break;
                                  }                                             
                    }

		             
		             //getting Steps List of a selected BP
		             List<String> steps = dbpController.getSteps((String)cbBusinessProcess.getSelectedItem()+".-1.-1");
					
		             
		         	for(int i=0; i < steps.size()+1; i++)
		         		cbSequenceNumber.addItem(i);	
		         	//	cbSequenceNumber.addItem(steps.get(i));	
		         
		         	//adding one more index to add new step at last position
		         	//cbSequenceNumber.addItem(steps.get(i));	
				
		         	//setting default Step index
					cbSequenceNumber.setSelectedIndex(steps.size());	
					
					
					
						
					createButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							
							DefineBusinessProcessController dbpController= new DefineBusinessProcessController();
							
							dbpController.addRequirementComponent(txtVerb.getText(), txtNoun.getText(), txtSentance.getText(), (String)cbBusinessProcess.getSelectedItem()+".-1.-1", cbSequenceNumber.getSelectedIndex());
							
							parent.refreshTree();

							dispose();
							
						}
					});
					
					
					cbBusinessProcess.addActionListener(new ActionListener() {

						public void actionPerformed(ActionEvent arg) {

							String selectedBusinessProcess =(String) cbBusinessProcess.getSelectedItem();

							List<String> steps = dbpController.getSteps(selectedBusinessProcess+".-1.-1");

							cbSequenceNumber.removeAllItems();

							for(int i = 0; i < steps.size()+1; i++)
								cbSequenceNumber.addItem(i);		
							
							
							cbSequenceNumber.setSelectedIndex(steps.size());	
						}
					
						
						//
					/*	
						public void actionPerformed(ActionEvent arg) {
							
							String selectedBusinessProcess = (String)cbBusinessProcess.getSelectedItem();
							
							cbSequenceNumber.removeAllItems();
							
							for(int i = 1; i <= selectedBusinessProcess.size()+1; i++)
								cbSequenceNumber.addItem(i);
							
							cbSequenceNumber.setSelectedIndex(selectedBusinessProcess.size());									
						}*/
					});										
					
				}
			}
		}
	}
}
