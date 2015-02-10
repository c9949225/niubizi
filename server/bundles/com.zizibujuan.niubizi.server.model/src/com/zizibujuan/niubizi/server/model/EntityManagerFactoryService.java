package com.zizibujuan.niubizi.server.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerFactoryService {
	private static EntityManagerFactory emf;

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public static void setEmf(EntityManagerFactory entityManagerFactory) {
		emf = entityManagerFactory;
	}
}
