package com.guoke.model.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface Metadata {  
  
    /** 
     * 是否为序列号 
     * @return 
     */  
    boolean id() default false;  
    /** 
     * 字段名称 
     * @return 
     */  
    String name() default "";  
    
    String mode() default "";   
    /** 
     * 字段描述 
     * @return 
     */  
    String description() default "";  
    /** 
     * 排序字段 
     * @return 
     */  
    int sort() default 1;  
    
    String type() default "";
}  