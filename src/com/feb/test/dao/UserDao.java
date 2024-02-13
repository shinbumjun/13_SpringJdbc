package com.feb.test.dao;

import java.util.HashMap;

import org.springframework.stereotype.Controller;

public interface UserDao {
	
	public int join(HashMap<String, Object> params);

	public boolean isMemberIdDuplicated(String memberId2); // 중복 아이디 확인

}
