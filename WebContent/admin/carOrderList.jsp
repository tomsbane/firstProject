<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored = "false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" id="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/css/orderList.css">
<style type="text/css">

</style>
<title>예약 내역</title>
</head>
<body>


<div class="banner" style="background-image: url(${pageContext.request.contextPath}/images/bg-rental-rez2.png);">주문관리</div>



<section class="orderList">
<div class="head-text">배차 예약 확인<br> </div>
<br>

<table>
<c:set var="doneLoop" value="false"/>	
	<c:forEach var="order" items="${carOrderList}" varStatus="status">
		<%-- doneLoop가 반대가 되면 break --%>
	     <c:if test="${not doneLoop}">
	        <c:choose>
			    <c:when test="${order.order_status eq 'get'}">
					<tr>
						<th>예약번호</th>
						<th>고객 ID</th>
						<th>차량번호</th>
						<th>시작일</th>
						<th>반납일</th>
						<th>주문 금액</th>	
						<th>주문 승인</th>	
						<th>주문 취소</th>	
					</tr>
					<%-- 원하는 결과가 나오면 true로 선언 : for문의 break 효과 --%>
					<c:set var="doneLoop" value="true" />
				</c:when>
			
				<c:when test="${order.order_status ne 'get'}">					
					아직 주문이 확인되지 않았습니다.		
					<%-- 원하는 결과가 나오면 true로 선언 : for문의 break 효과 --%>
					<c:set var="doneLoop" value="true" />		
				</c:when>
			</c:choose>
		</c:if>
	</c:forEach>
	
	
	
	<form method="post">
	<c:forEach var="order" items="${carOrderList}" varStatus="status"> 
		<c:if test="${order.order_status eq 'get'}">
			<tr>
				<td>
					<b>
					<a href="orderDetail.adm?order_no=${order.order_no}&c_id=${order.c_id}">${order.order_no }</a>
					</b>
				</td>
				<td>${order.c_id }</td>
				<td>${order.car_no }</td>
				<td>${order.rental_date}</td>
				<td>${order.rental_date}</td>
				<td>${order.rental_price }</td>
				<td><a href="orderToIng.ad?order_no=${order.order_no}">승인ㅇ</a></td>
				<td><a href="orderCancel.ad?order_no=${order.order_no}">취소</a></td>
			</tr>
		</c:if>
	</c:forEach>
	</form>
</table>



<!-- 1-2 주문 확인 대기에서 [주문 승인] :ing-->
<c:if test="${carOrderList ne null && carOrderList.size()>0}">
<div class="head-text">배차 승인<br> </div>
<br>
<table>
<c:set var="doneLoop" value="false"/>	
	<c:forEach var="order" items="${carOrderList}" varStatus="status">
		<%-- doneLoop가 반대가 되면 break --%>
	     <c:if test="${not doneLoop}">
	        <c:choose>
			    <c:when test="${order.order_status eq 'ing'}">
					<tr>
						<th>예약번호</th>
						<th>고객 ID</th>
						<th>차량번호</th>
						<th>시작일</th>
						<th>반납일</th>
						<th>주문 금액</th>	
						<th>주문 승인</th>	
						<th>주문 취소</th>	
					</tr>
					<%-- 원하는 결과가 나오면 true로 선언 : for문의 break 효과 --%>
					<c:set var="doneLoop" value="true" />
				</c:when>
			
				
			</c:choose>
		</c:if>
	</c:forEach>
	
	
	
	<form method="post">
	<c:forEach var="order" items="${carOrderList}" varStatus="status"> 
		<c:if test="${order.order_status eq 'ing'}">
			<tr>
				<td>
					<b>
					<a href="orderDetail.adm?order_no=${order.order_no}&c_id=${order.c_id}">${order.order_no }
					</a>
					</b>
				</td>
				<td>${order.c_id }</td>
				<td>${order.car_no }</td>
				<td>${order.rental_date}</td>
				<td>${order.rental_date}</td>
				<td>${order.rental_price }</td>
				<td><a href="orderGet.ad?order_no=${order.order_no}">승인</a></td>
				<td><a href="orderCancel.ad?order_no=${order.order_no}">취소</a></td>
			</tr>
		</c:if>
	</c:forEach>
	</form>
</table>
</c:if>

<!-- 1-3 배차 승인에서 [배차 완료] :done-->
<c:if test="${carOrderList ne null && carOrderList.size()>0}">
<div class="head-text">배차 완료<br> </div>
<br>
<table>
<c:set var="doneLoop" value="false"/>	
	<c:forEach var="order" items="${carOrderList}" varStatus="status">
		<%-- doneLoop가 반대가 되면 break --%>
	     <c:if test="${not doneLoop}">
	        <c:choose>
			    <c:when test="${order.order_status eq 'done'}">
					<tr>
						<th>예약번호</th>
						<th>고객 ID</th>
						<th>차량번호</th>
						<th>시작일</th>
						<th>반납일</th>
						<th>주문 금액</th>	
						<th>주문 승인</th>	
						<th>주문 취소</th>	
					</tr>
					<%-- 원하는 결과가 나오면 true로 선언 : for문의 break 효과 --%>
					<c:set var="doneLoop" value="true" />
				</c:when>
			
				
			</c:choose>
		</c:if>
	</c:forEach>
	
	
	
	<form method="post">
	<c:forEach var="order" items="${carOrderList}" varStatus="status"> 
		<c:if test="${order.order_status eq 'done'}">
			<tr>
				<td>
					<b>
					<a href="orderDetail.adm?order_no=${order.order_no}&c_id=${order.c_id}">${order.order_no }
					</a>
					</b>
				</td>
				<td>${order.c_id }</td>
				<td>${order.car_no }</td>
				<td>${order.rental_date}</td>
				<td>${order.rental_date}</td>
				<td>${order.rental_price }</td>
				<td><a href="orderGet.ad?order_no=${order.order_no}">승인</a></td>
				<td><a href="orderCancel.ad?order_no=${order.order_no}">취소</a></td>
			</tr>
		</c:if>
	</c:forEach>
	</form>
</table>
</c:if>

<!-- 2****************************************************** -->
<c:if test="${carOrderList eq null}">
	<p>주문 내역이 없습니다.</p>
</c:if>

</section>
</body>
</html>