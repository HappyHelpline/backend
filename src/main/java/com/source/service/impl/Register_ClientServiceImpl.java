package com.source.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.criterion.Projections;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.source.dao.Appoint_BookDao;
import com.source.dao.Register_ClientDao;
import com.source.dao.Register_VolunteerDao;
import com.source.model.Register_Client;
import com.source.model.Register_Volunteer;
import com.source.service.Register_ClientService;
import com.source.util.CommonMethod;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;

@Service
@Slf4j
public class Register_ClientServiceImpl implements Register_ClientService {

	@Autowired
	Register_ClientDao dao;

	@Autowired
	Register_VolunteerDao daovo;
	
	@Autowired
	Appoint_BookDao daoap;

	@Override
	public JSONObject save(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		try {
			if (!object.get("username").equals("") && !object.get("password").equals("")
					&& !object.get("contact_no").equals("") && !object.get("email_add").equals("")) {
				Register_Client client = new Register_Client();
				client.setUsername(object.get("username").trim());
				client.setPassword(object.get("password").trim());
				client.setEmail_add(object.get("email_add").trim());
				client.setContact_no(object.get("contact_no").trim());
				client.setTrndate(CommonMethod.getDate());
				boolean flag = dao.save(client);
				if (flag) {
					json.put("msg", "Successfuly Submit");
					json.put("status", "1");
				} else {
					json.put("msg", "Please Check And Retry !!!!!");
					json.put("status", "0");
				}
			} else {
				json.put("msg", "Please Try Again !!!!!");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "--->" + e);
			log.error("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "--->" + e);
		}

		return json;
	}

	@Override
	public JSONObject getlogin(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		try {
			if (!object.get("username").equals("") && !object.get("password").equals("")) {
				boolean flag = dao.getData(object.get("username").trim(), object.get("password").trim());
				if (flag) {
					JSONObject getlist=new JSONObject();
					getlist.put("client_id",object.get("username"));
					Register_Client client = dao.getByUserID(object.get("username").trim());
					if (client != null) {
						JSONObject client_json = new JSONObject();
						client_json.put("username", client.getUsername());
						client_json.put("mobile", client.getContact_no());
						client_json.put("email", client.getEmail_add());
						json.put("client", client_json);
						List<Register_Volunteer> list = daovo.getlist();
						org.json.JSONArray array=new org.json.JSONArray();
						for (Register_Volunteer vol : list) {
							JSONObject json_list = new JSONObject();
							json_list.put("first_name",vol.getFirst_name());
							json_list.put("volunteer_id",vol.getUsername());
							json_list.put("last_name", vol.getLast_name());
							json_list.put("gender_type", vol.getGender_type());
							json_list.put("contact_no", vol.getContact_no());
							json_list.put("alternate_no", vol.getAlternate_no());
							json_list.put("email_add", vol.getEmail_add());
							json_list.put("address", vol.getAddress());
							json_list.put("state", vol.getState());
							json_list.put("country",vol.getCountry());
							json_list.put("about_you", vol.getAbout_you());
							json_list.put("language",vol.getLanguage());
							json_list.put("whyjoinus",vol.getWhyjoinus());
							json_list.put("schedule_time", vol.getSchedule_time());
							json_list.put("professional",vol.getProfessional());
							json_list.put("id",vol.getId());
							array.put(json_list);
						}
						json.put("volunter",array);
						
						json.put("appointment", daoap.get5OR10(getlist));
					
						json.put("msg", "Successfuly get Data");
						json.put("status", "1");
					} else {
						json.put("msg", "User Does Not Exist");
						json.put("status", "0");
					}
				} else {
					json.put("msg", "Invalid Credentials !!!! Try Again..");
					json.put("status", "0");
				}
			} else {
				json.put("msg", "Please Enter Valid Data");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("Register_ClientServiceImpl  getlogin\t" + CommonMethod.getDate() + "--->" + e);
			log.error("Register_ClientServiceImpl getlogin\t" + CommonMethod.getDate() + "--->" + e);
		}
		return json;
	}
	
	@Override
	public JSONObject getValidUSR(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		try {
			if (!object.get("username").equals("")) {
				boolean flag = dao.validUSR(object.get("username").trim());
				if (flag) {
					json.put("msg", "** Volunteer Is Already Exist **");
					json.put("status", "0");
				} else {
					json.put("msg", "** Volunteer Is New  **");
					json.put("status", "1");
				}
			} else {
				json.put("msg", "Please Enter Valid Data");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("Register_VolunteerServiceimpl  getlogin\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_VolunteerServiceimpl  getlogin\t" + CommonMethod.getDate() + "=" + e);
		}
		return json;
	}

}
