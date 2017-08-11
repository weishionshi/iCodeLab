package com.volc.util;

import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import com.alibaba.fastjson.JSON;  
import com.alibaba.fastjson.JSONArray;  
import com.alibaba.fastjson.JSONObject;
import com.volc.bo.User;  
  
//下面是FastJson的简介：常用的方法！  
//  Fastjson API入口类是com.alibaba.fastjson.JSON，常用的序列化操作都可以用JSON类上的静态方法完成。
//	把JSON文本parse为JSONObject或者JSONArray
//  public static final Object parse(String text);
//	把JSON文本parse成JSONObject
//  public static final JSONObject parseObject(String text)； 
//	把JSON文本parse成JSONArray 
//	public static final JSONArray parseArray(String text);

//	把JSON文本parse为JavaBean
//  public static final <T> T parseObject(String text, Class<T> clazz);     
//	把JSON文本parse成JavaBean集合
//  public static final <T> List<T> parseArray(String text, Class<T> clazz);  


//  public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本   
//  public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为pretty的JSON文本   
//  public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray（和上面方法的区别是返回值不一样） 

public class JSon {

	public static void main(String[] args){
		
		String2Json();
		Json2Bean();
	}
	
	public static void String2Json(){
		
		String jString = "{\"userId\":33310001,\"userName\":\"Jim\",\"boolean\":true,\"null\":null,\"number\":123}";	
		JSONObject jsonObj = JSON.parseObject(jString);
		System.out.println(JSON.toJSONString(jsonObj, true));
		/*	output
		 * {
			"number":123,
			"boolean":true,
			"userName":"Jim",
			"userId":33310001
		}
		*/

	}
	
	
	
	public static void Json2Bean(){
		
		JSONObject json = new JSONObject();
		json.put("userId", "33310001");
		json.put("userName","Jim");
		json.put("statusDate","2017-08-10");
		
		//JSON.parseObject(json, User.class); 此写法 错误
		User user = JSON.parseObject(json.toString(), User.class);
		
		System.out.println("print user:  "+user);
		/**output
		 * com.volc.bo.User@1c655221
		 */
		
		
		System.out.println("print json.toJSONString():  "+json.toJSONString());
		/**output
		 * {"statusDate":"2017-08-10","userName":"Jim","userId":"33310001"} 
		 */
		System.out.println("print json.toString():  "+json.toString());
		/**output
		 * {"statusDate":"2017-08-10","userName":"Jim","userId":"33310001"}
		 */
	}
}
