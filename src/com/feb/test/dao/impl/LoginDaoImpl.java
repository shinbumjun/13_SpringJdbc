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
		        return null; // ���� ���� ��� null ��ȯ
		    } else {
		        return members.get(0); // ���� �ִ� ��� ù ��° ��� ��ȯ
		    }
		}
		
		// *****JdbcTemplate�� queryForObject �޼���� ����� ���� �� EmptyResultDataAccessException�� �����Ƿ�, 
		// �̸� ó���ϱ� ���� ���� ó���� �߰��ϰų�, query �޼��带 ����Ͽ� ����� ���� ��쿡 ���� ó���� ������ �� �ֽ��ϴ�
		
//		public Member login(String memberId) {
//			String sql = "select * from lecture.member where member_id = ?";
//			Member member = getJdbcTemplate().queryForObject(sql,  new Object[] {memberId}, new MemberRowMapper());
//			System.out.println("member111111111111 : " + member);
//			
//			return member;
//		}

	}
