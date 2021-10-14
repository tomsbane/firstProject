<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TAGO</title>
<link rel="stylesheet" href="css/style.css">
<style>
#customerListArea{
	margin:auto;
	width:870px;
	border:1px solid red;
	text-align: center;
}
table{
	margin: auto;
	width: 850px;
	text-align: center;
}
</style>
</head>
<body>

	<section id="customerListArea">
	<h1>회원 정보</h1>
	<br><br>
		<table>
			<tr>
				<td colspan="6">
				<form action="selectCustomer.ad" method="get" name="f">
				회원 ID 검색 :&nbsp;&nbsp;<input type="text" name="c_id" id="c_id" value="${param.c_id}" />&nbsp;&nbsp;<input type="submit" value="검색하기" >
				</form>
				<br></td>
				
			</tr>
			<!-- 10명만 표기하기, 회원정보 삭제 구현하기-->	
			<tr>
				<td>ID</td>
				<td>등급</td>
				<td>성명</td>
				<td>이메일</td>
				<td>연락처</td>
				<td>가입일</td>
			</tr>
			<c:forEach var="customer" items="${customerList}" varStatus="status" begin="0" end="9">
			<tr>
				<td> ${customer.c_id}</td>
				<td> ${customer.c_grade}</td>
				<td> ${customer.c_name}</td>
				<td> ${customer.c_email1}@${customer.c_email2}</td>
				<td> ${customer.c_tel}</td>
				<td> ${customer.c_joindate}</td>
				<c:choose>
				<c:when test="${customer.c_grade eq 'admin'}">
					<c:if test="${customer.c_id ne 'admin'}"><!-- id가 admin이면 등급변경불가 -->
					<td><a href="normalRegist.ad?c_id=${customer.c_id}"><button>관리자 해지</button></a></td>
					</c:if>
				</c:when>
				<c:otherwise>
				<td><a href="adminRegist.ad?c_id=${customer.c_id}"><button>관리자 등록</button></a></td>
				</c:otherwise>
				</c:choose>
			</tr>
			<tr>
			<td colspan="6"><hr></td>
			</tr>
			</c:forEach>
			<c:if test="${customerList == null}"> <!-- 2.개 상품목록이 없으면 -->
				<div class="div_empty">등록된 회원이 없습니다.</div>
			</c:if>
			<tr>
			<td colspan="6">최대 10명까지만 표시됩니다.~ <br>이 외 회원은 검색으로 찾아보실 수 있습니다.</td>
			</tr>
		</table>
	</section>

</body>
</html>















