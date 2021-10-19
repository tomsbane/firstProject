<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.* ,javax.sql.*, javax.naming.*"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>ID 찾기</title>
<style>
*{
margin: 0;
padding: 0;
}
header{

background-color:yellow;
text-align: center;
}
ul{
display: flex;
overflow: hidden;
list-style: none;
margin: 0;
padding: 0;
}
li a{
display: inline-block;
}
li a:hover {
    background-color: #FF0000;
}
a{
text-decoration:none;
}
#emailForm{
display:none;
position: absolute;
}
.tel{
display:none;
position: absolute;
}
#email a:hover #emailForm{
display: block;
}
#tel:hover .tel{
display: block;
}
#tel:link .tel{
display: block;
}
</style>
</head>

<!--[아이디중복확인]창이 열리면 onload이벤트에 의해 init()함수가 호출된다.-->
<body onload="init()">
<header>
<ul>
	<li>
		<a href="#" id="email">이메일로 찾기</a>
		<div id="emailForm">
			<form action="idFindProcess.jsp" method="post" name="f" >
			가입하신 이메일을 입력해주세요.
			<input type="text" name="email1Check" id="idCheck">@
			<input type="text" name="email2Check" id="idCheck">&nbsp;&nbsp;
			<input type="submit" value="찾기" id="submit">
			</form>
		</div>
	</li>
	<li>
		<a href="#" id="tel">전화번호로 찾기</a>
		<div class="tel">
			<form action="idFindProcess.jsp" method="post" name="f">
			가입하신 전화번호를 입력해주세요.
			<input type="text" name="tel" id="idCheck">
			<input type="submit" value="찾기" id="submit">
			</form>
		</div>
	</li>
</ul>
</header>
</body>
</html>

