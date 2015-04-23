package com.weiwei.contreservice.beans;

import java.util.Map;

import com.weiwei.centreservice.common.base.Constants;
import com.weiwei.centreservice.common.base.UtilityMethods;
import com.weiwei.contreservice.model.Announcement;

public class AnnouncementBean extends Announcement{
	
	@Override
	public void fillAnnouncement(Map map){
		super.fillAnnouncement(map);
		String url = UtilityMethods.getMapValueString(map, Constants.DB_COLUMN_ANNOUNCE_URL);
		fillPublishTime(url);
		
		String result = UtilityMethods.getMapValueString(map, Constants.DB_COLUMN_ANNOUNCE_RESULT);
		fillBody(result);
	}
	
	private void fillPublishTime(String url){
		if("".equalsIgnoreCase(url)){
			return;
		}
		url = url.substring(0, url.lastIndexOf("/"));
		publishTime = url.substring(url.lastIndexOf("/")+1);
	}
	
	private void fillBody(String result){
		if("".equalsIgnoreCase(result)){
			return;
		}
		//parse result
		String startKeyword = "{dede:field name='body'}";
		String endKeyword = "{/dede:field}";
		result = result.substring(result.indexOf(startKeyword));
		body = result.substring(startKeyword.length(), result.indexOf(endKeyword));
		
	}
}
