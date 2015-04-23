package com.weiwei.svr.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String sql = "SELECT title, url FROM dede_co_htmls where nid=4 and (url like '%2015-03%' or url like '%2015-04%') ORDER BY url desc LIMIT "+numbers;
 
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
	
	public List<?> findByUrl(String title){
		List<?> result = null;
		String sql = "SELECT result FROM dede_co_htmls WHERE nid=4 AND url=?";
		List<String> params = new ArrayList<String>();
		params.add(title);
		
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			DBHelper dbh = new DBHelper(conn);
			result = dbh.getResult(sql, params);
			return result;
		} catch (SQLException e){
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
