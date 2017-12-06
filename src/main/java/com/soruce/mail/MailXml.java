package com.soruce.mail;

import java.util.LinkedHashMap;

import com.source.util.CommonPath;

public class MailXml {

	public static String createMailxml(String emailId, String username, String loginid, String password) {

		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<MailSend>" + "<ApiKey>" + CommonPath.apikey
				+ "</ApiKey>" + "<Password>" + CommonPath.apipasswd + "</Password>" + "<Request>" + "<Mail>"
				+ "<Subject>" + com.source.util.EncoderDecoder.encodeBase64String((CommonPath.subject).getBytes())
				+ "</Subject>" + "<FromName>"
				+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.fromname).getBytes()) + "</FromName>"
				+ "<FromEmail>" + CommonPath.fromemail + "</FromEmail>" + "<ReplyTo>" + CommonPath.fromemail
				+ "</ReplyTo>" + "<Format>" + CommonPath.format + "</Format>" + "<ContentSource>"
				+ CommonPath.contentsource + "</ContentSource>" + "<Content>"
				+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.content).getBytes()) + "</Content>"
				+ "<TextContent>"
				+ com.source.util.EncoderDecoder
						.encodeBase64String((CommonPath.getTextContent(username, loginid, password)).getBytes())
				+ "</TextContent>" + "<AutoUnsubscribeLink>" + CommonPath.autounsubscribelink + "</AutoUnsubscribeLink>"
				+ "</Mail>" + "<Recipients>" + "<Recipient>" + "<EmailId>" + emailId + "</EmailId>" + "</Recipient>"
				+ "</Recipients>" + "</Request>" + "</MailSend>";
		return xml;
	}

	public static String createAppointMailVOL(LinkedHashMap<String, String> data) {
		String xml=null;
		if (!data.isEmpty()) {
			if (data.containsKey("VOL_Email") && data.containsKey("CL_Email") && data.containsKey("VOL_Name") && data.containsKey("CL_Name")
					&& data.containsKey("Appoint_ID") && data.containsKey("Sechdule_Date") && data.containsKey("Status")) {
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<MailSend>" + "<ApiKey>" + CommonPath.apikey
				+ "</ApiKey>" + "<Password>" + CommonPath.apipasswd + "</Password>" + "<Request>" + "<Mail>"
				+ "<Subject>" + com.source.util.EncoderDecoder.encodeBase64String((CommonPath.appointment).getBytes())
				+ "</Subject>" + "<FromName>"
				+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.fromname).getBytes()) + "</FromName>"
				+ "<FromEmail>" + CommonPath.fromemail + "</FromEmail>" + "<ReplyTo>" + CommonPath.fromemail
				+ "</ReplyTo>" + "<Format>" + CommonPath.format + "</Format>" + "<ContentSource>"
				+ CommonPath.contentsource + "</ContentSource>" + "<Content>"
				+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.content).getBytes()) + "</Content>"
				+ "<TextContent>"
				+ com.source.util.EncoderDecoder.encodeBase64String(
						(CommonPath.forAppointmentVOL(data.get("VOL_Name"), data.get("CL_Name"), data.get("Appoint_ID"), data.get("Sechdule_Date"), data.get("Status"))).getBytes())
				+ "</TextContent>" + "<AutoUnsubscribeLink>" + CommonPath.autounsubscribelink + "</AutoUnsubscribeLink>"
				+ "</Mail>" + "<Recipients>" + "<Recipient>" + "<EmailId>" + data.get("CL_Email") + "</EmailId>" + "</Recipient>"
				+ "</Recipients>" + "</Request>" + "</MailSend>";
			}
		}
		return xml;
	}

	public static String createAppointMailCL(LinkedHashMap<String, String> data) {
		String xml=null;
		if (!data.isEmpty()) {
			if (data.containsKey("VOL_Email") && data.containsKey("CL_Email") && data.containsKey("VOL_Name") && data.containsKey("CL_Name")
					&& data.containsKey("Appoint_ID") && data.containsKey("Sechdule_Date") && data.containsKey("Status")) {
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<MailSend>" + "<ApiKey>" + CommonPath.apikey
						+ "</ApiKey>" + "<Password>" + CommonPath.apipasswd + "</Password>" + "<Request>" + "<Mail>"
						+ "<Subject>" + com.source.util.EncoderDecoder.encodeBase64String((CommonPath.appointment).getBytes())
						+ "</Subject>" + "<FromName>"
						+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.fromname).getBytes()) + "</FromName>"
						+ "<FromEmail>" + CommonPath.fromemail + "</FromEmail>" + "<ReplyTo>" + CommonPath.fromemail
						+ "</ReplyTo>" + "<Format>" + CommonPath.format + "</Format>" + "<ContentSource>"
						+ CommonPath.contentsource + "</ContentSource>" + "<Content>"
						+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.content).getBytes()) + "</Content>"
						+ "<TextContent>"
						+ com.source.util.EncoderDecoder.encodeBase64String(
								(CommonPath.forAppointmentCL(data.get("VOL_Name"), data.get("CL_Name"), data.get("Appoint_ID"), data.get("Sechdule_Date"), data.get("Status"))).getBytes())
						+ "</TextContent>" + "<AutoUnsubscribeLink>" + CommonPath.autounsubscribelink + "</AutoUnsubscribeLink>"
						+ "</Mail>" + "<Recipients>" + "<Recipient>" + "<EmailId>" + data.get("CL_Email") + "</EmailId>" + "</Recipient>"
						+ "</Recipients>" + "</Request>" + "</MailSend>";
				
			}
		}
		return xml;
	}
	
	
	public static String infoAppointMailVOL(LinkedHashMap<String, String> data) {
		String xml=null;
		if (!data.isEmpty()) {
			if (data.containsKey("VOL_Email") && data.containsKey("CL_Email") && data.containsKey("VOL_Name") && data.containsKey("CL_Name")
					&& data.containsKey("Appoint_ID") && data.containsKey("Sechdule_Date") && data.containsKey("Status")) {
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + 
					"<MailSend>" +
						"<ApiKey>" + CommonPath.apikey	+ "</ApiKey>" +
						"<Password>" + CommonPath.apipasswd + "</Password>" + 
						"<Request>" + 
						"<Mail>"
						+ "<Subject>" + com.source.util.EncoderDecoder.encodeBase64String((CommonPath.appointment).getBytes())+ "</Subject>" + 
						"<FromName>"+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.fromname).getBytes()) + "</FromName>"
						+ "<FromEmail>" + CommonPath.fromemail + "</FromEmail>" + 
						"<ReplyTo>" + CommonPath.fromemail	+ "</ReplyTo>" +
						"<Format>" + CommonPath.format + "</Format>" + 
						"<ContentSource>"+ CommonPath.contentsource + "</ContentSource>" + 
						"<Content>"	+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.status).getBytes()) + "</Content>"
						+ "<TextContent>"	+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.infoAppointmentVOL(data.get("VOL_Name"), data.get("CL_Name"), data.get("Appoint_ID"), data.get("Sechdule_Date"), data.get("Status"))).getBytes())
						+ "</TextContent>" + 
						"<AutoUnsubscribeLink>" + CommonPath.autounsubscribelink + "</AutoUnsubscribeLink>"
						+ "</Mail>" +
						"<Recipients>" + 
						"<Recipient>" + 
						"<EmailId>" + data.get("VOL_Email") + "</EmailId>" +
						"</Recipient>"
					+ "</Recipients>" + 
				"</Request>" + 
		"</MailSend>";
			}
		}
		return xml;
	}

	public static String infoAppointMailCL(LinkedHashMap<String, String> data) {
		String xml=null;
		if (!data.isEmpty()) {
			if (data.containsKey("VOL_Email") && data.containsKey("CL_Email") && data.containsKey("VOL_Name") && data.containsKey("CL_Name")
					&& data.containsKey("Appoint_ID") && data.containsKey("Sechdule_Date") && data.containsKey("Status")) {
				xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					"<MailSend>" +
						"<ApiKey>" + CommonPath.apikey	+ "</ApiKey>" +
					"<Password>" + CommonPath.apipasswd + "</Password>" +
						"<Request>" + 
					"<Mail>"
						+ "<Subject>" + com.source.util.EncoderDecoder.encodeBase64String((CommonPath.appointment).getBytes())	+ "</Subject>" + 
					"<FromName>"+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.fromname).getBytes()) + "</FromName>"
						+ "<FromEmail>" + CommonPath.fromemail + "</FromEmail>" + 
					"<ReplyTo>" + CommonPath.fromemail	+ "</ReplyTo>" + 
						"<Format>" + CommonPath.format + "</Format>" + 
					"<ContentSource>"+ CommonPath.contentsource + "</ContentSource>" +
						"<Content>"	+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.status).getBytes()) + "</Content>"
						+ "<TextContent>"+ com.source.util.EncoderDecoder.encodeBase64String((CommonPath.infoAppointmentCL(data.get("VOL_Name"), data.get("CL_Name"), data.get("Appoint_ID"), data.get("Sechdule_Date"), data.get("Status"))).getBytes())+ "</TextContent>" + 
						"<AutoUnsubscribeLink>" + CommonPath.autounsubscribelink + "</AutoUnsubscribeLink>"
						+ "</Mail>" +
						"<Recipients>" +
						"<Recipient>" + 
						"<EmailId>" + data.get("CL_Email") + "</EmailId>" + 
					"</Recipient>"
				+ "</Recipients>" + 
		"</Request>" +
"</MailSend>";
				
			}
		}
		return xml;
	}
}