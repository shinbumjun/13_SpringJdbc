package com.feb.test.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.feb.test.service.FileUploadService;

@Controller
public class csvmemberController {
	
	@GetMapping("/csvmember")
	public ModelAndView csvmember() {
		System.out.println("ccccccccccccccccccccccccccccccccc");
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("csvmember");
		return mv;
	}
	
//	@PostMapping("/csvuploadphp") // csv파일을 업로드하면
//	public String csvuploadphp(@RequestParam HashMap<String, Object> csv) {
//		System.out.println("ssssssssssssssssssssssssssssssssssss");
//		System.out.println("csv : " + csv); // 값을 받아왔는지 출력해보기, 못받아 왔음 {}
//		return "1";
//	} // 이렇게 하면 안받아짐...? 검색을 해보았다
	
	@PostMapping("/csvuploadphp") // csv파일을 업로드하면, 이름이 "csv"인 파라미터를 받아오는 것
	public ModelAndView csvuploadphp(@RequestParam("csv") MultipartFile csvFile, HttpServletRequest request) throws IOException {
		
	   
	   System.out.println("ssssssssssssssssssssssssssssssssssss");
		
	   ModelAndView mv = new ModelAndView();
	   
       if (!isCSVFile(csvFile)) {
            System.out.println("CSV파일 형식으로 업로드하지 않았습니다");
            mv.setViewName("error");
            return mv; // 에러 페이지로 이동하거나 다른 처리를 수행할 수 있습니다.
        }
	       
	       
		// 업로드된 파일의 원본 파일 이름을 반환
		System.out.println("Uploaded file: " + csvFile.getOriginalFilename()); // member.csv
		// 업로드된 파일의 크기를 반환
		System.out.println("File size: " + csvFile.getSize()); // 231

		// 파일의 내용을 문자열로 읽어오기
	    String content = new String(csvFile.getBytes(), StandardCharsets.UTF_8);
	    System.out.println("File content: " + content);
		// File content: id,name,email,grade,goal	    
		// 1,????,agnes0417@knou.ac.kr,S,S	    
		// 2,?????,,A,S	    
		// 3,??????,whdudgms123@naver.com,B,S    
		// 4,?????,sinbumjun123@naver.com,B,S	    
		// 5,??????,jiweon.j.kim@gmail.com,C,S	    
		// 6,???,,C,S	    
		// 7,??????,jbw1838@gmail.com,C,S	    
	    
	    // csv파일을 DB에 insert할 것이기 때문에 반환 타입은 int형이다 
	    int result = FileUploadService.csvinsert(csvFile, request);
	    
	    mv.setViewName("index");
	    mv.addObject("resultMsg", "업로드 성공");
	    
        return mv;
  }
	
//   // csv파일을 선택하지 않았을때 이건 Gpt에게 코드 자체를 물어보았음
//   private boolean isCSVFile(MultipartFile file) throws IOException {
//        String contentType = file.getContentType();
//        if (contentType != null && contentType.equals("text/csv")) { // CSV 파일의 MIME 타입을 확인합니다.
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//                String line = reader.readLine();
//                if (line != null && line.contains(",")) { // 쉼표(,)가 포함되어 있으면 CSV 파일로 판단합니다.
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
	
	// BufferedReader 파일 깨지지 않게 하기
//	private boolean isCSVFile(MultipartFile file) throws IOException {
//	    String contentType = file.getContentType();
//	    if (contentType != null && contentType.equals("text/csv")) { // CSV 파일의 MIME 타입을 확인합니다.
//	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
//	            String line = reader.readLine();
//	            if (line != null && line.contains(",")) { // 쉼표(,)가 포함되어 있으면 CSV 파일로 판단합니다.
//	                return true;
//	            }
//	        }
//	    }
//	    return false;
//	}

	// Apache Commons IO 라이브러리 -> 이것도 안됨 그냥 파일 자체의 인코딩이 올바르지 않은 것  ("EUC-KR" 이것 해도 안됨)
	private boolean isCSVFile(MultipartFile file) throws IOException {
	    String contentType = file.getContentType();
	    if (contentType != null && contentType.equals("text/csv")) { // CSV 파일의 MIME 타입을 확인합니다.
	        try (InputStreamReader inputStreamReader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
	             BufferedReader reader = new BufferedReader(inputStreamReader)) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                System.out.println(line); // 파일의 각 라인을 출력합니다.
	                if (line.contains(",")) { // 쉼표(,)가 포함되어 있으면 CSV 파일로 판단합니다.
	                    return true;
	                }
	            }
	        }
	    }
	    return false;
	}
}





