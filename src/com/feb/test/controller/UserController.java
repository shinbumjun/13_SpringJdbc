package com.feb.test.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feb.test.service.UserService;

@Controller 
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/join") // 회원가입
	public ModelAndView UserContrroller(@RequestParam HashMap<String, Object> params, HttpServletRequest request) { // memberId, email, passwd
		
		ModelAndView mv = new ModelAndView();
		
		System.out.println(params); // {memberId=shinbumjun, email=sinbumjun123@naver.com, passwd=1512}
		System.out.println(params.get("memberId")); // shinbumjun
//		System.out.println("params.get(0) : " + params.get(0)); // null
		
		int result = userService.join(params, request); // *****1 또는 0 반환 - 에러메시지 받을려고, UserService에 request를 전달 
		mv.setViewName("login");
//		mv.addObject("resultCode", result);
		
		   if (result == 1) {
		        mv.addObject("resultMsg", "회원가입 성공");
		    } else if (result == 0) {
		        mv.addObject("resultMsg", "회원가입 실패");
		    } else {
		        mv.addObject("resultMsg", request.getAttribute("errorMessage")); // result는 에러 메시지
		        // -1이 반환될 때의 추가적인 처리 가능
		    }
		
		return mv; // 회원가입 성공하면 로그인 페이지
	}
	
}
