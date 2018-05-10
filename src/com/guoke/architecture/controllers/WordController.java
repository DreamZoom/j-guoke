package com.guoke.architecture.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.guoke.architecture.models.Word;
import com.guoke.controller.BaseController;


@Controller
@RequestMapping("/Word")
public class WordController extends BaseController<Word> {
	
	@RequestMapping("/Index")
	public  ModelAndView Index() {
		
		
        return View("/Word/Index");
	}

}
