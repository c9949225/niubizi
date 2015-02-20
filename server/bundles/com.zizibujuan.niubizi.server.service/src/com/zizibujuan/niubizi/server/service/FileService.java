package com.zizibujuan.niubizi.server.service;

import java.util.List;

import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.model.FileOpenLog;
import com.zizibujuan.niubizi.server.model.FileTag;
import com.zizibujuan.niubizi.server.model.TagInfo;

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

	FileInfo findFileByName(String fileName);

	List<FileInfo> get();

	List<TagInfo> getTags(int fileId);

	/**
	 * 记录文件打开的时间
	 * 
	 * @param fileOpenLog 文件访问信息
	 */
	void logOpenFile(FileOpenLog fileOpenLog);
}
