package com.zizibujuan.niubizi.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zizibujuan.niubizi.server.dao.UserDao;
import com.zizibujuan.niubizi.server.model.UserInfo;
import com.zizibujuan.niubizi.server.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		//logger.info("注入userDao");
		this.userDao = userDao;
	}
	
	public void unsetUserDao(UserDao userDao){
		if(this.userDao == userDao){
			//logger.info("注销userDao");
			this.userDao = null;
		}
	}

	@Override
	public List<UserInfo> getUsersOrderByDisplayName() {
		return new ArrayList<UserInfo>();
	}
	
}
