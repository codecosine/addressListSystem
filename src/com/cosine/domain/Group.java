package com.cosine.domain;

import java.util.HashSet;
import com.alibaba.fastjson.JSON;

public class Group<T> extends HashSet<T>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4730645866566418907L;

	public String toJsonString(){
		return JSON.toJSONString(this);
	}
	
	
}
