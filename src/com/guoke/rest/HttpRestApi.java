package com.guoke.rest;

import java.io.IOException;
import java.util.Map;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpRestApi {
	private final OkHttpClient client = new OkHttpClient();
	
	private String serverUrl;
	private String serverKey;
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getServerKey() {
		return serverKey;
	}
	public void setServerKey(String serverKey) {
		this.serverKey = serverKey;
	}
	
	public JsonElement parse(String json) {
	    JsonParser parser = new JsonParser();
	    JsonElement el = parser.parse(json);
	    return el;
	}
	
	public JsonObject parseObject(String json) {
	    JsonElement el =parse(json);
	    if(el.isJsonObject()){
		      return el.getAsJsonObject();
		}
	    return null;
	}
	
	public JsonArray parseArray(String json) {
	    JsonElement el = parse(json);
	    if(el.isJsonArray()){
	      return el.getAsJsonArray();
	    }
	    return null;
	}
	
	public String request(String api,Map<String, String> data) throws IOException {
		String  url = serverUrl + api;
		FormBody.Builder builder = new FormBody.Builder();
		for (Map.Entry<String, String> entry : data.entrySet()) {  
			 builder.add(entry.getKey(),entry.getValue());
		}  	
		RequestBody formBody = builder.build();
		Request request = new Request.Builder().url(url).post(formBody).build();
		Response response = client.newCall(request).execute();	    
		if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);	
		return response.body().string();
	}
	
	public JsonElement requestElement(String api,Map<String, String> data) throws IOException {		
		return parse(request(api,data));
	}
	
	public JsonObject requestObject(String api,Map<String, String> data) throws IOException {		
		return parseObject(request(api,data));
	}
	public JsonArray requestArray(String api,Map<String, String> data) throws IOException {
		return parseArray(request(api,data));
	}
}
