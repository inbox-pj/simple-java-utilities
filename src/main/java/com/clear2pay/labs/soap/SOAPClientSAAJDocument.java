package com.clear2pay.labs.soap;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.HashMap;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

import org.w3c.dom.CDATASection;

import com.esbbank.gbo.xml.schemas.v1_0.ESBServiceRequest;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.ContentType;

public class SOAPClientSAAJDocument {

    private static String soapEndpointUrl = "https://10.5.73.219:8703/ESBPayHubServices";

    private static String soapAction = "urn:esbbank.com/gbo/xml/schemas/v1_0/getCustomerRelation";

    public static void main(String args[]) {

        callSoapWebService(soapEndpointUrl, soapAction);
    }

    private static void callSoapWebService(String soapEndpointUrl, String soapAction) {
        try {
            // Create SOAP Connection
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();

            URL endpoint = new URL(null, soapEndpointUrl, new URLStreamHandler() {
                protected URLConnection openConnection(URL url) throws IOException {
                    // The url is the parent of this stream handler, so must create clone
                    URL clone = new URL(url.toString());
                    URLConnection connection = clone.openConnection();
                    connection.setConnectTimeout(1 * 1000);      // 15 sec
                    connection.setReadTimeout(1 * 1000);         // 15 sec

                    return connection;
                }
            });

            // Send SOAP Message to SOAP Server
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(soapAction), endpoint);

            // Print the SOAP Response
            System.out.println("Response SOAP Message:");
            soapResponse.writeTo(System.out);
            System.out.println();

            soapConnection.close();
        } catch (ConnectException | SocketTimeoutException | SOAPException e) {
            System.out.print("Connecction Refused");
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }

    private static SOAPMessage createSOAPRequest(String soapAction) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        createSoapEnvelope(soapMessage);

        HashMap<String, String> soapHeaders = new HashMap<String, String>();
        soapHeaders.put("Accept", "application/soap+xml,multipart/related,text/*");
        soapHeaders.put("Cache-Control", "no-cache");
        soapHeaders.put("Content-Type", "text/xml");
        soapHeaders.put("Pragma", "no-cache");
        soapHeaders.put("SOAPAction", soapAction);

        ContentType contentType = new ContentType("text/xml");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        soapHeaders.forEach((k, v) -> headers.addHeader(k, v));
        soapMessage.setProperty("Content-Type", contentType);

        soapMessage.saveChanges();

        soapMessage.writeTo(bout);

        System.out.println(bout.toString("UTF-8"));

        return soapMessage;

    }

    private static void createSoapEnvelope(SOAPMessage soapMessage) throws Exception {
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String myNamespace = "ns1";
        String myNamespaceURI = "urn:esbbank.com/gbo/xml/schemas/v1_0/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.addNamespaceDeclaration(myNamespace, myNamespaceURI);

        ESBServiceRequest esbServiceRequest = RequestBuilder.getEsbServiceRequest();

        SOAPElement soapBodyElem = soapBody.addChildElement("esbServiceRequest", myNamespace);

        soapBodyElem.addChildElement("referenceNum").addTextNode(esbServiceRequest.getReferenceNum());
        soapBodyElem.addChildElement("clientChannel").addTextNode(esbServiceRequest.getClientChannel());
        soapBodyElem.addChildElement("serviceName").addTextNode(esbServiceRequest.getServiceName());
        soapBodyElem.addChildElement("UserId").addTextNode(esbServiceRequest.getUserId());
        soapBodyElem.addChildElement("Password").addTextNode(esbServiceRequest.getPassword());

        SOAPElement dataElement = soapBodyElem.addChildElement("data");
        CDATASection cdata = dataElement.getOwnerDocument().createCDATASection(esbServiceRequest.getData().replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim());
        dataElement.appendChild(cdata);
    }
}
