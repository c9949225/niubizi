package com.zizibujuan.niubizi.server.service;

import com.zizibujuan.niubizi.server.model.FileInfo;

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
}
