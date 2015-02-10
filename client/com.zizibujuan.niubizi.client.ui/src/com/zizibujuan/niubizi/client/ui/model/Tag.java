package com.zizibujuan.niubizi.client.ui.model;

/**
 * 标签
 * 
 * @author jinzw
 * @since 0.0.1
 */
public class Tag {
	
	private int id;
	private String name;
	
	public Tag(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 获取标签标识
	 * @return 标签标识，从1开始的数字
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 设置标签标识
	 * @param id 标签标识，从1开始的数字
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 获取标签名称
	 * @return 设置标签名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置标签名称
	 * @param name 标签名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
