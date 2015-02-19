package com.zizibujuan.niubizi.server.service.impl;

import java.util.List;

import com.zizibujuan.niubizi.server.dao.CategoryDao;
import com.zizibujuan.niubizi.server.model.CategoryInfo;
import com.zizibujuan.niubizi.server.service.CategoryService;

public class CategoryServiceImpl implements CategoryService{

	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao){
		//logger.info("注入categoryDao");
		this.categoryDao = categoryDao;
	}
	
	public void unsetCategoryDao(CategoryDao categoryDao){
		if(this.categoryDao == categoryDao){
			//logger.info("注销categoryDao");
			this.categoryDao = null;
		}
	}
	
	@Override
	public List<CategoryInfo> get() {
		return categoryDao.get();
	}

	@Override
	public CategoryInfo findByName(String strCategoryName) {
		return categoryDao.findByName(strCategoryName);
	}

	@Override
	public int add(CategoryInfo categoryInfo) {
		return categoryDao.add(categoryInfo);
	}

	@Override
	public void remove(int id) {
		categoryDao.remove(id);
	}

}
