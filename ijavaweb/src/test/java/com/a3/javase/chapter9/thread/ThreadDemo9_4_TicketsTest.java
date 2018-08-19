package com.a3.javase.chapter9.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import com.a3.common.TestBase;

public class ThreadDemo9_4_TicketsTest extends TestBase{
	
	@Autowired
	private ThreadDemo9_4_Tickets thread;
	
//	@BeforeClass
//	public void beforeClass(){
//	thread = new ThreadDemo9_4_Tickets();
//	}

	@Test
	public void test() {
		
		//logger.info("调用 start()方法之前 , thread.isAlive() = "+thread.isAlive());  TBD-报错：找不到isAlive()
		new Thread(thread).start();
		new Thread(thread).start();

	}
	
	//TBD-报错
//	org.testng.TestNGException: 
//		Cannot find class in classpath: com.a3.javase.chapter9.thread.ThreadDemo9_4_TicketsTest
//			at org.testng.xml.XmlClass.loadClass(XmlClass.java:81)
//			at org.testng.xml.XmlClass.init(XmlClass.java:73)
//			at org.testng.xml.XmlClass.<init>(XmlClass.java:59)
//			at org.testng.xml.TestNGContentHandler.startElement(TestNGContentHandler.java:544)
}
