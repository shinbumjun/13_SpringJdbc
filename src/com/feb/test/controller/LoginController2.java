//package com.feb.test.controller;
//
//import java.net.URLEncoder;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class LoginController2 {
//
//	// 5. �α׾ƿ�
//	@GetMapping("/logout")
//	public String login(HttpSession session) {
//		// �α׾ƿ� ��ư�� ������ ������ ����
//		session.invalidate();
//		// Ȩ���� �̵�
//		return "redirect:/index";
//	}
//	
//	@GetMapping("/loginPage")
//    public String loginForm() {
//        return "login"; // �� �̸��� ��ȯ�մϴ�.
//    }
//	
//	@PostMapping("/login.do")
//	public String login(String memberId, String passwd, String logCheck, String toURL, HttpServletRequest request,HttpServletResponse response) throws Exception {
//		// 1. Login�� �Է��� ���� �� ���Դ��� Ȯ��
//		System.out.println("memberId : " + memberId);
//		System.out.println("passwd : " + passwd);
//		System.out.println("logCheck : " + logCheck); // üũ�ϸ� on, üũ���ϸ� null
//		
//		// 2. id�� pwd ��ġ���� ���� ���
//		if(!(loginCheck(memberId, passwd))) {
//			String msg = URLEncoder.encode("id �Ǵ� pwd�� ��ġ���� �ʽ��ϴ�.", "utf-8");
//			return "redirect:/loginPage"; // redirect
//		}
//		
//		// 3. memberId, passwd�� ��ġ�ϸ� ���� ��ü�� ������
//		HttpSession session = request.getSession();
//		session.setAttribute("memberId", memberId); // �Ű������� ���� memberId���� "memberId"Ű ������ ����
//		System.out.println("session.getMemberId() : " + session.getAttribute("memberId")); // "id"Ű���� ���� �Ǿ����� ����
//		
//		// 4. ��Ű �����ϱ�
//		// if(logCheck.equals("on")) { // üũ�ڽ� Ŭ�� o, ***** logCheck=null�� ���  NullPointerException 
//		if("on".equals(logCheck)) {
//			System.out.print("��Ű ����");
//			// ��Ű ����
//			Cookie cookie = new Cookie("memberId", memberId);
//			// ���信 ����
//			response.addCookie(cookie);
//		}else { // üũ�ڽ� Ŭ�� x
//			// ��Ű ����
//			Cookie cookie = new Cookie("memberId",memberId);
//			cookie.setMaxAge(0);
//			// ���信 ����
//			response.addCookie(cookie);
//		}
//
//		return "redirect:/index"; // �α��� ������ ��� Ȩ�������� �̵�
//	}
//
//	private boolean loginCheck(String memberId, String passwd) {
////		if(id=="s123s123s" && pwd == "12345") { ... // *****== �̰����� �������� if(ture)���� ������ �ȵǾ .equals()�� ����
//		boolean ok = ("s123s123s".equals(memberId) && "123".equals(passwd));
//
//		System.out.println("�α��� Ȯ�� : " + ok);
//		return ok;
//	}
//	
//}
