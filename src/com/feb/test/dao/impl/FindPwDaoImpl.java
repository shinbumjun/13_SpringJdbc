package com.feb.test.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.feb.test.dao.FindPwDao;
import com.feb.test.entity.Member;
import com.feb.test.entity.MemberRowMapper;

public class FindPwDaoImpl extends JdbcDaoSupport implements FindPwDao{
	
	@Override
	public int findMember(String memberId, String email) {
		// select * 쓰면 욕 먹을 확률 업업~~~ 메모리를 많이 쓰기 땜시.
		// 맞다면 1명만 나오면 되기 때문에 메모리를 아끼기 위해서 숫자 뜨는 게 더 좋음.
		String sql = "select count(1) from lecture.member where member_id = ? and email = ?";
		int result = getJdbcTemplate().queryForObject(sql, new Object[] {memberId , email}, Integer.class);
		
		return result;
	}
	
	// to-do 임시비밀번호로 업데이트
	// 사용자 테이블에 비밀번호 컬럼 수정하는 메서드 작성
	// interface > impl > service
	// update lecture.member set passwd = ?
	// where member_id = ? and email = ? 
	@Override
	public int updatePasswd(String passwd, String memberId, String emai) {

		String sql = "update lecture.member set passwd = ? where member_id = ? and email = ?";
		
		int result = getJdbcTemplate().update(sql, new Object[] {passwd, memberId, emai});
		
		return result;
	}
	
	
//	String sql = "INSERT INTO lecture.member "
//			+ "(member_id, passwd, email, join_dtm) "
//			+ "VALUES('" + params.get("memberId") // 아이디
//			+ "', '" + encodeTxt // 해시로 변환된 비밀번호
//			+ "', '" + params.get("email") // 이메일
//			+ "', to_char(now(), 'YYYYMMDDHH24MISS'))"; // 등록 날짜
//	
//	System.out.println("UUUUUUUUUUUUUU22222222222222 : " + sql); // NullPointerException이라는 에러가 나와서 확인 / *******에러
//	
//	return getJdbcTemplate().update(sql); // 3. update는 int반환
//	
//}
}
