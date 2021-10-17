<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>리뷰</title>
</head>
<body>
<c:choose>
	<c:when test="${c_id ne ''}">
		<table>
			<tr> <!-- 사용자가 리뷰작성 -->
				<td>
					<jsp:include page="writeReview.jsp"></jsp:include>
				<td>
			</tr>
			<tr> <!-- 작성된 리뷰 보기 -->
				<td>      <!--showReview에 showReview.jsp이 보여짐 -->
					<jsp:include page="${showReview}"></jsp:include>
				<td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		&nbsp;
	</c:otherwise>
</c:choose>
</body>
</html>