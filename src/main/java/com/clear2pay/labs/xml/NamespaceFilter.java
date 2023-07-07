package com.clear2pay.labs.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

public class NamespaceFilter extends XMLFilterImpl {
	
	private String namespace;
	
	public NamespaceFilter(String namespace) {
		this.namespace = namespace;
	}
	
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(namespace, localName, qName);
    }
 
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        super.startElement(namespace, localName, qName, atts);
    }
 
}
