package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {
	private String stringJson;
	
	public HttpUtils(String stringJson) {
		this.stringJson = stringJson;
	}
	public <T> T toModel(Class<T> models) {
		try {
			return new ObjectMapper().readValue(stringJson, models);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static <T> void toJson(ServletOutputStream write, T models) {
		try {
			new ObjectMapper().writeValue(write, models);;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static HttpUtils toStringJson (BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return new HttpUtils(sb.toString());
	}
}
