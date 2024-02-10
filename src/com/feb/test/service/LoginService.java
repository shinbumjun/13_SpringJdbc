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
		String memberId = params.get("memberId"); // 1-1) ����ڰ� �Է��� ���̵�
		Member member = loginDao.login(memberId); // 2-1) �����ͺ��̽����� �ش� ����� ������ ��ȸ
		
	    // ����� ������ ��ȸ���� ���� ��� null�� ���
	    if (member == null) {
	        return false; // �α��� ����
	    }
		
		String memberPw = member.getPasswd(); // 2-2) ��ȸ�� ����� �������� ��й�ȣ
		
		Sha512Encoder encoder = Sha512Encoder.getInstance(); // ��й�ȣ�� ��ȣȭ�ϱ� ���� Sha512Encoder�� �ν��Ͻ�ȭ
		String passwd = params.get("passwd"); // 1-2) ����ڰ� �Է��� ��й�ȣ
		
		String encodeTxt = encoder.getSecurePassword(passwd); // 1-3) ����ڰ� �Է��� ��й�ȣ�� ��ȣȭ			
		
		System.out.println(member);
		return StringUtils.pathEquals(memberPw, encodeTxt); // 3) �����ͺ��̽����� ��ȸ�� ��й�ȣ�� ����ڰ� �Է��� ��й�ȣ�� ��ȣȭ�� �� ��
	}
}
