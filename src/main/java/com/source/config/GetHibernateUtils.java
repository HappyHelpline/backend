package com.source.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetHibernateUtils {

	private HibernateUtlis hibernateUtlis;

	@Autowired
	public GetHibernateUtils(HibernateUtlis hibernateUtlis) {
		System.out.println("hibernateUtlis intilizing" + hibernateUtlis);
		this.hibernateUtlis = hibernateUtlis;

	}

	public GetHibernateUtils() {

	}

	public HibernateUtlis getHibernateUtlis() {
		return hibernateUtlis;
	}

}
