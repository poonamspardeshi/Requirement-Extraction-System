package composite;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public abstract class RequirementComponent extends DefaultMutableTreeNode {


	private RequirementComponent parent;	
	private Phrase phrase;
	
	public RequirementComponent(Phrase p) {
		this.phrase=p;
	}
	
	public RequirementComponent getParent() {
		return parent;
	}

	public void setParent(RequirementComponent parent) {
		this.parent = parent;
	}

	
	public void setPhrase(Phrase phrase){
		this.phrase = phrase;
	}
	
	public Phrase getPhrase(){
		return this.phrase;
	}
	
	public String toString()
	{
		
		return this.getPhrase().getSentence();
	}
	
	public int getChildCount() {
	return 0;
	}

	public String generate(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	  
	
}
