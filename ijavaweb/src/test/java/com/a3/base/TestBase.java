package com.a3.base;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

@ContextConfiguration(locations={"classpath:spring-test-config.xml"})
public class TestBase extends AbstractTestNGSpringContextTests{

	public static Logger logger = Logger.getLogger(TestBase.class);
	
	public TestBase(){
//		BasicConfigurator.configure();
//		logger.setLevel(Level.DEBUG);
		PropertyConfigurator.configure("log4j.properties");
		logger.info("project root path is:"+System.getProperty("user.dir"));
	}
	
	@BeforeMethod
	public void printHint(){
		logger.info("=========================test method begins=============================");
	}
	
	
}
