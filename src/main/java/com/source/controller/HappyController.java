package com.source.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.soruce.mail.MailTransfer;
import com.soruce.mail.SendMail;
import com.source.service.Appoint_BookService;
import com.source.service.Register_ClientService;
import com.source.service.Register_VolunteerService;
import com.source.util.CommonMethod;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin
public class HappyController {

	@Autowired
	Register_ClientService clinet;

	@Autowired
	Register_VolunteerService volunteer;

	@Autowired
	Appoint_BookService appoint;

	@RequestMapping(value = "/clientsave", method = RequestMethod.POST)
	public String saveclient(@RequestBody LinkedHashMap<String, String> object, HttpServletRequest httpServletRequest) {
		JSONObject json = new JSONObject();
		try {
			if (!object.isEmpty()) {
				json = clinet.save(object);
			} else {
				json.put("msg", "Your Request Is Empty !!! Please Try Again..");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("/clientsave \t" + CommonMethod.getDate() + "--->" + e);
			log.error("/clientsave \t" + CommonMethod.getDate() + "--->" + e);
		}
		return json.toString();
	}

	@RequestMapping(value = "/volunteersave", method = RequestMethod.POST)
	public String savevolunteer(@RequestBody LinkedHashMap<String, String> object,
			HttpServletRequest httpServletRequest) {
		JSONObject json = new JSONObject();
		try {
			if (!object.isEmpty()) {
				json = volunteer.save(object);
			} else {
				json.put("msg", "Your Request Is Empty !!! Please Try Again..");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("/volunteersave \t" + CommonMethod.getDate() + "--->" + e);
			log.error("/volunteersave \t" + CommonMethod.getDate() + "--->" + e);
		}
		return json.toString();
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getVerify(@RequestBody LinkedHashMap<String, String> object, HttpServletRequest httpServletRequest) {
		JSONObject json = new JSONObject();
		try {
			if (!object.isEmpty()) {
				if (object.get("type").equals("client")) {
					json = clinet.getlogin(object);
				} else if (object.get("type").equals("volunteer")) {
					json = volunteer.getlogin(object);
				} else {
					json.put("msg", "Please Enter Type !!!!!");
					json.put("status", "0");
				}
			} else {
				json.put("msg", "Your Request Is Empty !!! Please Try Again..");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("/login \t" + CommonMethod.getDate() + "--->" + e);
			log.error("/login \t" + CommonMethod.getDate() + "--->" + e);
		}
		return json.toString();
	}

	@RequestMapping(value = "/appointbook", method = RequestMethod.POST)
	public String saveappointbook(@RequestBody LinkedHashMap<String, String> object,
			HttpServletRequest httpServletRequest) {
		JSONObject json = new JSONObject();
		try {
			if (!object.isEmpty()) {
				json = appoint.save(object);
			} else {
				json.put("msg", "Your Request Is Empty !!! Please Try Again..");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("/appointbook \t" + CommonMethod.getDate() + "--->" + e);
			log.error("/appointbook \t" + CommonMethod.getDate() + "--->" + e);
		}
		return json.toString();
	}

	@RequestMapping(value = "/getappoint", method = RequestMethod.POST)
	public String getappoint(@RequestBody LinkedHashMap<String, String> object, HttpServletRequest httpServletRequest) {
		JSONObject json = new JSONObject();
		try {
			if (!object.isEmpty()) {
				json = appoint.getAllAppoint(object);
			} else {
				json.put("msg", "Your Request Is Empty !!! Please Try Again..");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("/login \t" + CommonMethod.getDate() + "--->" + e);
			log.error("/login \t" + CommonMethod.getDate() + "--->" + e);
		}
		return json.toString();
	}

	@RequestMapping(value = "/validusr", method = RequestMethod.POST)
	public String validusr(@RequestBody LinkedHashMap<String, String> object, HttpServletRequest httpServletRequest) {
		JSONObject json = new JSONObject();
		try {
			if (!object.isEmpty()) {
				json = clinet.getValidUSR(object);
			} else {
				json.put("msg", "Your Request Is Empty !!! Please Try Again..");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("/login \t" + CommonMethod.getDate() + "--->" + e);
			log.error("/login \t" + CommonMethod.getDate() + "--->" + e);
		}
		return json.toString();
	}
	
	@RequestMapping(value = "/aopoperation", method = RequestMethod.POST)
	public String aopoperation(@RequestBody LinkedHashMap<String, String> object, HttpServletRequest httpServletRequest) {
		JSONObject json = new JSONObject();
		try {
			if (!object.isEmpty()) {
				if (object.containsKey("appoint_id")) {
					json = appoint.updateByStatus(object);
				}else{
					json.put("msg", "Someting Is Missing  !!!!!!");
					json.put("status", "0");
				}
			} else {
				json.put("msg", "Your Request Is Empty !!! Please Try Again..");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("/login \t" + CommonMethod.getDate() + "--->" + e);
			log.error("/login \t" + CommonMethod.getDate() + "--->" + e);
		}
		return json.toString();
	}
	
	@RequestMapping(value = "/scheduletym", method = RequestMethod.POST)
	public String scheduletym(@RequestBody LinkedHashMap<String, String> object, HttpServletRequest httpServletRequest) {
		JSONObject json = new JSONObject();
		try {
			if (!object.isEmpty()) {
				if (object.containsKey("volunteer_id")) {
					json = volunteer.schedule_update(object);
				}else{
					json.put("msg", "Someting Is Missing  !!!!!!");
					json.put("status", "0");
				}
			} else {
				json.put("msg", "Your Request Is Empty !!! Please Try Again..");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("/login \t" + CommonMethod.getDate() + "--->" + e);
			log.error("/login \t" + CommonMethod.getDate() + "--->" + e);
		}
		return json.toString();
	}

	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public String sendmail(@RequestBody LinkedHashMap<String, String> object, HttpServletRequest httpServletRequest) {
		JSONObject json = null;
		try {
			if (!object.isEmpty()) {
				SendMail sendmail=new SendMail();
				json=new JSONObject(sendmail.callMailService(object));
			} 
		} catch (Exception e) {
			
			log.info("/sendmail \t" + CommonMethod.getDate() + "--->" + e);
			log.error("/sendmail \t" + CommonMethod.getDate() + "--->" + e);
		}
		return json.toString();
	}

}
