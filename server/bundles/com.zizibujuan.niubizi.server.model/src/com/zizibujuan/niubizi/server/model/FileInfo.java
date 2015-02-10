package com.zizibujuan.niubizi.server.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 文件信息
 * 
 * @author jinzw
 * @since 0.0.1
 */
@Entity
public class FileInfo {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String pathName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
}
