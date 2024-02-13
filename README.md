https://www.notion.so/3-6b58e0f107e6462c8b4ca99ca63db379?pvs=4

1. Eclipse에서 프로젝트 생성 및 기본 설정
   Dynamic Web Project : 13_SpringJdbc → web.xml(DispatcherServlet) →
   pom.xml(Maven 프로젝트) → spring-servlet.xml(MVC관련) → logback.xml →
   패키지, views 생성→ IndexController.java, index.jsp → 브라우저와 Console 확인

2. 로그인 페이지 만들기
   쿠키와 세션, redirect와 forward 사용하기

3. DB이용해서 로그인
   String memberId, String passwd, String logCheck, String toURL 매개변수
   → @RequestParam HashMap<String, String> params 매개변수

4. 게시판 로그인 했을 경우만 입장 (쿼리 문자열 방식, ModelAndView 이용하는 방식)
   로그인o → 게시판o / 로그인x → 게시판x → 로그인 폼o → 게시판

   ********** 시간 오래 잡아 먹음 **********
   4-1) 쿼리 문자열 방식
   서버에서 쿼리 문자열로 보내고
   return "redirect:/loginPage?toURL="+request.getRequestURL();
   >>>>>
   브라우저에서는 ${param.toURL} 출력 후 서버에서 다시 받는 방식이 있고
   http://localhost:8088/spring/loginPage?toURL=http://localhost:8088/spring/list
   
   4-2) ModelAndView 이용하는 방식
   서버에서 ModelAndView에 담아서 보내고
   mv.addObject("toURL", request.getRequestURL());
   >>>>>
   브라우저에서는 ${toURL} 출력 후 서버에서 다시 받는 방식도 있다

   4-3) 무엇보다 form안에 input태그 쓰자 ^^
   ********** 시간 오래 잡아 먹음 **********
   
5. 회원가입 구현하기
핵심 id중복을 어떻게 막기 / 회원가입 유효성 검사 완료!!!
문제점 : UserService, UserDao와 UserDaoImpl 아예 안보고 만들지 못했음

 ********** 머리 살짝 아픔 **********
5-1) parameterType 와 resultType 생각

5-2) 중요한 건 만드는 순서!!

Controller에서 메서드 호출 → UserService → UserDaoImpl → UserDao 이렇게 만들어 보았음 맞는지는 모름
5-3) UserDaoImpl 에서 sql작성하기 전에 DB에서 확인을 해보기

5-4) applicationContext-beans.xml, UserService.java 가장 중요한 것!!!
회원가입 : 이게 메인이라고 생각함 → 설명할 줄 알아야 함

5-5) NullPointerException이라는 에러가 나옴 
System.out.println(); 다 찍어봄 

5-6) 중복 id로 회원가입을 하였는데 가입이 되었다 이것을 막아보자!!! 완료

5-7) id와 pw길이를 제한해 보자!!! 완료 
id가 11111111111111 로그인 하려고 했는데 로그인이 실패되었다 
로그인 시 id값을 비교하게 로직을 구성하였기 때문에 오류가 난 것으로 보인다 
아예 중복된 아이디를 가입할 수 없도록 구현을 해봐야겠다!!!
******************** 커밋 에러 ********************

6. CSV 파일 읽어서 DB에 저장 (과제)
비밀번호 재확인은 추후에 구현 예정 



7. DB이용해서 게시판 CRUD 구현



   
