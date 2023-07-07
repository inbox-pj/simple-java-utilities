package com.clear2pay.labs.xml;

import java.io.Writer;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class CustomNamespacePrefixMapper extends DelegatingXMLStreamWriter {

	private static final NamespaceContext emptyNamespaceContext = new NamespaceContext() {

		@Override
		public String getNamespaceURI(String prefix) {
			return "";
		}

		@Override
		public String getPrefix(String namespaceURI) {
			return "";
		}

		@Override
		public Iterator<?> getPrefixes(String namespaceURI) {
			return null;
		}

	};

	public static XMLStreamWriter filter(Writer writer) throws XMLStreamException {
		return new CustomNamespacePrefixMapper(XMLOutputFactory.newInstance().createXMLStreamWriter(writer));
	}

	public CustomNamespacePrefixMapper(XMLStreamWriter writer) {
		super(writer);
	}

	@Override
	public NamespaceContext getNamespaceContext() {
		return emptyNamespaceContext;
	}

}
