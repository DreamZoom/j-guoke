package com.guoke.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

public class QueryBuilder {
	List<String> wheres;
	List<String> orders;
	public QueryBuilder() {
		this.wheres=new ArrayList<>();
		this.orders=new ArrayList<>();
	}
	
	public void Where(String where,Object... args) {
		 wheres.add(String.format(where, args));
	}
	
	public void Order(String order,Object... args) {
		orders.add(String.format(order, args));
	}
	
	public String getWhere() {
		return String.join(" and ", wheres);
	}
	
	public String getOrder() {
		return String.join(",", orders);
	}
	
	public void Where(Class<?> c,Map<String,String> map) {
		Field[] list = (Field[]) ArrayUtils.addAll(c.getDeclaredFields(), c.getSuperclass().getDeclaredFields());
		for(Field field:list) {
			if(map.containsKey(field.getName())) {
				this.Where(field.getName()+"="+map.get(field.getName()));
			}
	    }
	}

}
