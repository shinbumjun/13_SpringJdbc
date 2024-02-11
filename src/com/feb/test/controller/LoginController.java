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
	
	@PostMapping("/login")
	public ModelAndView login(@RequestParam HashMap<String, String> params, String toURL, 
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		// login.jsp���� name="toURL" �̰� �������� ���̶� mv ����ϸ� �ȵǰ� params ����ؾ���, �� null���� ����...
		// ��������� �������� �޾Ƽ� �������� ��µ� ���� �ٽ� ������ �ٽ� ���۵��� �ʴ´� -> URL�� �߰��ؼ� ������ �ɵ�
		System.out.println("toURL1111111111111111111 : " + params.get("toURL")); // o
		System.out.println("toURL2222222222222222222 : " + mv.getModel().get("toURL")); // null ����
		System.out.println("toURL3333333333333333333 : " + toURL); // o
		// ��� form�ȿ� input�±׸� ���߾��� ������ ���� �ȵ�� �Դ����� *****************************************
		
		
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
			
			// mv.setViewName("index");
	        // toURL �Ķ���Ͱ� ������ ��� �ش� URL�� �����̷�Ʈ (�ڵ�� GPT�̿�)
	        if (params.containsKey("toURL") && params.get("toURL") != null && !params.get("toURL").isEmpty()) {
	        	System.out.println("toURLooooooooooooooooooooo : " + params.get("toURL"));
	            mv.setViewName("redirect:" + params.get("toURL"));
	        } else {
	        	System.out.println("toURLxxxxxxxxxxxxxxxxxxxxx : " + params.get("toURL"));
	            mv.setViewName("index");
	        }
			
    	}else {
    		mv.addObject("resultMsg", "�α��ν���");
			mv.addObject("resultCode", "loginFail");
			mv.setViewName("/login");
    	}
    	
    	// URL�� null���� �ƴϸ� 
    	
    	return mv;
	}
}











