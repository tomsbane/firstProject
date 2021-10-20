<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored = "false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/style.css">
<title>회원정보보기</title>
<style type="text/css">
#customerViewArea{
	margin:auto;
	padding-left: 8%;
	width:640px;
	border:1px solid red;
}

img{
 	width: 280px;
 	height: 280px;
 	border: none;
}

#content_main{
	height:300px;
}

#content_left{
	width: 300px;
	float:left;
}

#content_right{
	width: 340px;
	float:left;
}

#button{
border: 1px black;
}
</style>

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
			
			if(roadAddr != ''){
				document.getElementById("address1").value = roadAddr;
			}else if(jibunAddr != ''){//도로명 주소가 없으면 지번주소가 등록됨
				document.getElementById("address1").value = jibunAddr;
				//만약 지번주소 대신 무조건 도로명 주소만 입력하고 싶다면 
				//document.getElementById("address1").value = roadAddr;
			}
			
			//상세주소 필드에 커서를 두어 바로 입력가능한 상태로 만듦
			document.getElementById("address2").focus();
        }
    }).open();  
}
</script>

<!-- 유효성 검사 -->
<script type="text/javascript">
function check(){
	//아이디와 비밀번호 값 데이터 정규화 공식
	const regIdPass = /^[a-zA-Z0-9]{8,12}$/;
	
	//이름 정규화 공식
	const regName = /^[가-힝a-zA-Z]{2,}$/;
	
	//이메일 정규화 공식
	const regEmail = /^[a-zA-Z0-9_\.\-]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-]+/;
	
	//휴대번호 정규화 공식
	const regCall = /^\d{3}\d{3,4}\d{4}$/; //-제외
	//consst regCall = /^\d{3}-\d{3,4}-\d{4}$/; //-포함
	
	//아이디 유효성 검사 - 정규화 공식 이용
	if( !document.f.c_id.value.trim() ){//if(document.f.c_id.value==false){
		alert("회원 아이디를 입력하세요.");
		document.f.c_id.focus();//커서를 깜박거림
		return false;
	}else if( !regIdPass.test(document.f.c_id.value.trim()) ){
		alert("아이디는 8~12자의 영어 대소문자와 숫자로만 입력가능합니다.");		
		return document.f.c_id.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
	}else
		
	//비밀번호 유효성 검사 - 정규화 공식 이용
	if( !document.f.c_password.value.trim() ){//if(document.f.c_password.value==false){
		alert("회원 비밀번호를 입력하세요.");
		document.f.c_password.focus();//커서를 깜박거림
		return false;
	}else if( !regIdPass.test(document.f.c_password.value.trim()) ){
		alert("비밀번호는 8~12자의 영어 대소문자와 숫자로만 입력가능합니다.");		
		return document.f.c_password.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
	}else
		
	//이름 유효성 검사 - 정규화 공식 이용
	if( !document.f.c_name.value.trim() ){//if(document.f.c_name.value==false){
		alert("회원 이름을 입력하세요.");
		document.f.c_name.focus();//이름 필드에 커서를 둠
		return false;
	}else if( !regName.test(document.f.c_name.value.trim()) ){//이름에 특수문자가 들어간 경우
		alert("이름이 잘못 입력되었습니다.(영어 대소문자와 한글만 입력가능)");	
		return document.f.c_name.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
	}else
		
	//이메일 유효성 검사 - 정규화 공식 이용
	if( !document.f.c_email.value.trim() ){//if(document.f.c_email.value==false){
		alert("이메일을 입력하세요.");
		document.f.c_email.focus();//커서를 깜박거림
		return false;
	}else if( !regEmail.test(document.f.c_email.value.trim()) ){
		alert("이메일 형식이 올바르지 않습니다.");	
		return document.f.c_email.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
	}else
		
	//휴대번호 유효성 검사 - 정규화 공식 이용
	if( !document.f.c_call.value.trim() ){//if(document.f.c_call.value==false){
		alert("휴대폰번호를 입력하세요.");
		document.f.c_call.focus();//커서를 깜박거림
		return false;
	}else if( !regCall.test(document.f.c_call.value.trim()) ){
		alert("휴대폰번호가 잘못 입력되었습니다. 숫자로만 입력해주세요");	
		return document.f.c_call.select();//입력한 부분에 블록 설정되어 바로 수정가능한 상태로 만듦
	}else
		
	/*
	postcode(우편번호)와 address1은 "DAUM API"로 사용하여 검색한 내용을 바로 셋팅하므로
	유효성 검사가 필요없음
	*/
	//address2 (상세주소) 검사 - 정규화 공식 이용
	if( document.f.address2.value.trim()=='' ){
		alert("상세주소를 입력하세요.");
		document.f.address2.focus();//커서를 깜박거림
		return false;
	}
	
	//위의 조건이 모두 거짓이면
	document.f.submit();
}
</script>
<script type="text/javascript">
  function idVal(id) {
  document.f.c_email2.value = id[id.selectedIndex].value;
  return false;
}
</script>
</head>
<body>
<section id = "customerViewArea">
<form action="customerModifyAction.cus" method="post" name="f" >
<br>
<table>
	<tr>
		<th colspan="2">
			<h2>[회원정보]</h2>
			<h3>회원님의 등급은 ${customer.c_grade} 입니다.</h3>
		</th>
	</tr>
	<tr>
		<th>아이디</th>
		<td><input type="text" name="c_id" id="c_id" value="${customer.c_id}" readonly /></td>
	</tr>	
	<tr>
	 	<th>이름</th>
	 	<td><input type="text" name="c_name" value="${customer.c_name}" placeholder="한글 또는 영문만 입력하세요.(특수문자 제외)" required></td>
	</tr>
	<tr>
		<th>이메일 주소</th>
		<td align="left"><input type="text" name="c_email1" value="${customer.c_email1}" size="7" required="required">@<input type="text" name="c_email2" value="${customer.c_email2}" size="7" required="required">
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
	 	<td>
	 		<input type="text" name="c_tel" value="${customer.c_tel}" placeholder="(-)없이 숫자만 입력하세요" required></td>
	 	</td>
	</tr>
	<tr>
	 	<th>주소</th>
	 	<td>
		 	<input type="text" name="postcode" id="postcode" value="${addr.postcode}" placeholder="우편번호" required>
		 	<input type="button" value="우편번호 찾기" onclick="findAddr();" required><br>
	 		<input type="text" name="address1" id="address1" value="${addr.address1}" placeholder="주소" required><br>
	 		<input type="text" name="address2" id="address2" value="${addr.address2}" placeholder="상세주소" required>
	 	</td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="수정하기" onclick="check();">
			<a href="customerDeleteAction.cus?id=${customer.c_id}">회원탈퇴</a>
		</td>
	</tr>
</table>
</form>

</section>
</body>
</html>