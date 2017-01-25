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

/*
import BusinessObjects.BusinessProcess;
import BusinessObjects.Repository;*/
import Controller.DefineBusinessProcessController;


import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CreateBusinessProcessDialog extends JDialog {
	private JLabel lblVerb;
	private JLabel lblNoun;
	private JTextField txtVerb;
	private JTextField txtNoun;
	private JLabel lblSentance;
	private JTextField txtSentance;
	private JLabel lblSequenceNumber;
	private JComboBox cbSequenceNumber;

	/**
	 * Launch the application.
	 */

	public CreateBusinessProcessDialog(RETGUI parent,ArrayList<String> phrase) {
		setBounds(100, 100, 450, 300);
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
					
					//txtSentance = new JTextField("");
					txtSentance.setBounds(171, 114, 216, 22);
					getContentPane().add(txtSentance);
					
					lblSentance=new JLabel("Sentence : ");
					lblSentance.setBounds(104, 114, 71, 22);
					getContentPane().add(lblSentance);
					
					JComboBox cbSequenceNumber = new JComboBox();
					cbSequenceNumber.setBounds(171, 146, 86, 22);
					getContentPane().add(cbSequenceNumber);
					
					JLabel lblNewLabel = new JLabel("Sequence No : ");
					lblNewLabel.setBounds(104, 149, 56, 16);
					getContentPane().add(lblNewLabel);
				
						JButton createButton = new JButton("Create Business Process");
						createButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
							}
						});
						createButton.setBounds(131, 201, 177, 22);
						getContentPane().add(createButton);
						createButton.setActionCommand("OK");
						getRootPane().setDefaultButton(createButton);
					
					

					lblSequenceNumber = new JLabel("Sequence Number : ");
			
					
					//DefineBusinessProcessController dbpController= new DefineBusinessProcessController();
					
					int numberOfBusinessProcess=dbpController.getBusinessProcesses().size();
					
					for(int i = 1; i <=( numberOfBusinessProcess+1); i++)
						cbSequenceNumber.addItem(i);
					
					cbSequenceNumber.setSelectedIndex(numberOfBusinessProcess);
			
					
					
					createButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {
							
							DefineBusinessProcessController dbpController= new DefineBusinessProcessController();
						//	System.out.println( " "+cbSequenceNumber.getSelectedIndex());
							
							dbpController.addRequirementComponent(txtVerb.getText(), txtNoun.getText(), txtSentance.getText() ,"-1.-1.-1",cbSequenceNumber.getSelectedIndex());
							
							parent.refreshTree();
							
							dispose();
							
						}
					});
					
					
					
					
				}
			}
		}
	}
}
