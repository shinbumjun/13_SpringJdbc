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
//	// 5. 로그아웃
//	@GetMapping("/logout")
//	public String login(HttpSession session) {
//		// 로그아웃 버튼을 누르면 세션을 종료
//		session.invalidate();
//		// 홈으로 이동
//		return "redirect:/index";
//	}
//	
//	@GetMapping("/loginPage")
//    public String loginForm() {
//        return "login"; // 뷰 이름을 반환합니다.
//    }
//	
//	@PostMapping("/login.do")
//	public String login(String memberId, String passwd, String logCheck, String toURL, HttpServletRequest request,HttpServletResponse response) throws Exception {
//		// 1. Login에 입력한 값이 잘 들어왔는지 확인
//		System.out.println("memberId : " + memberId);
//		System.out.println("passwd : " + passwd);
//		System.out.println("logCheck : " + logCheck); // 체크하면 on, 체크안하면 null
//		
//		// 2. id와 pwd 일치하지 않을 경우
//		if(!(loginCheck(memberId, passwd))) {
//			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
//			return "redirect:/loginPage"; // redirect
//		}
//		
//		// 3. memberId, passwd가 일치하면 세션 객체를 얻어오기
//		HttpSession session = request.getSession();
//		session.setAttribute("memberId", memberId); // 매개변수로 받은 memberId값을 "memberId"키 값으로 저장
//		System.out.println("session.getMemberId() : " + session.getAttribute("memberId")); // "id"키값이 저장 되었는지 찍어보기
//		
//		// 4. 쿠키 생성하기
//		// if(logCheck.equals("on")) { // 체크박스 클랙 o, ***** logCheck=null일 경우  NullPointerException 
//		if("on".equals(logCheck)) {
//			System.out.print("쿠키 저장");
//			// 쿠키 생성
//			Cookie cookie = new Cookie("memberId", memberId);
//			// 응답에 저장
//			response.addCookie(cookie);
//		}else { // 체크박스 클릭 x
//			// 쿠키 삭제
//			Cookie cookie = new Cookie("memberId",memberId);
//			cookie.setMaxAge(0);
//			// 응답에 저장
//			response.addCookie(cookie);
//		}
//
//		return "redirect:/index"; // 로그인 성공할 경우 홈페이지로 이동
//	}
//
//	private boolean loginCheck(String memberId, String passwd) {
////		if(id=="s123s123s" && pwd == "12345") { ... // *****== 이것으로 비교했을때 if(ture)문이 실행이 안되어서 .equals()로 수정
//		boolean ok = ("s123s123s".equals(memberId) && "123".equals(passwd));
//
//		System.out.println("로그인 확인 : " + ok);
//		return ok;
//	}
//	
//}
