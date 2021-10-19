<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.sql.* ,javax.sql.*, javax.naming.*"%>

<%
	String openInit="false";
	if(request.getParameter("openInit") != null){
		openInit=request.getParameter("openInit");//openInit=true가 됨
	}
%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>ID 찾기</title>
</head>

<script>
function init(){
	if(<%=openInit%>){//openInit가 true이면
		//opener인 부모 창의 c_id의 입력된 값을 가져와 아래 idCheck에 그대로 셋팅한다.
		document.getElementById("idCheck").value=opener.document.getElementById("c_id").value;
	}
}
//★★사용가능한 chk_id값을 회원가입창(=부모창=opener)의 아이디 입력란("c_id")의 값으로 그대로 셋팅
function useId(chk_id){
	//부모창에 접근하려면 opener를 사용해서 접근해야 한다.
	//opener.chkId = true;
	//opener.idcheck = chk_id.trim();
	opener.document.getElementById("c_id").value = chk_id;
	window.close();
}
</script>

<!--[아이디중복확인]창이 열리면 onload이벤트에 의해 init()함수가 호출된다.-->
<body onload="init()">
<form name="f">
<input type="submit" name="findChk" onclick="findChk();" value="email">&nbsp;&nbsp;<input type="button" name="findChk" value="휴대폰 번호로 찾기">
</form>

	<div style="display:hidden;">
	<form action="idFindProcess.jsp" method="post" name="f" >
		가입하신 이메일을 입력해주세요.
		<input type="text" name="email1Check" id="idCheck">@
		<input type="text" name="email2Check" id="idCheck">&nbsp;&nbsp;
		<input type="submit" value="찾기" id="submit">
	</form>
	</div>
	<form action="idFindProcess.jsp" method="post" name="f">
		가입하신 이메일을 입력해주세요.
		<input type="text" name="email1Check" id="idCheck">@
		<input type="text" name="email2Check" id="idCheck">&nbsp;&nbsp;
		<input type="submit" value="찾기" id="submit">
	</form>
</body>
</html>

<%
//★★idCheckProcess.jsp로 처리하고 다시 돌아온 후이므로 "html"밑에 써야 함

request.setCharacterEncoding("utf-8");
//idCheckProcess.jsp에서 파라미터로 전송한 chk_id와 useable
if(request.getParameter("chk_id")!=null && !request.getParameter("chk_id").trim().equals("")){
	String chk_id = request.getParameter("chk_id");
	String useable = request.getParameter("useable");
	/*처음으로 [아이디중복확인]창이 떴을때는 수평선이 없음.
	이후, idCheckProcess.jsp로 처리하고 다시 돌아온 후 수평선 나타남*/
	out.println("<hr/>");
	
	if(useable.equals("NO")){
		out.println("<h4>"+ chk_id +"는(은) 사용 불가능한 아이디입니다. 다시 시도하세요.</h4>");
		
	}else{
		out.println("<h4>"+ chk_id +"는(은) 사용가능한 아이디입니다.");
		//★★사용가능한 chk_id값을 회원가입창(=부모창=opener)의 아이디 입력란("c_id")의 값으로 그대로 셋팅
		out.println("<a href ='javascript:useId(\""+chk_id+"\")'>사용하기</a></h4>");
	}
}

%>
