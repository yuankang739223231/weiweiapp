package com.weiwei.svr.dao;

import java.util.List;

public interface ICourseDAO {
	public List<?> findByCategoryId(String categoryID, int startId, int endId);
	public List<?> findByCourseId(String CourseId);
}
