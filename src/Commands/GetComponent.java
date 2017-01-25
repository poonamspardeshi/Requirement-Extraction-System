package Commands;

import java.util.List;

import composite.CompositeComponent;
import composite.RequirementComponent;

public class GetComponent extends ListCommand{

	private CompositeComponent root;
	private int bpNum,stepNum,actionNum;
	
	public GetComponent(CompositeComponent root, String id){
		
		this.root = root;
		System.out.println(id);
		String[] num = id.split("\\.");
    	
		
	
		
    	bpNum = Integer.parseInt(num[0]);
    	stepNum = Integer.parseInt(num[1]);
    	actionNum = Integer.parseInt(num[2]);
    	
	}
	
	@Override
	public Object execute() {
		
		RequirementComponent child = root;
		
		if(bpNum != -1){
			child = root.getChildObjAt(bpNum);
			
			if(stepNum != -1){
				child = ((CompositeComponent)child).getChildObjAt(stepNum);
				
				if(actionNum != -1){
					child = ((CompositeComponent)child).getChildObjAt(actionNum);
				}
			}
		
		}
		
		return child;
	}

	@Override
	public Object undoExecute() {
		return null;
	}

}
