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

import org.springframework.context.ApplicationContext;

public class AnnouncementProcessor extends BaseProcessor{
	
	private List<?> announcementList_db = null;
	private List<AnnouncementBean> announcementList_response = null;
	private AnnouncementRequest request = null;
	
	@Override
	protected void preProcess(Map scopes){
		request = (AnnouncementRequest)scopes.get(Constants.CS_ANNOUNCEMENT_SERVICE_REQUEST);
	}

	@Override
	protected String executeProcess(Map scopes) {
		// TODO Auto-generated method stub
		String event = Constants.EVENT_SUCCESS;
		ConnectionManager connMrg = new ConnectionManager();
		ApplicationContext context = connMrg.getContext();
		IAnnouncementDAO announcementDAO = (IAnnouncementDAO) context.getBean("announcementDAO");
        
        announcementList_db = announcementDAO.findBySequenceId(Integer.parseInt(request.getStartNumber()), Integer.parseInt(request.getEndNumber()));
		return event;
	}
	
	@Override
	protected String postProcess(Map scopes, String event){
		announcementList_response = new ArrayList<AnnouncementBean>();
		for(int i=0; i<announcementList_db.size(); i++){
			Map valueMap = (Map)announcementList_db.get(i);
			AnnouncementBean announcement = new AnnouncementBean();;
			announcement.fillAnnouncement(valueMap);
			announcementList_response.add(announcement);
		}
		
		scopes.put(Constants.SERVICE_RESPONSE, announcementList_response);
		return event;
	}

}