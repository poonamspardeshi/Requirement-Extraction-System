package Commands;

import composite.CompositeComponent;
import composite.RequirementComponent;


public class AddComponent extends ListCommand {
	RequirementComponent parent;
	RequirementComponent child;
	int index;
	
	public AddComponent(RequirementComponent parent,RequirementComponent child,int index) {
		this.parent=parent;
		this.child=child;
		this.index = index;
	}
	
	@Override
	public Object execute()
	{	
		
		if(parent instanceof CompositeComponent ){
			((CompositeComponent) parent).addChildAt(index, child);
			child.setParent(parent);
			return true;
		}
		
		return false;
	}

	@Override
	public Object undoExecute(){
		RemoveComponent remove = new RemoveComponent(child);
		return remove.execute();
	}
}