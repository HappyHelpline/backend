package com.source.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class HibernateUtlis {

	@Autowired
	protected SessionFactory factory;

	ThreadLocal<Session> threadlocal = new ThreadLocal<Session>();
	ThreadLocal<Session> threadlocalmaster = new ThreadLocal<Session>();

	public HibernateUtlis() {
		System.out.println("HibernateUtlis intilize time");
	}

	public Session OpenSessiontransactinal() {
		Session session = null;
		if (threadlocal.get() == null) {
			session = factory.openSession();
			threadlocal.set(session);
		} else {
			session = threadlocal.get();
			if (!session.isOpen()) {
				threadlocal.remove();
				session = factory.openSession();
				threadlocal.set(session);
			}
		}

		return session;

	}

	public void CloaseConnectiontransactinal() {
		if (threadlocal.get() != null) {
			threadlocal.get().close();
			threadlocal.remove();
		}
	}

	public void CloaseConnectiontMaster() {
		if (threadlocalmaster.get() != null) {
			threadlocalmaster.get().close();
			threadlocalmaster.remove();
		}
	}

}
