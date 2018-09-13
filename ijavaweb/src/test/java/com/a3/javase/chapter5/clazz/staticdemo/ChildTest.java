package com.a3.javase.chapter5.clazz.staticdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.a3.base.TestBase;


public class ChildTest extends TestBase {
	
	@Autowired
	private Child child;
	private Child child2;
	
	ChildTest() {
		super();
	}

	@BeforeClass
	public void beforeClass() {
		logger.info("===================="+this.getClass().getSimpleName() + ": test begins...");
		//child=new Child();
	}

	@AfterClass
	public void afterClass() {
		logger.info("===================="+this.getClass().getSimpleName() + ": test ends.");
		
		child=null;
	}
	
	@Test
	public void testXmlInjection(){
		Assert.assertEquals(child.name,"name by constructor injection");
		Assert.assertEquals(child2.name,"name by property injection");
		
	}

	@Test
	public void print() {
		System.out.println("test print()");
		Child.print();
	}

	@Test
	public void printStaticStr1() {
		child.printStaticStr1();
	}

	@Test
	public void talk() {
		child.talk();
	}
}
