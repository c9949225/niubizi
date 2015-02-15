package com.zizibujuan.niubizi.server.dao;

import java.util.List;

import com.zizibujuan.niubizi.server.model.TagInfo;

public interface TagDao {

	int add(TagInfo tagInfo);
	List<TagInfo> get();
	void remove(int id);
	TagInfo findByName(String tagName);
}
