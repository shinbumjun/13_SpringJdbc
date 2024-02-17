package com.feb.test.service;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.feb.test.dao.FindPwDao;
import com.feb.test.dto.EmailDto;
import com.feb.test.util.EmailUtil;
import com.feb.test.util.Sha512Encoder;

public class FindPwService {
	
	@Autowired
	private FindPwDao findPwDao;	
	
	// applicationContext-beans.xml
	private EmailUtil emailUtil;
	public void setEmailUtil(EmailUtil emailUtil) {
		this.emailUtil = emailUtil;
	}
	
	public FindPwService() {} // 기본 생성자 반드시 작성하기!!
	public FindPwService(FindPwDao findPwDao) {
		System.out.println("findPwDao : " + findPwDao);
		this.findPwDao = findPwDao;
	}
	
	// 사용자 ID와 이메일로 사용자 찾기
	public boolean findMember(HashMap<String, String> params) {
		int result = findPwDao.findMember(params.get("memberId"), params.get("email"));
		
//		http://localhost:8088/spring/findPw.do?memberId=jyeory&email=jyeory@gmail.com
		System.out.println("result111111111 "+result); // 1
		// 아이디와 이메일이 일치하는 사용자는 무조건 1명이어야 함(중복 없기 때문). 
		if (result == 1) {

			// 1. 랜덤한 문자열 생성
			String uuid = UUID.randomUUID().toString();
			System.out.println("uuid1111111111111111111 : " + uuid);
			
			// 2. 필요없는 문자(-) 제거
			String newPw = uuid.replaceAll("-", "");
			System.out.println("newPw2222222222222222222 : " + newPw); // 사용자에게 보내는 것
			
			// 3. 암호화
			String encodedPw = Sha512Encoder.getInstance().getSecurePassword(newPw);
			System.out.println("encodedPw33333333333333333333 : " + encodedPw);

			// 4. 이메일
			EmailDto emailDto = new EmailDto();
			emailDto.setFrom("sinbumjun123@naver.com");
			emailDto.setReceiver(params.get("email")); // 특정한 사용자 한명
			emailDto.setSubject("임시 비밀번호를 전송해드립니다.");
			emailDto.setText(newPw);
			
			// http://localhost:8088/spring/findPw.do?memberId=shinshinshin&email=sinbumjun123@naver.com -> 1
			// http://localhost:8088/spring/findPw.do?memberId=shinshinshin&email=sinbumjun123@naver.com1 -> 0
			try {
				 // 이메일 발송 실패 시 예외처리
				 emailUtil.sendMail(emailDto);
			}catch(Exception e) {
				e.printStackTrace();
			}

			// to-do 임시비밀번호로 업데이트
			// 사용자 테이블에 비밀번호 컬럼 수정하는 메서드 작성
			// interface > impl > service
			// update lecture.member set passwd = ?
			// where member_id = ? and email = ? 
			int updateResult = findPwDao.updatePasswd(newPw, params.get("memberId"), params.get("email"));
			
			return updateResult==1;
		}
		
		return false; // 1이 아니면 전부 false
		
	}
}











