package com.feb.test.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.SingleColumnRowMapper;

//MemberRowMapper : 행이랑 Mapper 연결
public class MemberRowMapper extends SingleColumnRowMapper<Member> {
	
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member(); // 한줄 씩 
		member.setMemberIdx(rs.getInt("member_idx"));
		member.setMemberId(rs.getString("member_id"));
		member.setPasswd(rs.getString("passwd"));
		member.setEmail(rs.getString("email"));
		member.setJoinDtm(rs.getString("join_dtm"));
		return member;
	}
}