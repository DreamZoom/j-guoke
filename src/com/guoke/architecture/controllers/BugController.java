package com.guoke.architecture.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.guoke.architecture.models.Bug;
import com.guoke.controller.BaseController;

@Controller
@RequestMapping("/Bug")
public class BugController extends BaseController<Bug>{
	@RequestMapping("/Index")
	public  ModelAndView Index() {
		return View("/Bug/Index");
	}
}
