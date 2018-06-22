package com.bridgelabz.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionfactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {

		try {

			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();

		} catch (Exception e) {

			// Make sure you log the exception, as it might be swallowed
			System.err.println("SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);

		}
	}

	public static SessionFactory getSessionFactory() {

		return sessionfactory;

	}

	public static void shutdown() {

		// Close caches and connection pools
		getSessionFactory().close();

	}
}