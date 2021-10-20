<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored = "false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원수정완료</title>
</head>
<body>
	<div>
		<h3>[${sessionScope.c_name}]회원님의 정보 수정이 완료되었습니다.</h3>
		<br>
		<a href="shortRentList.do">확인</a>
	</div>
</body>
</html>