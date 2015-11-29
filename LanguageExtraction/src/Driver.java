import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;













import java.util.Scanner;
import java.util.Set;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.semgraph.SemanticGraphEdge;
import edu.stanford.nlp.trees.GrammaticalRelation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.util.CoreMap;


public class Driver {

	public static void main(String[] args) {
		Extractor extractor = new Extractor();
		Helper helper = new Helper();
		
		List<Policy> extractionPolicies;
		Scanner s = new Scanner(System.in);
		String filePath = "";
		
		System.out.println("===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===");
		System.out.println(":: SIMPLE POLICY INFORMATION EXTRACTION SYSTEM ::");
		System.out.print("   + Enter filename of Policy File (w/o .txt): ");
		filePath = s.nextLine();
		System.out.println("===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===-===");
		
		System.out.println("   + Loading policy file to the extraction system...");
		//extractionPolicies = helper.readFromPolicyFile("resources/" + filePath + ".txt");
		
		System.out.println("   + Extracting information from the loaded policies...");
		//extractionPolicies = helper.extractFromPolicies(extractionPolicies);
		
		System.out.println("   + Saving extracted information to file...");
		//helper.savePolicy(extractionPolicies);
		
		
		
//		// TODO Auto-generated method stub
//		Properties props = new Properties();
//	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref"
//	    		+ "");
//	    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//	    
//	    // read some text in the text variable
//	    //String text = "The Information Security Policy establishes requirements to ensure that information security policies remain current as business needs evolve and technology changes. This policy must be published and communicated to employees and relevant external parties. ";
//	    //String text = "Ministries may develop and implement additional information security policies, standards and guidelines for use within their organization or for a specific information system or program. Ministry developed information security policies, standards and guidelines can exceed but must not conflict with the baseline established by the Information Security Policy. ";
//	    //String text = "Appropriate contacts shall be maintained with local law enforcement authorities, emergency support staff and service providers."; 
//	    //String text = "Implementation of information security activities across government must be coordinated by the Office of the Government Chief Information Officer. ";		
//	    String text ="When audit reports or security risk and controls reviews identify high risk exposures involving information systems;";
//	    
//	    // create an empty Annotation just with the given text
//	    Annotation document = new Annotation(text);
//	    
//	    // run all Annotators on this text
//	    pipeline.annotate(document);
//	    
//	    
//	    System.out.println(extractor.extractGoal((document)));
//	    extractor.extractConstraint(document);
//	    // these are all the sentences in this document
//	    // a CoreMap is essentially a Map that uses class objects as keys and has values with custom types
//	    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
//	    
//	    for(CoreMap sentence: sentences) {
//	    	// traversing the words in the current sentence
//	    	// a CoreLabel is a CoreMap with additional token-specific methods
//	      for (CoreLabel token: sentence.get(TokensAnnotation.class)) {
//	        // this is the text of the token
//	        String word = token.get(TextAnnotation.class);
//	        
//	        // this is the POS tag of the token
//	        String pos = token.get(PartOfSpeechAnnotation.class);
//	        // this is the NER label of the token
//	        String ne = token.get(NamedEntityTagAnnotation.class);   
//	        
//	        System.out.println(word+"_"+pos+"_"+ne);
//	      }
//	      Tree tree = sentence.get(TreeAnnotation.class);
//	      System.out.println(tree);
//
//	      // this is the parse tree of the current sentence
//	     
//
//	      // this is the Stanford dependency graph of the current sentence
//	      SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
//	      System.out.println(dependencies);
//	    }
////
//	    System.out.println(document.get(CorefChainAnnotation.class));
//	    Map<Integer, CorefChain> graph = 
//	    	      document.get(CorefChainAnnotation.class);
//	    System.out.println(graph.keySet());
//	    for(Entry<Integer, CorefChain> set : graph.entrySet()){
//	    
//	    	System.out.println(set.getValue().getRepresentativeMention());
//	    }

	}

}
