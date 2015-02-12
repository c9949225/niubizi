package com.zizibujuan.niubizi.server.service;

import java.util.List;

import com.zizibujuan.niubizi.server.model.TagInfo;

public interface TagService {
	int add(TagInfo tagInfo);
	List<TagInfo> get();
}
