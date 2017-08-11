package com.volc.util;

import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import com.alibaba.fastjson.JSON;  
import com.alibaba.fastjson.JSONArray;  
import com.alibaba.fastjson.JSONObject;
import com.volc.bo.User;  
  
//������FastJson�ļ�飺���õķ�����  
//  Fastjson API�������com.alibaba.fastjson.JSON�����õ����л�������������JSON���ϵľ�̬������ɡ�
//	��JSON�ı�parseΪJSONObject����JSONArray
//  public static final Object parse(String text);
//	��JSON�ı�parse��JSONObject
//  public static final JSONObject parseObject(String text)�� 
//	��JSON�ı�parse��JSONArray 
//	public static final JSONArray parseArray(String text);

//	��JSON�ı�parseΪJavaBean
//  public static final <T> T parseObject(String text, Class<T> clazz);     
//	��JSON�ı�parse��JavaBean����
//  public static final <T> List<T> parseArray(String text, Class<T> clazz);  


//  public static final String toJSONString(Object object); // ��JavaBean���л�ΪJSON�ı�   
//  public static final String toJSONString(Object object, boolean prettyFormat); // ��JavaBean���л�Ϊpretty��JSON�ı�   
//  public static final Object toJSON(Object javaObject); ��JavaBeanת��ΪJSONObject����JSONArray�������淽���������Ƿ���ֵ��һ���� 

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
		
		//JSON.parseObject(json, User.class); ��д�� ����
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
