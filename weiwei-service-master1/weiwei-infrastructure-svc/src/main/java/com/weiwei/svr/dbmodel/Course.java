package com.weiwei.svr.dbmodel;

public class Course {
	protected String title;
	protected String id;
	
	public void setTitle(String str){
		title = str;
	}
	public String getTitle(){
		return title;
	}
	public void setId(String str){
		id = str;
	}
	public String getId(){
		return id;
	}
}
