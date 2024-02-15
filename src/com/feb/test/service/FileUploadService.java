package com.feb.test.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.feb.test.dao.FileUploadDao;

public class FileUploadService {
	
	static FileUploadDao fileUploadDao;
	
	public FileUploadService() {};
	
	public FileUploadService(FileUploadDao fileUploadDao) {
		this.fileUploadDao = fileUploadDao;
	}

	// 파일 업로드
	public static int csvinsert(MultipartFile csvFile, HttpServletRequest request) {
		// 전달 완료 : MultipartFile[field="csv", filename=member.csv, contentType=text/csv, size=231]
		System.out.println("csvFile111111111111111111111111111111111111 : " + csvFile);
		
		String content;
		try {
			content = new String(csvFile.getBytes(), StandardCharsets.UTF_8);
			System.out.println("File content2222222222222222222222222 : " + content);
			
			if (false) { // ***** 여기에 파일 형식 어떤지 넣어야함
		        throw new IOException("csv파일 업로드한 내용이 알맞지 않습니다");
		    }else { // 형식이 맞으면 한줄씩 읽어보기
		    	
		    	// BufferedReader를 사용하여 파일을 한 줄씩 읽음
		        BufferedReader reader = new BufferedReader(new InputStreamReader(csvFile.getInputStream(), StandardCharsets.UTF_8));
		        String line;
		        while ((line = reader.readLine()) != null) {
		            // 각 줄에 대한 처리
		            System.out.println("Line content: " + line);
		            // 예를 들어, 각 줄을 파싱하여 DB에 저장하는 등의 작업 수행ㅊ
		        }
		        
		        // 파일 읽기 완료 후 리소스 반환
		        reader.close();
		        
		    }
			
			// csv파일 형식이 DB와 일치하다면 DB에 저장하러 감
			return fileUploadDao.csvinsert(csvFile); // 생성하고 메서드 호출, 오류가 뜸 at com.feb.test.service.FileUploadService.csvinsert(FileUploadService.java:21)
			// return FileUploadDao.csvinsert(csvFile); // static
			
		} catch (IOException e) {
			
			request.setAttribute("errorMessage", e.getMessage());
			return -1; // 파일을 읽어보고 해당 내용이 들어 있지 않으면 -1 반환 
			
		}
	}
}



