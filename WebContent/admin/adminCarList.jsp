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
</style>
</head>
<body>

	<section id="carListArea">
	<h1>전체 차량보기 페이지입니다.</h1>
	<br><br>
		<table>
			<tr>
				<td>
				<h3>차량 선택</h3>
				</td>
			</tr>
				
			
			<c:forEach var="car" items="${carList}" varStatus="status">
			<tr>
				<td><a href="carView.ad?no=${car.car_no}">${car.car_no}</a></td>
				<td>상품명: ${car.car_name}&nbsp;&nbsp;no:${car.car_no}</td>
				<td>예약가능여부 :</td>
				<td>${car.car_reserve }&nbsp;</td>
				<td><a href=""><input type="button" value="Change"></a></td>
			</tr>
			</c:forEach>
				
			<c:if test="${carList == null}"> <!-- 2.개 상품목록이 없으면 -->
				<div class="div_empty">렌트카 상품이 없습니다.</div>
			</c:if>	
		</table>
		<a href="carRegistForm.ad">차량추가하기</a> | <a href="reserveCar.ad">예약중 차량만 보기</a>
	</section>

</body>
</html>















