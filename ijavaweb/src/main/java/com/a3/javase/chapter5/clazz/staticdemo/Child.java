package com.a3.javase.chapter5.clazz.staticdemo;

import org.springframework.stereotype.Component;

//@Component
public class Child extends Father {
	//private static String staticStr1 ="staticStr1 in Child";
	public Child(){
		
	}
	
	public Child(String name,int age){
		this.name= name;
		this.age=age;
	}
	public void talk(){
		name="child";
		age=3;
		System.out.println("Child:I'm "+this.name+","+this.age +" years old.");
	}
	
	public static void print(){
		System.out.println("Child:I'm always the child!");
	}
	
	public void printStaticStr1(){
		staticStr1="changed by child";
		System.out.println("staticStr1="+staticStr1);
	}
}
