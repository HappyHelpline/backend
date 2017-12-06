package com.source.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="register_volunteer")
public @Data class Register_Volunteer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private int id;
	private String username;
	private String password;
	private String first_name;
	private String last_name;
	private String gender_type;
	private String contact_no;
	private String alternate_no;
	private String email_add;
	private String address;
	private String state;
	private String country;
	private String professional;
	private String about_you;
	private String language;
	private String whyjoinus;
	private String schedule_time;
	private String trndate;

	
}
