package com.source.util;

public class EncoderDecoder {
	
	public static final String CHARACTER_ENCODING = "UTF-8";
	
	public static String encodeBase64String(byte[] bytes) {
		return new String(java.util.Base64.getEncoder().encode(bytes));
	}
	
	public static byte[] decodeBase64StringTOByte(String stringData) throws Exception {
		return java.util.Base64.getDecoder().decode(stringData.getBytes(CHARACTER_ENCODING));
	}
	
}

