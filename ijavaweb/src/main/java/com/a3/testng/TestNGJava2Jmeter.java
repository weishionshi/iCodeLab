package com.a3.testng;

import org.testng.annotations.Test;

import com.a3.j2j.Java2Jmeter;

import org.testng.annotations.BeforeClass;

public class TestNGJava2Jmeter {
    Java2Jmeter j2j=null;
  @BeforeClass
  public void beforeClass() {
      System.out.print("run beforeClass...");
      j2j = new Java2Jmeter("com.a3.test.CreateOrderAndPay");
  }


  @Test
  public void transfer() {
    j2j.transfer();
  }
}