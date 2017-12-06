package com.source.dao;

import java.util.LinkedHashMap;
import java.util.List;

import com.source.model.Register_Client;
import com.source.model.Register_Volunteer;

public interface Register_VolunteerDao {

	public boolean save(Register_Volunteer data);
	
	public boolean update(LinkedHashMap<String, String> data);

	public boolean getData(String user, String pwd);

	public Register_Volunteer getByUserID(String user);
	
	public Register_Volunteer getByID(String user);
	
	public List<Register_Volunteer> getlist();
}
