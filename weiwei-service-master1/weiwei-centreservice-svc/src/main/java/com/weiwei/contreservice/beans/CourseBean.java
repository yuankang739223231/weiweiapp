package com.weiwei.contreservice.beans;

import com.weiwei.contreservice.model.Course;
import com.weiwei.svr.dbmodel.CourseBodyTable;

public class CourseBean extends Course{
	public CourseBean(){
		super();
	}
	public CourseBean(com.weiwei.svr.dbmodel.Course c){
		super();
		setTitle(c.getTitle());
		setCourseID(c.getId());
	}
	public CourseBean(CourseBodyTable c){
		super();
		setBody(c.getBody());
		setCourseID(c.getAid());
	}
	
}
