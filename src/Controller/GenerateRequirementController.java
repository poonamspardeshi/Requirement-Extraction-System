package Controller;

import java.util.List;

import Commands.EditComponent;
import Commands.GetChildList;
import Opeartion.OperationMgr;
import composite.Phrase;

public class GenerateRequirementController {
	
    public String generateRequirement(){
        
        return OperationMgr.getInstance().generateRequirement();
	}
}
