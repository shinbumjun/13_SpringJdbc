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
	
	// 5. 로그아웃
	@GetMapping("/logout")
	public String login(HttpSession session) {
		// 로그아웃 버튼을 누르면 세션을 종료
		session.invalidate();
		// 홈으로 이동
		return "redirect:/index";
	}
	
	@GetMapping("/loginPage")
    public String loginForm() {
        return "login"; // 뷰 이름을 반환합니다.
    }
	
	@PostMapping("/login")
	public ModelAndView login(@RequestParam HashMap<String, String> params, String toURL, 
						HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		// login.jsp에서 name="toURL" 이걸 가져오는 것이라 mv 사용하면 안되고 params 사용해야함, 다 null값이 찍힘...
		// 결론적으로 서버에서 받아서 브라우저에 출력된 값은 다시 서버로 다시 전송되지 않는다 -> URL에 추가해서 보내야 될듯
		System.out.println("toURL1111111111111111111 : " + params.get("toURL")); // o
		System.out.println("toURL2222222222222222222 : " + mv.getModel().get("toURL")); // null 찍힘
		System.out.println("toURL3333333333333333333 : " + toURL); // o
		// 결론 form안에 input태그를 안했었기 때문에 값이 안들어 왔던거임 *****************************************
		
		
    	boolean result = loginService.login(params);
    	if(result) {
    		// 로그인 성공 시 사용자 정보를 세션에 저장
            HttpSession session = request.getSession();
            session.setAttribute("memberId", params.get("memberId")); // 예시로 memberId를 세션에 저장
            
            // 로그인 성공시 체크 박스 선택에 따라 쿠키 생성 및 삭제
            System.out.println("쿠키 값 확인 : " + params.get("logCheck")); // on, null
    		if("on".equals(params.get("logCheck"))) {
				System.out.print("쿠키 저장");
				// 쿠키 생성
				Cookie cookie = new Cookie("memberId", params.get("memberId"));
				// 응답에 저장
				response.addCookie(cookie);
			}else { // 체크박스 클릭 x
				// 쿠키 삭제
				Cookie cookie = new Cookie("memberId","");
				cookie.setMaxAge(0);
				// 응답에 저장
				response.addCookie(cookie);
			}
            
			mv.addObject("resultMsg", "로그 성공");
			mv.addObject("resultCode", "loginOk");
			
			// mv.setViewName("index");
	        // toURL 파라미터가 존재할 경우 해당 URL로 리다이렉트 (코드는 GPT이용)
	        if (params.containsKey("toURL") && params.get("toURL") != null && !params.get("toURL").isEmpty()) {
	        	System.out.println("toURLooooooooooooooooooooo : " + params.get("toURL"));
	            mv.setViewName("redirect:" + params.get("toURL"));
	        } else {
	        	System.out.println("toURLxxxxxxxxxxxxxxxxxxxxx : " + params.get("toURL"));
	            mv.setViewName("index");
	        }
			
    	}else {
    		mv.addObject("resultMsg", "로그인실패");
			mv.addObject("resultCode", "loginFail");
			mv.setViewName("/login");
    	}
    	
    	// URL이 null값이 아니면 
    	
    	return mv;
	}
}











