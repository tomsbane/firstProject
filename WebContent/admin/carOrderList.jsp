<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored = "false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<style type="text/css">
table, th, td { 
	border: 1px solid black; 
}
table { border-collapse: collapse; }
</style>
<title>오늘 주문한 리스트</title>
</head>
<body>
<section>
<!-- 1****************************************************** -->
<!-- 1-1 주문 확인 대기 -->
<div>
	<h3>주문 확인 대기</h3>
</div>
<br>

<table>		
	<c:forEach var="order" items="${carOrderList}" varStatus="status">
		<%-- doneLoop가 반대가 되면 break --%>
	     <c:if test="${not doneLoop}">
	        <c:choose>
			    <c:when test="${order.order_status eq 'order'}">
					<tr>
						<th>예약번호</th>
						<th>고객ID</th>
						<th>예약시간-시작일</th>
						<th>예약시간-반납일</th>
						<th>총 금액</th>
						<th>주문상태</th>
						<th>주문'승인/취소'</th>	
					</tr>
					<%-- 원하는 결과가 나오면 true로 선언 : for문의 break 효과 --%>
					<c:set var="doneLoop" value="true" />
				</c:when>
			
				<c:when test="${order.order_status ne 'order'}">					
					아직 예약 대기 리스트가 없습니다.		
					<%-- 원하는 결과가 나오면 true로 선언 : for문의 break 효과 --%>
					<c:set var="doneLoop" value="true" />		
				</c:when>
			</c:choose>
		</c:if>
	</c:forEach>	
	<form method="post">
	<c:forEach var="order" items="${carOrderList}" varStatus="status">
		<c:if test="${order.order_status eq 'order'}">
			<tr>
				<td>
					<b>
					<a href="orderDetail.adm?order_no=${order.order_no}&u_id=${order.c_id}">${order.order_no }
					</a>
					</b>
				</td>
				<td>${order.u_id }</td>
				<td>${order.order_date }</td>
				<td>예약 승인 대기 상태</td>
				<td>${order.totalMoney }</td>
				<td>
					<a href="orderGet.ad?order_no=${order.order_no}">예약승인</a>
				    <a href="orderCancel.ad?order_no=${order.order_no}">예약취소</a>
				</td>
			</tr>
		</c:if>	
		</c:forEach>
	</form>
</table>

<br> <hr/> <br>

<!-- 1-2 주문 확인 대기에서 [주문 승인] :get-->
<c:if test="${carOrderList ne null && carOrderList.size()>0}">
<div>
	<h3>주문 승인</h3>
</div>
<br>

<table>    
	<c:set var="doneLoop" value="false"/>	
	<c:forEach var="order" items="${carOrderList}" varStatus="status">
		<%-- doneLoop가 반대가 되면 break --%>
	     <c:if test="${not doneLoop}">
	        <c:choose>
			    <c:when test="${order.order_status ne 'get'}">
					<tr>
						<th>주문번호</th>
						<th>고객ID</th>
						<th>주문시간</th>
						<th>주문상태</th>
						<th>총 금액</th>		
					</tr>
					<%-- 원하는 결과가 나오면 true로 선언 : for문의 break 효과 --%>
					<c:set var="doneLoop" value="true" />
				</c:when>
			
				<c:when test="${order.order_status eq 'get'}">					
					아직 주문완료된 리스트가 없습니다.		
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
					<a href="orderDetail.adm?order_no=${order.order_no}&u_id=${order.u_id}">${order.order_no }
					</a>
					</b>
				</td>
				<td>${order.u_id }</td>
				<td>${order.order_date }</td>
				<td>주문 승인</td>
				<td>${order.totalMoney }</td>
			</tr>
		</c:if>		
	</c:forEach>
	</form>
</table>
</c:if>
<br> <hr/> <br>

<!-- 1-3 주문 확인 대기에서 [주문 취소] :cancel-->
<c:if test="${carOrderList ne null && carOrderList.size()>0}">
<div>
	<h3>주문 취소</h3>
</div>
<br>
<table>
<c:set var="doneLoop" value="false"/>	
	<c:forEach var="order" items="${carOrderList}" varStatus="status">
		<%-- doneLoop가 반대가 되면 break --%>
	     <c:if test="${not doneLoop}">
	        <c:choose>
			    <c:when test="${order.order_status eq 'cancel'}">
					<tr>
						<th>주문번호</th>
						<th>고객ID</th>
						<th>주문시간</th>
						<th>주문상태</th>
						<th>총 금액</th>		
					</tr>
					<%-- 원하는 결과가 나오면 true로 선언 : for문의 break 효과 --%>
					<c:set var="doneLoop" value="true" />
				</c:when>
			
				<c:when test="${order.order_status ne 'cancel'}">					
					아직 주문취소된 리스트가 없습니다.		
					<%-- 원하는 결과가 나오면 true로 선언 : for문의 break 효과 --%>
					<c:set var="doneLoop" value="true" />		
				</c:when>
			</c:choose>
		</c:if>
	</c:forEach>
	
	
	
	<form method="post">
	<c:forEach var="order" items="${carOrderList}" varStatus="status"> 
		<c:if test="${order.order_status eq 'cancel'}">
			<tr>
				<td>
					<b>
					<a href="orderDetail.adm?order_no=${order.order_no}&u_id=${order.u_id}">${order.order_no }
					</a>
					</b>
				</td>
				<td>${order.u_id }</td>
				<td>${order.order_date }</td>
				<td>주문 취소</td>
				<td>${order.totalMoney }</td>
			</tr>
		</c:if>
	</c:forEach>
	</form>
</table>



</c:if>


<!-- 2****************************************************** -->
<c:if test="${carOrderList eq null}">
	<p>아직 주문한 메뉴가 없습니다.</p>
</c:if>

<br> <hr/> <br>

</section>
</body>
</html>