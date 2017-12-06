package com.intesasanpaolo.garn0.archi.fmk.core.segregation;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

public class InterceptorUtils {
	
	HttpServletRequest request;
	
	Map<String,String> headerVariables;
	Map<String,String> pathVariables;
	Map<String,String[]> queryStringVariables;
	Map<String,String[]> bodyVariables;
	
	public InterceptorUtils(HttpServletRequest req,Method method) {
		request = req;
		
		headerVariables = initHeaderVariables();
		pathVariables = initPathVariables(method);
		queryStringVariables = initQueryStringVariables();
		bodyVariables = initBodyVariables();
		
	}

	private Map<String,String> initPathVariables(Method method) {
		
		Map<String,String> result = new HashMap<String, String>();
		
		RequestMapping mapping = method.getAnnotation(RequestMapping.class);
		String mask = mapping.value()[0];
		
		if(!mask.contains("{"))
			return result;	
		
		String url = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		
		if(mask.startsWith("/"))
			mask = mask.substring(1);
			url = url.substring(1);
		
		String[] keys = mask.split("\\/");
		String[] vals = url.split("\\/");		
		
		for(int i = 0; i < keys.length; i++) {
			if(keys[i].startsWith("{")) {			
				result.put(keys[i].substring(1, keys[i].length()-1), vals[i]);
			}				
		}
		
		return result;
		
	}
	private Map<String,String[]> initBodyVariables() {	
		
		try {
			Scanner s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	private Map<String,String[]> initQueryStringVariables() {
		return request.getParameterMap();
	}
	private Map<String, String> initHeaderVariables() {
		Map<String, String> map = new HashMap<String, String>();

		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String value = request.getHeader(key);
			map.put(key, value);
		}

		return map;	
	}

	
	public Map<String, String> getHeaderVariables() {
		return headerVariables;
	}

	public Map<String, String> getPathVariables() {
		return pathVariables;
	}

	public Map<String, String[]> getQueryStringVariables() {
		return queryStringVariables;
	}

	public Map<String, String[]> getBodyVariables() {
		return bodyVariables;
	}

	
}
