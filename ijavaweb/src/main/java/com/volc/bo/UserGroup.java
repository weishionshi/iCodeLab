package com.volc.bo;

import java.util.ArrayList;  
import java.util.List;

import com.volc.bo.User; 

public class UserGroup {
	
	private int groupId;  
    private String name;  
    private List<User> list = new ArrayList<User>();  
 
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public List<User> getList() {  
        return list;  
    }  
    public void setList(List<User> list) {  
        this.list = list;  
    }
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}  

}
