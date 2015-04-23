package com.weiwei.service.controller;

import org.springframework.web.bind.annotation.RequestBody;

import com.weiwei.centreservice.common.request.AnnouncementRequest;
import com.weiwei.contreservice.beans.AnnouncementBean;
import com.weiwei.service.common.response.GeneralServiceResponse;

public interface IServiceController {
	//public GeneralServiceResponse generalService(final GeneralServiceRequest generalServiceRequest);
	//public IntellectualServiceResponse intellectualService(final IntellectualServiceRequest serviceRequest);
	//public CentreServiceResponse centreService(final CentreServiceRequest serviceRequest);
	//public VIPServiceResponse VIPService(final VIPServiceRequest serviceRequest);
	public GeneralServiceResponse<AnnouncementBean> announcementService(@RequestBody AnnouncementRequest serviceRequest);
}
