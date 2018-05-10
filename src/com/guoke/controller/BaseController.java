package com.guoke.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guoke.model.BaseEntity;
import com.guoke.model.meta.FieldInfo;
import com.guoke.model.meta.MetadataProvider;
import com.guoke.service.BaseService;


@Controller
public abstract class BaseController<TEntity extends BaseEntity> {
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected Map<String, String> map;
	
	@SuppressWarnings("unchecked")
	public BaseService<TEntity> Service(){
		String serviceName=this.getClass().getSimpleName();
		serviceName=serviceName.replace("Controller", "Service");
		serviceName=(new StringBuilder()).append(Character.toLowerCase(serviceName.charAt(0))).append(serviceName.substring(1)).toString();
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();   
		return (BaseService<TEntity>)context.getBean(serviceName);
	}

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	    
		map=new HashMap<String, String>();
		
		Enumeration<String> enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
			String paraName=(String)enu.nextElement();  
			map.put(paraName, request.getParameter(paraName));
		}
		
	}
	
	@RequestMapping(value = "/Create", produces = "application/json;charset=UTF-8")
	public @ResponseBody String Create(TEntity model) {
		try {
			TEntity result = Service().create(model,map);	
			return Success("创建成功",result);
		} catch (Exception e) {
			// TODO: handle exception
			return Error("创建失败"+e.getMessage());
		}
		
		
	}
	
	@RequestMapping(value = "/Update", produces = "application/json;charset=UTF-8")
	public @ResponseBody String Update(TEntity model) {
		try {
			TEntity result =Service().edit(model,map);		
			return Success("更新成功",result);
		} catch (Exception e) {
			// TODO: handle exception
			return Error("更新失败"+e.getMessage());
		}
	}
	
	@RequestMapping(value = "/Delete", produces = "application/json;charset=UTF-8")
	public @ResponseBody String Delete(int id) {
		try {
			Service().delete(id);		
		} catch (Exception e) {
			// TODO: handle exception
			return Error("删除失败"+e.getMessage());
		}	
		return Success("删除成功");
	}
	
	@RequestMapping(value = "/BatchDelete", produces = "application/json;charset=UTF-8")
	public @ResponseBody String BatchDelete(String keys) {
		try {
			Service().batchDelete(keys);
		} catch (Exception e) {
			// TODO: handle exception
			return Error("批量删除失败"+e.getMessage());
		}	
		return Success("批量删除成功");
	}
	
	
	@RequestMapping(value = "/List", produces = "application/json;charset=UTF-8")
	public @ResponseBody String List(int page, int limit) {			
		return JSON(Service().getPageList(page, limit, map));
	}
	
	
	public String JSON(Object obj) {
		String callback = request.getParameter("callback");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();	
		String json = gson.toJson(obj);
		if(!StringUtils.isEmpty(callback)) {
			return  callback+"("+json+")";
		}
		return json;
	}
	
	public String Success(String message,Object data) {
		Map<String,Object> msg = new HashMap<>();
		msg.put("data", data);
		msg.put("message", message);
		msg.put("result", true);
		msg.put("code", 0);
		return JSON(msg);
	}
	
	public String Success(String message) {
		return Success(message,null);
	}
	
	public String Error(String message) {
		Map<String,Object> msg = new HashMap<>();
		msg.put("message", message);
		msg.put("result", false);
		msg.put("code", -1);
		return JSON(msg);
	}
	
	
	public java.util.List<FieldInfo> MetaInfo() {
		java.util.List<FieldInfo> list=new ArrayList<FieldInfo>();
		try {
			java.util.List<FieldInfo> metas= MetadataProvider.load(Service().getEntityClass().getName());
			list.addAll(metas);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ModelAndView View(String viewName) {
		Object o=new Object();
		return View(viewName,o);
	}
	
	public ModelAndView View(String viewName,Object model) {
		ModelAndView view = new ModelAndView(); 
		view.setViewName(viewName);
		view.addObject("model", model);
		view.addObject("controller", this.getClass().getSimpleName().replace("Controller", ""));
		
		view.addObject("metadata", MetaInfo());
		
		return view;
	}
	
	
	public void log(Object message) {
		
	}
}
