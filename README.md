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

6. DB이용해서 게시판 CRUD 구현



   
