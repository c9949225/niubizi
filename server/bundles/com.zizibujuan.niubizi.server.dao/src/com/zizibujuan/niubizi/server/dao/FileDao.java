package com.zizibujuan.niubizi.server.dao;

import java.util.List;

import com.zizibujuan.niubizi.server.model.FileInfo;
import com.zizibujuan.niubizi.server.model.FileOpenLog;
import com.zizibujuan.niubizi.server.model.FileTag;
import com.zizibujuan.niubizi.server.model.TagInfo;

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
