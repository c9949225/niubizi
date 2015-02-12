package com.zizibujuan.niubizi.client.ui;

import com.zizibujuan.niubizi.server.service.FileService;
import com.zizibujuan.niubizi.server.service.TagService;

public class ServiceHolder {
	private static ServiceHolder singleton;

	public static ServiceHolder getDefault() {
		return singleton;
	}

	public void activate() {
		singleton = this;
	}

	public void deactivate() {
		singleton = null;
	}
	
	private FileService fileService;
	public void unsetFileService(FileService fileService) {
		//logger.info("注销fileService");
		if (this.fileService == fileService) {
			this.fileService = null;
		}
	}
	public void setFileService(FileService fileService) {
		//logger.info("注入fileService");
		this.fileService = fileService;
	}
	public FileService getFileService() {
		return fileService;
	}
	
	private TagService tagService;
	public void unsetTagService(TagService tagService) {
		//logger.info("注销tagService");
		if (this.tagService == tagService) {
			this.tagService = null;
		}
	}
	public void setTagService(TagService tagService) {
		//logger.info("注入tagService");
		this.tagService = tagService;
	}
	public TagService getTagService() {
		return tagService;
	}
}
