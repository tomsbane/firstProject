<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"> 
<meta http-equiv="Content_type" content="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<head>

<title>Insert title here</title>
<style>

</style>
</head>
<body>


<nav class="header-box">
<div class="header-logo">
	<h2 class="header-logo-text">	
		<a href="${pageContext.request.contextPath}/index.jsp">TAGO</a>		
	</h2>
</div>
<div class="back">	
<div class="toggle"></div>
</div>	


		<c:choose>
	    	<c:when test="${c_grade eq 'admin'}">
			<ul>
				<li><a href="${pageContext.request.contextPath}/adminCarList.ad">차량관리</a></li>
				<li><a href="${pageContext.request.contextPath}/carOrderList.ad">예약현황관리</a></li>
				<li><a href="${pageContext.request.contextPath}/customerList.ad">회원관리</a></li>
				<li><a href="${pageContext.request.contextPath}/totalSalesList.ad">매출관리</a></li>
				<li><a href="${pageContext.request.contextPath}/customerView.cus">MyPage</a></li>
			</ul>
			</c:when>
			<c:otherwise>
			<ul>
			
				<li><a href="longrentList.jsp">회사소개</a></li>
				<li><a href="${pageContext.request.contextPath}/shortRentList.do">단기렌트</a></li>
				<li><a href="longrentList.jsp">장기렌트</a></li>
				<li><a href="longrentList.jsp">견적상담</a></li>
				<li><a href="${pageContext.request.contextPath}/customerView.cus">MyPage</a></li>
			</ul>
			</c:otherwise>
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
	
<div class="menu">
		<ul>
			<li><a href="longrentList.jsp">회사소개</a></li>
			<li><a href="shortRentList.do">단기렌트</a></li>
			<li><a href="longrentList.jsp">장기렌트</a></li>
			<li><a href="longrentList.jsp">견적상담</a></li>
			<li><a href="longrentList.jsp">고객센터</a></li>
		</ul>
</div>	

<script>
		const menuToggle = document.querySelector('.toggle')
		const header-box = document.querySelector('.header-box')
		
		menuToggle.addEventListener('click', () => {
			menuToggle.classList.toggle('active')
			header-box.classList.toggle('active')
		})
</script>

</nav>		

		

	
</body>
</html>
