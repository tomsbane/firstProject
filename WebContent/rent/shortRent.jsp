<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>TAGO</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/shortRent.css">
<link type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css" rel="stylesheet" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript">
$(function(){
	$("#rental_date").appendDtpicker({			
		locale: "ko",	
		timeFormat: 'h:mm',
        interval: 60,
        minTime: '10',
        maxTime: '17',
        defaultTime: '10',
        startTime: '10',
        dynamic: false,
        dropdown: true,
        scrollbar: true
	});
	$('#return_date').appendDtpicker({			
		locale: "ko",	
		timeFormat: 'h:mm',
        interval: 60,
        minTime: '10',
        maxTime: '17',
        defaultTime: '11',
        startTime: '13',
        dynamic: false,
        dropdown: true,
        scrollbar: true
	});
});
</script>
<script type="text/javascript">
function dateChk(){
	
	var rental_date= document.getElementById("rental_date").value;
	var return_date= document.getElementById("return_date").value;
	const rentalTime = rental_date.substring(11,13);
	const returnTime = return_date.substring(11,13);
	const rentalDatetime = rental_date.substring(8,13).replace(" ","");
	const returnDatetime = return_date.substring(8,13).replace(" ","");
	
	if(rental_date > return_date){
			alert("반납일이 인수일 보다 늦어야 합니다.");
			return false; 
		}
	else if(rentalTime<10 || returnTime<10){
			alert("10시에서 18시까지만 예약이 가능합니다. 이외 시간예약은 고객센터에 문의해주세요.");
			return false;
		}
	else if(rentalTime>18 || returnTime>18){
			alert("10시에서 18시까지만 예약이 가능합니다. 이외 시간예약은 고객센터에 문의해주세요.");
			return false;
		}
	else if((returnDatetime-rentalDatetime)<4){
			alert("최소 4시간 이상 예약만 가능합니다.");
			return false;
		}
};
</script>
</head>

<body>

<section class=section-bg-img>
<div class="banner" style="background-image: url(images/bg1.png)"><h2>단기렌트예약</h2></div>
</section>
<nav class="menu-box">
	<ul>
		<li><a href="selectAll.do">전체</a></li>
		<li><a href="selectSmallSize.do">경차</a></li>
		<li><a href="selectMidSize.do">중형</a></li>
		<li><a href="selectFullSize.do">대형</a></li>
		<li><a href="selectOverseas.do">수입</a></li>
	</ul>
</nav>
<form name="dateForm" id="dateForm" method="post">
	<div align="center">	
		인수일시 <input type="text" name="rental_date" id="rental_date" value="">&nbsp;<img src="images/arrow.png" class="arrow">
		반납일시 <input type="text" name="return_date" id="return_date" value="">
	</div>
</form>

<section id="carListArea">
	<table class="list-car" >
		<tr>
			<td colspan="3">
				<h2>차량 선택</h2>
			</td>
		</tr>
		<tr>
			<c:forEach var="car" items="${carList}" varStatus="status">
				<td>
				<img src="carImages/${car.car_image}"/> <br />
				상품명: ${car.car_name} <br />
				가격: ${car.car_price}원 <br />
				<form action="shortRentCheck.do" name="f" method="post" onsubmit="return dateChk()">
				<input type="hidden" name="car_no" id="car_no" value="${car.car_no }">
				<input type="submit" value="문의하기">
				</form>
				</td>
				<c:if test="${((status.index+1) mod 3) == 0 }">
		</tr>
		<tr>
			</c:if>
			</c:forEach>
		</tr>
		<c:if test="${carList == null}">
			<!-- 2.개 상품목록이 없으면 -->
			<div class="div_empty">렌트카 상품이 없습니다. 예약불가</div>
		</c:if>
	</table>
</section>

</body>
</html>