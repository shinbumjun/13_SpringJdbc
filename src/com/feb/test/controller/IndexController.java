package com.feb.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	public IndexController() {
		System.out.println("111111111111111111111111");
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		System.out.print("222222222222222222222222");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
}
