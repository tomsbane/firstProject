<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style1.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script>
function check(){
	if(f.check.checked ==false){
		
		return alert("차량 손해 면책제도를 동의하지 않으셨습니다.");
	}
	f.submit();
}
</script> 
</head>
<body>
<jsp:include page="/userHeader.jsp"></jsp:include>

<section class="section-bg-img2">
	<div class="banner"
		style="background-image: url(images/bg-rental-rez2.png); height: 180px;"></div>
</section>
<form action="shortRentFinalCheck.do" method="post" name="f">
<input type="hidden" value="${car_no }" name="car_no" id="car_no">
<section class="section-reserve"> 
	<div class="car-info">
		<table class="car-table">
			<tr>
				<td class="line_left">차 이름</td>
				<td class="line_right">${carInfo.car_name}</td>
			</tr>
			<tr>
				<td class="line_left">차종</td>
				<td class="line_right">${carInfo.car_group}</td>
			</tr>
			<tr>
				<td class="line_left">차 브랜드</td>
				<td class="line_right">${carInfo.car_brand}</td>
			</tr>
			<tr>
				<td class="line_left">가격</td>
				<td class="line_right">${carInfo.car_price}원</td>
			</tr>
		</table>	
	<img src="carImages/${carInfo.car_image }"> 
	</div>
			
	<div class="div_clear"></div>
	
	<div class="cnt-group">
	<div class="div-date">
		<input type="hidden" id="rental_date" name="rental_date" value="${rental_date }">
		<input type="hidden" id="return_date" name="return_date" value="${return_date }">
		<span class="div-font">인수일시</span> ${rental_date} <img src="images/arrow.png" class="arrow"> <span class="div-font">반납일시</span> ${return_date}
	</div>
	</div>

		
	<div class="col-group">
		<div class=col-left>
		 	<div class="cnt-title"><h2>배차(인수)</h2></div>
				<div class="cnt-info">현재 타고 수성점에서만 배차하실 수 있습니다.</div>
				
				<label for="rental_place1" class="label-hidden">시도 선택</label>
				<select name="rental_place1">
					<option value="대구광역시">대구광역시</option>
				</select>
				
				<label for="rental_place2" class="label-hidden">구 선택</label>
				<select name="rental_place2" id="rental_place2">
					<option value="수성구">수성구</option>
				</select>
				
				<label for="rental_place3" class="label-hidden">동 선택</label>
				<select name="rental_place3">
					<option value="만촌동">만촌동</option>
				</select>
		</div>
		 
		
		<div class=col-right>
			<div class="cnt-title"><h2>회차(반납)</h2></div>
			
				<div class="cnt-info">현재 타고 수성점에서만 반납하실 수 있습니다.</div>
				
				<label for="return_place1" class="label-hidden">시도 선택</label>
				<select name="return_place1">
					<option value="대구광역시">대구광역시</option>
				</select>
				
				<label for="return_place2" class="label-hidden">구 선택</label>
				<select name="return_place2">
					<option value="수성구">수성구</option>
				</select>
				
				<label for="return_place3" class="label-hidden">동 선택</label>
				<select name="return_place3">
					<option value="만촌동">만촌동</option>
				</select>
		</div>
	</div>
	
	
	<div class="div_clear"></div>
	
	<section class="section-insur">	
		<div class="text">차량 손해 면책제도 선택</div>

		<div class="select-insur">
		<div class="div_clear"></div>
			<div class=insur-button>
				<div class="insur-text">
				<b>일반자차</b><br>
				사고 건당 고객부담금 
				50만원<br>(면책한도 500만원, 휴차료 별도)
				</div>	
			</div>	
			<div class=insur-button>
				<div class="insur-text">
				<b>슈퍼자차</b><br>
				사고 건당 고객부담금 
				20만원<br>(면책한도 500만원, 휴차료 포함)
				</div>
			</div>
			<div class=insur-button>
				<div class="insur-text"><b>차량면책 선택안함</b></div>
			</div>		
			<div class="div_clear"></div>	
			<div class="alert">			
				<ul>
					<li>- 차량대여 중 운전자의 과실에 의한 대여차량의 손해는 임차인의 책임이나,
					 	본 제도에 가입함으로써 보상 받을 수 있습니다.</li>
					<li>- 단, 임차도중 사고발생 시 반드시 지티렌트카로 연락해주셔야 합니다.</li>
					<li style="color:#F73E3E;">- 고객부담금은 사고 발생 시 고객이 최소한으로 부담해야 하는 수리금액입니다.</li>
					<li style="color:#F73E3E;">- 휴차료는 영업용 자동차가 수리기간으로 인해 영업을 하지 못하는 휴차 손해를 말합니다.</li>
				</ul>
			</div>
		</div>
		
	
		
		<div class="text">유의사항</div>
			
		<div class="alert2">
			차량 인수 후, 단순 변심으로 반납할 경우 배송비(차량 탁송비용)는 고객부담입니다. <br>
			<br>(부가용품 안내) 차량내 비치된 블랙박스 및 내비게이션은 고객님의 편의를 위해 무상으로 제공되는 부가용품입니다.<br>
			부가용품의 의한 문제 발생시 당사에서 책임지지 않습니다.<br>
			(금연 안내) 차량 내부의 흡연 및 실내악취 발생시 클리닝비용 15만원이 청구 및 이용제한이 될 수 있습니다.<br>
			(과태료 안내) 임차 기간중 발생한 과태료 및 통행료는 임차인의 주소지로 자동 명의 이전 됩니다.<br>
			<br>공급업체의 반납·환불 정책 관련 또는 기타 자세한 안내를 원하시면 지티렌트카 고객센터 (1577-3087)로 문의해 주세요.
		</div>
		<hr>	
		
		<div class="label-left" style="align:left">
		<label ><input type="checkbox" name="check"> 위 차량 손해 면책제도 안내에 동의하셨습니다.</label>
		</div>
		
		<div class="box">
			<a href="javascript:history.back();" class="box-b"> < 이전단계</a>
			
			<a href="javascript:check();" class="box-a">다음 단계 > </a>
		</div>
		
	</section>	
</section>
</form>
</body>
</html>