<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TAGO</title>
<link rel="stylesheet" href="css/style.css">
<link type="text/css" href="css/jquery.simple-dtpicker.css" rel="stylesheet" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.simple-dtpicker.js"></script>
<script type="text/javascript">
	$(function(){
		$('*[name=rental_date]').appendDtpicker({			
			"locale": "ko"
		});
	});
	</script>
<script type="text/javascript">
	$(function(){
		$('*[name=return_date]').appendDtpicker({			
			"locale": "ko"	
		});
	});
</script>

<script>
$(function() {
    $("#rental_date").appendDtpicker({
        timeFormat: 'h:mm p',
        interval: 60,
        minTime: '10',
        maxTime: '6:00pm',
        defaultTime: '10',
        startTime: '10:00',
        dynamic: false,
        dropdown: true,
        scrollbar: true        
    });
});
$(function() {
    $("#return_date").appendDtpicker({
        timeFormat: 'h:mm p',
        interval: 60,
        minTime: '0',
        maxTime: '6:00pm',
        defaultTime: '11',
        startTime: '10:00',
        dynamic: false,
        dropdown: true,
        scrollbar: true        
    });
});
</script>
</head>

<body>
<jsp:include page="header1.jsp"></jsp:include>

<section class=section-bg-img>
<div class="banner" style="background-image: url(images/bg1.png)"><h2>단기렌트예약</h2></div>
</section>

<form name="f">
	<div align="center">	
		인수일시 <input type="text" name="rental_date" id="rental_date" value="">&nbsp; -->
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
				<form action="shortRentCheck.do" method="post">
				<td>
				<img src="carImages/${car.car_image}"/> <br />
				상품명: ${car.car_name} <br />
				가격: ${car.car_price}원 <br />
				<input type="hidden" name="car_no" id="car_no" value="${car.car_no }">
				<input type="hidden" name="return_date" id="return_date" value="javascript:f.return_date.value">
				<input type="hidden" name="rental_date" id="rental_date" value="javascript:f.rental_date.value">
				<input type="submit" value="문의하기">
				</td>
				</form>
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