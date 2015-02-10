package com.zizibujuan.niubizi.server.dao.jpa;

import javax.persistence.EntityManager;

import com.zizibujuan.niubizi.server.dao.FileDao;
import com.zizibujuan.niubizi.server.model.EntityManagerFactoryService;
import com.zizibujuan.niubizi.server.model.FileInfo;

public class FileDaoImpl implements FileDao{

	@Override
	public int add(FileInfo fileInfo) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(fileInfo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return fileInfo.getId();
	}


}
