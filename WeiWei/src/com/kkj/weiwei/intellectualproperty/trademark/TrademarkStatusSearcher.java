package com.kkj.weiwei.intellectualproperty.trademark;

public class TrademarkStatusSearcher extends TrademarkSearcherOnline {
	
	//public void doSearch(){
	//	String strUrl = toURLString();
	//	getResponse(strUrl);
	//}
	
	public String toURLString(){
		String strUrl = Constants.URLSTR+Constants.URLSTR_REG+applicationNumber;
		return strUrl;
	}
}
