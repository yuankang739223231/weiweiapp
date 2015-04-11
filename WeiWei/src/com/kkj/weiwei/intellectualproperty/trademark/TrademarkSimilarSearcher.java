package com.kkj.weiwei.intellectualproperty.trademark;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TrademarkSimilarSearcher extends TrademarkSearcherOnline{

	public String toURLString(){
		String strUrl = Constants.URLSTR+Constants.URLSTR_SELECT_LIST;
		strUrl += Constants.URLSTR_INTCLS+categoryNumber;
		strUrl += Constants.URLSTR_AND;
		strUrl += Constants.URLSTR_CONTENT+searchContent;
		strUrl += Constants.URLSTR_AND;
		strUrl += Constants.URLSTR_TYPE+searchType;
		strUrl += Constants.URLSTR_AND;
		strUrl += Constants.URLSTR_SF;
		return strUrl;
	}
	
	public String getWelcomePageImageCode(){
		String imageCodeStr = "";
		try{
			String strUrl = Constants.URLSTR + Constants.URLSTR_SIMILAR_SEARCH_PAGE;
			Document doc = Jsoup.connect(strUrl).get();
			Elements img = doc.getElementsByTag("img");
			for(Element el : img){
				String src = el.absUrl("src");
				if(src.contains("CodeImage")){
					getImage(src);
				}
			}
			
			String strUrlnew = "http://sbcx.saic.gov.cn:9080/tmois/wsjscx_getSlectListBySys.xhtml?intcls=1&content=%25E6%2598%259F%25E7%25A9%25BA&type=CHN&codeShow=";
			File fin = new File("/Users/Yingkai/AndroidStudioProjects/image.txt");
			FileInputStream fis = new FileInputStream(fin);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String line = null;
			String code = "";
			while((line = br.readLine()) != null){
				code += line;
			}
			br.close();
			fis.close();
			strUrlnew += code;
			Document docnew = Jsoup.connect(strUrlnew).get();
			System.out.print(docnew);
		}catch(ClientProtocolException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return imageCodeStr;
	}
	
	private static void getImage(String src) throws IOException{
		URL url = new URL(src);
		InputStream in = url.openStream();
		OutputStream out = new BufferedOutputStream(new FileOutputStream("/Users/Yingkai/AndroidStudioProjects/image.png"));
		for(int b; (b = in.read()) != -1; ){
			out.write(b);
		}
		out.close();
		in.close();
	}
}
