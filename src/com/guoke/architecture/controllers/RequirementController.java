package com.guoke.architecture.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.guoke.architecture.models.Requirement;
import com.guoke.controller.BaseController;


@Controller
@RequestMapping("/Requirement")
public class RequirementController extends BaseController<Requirement> {
	
	@RequestMapping("/Index")
	public  ModelAndView Index() {

		return View("/Requirement/Index");
	}

}
