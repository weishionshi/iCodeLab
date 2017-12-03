package com.volc.test;

import java.util.Properties;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.volc.util.ReadFromFileUtil;

public class MainTest {

	public static void main(String[] args) {
		//seperatLine();
		getSystemProperties();
		seperatLine();
		
		test();
		seperatLine();
		
		//objectCompare();
		//seperatLine();

	}
	
	public static void objectCompare(){
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();
		
		json1.put("userId", "333");
		json1.put("userName", "Allen");
		json1.put("userAddress", "address");
		
		json2.put("userName", "Allen");
		json2.put("userId", "333");
		json2.put("userAddress", "address");
		
		System.out.println("json1:"+json1.toJSONString());
		System.out.println("json2:"+json2.toJSONString());
		
		System.out.println(json1.equals(json2));
	}
	
	public static void test(){
		String filePath1 = "/src/main/resources/json/test.json";
		String filePath2 = "/src/main/resources/acquire/createOrderAndPay.json";
		String filePath3 = "/src/main/resources/response/createOrderAndPay.json";
		
		String string = ReadFromFileUtil.readFromFile(filePath3);
		//JSONObject json = ReadFromFileUtil.getJsonFromFile(filePath1);
		
		System.out.println(string);

	}
	
	public static void getSystemProperties(){
        Properties sysProperty=System.getProperties(); //œµÕ≥ Ù–‘
        Set<Object> keySet = sysProperty.keySet();
        for (Object object : keySet) {
            String property = sysProperty.getProperty(object.toString());
            System.out.println(object.toString()+" : "+property);
        }
		System.out.println("user.dir: "+System.getProperty("user.dir"));
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
	}
	
	public static void seperatLine(){
		System.out.println("-------------------------------");
	}

}
