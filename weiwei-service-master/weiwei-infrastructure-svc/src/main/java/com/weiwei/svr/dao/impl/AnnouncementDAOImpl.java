package com.weiwei.svr.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.weiwei.svr.dao.IAnnouncementDAO;
import com.weiwei.svr.infra.DBHelper;

public class AnnouncementDAOImpl implements IAnnouncementDAO{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<?> findBySequenceId(int startId, int endId){
		
		List<?> result = null;
		
		String numbers = String.valueOf(endId-startId+1);
		String sql = "SELECT title, url FROM dede_co_htmls LIMIT "+numbers;
 
		Connection conn = null;
 
		try {
			conn = dataSource.getConnection();
			DBHelper dbh = new DBHelper(conn);
			result = dbh.getResult(sql);
			
			return result;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}