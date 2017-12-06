package com.source.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONObject;

import com.source.model.Appoint_Book;

public interface Appoint_BookDao {

	public boolean save(Appoint_Book data);
	
	public List<Appoint_Book> getByEveryOne(JSONObject object);
	
	public Appoint_Book getByAppoint(String appoint);
	
	public boolean updateByStatus(String status,String appoint_id);
	
	public List<Object[]> getAllAppoint(LinkedHashMap<String, String> object);
	
	public JSONObject get5OR10(JSONObject object);
	
}
