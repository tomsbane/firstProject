<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TAGO</title>
<link rel="stylesheet" href="css/index.css">
</head>	
<body>
	<section class="showcase">
		<header>
		<div class="logo"><a href="index.jsp">TAGO</a></div>
			<div class="toggle"></div>
		</header>

		<video preload="auto" autoplay loop muted="muted" width="100%" height="400" src="video/main.mp4" type="video/mp4"></video>
		
		<div class="overlay"></div>
		
		<div class="text">
			<h2>DAEGU RENTCAR</h2>
			<h3>TAGO</h3>
			<p>렌트카</p>
			   <a href="shortRentList.do">단기렌트</a> <a href="longRentList.do">장기렌트</a>
			   
		</div>
		<ul class="social">
			<li><a href=""><img src="images/fb.png"></a></li>
			<li><a href=""><img src="images/twit.png"></a></li>
			<li><a href=""><img src="images/insta1.png"></a></li>
		</ul>
	</section>
	
	<div class="menu">
		<ul>
		<li><a href="main.jsp">회사소개</a></li>
		<li><a href="shortRentList.do">단기렌트</a></li>
		<li><a href="longRentList.do">장기렌트</a></li>
		<li><a href="quote.do">견적상담</a></li>
		<li><a href="qna.do">고객센터</a></li>
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


