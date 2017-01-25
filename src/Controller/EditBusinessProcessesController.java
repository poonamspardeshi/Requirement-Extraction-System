package Controller;

import Commands.EditComponent;
import Commands.RemoveComponent;
import Opeartion.OperationMgr;
import composite.CompositeComponent;
import composite.Phrase;
import composite.PrimitiveComponent;
import composite.RequirementComponent;

public class EditBusinessProcessesController {

	public void removeRequirementComponent(String removedComponenet)
	{
		OperationMgr opManager = OperationMgr.getInstance();
		CompositeComponent oldComponent = (CompositeComponent) opManager.getComponent(removedComponenet);

		opManager.removeComponent(oldComponent);
	}

	public void editCompositeComponent(String oldComponentId, String parentId, String verb, String noun, String sentence, int position)
	{

		Phrase phrase = new Phrase(verb, noun);

		if(sentence!=null && sentence.length()>0)
			phrase.setSentence(sentence);

		CompositeComponent newComponent = new CompositeComponent(phrase);		
		OperationMgr opManager = OperationMgr.getInstance();
		
		CompositeComponent oldComponent = (CompositeComponent) opManager.getComponent(oldComponentId);		
		newComponent.setChild(oldComponent.getChild());

		if(parentId!=null)
		{
			RequirementComponent parent = opManager.getComponent(parentId);
			newComponent.setParent(parent);
		}
		else
		{
			newComponent.setParent(oldComponent.getParent());
		}

		opManager.editComponent(oldComponent, newComponent, position);

	}

	public void editPrimitiveComponent(String oldComponentId, String parentId, String verb, String noun, String sentence, int position){

		Phrase phrase = new Phrase(verb, noun);

		if(sentence!=null && sentence.length()>0)
			phrase.setSentence(sentence);

		RequirementComponent newComponent = new PrimitiveComponent(phrase);		
		OperationMgr opManager = OperationMgr.getInstance();
		RequirementComponent oldComponent = opManager.getComponent(oldComponentId);
		RequirementComponent parent = opManager.getComponent(parentId);
		newComponent.setParent(parent);
		opManager.editComponent(oldComponent, newComponent, position);

	}

}