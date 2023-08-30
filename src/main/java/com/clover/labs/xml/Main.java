package com.clover.labs.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CDATASection;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Main {
  public static void main(String[] argv) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setValidating(true);
    factory.setExpandEntityReferences(false);

    Document doc = factory.newDocumentBuilder().parse(new File("filename"));
    Element element = doc.getElementById("key1");
    CDATASection cdataNode = doc.createCDATASection("");
    Comment commentNode = doc.createComment("");
    Text textNode = doc.createTextNode("");

 
    CharacterData cdata = cdataNode;
    cdata = commentNode;
    cdata = textNode;

    cdata.setData("some data");
    int len = cdata.getLength();

  }
}