package com.zizibujuan.niubizi.server.service;

import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.model.FileTag;

public interface FileService {
	
	/**
	 * 新增文件
	 * 
	 * @param filePath
	 * @param fileName
	 * @param fileType
	 * @return 返回文件标识
	 */
	int add(FileInfo fileInfo);

	void removeTag(FileTag ft);

	void addTag(FileTag ft);

	void update(FileInfo fileInfo);
}
