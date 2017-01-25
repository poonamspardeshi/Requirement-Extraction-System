package Commands;

import composite.CompositeComponent;
import composite.RequirementComponent;



public class RemoveComponent extends ListCommand{
	
	RequirementComponent child;
	RequirementComponent parent;
	
	int index;
	
	public RemoveComponent(RequirementComponent child) {
		this.child = child;
		this.parent = child.getParent();
	}
	
	
	@Override
	public Object execute()
	{
		
		if(parent instanceof CompositeComponent){
			int index = ((CompositeComponent)parent).getChild().indexOf(child);
			
			if(index == -1){
				return false;
			}
			
			this.index = index;
			
			((CompositeComponent)parent).getChild().remove(index);
			
			return true;
		}

		return false;
	}


	@Override
	public Object undoExecute() {
		
		ListCommand add = new AddComponent(parent, child, index);
		
		return add.execute();
	}

}