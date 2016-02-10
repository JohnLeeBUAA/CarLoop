package com.maker.util;

import java.io.IOException;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpUtil {
	//public static final String BASE_URL="http://10.0.2.2:8080/MyServer/";
	public static final String BASE_URL="http://172.25.147.190:8080/MyServer/";
	public static HttpGet getHttpGet(String url){
		HttpGet request=new HttpGet(url);
		return request;
	}
	public static HttpPost getHttpPost(String url){
		HttpPost request=new HttpPost(url);
		return request;
	}
	public static HttpResponse getHttpResponse(HttpGet request) throws ClientProtocolException,IOException{
		HttpResponse response=new DefaultHttpClient().execute(request);
		return response;
	}
	public static HttpResponse getHttpResponse(HttpPost request) throws ClientProtocolException,IOException{
		HttpResponse response=new DefaultHttpClient().execute(request);
		return response;
	}
	
	public static String queryStringForPost(String url){
		
		HttpPost request=HttpUtil.getHttpPost(url);
		String result=null;
		HttpResponse response;
		try {
			response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result=EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="ÍøÂçÒì³££¡";
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="ÍøÂçÒì³££¡";
			return result;
		}
		return null;
	}
	public static String queryStringForGet(String url){
		HttpGet request=HttpUtil.getHttpGet(url);
		String result=null;
		HttpResponse response;
		try {
			response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result=EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="ÍøÂçÒì³££¡";
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="ÍøÂçÒì³££¡";
			return result;
		}
		return null;
	}
	public static String queryStringForPost(HttpPost request){
		String result=null;
		HttpResponse response;
		try {
			response = HttpUtil.getHttpResponse(request);
			if(response.getStatusLine().getStatusCode()==200){
				result=EntityUtils.toString(response.getEntity());
				return result;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="ÍøÂçÒì³££¡";
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="ÍøÂçÒì³££¡";
			return result;
		}
		return null;
	}
}