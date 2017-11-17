package com.volc.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;




public class ReadFromFileUtil {
	
	//private static String prjPath = System.getProperty("user.dir");
	private static String prjPath = "F:/GitHub/iCodeLab/ijavaweb";

	
	public static String readFromFile(String filePath){
		BufferedReader reader = null;
		String string = "";
		//1.先实例化一个File对象
		File file = new File(prjPath+filePath);
		//2.
		
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = "";  
			while((tempString=reader.readLine()) !=null){
				string = string + tempString;
				 System.out.println(tempString); 
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return string;	
				
	}
	
	public static JSONObject getJsonFromFile(String filePath){
		return JSON.parseObject(readFromFile(filePath));

	}
	


}
