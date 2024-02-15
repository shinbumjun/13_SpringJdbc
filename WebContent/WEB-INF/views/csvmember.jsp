<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CS 파일 업로드</title>
</head>
<body> 
	<p><a href="/spring/index">홈 화면으로 이동</a></p>
	<!-- 파일 업로드 method="post" enctype="multipart/form-data" 필수 -->
    <form method="post" name="upform" enctype="multipart/form-data" action="csvuploadphp">
        <label for="">CSV 파일을 업로드 해주세요</label> <br>
        <input type="file" name="csv" id="csv">
        <button type="submit" id="btn">확인</button>
    </form>

    <!-- 파일을 선택 안했을시 -->
    <script>
        const btn = document.querySelector("#btn"); // id값
        btn.addEventListener("click", (e) => { // 클릭을 했을때

            // 파일을 선택도 안했는데 확인을 누르는것을 방지하고자
            e.preventDefault(); // 원래는 확인을 누르면 /csvupload 간다, 무력화 시키는것

            const csv = document.querySelector("#csv");
            if(csv.value == ""){ // csv 값이 비어있으면
                alert('파일을 선택해 주세요');
                return false;
            }

            // 파일을 선택했다면 /csvupload 이동함
            document.upform.submit();
        }) 
    </script>
    
</body>
</html>