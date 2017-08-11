package ijavaweb;

import com.volc.util.ReadFromFileUtil;

public class MainTest {

	public static void main(String[] args) {
		String filePath = "/src/main/resources/json/test.json";
		String string = ReadFromFileUtil.ReadFromFile(filePath);
		
		System.out.println(string);

		System.out.println(ReadFromFileUtil.ReadFromFileAsJson(filePath));

	}

}
