package com.volc.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class OpenActionTemplate {
	
	private JSONObject jsonRequest;
	private JSONObject jsonResponse;
	private static final String URL ="http://www.baidu.com";
	private static final String REQUEST_PATH	="/src/test/resource/request/";
	
	public String doPostWithString(){
		return "";
	}

	public void setContext(String path,Object value){
		
	}
	public void assertResult(){
		
	}
	
	public Map execute(){
		String result = HttpClientManager.doPostWithString(URL);
		Map<String, Object> map = new HashMap<String,Object>();
		return map;
	}
}
