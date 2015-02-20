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
 * 文件信息
 * 
 * @author jinzw
 * @since 0.0.1
 */
@Entity
@Table(name="NBZ_FILE", uniqueConstraints=@UniqueConstraint(name="UN_FILE_NAME", columnNames={"FILE_NAME"}))
public class FileInfo {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="DBID")
	private int id;
	
	@Column(name="FILE_NAME", length=152)
	private String fileName;
	
	@Column(name="FILE_TYPE", length=10)
	private String fileType; // 文件类型，存储文件的后缀名。
	
	@Column(name="FILE_PATH", length=3000)
	private String filePath;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime; // 创建时间
	
	@Column(name="UNTRACK_FILE_NAME", length=36)
	private String untrackFileName; // 未跟踪状态的文件都会赋予一个唯一的临时名称
	
	@Column(name="FILE_MANAGE_STATUS", length=1)
	private String fileManageStatus;
	
	@Column(name="FILE_CATEGORY")
	private int category; // 文档分类，一个文档只属于一个分类
	
	@Column(name="FILE_SENDER")
	private int sender; // 文件发送人
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUntrackFileName() {
		return untrackFileName;
	}

	public void setUntrackFileName(String untrackFileName) {
		this.untrackFileName = untrackFileName;
	}

	public String getFileManageStatus() {
		return fileManageStatus;
	}

	public void setFileManageStatus(String fileManageStatus) {
		this.fileManageStatus = fileManageStatus;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getSender() {
		return sender;
	}

	public void setSender(int sender) {
		this.sender = sender;
	}
}
