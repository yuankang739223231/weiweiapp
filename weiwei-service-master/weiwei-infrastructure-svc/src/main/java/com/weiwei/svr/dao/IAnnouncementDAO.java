package com.weiwei.svr.dao;

import java.util.List;

public interface IAnnouncementDAO {
	public List<?> findBySequenceId(int startId, int endId);
}