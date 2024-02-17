package com.feb.test.dao;

public interface FindPwDao {
	
	public int findMember(String memberId, String email);
	
	public int updatePasswd(String passwd, String memberId, String emai);
	
}
