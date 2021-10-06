<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
isELIgnored="false"%><!-- 최신이클립스는 isELIgnored="false" 자동 추가되므로 생략가능 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
<header id="header">
	<div class="header1">
		<a href="${pageContext.request.contextPath}/userMain.jsp">
			<img src="${pageContext.request.contextPath}/img/logo/maclogo.png" width="50%">
		</a>
	</div>
	<div class="header2"><!-- kiosk :무인주문시스템 -->
		<a href="${pageContext.request.contextPath}/menu.kiosk">주문하기</a>
		<a href="#">메뉴</a>
		<a href="#">이벤트</a>
		<a href="#">가맹점 문의</a>
	</div>
	
	<br>
	<!-- 로그인에 성공하면 세션영역에 "u_id값과 name값"을 속성으로 공유 -->
	
	<div class="header3">
	<c:choose>
		<c:when test="${u_id eq null}">
		<a href="${pageContext.request.contextPath}/userLogin.usr">로그인</a>
		|
		<a href="${pageContext.request.contextPath}/userJoin.usr">회원가입</a>
		</c:when>
		<c:otherwise>
		${u_name}님 환영합니다.<br>
		<a href="${pageContext.request.contextPath}/userLogout.usr">로그아웃</a>
		|
		<a href="${pageContext.request.contextPath}/myOrder.kiosk">주문내역보기</a>
		|
		<a href="${pageContext.request.contextPath}/userView.usr">회원정보관리</a>
		|
		<a href="#">고객관리</a>
		</c:otherwise>
	</c:choose>
	</div>
<br><br><br><br>
<hr width=100% />
</header>


</body>
</html>



