package com.volc.service.interfaces;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.volc.bo.User;

@WebService
public interface IHelloWorld {
	
	@SuppressWarnings("restriction")    
	    //@WebParam给参数命名，提高可代码可读性。此项可选   
		String sayHi(@WebParam(name="text") String text);   
		String sayHiToUser(User user);
        String[] SayHiToUserList(List<User> userList);

}
