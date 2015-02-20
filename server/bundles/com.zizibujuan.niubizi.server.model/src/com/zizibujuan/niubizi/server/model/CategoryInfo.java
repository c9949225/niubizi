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
 * 分类信息,只支持一级分类。
 * 
 * @author jinzw
 * @since 0.0.1
 */
@Entity
@Table(name="NBZ_CATEGORY", uniqueConstraints=@UniqueConstraint(columnNames = { "CATEGORY_NAME" }))
public class CategoryInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DBID")
	private int id;
	
	@Column(name="CATEGORY_NAME", length=36)
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime; // 创建时间
	
	@Column(name="SEQ")
	private int seq = 0;
	
	@Column(name="FILE_NAME_TEMPLATE", length = 152)
	private String fileNameTemplate; // 文件名标准命名方式

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

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getFileNameTemplate() {
		return fileNameTemplate;
	}

	public void setFileNameTemplate(String fileNameTemplate) {
		this.fileNameTemplate = fileNameTemplate;
	}
	
}
