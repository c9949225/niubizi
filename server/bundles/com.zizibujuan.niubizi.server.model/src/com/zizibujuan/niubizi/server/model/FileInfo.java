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

/**
 * 文件信息
 * 
 * @author jinzw
 * @since 0.0.1
 */
@Entity
@Table(name="NBZ_FILE")
public class FileInfo {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="DBID")
	private int id;
	
	@Column(name="FILE_PATH", length=3000)
	private String pathName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime; // 创建时间

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
