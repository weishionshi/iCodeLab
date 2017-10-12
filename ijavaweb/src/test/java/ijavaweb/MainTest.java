package ijavaweb;

import com.alibaba.fastjson.JSONObject;
import com.volc.util.ReadFromFileUtil;

public class MainTest {

	public static void main(String[] args) {
		objectCompare();

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
		String filePath = "/src/main/resources/json/test.json";
		String string = ReadFromFileUtil.ReadFromFile(filePath);
		
		System.out.println(string);
		System.out.println(ReadFromFileUtil.ReadFromFileAsJson(filePath));
	}

}
