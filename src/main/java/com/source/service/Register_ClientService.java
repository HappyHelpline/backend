package com.source.service;

import java.util.LinkedHashMap;

import org.json.JSONObject;

import com.source.model.Register_Client;

public interface Register_ClientService {
 
	public JSONObject save(LinkedHashMap<String, String> object);
	
	public JSONObject getlogin(LinkedHashMap<String, String> object);
	
	public JSONObject getValidUSR(LinkedHashMap<String, String> object);

}
