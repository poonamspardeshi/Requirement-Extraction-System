package composite;



public class PrimitiveComponent extends RequirementComponent{

	public PrimitiveComponent(Phrase p) {
		super(p);
	}
	
	public String generate(String parent) {
	
		String sent= this.getPhrase().getSentence()+"\n";

		parent = "      "+parent;
		return (parent +" "+ sent );
	}

	
	
	
}
