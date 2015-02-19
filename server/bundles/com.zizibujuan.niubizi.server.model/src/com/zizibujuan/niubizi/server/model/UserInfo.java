package com.zizibujuan.niubizi.server.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户信息
 * 
 * @author jinzw
 * @since 0.0.1
 */
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DBID")
	private int id;
	
	@Column(name="FULL_NAME", length=32)
	private String fullName; // 姓名
	
	@Column(name="DISPLAY_NAME", length=32)
	private String displayName; // 显示名，默认与fullName相同
	
	@Column(name="EMAIL", length=32)
	private String email; // 用户会有很多邮箱，但是这里只填写常用邮箱地址

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
