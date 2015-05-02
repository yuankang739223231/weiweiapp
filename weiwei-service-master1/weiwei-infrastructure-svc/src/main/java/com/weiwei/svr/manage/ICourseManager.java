package com.weiwei.svr.manage;

import java.util.List;

public interface ICourseManager {
	public List<?> findByCategoryId(String categoryID, int startId, int endId);
	public List<?> findByCourseId(String CourseId);
}
