package com.zizibujuan.niubizi.server.dao;

import com.zizibujuan.niubizi.server.model.FileInfo;

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
}
