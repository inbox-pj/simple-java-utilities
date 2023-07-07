package com.clear2pay.labs.ldap;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class LDAPConnection {
	
	static String searchUser = "(&(objectClass=user)(|(cn=yyUser)(sAMAccountName=yyUser)))";
	static String replaceUserPattern = "yyUser";
	static String username = "bva111";
	static String baseSearch = "DC=rbpsh,DC=bank";
	
	public static void main(String[] args) throws Exception {
		Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.58.235.96:389");
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, "CN=svc_bpm,OU=Service Accounts,OU=Riyad Bank,DC=rbpsh,DC=bank");
        env.put(Context.SECURITY_CREDENTIALS, "P@ssword123!");
        env.put(Context.REFERRAL, "follow");

        DirContext ctx = new InitialDirContext(env);
        
        SearchControls ctls = new SearchControls();
        ctls.setTimeLimit(Integer.valueOf(6000));
        ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        
        String filter = replaceAll(searchUser, replaceUserPattern, username);
        NamingEnumeration results = ctx.search(baseSearch, filter, ctls);

        String dn = baseSearch;
        List<String> groups = new ArrayList<String>();

        SearchResult searchResult = (SearchResult) results.next();
        Attributes attributes = searchResult.getAttributes();
        
        dn = (String) attributes.get("distinguishedName").get();
        String displayName = (String) attributes.get("displayname").get();
        String mail = (String) attributes.get("mail").get();
        
        // mail=mail: pjaiswal@gmail.com
        // st=st: UP
        // company=company: FIS
        //sn=sn: Jaiswal
        //name=name: Pankaj J
        //initials=initials: Mr
        //telephonenumber=telephoneNumber: 1234
        //postalcode=postalCode: 201309
        //wwwhomepage=wWWHomePage: www.pjaiswal.com
        //co=co: India
        //homephone=homePhone: 1
        //mobile=mobile: 3
        //givenname=givenName: Pankaj
        //displayname=displayName: PankajJaiswal
        //department=department: Admin
        //streetaddress=streetAddress: C-5 Sec-126
        //countrycode=countryCode: 356
        //l=l: Noida
        //postofficebox=postOfficeBox: 201301
        
        
        System.out.println("Obtained principal '" + dn + "' for user " + username + ".");
        
        try {
            Attribute attribute = attributes.get("memberOf");
            if (attribute != null) {
            	System.out.println("Obtained from AD memberOf attribute='" + attribute + "' size="
                        + attribute.size() + " for user " + username + ".");
                for (int i = 0; i < attribute.size(); i++) {
                    String group = (String) attribute.get(i);
                    System.out.println(
                            "Obtained from AD i=" + i + " memberOf='" + group + "' for user " + username + ".");
                    if (group != null) {
                        int nameStart = group.indexOf("="); //cn=abc
                        if (nameStart >= 0) {
                            int nameEnd = group.indexOf(",", nameStart);
                            if (group.length() > nameStart + 1) {
                                if (nameEnd < 0) {
                                    group = group.substring(nameStart + 1);
                                } else {
                                    group = group.substring(nameStart + 1, nameEnd);
                                }
                            } else {
                            	System.out.println( "Obtained invalid group '" + group + "' for user "
                                        + username + ". Ignore.");
                                continue;
                            }
                        }
                        System.out.println(
                                "Obtained group '" + group + "' for user " + username + ".");
                        if (group.length() > 0) {
                            groups.add(group);
                        }
                    } else {
                    	System.out.println(
                                "Could not obtain from AD memberOf attribute for user " + username + ".");
                    }
                }
            } else {
            	System.out.println(
                        "Could not obtain from AD memberOf attribute for user " + username + ".");
            }
            //CHECKSTYLE:OFF
        } catch (Exception e) {//CHECKSTYLE:ON
        	System.out.println("Failure finding groups for user " + dn + ". " + e);
            //Ignore
        }
	}
	
	private static String replaceAll(String baseString, String replacementTag, String replacementValue) {
        int replacementIndex = 0;
        if ((baseString == null) || (replacementTag == null) || (replacementValue == null)) {
            return baseString;
        }

        if ((baseString.trim().length() == 0) || (replacementTag.trim().length() == 0)) {
            return baseString;
        }
        StringBuffer baseStringBuf = new StringBuffer(baseString);

        while (replacementIndex >= 0) {
            replacementIndex = baseStringBuf.indexOf(replacementTag);
            if (replacementIndex >= 0) {
                baseStringBuf.replace(replacementIndex, replacementIndex + replacementTag.length(), replacementValue);
            }
        }
        return baseStringBuf.toString();
    }
}

