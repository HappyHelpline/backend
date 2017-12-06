package com.source.dao;

import java.util.List;

import com.source.model.Register_Client;

public interface Register_ClientDao {

	public boolean save(Register_Client data);
	
	public boolean getData(String user,String pwd);
	
	public Register_Client getByUserID(String user);
	
	public boolean validUSR(String user);
	
	public List<Register_Client> getlist();
	
	
}
