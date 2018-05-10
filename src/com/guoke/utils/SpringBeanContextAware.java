package com.guoke.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBeanContextAware implements ApplicationContextAware {
	private static ApplicationContext context;	
	@Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
    	SpringBeanContextAware.context = context;
    }
    public static ApplicationContext getContext(){
        return context;
    }
}
