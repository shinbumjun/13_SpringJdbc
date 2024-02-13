package com.feb.test.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.feb.test.dao.UserDao;

// 회원가입하는 로직
public class UserService {

	private static final int MAX_MEMBERID_LENGTH = 19; // 영어 최대 19 글자
	private static final int MIN_MEMBERID_LENGTH = 2; // 한글 최소 2글자 
	
	private static final int MAX_PASSWD_LENGTH = 32; // 비밀번호 최대 32글
	private static final int MIN_PASSWD_LENGTH = 8; // 비밀번호 최소 8글자
	
	@Autowired
	UserDao userdao;
	
	public UserService() {} // 기본 생성자 반드시 작성하기!!
	
	public UserService(UserDao userDao) {
		System.out.println("userDao : " + userDao);
		this.userdao = userDao;
	}
	
	
	// 2. Controller에 있는 코드 자동 생성 : UserService.join(params); 
	public int join(HashMap<String, Object> params, HttpServletRequest request) {
		System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuu: " + params); // NullPointerException이라는 에러가 나와서 확인 / 확인
		
		try {
			// ***** 1.유효성 검사: 아이디와 비밀번호 최소, 최대 길이 확인
			String memberId2 = (String) params.get("memberId");
			String passwd2 = (String) params.get("passwd");
			
		    // 아이디의 길이 유효성 검사
		    if (memberId2 == null || memberId2.length() < MIN_MEMBERID_LENGTH || memberId2.length() > MAX_MEMBERID_LENGTH) {
		        throw new IllegalArgumentException("아이디는 최소 " + MIN_MEMBERID_LENGTH + "글자 이상, 최대 " + MAX_MEMBERID_LENGTH + "글자 이하여야 합니다.");
		    }
	
		    // 비밀번호의 길이 유효성 검사
		    if (passwd2 == null || passwd2.length() < MIN_PASSWD_LENGTH || passwd2.length() > MAX_PASSWD_LENGTH) {
		        throw new IllegalArgumentException("비밀번호는 최소 " + MIN_PASSWD_LENGTH + "글자 이상, 최대 " + MAX_PASSWD_LENGTH + "글자 이하여야 합니다.");
		    }
	
		    // 비밀번호의 안전성 유효성 검사 (예: 대소문자 및 특수문자 포함 여부 등)
		    if (!isPasswordSecure(passwd2)) {
		        throw new IllegalArgumentException("비밀번호는 대소문자, 숫자, 특수문자를 모두 포함해야 합니다.");
		    }
			
		    // *****아이디 중복 체크
		    if (userdao.isMemberIdDuplicated(memberId2)) {
		        throw new IllegalArgumentException("이미 사용 중인 아이디입니다."); // 중복값이면 true 반환해서 예외
		    }
		    
		    return userdao.join(params); // NullPointerException 발생 !!!
		    
		} catch (IllegalArgumentException e) {  
			// request 객체는 Spring MVC의 컨트롤러나 서블릿에서만 직접 사용할 수 있다 (컨트롤러에서 받아야 한다)
			request.setAttribute("errorMessage", e.getMessage()); // 에러메시지 받기 위해서 
			return -1; // 예외면 -1를 반환, 이값을 이용하자
		}
	}
	
	
	
	// ***** 2.비밀번호의 안전성을 검사하는 메서드
	private boolean isPasswordSecure(String password2) {
	    // 대소문자, 숫자, 특수문자를 포함하는지 검사하는 정규표현식
	    String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,32}$";
	    return password2.matches(regex); // 주어진 비밀번호가 정규표현식에 맞으면 true, 그렇지 않으면 false를 반환
	}
}



