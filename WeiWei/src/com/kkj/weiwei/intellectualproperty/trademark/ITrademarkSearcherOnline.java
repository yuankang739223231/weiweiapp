package com.kkj.weiwei.intellectualproperty.trademark;

import java.util.ArrayList;

public interface ITrademarkSearcherOnline {
	public ArrayList<TradeMark> doSearch();
	public boolean hasResult();
}
