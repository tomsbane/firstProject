<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<script>
function check(){
	if(f.check1.checked ==false){
		
		return alert("개인정보 처리방침을 동의하지 않으셨습니다.");
	}
	if(f.check2.checked ==false){
		
		return alert("대여조건/환불정책에 동의하지 않으셨습니다.");
	}
	f.submit();
}
</script> 
<script type="text/javascript">
	let same = document.getElementById('same');
	let name = document.getElementById('c_name');
	let tel = document.getElementById('c_tel');
	let birth = document.getElementById('c_birth');
	
	same.onclick = function(event){
		name=${customer.c_name};
	}
	
</script>
<body>
	<jsp:include page="/userHeader.jsp"></jsp:include>

	<section class="section-bg-img2">
		<div class="banner"
			style="background-image: url(../images/bg-rental-rez2.png); height: 180px;"></div>
	</section>
	

</script>
<form action="shortRentcarOrder.do" method="post" name="f">

	<section class="form-field">
	<section class="left-field">
	
		<div class="text">예약자정보</div>
		<div class="div_clear"></div>
		
		<!-- 추후에 비로그인 상태에서 주문할 수 있도록 아이디 칸 두개로 만듬 -->
		<div class="col-group">
			<c:if test="${customer eq null }">
			<span>이름</span>
			<div class="col-input">
				<input type="text" placeholder=" 이름을 입력하세요." name="c_name"><br>
			</div>
	
			<span>핸드폰</span>
			<div class="col-input">
				<input type="number" placeholder=" 숫자만 입력하세요." name="c_tel"><br>
			</div>
	
			<span>생년월일</span>
			<div class="col-input">
				<input type="text" name="c_birth" width="350" maxlength="8" placeholder="8자리(yyyy-mm-dd)">
			</div>
			</c:if>
			<c:if test="${customer ne null }">
			<span>이름</span>
			<div class="col-input">
				<input type="text" placeholder=" 이름을 입력하세요." name="c_name" value="${customer.c_name }"><br>
			</div>
	
			<span>핸드폰</span>
			<div class="col-input">
				<input type="number" placeholder=" 숫자만 입력하세요." name="c_tel"  value="${customer.c_tel }"><br>
			</div>
	
			<span>생년월일</span>
			<div class="col-input">
				<input type="text" name="c_birth" width="350" maxlength="8" placeholder="8자리(yyyy-mm-dd)" value="${customer.c_birth}">
			</div>
			</c:if>
		</div>
			
		<div class="text">운전자정보</div>
		<input type="checkbox" 	id="elem">

		<div class="div_clear"></div>
	
		<div class="col-group">
		
				<label for="is-same">
				<input type="checkbox" id="same">예약자와 운전자가 동일(체크)</label><br>
				
				<span>이름</span>
				<div class="col-input">
					<input type="text" placeholder=" 이름을 입력하세요." id="c_name"><br>
				</div>
		
				<span>핸드폰</span>
				<div class="col-input">
					<input type="text" placeholder="숫자만 입력하세요." id="c_tel"><br>
				</div>
		
				<span>생년월일</span>
				<div class="col-input">
					<input type="text" name="c_birth" width="350" maxlength="8" id="c_birth" placeholder="8자리(yyyy-mm-dd)">
				</div>
			
			
	
			
		</div>
	
	
		<div class="text">면허정보(선택)</div>
		<div class="div_clear"></div>
		<div class="col-group">
	
			<label for="kind1" class="label-radio"><input type="radio"
				id="kind1" name="lic_info" onclick=""><span> 1종 보통</span></label> <label
				for="kind2" class="label-radio"><input type="radio"
				id="kind2" name="lic_info"><span> 1종 대형</span></label> <label
				for="kind3" class="label-radio"><input type="radio"
				id="kind3" name="lic_info"><span> 2종 보통</span></label>
	
			<div class="div_clear"></div>
			<select id="lic_area" style="width: 220px;">
				<option value="서울">서울</option>
				<option value="부산">부산</option>
				<option value="경기">경기</option>
				<option value="경기남부">경기남부</option>
				<option value="경기북부">경기북부</option>
			</select>
			<input type="text" id="lic_num" size="10.5" maxlength="8" placeholder=" xx xxxxxx xx">
		</div>
	
		<div class="text">요청사항(선택)</div>
		<div class="div_clear"></div>
		<div class="col-group">
			<textarea name="request" id="request" placeholder="내용을 입력하세요."  cols="70" rows="5"></textarea>
		</div>
	
	</section>
	
	
	
	<section class="right-field">
	
		<div class="col-field">
			<div class="col-field-form">
				<table class="col-table">
					<tr>
						<td class="line_left" colspan="3"><h4>대여기간</h4></td>
					</tr>
					<tr>
						<td class="line_left" colspan="3">
						${rental_date} ~ ${return_date}
						<input type="hidden" name="rental_date" value="${rental_date}">
						<input type="hidden" name="return_date" value="${return_date}">
						
						</td>
					</tr>
					<tr>
						<td class="line_left" colspan="3">배차 위치</td>
					</tr>
					<tr>
						<td class="line_left">
							${rental_place1} ${rental_place2} ${rental_place3}
							<input type="hidden" name="rental_place1" value="${rental_place1}">
							<input type="hidden" name="rental_place2" value="${rental_place2}">
							<input type="hidden" name="rental_place3" value="${rental_place3}">
						</td>
					</tr>
					<tr>
						<td class="line_left" colspan="3">반납위치</td>
					</tr>
					<tr>
						<td class="line_left">
							${return_place1} ${return_place2} ${return_place3}
							<input type="hidden" name="return_place1" value="${return_place1}">
							<input type="hidden" name="return_place2" value="${return_place2}">
							<input type="hidden" name="return_place3" value="${return_place3}">
						</td>
					</tr>
				</table>
	
				<table>
					<tr>
						<td class="line_right-map">
							<div id="map" style="width:425px; height:250px;" align="center"></div>
						</td>
					</tr>
				</table>
	
				<table class="col-table">
					<tr>
						<td class="line_left">차량 대여료</td>
						<td class="line_right">
						${carInfo.car_price }
						<input type="hidden" name="rental_price" value="${carInfo.car_price }">
						</td>
					</tr>
	
					<tr>
						<td class="line_left">차량 보험료</td>
						<td class="line_right">(미구현)</td>
					</tr>
				</table>
	
	
				<table class="col-table">
					<tr>
						<td class="line_left">사용 쿠폰</td>
						<td class="line_right">(미구현)</td>
					</tr>
					<tr>
						<td class="line_left">할인 금액</td>
						<td class="line_right">(미구현)</td>
					</tr>
					<tr>
						<td class="line_left">총 대여금액</td>
						<td class="line_right">${carInfo.car_price }</td>
					</tr>
				</table>
	
	
				<input type="checkbox" name="check1"> 개인정보처리방침 동의 <a href="#">바로가기▶</a><br>
				<input type="checkbox" name="check2"> 대여조건/환불정책 동의 <a href="#">바로가기▶</a><br>
					<hr>
					
				<a href="javascript:history.back();">이전단계</a> 
				<a href="javascript:check();">예약신청완료</a>
				
			</div>
		</div>
	
	</section>
	</section>
	<input type="hidden" name="car_no" value="${car_no}">
	
	
</form>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d0ce45414c82b140d5a726a741f11ca1"></script>
	<script>
		var container = document.getElementById('map');
		var options = {
			center : new kakao.maps.LatLng(35.85949249484983,
					128.64698833298496),
			level : 3
		};
		
		var map = new kakao.maps.Map(container, options);
	</script>
</body>
</html>