<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- %@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"% -->
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<!-- Coding By CodingNepal - codingnepalweb.com -->
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- ===== Iconscout CSS ===== -->
<link rel="stylesheet"
	href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

<!-- ===== CSS 주의할점 : /resources/css/style.css resource안에 css파일 안에 style.css를 넣는다.  ===== -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

<script type="text/javascript">
	//컨트롤러에서 보낸 메세지가 있을 경우
	 window.onload = function(e){ 
		
			var resultMsg = '${resultMsg}';
		 	var resultCode = '${resultCode}';
			if(resultMsg.length > 0){
		 		alert(resultMsg);
		 		// 메시지를 한 번 보여준 후 resultMsg 값을 비웁니다.
		         resultMsg = '';
			}
		
		// 새로고침 할 때 마다 뜨길래 코드를 아래와 같이 작성했는데 이러면 메시지가 아예 안뜸 
	    /* var resultMsg = '${resultMsg}';
	    var resultCode = '${resultCode}';
	    if(resultMsg.length > 0){
	        // 로컬 스토리지에 메시지를 저장합니다.
	        localStorage.setItem('resultMsg', resultMsg);
	        alert(resultMsg);
	        // 메시지를 한 번 보여준 후 resultMsg 값을 비웁니다.
	        '${resultMsg}' = '';
	    } else {
	        // 이전에 저장된 메시지가 있는지 확인하고 있다면 표시합니다.
	        var storedMsg = localStorage.getItem('resultMsg');
	        if (storedMsg) {
	            alert(storedMsg);
	            // 이전에 저장된 메시지를 지웁니다.
	            localStorage.removeItem('resultMsg');
	        }
	    } */
		
	}
</script>

<title>Login & Registration Form</title>
</head>
<body>

	<div class="container">
		<div class="forms">
			<div class="form login">
			    <span class="title">로그인</span> 
			    <span style="float: right;"><a href="/spring/index" style="text-decoration: none;">홈으로</a></span>
			    <div style="clear: both;"></div> <!-- Clear float -->
			    
				<form action="<c:url value='/login'/>" method="post"
					onsubmit="return formCheck(this);">
					
					<!-- 화면에 표시하지 않고 공간을 차지하지 않도록 하는 방법 -->
					<!-- ListController에서 mv에 담았기 때문에 el로 값을 찍어보기 / 브라우저에서 페이지 소스보기 / *****mv에 담지말고 URL로 보낸값 받아오기***** -->
					<input type="text" name="toURL" value="${toURL}" style="display: none;">
					<!-- 화면에는 표시하지 않되 공간을 차지하는 방법 -->
					<!-- input type="text" style="visibility: hidden;" -->
					<!-- 서버에 값이 안들어 온 이유는 form안에 작성을 안했었기 때문임 ********************************* -->
					
					<div class="input-field">
						<input type="text" name="memberId" value="${cookie.memberId.value}" placeholder="아이디를 입력하세요"
							required> <i class="uil uil-envelope icon"></i>
					</div>
					<div class="input-field">
						<input type="password" name="passwd" class="password"
							placeholder="비밀번호를 입력하세요" required> <i
							class="uil uil-lock icon"></i> <i
							class="uil uil-eye-slash showHidePw"></i>
					</div>

					<div class="checkbox-text">
						<div class="checkbox-content">
						<!-- id="keep", for="keep 선택 영역 -->
							<input type="checkbox" id="keep" name="logCheck" class="input_keep" ${empty cookie.memberId.value ? "":"checked"}> <label
								for="keep" class="keep_text">로그인 상태 유지</label>
						</div>

						<a href="#" class="text">비밀번호 찾기</a>
					</div>

					<div class="input-field button">
						<input type="submit" value="로그인">
					</div>
					<script>
						function formCheck(frm) {
							let msg = '';

							if (frm.id.value.length == 0) {
								setMessage('id를 입력해주세요.', frm.id);
								return false;
							}

							if (frm.pwd.value.length == 0) {
								setMessage('password를 입력해주세요.', frm.pwd);
								return false;
							}

							return true;
						}

						function setMessage(msg, element) {
							document.getElementById("msg").innerHTML = ` ${'${msg}'}`;

							if (element) {
								element.select();
							}
						}
					</script>
				</form>

				<div class="login-signup">
					<span class="text">회원이 아니신가요? <a href="#"
						class="text signup-link">회원가입</a>
					</span>
				</div>
			</div>

			<!-- Registration Form -->
			<div class="form signup">
				<span class="title">회원가입</span>

				<form action="<c:url value='/join'/>" method="post">
					<div class="input-field">
						<input type="text" name="memberId"  placeholder="사용자 아이디" required>
						<i class="uil uil-user"></i>
					</div>
					<div class="input-field">
						<input type="text" name="email" placeholder="사용자 이메일" required>
						<i class="uil uil-envelope icon"></i>
					</div>
					<div class="input-field">
						<input type="password" name="passwd" class="password"
							placeholder="사용자 비밀번호" required> <i
							class="uil uil-lock icon"></i>
					</div>
					<div class="input-field">
						<input type="password" class="password"
							placeholder="사용자 비밀번호 재확인" required> <i
							class="uil uil-lock icon"></i> <i
							class="uil uil-eye-slash showHidePw"></i>
					</div>

					<!--  div class="checkbox-text">
						<div class="checkbox-content">
							<input type="checkbox" id="termCon"> <label for="termCon"
								class="text">I accepted all terms and conditions</label>
						</div>
					</div -->

					<div class="input-field button">
						<input type="submit" value="가입">
					</div>
				</form>

				<div class="login-signup">
					<span class="text">회원이신가요? <a href="#"
						class="text login-link">로그인</a>
					</span>
				</div>
			</div>
		</div>
	</div>
	<!-- 주의할점 : /resources/js/script.js설정시 resources안에 js파일안에 script.js 넣을 것 -->
	<script src="${pageContext.request.contextPath}/resources/js/script.js"></script>

</body>
</html>