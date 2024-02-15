package com.feb.test.dao.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.web.multipart.MultipartFile;

import com.feb.test.dao.FileUploadDao;

public class FileUploadDaoImpl extends JdbcDaoSupport implements FileUploadDao{

	@Override
	public int csvinsert(MultipartFile csvFile) {
		
		// JdbcDaoSupport 클래스를 상속받았기 때문에 getJdbcTemplate() 메서드를 사용하여 JdbcTemplate 객체를 가져옵니다.
        JdbcTemplate jdbcTemplate = getJdbcTemplate();
		
		// 전달 완료 : MultipartFile[field="csv", filename=member.csv, contentType=text/csv, size=231]
		System.out.println("csvFile333333333333333333333333333333 : " + csvFile);
		System.out.println("csvFile : " + csvFile);
		
		
		
		
		
//		일반적으로는 파일의 내용을 DB에 저장하는 것이 아니라 
//		파일을 서버의 파일 시스템에 저장하고 파일의 경로를 DB에 저장하는 것이 일반적이라고 함!!!
		
		// ******************하지만 DB에 직접 넣어보고 싶어서 gpt한테 물어봄 
        try {
            // 파일 내용 읽기
            InputStream inputStream = csvFile.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            
            String line;
            while ((line = reader.readLine()) != null) {
                // CSV 파일의 각 라인을 적절히 파싱하여 DB에 삽입하는 로직을 작성
                // 예를 들어, 쉼표(,)로 구분된 값을 추출하여 SQL 쿼리를 작성하고 실행할 수 있습니다.
                String[] values = line.split(","); // 쉼표로 구분하여 값들을 추출
                
                // 적절한 SQL 쿼리 작성 및 실행
                String sql = "INSERT INTO lecture.csvmember (name, email, grade, goal) VALUES (?, ?, ?, ?)";
                jdbcTemplate.update(sql, values[0], values[1], values[2], values[3]); // 값들을 ?에 바인딩하여 SQL 실행
            }

            reader.close();

            return 1; // 성공적으로 파일 내용을 DB에 삽입한 경우
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // 파일 읽기 또는 DB에 삽입하는 중에 오류가 발생한 경우
        }
    }
}