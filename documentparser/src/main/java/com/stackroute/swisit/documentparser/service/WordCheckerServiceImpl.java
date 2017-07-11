package com.stackroute.swisit.documentparser.service;

import com.uttesh.exude.ExudeData;

import com.uttesh.exude.exception.InvalidDataException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
import java.util.Map;


/**
 * Created by user on 30/6/17.
 */
@Service
public class WordCheckerServiceImpl implements WordCheckerService{
	public HashMap<String,List<String>> getWordCheckerByWord(HashMap<String,String> input){
		
		/*String inputData = "This handy tool helps you create dummy text for all your layout needs. 
		We are gradually adding new functionality and we welcome your suggestions and feedback. 
		Please feel free to send us any additional dummy texts.";*/

		/*Iterator<HashMap.Entry<String,String>> ksritr = input.entrySet().iterator();
	        while(ksritr.hasNext()){
	        	HashMap.Entry wordInput = ksritr.next();
				System.out.println("wordInput.getKey() :   " + wordInput.getKey() + "      wordInput.getValue()  :  " + wordInput.getValue());
			}*/
		HashMap<String,List<String>> tockenizedWords = new HashMap<>();
		
		HashMap<String,String> map = new HashMap<>();
		//System.out.println("Outside while of input");
		Iterator<Map.Entry<String, String>> entries = input.entrySet().iterator();
		while(entries.hasNext()) {
			//System.out.println("Inside while of input");
			Map.Entry<String, String> entry =  entries.next();
			String key = entry.getKey();
			String inputData = entry.getValue();
			String filteredWords = "";
			String swearWords = "";
			try {

					if (inputData == null ){
						throw new InvalidDataException("Empty input data");
					}
				filteredWords = ExudeData.getInstance().filterStoppings(inputData);
				swearWords = ExudeData.getInstance().getSwearWords(inputData);
			} catch (InvalidDataException e) {

			}
			//System.out.println("output : " + filteredWords);
			//System.out.println("output : " + swearWords);
			String filteredSpecialChar = filteredWords.replaceAll("[$_&+,:;=?@#|'<>.-^*()%!]", "");
			//System.out.println("Filtered output:::: "+filteredSpecialChar);
			List<String> result = new ArrayList<>();
			for (String s1 : filteredSpecialChar.split(" ")) {
				//System.out.println("string here -----"+s1);
				if(s1.isEmpty())
					continue;
					result.add(s1.trim());
				//System.out.println(result);
			}
			tockenizedWords.put(key,result);
			/*Iterator<HashMap.Entry<String,List<String>>> wordString = tockenizedWords.entrySet().iterator();
			while(wordString.hasNext()){
				HashMap.Entry wordOutput = wordString.next();
				System.out.println("wordOutput.getKey() :   " + wordOutput.getKey() + "      wordOutput.getValue()  :  " + wordOutput.getValue());
			}*/
		}
		return tockenizedWords;
	}
}
