package com.clear2pay.labs.soap;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

import com.esbbank.gbo.xml.schemas.v1_0.ESBServiceRequest;

public class XMLUtility {

    public static void main(String[] args) throws Exception {
        System.setProperty("javax.xml.stream.isCoalescing", "false");

        ESBServiceRequest esbServiceRequest = RequestBuilder.getEsbServiceRequest();

        formatSoapRequest(RequestBuilder.svcFactory.createEsbServiceRequest(esbServiceRequest));

    }

    private static void formatSoapRequest(JAXBElement<? extends Object> jbElement) {

        try {
            FileOutputStream sw = new FileOutputStream("XMLUtility.txt");

            JAXBContext context = FxJAXBContextProvider.initEsbContext();
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            m.marshal(jbElement, sw);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


