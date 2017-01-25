package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import Controller.ExportRequirementController;
import Controller.GenerateRequirementController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class GenerateDialog extends JDialog{
	private final JPanel contentPanel = new JPanel();
	
	private GenerateRequirementController controller;
	
	public GenerateDialog(){
		setTitle("Generated Requirements ");
		setBounds(100, 100, 654, 730);
		
		controller = new GenerateRequirementController();
		String requirement = controller.generateRequirement();
		//System.out.println(requirement);
        
		JTextArea text = new JTextArea();
        text.setBounds(6, 2, 635, 625);
        text.setText(requirement);
		
        JScrollPane scrollPane = new JScrollPane(text);
        scrollPane.setBounds(0, 0, 654, 661);
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);	
		
		 JButton export = new JButton("Export");
		 export.setBounds(253, 673, 138, 29);
	        export.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent arg0) {
	        		JFileChooser fc = new JFileChooser();


				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter("txt","txt");
				FileNameExtensionFilter docFilter = new FileNameExtensionFilter("doc","doc");
				FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("pdf","pdf");
				FileNameExtensionFilter xmlFilter = new FileNameExtensionFilter("xml","xml");

				fc.addChoosableFileFilter(txtFilter);
				fc.addChoosableFileFilter(docFilter);
				fc.addChoosableFileFilter(pdfFilter);
				fc.addChoosableFileFilter(xmlFilter);
				
				fc.setAcceptAllFileFilterUsed(false);
				fc.showSaveDialog(null);

				File file = fc.getSelectedFile();
				String filePath = file.getAbsolutePath() + "." + fc.getFileFilter().getDescription();
				String extension = fc.getFileFilter().getDescription();
				ExportRequirementController exp = new ExportRequirementController();
				exp.setExtension(extension);
				exp.setPath(filePath);
				exp.setRequirement(requirement);
				exp.exportRequirement();
	        	}
	        });
		contentPanel.setLayout(null);
		contentPanel.add(export);
		contentPanel.add(scrollPane);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);

	    
	}
}
