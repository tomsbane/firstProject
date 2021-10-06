<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<!-- html5에서 소개된 뷰포트 <meta>태그를 사용하면 모바일 기기에서 실제 랜더링되는 영역과 뷰포트의 크기, 중 레벨을 조절할 수 있다. 
- 일반적으로 사용되는 설정
- width=device-width : 페이지의 너비를 기기의 스크린 너비로 설정한다. 즉, 랜더링 영역을 기기의 뷰포트의 크기와 같게 만들어 준다.
- initial-scale=1.0 : 처음 페이지 로딩시 확대/축소가 되지 않은 원래 크기를 사용하도록 한다. 0~10사이의 값을 가진다.
-->
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/style.css">
<title>맥 카페</title>
</head>
<body>
<div>
	<jsp:include page="userHeader.jsp" />
	<section id="section">
		<h3>움직이는 배너나 홍보 이미지</h3>
		<h3>메인부분에 올라갈 상품 이미지나 글 입력</h3>
		<a href="${pageContext.request.contextPath}/burgerView.kiosk?id=mac_lunch">
			<img src="${pageContext.request.contextPath}/img/user_img/user_main/mac_lunch.png">
		</a>
	</section>
	<jsp:include page="userFooter.jsp" />
</div>
</body>
</html>