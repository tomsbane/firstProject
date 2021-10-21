<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<meta http-equiv="Content_type" content="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<head>
 <link rel="stylesheet" href="css/header.css">
<title>Insert title here</title>

</head>
<body>
	<section id="header">
	
		<div class="logo">
			<h2 class="logo-text">	
				<a href="index.jsp">TAGO</a>
			</h2>
		</div>
		
		
		
		<div class="menu clearfix">
		<c:choose>
	    	<c:when test="${c_grade eq 'admin'}">
			<ul class="menu-list">
				<li><a href="${pageContext.request.contextPath}/adminCarList.ad">차량관리</a></li>
				<li><a href="${pageContext.request.contextPath}/carOrderList.ad">예약현황관리</a></li>
				<li><a href="${pageContext.request.contextPath}/customerList.ad">회원관리</a></li>
				<li><a href="${pageContext.request.contextPath}/totalSalesList.ad">매출관리</a></li>
				<li><a href="qna.do">고객센터</a></li>
			</ul>
			</c:when>
			<c:otherwise>
			<ul class="menu-list">
				<li><a href="main.jsp">회사소개</a></li>
				<li><a href="${pageContext.request.contextPath}/shortRentList.do">단기렌트</a></li>
				<li><a href="longRentList.do">장기렌트</a></li>
				<li><a href="quote.do">견적상담</a></li>
				<li><a href="qna.do">고객센터</a></li>
				<%-- <li><a href="${pageContext.request.contextPath}/review.re">리뷰(임시)</a></li> --%>
				<%-- <li><a href="${pageContext.request.contextPath}/customerView.cus">내정보 보기(임시)</a></li> --%>
			</ul>
			</c:otherwise>
		</c:choose>
			
			
		</div>
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
		

	</section>
</body>
</html>
