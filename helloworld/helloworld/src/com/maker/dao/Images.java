package com.maker.dao;

import java.util.ArrayList;

import com.maker.util.HttpUtil;

public class Images{
	public final static ArrayList<String> imageid=new ArrayList<String>();
	public static void getImages(int eid){
		String result=query(eid);
		int j=0;
		for(int i=0;i<result.length();i++)
		{
			if(result.charAt(i)==',')
			{
				imageid.add(result.substring(j,i));
				j=i+1;
			}
		}
	}
	private static String query(int eid){
    	String queryString="eid="+eid;
    	String url=HttpUtil.BASE_URL+"servlet/PhotoGetPid?"+queryString;
    	return HttpUtil.queryStringForPost(url);
    }
}