package com.volc.lab;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.alibaba.fastjson.JSONObject;

public class MapTest {
	public static void main(String[] args){
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("userId", "333001");
		map.put("userName", "zhangsan");
		map.put("", "333003");
		
		System.out.println("map:"+map);
		
		JSONObject json = new JSONObject();
		json.put("userId", "333002");
		json.put("userName", "lisi");
		json.put("", "xxx");
		//json.putAll(map);
		System.out.println("json:"+json);
		System.out.println("json2map:"+JSONObject.parseObject(json.toString(),Map.class));
		
		//获取当前项目的路径
		String projPath = System.getProperty("user.dir");
		System.out.println(projPath);
		Properties prop = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(
					new FileInputStream(projPath+"/src/main/resources/config.properties"));
			prop.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Enumeration en = prop.propertyNames(); //得到配置文件文件中的所有keys 
		        while(en.hasMoreElements()) {
		            String strKey = (String) en.nextElement();
		            String strValue = prop.getProperty(strKey);
		             System.out.println(strKey + "=" + strValue);
		         }
		
		
		
	}
}
