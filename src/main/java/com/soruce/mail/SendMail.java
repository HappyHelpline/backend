package com.soruce.mail;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Component;

import com.source.util.CommonMethod;
import com.source.util.CommonPath;

import lombok.extern.log4j.Log4j;

@Log4j
public class SendMail {

	public static String callMailService(LinkedHashMap<String, String> requestPayload) {
		String response = null;
		String emailId = null;
		String username = null;
		String loginid = null;
		String password = null;
		JSONObject jsonResponse = null;
		try {
			emailId = requestPayload.get("emailId");
			username = requestPayload.get("username");
			loginid = requestPayload.get("loginid");
			password = requestPayload.get("password");
			String requestPayloadXml = MailXml.createMailxml(emailId, username, loginid, password);
			String requestUrl = CommonPath.mailrequesturl;
			response = MailTransfer.postDataEmail(requestUrl, requestPayloadXml);
			if (response != null) {
				jsonResponse = XML.toJSONObject(response);
				return jsonResponse.toString();
			}
		} catch (Exception e) {
			log.info("SendMail   callMailService \t" + CommonMethod.getDate() + "--->" + e);
			log.error("SendMail  callMailService  \t" + CommonMethod.getDate() + "--->" + e);
		}
		return jsonResponse.toString();
	}

	public static String sendAppoint(LinkedHashMap<String, String> requestPayload) {
		String responseCL = null;
		String responseVOL = null;
		
		JSONObject jsonResponse = null;
		try {
			String requestPayloadXmlVOL = MailXml.createAppointMailVOL(requestPayload);
			String requestPayloadXmlCL = MailXml.createAppointMailCL(requestPayload);
			String requestUrl = CommonPath.mailrequesturl;
			responseVOL = MailTransfer.postDataEmail(requestUrl, requestPayloadXmlVOL);
			responseCL = MailTransfer.postDataEmail(requestUrl, requestPayloadXmlCL);
			if (responseVOL != null) {
				jsonResponse = XML.toJSONObject(responseVOL);
				return jsonResponse.toString();
			}
			if (responseCL != null) {
				jsonResponse = XML.toJSONObject(responseCL);
				return jsonResponse.toString();
			}
		} catch (Exception e) {
			log.info("SendMail   callMailService \t" + CommonMethod.getDate() + "--->" + e);
			log.error("SendMail  callMailService  \t" + CommonMethod.getDate() + "--->" + e);
		}
		return jsonResponse.toString();
	}
	
	public static String infoAppoint(LinkedHashMap<String, String> requestPayload) {
		String responseCL = null;
		String responseVOL = null;
		
		JSONObject jsonResponse = null;
		try {
			String requestPayloadXmlVOL = MailXml.infoAppointMailVOL(requestPayload);
			String requestPayloadXmlCL = MailXml.infoAppointMailCL(requestPayload);
			String requestUrl = CommonPath.mailrequesturl;
			responseVOL = MailTransfer.postDataEmail(requestUrl, requestPayloadXmlVOL);
			responseCL = MailTransfer.postDataEmail(requestUrl, requestPayloadXmlCL);
			if (responseVOL != null) {
				jsonResponse = XML.toJSONObject(responseVOL);
				return jsonResponse.toString();
			}
			if (responseCL != null) {
				jsonResponse = XML.toJSONObject(responseCL);
				return jsonResponse.toString();
			}
		} catch (Exception e) {
			log.info("SendMail   callMailService \t" + CommonMethod.getDate() + "--->" + e);
			log.error("SendMail  callMailService  \t" + CommonMethod.getDate() + "--->" + e);
		}
		return jsonResponse.toString();
	}
}
