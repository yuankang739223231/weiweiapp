package com.weiwei.svr.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weiwei.svr.dao.ICourseDAO;
import com.weiwei.svr.manage.ICourseManager;

@Service
public class CourseManagerImpl implements ICourseManager{

	@Autowired
	private ICourseDAO courseDao;
	
	public List<?> findByCategoryId(String categoryID, int startId, int endId) {
		// TODO Auto-generated method stub
		return courseDao.findByCategoryId(categoryID, startId, endId);
	}

	public List<?> findByCourseId(String CourseId) {
		// TODO Auto-generated method stub
		return courseDao.findByCourseId(CourseId);
	}

}
