package Controller;

import java.awt.Color;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.tree.DefaultTreeModel;

import Commands.AddComponent;
import Commands.GetChildList;
import Commands.RemoveComponent;
import Opeartion.OperationMgr;
import composite.CompositeComponent;
import composite.Phrase;
import composite.PrimitiveComponent;
import composite.RequirementComponent;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;


public class DefineBusinessProcessController {
	
	static DefaultTreeModel treeModel;
	
	public List<String> getBusinessProcesses(){
		
		List<String> bPList=new ArrayList<String>();
		OperationMgr opn= OperationMgr.getInstance();
		
		
		List<RequirementComponent> BpList=opn.getChildList(opn.getComponent("-1.-1.-1"));
		
		
		for (int i = 0; i < BpList.size(); i++) {
			
			bPList.add(i+".-1.-1");
		
		}
		
		return bPList;
		
		
	}
	
	public DefaultTreeModel getTreeModel()
	{
		OperationMgr opn= OperationMgr.getInstance();
		
		
		return new DefaultTreeModel(opn.getComponent("-1.-1.-1"));
		
	}
	
	public void setTreeModel(DefaultTreeModel _treeModel){
		treeModel = _treeModel;
	}
	
	public DefaultTreeModel getCompleteModel(){
		return treeModel;
	}
	
	public List<String> getSteps(String bpID){

		String[] num =bpID.split("\\.");
		List<String> stepList=new ArrayList<String>();
		OperationMgr opn= OperationMgr.getInstance();
		
		
		List<RequirementComponent> StepList=opn.getChildList(opn.getComponent(bpID));
		
		
		for (int i = 0; i < StepList.size(); i++) {
			
			stepList.add(num[0]+"."+i+".-1");
		
		}
		
		return stepList;
		
		
		
	}
	
	public String getSentance(String verb,String noun)
	{
		Phrase p=new Phrase(verb,noun);
		String sentance = p.getSentence();
	
		return sentance;
	}
	
	
	
	public List<String> getActions(String step){


		String[] num =step.split("\\.");
		List<String> stepList=new ArrayList<String>();
		OperationMgr opn= OperationMgr.getInstance();
		
		
		List<RequirementComponent> StepList=opn.getChildList(opn.getComponent(step));
		
		
		for (int i = 0; i < StepList.size(); i++) {
			
			stepList.add(num[0]+"."+num[1]+"."+i);
		
		}
		
		return stepList;
		
		
		
	}
	
	  public String[] getComponentPhraseInfo(String componentId)
	    {
	       OperationMgr opn= OperationMgr.getInstance();
	       Phrase phrase = opn.getComponent(componentId).getPhrase();
	       String[] phraseInfo = new String[3];
	       if(phrase!=null)
	       {
	             phraseInfo[0] = phrase.getVerb();
	             phraseInfo[1] = phrase.getNoun();
	             phraseInfo[2] = phrase.getSentence();
	       }
	       return phraseInfo;
	    }

	/*public void addCompositeComponent(String verb, String noun, String sentence, int position,String parentID){
		
		Phrase phrase = new Phrase(verb, noun);
		
		if(sentence!=null && sentence.length()>0)
			phrase.setSentence(sentence);
		
		RequirementComponent compositeComponent = new CompositeComponent(phrase);
		
		OperationMgr opn= OperationMgr.getInstance();
		
		opn.addComponent(opn.getComponent(parentID), compositeComponent, position);
		
		
	}
	

    
    public void addPrimitiveComponent(String verb, String noun, String sentence, String parentID ,int position){
		
		Phrase phrase = new Phrase(verb, noun);
		
		if(sentence!=null && sentence.length()>0)
			phrase.setSentence(sentence);
		
		RequirementComponent businessProcess = new PrimitiveComponent(phrase);
		
		OperationMgr opn= OperationMgr.getInstance();
		
		opn.addComponent(opn.getComponent(parentID), businessProcess, position);
		
		
	}*/
    
    
    public void addRequirementComponent(String verb, String noun, String sentence , String parentID,int position){
		
		Phrase phrase = new Phrase(verb, noun);
		
		if(sentence!=null && sentence.length()>0)
			phrase.setSentence(sentence);
		
		String[] num = parentID.split("\\.");
    	int bp = Integer.parseInt(num[0]);
    	int step = Integer.parseInt(num[1]);
    	int action = Integer.parseInt(num[2]);
    	
    	
		
    	RequirementComponent component = null ;
  	
		if(bp != -1 && step != -1 && action==-1)
		{
			 component = new PrimitiveComponent(phrase);
			// System.out.println("primitive created");
			
		}
		else
		{
			component = new CompositeComponent(phrase);
		//System.out.println("composite created");
			
		}
		
		OperationMgr opn= OperationMgr.getInstance();
		
		opn.addComponent(opn.getComponent(parentID), component, position);
		
		
	}
    
}
