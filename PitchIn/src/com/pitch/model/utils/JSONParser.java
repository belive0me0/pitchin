package com.pitch.model.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {
	static InputStream inputstream = null;
	static JSONObject jsonObject = null;
	static String json = "";
	
	public JSONParser() {
		
	}
	
	public JSONObject getJSONFromUrl(String url, List params) {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			inputstream = httpEntity.getContent();
			
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		} catch (ClientProtocolException cpe) {
			cpe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} 
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream, "iso-8859-1"), 8);
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line + "n");
			}
			inputstream.close();
			json = stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			jsonObject = new JSONObject(json);
		} catch(JSONException je) {
			je.printStackTrace();
		}
		return jsonObject;
	}
}
