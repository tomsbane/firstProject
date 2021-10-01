<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TAGO</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<section class="showcase">
		<header>
			<a href="index.jsp" style="text-decoration: none"><h1 class="logo">TAGO</h1></a>
			<div class="toggle"></div>
		</header>
		
	
	
	
	</section>
	
	<section>
	<div class="banner"></div>
	</section>
	
	<div class="menu">
		<ul>
		<li><a href="main.jsp">회사소개</a></li>
		<li><a href="shortrent.jsp">단기렌트</a></li>
		<li><a href="longrent.jsp">장기렌트</a></li>
		<li><a href="quota.jsp">견적상담</a></li>
		<li><a href="qna.jsp">고객센터</a></li>
		</ul>
	</div>
	
	<script>
		const menuToggle = document.querySelector('.toggle')
		const showcase = document.querySelector('.showcase')
		
		menuToggle.addEventListener('click', () => {
			menuToggle.classList.toggle('active')
			showcase.classList.toggle('active')
		})
	</script>

</body>
</html>















