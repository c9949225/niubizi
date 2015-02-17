package com.zizibujuan.niubizi.server.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * 文件标签，用来标识文件
 * 
 * @author jinzw
 * @since 0.0.1
 */
@Entity
@Table(name="NBZ_TAG", uniqueConstraints=@UniqueConstraint(columnNames = { "TAG_NAME" }))
public class TagInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DBID")
	private int id;
	
	@Column(name="TAG_NAME", length=20)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime; // 创建时间
	
	@Column(name="FILE_COUNT")
	private int fileCount = 0; // 使用标签的文件个数

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getFileCount() {
		return fileCount;
	}

	public void setFileCount(int fileCount) {
		this.fileCount = fileCount;
	}

	public void decreaseFileCount() {
		if(fileCount > 0){
			fileCount--;
		}
	}

	public void increaseFileCount() {
		fileCount++;
	}
	
}
