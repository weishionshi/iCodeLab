package com.volc.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.alibaba.fastjson.JSON;




public class ReadFromFileUtil {
	
	private static String prjPath = System.getProperty("user.dir");

	
	public static String ReadFromFile(String filePath){
		BufferedReader reader = null;
		String string = "";
		//1.先实例化一个File对象
		File file = new File(prjPath+filePath);
		//2.
		
		try {
			reader = new BufferedReader(new FileReader(file));
			
			while(reader.readLine() !=null){
				string = string + reader.readLine();
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
	
	public static JSON ReadFromFileAsJson(String filePath){
		return (JSON) JSON.toJSON(ReadFromFile(filePath));

	}
	


}
