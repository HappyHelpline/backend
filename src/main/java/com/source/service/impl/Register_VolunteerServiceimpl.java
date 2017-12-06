package com.source.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soruce.mail.SendMail;
import com.source.dao.Appoint_BookDao;
import com.source.dao.Register_ClientDao;
import com.source.dao.Register_VolunteerDao;
import com.source.model.Register_Client;
import com.source.model.Register_Volunteer;
import com.source.service.Register_VolunteerService;
import com.source.util.CommonMethod;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Register_VolunteerServiceimpl implements Register_VolunteerService {

	@Autowired
	Register_VolunteerDao dao;

	@Autowired
	Register_ClientDao daocl;

	@Autowired
	Appoint_BookDao daoap;
	
	@Override
	public JSONObject save(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		try {
			if (!object.get("first_name").equals("") && !object.get("last_name").equals("")
					&& !object.get("gender_type").equals("") && !object.get("contact_no").equals("")
					&& !object.get("alternate_no").equals("") && !object.get("email_add").equals("")
					&& !object.get("address").equals("") && !object.get("state").equals("")
					&& !object.get("country").equals("") && !object.get("professional").equals("")
					&& !object.get("about_you").equals("") && !object.get("language").equals("")
					&& !object.get("whyjoinus").equals("") && !object.get("schedule_time").equals("")) {
				LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
				Register_Volunteer volunteer = new Register_Volunteer();
				String username = "VOL" + CommonMethod.getUniqeID();
				String pwd = CommonMethod.getUniqeID();
				volunteer.setPassword(pwd);
				volunteer.setUsername(username);
				volunteer.setFirst_name(object.get("first_name").trim());
				data.put("emailId", object.get("email_add").trim());
				data.put("username", object.get("first_name").trim());
				data.put("loginid", object.get("email_add").trim());
				data.put("password", pwd);
				volunteer.setLast_name(object.get("last_name").trim());
				volunteer.setGender_type(object.get("gender_type").trim());
				volunteer.setEmail_add(object.get("email_add").trim());
				volunteer.setContact_no(object.get("contact_no").trim());
				volunteer.setAlternate_no(object.get("alternate_no").trim());
				volunteer.setAddress(object.get("address").trim());
				volunteer.setCountry(object.get("country").trim());
				volunteer.setState(object.get("state").trim());
				volunteer.setProfessional(object.get("country").trim());
				volunteer.setAbout_you(object.get("about_you").trim());
				volunteer.setLanguage(object.get("language").trim());
				volunteer.setWhyjoinus(object.get("whyjoinus").trim());
				volunteer.setSchedule_time(object.get("schedule_time").trim());
				volunteer.setTrndate(CommonMethod.getDate().trim());
				boolean flag = dao.save(volunteer);
				if (flag) {
					json.put("volunteer_id", object.get("first_name").trim() + CommonMethod.getDate());
					json.put("password", pwd);
					json.put("msg", "Successfuly Submit");
					json.put("status", "1");
					SendMail sendmail = new SendMail();
					sendmail.callMailService(data);
				} else {
					json.put("msg", "Please Check And Retry !!!!!");
					json.put("status", "0");
				}
			} else {
				json.put("msg", "Please Enter Complete Details !!!  Try Again !!!!!");
				json.put("status", "0");
			}
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("Register_VolunteerServiceimpl  save\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_VolunteerServiceimpl  save\t" + CommonMethod.getDate() + "=" + e);
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
					Register_Volunteer volt = dao.getByUserID(object.get("username").trim());
					
					if (volt != null) {
						JSONObject volt_json = new JSONObject();
						volt_json.put("volunteer_id", volt.getUsername());
						JSONObject getlist=new JSONObject();
						getlist.put("volunteer_id",volt.getUsername());
						volt_json.put("first_name", volt.getFirst_name().trim());
						volt_json.put("last_name", volt.getLast_name().trim());
						volt_json.put("gender_type", volt.getGender_type().trim());
						volt_json.put("email_add", volt.getEmail_add().trim());
						volt_json.put("contact_no", volt.getContact_no().trim());
						volt_json.put("alternate_no", volt.getAlternate_no().trim());
						volt_json.put("address", volt.getAddress().trim());
						volt_json.put("country", volt.getCountry().trim());
						volt_json.put("state", volt.getState().trim());
						volt_json.put("about_you", volt.getAbout_you().trim());
						volt_json.put("language", volt.getLanguage().trim());
						volt_json.put("whyjoinus", volt.getWhyjoinus().trim());
						volt_json.put("schedule_time", volt.getSchedule_time().trim());
						volt_json.put("id", volt.getId());
						json.put("profile", volt_json);
						List<Register_Client> list = daocl.getlist();
						org.json.JSONArray array = new org.json.JSONArray();
						for (Register_Client client : list) {
							JSONObject json_list = new JSONObject();
							json_list.put("username", client.getUsername());
							json_list.put("contact_no", client.getContact_no());
							json_list.put("email_add", client.getEmail_add());
							json_list.put("id", client.getId());
							array.put(json_list);
						}
						json.put("client", array);
						json.put("appointment", daoap.get5OR10(getlist));
						json.put("msg", "Successfuly Login");
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
			log.info("Register_VolunteerServiceimpl  getlogin\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_VolunteerServiceimpl  getlogin\t" + CommonMethod.getDate() + "=" + e);
		}
		return json;
	}

	
	@Override
	public JSONObject schedule_update(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		try {
			if (object.containsKey("volunteer_id")) {
				if (!object.get("volunteer_id").equals("")) {
					boolean flag = dao.update(object);
					if (flag) {
						json.put("msg", "Successfuly Login");
						json.put("status", "1");
					}else{
						json.put("msg", "Something Went Wrong");
						json.put("status", "0");
					}
				} else {
					json.put("msg", "User Does Not Exist");
					json.put("status", "0");
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
