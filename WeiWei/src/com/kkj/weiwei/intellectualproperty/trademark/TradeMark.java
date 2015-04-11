package com.kkj.weiwei.intellectualproperty.trademark;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class TradeMark {

	public String name;
	public String applicationNumber;
	public String categoryNumber;
	public String applicantName;
	
	public JSONObject toJsonObject(){
		try{
            JSONObject jsonObj = new JSONObject();
            if (null != applicationNumber){
                jsonObj.put(Constants.JSON_NAME_APPLICATION_NUMBER, applicationNumber);
            }
            if (null != categoryNumber){
            	jsonObj.put(Constants.JSON_NAME_CATEGORY_NUMBER, categoryNumber);
            }
            if (null != name){
            	jsonObj.put(Constants.JSON_NAME_NAME, name);
            }
            if (null != applicantName){
            	jsonObj.put(Constants.JSON_NAME_APPLICANT_NAME, applicantName);
            }
            return jsonObj;
        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
	}
	
	public void setName(String string){
		name = string;
	}
	public void setApplicationNumber(String string){
		applicationNumber = string;
	}
	public void setCategoryNumber(String string){
		categoryNumber = string;
	}
	public void setApplicantName(String string){
		applicantName = string;
	}
	
}
