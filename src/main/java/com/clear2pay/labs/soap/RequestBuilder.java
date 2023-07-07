package com.clear2pay.labs.soap;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.adib.customerrelation.Authentication;
import com.adib.customerrelation.Client;
import com.adib.customerrelation.CountryCodeType;
import com.adib.customerrelation.EAIHEADER;
import com.adib.customerrelation.GetCustomerRelationRequest;
import com.adib.customerrelation.LocationInfo;
import com.adib.customerrelation.MsgChannel;
import com.adib.customerrelation.ObjectFactory;
import com.adib.customerrelation.RequestorLanguage;
import com.adib.customerrelation.SecurityInfo;
import com.adib.customerrelation.ServiceType;
import com.esbbank.gbo.xml.schemas.v1_0.ESBServiceRequest;

public final class RequestBuilder {

    private static final String ADIB_WS_DATE_TIME_PATTERN = "yyyyMMddHHmmssSSS";

    static ObjectFactory reqFactory = new ObjectFactory();

    static com.esbbank.gbo.xml.schemas.v1_0.ObjectFactory svcFactory = new com.esbbank.gbo.xml.schemas.v1_0.ObjectFactory();

    static ESBServiceRequest getEsbServiceRequest() {
        ESBServiceRequest esbServiceRequest = svcFactory.createESBServiceRequest();

        esbServiceRequest.setClientChannel("PHUB");
        esbServiceRequest.setServiceName("GET.CUSTOMER.DETAIL");

        GetCustomerRelationRequest getCustomerRelationRequest = createGetCustomerRelationRequest();
        esbServiceRequest.setUserId(getCustomerRelationRequest.getEAIHEADER().getSecurityInfo().getAuthentication().getUserId());
        esbServiceRequest.setPassword(getCustomerRelationRequest.getEAIHEADER().getSecurityInfo().getAuthentication().getPassword());
        esbServiceRequest.setReferenceNum(getCustomerRelationRequest.getEAIBODY().getReferenceNum());
        //esbServiceRequest.setData(formatData(reqFactory.createGetCustomerRelationRequest(getCustomerRelationRequest)));
        esbServiceRequest.setData(formatData(reqFactory.createGetCustomerRelationRequest(getCustomerRelationRequest)));

        return esbServiceRequest;
    }

    private static GetCustomerRelationRequest createGetCustomerRelationRequest() {
        GetCustomerRelationRequest getCustomerRelationRequest = reqFactory.createGetCustomerRelationRequest();

        getCustomerRelationRequest.setEAIHEADER(createEAIHEADER());
        getCustomerRelationRequest.setEAIBODY(createCustomerRelationRequestEAIBody());

        return getCustomerRelationRequest;
    }

    private static EAIHEADER createEAIHEADER() {
        EAIHEADER eaiHeader = reqFactory.createEAIHEADER();
        eaiHeader.setServiceName("GET.CUSTOMER.DETAIL");
        eaiHeader.setServiceType(ServiceType.valueOf("SYNC"));
        eaiHeader.setServiceVersion("1");
        eaiHeader.setClient(Client.valueOf("ADIB"));
        eaiHeader.setClientChannel("PHUB");
        eaiHeader.setMsgChannel(MsgChannel.valueOf("HTTP"));

        LocationInfo locationInfo = reqFactory.createLocationInfo();
        locationInfo.setCountryCode(CountryCodeType.valueOf("AE"));
        eaiHeader.setLocationInfo(locationInfo);

        eaiHeader.setRequestorLanguage(RequestorLanguage.valueOf("E"));
        eaiHeader.setReturnCode("0000");

        SecurityInfo securityInfo = reqFactory.createSecurityInfo();
        Authentication authentication = reqFactory.createAuthentication();

        authentication.setUserId("PHUB");
        authentication.setPassword("password");
        securityInfo.setAuthentication(authentication);
        eaiHeader.setSecurityInfo(securityInfo);

        return eaiHeader;
    }

    private static GetCustomerRelationRequest.EAIBODY createCustomerRelationRequestEAIBody() {
        GetCustomerRelationRequest.EAIBODY getCustomerRelationRequestEAIBODY = reqFactory.createGetCustomerRelationRequestEAIBODY();
        Date currentDate = new Date(System.currentTimeMillis());
        getCustomerRelationRequestEAIBODY.setReferenceNum("5860209210000502");
        getCustomerRelationRequestEAIBODY.setAccountNumber("12345");
        getCustomerRelationRequestEAIBODY.setAccountType("SAV");
        getCustomerRelationRequestEAIBODY.setRelationshipId(44);
        getCustomerRelationRequestEAIBODY.setRequestTime("20210222101524085");

        return getCustomerRelationRequestEAIBODY;
    }

    public static String formatISODateTime(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat isoDateTimeFormat = new SimpleDateFormat(ADIB_WS_DATE_TIME_PATTERN);
        isoDateTimeFormat.setLenient(false);
        return isoDateTimeFormat.format(date);
    }

    private static String formatData(JAXBElement<? extends Object> jbElement) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = FxJAXBContextProvider.initContext();
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(jbElement, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sanitizeRequestMessage(sw.toString());
    }

    private static String formatDataToDocumentAndTransform(JAXBElement<? extends Object> jbElement) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context = FxJAXBContextProvider.initContext();
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.marshal(jbElement, sw);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return sanitizeRequestMessage(sw.toString());
    }

    static String sanitizeRequestMessage(String request) {
        //String finalRequest = request.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
        return request;
    }
}