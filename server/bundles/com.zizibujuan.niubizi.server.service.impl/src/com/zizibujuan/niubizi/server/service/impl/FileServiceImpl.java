package com.zizibujuan.niubizi.server.service.impl;

import com.zizibujuan.niubizi.server.dao.FileDao;
import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.service.FileService;

public class FileServiceImpl implements FileService{

	private FileDao fileDao;
	
	public void setFileDao(FileDao fileDao){
		//logger.info("注入fileDao");
		this.fileDao = fileDao;
	}
	
	public void unsetFileDao(FileDao fileDao){
		if(this.fileDao == fileDao){
			//logger.info("注销fileDao");
			this.fileDao = null;
		}
	}
	
	@Override
	public int add(FileInfo fileInfo) {
		return fileDao.add(fileInfo);
	}

}
