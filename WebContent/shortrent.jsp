<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TAGO</title>
<link rel="stylesheet" href="css/style.css">
<style>
table{
	margin: auto;
	width: 380px;
	text-align: center;
}
</style>
</head>
<body>
	<!-- <section class="showcase"></section>
	<header>
		<a href="index.jsp"><h1 class="blogo">TAGO</h1></a>
		<div class="btoggle"></div>
	</header>
	<div class="banner"></div>
	-->
	<div class="calendar">
		<table>
			<tr>
				<th><h1>예약 일정 선택 </h1></th>
			</tr>
			
		<tr>
				<td>렌트를 희망하는 인수일시와 시간을 선택해주세요</td>
			</tr>	
		</table>
	</div>
	
	<section id="carListArea">
		<table border=1 >
			<tr>
				<td colspan="3">
					<h2>차량 선택</h2>
				</td> 
			</tr> 
						
			<tr> 
				<c:forEach var="car" items="${carList}" varStatus="status">
					<td>
						<a href="#"><img src="carImages/${car.car_image}" id="productImage" /></a>
						<br/>상품명: ${car.car_name}
						<br/>가격 :${car.car_price}	
						<br/><input type="button" value="문의하기">
					</td>
				<c:if test="${((status.index+1) mod 3) == 0 }">
			</tr>
			
			<tr>	
				</c:if>
				</c:forEach>
			</tr>		
				<c:if test="${carList == null}"> <!-- 2.개 상품목록이 없으면 -->
					<div class="div_empty">렌트카 상품이 없습니다. 예약불가</div>
				</c:if>	
			</table>
		</section>
			
	 <!--  메뉴 -->
 	 <div class="menu">
		<ul>
		<li><a href="main.jsp">회사소개</a></li>
		<li><a href="shortRentList.do">단기렌트</a></li>
		<li><a href="longRentList.do">장기렌트</a></li>
		<li><a href="quote.do">견적상담</a></li>
		<li><a href="qna.do">고객센터</a></li>
		</ul>
	</div>
	
	<script>
		const menuToggle = document.querySelector('.btoggle')
		const showcase = document.querySelector('.showcase')
		
		menuToggle.addEventListener('click', () => {
			menuToggle.classList.toggle('active')
			showcase.classList.toggle('active')
		})
	</script>
	
	
	
</body>
</html>















