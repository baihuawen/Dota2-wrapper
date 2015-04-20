package com.github.kaisle.util;

import org.jdom2.Document;
import org.jdom2.Element;
/**
 * This class is responsible for creating JDOM-Documents. 
 * @author Anders Borum
 *
 */
public class DocumentFactory {
	
	/**
	 * Create a document.
	 * @param rootName - Name of root element.
	 * @return The document.
	 */
	public static Document createDocument(String rootName) {
		Document document = new Document(); 
		document.setRootElement(new Element(rootName)); 
		return document; 
	}
	
	/**
	 * Create a document.
	 * @param rootName - Name of root element. 
	 * @param rootText - Text of root element. 
	 * @return The document. 
	 */
	public static Document createDocument(String rootName, String rootText) {
		Document document = new Document(); 
		Element root = new Element(rootName); 
		root.setText(rootText);
		document.setRootElement(root); 
		return document; 
	}
	
	

}
