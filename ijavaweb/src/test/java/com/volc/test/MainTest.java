package com.volc.test;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.volc.util.ReadFromFileUtil;
import org.apache.log4j.Logger;

public class MainTest {

	private static Logger log = Logger.getLogger(MainTest.class);
	public static void main(String[] args) {
		log.debug("debug message");
		log.info("info message");
		log.error("error meesage");
		line();
		
		getSystemProperties();
		line();
		
		//test();
		//line();
		
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
        Iterator it=sysProperty.entrySet().iterator();
        while(it.hasNext()){
        	Map.Entry entry=(Map.Entry)it.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(key +":"+value);
        }
//        Set keySet = sysProperty.keySet();
//        for (Object object : keySet) {
//            String property = sysProperty.getProperty(object.toString());
//            System.out.println(object.toString()+" : "+property);
//        }
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		
	}
	
	public static void line(){
		System.out.println("-------------------------------");
	}
	
	public static void print(Object obj){
		System.out.println(obj.toString());
		
	}

}
