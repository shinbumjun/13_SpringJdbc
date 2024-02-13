package com.feb.test.dao.impl;

import java.util.HashMap;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.feb.test.dao.UserDao;
import com.feb.test.util.Sha512Encoder;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao{

	@Override // memberId, email, passwd 브라우저 작성한 이 값들을 params로 가져옴 
	public int join(HashMap<String, Object> params) {
		
		System.out.println("UUUUUUUUUUUUUU111111111111111 : " + params); // NullPointerException이라는 에러가 나와서 확인 / 확인
		System.out.println("memberId : " + params.get("memberId"));
		System.out.println("email : " + params.get("email"));
		
		// 이런 식으로 만들어야함
		// INSERT INTO lecture."member"
		// (member_id, passwd, email, join_dtm)
		// VALUES('', '', '', '');
		
		// 1. Sha512Encoder 객체를 생성합니다. 이 객체는 비밀번호를 SHA-512 해시로 인코딩하는 데 사용
		Sha512Encoder encoder = Sha512Encoder.getInstance();
		String passwd = (String)params.get("passwd"); // String 형변환
		// 2. 가져온 비밀번호를 Sha512Encoder를 사용하여 안전한 해시로 변환
		String encodeTxt = encoder.getSecurePassword(passwd);
		
		String sql = "INSERT INTO lecture.member "
				+ "(member_id, passwd, email, join_dtm) "
				+ "VALUES('" + params.get("memberId") // 아이디
				+ "', '" + encodeTxt // 해시로 변환된 비밀번호
				+ "', '" + params.get("email") // 이메일
				+ "', to_char(now(), 'YYYYMMDDHH24MISS'))"; // 등록 날짜
		
		System.out.println("UUUUUUUUUUUUUU22222222222222 : " + sql); // NullPointerException이라는 에러가 나와서 확인 / *******에러
		
		return getJdbcTemplate().update(sql); // 3. update는 int반환
		
	}

	
	
	// 아이디 중복 체크 메서드
	public boolean isMemberIdDuplicated(String memberId) {
		System.out.println(memberId);
		
	    String sql = "SELECT COUNT(*) FROM lecture.member WHERE member_id = ?";
	    System.out.println(sql);
	    
	    int count = getJdbcTemplate().queryForObject(sql, Integer.class, memberId);
	    System.out.println(count);
	    
	    return count > 0; // 반환된 수가 0보다 크면 중복된 아이디가 있다는 의미, true를 반환
	}
	
	

}



