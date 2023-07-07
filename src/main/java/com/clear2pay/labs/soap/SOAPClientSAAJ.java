package com.clear2pay.labs.soap;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.Document;

import com.esbbank.gbo.xml.schemas.v1_0.ESBServiceRequest;
import com.sun.xml.internal.bind.marshaller.DataWriter;

public class SOAPClientSAAJ {

    private static String soapEndpointUrl = "https://10.5.73.219:8703/ESBPayHubServices";
    private static String soapAction = "urn:esbbank.com/gbo/xml/schemas/v1_0/eFxDeal";


    public static void main(String args[]) {

        callSoapWebService(soapEndpointUrl, soapAction);
    }

    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), soapEndpointUrl);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", soapAction);
        headers.addHeader("Accept", "application/soap+xml,multipart/related,text/*");
        headers.addHeader("Cache-Control", "no-cache");
        headers.addHeader("Content-Type", "text/xml; charset=UTF-8");
        headers.addHeader("Pragma", "no-cache");

        soapMessage.saveChanges();

        FileOutputStream stream = new FileOutputStream("SOAPClientSAAJ.txt");
        soapMessage.writeTo(stream);

        return soapMessage;
    }

    private static void createSoapEnvelope(SOAPMessage soapMessage) throws SOAPException {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        //String myNamespace = "soapenv";
        //String myNamespaceURI = "http://schemas.xmlsoap.org/soap/envelope/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        //envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        ESBServiceRequest esbServiceRequest = RequestBuilder.getEsbServiceRequest();
        //soapBody.addTextNode(formatSoapRequest(RequestBuilder.svcFactory.createEsbServiceRequest(esbServiceRequest)));
        soapBody.addDocument(formatSoapRequestIntoDocument(RequestBuilder.svcFactory.createEsbServiceRequest(esbServiceRequest)));

        //SOAPElement soapBodyElem = soapBody.addChildElement("esbServiceRequest", myNamespace);
        //SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("referenceNum", myNamespace);
        //soapBodyElem1.addTextNode("586051821cbd1fa8");
    }


    private static String formatSoapRequest(JAXBElement<? extends Object> jbElement) {
        StringWriter sw = new StringWriter();

        try {
            JAXBContext context = FxJAXBContextProvider.initEsbContext();
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            PrintWriter printWriter = new PrintWriter(sw);
            DataWriter dataWriter = new DataWriter(printWriter, "UTF-8"/*, new JaxbCharacterEscapeHandler()*/);

            m.marshal(jbElement, dataWriter);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return RequestBuilder.sanitizeRequestMessage(sw.toString());
    }

    private static Document formatSoapRequestIntoDocument(JAXBElement<? extends Object> jbElement) {
        Document document = null;

        try {
            JAXBContext context = FxJAXBContextProvider.initEsbContext();
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.newDocument();

            m.marshal(jbElement, document);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }





}
