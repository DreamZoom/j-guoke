package com.guoke.architecture.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.guoke.architecture.models.Feature;
import com.guoke.controller.BaseController;


@Controller
@RequestMapping("/Feature")
public class FeatureController extends BaseController<Feature> {
	
	@RequestMapping("/Index")
	public  ModelAndView Index() {
		
		
		return View("/Feature/Tree");
	}
	
	@RequestMapping("/Tree")
	public  ModelAndView Tree() {

		return View("/Feature/Tree");
	}

}
