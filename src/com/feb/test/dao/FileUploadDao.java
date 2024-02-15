package com.feb.test.dao;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadDao {

	// 파일 업로드
	public int csvinsert(MultipartFile csvFile);
	

}
