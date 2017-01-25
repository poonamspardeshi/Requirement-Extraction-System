package Commands;

import composite.CompositeComponent;
import composite.RequirementComponent;

public class EditComponent extends ListCommand {
	RequirementComponent oldCom;
	RequirementComponent newCom;
	
	int oldIndex;
	int newIndex;

	public EditComponent(
			RequirementComponent oldCom,
			RequirementComponent newCom,
			int newIndex) {
		this.newCom = newCom;
		this.oldCom = oldCom;
		this.newIndex = newIndex;
		this.oldIndex = ((CompositeComponent)oldCom.getParent()).getChild().indexOf(oldCom);
	}

	@Override
	public Object execute() {

		RemoveComponent remove = new RemoveComponent(oldCom);
		remove.execute();

		AddComponent add = null;

		add = new AddComponent(newCom.getParent(), newCom,
				newIndex);

		return add.execute();
	}
	
	@Override
	public Object undoExecute() {

		RemoveComponent remove = new RemoveComponent(newCom);
		remove.execute();

		AddComponent add = null;

		add = new AddComponent(oldCom.getParent(), oldCom,
				oldIndex);

		return add.execute();
	}

}