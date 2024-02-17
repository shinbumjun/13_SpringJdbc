package com.feb.test.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feb.test.service.FindPwService;

@Controller
public class FindPwController {

		@Autowired
		private FindPwService findPwService;	
	
		// ��й�ȣ ã�� ����
		@GetMapping("/findPw.do")
		public ModelAndView findPw(@RequestParam HashMap<String, String> params) {
			ModelAndView mv = new ModelAndView();
			boolean result = findPwService.findMember(params);
			
			mv.setViewName("common/broker");
			mv.addObject("resultCode", result);
			mv.addObject("nextUri", "/loginPage.do");
			
			// http://localhost:8088/spring/findPw.do?memberId=jyeory&email=jyeory@gmail.com
			// �̸��Ϸ� �ӽ� ��й�ȣ
			if(result) {
				mv.addObject("resultMsg", "이메일 임시 비밀번호 발송");
			}else{
				mv.addObject("resultMsg", "사용자가 존재하지 않습니다");
			}
			
			return mv;
		}
	
}
