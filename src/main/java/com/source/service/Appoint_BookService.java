package com.source.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;

import com.source.model.Appoint_Book;

public interface Appoint_BookService {

	public JSONObject save(LinkedHashMap<String, String> object);
	
	public JSONObject getByEveryOne(LinkedHashMap<String, String> object);
	
	public JSONObject updateByStatus(LinkedHashMap<String, String> object);
	
	public JSONObject getAllAppoint(LinkedHashMap<String, String> object);
	
	public JSONObject getAllLast5OR10(LinkedHashMap<String, String> object);
	
	
}
