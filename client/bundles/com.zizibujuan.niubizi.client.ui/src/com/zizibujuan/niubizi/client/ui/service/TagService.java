package com.zizibujuan.niubizi.client.ui.service;

import java.util.ArrayList;
import java.util.List;

import com.zizibujuan.niubizi.client.ui.model.Tag;

/**
 * 标签服务
 * 
 * @author jinzw
 * @since 0.0.1
 */
public class TagService {

	public List<Tag> get(){
		List<Tag> result = new ArrayList<Tag>();
		Tag tag1 = new Tag(1, "a");
		Tag tag2 = new Tag(2, "b");
		Tag tag3 = new Tag(3, "c");
		
		result.add(tag1);
		result.add(tag2);
		result.add(tag3);
		
		return result;
	}
}
