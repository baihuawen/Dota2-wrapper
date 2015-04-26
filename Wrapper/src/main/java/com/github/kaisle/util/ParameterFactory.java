package com.github.kaisle.util;

import java.util.HashMap;
/**
 * This class is responsible for creating and modifying hash-maps that can store query-string parameters. 
 * @author Anders Borum
 *
 */
public class ParameterFactory {
	
	/**
	 * Create a new HashMap with a pre-configured XML-parameter mapping. 
	 * @return e
	 */
	public static HashMap<String, String> getParametersWithXML() {
		HashMap<String, String> params = new HashMap<String, String>(); 
		params.put("format", "XML"); 
		return params; 
	}
	
	/**
	 * Append a mapping to the HashMap, setting the format to XML.
	 * @param parameterMap The HashMap to be used.
	 */
	public static void setFormatToXML(HashMap<String, String> parameterMap) {

		parameterMap.put("format", "XML");
	}

	/**
	 * Append a mapping to the HashMap, setting the language to the language of the specified country.
	 * @param parameterMap The HashMap to be used. 
	 * @param countryCode The code of the country/language, e.g. "en" for English. 
	 */
	public static void setLanguage(HashMap<String, String> parameterMap,
			String countryCode) {

		parameterMap.put("language", countryCode);
	}

}
