package com.source.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;

import com.source.model.Register_Volunteer;

public interface Register_VolunteerService {
	
	public JSONObject save(LinkedHashMap<String, String> object);
	
	public JSONObject getlogin(LinkedHashMap<String, String> object);
	
	public JSONObject schedule_update(LinkedHashMap<String, String> object);
	
}
