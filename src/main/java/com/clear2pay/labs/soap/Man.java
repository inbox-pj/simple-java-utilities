package com.clear2pay.labs.soap;

import java.io.ByteArrayInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import com.esbbank.gbo.xml.schemas.v1_0.ESBServiceReply;

public class Man {

    static String response  =
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "<soapenv:Body>\n" +
                    "<ns2:esbServiceReply xmlns:ns2=\"urn:esbbank.com/gbo/xml/schemas/v1_0/\">\n" +
                    "   <referenceNum>568024021EFX43ee</referenceNum>\n" +
                    "   <returnStatus>\n" +
                    "       <returnCode>0000</returnCode>\n" +
                    "       <returnDesc>successfully executed</returnDesc>\n" +
                    "   </returnStatus>\n" +
                    "   <data><![CDATA[<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\"?>\n" +
                    "   <out:eFxDealReply xmlns:out=\"urn:esbbank.com/gbo/xml/schemas/v1_0/\">\n" +
                    "       <eAI_HEADER>\n" +
                    "           <serviceName>EFX.GETRATE</serviceName>\n" +
                    "           <serviceType>SYNC</serviceType>\n" +
                    "           <serviceVersion>1</serviceVersion>\n" +
                    "           <client>ADIB</client>\n" +
                    "           <clientChannel>CASH</clientChannel>\n" +
                    "           <msgChannel>HTTP</msgChannel>\n" +
                    "           <locationInfo>\n" +
                    "               <countryCode>IGTBAE</countryCode>\n" +
                    "           </locationInfo>\n" +
                    "           <requestorLanguage>E</requestorLanguage>\n" +
                    "           <securityInfo>\n" +
                    "               <authentication>\n" +
                    "                   <UserId>CASH</UserId>\n" +
                    "                   <Password>XXXXXXXXX</Password>\n" +
                    "               </authentication>\n" +
                    "           </securityInfo>\n" +
                    "           <returnCode>0000</returnCode>\n" +
                    "       </eAI_HEADER>\n" +
                    "       <eAI_BODY>\n" +
                    "           <referenceNum>545Q210222306363</referenceNum>\n" +
                    "           <referenceNumConsumer />\n" +
                    "           <branchCode>01</branchCode>\n" +
                    "           <atmSwitchId>CASH</atmSwitchId>\n" +
                    "           <terminalId>FX</terminalId>\n" +
                    "           <employeeId>1</employeeId>\n" +
                    "           <supervisorId>1</supervisorId>\n" +
                    "           <userName>CBX</userName>\n" +
                    "           <segment>BBD</segment>\n" +
                    "           <product>Transfers</product>\n" +
                    "           <entity>UAE</entity>\n" +
                    "           <customerID>573567</customerID>\n" +
                    "           <RequesterId>UAE_CASH_14_Transfers</RequesterId>\n" +
                    "           <currencyPair>\n" +
                    "               <buyCurrency>USD</buyCurrency>\n" +
                    "               <sellCurrency>AED</sellCurrency>\n" +
                    "               <dealtAmount>10</dealtAmount>\n" +
                    "               <direction>BUY</direction>\n" +
                    "               <valueDateType>SPOT</valueDateType>\n" +
                    "               <valueDate>2021-02-24</valueDate>\n" +
                    "               <valueDateChanged>false</valueDateChanged>\n" +
                    "               <customerRate>3.6670000000000003</customerRate>\n" +
                    "               <contraAmount>36.67</contraAmount>\n" +
                    "               <timeToLive>90</timeToLive>\n" +
                    "               <gidId>238079</gidId>\n" +
                    "               <midRate>3.6725</midRate>\n" +
                    "               <traderName>Autotrader Trapi1</traderName>\n" +
                    "               <manualRFQ>false</manualRFQ>\n" +
                    "           </currencyPair>\n" +
                    "           <currencyPair>\n" +
                    "               <buyCurrency>USD</buyCurrency>\n" +
                    "               <sellCurrency>AED</sellCurrency>\n" +
                    "               <dealtAmount>10</dealtAmount>\n" +
                    "               <direction>BUY</direction>\n" +
                    "               <valueDate>2021-05-24</valueDate>\n" +
                    "               <dealtCurrency>USD</dealtCurrency>\n" +
                    "               <customerRate>3.667</customerRate>\n" +
                    "               <midRate>3.6725</midRate>\n" +
                    "               <clientRate>3.6725</clientRate>\n" +
                    "               <status>OK</status>\n" +
                    "           </currencyPair>\n" +
                    "           <currencyPair>\n" +
                    "               <buyCurrency>AED</buyCurrency>\n" +
                    "               <sellCurrency>AED</sellCurrency>\n" +
                    "               <dealtAmount>10</dealtAmount>\n" +
                    "               <direction>BUY</direction>\n" +
                    "               <valueDate>2021-05-24</valueDate>\n" +
                    "               <dealtCurrency>AED</dealtCurrency>\n" +
                    "               <customerRate>1</customerRate>\n" +
                    "               <midRate>1</midRate>\n" +
                    "               <clientRate>1</clientRate>\n" +
                    "               <status>OK</status>\n" +
                    "           </currencyPair>\n" +
                    "           <snapIn>" +
                    "<nameValue Name=\"instanceId\" Value=\"TRAPIDIT\" />" +
                    "</snapIn>\n" +
                    "           <requestTime>20210222101524085</requestTime>\n" +
                    "           <returnStatus>\n" +
                    "               <returnCode>0000</returnCode>\n" +
                    "               <returnCodeDesc>Successful</returnCodeDesc>\n" +
                    "           </returnStatus>\n" +
                    "           <returnStatusProvider>\n" +
                    "               <returnCodeProvider>0</returnCodeProvider>\n" +
                    "               <returnCodeDescProvider>Successful</returnCodeDescProvider>\n" +
                    "           </returnStatusProvider>\n" +
                    "       </eAI_BODY>\n" +
                    "   </out:eFxDealReply>]]>\n" +
                    "   </data>\n" +
                    "   </ns2:esbServiceReply>\n" +
                    "   </soapenv:Body>"+
                    "</soapenv:Envelope>";

