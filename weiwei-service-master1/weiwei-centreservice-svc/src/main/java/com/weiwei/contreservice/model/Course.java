package com.weiwei.contreservice.model;

public class Course {
	protected String title;
	protected String body;
	protected String courseID;
	
	public void setTitle(String str){
		title = str;
	}
	public String getTitle(){
		return title;
	}
	public void setBody(String str){
		body = str;
	}
	public String getBody(){
		return body;
	}
	public void setCourseID(String str){
		courseID = str;
	}
	public String getCourseID(){
		return courseID;
	}
}
