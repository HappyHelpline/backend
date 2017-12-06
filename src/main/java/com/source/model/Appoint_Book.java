package com.source.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="appointment_sechdule")
public @Data class Appoint_Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private int id;
	private String appoint_id;
	private String volunteer_id;
	private String client_id;
	private String status;
	private String schedule_date;
	private String description;
	private String trndate;
	
		
	

}
