<!-- 개 하나의 상세 정보를 출력해주는 뷰페이지  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@page import="vo.Dog"%> --%> <!-- 추가 -->
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#viewForm{
	margin:auto;
	padding-left: 8%;
	width:640px;
	border:1px solid red;
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

#button{
border: 1px black;
}
</style>
</head>
<script type="text/javascript">
function deleteCar(car_no){
	if(confirm("정말로 삭제하시겠습니까?\n삭제 후 다시 복원되지 않습니다") == true){
		location.href="carDelete.ad?car_no="+car_no;
	}else{
		return false;
	}
}
</script>
<body>
<!-- ★혜-752p-두번째 그림 -->
<section id="viewForm">
	<h2>${car.car_name}의 상세정보</h2>	
	<section id = "content_main">
		<section id = "content_left">
			<img src = "${pageContext.request.contextPath}/carImages/${car.car_image}" />
			
		</section>
		<section id = "content_right"> 
			<form action="carModify.ad" method="post" name="modifyForm" enctype="multipart/form-data">
			<input type="hidden" name="car_no" value="${car.car_no}">
			<b>이미지 수정:</b>${car.car_image} <input type="file" name="car_image"><br/>
			<b>상품명 :</b> <input type="text" name="car_name" value="${car.car_name}"> <br/>
			<b>그룹명 :</b> 
			<select name="car_group">
				<option value="경형" <c:if test="${car.car_group eq '경형'}">selected</c:if>>
				경형
				</option>
				<option value="중소형" <c:if test="${car.car_group eq '중소형'}">selected</c:if>>
				중소형
				</option>
				<option value="SUV"  <c:if test="${car.car_group eq 'SUV'}">selected</c:if>>
				SUV
				</option>
				<option value="대형"  <c:if test="${car.car_group eq '대형'}">selected</c:if>>
				대형
				</option>
				<option value="수입"  <c:if test="${car.car_group eq '수입'}">selected</c:if>>
				수입
				</option>
			</select> <br/>
			<b>연식 :</b> <input type="number" name="car_year" min="1950" max="2050" step="1" value="${car.car_year}"> <br/>
			<b>예약가능여부 :</b> 
			<select name="car_reserve">
				<option <c:if test="${car.car_reserve eq 'y'}">selected</c:if>>
				y
				</option>
				<option <c:if test="${car.car_reserve eq 'n'}">selected</c:if>>
				n
				</option>
			</select> <br/>
			<b>가격 :</b> <input type="number" name="car_price" value="${car.car_price}" min="0" max="1000000" step="1000"> <br/>
			<b>브랜드 :</b> <input type="text" name="car_brand" value="${car.car_brand}"> <br/><br/>
			<a href="adminCarList.ad"><button>전체 목록보기</button></a>
			<input type="submit" value="수정하기" >
			<input type="button" value="삭제하기" onclick="deleteCar('${car.car_no}')">
			<br><br>
			</form>
		</section>
	</section>
	
	<div style="clear:both"></div>
	
	
</section>
</body>
</html>




