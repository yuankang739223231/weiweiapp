package com.kkj.weiwei.infrastructure;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.kkj.weiwei.intellectualproperty.trademark.TradeMark;
import com.kkj.weiwei.intellectualproperty.trademark.ITrademarkSearcherOnline;
import com.kkj.weiwei.intellectualproperty.trademark.TrademarkSimilarSearcher;
import com.kkj.weiwei.intellectualproperty.trademark.TrademarkStatusSearcher;

@Path(Constants.REQUEST_TRADEMARK)
public class TrademarkService {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTrademarkSearchKeys(Map<String, Object> requestJSON){
		ArrayList<TradeMark> result_list = new ArrayList<TradeMark>();
		ITrademarkSearcherOnline isearcher = null;
		if(requestJSON.containsKey(Constants.SIMILAR_SEARCH_TRADEMARK)){
			System.out.println("Start trademark similar search...");
			Map<String, String> searchKeys = (Map<String, String>)requestJSON.get(Constants.SIMILAR_SEARCH_TRADEMARK);
			TrademarkSimilarSearcher searcher = new TrademarkSimilarSearcher();
			
			
			System.out.println("End trademark similar search.");
		}
		else if(requestJSON.containsKey(Constants.INTEGRATED_SEARCH_TRADEMARK)){
			
		}
		else if(requestJSON.containsKey(Constants.STATUS_SEARCH_TRADEMARK)){
			System.out.println("Start trademark status search...");
			Map<String, String> searchKeys = (Map<String, String>)requestJSON.get(Constants.STATUS_SEARCH_TRADEMARK);
			TrademarkStatusSearcher searcher = new TrademarkStatusSearcher();
			
			if(searchKeys.containsKey(Constants.JSON_NAME_APPLICATION_NUMBER)){
				searcher.setApplicationNumber(searchKeys.get(Constants.JSON_NAME_APPLICATION_NUMBER));
			}
			result_list = searcher.doSearch();
			isearcher = searcher;
			System.out.println("End trademark status search.");
		}
		else{
			return null;
		}

		String json="";
		JSONObject jsonObject = new JSONObject();
		if(!isearcher.hasResult()){
			try{
				jsonObject.put(Constants.JSON_NAME_HAS_RESULT, Constants.NO);
				json = jsonObject.toString();
			}catch(JSONException e){
				e.printStackTrace();
			}
		}else{
			try{
				jsonObject.put(Constants.JSON_NAME_HAS_RESULT, Constants.YES);
				ArrayList<JSONObject> result_list_jsonObject = new ArrayList<JSONObject>();
				for(TradeMark tm : result_list){
					result_list_jsonObject.add(tm.toJsonObject());
				}
				jsonObject.put("results",result_list_jsonObject);
				json = jsonObject.toString();
			}catch(JSONException e){
				e.printStackTrace();
			}
		}
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
		//return Response.status(200).entity(result).build();
	}
	
	@GET
	@Path(Constants.HEALTHCHECK+Constants.CONNECTION)
	public String healthcheckConnection(){
		return "fine";
	}
	
}
