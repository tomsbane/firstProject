<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/qna.css">
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

<section class=section-bg-img>
<div class="banner" style="background-image: url(${pageContext.request.contextPath}/images/bg1.png)"><h2>고객센터</h2></div>
</section>

<div class="request-box clearfix">
	<ul>
		<li class="request-list">			
			<span class="cs-img"><img src="${pageContext.request.contextPath}/images/cs.png"></span>
			<div class="request-text">
			<h3>고객지원센터</h3>
			<a href="#">1588-0123</a>
			<p>이용시간 09:00~18:00(연중무휴)</p>
			</div>
		</li>
		<li class="request-list-right">			
			<span class="cs-img"><img src="${pageContext.request.contextPath}/images/cs2.png"></span>
			<div class="request-text">
			<h3>고객지원센터</h3>	
			<a href="#">1588-0123</a>
			<p>이용시간 09:00~18:00(연중무휴)</p>
			</div>
		</li>
	</ul>
</div>

</body>
</html>