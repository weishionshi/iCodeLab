package com.a3.javase.chapter12.collection;

import org.apache.log4j.Logger;

public class CollectionDemo {
private String id;
private String name;
private String author;
private String press;

private final Logger logger = Logger.getLogger(this.getClass());


public CollectionDemo(String id, String name, String author, String press) {

	this.id = id;
	this.name = name;
	this.author = author;
	this.press = press;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getPress() {
	return press;
}
public void setPress(String press) {
	this.press = press;
}

@Override
public int hashCode() {
	logger.debug("override hashCode()");
	final int prime = 31;
	int result = 1;
	result = prime * result + ((author == null) ? 0 : author.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((press == null) ? 0 : press.hashCode());
	return result;
}


@Override
public boolean equals(Object obj) {
	logger.debug("override equals()");
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	
	if (getClass() != obj.getClass())
		return false;
	CollectionDemo other = (CollectionDemo) obj;
	if (author == null) {
		if (other.author != null)
			return false;
	} else if (!author.equals(other.author))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (press == null) {
		if (other.press != null)
			return false;
	} else if (!press.equals(other.press))
		return false;
	return true;
}

}
