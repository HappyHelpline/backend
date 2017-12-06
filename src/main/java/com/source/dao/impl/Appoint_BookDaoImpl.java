package com.source.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.source.config.GetHibernateUtils;
import com.source.dao.Appoint_BookDao;
import com.source.model.Appoint_Book;
import com.source.model.Register_Client;
import com.source.model.Register_Volunteer;
import com.source.service.impl.Appoint_BookServiceImpl;
import com.source.util.CommonMethod;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Repository
@Log4j
public class Appoint_BookDaoImpl implements Appoint_BookDao {

	@Autowired
	GetHibernateUtils getsessions;

	@Override
	public boolean save(Appoint_Book data) {
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
			log.info("Appoint_BookDaoImpl  save\t" + CommonMethod.getDate() + "=" + e);
			log.error("Appoint_BookDaoImpl  save\t" + CommonMethod.getDate() + "=" + e);
			flag = false;
			try {
				transaction.rollback();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} catch (RuntimeException ee) {
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
				log.info("Appoint_BookDaoImpl  save\t" + CommonMethod.getDate() + "=" + ee);
				log.error("Appoint_BookDaoImpl  save\t" + CommonMethod.getDate() + "=" + ee);
			}
			flag = false;
		}
		return flag;
	}

	@Override
	public List<Appoint_Book> getByEveryOne(JSONObject object) {
		List<Appoint_Book> data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Appoint_Book.class);
			if (!object.get("appoint_id").equals("")) {
				cr.add(Restrictions.eq("appoint_id", object.get("appoint_id")));
				data = cr.list();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} else if (!object.get("volunteer_id").equals("")) {
				cr.add(Restrictions.eq("volunteer_id", object.get("volunteer_id")));
				data = cr.list();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} else if (!object.get("client_id").equals("")) {
				cr.add(Restrictions.eq("client_id", object.get("client_id")));
				data = cr.list();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} else if (!object.get("status").equals("")) {
				cr.add(Restrictions.eq("status", object.get("status")));
				data = cr.list();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} else if (!object.get("sechdule_date").equals("")) {
				cr.add(Restrictions.eq("sechdule_date", object.get("sechdule_date")));
				data = cr.list();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} else if (!object.get("description").equals("")) {
				cr.add(Restrictions.eq("description", object.get("description")));
				data = cr.list();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} else if (!object.get("trndate").equals("")) {
				cr.add(Restrictions.eq("trndate", object.get("trndate")));
				data = cr.list();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			}
		} catch (Exception e) {
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			log.info("Appoint_BookDaoImpl  getByEveryOne\t" + CommonMethod.getDate() + "=" + e);
			log.error("Appoint_BookDaoImpl  getByEveryOne\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	@Override
	public boolean updateByStatus(String status, String appoint_id) {
		Transaction transaction = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			CriteriaBuilder cr = session.getCriteriaBuilder();
			CriteriaUpdate<Appoint_Book> update = cr.createCriteriaUpdate(Appoint_Book.class);
			Root root = update.from(Appoint_Book.class);
			update.set("status", status);
			update.where(cr.equal(root.get("appoint_id"), appoint_id));
			transaction = session.beginTransaction();
			int flag = session.createQuery(update).executeUpdate();
			transaction.commit();
			if (flag > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			transaction.rollback();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			log.info("Appoint_BookDaoImpl  updateByStatus\t" + CommonMethod.getDate() + "=" + e);
			log.error("Appoint_BookDaoImpl  updateByStatus\t" + CommonMethod.getDate() + "=" + e);
		}
		return false;
	}

	@Override
	public List<Object[]> getAllAppoint(LinkedHashMap<String, String> object) {
		List<Object[]> appoint = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			SQLQuery query = null;
			if (object.containsKey("volunteer_id")) {
				query = session.createSQLQuery(
						"SELECT A.id,A.appoint_id,A.volunteer_id,A.client_id,A.status,A.schedule_date,A.description,R.contact_no,R.email_add "
								+ "FROM `appointment_sechdule`A INNER JOIN `register_client` R ON A.client_id=R.username "
								+ "WHERE A.volunteer_id='" + object.get("volunteer_id") + "'");
			}
			if (object.containsKey("client_id")) {
				query = session.createSQLQuery(
						"SELECT A.id,A.appoint_id,A.volunteer_id,A.client_id,A.status,A.schedule_date,A.description,"
								+ "V.contact_no,V.email_add,V.first_name,V.last_name FROM `appointment_sechdule`A INNER JOIN `register_volunteer` V"
								+ " ON A.volunteer_id=V.username WHERE A.client_id='" + object.get("client_id") + "'");
			}
			appoint = query.list();
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			log.info("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
			log.error("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
		}
		return appoint;
	}

	@Override
	public JSONObject get5OR10(JSONObject object) {
		JSONObject json = null;
		if (object.has("client_id")) {
			json = new JSONObject();
			List<Object[]> appoint = get10(object.getString("client_id"));
			if (appoint != null && !appoint.isEmpty() && appoint.size() > 0) {
				org.json.JSONArray all = new org.json.JSONArray();
				for (Object[] objects : appoint) {
					JSONObject jsonap = new JSONObject();
					jsonap.put("appoint_id", objects[0].toString());
					jsonap.put("status", objects[1].toString());
					jsonap.put("schedule_date", objects[2].toString());
					jsonap.put("description", objects[3].toString());
					jsonap.put("vol_name", objects[4].toString() + " " + objects[5].toString());
					all.put(jsonap);
				}
				json.put("all_data", all);
			}
		} else if (object.has("volunteer_id")) {
			json = new JSONObject();
			List<Object[]> appointpending = getPending(object.getString("volunteer_id"));
			List<Object[]> appointapproved = getApproved(object.getString("volunteer_id"));
			List<Object[]> appointcompelted = getCompleted(object.getString("volunteer_id"));
			if (appointcompelted != null && !appointcompelted.isEmpty() && appointcompelted.size() > 0) {
				org.json.JSONArray complete = new org.json.JSONArray();
				for (Object[] objects : appointcompelted) {
					JSONObject jsonap = new JSONObject();
					jsonap.put("appoint_id", objects[0].toString());
					jsonap.put("schedule_date", objects[1].toString());
					jsonap.put("description", objects[2].toString());
					jsonap.put("cl_name", objects[3].toString());
					complete.put(jsonap);
				}
				json.put("completed",complete);
			}
			if (appointapproved != null && !appointapproved.isEmpty() && appointapproved.size() > 0) {
				org.json.JSONArray pending = new org.json.JSONArray();
				for (Object[] objects : appointapproved) {
					JSONObject jsonap = new JSONObject();
					jsonap.put("appoint_id", objects[0].toString());
					jsonap.put("schedule_date", objects[1].toString());
					jsonap.put("description", objects[2].toString());
					jsonap.put("cl_name", objects[3].toString());
					pending.put(jsonap);
				}
				json.put("approved",pending);
			}
			if (appointpending != null && !appointpending.isEmpty() && appointpending.size() > 0) {
				org.json.JSONArray approved = new org.json.JSONArray();
				for (Object[] objects : appointpending) {
					JSONObject jsonap = new JSONObject();
					jsonap.put("appoint_id", objects[0].toString());
					jsonap.put("schedule_date", objects[1].toString());
					jsonap.put("description", objects[2].toString());
					jsonap.put("cl_name", objects[3].toString());
					approved.put(jsonap);
				}
				json.put("pending",approved);
			}
		}
		return json;
	}

	public List<Object[]> getPending(String userid) {
		List<Object[]> data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			SQLQuery query = null;
			if (!userid.equals("")) {
				query = session.createSQLQuery(
						"SELECT A.appoint_id,A.schedule_date,A.description,C.username FROM `appointment_sechdule`A INNER JOIN `register_client` C ON A.client_id=C.username WHERE A.volunteer_id='"
								+ userid + "' AND A.status='Pending' ORDER BY A.id DESC  LIMIT 5;");

				data = query.list();
			}
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			log.info("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
			log.error("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	public List<Object[]> getCompleted(String userid) {
		List<Object[]> data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			SQLQuery query = null;
			if (!userid.equals("")) {
				query = session.createSQLQuery(
						"SELECT A.appoint_id,A.schedule_date,A.description,C.username FROM `appointment_sechdule`A INNER JOIN `register_client` C ON A.client_id=C.username WHERE A.volunteer_id='"
								+ userid + "' AND A.status='Complete' ORDER BY A.id DESC  LIMIT 5;");

				data = query.list();
				System.out.println(data);

			}
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			log.info("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
			log.error("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	public List<Object[]> getApproved(String userid) {
		List<Object[]> data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			SQLQuery query = null;
			if (!userid.equals("")) {
				query = session.createSQLQuery(
						"SELECT A.appoint_id,A.schedule_date,A.description,C.username FROM `appointment_sechdule`A INNER JOIN `register_client` C ON A.client_id=C.username WHERE A.volunteer_id='"
								+ userid + "' AND A.status='Approved' ORDER BY A.id DESC  LIMIT 5;");
				data = query.list();
				System.out.println(data);
			}
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			log.info("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
			log.error("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	public List<Object[]> get10(String userid) {
		List<Object[]> data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			SQLQuery query = null;
			if (!userid.equals("")) {
				query = session.createSQLQuery(
						"SELECT A.appoint_id,A.status,A.schedule_date,A.description,V.first_name,V.last_name FROM `appointment_sechdule`A INNER JOIN `register_volunteer` V ON A.volunteer_id=V.username WHERE A.client_id='"
								+ userid + "' ORDER BY A.id DESC  LIMIT 10;");
				data = query.list();
				System.out.println(data);
			}
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
		} catch (Exception e) {
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			log.info("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
			log.error("Appoint_BookDaoImpl  getAllAppoint\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;

	}

	@Override
	public Appoint_Book getByAppoint(String appoint) {
		Appoint_Book data = null;
		try {
			Session session = getsessions.getHibernateUtlis().OpenSessiontransactinal();
			Criteria cr = session.createCriteria(Appoint_Book.class);
			if (!appoint.equals("")) {
				cr.add(Restrictions.eq("appoint_id", appoint));
				data = (Appoint_Book) cr.uniqueResult();
				getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			} else {
				data = null;
			}
		} catch (Exception e) {
			getsessions.getHibernateUtlis().CloaseConnectiontMaster();
			log.info("Appoint_BookDaoImpl  getByEveryOne\t" + CommonMethod.getDate() + "=" + e);
			log.error("Appoint_BookDaoImpl  getByEveryOne\t" + CommonMethod.getDate() + "=" + e);
		}
		return data;
	}

	/*
	 * Session session =
	 * getsessions.getHibernateUtlis().OpenSessiontransactinal(); transaction =
	 * session.beginTransaction(); CriteriaBuilder builder =
	 * session.getCriteriaBuilder(); CriteriaQuery<Object[]> criteriaQuery =
	 * builder.createQuery(Object[].class); Root<Appoint_Book> aop =
	 * criteriaQuery.from(Appoint_Book.class); if
	 * (object.containsKey("volunteer_id")) { Root<Register_Client> cl =
	 * criteriaQuery.from(Register_Client.class); criteriaQuery.multiselect(aop,
	 * cl); criteriaQuery.where(builder.equal(aop.get("client_id"),
	 * cl.get("username")), builder.equal(aop.get("volunteer_id"),
	 * object.get("volunteer_id"))); } else if (object.containsKey("client_id"))
	 * { Root<Register_Volunteer> vol =
	 * criteriaQuery.from(Register_Volunteer.class);
	 * criteriaQuery.multiselect(aop, vol);
	 * criteriaQuery.where(builder.equal(aop.get("volunteer_id"),
	 * vol.get("username")), builder.equal(aop.get("client_id"),
	 * object.get("client_id"))); } Query<Object[]> query =
	 * session.createQuery(criteriaQuery); appoint = query.getResultList(); if
	 * (object.containsKey("client_id")) { for (Object[] objects : appoint) {
	 * Appoint_Book aoop = (Appoint_Book) objects[0]; Register_Volunteer vol =
	 * (Register_Volunteer) objects[1]; System.out.println(aoop.getAppoint_id()
	 * + "\t" + aoop.getStatus()); } } else if
	 * (object.containsKey("volunteer_id")) { for (Object[] objects : appoint) {
	 * Appoint_Book aoop = (Appoint_Book) objects[0]; Register_Client vol =
	 * (Register_Client) objects[1]; System.out.println(aoop.getAppoint_id() +
	 * "\t" + aoop.getStatus()); } } transaction.commit();
	 */

}
