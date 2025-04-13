package com.tap.security;

import com.tap.secret.Secret;

public class Encrypt {
	
	
	public static String encryptUsername(String username) {

		String encryptedUsername="";
		for(int i=0;i<username.length();i++) {
			encryptedUsername=encryptedUsername+((char)(username.charAt(i)+Secret.getKey()));
		}
	
		return encryptedUsername;
	}
	public static String encryptPassword(String password) {

		String encryptedPassword="";
		for(int i=0;i<password.length();i++) {
			encryptedPassword=encryptedPassword+((char)(password.charAt(i)+Secret.getKey()));
		}
	
		return encryptedPassword;
	}

}
