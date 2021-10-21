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
a {
	text-decoration: none;
	color: black;
}
.header-logo {
	width:145px;
	height:83px;
	margin-right:10px;
	float: left;
}
.header-logo-text{
	font-size: 50px;
	color: black;
	text-transform: uppercase;
	cursor: pointer;
	text-decoration: none;
}
.header-box { 
	text-align:center;
	text-align:center;
	width: 100%;
	height: 83px;
	padding: 0px 115px 0px 115px;
	z-index: 10;
	position: relative;
}

.header-box > ul {
	list-style: none;
	text-decoration: none;
	display:inline-block; 
	
	height: 83px;
}

.header-box > ul > li {
	cursor: pointer;
	margin-left: 20px;
	margin-right:20px;
	float:left;
	font-weight: bold;
	font-size: 20px;
	text-align: center;
	line-height: 83px;
	
}
.header-box > ul > li > a{
	text-decoration: none;
	color: black;
}
.header-box > ul > li:hover{
	text-decoration: none;
	color: black;
}
.log {
	width:100px;
	float:right;
	text-decoration: none;
	font-size: 20px;
	line-height: 83px;
	font-weight: bold;	
}
</style>
</head>
<body>
<nav class="header-box">

<div class="header-logo">
	<h2 class="header-logo-text">	
		<a href="${pageContext.request.contextPath}/index.jsp">TAGO</a>
	</h2>
</div>

	<c:choose>
    	<c:when test="${c_grade eq 'admin'}">
			<ul>
				<li><a href="${pageContext.request.contextPath}/adminCarList.ad">차량관리</a></li>
				<li><a href="${pageContext.request.contextPath}/carOrderList.ad">예약현황관리</a></li>
				<li><a href="${pageContext.request.contextPath}/customerList.ad">회원관리</a></li>
				<li><a href="${pageContext.request.contextPath}/totalSalesList.ad">매출관리</a></li>
				<li><a href="#">고객센터</a></li>
				<li><a href="${pageContext.request.contextPath}/customerView.cus">내정보 보기(임시)</a></li>
			</ul>
		</c:when>
	</c:choose>
	
<div class="log">
	<c:choose>
	   	<c:when test="${c_id eq null}">
			<a href="${pageContext.request.contextPath}/customerLogin.cus">로그인</a>
		</c:when>
		
		<c:otherwise>
			<a href="${pageContext.request.contextPath}/customerLogout.cus">로그아웃</a>
		</c:otherwise>
	</c:choose>
</div>	
		
</nav>			
</body>
</html>