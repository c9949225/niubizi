package com.zizibujuan.niubizi.server.service;

import java.util.List;

import com.zizibujuan.niubizi.server.model.CategoryInfo;

public interface CategoryService {

	List<CategoryInfo> get();

	CategoryInfo findByName(String strCategoryName);

	int add(CategoryInfo categoryInfo);

	void remove(int id);

}
