package com.pitch.model.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class UserFunctions {
	private JSONParser mJSONParser;
	private static String login_tag = "login";
	private static String register_tag = "register";
	private static String forpass_tag = "forpass";
	private static String chgpass_tag = "chgpass";

	private static String loginURL = "localhost/pitchin_api";
	private static String registerURL = "localhost/pitchin_api";
	private static String forpassURL = "localhost/pitchin_api";
	private static String chgpassURL = "localhost/pitchin_api";

	public UserFunctions() {
		mJSONParser = new JSONParser();
	}
	
	public JSONObject loginUser(String email, String password) {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = mJSONParser.getJSONFromUrl(loginURL, params);
		return json;
	}
	
	public JSONObject changePassword(String newPassword, String email) {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("tag", chgpass_tag));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("newpassword", newPassword));
		JSONObject json = mJSONParser.getJSONFromUrl(chgpassURL, params);
		return json;
		
	}
	
	public JSONObject forgotPassword(String password) {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("tag", forpass_tag));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = mJSONParser.getJSONFromUrl(forpassURL, params);
		return json;		
	}
	
	public JSONObject registerUser(String firstName, String lastName, String userName, String password) {
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("firstName", firstName));
		params.add(new BasicNameValuePair("lastName", lastName));
		params.add(new BasicNameValuePair("userName", userName));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = mJSONParser.getJSONFromUrl(registerURL, params);
		return json;		
	}
	
	public boolean logoutUser(Context context) {
		DatabaseHandler database = new DatabaseHandler(context);
		database.resetTables();
		return true;
	}
}
