package com.feb.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListController {
	
	@GetMapping("/list")
	public ModelAndView ListController(HttpServletRequest request) {
		
		// ********************* 1.이 방식으로 하면 브라우저에서${param.toURL} 이렇게 받고 
		// http://localhost:8088/spring/loginPage?toURL=http://localhost:8088/spring/list
		// 이렇게하면 URL이 좀 더러워져서 아래 방식으로 함
//		if(!(loginCheck(request))) {
//			return "redirect:/loginPage?toURL="+request.getRequestURL(); // 로그인 안했을 시에는 toURL를 달고 로그인 폼으로
//		}
//		return "list"; // 로그인 했을 때는 바로 게시판으로 
		
		
		
		
		
		// ********************* 2. 이 방식으로 하면 브라우저에서 ${toURL} 이렇게 받고 하면 됨
		// http://localhost:8088/spring/list 
		// 로그인 폼 화면이고 로그인이 완료되면  http://localhost:8088/spring/list 브라우저로 가야한다
		System.out.println("request.getRequestURL() : " + request.getRequestURL());
		
		ModelAndView mv = new ModelAndView();
		
		if(!(loginCheck(request))) { // 세션 값이 null이면 true
			
			// "toURL" 키를 사용하여 현재 요청의 URL을 ModelAndView 객체에 추가합니다.
			mv.addObject("toURL", request.getRequestURL());
			System.out.println("request.getRequestURL().toString() : " + request.getRequestURL().toString());
			
			mv.setViewName("login"); // 로그인이 안되어 있다면 로그인 폼으로 이동
			return mv; 
		}
		
		mv.setViewName("list");
		return mv;
		
		
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻어서
		HttpSession session = request.getSession();
		// 2. 세션에 id가 있는지 확인, 있으면 true를 반환
		return session.getAttribute("memberId")!=null; // 세션 값이 null이면 false
	}
}



