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
	// DATE 객체
	var NOW_DATE = new Date(); 

	// UTC 시간 계산
	const UTC = NOW_DATE.getTime() + (NOW_DATE.getTimezoneOffset() * 60 * 1000); 

	// UTC to KST (UTC + 9시간)
	const KR_TIME_DIFF = 9 * 60 * 60 * 1000;
	const KR_DATE = new Date(UTC + (KR_TIME_DIFF));

	// 개별 데이터 확인 실시
	var YYYY = KR_DATE.getFullYear(); // 연 (4자리)    		
	var MM = ("00"+(KR_DATE.getMonth()+1)).slice(-2); // 월 (2자리)
	var DD = ("00"+KR_DATE.getDate()).slice(-2); // 일 (2자리)
	var HH24 = ("00"+KR_DATE.getHours()).slice(-2); // 시간 (24시간 기준, 2자리)
	var MI = ("00"+KR_DATE.getMinutes()).slice(-2); // 분 (2자리)
	var SS = ("00"+KR_DATE.getSeconds()).slice(-2); // 초 (2자리)
	
	var todayDatetime=YYYY+MM+DD+HH24+MI;
	var rental_date= document.getElementById("rental_date").value;
	var return_date= document.getElementById("return_date").value;
	const rentalTime = rental_date.substring(11,13);
	const returnTime = return_date.substring(11,13);
	const rentalDatetime=rental_date.replace("-","").replace(":","").replace(" ","").replace("-","");
	const returnDatetime=return_date.replace("-","").replace(":","").replace(" ","").replace("-","");
	 if(!document.f.car_no.value){
			alert("차량을 선택해 주세요.");
			return false; 
		}
	else if(todayDatetime > rentalDatetime || todayDatetime > returnDatetime){
			alert("현재 시간 이후만 예약가능합니다.");
			return false; 
		}
	else if(rentalDatetime > returnDatetime){
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
<form action="shortRentCheck.do" name="f" method="post" onsubmit="return dateChk()">
	<div align="center">	
		인수일시 <input type="text" name="rental_date" id="rental_date" value="">&nbsp;<img src="images/arrow.png" class="arrow">
		반납일시 <input type="text" name="return_date" id="return_date" value="">
	</div>

<section id="carListArea">
	<table class="list-car" >
		<tr>
			<td colspan="3">
				<h2>차량 선택</h2>
			</td>
		</tr>
		<tr>
			<c:forEach var="car" items="${overseas}" varStatus="status">
				<td>
				<label>
				<img src="carImages/${car.car_image}"/> <br />
				상품명: ${car.car_name} <br />
				가격: ${car.car_price}원 <br />
				
				<input type="radio" name="car_no" id="car_no" value="${car.car_no }">
				<input type="submit" value="문의하기">
				</label>
				</form>
				</td>
				<c:if test="${((status.index+1) mod 3) == 0 }">
		</tr>
		<tr>
			</c:if>
			</c:forEach>
		</tr>
		<c:if test="${overseas == null}">
			<!-- 2.개 상품목록이 없으면 -->
			<div class="div_empty">렌트카 상품이 없습니다. 예약불가</div>
		</c:if>
	</table>
</section>

</body>
</html>