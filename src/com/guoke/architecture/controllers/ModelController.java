package com.guoke.architecture.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.guoke.architecture.models.Model;
import com.guoke.architecture.services.imps.ModelService;
import com.guoke.controller.BaseController;



@Controller
@RequestMapping("/Model")
public class ModelController extends BaseController<Model> {
	
	@RequestMapping("/Index")
	public  ModelAndView Index() {

		return View("/Model/Index");
	}
	
	@RequestMapping("/EditModel")
	public  ModelAndView EditModel(int Id) {
		ModelService modelService=(ModelService) Service();
		Model model =modelService.findById(Id);
		return View("/Model/EditModel",model);
	}
	
	@RequestMapping(value = "/SaveModel", produces = "application/json;charset=UTF-8")
	public @ResponseBody String SaveModel(int id,String xml) {
		try {
			
			ModelService modelService=(ModelService) Service();
			Model model =modelService.findById(id);
			model.setBody(xml);
			modelService.edit(model);
			
		} catch (Exception e) {
			// TODO: handle exception
			return Error("删除失败"+e.getMessage());
		}	
		return Success("删除成功");
	}

}
