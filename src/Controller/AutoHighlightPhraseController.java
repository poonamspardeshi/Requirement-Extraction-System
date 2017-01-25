package Controller;

import java.awt.Color;
import java.io.StringReader;
import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class AutoHighlightPhraseController {

	//Variables for Stanfor NLP
	private static String modelFile;
	private static MaxentTagger tagger;	
		
	public static void autoHighlight(String text, Highlighter highlighter)
	{
		modelFile = "english-bidirectional-distsim.tagger";
		tagger = new MaxentTagger(modelFile);
		
		StringReader stringReader = new StringReader(text);

		List<List<HasWord>> sentences = MaxentTagger.tokenizeText(stringReader);			    

		try
		{
			for (List<HasWord> sentence : sentences) 
			{
				List<TaggedWord> tSentence = tagger.tagSentence(sentence);  
				for(TaggedWord tword : tSentence) 
				{
					if(tword.value().equals("-RRB-") || tword.value().equals("-LRB-"))
						continue;

					if(tword.tag().equals("VBG") || tword.tag().equals("VBZ"))   //VBZ VBP
						highlighter.addHighlight(tword.beginPosition(), tword.endPosition(), new DefaultHighlighter.DefaultHighlightPainter(Color.yellow));
					else if(tword.tag().equals("NN"))
						highlighter.addHighlight(tword.beginPosition(), tword.endPosition(),new DefaultHighlighter.DefaultHighlightPainter(Color.green));
				}			      
			}
		}catch(BadLocationException ex)
		{
			ex.printStackTrace();
		}
	}
}
