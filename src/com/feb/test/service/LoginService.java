package com.feb.test.service;

import java.util.HashMap;

import org.springframework.util.StringUtils;

import com.feb.test.dao.LoginDao;
import com.feb.test.entity.Member;
import com.feb.test.util.Sha512Encoder;

public class LoginService {
	
	LoginDao loginDao;
	
	public LoginService() {}
	public LoginService(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	
	public boolean login(HashMap<String,String> params) {
		String memberId = params.get("memberId"); // 1-1) 사용자가 입력한 아이디
		Member member = loginDao.login(memberId); // 2-1) 데이터베이스에서 해당 사용자 정보를 조회
		
	    // 사용자 정보가 조회되지 않은 경우 null일 경우
	    if (member == null) {
	        return false; // 로그인 실패
	    }
		
		String memberPw = member.getPasswd(); // 2-2) 조회된 사용자 정보에서 비밀번호
		
		Sha512Encoder encoder = Sha512Encoder.getInstance(); // 비밀번호를 암호화하기 위해 Sha512Encoder를 인스턴스화
		String passwd = params.get("passwd"); // 1-2) 사용자가 입력한 비밀번호
		
		String encodeTxt = encoder.getSecurePassword(passwd); // 1-3) 사용자가 입력한 비밀번호을 암호화			
		
		System.out.println(member);
		return StringUtils.pathEquals(memberPw, encodeTxt); // 3) 데이터베이스에서 조회된 비밀번호와 사용자가 입력한 비밀번호를 암호화한 값 비교
	}
}