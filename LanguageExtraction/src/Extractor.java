import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.dcoref.Dictionaries;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.IndexedWord;
import edu.stanford.nlp.ling.tokensregex.CoreMapExpressionExtractor;
import edu.stanford.nlp.ling.tokensregex.MatchedExpression;
import edu.stanford.nlp.ling.tokensregex.TokenSequenceMatcher;
import edu.stanford.nlp.ling.tokensregex.TokenSequencePattern;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.tagger.maxent.Dictionary;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.IntPair;


public class Extractor {
	
	// goal -> mostly verbs and verb phrases
	/*
	 * Rules 
	 * Get the main verb of the sentence. This will be the goal of the policy
	 * If the verb is a main phrase (is X or are X) 
	 * 
	 */
	public List<String> extractGoal(Annotation document){
		String extractionFile = "./resources/goalExtractor.regx";
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		CoreMapExpressionExtractor extractor = CoreMapExpressionExtractor.createExtractorFromFiles(TokenSequencePattern.getNewEnv(),extractionFile);
		List<CoreMap> extracted = new ArrayList<CoreMap>();
		extractor.extractCoreMapsToList(extracted, document);
		
		List<String> goals = new ArrayList<String>();
		for(CoreMap node: extracted){
			System.out.println("Nodes: " + node.toString());
			goals.add(node.toString());
		}
		
		
		return goals;
	
	}

	public String extractSubject(Annotation document){
		Map<Integer, CorefChain> graph = 
	    	      document.get(CorefChainAnnotation.class);
		if(graph.size() > 1){
			return graph.get(2).getRepresentativeMention().mentionSpan;
		}
		
		return null;
		
	}
	
	// extract the scope in the direct object
	// used dependency parsing to extract direct object
	public List<String> extractScope(Annotation document){
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		List<String> scopes = new ArrayList<String>();
		for(CoreMap sentence:sentences){
			 SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
			 for(TypedDependency node: dependencies.typedDependencies()){
				 	String scope = "";
			    	if(node.reln().toString().equals("dobj")){
			    		for(IndexedWord child: dependencies.getChildList(node.dep())){
			    			scope += child.originalText() + " ";
			    		}
			    		scope += node.dep();
			    	}
			    	
			 }
		}
		
		return scopes;
	}
	
	public String extractJurisdiction(Annotation document){
		return "";
	}
	
	public String extractConstraint(Annotation document){
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);
		List<String> scopes = new ArrayList<String>();
		for(CoreMap sentence:sentences){
			 SemanticGraph dependencies = sentence.get(CollapsedCCProcessedDependenciesAnnotation.class);
			 for(TypedDependency node: dependencies.typedDependencies()){
				 	String scope = "";
				 	System.out.println("type: " + node.gov().tag());
				 	if(node.gov().tag()!= null){
				 		if(node.gov().tag().equals("VBP")||node.gov().tag().equals("VBZ")){
				    		for(IndexedWord child: dependencies.getChildList(node.dep())){
				    			System.out.println("Child: " +child );
				    			
				    		}
				    		System.out.println("Get children: " + getChildren(dependencies,node.dep()));
				    		scope += node.dep();
				    	}
				 	}
				 	
			    	
			 }
		}
		
		
		return "";
	}

	public String getChildren(SemanticGraph graph, IndexedWord node){

		if(graph.getChildList(node) != null){
			for(IndexedWord word: graph.getChildList(node)){
				System.out.println("Method: "+word);
				return getChildren(graph,word)+ " ";
			}
		} 
		return node.originalText();
	
	}
	
	public Policy extractPolicies(Annotation document){
		Policy policy = new Policy();
		policy.setGoal(extractGoal(document));
		policy.setSubject(extractSubject(document));
		policy.setScope(extractScope(document));
		extractConstraint(document);
		return policy;
	}
	
}
