package com.a3.javase.chapter14.annotation;

public @interface Usecase {
	public int id();
	public String description() default "no description";
}
