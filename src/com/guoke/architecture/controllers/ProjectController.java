package com.guoke.architecture.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.guoke.architecture.models.Project;
import com.guoke.controller.BaseController;



@Controller
@RequestMapping("/Project")
public class ProjectController extends BaseController<Project> {
	
	@RequestMapping("/Index")
	public  ModelAndView Index() {

		return View("/Project/Index");
	}

}
