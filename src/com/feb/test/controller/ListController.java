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
		
		// ********************* 1.�� ������� �ϸ� ����������${param.toURL} �̷��� �ް� 
		// http://localhost:8088/spring/loginPage?toURL=http://localhost:8088/spring/list
		// �̷����ϸ� URL�� �� ���������� �Ʒ� ������� ��
//		if(!(loginCheck(request))) {
//			return "redirect:/loginPage?toURL="+request.getRequestURL(); // �α��� ������ �ÿ��� toURL�� �ް� �α��� ������
//		}
//		return "list"; // �α��� ���� ���� �ٷ� �Խ������� 
		
		
		
		
		
		// ********************* 2. �� ������� �ϸ� ���������� ${toURL} �̷��� �ް� �ϸ� ��
		// http://localhost:8088/spring/list 
		// �α��� �� ȭ���̰� �α����� �Ϸ�Ǹ�  http://localhost:8088/spring/list �������� �����Ѵ�
		System.out.println("request.getRequestURL() : " + request.getRequestURL());
		
		ModelAndView mv = new ModelAndView();
		
		if(!(loginCheck(request))) { // ���� ���� null�̸� true
			
			// "toURL" Ű�� ����Ͽ� ���� ��û�� URL�� ModelAndView ��ü�� �߰��մϴ�.
			mv.addObject("toURL", request.getRequestURL());
			System.out.println("request.getRequestURL().toString() : " + request.getRequestURL().toString());
			
			mv.setViewName("login"); // �α����� �ȵǾ� �ִٸ� �α��� ������ �̵�
			return mv; 
		}
		
		mv.setViewName("list");
		return mv;
		
		
	}
	
	private boolean loginCheck(HttpServletRequest request) {
		// 1. ������ ��
		HttpSession session = request.getSession();
		// 2. ���ǿ� id�� �ִ��� Ȯ��, ������ true�� ��ȯ
		return session.getAttribute("memberId")!=null; // ���� ���� null�̸� false
	}
}



