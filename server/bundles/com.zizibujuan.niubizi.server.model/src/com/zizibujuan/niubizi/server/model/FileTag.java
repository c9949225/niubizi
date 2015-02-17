package com.zizibujuan.niubizi.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="NBZ_FILE_TAG", uniqueConstraints=@UniqueConstraint(columnNames = { "FILE_ID", "TAG_ID" }))
public class FileTag {

	@Id
	@Column(name="DBID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="FILE_ID")
	private int fileId;
	

	@Column(name="TAG_ID")
	private int tagId;

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
}
