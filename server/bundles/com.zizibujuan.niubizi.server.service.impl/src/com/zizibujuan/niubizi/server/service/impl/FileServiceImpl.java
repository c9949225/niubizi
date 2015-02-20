package com.zizibujuan.niubizi.server.service.impl;

import java.util.List;

import com.zizibujuan.niubizi.server.dao.FileDao;
import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.model.FileOpenLog;
import com.zizibujuan.niubizi.server.model.FileTag;
import com.zizibujuan.niubizi.server.model.TagInfo;
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

	@Override
	public void removeTag(FileTag fileTag) {
		fileDao.removeTag(fileTag);
	}

	@Override
	public void addTag(FileTag fileTag) {
		fileDao.addTag(fileTag);
	}

	@Override
	public void update(FileInfo fileInfo) {
		fileDao.update(fileInfo);
	}

	@Override
	public FileInfo findFileByName(String fileName) {
		return fileDao.findFileByName(fileName);
	}

	@Override
	public List<FileInfo> get() {
		return fileDao.get();
	}

	@Override
	public List<TagInfo> getTags(int fileId) {
		return fileDao.getTags(fileId);
	}

	@Override
	public void logOpenFile(FileOpenLog fileOpenLog) {
		fileDao.logOpenFile(fileOpenLog);
		
	}

}
