<!-- 개 하나의 상세 정보를 출력해주는 뷰페이지  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>

<%-- <%@page import="vo.Dog"%> --%> <!-- 추가 -->
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#viewForm{
	margin:auto;
	width:640px;
	border:1px solid red;
}

h2{
	text-align:center;
}

img{
 	width: 280px;
 	height: 280px;
 	border: none;
}

#content_main{
	height:300px;
}

#content_left{
	width: 300px;
	float:left;
}

#content_right{
	width: 340px;
	float:left;
}

#commandList{
	text-align: center;
}

</style>
</head>
<body>
<!-- ★혜-752p-두번째 그림 -->
<section id="viewForm">
	<h2>${car.car_name}의 상세정보</h2>	
	<section id = "content_main">
		<section id = "content_left">
			<img src = "carImages/${car.car_image}" />
		</section>
		<section id = "content_right"> 
			<b>상품명 :</b> ${car.car_name} <br/>
			<b>연식 :</b> ${car.car_year} <br/>
			<b>예약가능여부 :</b> ${car.car_reserve} <br/>
			<b>가격 :</b> ${car.car_price} <br/>
			<b>브랜드 :</b> ${car.car_brand} <br/>
		</section>
	</section>
	
	<div style="clear:both"></div>
	
	<nav id="commandList">
		<a href="adminCarList.ad">전체 목록보기</a>
	</nav>
</section>
</body>
</html>




