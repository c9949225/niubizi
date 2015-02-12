package com.zizibujuan.niubizi.server.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import com.zizibujuan.niubizi.server.dao.TagDao;
import com.zizibujuan.niubizi.server.model.EntityManagerFactoryService;
import com.zizibujuan.niubizi.server.model.TagInfo;

public class TagDaoImpl implements TagDao{

	@Override
	public int add(TagInfo tagInfo) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(tagInfo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return tagInfo.getId();
	}

	@Override
	public List<TagInfo> get() {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		return entityManager.createQuery("select t from TagInfo t", TagInfo.class).getResultList();
	}

}
