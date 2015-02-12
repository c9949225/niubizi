package com.zizibujuan.niubizi.server.service.impl;

import java.util.List;

import com.zizibujuan.niubizi.server.dao.TagDao;
import com.zizibujuan.niubizi.server.model.TagInfo;
import com.zizibujuan.niubizi.server.service.TagService;

public class TagServiceImpl implements TagService{

	private TagDao tagDao;
	
	public void setTagDao(TagDao tagDao){
		//logger.info("注入tagDao");
		this.tagDao = tagDao;
	}
	
	public void unsetTagDao(TagDao tagDao){
		if(this.tagDao == tagDao){
			//logger.info("注销tagDao");
			this.tagDao = null;
		}
	}
	
	@Override
	public int add(TagInfo tagInfo) {
		return tagDao.add(tagInfo);
	}

	@Override
	public List<TagInfo> get() {
		return tagDao.get();
	}

}
