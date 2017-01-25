package Opeartion;

import java.util.List;

import composite.CompositeComponent;
import composite.Phrase;
import composite.RequirementComponent;

public class OperationMgr {

	private static OperationMgr instance;
	private OpeartionInterface operation;
	
	public static OperationMgr getInstance(){
		if(instance == null)
			instance = new OperationMgr();
		
		return instance;
	}
	
	public OperationMgr(){
		operation = new OpeartionImpl();
	}
	
	public boolean addComponent(RequirementComponent parent,RequirementComponent child,int index){
		 return operation.addComponent(parent, child, index);
	}
	
	public boolean removeComponent(RequirementComponent child){
		return operation.removeComponent(child);
	}
	
	public List<RequirementComponent> getChildList(RequirementComponent parent){
		return operation.getChildList(parent);
	}
	
	public RequirementComponent getComponent(String id){
		return operation.getComponent(id);
	}
	
	public boolean editComponent(RequirementComponent oldCom,RequirementComponent newCom,int newIndex){
		return operation.editComponent(oldCom, newCom, newIndex);
	}
	
	public String generateRequirement(){
		return operation.generateRequirement();
	}
	
	public boolean exportRequirement(String requirement, String path, String extension){
		return operation.exportRequirement(requirement, path, extension);
	}
	
	public boolean undo(){
		return operation.undo();
	}
	
	public boolean redo(){
		return operation.redo();
	}
}
