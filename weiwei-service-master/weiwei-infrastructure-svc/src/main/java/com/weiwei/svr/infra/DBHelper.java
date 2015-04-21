package com.weiwei.svr.infra;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.weiwei.svr.utils.SVRFieldValidator;

public class DBHelper {
	private Connection conn = null;
	public DBHelper(Connection c){
		conn = c;
	}
	
	public List getResult(String sql) throws SQLException{
		return getResult(sql, null);
	}
	
	public List getResult(String sql, List params) throws SQLException{
		Vector v = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		
		try{
			if(params == null && (SVRFieldValidator.isNullOrBlank(sql))){
				
			}else{
				if(params != null && params.size() > 0){
					pstmt = conn.prepareStatement(sql);
					for (int i = 0; i<params.size(); i++){
						pstmt.setObject(i+1, params.get(i));
					}
					rs = pstmt.executeQuery();
				}else{
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
				}
				
				ResultSetMetaData rsmd = rs.getMetaData();
				int mx = rsmd.getColumnCount();
				String tempString = "", colName = "";
				int x = 1;
				v = new Vector();
				Map map = null;
				while(rs.next()){
					x = 1;
					map = new HashMap();
					while(x <= mx){
						colName = rsmd.getColumnName(x);
						tempString = rs.getString(x);
						map.put(colName, tempString);
						x++;
					}
					v.add(map);
				}
				close(rs);
			}
		} catch(SQLException e){
			throw e;
		} finally{
			close(rs);
			close(pstmt);
			close(stmt);
		}
		return v;
	}
	
	public static void close(ResultSet rs){
		try{
			if(null != rs){
				rs.close();
			}
		}catch(SQLException e){
			
		}
	}
	public static void close(Statement stmt){
		try{
			if(null != stmt){
				stmt.close();
			}
		}catch (SQLException e){
			
		}
	}
}
