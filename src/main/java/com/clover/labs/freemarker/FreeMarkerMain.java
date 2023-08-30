package com.clover.labs.freemarker;

import com.clover.labs.xml.Employee;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class FreeMarkerMain {

	public static void main(String[] args) throws Exception {
		Employee request = new Employee("Pankaj");
		
		freemarker.template.Configuration freemarkerConfig = new freemarker.template.Configuration
                (freemarker.template.Configuration.VERSION_2_3_23);
        freemarkerConfig.setClassForTemplateLoading(FreeMarkerMain.class, "/com/clear2pay/rbpsh/inquiry");
        Template template = freemarkerConfig.getTemplate("error.ftl");
        
        Map<String, Object> templateDataMap = new HashMap<>();
        templateDataMap.put("reasonCode", "9999");
        templateDataMap.put("request", request);
        
        StringWriter out = new StringWriter();
        template.process(templateDataMap, out);
        String data = out.getBuffer().toString();
        
        System.out.println(data);
	}

	/*public static void testRequestFreeMarker(ASIMessage request) throws Exception {
		
		freemarker.template.Configuration freemarkerConfig = new freemarker.template.Configuration
                (freemarker.template.Configuration.VERSION_2_3_23);
        freemarkerConfig.setClassForTemplateLoading(FreeMarkerMain.class, "/com/clear2pay/rbpsh/inquiry");
        Template template = freemarkerConfig.getTemplate("error.ftl");
        
        Map<String, Object> templateDataMap = new HashMap<>();
        templateDataMap.put("reasonCode", "9999");
        templateDataMap.put("request", request);
        templateDataMap.put("SARIE_MSG_UTI", request.getSARIEMSGUTI());
        templateDataMap.put("ID_Type", request.getCustIdentificationDetails().getIDType());
        templateDataMap.put("RB_Reference_No", request.getRBReferenceNo());
        
        StringWriter out = new StringWriter();
        template.process(templateDataMap, out);
        String data = out.getBuffer().toString();
        
        System.out.println(data);
	}*/
	
    /*public static void testResponseFreeMarker(com.riyadbankservices.paymenthubinqrs.ASIMessage response) throws Exception {
		
		freemarker.template.Configuration freemarkerConfig = new freemarker.template.Configuration
                (freemarker.template.Configuration.VERSION_2_3_23);
        freemarkerConfig.setClassForTemplateLoading(FreeMarkerMain.class, "/com/clear2pay/rbpsh/inquiry");
        Template template = freemarkerConfig.getTemplate("error.ftl");
        
        Map<String, Object> templateDataMap = new HashMap<>();
        templateDataMap.put("reasonCode", "9999");
        templateDataMap.put("request", response);
        templateDataMap.put("SARIE_MSG_UTI", response.getSARIEMSGUTI());
        templateDataMap.put("ID_Type", response.getCustIdentificationDetails().getIDType());
        templateDataMap.put("RB_Reference_No", response.getRBReferenceNo());
        templateDataMap.put("repeatingGroupList", response.getRepeatingGroup());
        
        StringWriter out = new StringWriter();
        template.process(templateDataMap, out);
        String data = out.getBuffer().toString();
        
        System.out.println(data);
	}*/
}

