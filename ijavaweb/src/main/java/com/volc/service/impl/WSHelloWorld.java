package com.volc.service.impl;

import javax.xml.ws.Endpoint;

public class WSHelloWorld {
	
	 public static void main(String[] args) {
         System.out.println("web service start");
         HelloWorldImpl implementor= new HelloWorldImpl();
         String address="http://localhost:9090/HelloWorld";
         Endpoint publish = Endpoint.publish(address, implementor);
         System.out.println("web service started...");
}

}
