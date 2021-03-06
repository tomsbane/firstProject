<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ page import="vo.Rentcar" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TAGO</title>
<link rel="stylesheet" href="css/style.css">
<style>
#carListArea{
	margin:auto;
	width:540px;
	border:1px solid red;
}
table{
	margin: auto;
	width: 520px;
	text-align: center;
}
#commandList{
	text-align: center;
}
</style>
</head>
<body>

	<section id="carListArea">
	<h1 align="center">예약중 차량</h1>
	<br><br>
		<table>
			<tr>
				<td colspan="5">
				<h3>차량 선택</h3>
				</td>
			</tr>
				
			
			<c:forEach var="car" items="${reservecarList}" varStatus="status">
			<tr>
				<td><a href="carView.ad?no=${car.car_no}">${car.car_no}</a></td>
				<td>상품명: ${car.car_name}&nbsp;&nbsp;no:${car.car_no}</td>
				<td>예약가능여부 :</td>
				<td>${car.car_reserve }&nbsp;</td>
				<td><a href="reserveChange.ad?car_no=${car.car_no}&car_reserve=${car.car_reserve}"><input type="button" value="전환"></a></td>
			</tr>
			</c:forEach>
				
			<c:if test="${reservecarList == null}"> <!-- 2.개 상품목록이 없으면 -->
				<div class="div_empty">예약중인 렌트카 상품이 없습니다.</div>
			</c:if>	
		</table>
		<nav id="commandList">
		<div><a href="adminCarList.ad">전체 목록보기</a></div>
		</nav>
	</section>

</body>
</html>
