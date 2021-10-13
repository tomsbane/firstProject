<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String cookieId = "";//★★null로 초기화 하면 안됨

Cookie[] cookies = request.getCookies();
if(cookies != null && cookies.length > 0){
	for(int i=0 ; i<cookies.length ; i++){
		if(cookies[i].getName().equals("u_id")){
			cookieId = cookies[i].getValue();//쿠키값을 얻어와
		}
	}
}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>사용자 로그인 폼 페이지</title>
<style>
#loginformArea{
	margin : auto;
	width : 400px;
	border : 1px solid gray;
}

table{
	margin : auto;
	width : 380px;
	text-align: center;
}
</style>
</head>
<body>
<section id = "loginformArea">
<form action="userLoginAction.usr" method="post" name="loginform">
<input type="hidden" name="u_grade">
<table>
	<tr>
		<td colspan="2">
			<h1>로그인</h1>
		</td>
	</tr>
	<tr>
	 	<th>아이디</th>
	 	<td><input type="text" name="u_id" value="<%=cookieId%>" size="30" placeholder="아이디입력(필수)"></td>
	</tr>
	<tr>
	 	<th>비밀번호</th>
	 	<td><input type="password" name="u_password" size="30" placeholder="비밀번호입력(필수)"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="checkbox" name="remember" />아이디 저장
			<input type="submit" value="로그인" />			
		</td>
	</tr>
	<tr>
		<th colspan="2">
			<a href="userIdFindForm.usr">[아이디 찾기]</a>
			<a href="userPwFindForm.usr">[비밀번호 찾기]</a>
		</th>
	</tr>
</table>
</form>

</section>
</body>
</html>