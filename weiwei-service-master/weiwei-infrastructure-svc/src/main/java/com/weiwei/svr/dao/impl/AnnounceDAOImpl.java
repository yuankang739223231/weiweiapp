package com.weiwei.svr.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.weiwei.svr.model.Announce;
import com.weiwei.svr.dao.IAnnouncementDAO;

@Service
public class AnnounceDAOImpl extends JdbcDaoSupport implements IAnnouncementDAO{
	
	@Autowired
	public AnnounceDAOImpl(DataSource dataSource){
		setDataSource(dataSource);
	}
	
	public List<Announce> findBySequenceId(int startId, int endId) {
		// TODO Auto-generated method stub
		
		String numbers = String.valueOf(endId-startId+1);
		String sql = "SELECT title, url FROM dede_co_htmls where nid=4 and (url like '%2015-03%' or url like '%2015-04%') ORDER BY url desc LIMIT "+numbers;
		return getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Announce.class));
		
	}

	public List<?> findByUrl(String url) {
		// TODO Auto-generated method stub
		String sql = "SELECT result FROM dede_co_htmls WHERE nid=4 AND url=?";
		
		return  getJdbcTemplate().query(sql, new String[]{url}, new BeanPropertyRowMapper(Announce.class));
	}

}
