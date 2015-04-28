package com.weiwei.centreservice.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.weiwei.centreservice.common.base.Constants;
import com.weiwei.centreservice.common.request.AnnouncementRequest;
import com.weiwei.contreservice.beans.AnnouncementBean;
import com.weiwei.service.processors.base.BaseProcessor;
import com.weiwei.svr.dao.IAnnouncementDAO;
import com.weiwei.svr.infra.ConnectionManager;
import com.weiwei.svr.manage.AnnounceManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnouncementProcessor extends BaseProcessor{
	
	private List<?> announcementList_db = null;
	private List<AnnouncementBean> announcementList_response = null;
	private AnnouncementRequest request = null;
	protected ApplicationContext ctx;
	
	@Override
	protected void preProcess(Map scopes){
		request = (AnnouncementRequest)scopes.get(Constants.CS_ANNOUNCEMENT_SERVICE_REQUEST);
	}

	@Override
	protected String executeProcess(Map scopes) {
		// TODO Auto-generated method stub
		
		ctx = new ClassPathXmlApplicationContext("classpath*:centreservice/Announcement.xml");
		AnnounceManager announceManager = (AnnounceManager) ctx.getBean("announceManagerImpl");
		
		if(request.getUrl() != null && !"".equalsIgnoreCase(request.getUrl().trim())){
			announcementList_db = announceManager.findByUrl(request.getUrl());
			return Constants.EVENT_ANNOUNCE_BODY;
		}
		else if(request.getStartNumber()!=null && request.getEndNumber()!=null){
			if(Integer.parseInt(request.getEndNumber()) >= Integer.parseInt(request.getStartNumber())){
				announcementList_db = announceManager.findBySequenceId(Integer.parseInt(request.getStartNumber()), Integer.parseInt(request.getEndNumber()));
				return Constants.EVENT_ANNOUNCE_LIST;
			}
		}
		
		/*
		ConnectionManager connMrg = new ConnectionManager();
		ApplicationContext context = connMrg.getContext();
		IAnnouncementDAO announcementDAO = (IAnnouncementDAO) context.getBean("announcementDAO");
        
		if(request.getUrl() != null && !"".equalsIgnoreCase(request.getUrl().trim())){
			announcementList_db = announcementDAO.findByUrl(request.getUrl());
			return Constants.EVENT_ANNOUNCE_BODY;
		}
		else if(request.getStartNumber()!=null && request.getEndNumber()!=null){
			if(Integer.parseInt(request.getEndNumber()) >= Integer.parseInt(request.getStartNumber())){
				announcementList_db = announcementDAO.findBySequenceId(Integer.parseInt(request.getStartNumber()), Integer.parseInt(request.getEndNumber()));
				return Constants.EVENT_ANNOUNCE_LIST;
			}
		}
		*/
		return Constants.EVENT_FAIL;
	}
	
	@Override
	protected String postProcess(Map scopes, String event){
		announcementList_response = new ArrayList<AnnouncementBean>();
		for(int i=0; i<announcementList_db.size(); i++){
			com.weiwei.svr.model.Announce announce = (com.weiwei.svr.model.Announce)announcementList_db.get(i);
			if(Constants.EVENT_ANNOUNCE_LIST.equalsIgnoreCase(event)){
				AnnouncementBean announcement = new AnnouncementBean(announce);
				announcement.fillPublishTime();
				announcementList_response.add(announcement);
			}
			else if(Constants.EVENT_ANNOUNCE_BODY.equalsIgnoreCase(event)){
				AnnouncementBean announcement = new AnnouncementBean();
				announcement.fillBody(announce.getResult());
				announcementList_response.add(announcement);
			}
		}
		
		scopes.put(Constants.SERVICE_RESPONSE, announcementList_response);
		return event;
	}

}
