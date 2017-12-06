package com.source.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.source.config.GetHibernateUtils;
import com.source.config.HibernateConfig;
import com.source.dao.Register_ClientDao;
import com.source.model.Register_Client;
import com.source.model.Register_Volunteer;
import com.source.util.CommonMethod;

import lombok.extern.slf4j.Slf4j;

@Repository("registerclientdao")
@Slf4j
public class Register_ClientDaoImpl implements Register_ClientDao {

	@Autowired
	GetHibernateUtils getsessions;

	@Override
	public boolean save(Register_Client data) {
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
			log.info("Register_ClientDaoImpl  save\t"+CommonMethod.getDate() + "=" + e);
			log.error("Register_ClientDaoImpl  save\t"+CommonMethod.getDate() + "=" + e);
			flag = false;
			try {
				transaction.rollback();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} catch (RuntimeException ee) {
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
				log.info("Register_ClientDaoImpl  save\t"+CommonMethod.getDate() + "=" + ee);
				log.error("Register_ClientDaoImpl  save\t"+CommonMethod.getDate() + "=" + ee);
			}
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean getData(String user, String pwd) {
		boolean flag=false;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Register_Client.class);
			cr.add(Restrictions.eq("username",user));
			cr.add(Restrictions.eq("password",pwd));
			Register_Client ob = (Register_Client) cr.uniqueResult();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			if (ob!=null && !ob.equals("")) {
				flag=true;
				
			}else{
				flag=false;
			}
		} catch (Exception e) {
			log.info("Register_ClientDaoImpl  getData\t"+CommonMethod.getDate() + "=" + e);
			log.error("Register_ClientDaoImpl  getData\t"+CommonMethod.getDate() + "=" + e);
		}
		return flag;
	}

	@Override
	public Register_Client getByUserID(String user) {
		Register_Client data=null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Register_Client.class);
			cr.add(Restrictions.eq("username",user));
			data = (Register_Client) cr.uniqueResult();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			log.info("Register_ClientDaoImpl  getByUserID\t"+CommonMethod.getDate() + "=" + e);
			log.error("Register_ClientDaoImpl  getByUserID\t"+CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	@Override
	public List<Register_Client> getlist() {
		List<Register_Client> data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Register_Client.class);
			data = cr.list();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			log.info("Register_VolunteerDaoImpl  getlist\t" + CommonMethod.getDate() + "=" + e);
			log.error("Register_VolunteerDaoImpl  getlist\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	@Override
	public boolean validUSR(String user) {
		boolean flag=false;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Register_Client.class);
			cr.add(Restrictions.eq("username",user));
			Register_Client ob = (Register_Client) cr.uniqueResult();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			if (ob!=null && !ob.equals("")) {
				flag=true;
				
			}else{
				flag=false;
			}
		} catch (Exception e) {
			log.info("Register_ClientDaoImpl  validUSR\t"+CommonMethod.getDate() + "=" + e);
			log.error("Register_ClientDaoImpl  validUSR\t"+CommonMethod.getDate() + "=" + e);
		}
		return flag;
	}

}
