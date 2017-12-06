package com.source.service.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soruce.mail.SendMail;
import com.source.dao.Appoint_BookDao;
import com.source.dao.Register_ClientDao;
import com.source.dao.Register_VolunteerDao;
import com.source.dao.impl.Appoint_BookDaoImpl;
import com.source.model.Appoint_Book;
import com.source.model.Register_Client;
import com.source.model.Register_Volunteer;
import com.source.service.Appoint_BookService;
import com.source.service.Register_VolunteerService;
import com.source.util.CommonMethod;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
@Log4j
public class Appoint_BookServiceImpl implements Appoint_BookService {

	@Autowired
	Appoint_BookDao dao;
	@Autowired
	Register_ClientDao daocl;
	
	@Autowired
	Register_VolunteerDao daovol;
	@Override
	public JSONObject save(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		try {
			if (!object.get("volunteer_id").equals("") && !object.get("client_id").equals("")
					&& !object.get("sechdule_date").equals("") && !object.get("description").equals("")) {
				LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
				Appoint_Book appoint = new Appoint_Book();
				String appoint_id="AP" + CommonMethod.getUniqeID();
				appoint.setAppoint_id(appoint_id);
				appoint.setClient_id(object.get("client_id"));
				appoint.setVolunteer_id(object.get("volunteer_id"));
				appoint.setStatus("Pending");
				appoint.setSchedule_date(object.get("sechdule_date"));
				appoint.setDescription(object.get("description"));
				appoint.setTrndate(CommonMethod.getDate());
				boolean flag = dao.save(appoint);
				if (flag) {
					Register_Volunteer vol=daovol.getByID(object.get("volunteer_id"));
					Register_Client cl=daocl.getByUserID(object.get("client_id"));
					if (cl != null && vol != null) {
						data.put("VOL_Email", vol.getEmail_add());
						data.put("CL_Email", cl.getEmail_add());
						data.put("VOL_Name",vol.getFirst_name());
						data.put("CL_Name", cl.getUsername());
						data.put("Appoint_ID", appoint_id);
						data.put("Sechdule_Date", object.get("sechdule_date"));
						data.put("Status", object.get("sechdule_date"));
						CommonMethod.sendMail(data);
						json.put("msg", "Successfuly Submit");
						json.put("status", "1");
					}else{
						json.put("msg", "Please Check And Retry !!!!!");
						json.put("status", "0");
					}
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
			log.info("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
		}

		return json;
	}

	@Override
	public JSONObject getByEveryOne(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		try {
			json.put("msg", CommonMethod.getDate());
			json.put("status", "0");
		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
		}

		return json;
	}

	@Override
	public JSONObject updateByStatus(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		try {
			if (object.containsKey("appoint_id") && object.containsKey("status")) {
				if (!object.get("status").equals("") &&  !object.get("appoint_id").equals("")) {
					boolean flag=dao.updateByStatus(object.get("status"), object.get("appoint_id"));
					if (flag) {
						Appoint_Book data_data=dao.getByAppoint(object.get("appoint_id"));
						if (data_data != null) {
							Register_Volunteer vol=daovol.getByID(data_data.getVolunteer_id());
							Register_Client cl=daocl.getByUserID(data_data.getClient_id());
							if (cl != null && vol != null) {
									LinkedHashMap<String, String> data = new LinkedHashMap<String, String>();
									data.put("VOL_Email", vol.getEmail_add());
									data.put("CL_Email", cl.getEmail_add());
									data.put("VOL_Name",vol.getFirst_name());
									data.put("CL_Name", cl.getUsername());
									data.put("Appoint_ID", data_data.getAppoint_id());
									data.put("Sechdule_Date", data_data.getSchedule_date());
									data.put("Status", data_data.getStatus());
									CommonMethod.infoEmail(data);
									json.put("msg", "****  Record Succesfully Update ****");
									json.put("status", "1");
							}else{
								json.put("msg", "Please Check And Retry !!!!!");
								json.put("status", "0");
							}
						}else{
							
						}
						
					}else{
						json.put("msg", "****  Someting Have Wrong ****");
						json.put("status", "0");
					}
				}else{
					json.put("msg", "Someting Went Wrong !!!!! Appoint ID Not Found");
					json.put("status", "0");
				}
			} else {
				json.put("msg", "Someting Went Wrong !!!!! Appoint ID Not Found");
				json.put("status", "0");
			}
			} catch (Exception e) {
			json.put("msg", "!!!!! Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
		}

		return json;
	}

	@Override
	public JSONObject getAllAppoint(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		List<Object[]> appoint = null;
		try {
			if (object.containsKey("volunteer_id")) {
				if (!object.get("volunteer_id").equals("") && object.get("volunteer_id") != null) {
					appoint = dao.getAllAppoint(object);
					if (appoint != null && !appoint.isEmpty() && appoint.size()>0) {
						org.json.JSONArray approved = new org.json.JSONArray();
						org.json.JSONArray pending = new org.json.JSONArray();
						org.json.JSONArray reject = new org.json.JSONArray();
						org.json.JSONArray complete = new org.json.JSONArray();
						for (Object[] objects : appoint) {
							
							if (objects[4].equals("Approved")) {
								JSONObject jsonap = new JSONObject();
								jsonap.put("id", objects[0].toString());
								jsonap.put("appoint_id",objects[1].toString());
								jsonap.put("volunteer_id",objects[2].toString());
								jsonap.put("client_id", objects[3].toString());
								jsonap.put("status", objects[4].toString());
								jsonap.put("schedule_date", objects[5].toString());
								jsonap.put("description", objects[6].toString());
								jsonap.put("cl_contact_no", objects[7].toString());
								jsonap.put("cl_email_id", objects[8].toString());
								approved.put(jsonap);
							} else if (objects[4].equals("Pending")) {
								JSONObject jsonpe = new JSONObject();
								jsonpe.put("id", objects[0].toString());
								jsonpe.put("appoint_id",objects[1].toString());
								jsonpe.put("volunteer_id",objects[2].toString());
								jsonpe.put("client_id", objects[3].toString());
								jsonpe.put("status", objects[4].toString());
								jsonpe.put("schedule_date", objects[5].toString());
								jsonpe.put("description", objects[6].toString());
								jsonpe.put("cl_contact_no", objects[7].toString());
								jsonpe.put("cl_email_id", objects[8].toString());
								pending.put(jsonpe);
							} else if (objects[4].equals("Reject")) {
								JSONObject jsonrj = new JSONObject();
								jsonrj.put("id", objects[0].toString());
								jsonrj.put("appoint_id",objects[1].toString());
								jsonrj.put("volunteer_id",objects[2].toString());
								jsonrj.put("client_id", objects[3].toString());
								jsonrj.put("status", objects[4].toString());
								jsonrj.put("schedule_date", objects[5].toString());
								jsonrj.put("description", objects[6].toString());
								jsonrj.put("cl_contact_no", objects[7].toString());
								jsonrj.put("cl_email_id", objects[8].toString());
								reject.put(jsonrj);
							} else if (objects[4].equals("Complete")) {
								JSONObject jsonco = new JSONObject();
								jsonco.put("id", objects[0].toString());
								jsonco.put("appoint_id",objects[1].toString());
								jsonco.put("volunteer_id",objects[2].toString());
								jsonco.put("client_id", objects[3].toString());
								jsonco.put("status", objects[4].toString());
								jsonco.put("schedule_date", objects[5].toString());
								jsonco.put("description", objects[6].toString());
								jsonco.put("cl_contact_no", objects[7].toString());
								jsonco.put("cl_email_id", objects[8].toString());
								complete.put(jsonco);
							}
							json.put("Approved", approved);
							json.put("Pending", pending);
							json.put("Reject", reject);
							json.put("Complete", complete);
							json.put("msg", "Successfuly Found Data");
							json.put("status", "1");
						}
					} else {
						json.put("msg", "Record Not Found !!!!!");
						json.put("status", "0");
					}
				} else {
					json.put("msg", "Please Check And Retry !!!!!");
					json.put("status", "0");
				}
			} else if (object.containsKey("client_id")) {
				if (!object.get("client_id").equals("") && object.get("client_id") != null) {
					appoint = dao.getAllAppoint(object);
					if (appoint != null && !appoint.isEmpty() && appoint.size()>0) {
						org.json.JSONArray approved = new org.json.JSONArray();
						org.json.JSONArray pending = new org.json.JSONArray();
						org.json.JSONArray reject = new org.json.JSONArray();
						org.json.JSONArray complete = new org.json.JSONArray();
						for (Object[] objects : appoint) {
							if (objects[4].equals("Approved")) {
								JSONObject jsonap = new JSONObject();
								jsonap.put("id", objects[0].toString());
								jsonap.put("appoint_id",objects[1].toString());
								jsonap.put("volunteer_id",objects[2].toString());
								jsonap.put("client_id", objects[3].toString());
								jsonap.put("status", objects[4].toString());
								jsonap.put("schedule_date", objects[5].toString());
								jsonap.put("description", objects[6].toString());
								jsonap.put("vol_contact_no", objects[7].toString());
								jsonap.put("vol_email_id", objects[8].toString());
								jsonap.put("vol_name",  objects[9].toString()+" "+ objects[10].toString());
								approved.put(jsonap);
							} else if (objects[4].equals("Pending")) {
								JSONObject jsonpe = new JSONObject();
								jsonpe.put("id", objects[0].toString());
								jsonpe.put("appoint_id",objects[1].toString());
								jsonpe.put("volunteer_id",objects[2].toString());
								jsonpe.put("client_id", objects[3].toString());
								jsonpe.put("status", objects[4].toString());
								jsonpe.put("schedule_date", objects[5].toString());
								jsonpe.put("description", objects[6].toString());
								jsonpe.put("vol_contact_no", objects[7].toString());
								jsonpe.put("vol_email_id", objects[8].toString());
								jsonpe.put("vol_name",  objects[9].toString()+" "+ objects[10].toString());
								pending.put(jsonpe);
							} else if (objects[4].equals("Reject")) {
								JSONObject jsonrj = new JSONObject();
								jsonrj.put("id", objects[0].toString());
								jsonrj.put("appoint_id",objects[1].toString());
								jsonrj.put("volunteer_id",objects[2].toString());
								jsonrj.put("client_id", objects[3].toString());
								jsonrj.put("status", objects[4].toString());
								jsonrj.put("schedule_date", objects[5].toString());
								jsonrj.put("description", objects[6].toString());
								jsonrj.put("vol_contact_no", objects[7].toString());
								jsonrj.put("vol_email_id", objects[8].toString());
								jsonrj.put("vol_name",  objects[9].toString()+" "+ objects[10].toString());
								reject.put(jsonrj);
							} else if (objects[4].equals("Complete")) {
								JSONObject jsonco = new JSONObject();
								jsonco.put("id", objects[0].toString());
								jsonco.put("appoint_id",objects[1].toString());
								jsonco.put("volunteer_id",objects[2].toString());
								jsonco.put("client_id", objects[3].toString());
								jsonco.put("status", objects[4].toString());
								jsonco.put("schedule_date", objects[5].toString());
								jsonco.put("description", objects[6].toString());
								jsonco.put("vol_contact_no", objects[7].toString());
								jsonco.put("vol_email_id", objects[8].toString());
								jsonco.put("vol_name",  objects[9].toString()+" "+ objects[10].toString());
								complete.put(jsonco);
							}
							json.put("Approved", approved);
							json.put("Pending", pending);
							json.put("Reject", reject);
							json.put("Complete", complete);
							json.put("msg", "Successfuly Found Data");
							json.put("status", "1");
						}
					} else {
						json.put("msg", "Record Not Found !!!!!");
						json.put("status", "0");
					}
				} else {
					json.put("msg", "Please Check And Retry !!!!!");
					json.put("status", "0");
				}
			} else {
				json.put("msg", "Please Check And Retry !!!!!");
				json.put("status", "0");
			}

		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
		}
		return json;
	}


	@Override
	public JSONObject getAllLast5OR10(LinkedHashMap<String, String> object) {
		JSONObject json = new JSONObject();
		List<Object[]> appoint = null;
		try {
			if (object.containsKey("volunteer_id")) {
				if (!object.get("volunteer_id").equals("") && object.get("volunteer_id") != null) {
					appoint = dao.getAllAppoint(object);
					if (appoint != null && !appoint.isEmpty() && appoint.size()>0) {
						org.json.JSONArray approved = new org.json.JSONArray();
						org.json.JSONArray pending = new org.json.JSONArray();
						org.json.JSONArray reject = new org.json.JSONArray();
						org.json.JSONArray complete = new org.json.JSONArray();
						for (Object[] objects : appoint) {
							
							if (objects[4].equals("Approved")) {
								JSONObject jsonap = new JSONObject();
								jsonap.put("id", objects[0].toString());
								jsonap.put("appoint_id",objects[1].toString());
								jsonap.put("volunteer_id",objects[2].toString());
								jsonap.put("client_id", objects[3].toString());
								jsonap.put("status", objects[4].toString());
								jsonap.put("schedule_date", objects[5].toString());
								jsonap.put("description", objects[6].toString());
								jsonap.put("cl_contact_no", objects[7].toString());
								jsonap.put("cl_email_id", objects[8].toString());
								approved.put(jsonap);
							} else if (objects[4].equals("Pending")) {
								JSONObject jsonpe = new JSONObject();
								jsonpe.put("id", objects[0].toString());
								jsonpe.put("appoint_id",objects[1].toString());
								jsonpe.put("volunteer_id",objects[2].toString());
								jsonpe.put("client_id", objects[3].toString());
								jsonpe.put("status", objects[4].toString());
								jsonpe.put("schedule_date", objects[5].toString());
								jsonpe.put("description", objects[6].toString());
								jsonpe.put("cl_contact_no", objects[7].toString());
								jsonpe.put("cl_email_id", objects[8].toString());
								pending.put(jsonpe);
							} else if (objects[4].equals("Reject")) {
								JSONObject jsonrj = new JSONObject();
								jsonrj.put("id", objects[0].toString());
								jsonrj.put("appoint_id",objects[1].toString());
								jsonrj.put("volunteer_id",objects[2].toString());
								jsonrj.put("client_id", objects[3].toString());
								jsonrj.put("status", objects[4].toString());
								jsonrj.put("schedule_date", objects[5].toString());
								jsonrj.put("description", objects[6].toString());
								jsonrj.put("cl_contact_no", objects[7].toString());
								jsonrj.put("cl_email_id", objects[8].toString());
								reject.put(jsonrj);
							} else if (objects[4].equals("Complete")) {
								JSONObject jsonco = new JSONObject();
								jsonco.put("id", objects[0].toString());
								jsonco.put("appoint_id",objects[1].toString());
								jsonco.put("volunteer_id",objects[2].toString());
								jsonco.put("client_id", objects[3].toString());
								jsonco.put("status", objects[4].toString());
								jsonco.put("schedule_date", objects[5].toString());
								jsonco.put("description", objects[6].toString());
								jsonco.put("cl_contact_no", objects[7].toString());
								jsonco.put("cl_email_id", objects[8].toString());
								complete.put(jsonco);
							}
							json.put("Approved", approved);
							json.put("Pending", pending);
							json.put("Reject", reject);
							json.put("Complete", complete);
							json.put("msg", "Successfuly Found Data");
							json.put("status", "1");
						}
					} else {
						json.put("msg", "Record Not Found !!!!!");
						json.put("status", "0");
					}
				} else {
					json.put("msg", "Please Check And Retry !!!!!");
					json.put("status", "0");
				}
			} else if (object.containsKey("client_id")) {
				if (!object.get("client_id").equals("") && object.get("client_id") != null) {
					appoint = dao.getAllAppoint(object);
					if (appoint != null && !appoint.isEmpty() && appoint.size()>0) {
						org.json.JSONArray approved = new org.json.JSONArray();
						org.json.JSONArray pending = new org.json.JSONArray();
						org.json.JSONArray reject = new org.json.JSONArray();
						org.json.JSONArray complete = new org.json.JSONArray();
						for (Object[] objects : appoint) {
							if (objects[4].equals("Approved")) {
								JSONObject jsonap = new JSONObject();
								jsonap.put("id", objects[0].toString());
								jsonap.put("appoint_id",objects[1].toString());
								jsonap.put("volunteer_id",objects[2].toString());
								jsonap.put("client_id", objects[3].toString());
								jsonap.put("status", objects[4].toString());
								jsonap.put("schedule_date", objects[5].toString());
								jsonap.put("description", objects[6].toString());
								jsonap.put("vol_contact_no", objects[7].toString());
								jsonap.put("vol_email_id", objects[8].toString());
								jsonap.put("vol_name",  objects[9].toString()+" "+ objects[10].toString());
								approved.put(jsonap);
							} else if (objects[4].equals("Pending")) {
								JSONObject jsonpe = new JSONObject();
								jsonpe.put("id", objects[0].toString());
								jsonpe.put("appoint_id",objects[1].toString());
								jsonpe.put("volunteer_id",objects[2].toString());
								jsonpe.put("client_id", objects[3].toString());
								jsonpe.put("status", objects[4].toString());
								jsonpe.put("schedule_date", objects[5].toString());
								jsonpe.put("description", objects[6].toString());
								jsonpe.put("vol_contact_no", objects[7].toString());
								jsonpe.put("vol_email_id", objects[8].toString());
								jsonpe.put("vol_name",  objects[9].toString()+" "+ objects[10].toString());
								pending.put(jsonpe);
							} else if (objects[4].equals("Reject")) {
								JSONObject jsonrj = new JSONObject();
								jsonrj.put("id", objects[0].toString());
								jsonrj.put("appoint_id",objects[1].toString());
								jsonrj.put("volunteer_id",objects[2].toString());
								jsonrj.put("client_id", objects[3].toString());
								jsonrj.put("status", objects[4].toString());
								jsonrj.put("schedule_date", objects[5].toString());
								jsonrj.put("description", objects[6].toString());
								jsonrj.put("vol_contact_no", objects[7].toString());
								jsonrj.put("vol_email_id", objects[8].toString());
								jsonrj.put("vol_name",  objects[9].toString()+" "+ objects[10].toString());
								reject.put(jsonrj);
							} else if (objects[4].equals("Complete")) {
								JSONObject jsonco = new JSONObject();
								jsonco.put("id", objects[0].toString());
								jsonco.put("appoint_id",objects[1].toString());
								jsonco.put("volunteer_id",objects[2].toString());
								jsonco.put("client_id", objects[3].toString());
								jsonco.put("status", objects[4].toString());
								jsonco.put("schedule_date", objects[5].toString());
								jsonco.put("description", objects[6].toString());
								jsonco.put("vol_contact_no", objects[7].toString());
								jsonco.put("vol_email_id", objects[8].toString());
								jsonco.put("vol_name",  objects[9].toString()+" "+ objects[10].toString());
								complete.put(jsonco);
							}
							json.put("Approved", approved);
							json.put("Pending", pending);
							json.put("Reject", reject);
							json.put("Complete", complete);
							json.put("msg", "Successfuly Found Data");
							json.put("status", "1");
						}
					} else {
						json.put("msg", "Record Not Found !!!!!");
						json.put("status", "0");
					}
				} else {
					json.put("msg", "Please Check And Retry !!!!!");
					json.put("status", "0");
				}
			} else {
				json.put("msg", "Please Check And Retry !!!!!");
				json.put("status", "0");
			}

		} catch (Exception e) {
			json.put("msg", "Someting Went Wrong !!!!!");
			json.put("status", "0");
			log.info("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_ClientServiceImpl  save\t" + CommonMethod.getDate() + "=" + e);
		}
		return json;
	}

}
