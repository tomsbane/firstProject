<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String cookieId = "";//★★null로 초기화 하면 안됨

Cookie[] cookies = request.getCookies();
if(cookies != null && cookies.length > 0){
	for(int i=0 ; i<cookies.length ; i++){
		if(cookies[i].getName().equals("c_id")){
			cookieId = cookies[i].getValue();//쿠키값을 얻어와
		}
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 사이트</title>
<meta http-equiv="Content_type" content="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

</head>
<body>

	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type=button class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">TAGO</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		
			<ul class="nav navbar-nav">
				
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="botton" aria-haspupup="true"
						aria-expanded="false">메뉴<span class=caret></span></a>
						
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/index.jsp">홈으로</a></li>
						<li><a href="customerJoin.cus">회원가입</a></li>
						<li><a href="javascript:window.open('${pageContext.request.contextPath}/idCheck/idFind.jsp?openInit=true','아이디찾기','top=10, left=10, width=500, height=300')">아이디 찾기</a></li>
						<li><a href="javascript:window.open('${pageContext.request.contextPath}/idCheck/idFind.jsp?openInit=true','비밀번호찾기','top=10, left=10, width=500, height=300')">비밀번호 찾기</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="customerLoginAction.cus">
					
					<div class="form-group">
						<input type="text" class="form-control" name="c_id" value="<%=cookieId%>" placeholder="아이디" name="userID" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" name="c_password" placeholder="비밀번호" name="userPassword" maxlength="20"/>
					</div>
						<input type="submit" class="btn btn-primary form-control" value="로그인"><br>
						<input type="checkbox" name="remember" <%if(cookieId != "") {out.print("checked");} %>/>아이디 저장<br><br>
						
						<nav style="display: flex;">
						<div><a href="#"><input type="image" src="${pageContext.request.contextPath}/images/naver_login.png" style="width:100px;"></a></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<div><a href="#"><input type="image" src="${pageContext.request.contextPath}/images/kakao_login.png" style="width:100px;"></a></div>
						</nav>
						<br> <a href="customerJoin.cus">계정이 없으신가요? 회원가입 하기</a>
				</form>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>