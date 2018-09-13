package com.a3.javase.chapter15.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.a3.javase.chapter14.annotation.PasswordUtils;
import com.a3.javase.chapter14.annotation.Usecase;

public class AnnotationDemo {
	public static void main(String[] args) {
	     List<Integer> useCases = new ArrayList<Integer>();
	     Collections.addAll(useCases, 47, 48, 49, 50);
	     trackUseCases(useCases, PasswordUtils.class);
	 }
	 
	 public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
	     for (Method m : cl.getDeclaredMethods()) {
	         Usecase uc = m.getAnnotation(Usecase.class);
	         if (uc != null) {
	             System.out.println("Found Use Case:" + uc.id() + " "
	                         + uc.description());
	             useCases.remove(new Integer(uc.id()));
	         }
	     }
	     for (int i : useCases) {
	         System.out.println("Warning: Missing use case-" + i);
	     }
	 }
}
