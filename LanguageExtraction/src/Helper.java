import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class Helper {
	//Delimiter used in CSV file
	private final String COMMA_DELIMITER = ",";
	private final String NEW_LINE_SEPARATOR = "\n";
	
	//CSV file header
	private final String FILE_HEADER = "POLICY NUMBER,GOAL,SUBJECT,SCOPE,CONSTRAINTS,JURISDICTION";
	
	private Extractor extractor = new Extractor();
	
	public List<Policy> readFromPolicyFile(String filename) {
		List<Policy> policies = new ArrayList<Policy>();
		String line = "";

	    try {
	        FileReader fileReader = new FileReader(filename);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);

	        while((line = bufferedReader.readLine()) != null) {
	        	// String to be scanned to find the pattern.
	            String pattern = "(.*)(\\d+)(.*)";

	            // Create a Pattern object
	            Pattern r = Pattern.compile(pattern);

	            // Now create matcher object.
	            Matcher m = r.matcher(line);
	            if (m.find( )) {
	               System.out.println("Found value: " + m.group(0) );
	               System.out.println("Found value: " + m.group(1) );
	               System.out.println("Found value: " + m.group(2) );
	            } else {
	               System.out.println("NO MATCH");
	            }
	        	
	            //stopWords.add(line);
	            //System.out.println("StopWord: " + line);
	        }   

	        bufferedReader.close(); 
	        System.out.println("     > [SUCCESS] Policy file succesfully loaded: " + filename);
	        
	    } catch(Exception ex) {
	        System.out.println("     > [ERROR] Policy file was not loaded.");                
	    }
		
	    return policies;
	}
	
	public List<Policy> extractFromPolicies(List<Policy> policies) {
		Properties props = new Properties();
	    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, dcoref");
	    
		for(Policy poli : policies) {
			String poliCont = poli.getPolicyContent();
			
			StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		    Annotation document = new Annotation(poliCont);
		    pipeline.annotate(document);
			
			Policy temp = extractor.extractPolicies(document);
		
			poli.setGoal(temp.getGoal());
			poli.setSubject(temp.getSubject());
			poli.setScope(temp.getScope());
			// poli.setJuridiction(temp.getJuridiction());
			// poli.setRegulation(temp.getRegulation());
			// poli.setConstraints(temp.getConstraints());
		}
		
		return policies;
		
	}
	
	public void savePolicy(List<Policy> policies){
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_HH:mm:ss");
		Date date = new Date();
		String strDate = "PolicyExtraction_" + dateFormat.format(date);
		
		FileWriter fileWriter = null;
				
		try {
			fileWriter = new FileWriter(strDate);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER);
			
			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);
			
			//Write a new student object list to the CSV file
			for (Policy policy : policies) {
				fileWriter.append("[" + policy.getPolicyNumber() + "]");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("[" + listToString(policy.getGoal()) + "]");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("[" + policy.getSubject() + "]");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("[" + listToString(policy.getScope()) + "]");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("[" + listToString(policy.getConstraints()) + "]");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("[" + listToString(policy.getJuridiction()) + "]");
				fileWriter.append(NEW_LINE_SEPARATOR);
			}

			System.out.println("     > [SUCCESS] Extraction results saved to file: " + strDate);
			
		} catch (Exception e) {
			System.out.println("     > [ERROR] Extraction results not saved to file.");
			e.printStackTrace();
		} finally {
			
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				//System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
			}
			
		}
		
	}
	
	public String listToString(List<String> list) {
		String lstStr = "";
		
		for(String s : list) {
			lstStr += "/" + s;
		}
		
		return lstStr;
	}
}
