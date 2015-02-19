package com.zizibujuan.niubizi.server.service;

import java.util.List;

import com.zizibujuan.niubizi.server.model.UserInfo;

public interface UserService {

	// 正序
	List<UserInfo> getUsersOrderByDisplayName();

}
