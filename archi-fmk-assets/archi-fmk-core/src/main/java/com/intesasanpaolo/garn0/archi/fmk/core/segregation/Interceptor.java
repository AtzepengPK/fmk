package com.intesasanpaolo.garn0.archi.fmk.core.segregation;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class Interceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod hm = (HandlerMethod) handler;
		Method method = hm.getMethod();

		InterceptorUtils util = new InterceptorUtils(request, method);

		if (method.isAnnotationPresent(Segregate.class)) {
			Segregate s = method.getAnnotation(Segregate.class);
			
			KLData[] datas = s.data();		
			
			for(KLData data : datas ) {
				//CALL KEYLOOKUP SMART WAY
				
				//IF EVERY CALL IS OK DO NOTHING
				
				//IF >0 CALL ARE KO CUSTOM RESPONSE AND RETURN FALSE
			}
			
		}

		return true;
	}

}
