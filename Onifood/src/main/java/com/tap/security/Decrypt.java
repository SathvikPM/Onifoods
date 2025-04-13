package com.tap.security;

import com.tap.secret.Secret;

public class Decrypt {

	    
         public static String decryptUsername(String username) {
        
		String decryptedUsername="";
		for(int i=0;i<username.length();i++) {
			decryptedUsername=decryptedUsername+((char)(username.charAt(i)-Secret.getKey()));
		}
		
		return decryptedUsername;
	}
}
