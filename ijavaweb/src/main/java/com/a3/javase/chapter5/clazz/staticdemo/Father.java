package com.a3.javase.chapter5.clazz.staticdemo;

import org.springframework.stereotype.Component;

//@Component
public class Father {
	protected static String staticStr1 = "staticString1 in Father";
	protected String name="Father";
	protected int age=30;
	private String privateStr1;
	
	public void talk(){
		System.out.println("Father:I'm "+this.name+","+this.age +" years old.");
	}
	
	public static void print(){
		System.out.println("Father:I'm always the father!");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}
