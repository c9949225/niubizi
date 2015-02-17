package com.zizibujuan.niubizi.server.dao;

import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.model.FileTag;

public interface FileDao {

	/**
	 * 新增文件
	 * 
	 * @param filePath
	 * @param fileName
	 * @param fileType
	 * @return 返回文件标识
	 */
	int add(FileInfo fileInfo);

	void removeTag(FileTag fileTag);

	void addTag(FileTag fileTag);
}
