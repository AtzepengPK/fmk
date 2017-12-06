package com.intesasanpaolo.garn0.archi.fmk.core.segregation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface KLData {
	
	String resourceType();
	String[] resourceValue();
}
