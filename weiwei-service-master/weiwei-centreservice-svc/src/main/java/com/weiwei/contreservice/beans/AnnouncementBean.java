package com.weiwei.contreservice.beans;

import java.util.Map;

import com.weiwei.centreservice.common.base.UtilityMethods;
import com.weiwei.contreservice.model.Announcement;

public class AnnouncementBean extends Announcement{
	
	@Override
	public void fillAnnouncement(Map map){
		super.fillAnnouncement(map);
		String url = UtilityMethods.getMapValueString(map, "url");
		url = url.substring(0, url.lastIndexOf("/"));
		subTitle = url.substring(url.lastIndexOf("/")+1);
	}
}
