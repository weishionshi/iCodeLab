package com.volc.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;

import com.volc.bo.User;
import com.volc.service.interfaces.IHelloWorld;

@WebService(endpointInterface="com.volc.service.interfaces.IHelloWorld",serviceName="HelloWorld")
public class HelloWorldImpl implements IHelloWorld {

	 Map<Integer, User> users = new LinkedHashMap<Integer, User>();

	 
     public String sayHi(String text) {
                 return "Hello " + text;
    }



    public String sayHiToUser(User user) {
              users.put(users.size()+1, user);
              return "Hello "+ user.getUserName();
    }

    public String[] SayHiToUserList(List<User> userList) {
              String[] result = new String[userList.size()];
              int i=0;
              for(User u:userList){
                   result[i] = "Hello " + u.getUserName();
                   i++;
              }
      return result;
    }

}
