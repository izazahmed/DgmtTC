package com.dgmt.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Converter
{
	public static String SHA256(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException
	{
		MessageDigest md = MessageDigest.getInstance("SHA-256"); 
		md.update(text.getBytes());
	    return new sun.misc.BASE64Encoder().encode(md.digest());
	}	
}