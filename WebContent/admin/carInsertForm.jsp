<!-- ★관리자모드 : 새로운 개 상품 등록 정보를 입력하는 뷰페이지  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 754p 그림참조하여 스타일 만들기 -->
<style type="text/css">
#registForm{
	margin:auto;
	width:500px;
	height:600px;
	border:1px solid red;
}

h2{
	text-align:center;
}

table{
	margin: auto;
	width: 450px;
}

.td_left{
	width: 150px;
	background: orange;
}

.td_right{
	width: 300px;
	background: skyblue;
}

#commandList{
	text-align: center;
}

</style>
</head>
<body>
<section id="registForm">
	<header><h2>렌트카 정보 등록</h2></header>
	<!-- 파일 업로드 요청하기 위해 enctype="multipart/form-data"로 지정해야 함 -->
	<form action="carInsert.ad" method="post" name="addForm" enctype="multipart/form-data">
		<table>
			<tr>
				<td colspan="2"><a href="carList.dog">목록보기</a>	</td>
			</tr>
			<tr>
				<th class="td_left">상품명</th> 
				<td class="td_right"><input type="text" name="car_name" required="required"></td>
			</tr>
			<tr>
				<th class="td_left">그룹</th> 
				<td class="td_right">
					<select name="car_group">
						<option value="경형">
						경형
						</option>
						<option value="중소형">
						중소형
						</option>
						<option value="SUV">
						SUV
						</option>
						<option value="대형">
						대형
						</option>
						<option value="수입">
						수입
						</option>
					</select></td>
			</tr>
			<tr>
				<th class="td_left">연식</th>
				<td class="td_right"><input type="text" name="car_year"></td>
			</tr>
			<tr>
				<th class="td_left">예약가능여부</th>
				<td class="td_right">
					<input type="radio" name="car_reserve" value="y" checked="checked">y&nbsp;&nbsp;
					<input type="radio" name="car_reserve" value="n">n
				</td>
			</tr>
			<tr>
				<th class="td_left">가격</th>
				<td class="td_right"><input type="text" name="car_price"></td>
			</tr>
			<tr>
				<th class="td_left">브랜드</th>
				<td class="td_right"><input type="text" name="car_brand"></td>
			</tr>
			<tr>
				<th class="td_left">상품이미지</th>
				<td class="td_right"><input type="file" name="car_image"></td>
			</tr>
			<tr>
				<td colspan="2" id="commandCell">
					<input type="submit" value="렌트카상품등록">
					<input type="reset" value="다시작성">
					<input type="button" value="뒤로가기" onclick="javascript:history.back();" />
				</td>
			</tr>
		</table>
	</form>

</section>

</body>
</html>


