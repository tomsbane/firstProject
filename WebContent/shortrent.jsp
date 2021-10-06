<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TAGO</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />  
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">

<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>  
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
    $(document).ready(function () {
            $.datepicker.setDefaults($.datepicker.regional['ko']); 
            $( "#rental_date" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yymmdd",  
                 minDate: 0,
                 onClose: function( selectedDate ) {    
                      //시작일(startDate) datepicker가 닫힐때
                      //종료일(endDate)의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                     $("#return_date").datepicker( "option", "minDate", selectedDate );
                 }    
 
            });
            $( "#return_date" ).datepicker({
                 changeMonth: true, 
                 changeYear: true,
                 nextText: '다음 달',
                 prevText: '이전 달', 
                 dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                 dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                 dateFormat: "yymmdd",
                 minDate: 0,
                 maxDate: 30,                       // 선택할수있는 최대날짜, ( 0 : 오늘 이후 날짜 선택 불가)
                 onClose: function( selectedDate ) {    
                     // 종료일(endDate) datepicker가 닫힐때
                     // 시작일(startDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 시작일로 지정
                     $("#rental_date").datepicker( "option", "maxDate", selectedDate );
                 }    
 
            });    
    });
</script>
<script>
$(function() {
    $("#rental_time").timepicker({
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
    $("#return_time").timepicker({
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
	<form action="shortRentAgree.do" method="post">
		<div align="center">
				인수일시 : <input type="text" id="rental_date" placeholder="인수일시">&nbsp;
						<input type="text" id="rental_time" name="rental_date" class="form-control" style="width:130px;">&nbsp;&nbsp;&nbsp; --> &nbsp;
				반납일시 : <input type="text" id="return_date" placeholder="반납일시">&nbsp;
						<input type="text" id="return_time" name="return_date" class="form-control" style="width:130px;">&nbsp;&nbsp;&nbsp;
		</div>
		<section id="carListArea">
				<table border=1>
					<tr>
						<td colspan="3">
							<h2>차량 선택</h2>
						</td>
					</tr>
					<tr>
						<c:forEach var="car" items="${carList}" varStatus="status">
							<td><a href="#"><img src="carImages/${car.car_image}"
									id="productImage" /></a> <br />상품명: ${car.car_name} <br />가격
								:${car.car_price} <br />
							<input type="submit" value="문의하기"></td>
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
	</form>
</body>
</html>















