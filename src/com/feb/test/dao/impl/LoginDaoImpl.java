package com.feb.test.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.feb.test.dao.LoginDao;
import com.feb.test.entity.Member;
import com.feb.test.entity.MemberRowMapper;

public class LoginDaoImpl extends JdbcDaoSupport implements LoginDao{

		@Override
		public Member login(String memberId) {
		    String sql = "select * from lecture.member where member_id = ?";
		    List<Member> members = getJdbcTemplate().query(sql, new Object[] {memberId}, new MemberRowMapper());
		    
		    if (members.isEmpty()) {
		        return null; // 값이 없는 경우 null 반환
		    } else {
		        return members.get(0); // 값이 있는 경우 첫 번째 요소 반환
		    }
		}
		
		// *****JdbcTemplate의 queryForObject 메서드는 결과가 없을 때 EmptyResultDataAccessException을 던지므로, 
		// 이를 처리하기 위해 예외 처리를 추가하거나, query 메서드를 사용하여 결과가 없는 경우에 대한 처리를 수행할 수 있습니다
		
//		public Member login(String memberId) {
//			String sql = "select * from lecture.member where member_id = ?";
//			Member member = getJdbcTemplate().queryForObject(sql,  new Object[] {memberId}, new MemberRowMapper());
//			System.out.println("member111111111111 : " + member);
//			
//			return member;
//		}

	}
