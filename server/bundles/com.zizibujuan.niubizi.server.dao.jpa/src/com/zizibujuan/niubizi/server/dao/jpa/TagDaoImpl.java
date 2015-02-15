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
		return entityManager.createQuery("select t from TagInfo t order by t.id desc", TagInfo.class).getResultList();
	}

	@Override
	public void remove(int id) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		TagInfo tag = entityManager.find(TagInfo.class, id);
		entityManager.remove(tag);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Override
	public TagInfo findByName(String tagName) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		List<TagInfo> result = entityManager.createQuery("select t from TagInfo t where t.name=:name", TagInfo.class).setParameter("name", tagName).getResultList();
		return result.isEmpty() ? null : result.get(0);
	}

}
