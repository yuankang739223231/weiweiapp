package com.weiwei.contreservice.model;

import java.util.Map;

import com.weiwei.centreservice.common.base.UtilityMethods;

public class Announcement {
	protected String title;
	protected String body;
	protected String subTitle;
	protected String source;
	
	public void fillAnnouncement(Map map){
		title = UtilityMethods.getMapValueString(map, "title");
		body = UtilityMethods.getMapValueString(map, "body");
		subTitle = UtilityMethods.getMapValueString(map, "subTitle");
		source = UtilityMethods.getMapValueString(map, "source");
	}
	
	public void setTitle(String str){
		title = str;
	}
	public String getTitle(){
		return title;
	}
	public void setPublishTime(String str){
		subTitle = str;
	}
	public String getPublishTime(){
		return subTitle;
	}
	public void setBody(String str){
		body = str;
	}
	public String getBody(){
		return body;
	}
	public void setOwner(String str){
		source = str;
	}
	public String getOwner(){
		return source;
	}
}
