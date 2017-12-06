package com.source.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Random;

import com.soruce.mail.SendMail;

public class CommonMethod {

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String today = dateFormat.format(date);
		return today; 
	}
	
	public static String getUniqeID(){
		Random random = new Random();    
		long n = (long) (100000L + random.nextFloat() * 900000L);
		return n+"";
	}
	
	public static String sendMail(LinkedHashMap<String, String> data){
		return SendMail.sendAppoint(data);
	}
	
	public static String infoEmail(LinkedHashMap<String, String> data){
		return SendMail.infoAppoint(data);
	}
	
	
}
