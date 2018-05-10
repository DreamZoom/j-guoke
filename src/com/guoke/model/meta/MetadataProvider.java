package com.guoke.model.meta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MetadataProvider {
	public static List<FieldInfo> load(String className) throws SecurityException, ClassNotFoundException{
		List<FieldInfo> list=new ArrayList<FieldInfo>();
		Field[] fields=Class.forName(className).getDeclaredFields();
		for(Field field : fields) {
			if(field.getName().equals("serialVersionUID")) continue;
			Metadata meta=field.getAnnotation(Metadata.class);
			FieldInfo info=new FieldInfo();
			info.setName(field.getName());
			info.setDisplay(meta==null?field.getName():meta.name());
			if(meta==null || "".equals(meta.type())) {
				info.setType(field.getType().getSimpleName());
			}
			else {
				info.setType(meta.type());
			}
			info.setMode(meta==null?"create|edit|list":meta.mode());
			list.add(info);
		}
		return list;
	}
}
