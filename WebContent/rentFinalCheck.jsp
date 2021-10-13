<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header1.jsp"></jsp:include>

<section class="section-bg-img2">
	<div class="banner"
		style="background-image: url(images/bg-rental-rez2.png); height: 180px;"></div>
</section>
<section class="section-reserve">
	<section class="form-field">
		<form>
			<div class="text-input">예약자정보</div>
			<div class="div_clear"></div>
			<label>예약자명</label> <input type="text" placeholder="이름을 입력하세요."
				size="50"> <label>휴대폰번호</label> <input type="text"
				placeholder="숫자만 입력하세요." size="50">

			<div class="text-input">운전자정보</div>
			<div class="div_clear"></div>
			<label>생년월일</label> <input type="text" placeholder="이름을 입력하세요.">

			<label>운전경력</label> <input type="text" placeholder="숫자만 입력하세요.">

			<div class="text-input">면허정보(선택)</div>
			<div class="div_clear"></div>
			<label>면허정보</label> <input type="text" placeholder="이름을 입력하세요.">

			<label>요청사항(선택)</label>
			<textarea placeholder="내용을 입력하세요." cols="70" rows="5"></textarea>
		</form>
	</section>
	
	<hr>

	<section>
		<div>
			<input type="radio" name="check2"> 개인정보처리방침 동의 <a href="#">바로가기▶</a>
			<input type="radio" name="check2"> 대여조건/환불정책 동의<a href="#">바로가기▶</a>
		</div>
		<hr>
		<a href="javascript:history.back();">이전단계</a> <a
			href="javascript:history.back();">다음단계</a>
	</section>
</section>
	
<section class="section-reserve"> 	

</section>
</body>
</html>