<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<meta http-equiv="Content_type" content="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<head>
<!-- <link rel="stylesheet" href="css/header.css"> -->
<title>Insert title here</title>
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Poppins', sans-serif;
}

#header {
	width: auto;
	height: 83px;
	margin-left:15px;
	margin-right:15px;
	display:flex;
	z-index: 10;
	position: relative;
}

a {
	text-decoration: none;
	color: black;
}

a:active {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: none;
	color: black;
}

a:checked {
	text-decoration: none;
	color: black;
}

.logo {
	font-size: 50px;
	color: black;
	text-transform: uppercase;
	cursor: pointer;
	text-decoration: none;
	margin-left:50px;
}

.menu {
	font-size: 17px;
	line-height: 83px;
	margin: 0px 0px 0px 50px;
	justify-content: space-between;
	
}
 
.lists li {
	list-style: none;
	padding: 0px;
	float: left;
	margin-right: 50px;
}

.log {
	text-decoration: none;
	float: right;
	font-size: 17px;
	line-height: 83px;
	margin-right: 50px;
}
</style>
</head>
<body>
	<section id="header">
		<div class="logo">
			<h2 class="logo">
				<a href="index.jsp">TAGO</a>
			</h2>
		</div>
		<div class="menu">
		<c:choose>
	    	<c:when test="${c_grade eq 'admin'}">
			<ul class="lists">
				<li><a href="adminCarList.ad">차량관리</a></li>
				<li><a href="#">예약현황관리</a></li>
				<li><a href="customerList.ad">회원관리</a></li>
				<li><a href="longRentList.do">매출관리</a></li>
				<li><a href="qna.do">고객센터</a></li>
			</ul>
			</c:when>
			<c:otherwise>
			<ul class="lists">
				<li><a href="main.jsp">회사소개</a></li>
				<li><a href="shortRentList.do">단기렌트</a></li>
				<li><a href="longRentList.do">장기렌트</a></li>
				<li><a href="quote.do">견적상담</a></li>
				<li><a href="qna.do">고객센터</a></li>
				<li><a href="review.re">리뷰(임시)</a></li>
			</ul>
			</c:otherwise>
		</c:choose>
		</div>
		 <c:choose>
	    	<c:when test="${c_id eq null}">
			<div class="log"><a href="${pageContext.request.contextPath}/customerLogin.cus">Log In</a></div>
			</c:when>
			<c:otherwise>
			<div class="log"><a href="${pageContext.request.contextPath}/customerLogout.cus">Log Out</a></div>
			</c:otherwise>
		</c:choose>
	</section>
</body>
</html>
