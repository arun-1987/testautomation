package com.selenium.connectors;

import java.io.FileReader;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.selenium.ui.helper.ResourceHelper;

public class JsonConnector {

	public static String getConfig(String requestparam) {
		 try {
			Object obj = new JSONParser().parse(new FileReader(ResourceHelper.getResourceHelper("/src/main/resources/config.json")));
			 JSONObject jo = (JSONObject) obj;
			 requestparam = (String) jo.get(requestparam); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		 return requestparam;	 
	}

	
	
}
