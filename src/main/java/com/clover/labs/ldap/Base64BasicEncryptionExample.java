/*
 * Copyright (c) 2005-2009 Clear2Pay nv/sa. All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Clear2Pay nv/sa. ("Confidential Information").
 * It may not be copied or reproduced in any manner without the express written permission of Clear2Pay nv/sa.
 */
 
package com.clover.labs.ldap;

import java.nio.charset.Charset;

public class Base64BasicEncryptionExample {

    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");

    public static void main(String[] args) throws Exception {


    	/*// Getting encoder
        Base64.Encoder encoder = Base64.getEncoder();  
      
        // Encoding string  
        String str = encoder.encodeToString("1qaz@WSX1234".getBytes());  
        System.out.println("Encoded string Base64: "+str);  
        
        // Getting decoder  
        Base64.Decoder decoder = Base64.getDecoder();  
        // Decoding string  
        String dStr = new String(decoder.decode(str));  
        System.out.println("Decoded string Base64: "+dStr);
        
        String encoded_string = PasswordUtil.encode("3FRUJtY8KwQv3mJgbRRg");
        System.out.println("\n\nEncoded Algo :" + PasswordUtil.getCryptoAlgorithm(encoded_string));
        System.out.println("Encoded string Base64-XOR: "+encoded_string);  
        System.out.println("Decoded string Base64-XOR: " + PasswordUtil.decode("3FRUJtY8KwQv3mJgbRRg"));*/








        System.out.println("----------------------------------------");
        byte[] basicAuthenticationBytes = "password".getBytes(CHARSET_UTF8);
        System.out.println("Basic " + Base64.encodeToString(basicAuthenticationBytes, false));

        System.out.println(new String(Base64.decode("YnZhMTpwYXNzd29yZA==")));
    }  
	
}  