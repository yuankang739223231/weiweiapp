package com.kkj.weiwei.intellectualproperty.trademark;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TrademarkSearcherOnline implements ITrademarkSearcherOnline{
	private ArrayList<TradeMark> searchResult;
	private boolean noResult = true;
	
	public String applicationNumber;
	public String categoryNumber;
	public String searchContent;
	public String searchType;
	
	public ArrayList<TradeMark> doSearch(){
		searchResult = new ArrayList<TradeMark>();
		String strUrl = toURLString();
		getResponse(strUrl);
		return searchResult;
	}
	
	public void getResponse(String strUrl){
		try{
			Document doc = Jsoup.connect(strUrl).get();
			ArrayList<Elements> tds_list = new ArrayList<Elements>();
			for(Element table : doc.select("table")){
				for(Element tbody : table.select("tbody")){
					for(Element subTable : tbody.select("table")){
						for(Element row : subTable.select("tr")){
							tds_list.add(row.select("td"));
						}
					}
				}
			}
			int i=0;
			for(Elements tds : tds_list){
				if(i==0){
					i++;
					continue;
				}
				if(tds.size() == 1){
					noResult = true;
					break;
				}else{
					noResult = false;
				}
				TradeMark tm = new TradeMark();
				tm.setApplicationNumber(tds.get(1).text());
				tm.setCategoryNumber(tds.get(2).text());
				tm.setName(tds.get(3).text());
				tm.setApplicantName(tds.get(4).text());
				searchResult.add(tm);
				i++;
			}
		}
		catch(ClientProtocolException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public String toURLString(){
		return null;
	}
	
	public void setApplicationNumber(String string){
		applicationNumber = string;
	}
	public String getApplicationNumber(){
		return applicationNumber;
	}
	public void setCategoryNumber(String string){
		categoryNumber = string;
	}
	public String getCategoryNumber(){
		return categoryNumber;
	}
	public void setSearchContent(String string){
		searchContent = string;
	}
	public String getSearchContent(){
		return searchContent;
	}
	public void setSearchType(String string){
		searchType = string;
	}
	public String getSearchType(){
		return searchType;
	}
	public void setNoResult(boolean b){
		noResult = b;
	}
	public boolean getNoResult(){
		return noResult;
	}
	public boolean hasResult(){
		return !noResult;
	}
}