    private static String response1 =
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
                    + "   <soapenv:Body>\n"
                    + "      <NS1:esbServiceReply xmlns:NS1=\"urn:esbbank.com/gbo/xml/schemas/v1_0/\">\n"
                    + "         <referenceNum>561SA668A11SJ1</referenceNum>\n"
                    + "         <data>\n"
                    + "<![CDATA[<NS1:getAccountCustDetailReply xmlns:NS1=\"urn:esbbank.com/gbo/xml/schemas/v1_0/\">\n"
                    + "<eAI_HEADER>\n"
                    + "<serviceName>GET.ACCOUNT.DETAIL</serviceName>\n"
                    + "<serviceType>SYNC</serviceType>\n"
                    + "<serviceVersion>1.0</serviceVersion>\n"
                    + "<client>ADIB</client>\n"
                    + "<clientChannel>ITMU</clientChannel>\n"
                    + "<msgChannel>HTTP</msgChannel>\n"
                    + "<requestorLanguage>E</requestorLanguage>\n"
                    + "<securityInfo>\n"
                    + "<authentication>\n"
                    + "<UserId>ITMU</UserId>\n"
                    + "<Password>XXXXXXXXXXXX</Password>\n"
                    + "</authentication>\n"
                    + "</securityInfo>\n"
                    + "<returnCode>0000</returnCode>\n"
                    + "</eAI_HEADER>\n"
                    + "<eAI_BODY>\n"
                    + "<referenceNum>561SA668A11SJ1</referenceNum>\n"
                    + "<account>\n"
                    + "<accountNumber>10005723<accountNumber>\n"
                    + "<accountType>CUR </accountType>\n"
                    + "<classCode>101</classCode>\n"
                    + "<accountRSM>1</accountRSM>\n"
                    + "<branchCode>398</branchCode>\n"
                    + "<currencyId>6</currencyId>\n"
                    + "<currencyCode>AED </currencyCode>\n"
                    + "<decimalDigits>2</decimalDigits>\n"
                    + "<openDate>1998-10-03T00:00:00</openDate>\n"
                    + "<maturityDate/>\n"
                    + "<trm/>\n"
                    + "<period/>\n"
                    + "<ODLimit>0.000000</ODLimit>\n"
                    + "<title1>title_1</title1>\n"
                    + "<title2>title_2</title2>\n"
                    + "<ibanNum>AE590500000000010005723</ibanNum>\n"
                    + "<status>Active                      </status>\n"
                    + "<depNo/>\n"
                    + "<checkNo/>\n"
                    + "<wdNo/>\n"
                    + "<balanceMax/>\n"
                    + "<accountJointRel>1</accountJointRel>\n"
                    + "</account>\n"
                    + "<customer>\n"
                    + "<number>45</number>\n"
                    + "<status>Active                      </status>\n"
                    + "<title>MR.</title>\n"
                    + "<firstName>FIRST_NAME</firstName>\n"
                    + "<middleInitial/>\n"
                    + "<lastName>LAST_NAME</lastName>\n"
                    + "<preferredName/>\n"
                    + "<olLastName/>\n"
                    + "<olFirstName/>\n"
                    + "<olMiddleName/>\n"
                    + "<olPreferredName/>\n"
                    + "<motherMaidenName/>\n"
                    + "<branchId>398</branchId>\n"
                    + "<branchName>Al Najda Branch</branchName>\n"
                    + "<RSM>1</RSM>\n"
                    + "<classCode>310</classCode>\n"
                    + "<classDescription>CLASSIC-Deposit (Mass)</classDescription>\n"
                    + "<countryCode>UAE    </countryCode>\n"
                    + "<addrCountry>United Arab Emirates</addrCountry>\n"
                    + "<restrictId>5005</restrictId>\n"
                    + "<restrictDescription>Customer</restrictDescription>\n"
                    + "<languageID>1</languageID>\n"
                    + "<rimType>Personal        </rimType>\n"
                    + "<sex>M</sex>\n"
                    + "<birthdate>1968-09-20T00:00:00</birthdate>\n"
                    + "<Tin>5011A18718281         </Tin>\n"
                    + "<identId>23</identId>\n"
                    + "<idType>Resident Passport</idType>\n"
                    + "<idNumber/>\n"
                    + "<IdExpiryDate>2023-06-19T00:00:00</IdExpiryDate>\n"
                    + "<idIssueDate>2016-06-20T00:00:00</idIssueDate>\n"
                    + "<idIssueCountry>EGY    </idIssueCountry>\n"
                    + "<identId2>27</identId2>\n"
                    + "<IdType2>UAE - Identity Card</IdType2>\n"
                    + "<idNumber2/>\n"
                    + "<idExpirydt2>2021-08-06T00:00:00</idExpirydt2>\n"
                    + "<idIssueDate2/>\n"
                    + "<idIssueCountry2>UAE    </idIssueCountry2>\n"
                    + "<nationalId/>\n"
                    + "<nationalIdExpDate/>\n"
                    + "<nationalIdIssueDate/>\n"
                    + "<sicCode>1400</sicCode>\n"
                    + "<sicDescription>Personal</sicDescription>\n"
                    + "<phone1>phone_1</phone1>\n"
                    + "<phone1Ext/>\n"
                    + "<phone2/>\n"
                    + "<phone2Ext/>\n"
                    + "<phone3/>\n"
                    + "<phone3Ext/>\n"
                    + "<faxPhone/>\n"
                    + "<faxPhoneExt/>\n"
                    + "<emailAddr1/>\n"
                    + "<emailAddr2/>\n"
                    + "<addressLine1>ADDRESS_LINE_1</addressLine1>\n"
                    + "<addressLine2/>\n"
                    + "<addressLine3/>\n"
                    + "<addressCountry>UAE    </addressCountry>\n"
                    + "<city>Abu Dhabi</city>\n"
                    + "<state>Abu Dhabi</state>\n"
                    + "<zip>40448</zip>\n"
                    + "<province/>\n"
                    + "<territory/>\n"
                    + "<republic/>\n"
                    + "<town/>\n"
                    + "<district/>\n"
                    + "<region/>\n"
                    + "<raceId>5011</raceId>\n"
                    + "<race>EGYPTIAN</race>\n"
                    + "<employee>EMPL</employee>\n"
                    + "<politicalExposed/>\n"
                    + "<pepCategoryId/>\n"
                    + "<pepCategory/>\n"
                    + "<countryOfBirth/>\n"
                    + "<countryOfBirthDesc/>\n"
                    + "<totAsstRngId/>\n"
                    + "<annTurnRngId/>\n"
                    + "<natId/>\n"
                    + "<nationalityDesc/>\n"
                    + "<natId2/>\n"
                    + "<nationalityDesc2/>\n"
                    + "<annIncRngId/>\n"
                    + "<annNcRngValue/>\n"
                    + "<fullName>FIRST_NAME LAST_NAME</fullName>\n"
                    + "</customer>\n"
                    + "<returnStatus>\n"
                    + "<returnCode>0000</returnCode>\n"
                    + "<returnCodeDesc>Successful</returnCodeDesc>\n"
                    + "</returnStatus>\n"
                    + "<returnStatusProvider>\n"
                    + "<returnCodeProvider>0000</returnCodeProvider>\n"
                    + "<returnCodeDescProvider>Successful</returnCodeDescProvider>\n"
                    + "</returnStatusProvider>\n"
                    + "<requestTime>20210527084652479</requestTime>\n"
                    + "</eAI_BODY>\n"
                    + "</NS1:getAccountCustDetailReply>]]>\n"
                    + "</data>\n"
                    + "         <returnStatus>\n"
                    + "            <returnCode>0000</returnCode>\n"
                    + "            <returnCodeDesc>Successful</returnCodeDesc>\n"
                    + "         </returnStatus>\n"
                    + "         <returnStatusProvider>\n"
                    + "            <returnCodeProvider>0000</returnCodeProvider>\n"
                    + "            <returnCodeDescProvider>Successful</returnCodeDescProvider>\n"
                    + "         </returnStatusProvider>\n"
                    + "      </NS1:esbServiceReply>\n"
                    + "   </soapenv:Body>\n"
                    + "</soapenv:Envelope>";


    public static void main1(String[] args) throws Exception {

        SOAPMessage message = MessageFactory.newInstance().createMessage(null, new ByteArrayInputStream(response1.getBytes("UTF-8")));


        XMLInputFactory xif = XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);
        XMLStreamReader xsr = xif.createXMLStreamReader(message.getSOAPPart().getContent());
        xsr.nextTag(); // Advance to Envelope tag
        xsr.nextTag(); // Advance to Body tag
        xsr.nextTag(); // Advance to getNumberResponse tag

        JAXBContext jc = JAXBContext.newInstance(ESBServiceReply.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement<ESBServiceReply> je = unmarshaller.unmarshal(xsr, ESBServiceReply.class);
        ESBServiceReply reply = je.getValue();


        System.out.println(reply);

    }

    public static void main(String[] args) throws Exception {
        String s = "Hello Dr. World Dr.";

        if (s != null && "Dr." != null && s.startsWith("Dr.")) {
            s= s.substring("Dr. ".length());
        }

        System.out.print(s);
    }


}
