<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!--  -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- 
jsp 파일에 alert 할 때 모든 파일에 alert 코드를 넣는 건 중복이 너무 많음.
이 스크립트 코드를 한 곳에 두고 쓰기 위해서 
-->
<script type="text/javascript">
	//컨트롤러에서 보낸 메세지가 있을 경우
	window.onload = function(e){ 
		var resultMsg = '${resultMsg}';
		var resultCode = '${resultCode}';
		if(resultMsg.length > 0){
			alert(resultMsg);
		}
		// 성공한다면 ~ 
		if(resultCode == 'ok'){
			window.location.href = '<c:url value= "${nextUri}}"/>?offset=1&limit=20';
		}
	}
</script>
<body>

</body>
</html>