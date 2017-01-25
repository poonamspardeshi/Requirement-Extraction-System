package Controller;

import Opeartion.OperationMgr;

public class ExportRequirementController {
	OperationMgr OMgr = new OperationMgr();
	private String requirement;
	private String path;
	private String extension;
	
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void exportRequirement(){
		OMgr.exportRequirement(requirement, path, extension);
	}
}
