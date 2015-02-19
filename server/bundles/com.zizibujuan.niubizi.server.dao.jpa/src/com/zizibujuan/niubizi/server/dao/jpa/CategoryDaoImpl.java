package com.zizibujuan.niubizi.server.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;

import com.zizibujuan.niubizi.server.dao.CategoryDao;
import com.zizibujuan.niubizi.server.model.CategoryInfo;
import com.zizibujuan.niubizi.server.model.EntityManagerFactoryService;

public class CategoryDaoImpl implements CategoryDao{

	@Override
	public List<CategoryInfo> get() {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		return entityManager.createQuery("select t from CategoryInfo t order by t.seq", CategoryInfo.class).getResultList();
	}

	@Override
	public CategoryInfo findByName(String categoryName) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		List<CategoryInfo> result = entityManager
				.createQuery("select t from CategoryInfo t where t.name=:name", CategoryInfo.class)
				.setParameter("name", categoryName)
				.getResultList();
		return result.isEmpty() ? null : result.get(0);
	}

	@Override
	public int add(CategoryInfo categoryInfo) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(categoryInfo);
		entityManager.getTransaction().commit();
		entityManager.close();
		return categoryInfo.getId();
	}

	@Override
	public void remove(int id) {
		EntityManager entityManager = EntityManagerFactoryService.getEntityManager();
		entityManager.getTransaction().begin();
		CategoryInfo category = entityManager.find(CategoryInfo.class, id);
		entityManager.remove(category);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
