<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String cookieId = "";//★★null로 초기화 하면 안됨

Cookie[] cookies = request.getCookies();
if(cookies != null && cookies.length > 0){
	for(int i=0 ; i<cookies.length ; i++){
		if(cookies[i].getName().equals("c_id")){
			cookieId = cookies[i].getValue();//쿠키값을 얻어와
		}
	}
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹 사이트</title>
<meta http-equiv="Content_type" content="text/html" charset="utf-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">

</head>
<!-- DAUM API 적용(DAUM에서 제공하는 주소 검색을 사용하기 위해 반드시 포함) -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
function findAddr(){   
    new daum.Postcode({
        oncomplete: function(data) { //선택시 입력값 세팅
			console.log(data);
			
			document.getElementById("postcode").value = data.zonecode;//우편번호
			
			var roadAddr = data.roadAddress;//도로명 주소
			var jibunAddr = data.jibunAddress;//지번 주소
			
			if(roadAddr != ''){//도로명 주소가 있으면 도로면 주소가 등록되고
				document.getElementById("address1").value = roadAddr;
			}else if(jibunAddr != ''){//도로명 주소가 없고 지번주소가 있으면 지번주소가 등록됨
				document.getElementById("address1").value = jibunAddr;
			}
			//만약 지번주소 대신 무조건 도로명 주소만 입력하고 싶다면 if-else 대신 
			//document.getElementById("address1").value = roadAddr;
			
			//상세주소 필드에 커서를 두어 바로 입력가능한 상태로 만듦
			document.getElementById("address2").focus();
        }
    }).open();  
}</script>
<!-- 유효성 검사 -->
<script type="text/javascript">
function check(){
	//아이디와 비밀번호 값 데이터 정규화 공식
	/* const regIdPass = /^[a-zA-Z0-9]{8,12}$/; */
	const regIdPass = /^[0-9a-z]+$/;
	//이름 정규화 공식
	const regName = /^[가-힝a-zA-Z]{2,}$/;
	
	//이메일 정규화 공식
	/* const regEmail = /^[a-zA-Z0-9_\.\-]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-]+/; */
	const regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	//휴대번호 정규화 공식
	const regCall = /^\d{3}\d{3,4}\d{4}&/; /* -제외 */
	//const regCall = /^\d{3}-\d{3,4}-\d{4}$/; //-포함
	
	//아이디 유효성 검사 - 정규화 공식 이용
	if(!document.f.c_id.value.trim()){
		alert("회원 아이디를 입력하세요.");
		document.f.c_id.focus();//커서를 깜박거림
		return false;
	}else if(!regIdPass.test(document.f.c_id.value.trim())){
		alert("아이디는 8~12자의 영어 대소문자와 숫자로만 입력가능합니다.");
		return document.f.c_id.select();/* 입력한 부분에 블록 설정되어 바로 수정가능한 상태 */
	}else 
	
		//비밀번호 유효성 검사 - 정규화 공식 이용
		if(!document.f.c_password.value.trim()){
			alert("회원 비밀번호를 입력하세요.");
			document.f.c_password.focus();//커서를 깜박거림
			return false;
		}else if(!regIdPass.test(document.f.c_password.value.trim())){
			alert("비밀번호는 8~12자의 영어 대소문자와 숫자로만 입력가능합니다.");
			return document.f.c_password.select();/* 입력한 부분에 블록 설정되어 바로 수정가능한 상태 */
		}else
			
		//비밀번호 유효성 검사 - 정규화 공식 이용
		if(!document.f.c_name.value.trim()){
			alert("회원 이름을 입력하세요.");
			document.f.c_name.focus();//커서를 깜박거림
			return false;
		}else if(!regNamePass.test(document.f.c_name.value.trim())){//이름에 특수문자 들어간 경우
			alert("이름이 잘못 입력되었습니다.(영어 대소문자와 한글만 입력가능)");
			return document.f.c_name.select();/* 입력한 부분에 블록 설정되어 바로 수정가능한 상태 */
		}else		
				
		//이메일 유효성 검사 - 정규화 공식 이용
		if(!document.f.c_email1.value.trim()){
			alert("이메일을 입력하세요.");
			document.f.c_email.focus();//커서를 깜박거림
			return false;
		}else if(!document.f.c_email2.value.trim()){
			alert("도메인을 입력하세요.");
			document.f.c_email.focus();//커서를 깜박거림
			return false;
		}else
					
		//휴대번호 유효성 검사 - 정규화 공식 이용
		if(!document.f.c_call.value.trim()){
			alert("휴대폰번호를 입력하세요.");
			document.f.c_call.focus();//커서를 깜박거림
			return false;
		}else if(!regCall.test(document.f.c_call.value.trim())){//이름에 특수문자 들어간 경우
			alert("휴대폰번호가 잘못 입력되었습니다. 숫자로만 입력해주세요");
			return document.f.c_call.select();/* 입력한 부분에 블록 설정되어 바로 수정가능한 상태 */
		}else
		/*
		postcode(우편번호)와 address1은 "DAUM API"로 사용하여 검색한 내용을 바로 셋팅하므로
		유효성 검사가 필요없음
		*/
		//address2 (상세주소) 유효성 검사 - 정규화 공식 이용
		if(!document.f.address2.value.trim()==''){
			alert("상세주소를 입력하세요.");
			document.f.address2.focus();//커서를 깜박거림
			return false;
		}
	// 위의 조건이 모두 거짓이면
	document.f.submit();
}

</script>
<script type="text/javascript">
  function idVal(id) {
  document.f.c_email2.value = id[id.selectedIndex].value;
  return false;
}
</script>
<body>

	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type=button class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">TAGO</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		
		<!-- 	<ul class="nav navbar-nav">
				<li><a href="main.jsp">메인</a></li>
				<li><a href="BBS.jsp">게시판</a></li>
			</ul> -->
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="botton" aria-haspupup="true"
						aria-expanded="false">메뉴<span class=caret></span></a>
						
					<ul class="dropdown-menu">
						<li><a href="${pageContext.request.contextPath}/index.jsp">홈으로</a></li>
						<li><a href="${pageContext.request.contextPath}/customerLogin.cus">로그인</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
	<div class="container" style="width: 1600px;">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<section id = "joinformArea">
<form action="customerJoinAction.cus" method="post" name="f">
	<!-- 처음 회원가입하면 무조건 Normal등급 -->
	<input type="hidden" name="c_grade" value="normal"/>
		<table>
			<tr>
				<th colspan="2">
					<h3>회원가입</h3>
				</th>
			</tr>
			<tr>
				<th>아이디</th>
				<td align="left"><input type="text" name="c_id" id="c_id" placeholder="영문만 입력하세요." required="required">
				<!-- window.open('팝업 주소', '팝업창 이름', '팝업창 설정') -->
				<input type="button" name="c_idck" id="c_idck" value="중복확인" onclick="window.open('${pageContext.request.contextPath}/idCheck/idCheck.jsp?openInit=true','아이디중복확인','top=10, left=10, width=500, height=300')"/>
				</td>
			</tr>
			<tr id="jointd">
				<th>비밀번호</th>
				<td align="left"><input type="password" name="c_password" placeholder="8~12자의 영문과 숫자만 입력하세요." maxlength="12" size="29"  required="required"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td align="left"><input type="text" name="c_name" placeholder="한글과 영문만 입력하세요."  required="required"></td>
			</tr>
			<tr>
				<th>성별</th>
				<td align="left"><label><input type="radio" name="c_gender" value="M">남</label>&nbsp;&nbsp;
				<label><input type="radio" name="c_gender" value="F">여</label></td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td align="left"><input type="date" name="c_birth"></td>
			</tr>
			<tr>
				<th>이메일 주소</th>
				<td align="left"><input type="text" name="c_email1" size="7" required="required">@<input type="text" name="c_email2" size="7" required="required">
					<select  onchange="idVal(this)" id="idValue">
 					  <option value="" id="optionId1">직접입력</option>
 					  <option value="naver.com" id="optionId1">naver.com</option>
 					  <option value="nate.com" id="optionId1">nate.com</option>
 					  <option value="kakao.com" id="optionId1">kakao.com</option>
					</select>
				</td>
				
			</tr>
			<tr>
				<th>휴대전화</th>
				<td align="left"><input type="text" name="c_tel" placeholder="(-)없이 숫자만 입력하세요" required="required"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td align="left">
					<input type="text" name="postcode" id="postcode" placeholder="우편번호" required="required">
					<input type="button" value="우편번호 찾기" onclick="findAddr()" required="required"><br>
					<input type="text" name="address1" id="address1" placeholder="주소" required="required"><br>
					<input type="text" name="address2" id="address2" placeholder="상세주소" required="required">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center;">
					<br><input type="submit" value="가입하기" onclick="check();">
					<input type="button" value="돌아가기" onclick="login.jsp">
				</td>
			</tr>
		</table>
	</form>

</section>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</body>
</html>