package com.zizibujuan.niubizi.client.ui;

/**
 * 牛鼻子文件管理软件系统级别的常量
 * 
 * @author jinzw
 * @since 0.0.1
 */
public class NBZ {

	/**
	 * 未正式跟踪的文件，有重名的文件会先复制到这个文件夹中
	 */
	public static String DIR_UNTRACKED = "Untracked";
	
	/**
	 * 正式托管的文件夹
	 */
	public static String DIR_MANAGED = "Managed";
	
	public static String FILE_MANAGED = "1";
	
	public static String FILE_UNTRACKED = "2";
	
	public static int UNDEFINED_KEY = -1;
	public static String UNDEFINED_VALUE = "未指派";
}
