package com.feb.test.controller;

import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.feb.test.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	// 5. �α׾ƿ�
	@GetMapping("/logout")
	public String login(HttpSession session) {
		// �α׾ƿ� ��ư�� ������ ������ ����
		session.invalidate();
		// Ȩ���� �̵�
		return "redirect:/index";
	}
	
	@GetMapping("/loginPage")
    public String loginForm() {
        return "login"; // �� �̸��� ��ȯ�մϴ�.
    }
	
	@PostMapping("/login.do")
	public ModelAndView login(@RequestParam HashMap<String, String> params, 
						HttpServletRequest request,HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		System.out.println(params);

    	boolean result = loginService.login(params);
    	if(result) {
    		// �α��� ���� �� ����� ������ ���ǿ� ����
            HttpSession session = request.getSession();
            session.setAttribute("memberId", params.get("memberId")); // ���÷� memberId�� ���ǿ� ����
            
            
            // �α��� ������ üũ �ڽ� ���ÿ� ���� ��Ű ���� �� ����
            System.out.println("��Ű �� Ȯ�� : " + params.get("logCheck")); // on, null
    		if("on".equals(params.get("logCheck"))) {
				System.out.print("��Ű ����");
				// ��Ű ����
				Cookie cookie = new Cookie("memberId", params.get("memberId"));
				// ���信 ����
				response.addCookie(cookie);
			}else { // üũ�ڽ� Ŭ�� x
				// ��Ű ����
				Cookie cookie = new Cookie("memberId","");
				cookie.setMaxAge(0);
				// ���信 ����
				response.addCookie(cookie);
			}
            
            
			mv.addObject("resultMsg", "�α� ����");
			mv.addObject("resultCode", "loginOk");
			mv.setViewName("index");
    	}else {
    		mv.addObject("resultMsg", "�α��ν���");
			mv.addObject("resultCode", "loginFail");
			mv.setViewName("/login");
    	}
    	return mv;
	}
}











