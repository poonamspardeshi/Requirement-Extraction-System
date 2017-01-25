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
import java.awt.event.ActionEvent;

public class EditBusinessProcessDialog extends JDialog {
	private JLabel lblVerb;
	private JLabel lblNoun;
	private JTextField txtVerb;
	private JTextField txtNoun;
	private JLabel lblSentance;
	private JTextField txtSentance;
	private JLabel lblSequenceNumber;
	private JComboBox cbSequenceNumber;



	/**
	 * Create the dialog.
	 */
	public EditBusinessProcessDialog(RETGUI parent, String bpId) {


		setBounds(100, 100, 450, 300);
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
					lblSentance.setBounds(104, 114, 71, 22);
					getContentPane().add(lblSentance);

					JComboBox cbSequenceNumber = new JComboBox();
					cbSequenceNumber.setBounds(171, 146, 86, 22);
					getContentPane().add(cbSequenceNumber);

					lblSequenceNumber = new JLabel("Sequence No : ");
					lblSequenceNumber.setBounds(104, 149, 56, 16);
					getContentPane().add(lblSequenceNumber);

					JButton saveButton = new JButton("Save");
					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
						}
					});
					saveButton.setBounds(164, 201, 93, 22);
					getContentPane().add(saveButton);
					saveButton.setActionCommand("OK");
					getRootPane().setDefaultButton(saveButton);					

					DefineBusinessProcessController dbpController= new DefineBusinessProcessController();
					
					String[] phraseInfo = dbpController.getComponentPhraseInfo(bpId);
					txtVerb.setText(phraseInfo[0]);
					txtNoun.setText(phraseInfo[1]);
					txtSentance.setText(phraseInfo[2]);		

					int numberOfBusinessProcess=dbpController.getBusinessProcesses().size();										
					for(int i = 1; i <=( numberOfBusinessProcess); i++)
						cbSequenceNumber.addItem(i);
					
					int bpIndex = Integer.parseInt(bpId.split("\\.")[0]);
					cbSequenceNumber.setSelectedIndex(bpIndex);													

					saveButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg) {

							EditBusinessProcessesController editBPController=new EditBusinessProcessesController();
							editBPController.editCompositeComponent(bpId, null, txtVerb.getText(), txtNoun.getText(), txtSentance.getText(), cbSequenceNumber.getSelectedIndex());

							parent.refreshTree();

							dispose();

						}
					});								
				}
			}
		}
	}
}