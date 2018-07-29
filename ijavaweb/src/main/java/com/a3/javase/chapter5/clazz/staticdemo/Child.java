package com.a3.javase.chapter5.clazz.staticdemo;

public class Child extends Father {
	private static String staticStr1 ="staticStr1 in Child";
	
	public void talk(){
		System.out.println("Child:I'm "+this.name+","+this.age +" years old.");
	}
	
	public static void print(){
		System.out.println("Child:I'm always the child!");
	}
	
	public void printStaticStr1(){
		System.out.println("staticStr1="+this.staticStr1);
	}
}
