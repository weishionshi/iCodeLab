package com.a3.test;

import java.util.Properties;
import java.util.Set;

import com.a3.javase.chapter9.thread.ThreadDemo1;
import com.a3.javase.chapter9.thread.ThreadSleepAndInterrupt;
import com.alibaba.fastjson.JSONObject;
import com.volc.util.ReadFromFileUtil;

public class MainTest {

	public static void main(String[] args) {
//		testTherad1();
		testThreadSleepAndInterrupt();
	}

	public static void objectCompare() {
		JSONObject json1 = new JSONObject();
		JSONObject json2 = new JSONObject();

		json1.put("userId", "333");
		json1.put("userName", "Allen");
		json1.put("userAddress", "address");

		json2.put("userName", "Allen");
		json2.put("userId", "333");
		json2.put("userAddress", "address");

		System.out.println("json1:" + json1.toJSONString());
		System.out.println("json2:" + json2.toJSONString());

		System.out.println(json1.equals(json2));
	}

	public static void test() {
		String filePath1 = "/src/main/resources/json/test.json";
		String filePath2 = "/src/main/resources/acquire/createOrderAndPay.json";
		String filePath3 = "/src/main/resources/response/createOrderAndPay.json";

		String string = ReadFromFileUtil.readFromFile(filePath3);
		// JSONObject json = ReadFromFileUtil.getJsonFromFile(filePath1);

		System.out.println(string);

	}

	/*
	 * public static void getSystemProperties() { Properties sysProperty =
	 * System.getProperties(); // ϵͳ���� Set keySet = sysProperty.keySet(); for
	 * (Object object : keySet) { String property =
	 * sysProperty.getProperty(object.toString());
	 * System.out.println(object.toString() + " : " + property); }
	 * System.out.println("user.dir: " + System.getProperty("user.dir"));
	 * System.out.println(); System.out.println(); System.out.println();
	 * System.out.println();
	 * 
	 * }
	 */

	public static void seperatLine() {
		System.out.println("-------------------------------");
	}

	public static void testTherad1() {
		ThreadDemo1 threadDemo1 = new ThreadDemo1();
		Thread pp = new Thread(threadDemo1);
		pp.start();
		int i = 0;
		for (int x = 0; x < 10; x++) {
			if (i == 5) {
				try {
					pp.join();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
			System.out.println("main Thread " + i++);
		}
	}

	public static void testThreadSleepAndInterrupt() {
		{
			ThreadSleepAndInterrupt si = new ThreadSleepAndInterrupt();
			Thread t = new Thread(si);
			t.start();
			// 在此休眠是为确保线程能运行一会
			try {
				Thread.sleep(2000);
			} catch (InterruptedException x) {
			}
			System.out.println("A:t.isInterrupted() = " + t.isInterrupted());
			System.out.println("在 main()方法中 - 中断其它线程");
			t.interrupt();
			System.out.println("B:t.isInterrupted() = " + t.isInterrupted());

			try {
				Thread.sleep(2000);
				System.out.println("线程没有被中断!");
			} catch (InterruptedException x) {
				System.out.println("线程被中断!");
			}
			// 因为 sleep 抛出了异常，所以它清除了中断标志,D变成了false
			System.out.println("D:t.isInterrupted() = " + t.isInterrupted());
			System.out.println("在 main()方法中 - 退出");
		}
	}

}
