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
	text-align: center;
}
table{
	margin: auto;
	width: 520px;
	text-align: center;
}
#button{
background-color: yellow;
}
</style>
</head>
<body>

	<section id="carListArea">
	<h1>전체 차량보기 페이지입니다.</h1>
	<br><br>
		<table>
			<tr>
				<td>차량번호</td>
				<td>상품명</td>
				<td>예약가능여부</td>
			</tr>
			<c:forEach var="car" items="${carList}" varStatus="status">
			<tr>
				<td><a href="carView.ad?no=${car.car_no}">${car.car_no}</a></td>
				<td>${car.car_name}&nbsp;&nbsp;no:${car.car_no}</td>
				<td>${car.car_reserve }&nbsp;</td>
				<td><a href="reserveChange.ad?car_no=${car.car_no}&car_reserve=${car.car_reserve}"><input type="button" value="전환"></a></td>
			</tr>
			<tr>
				<td colspan="4"><hr></td>
			</tr>
			</c:forEach>
				
			<c:if test="${carList == null}"> <!-- 2.개 상품목록이 없으면 -->
				<div class="div_empty">렌트카 상품이 없습니다.</div>
			</c:if>	
		</table>
		<br>
		<button id="button"><a href="carInsertForm.ad">차량추가하기</a></button>  <button id="button"><a href="reserveCarList.ad">예약불가 차량 보기</a></button>
		<br><br>
	</section>

</body>
</html>















