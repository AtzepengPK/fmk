package com.intesasanpaolo.garn0.archi.fmk.impl;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.intesasanpaolo.garn0.archi.fmk.core.segregation.KLData;
import com.intesasanpaolo.garn0.archi.fmk.core.segregation.Segregate;

@RestController
public class Controller {	
	
	@Segregate(data= {
			@KLData(resourceType="path1",resourceValue= {"path2,param"})
	})
	@RequestMapping("/test/{path1}/{path2}")
	public void test(@PathVariable String path1,
			@PathVariable String path2,
			@RequestParam String param) {		
		
		
		//CALL BACK-END WITH KEYLOOKUPRESPONSE
	}

}
