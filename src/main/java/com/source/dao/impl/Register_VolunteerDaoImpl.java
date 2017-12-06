package com.source.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.source.config.GetHibernateUtils;
import com.source.dao.Register_VolunteerDao;
import com.source.model.Register_Client;
import com.source.model.Register_Volunteer;
import com.source.util.CommonMethod;

import lombok.extern.slf4j.Slf4j;

@Repository("registervolunteerdao")
@Slf4j
public class Register_VolunteerDaoImpl implements Register_VolunteerDao {

	@Autowired
	GetHibernateUtils getsessions;

	@Override
	public boolean save(Register_Volunteer data) {
		Transaction transaction = null;
		boolean flag = false;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			transaction = session.beginTransaction();
			session.save(data);
			transaction.commit();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			flag = true;
		} catch (Exception e) {
			log.info("Register_VolunteerDaoImpl  save\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_VolunteerDaoImpl  save\t" + CommonMethod.getDate() + "=" + e);
			flag = false;
			try {
				transaction.rollback();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} catch (RuntimeException ee) {
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
				log.info("Register_VolunteerDaoImpl  save\t" + CommonMethod.getDate() + "=" + ee);
				log.error("Register_VolunteerDaoImpl  save\t" + CommonMethod.getDate() + "=" + ee);
			}
			flag = false;
		}

		return flag;
	}

	@Override
	public boolean getData(String user, String pwd) {
		boolean flag = false;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Register_Volunteer.class);
			cr.add(Restrictions.eq("email_add", user));
			cr.add(Restrictions.eq("password", pwd));
			Register_Volunteer ob = (Register_Volunteer) cr.uniqueResult();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			if (ob != null && !ob.equals("")) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			log.info("Register_VolunteerDaoImpl  getData\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_VolunteerDaoImpl  getData\t" + CommonMethod.getDate() + "=" + e);
		}
		return flag;
	}

	@Override
	public Register_Volunteer getByUserID(String user) {
		Register_Volunteer data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Register_Volunteer.class);
			cr.add(Restrictions.eq("email_add", user));
			data = (Register_Volunteer) cr.uniqueResult();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			log.info("Register_VolunteerDaoImpl  getByUserID\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_VolunteerDaoImpl  getByUserID\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	@Override
	public List<Register_Volunteer> getlist() {
		List<Register_Volunteer> data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Register_Volunteer.class);
			data = cr.list();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			log.info("Register_VolunteerDaoImpl  getlist\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_VolunteerDaoImpl  getlist\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	@Override
	public Register_Volunteer getByID(String user) {
		Register_Volunteer data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Register_Volunteer.class);
			cr.add(Restrictions.eq("username", user));
			data = (Register_Volunteer) cr.uniqueResult();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			log.info("Register_VolunteerDaoImpl  getByUserID\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_VolunteerDaoImpl  getByUserID\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	@Override
	public boolean update(LinkedHashMap<String, String> data) {

		return false;
	}

}
