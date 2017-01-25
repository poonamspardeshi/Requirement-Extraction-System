package composite;

public class Phrase {
	

	private String verb;
	private String noun;
	private String sentence;
	
	public Phrase(String v, String n)
	{
		verb = v;
		noun = n;
		//Root node will not have verb and noun
		if(v == null && n == null )
			sentence="Requirements";
		else
		sentence = createSentence();
	}
	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public String getNoun() {
		return noun;
	}

	public void setNoun(String noun) {
		this.noun = noun;
	}


	private String createSentence()
	{
		return "The system shall allow the user to " + verb + " " + noun;
	}

}
