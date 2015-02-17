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
	
	@Column(name="FILE_NAME", length=152)
	private String fileName;
	
	@Column(name="FILE_PATH", length=3000)
	private String filePath;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	private Date createTime; // 创建时间
	
	@Column(name="UNTRACK_FILE_NAME", length=36)
	private String untrackFileName; // 未跟踪状态的文件都会赋予一个唯一的临时名称
	
	@Column(name="FILE_MANAGE_STATUS", length=1)
	private String fileManageStatus;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
}
