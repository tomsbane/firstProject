<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored = "false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   

<%@ page import="java.util.Calendar" %>  

<%
Calendar cal = Calendar.getInstance();

String yearR = request.getParameter("year");//2021
String monthR = request.getParameter("month");//10

//값을 파라미터로 전송받지 않았을 때, 기본값
int year = cal.get(Calendar.YEAR);
int month = cal.get(Calendar.MONTH);

if(yearR != null && monthR != null && !yearR.equals("") && !monthR.equals("")){
	year = Integer.parseInt(yearR);
	month = Integer.parseInt(monthR)-1;//★★중요:Calendar의 월 0~11이므로
}

//달력 시작 시점 지정, 세팅한 시점부터 값을 결정
cal.set(year, month, 1);//리턴 타입이 void이므로
//Calendar.DAY_OF_WEEK :그 달의 시작일의 요일을 알 수 있다.(1~7 사이의 정수 리턴:1일~7토)
int dayOfweek = cal.get(Calendar.DAY_OF_WEEK);//월 시작일을 정수로 리턴(2021년 10월 1일이 금요일이면 6을 리턴)
//달력 마지막 날 지정(예: 30 또는 31 리턴함)
int lastday = cal.getActualMaximum(Calendar.DATE);//2021년 10월이라면 31일 리턴

//*----------------------------------------*/
//◀ 이전버튼 
int before_year = year;
int before_month = month;//★★주의 : 월 0~11
if(before_month == 0){//◀ 이전버튼 클릭하여 월이 0이 되면
	before_year = before_year-1;//년도를 1감소
	before_month = 12;//12월로
}
//▶ 다음버튼
int after_year = year;
int after_month = month+2;//★★주의 :월 0~11->2를 더하여 월2~13으로 만듦
if(after_month == 13){//▶ 다음버튼 클릭하여 월이 13이 되면
	after_year = after_year+1;//년도를 1증가
	after_month = 1;//1월로
}
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#totalSales{
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
</head>
<script type="text/javascript">
function deleteCar(car_no){
	if(confirm("정말로 삭제하시겠습니까?\n삭제 후 다시 복원되지 않습니다") == true){
		location.href="carDelete.ad?car_no="+car_no;
	}else{
		return false;
	}
}
</script>
<body>
<header id="totalSales">

</header>
<section id="totalSales">
		<button type="button" onclick="location.href='./totalSalesList.ad?year=<%=before_year%>&month=<%=before_month%>'">◀</button>
		<%=year%>년  <%=month+1%>월
		<button type="button" onclick="location.href='./totalSalesList.ad?year=<%=after_year%>&month=<%=after_month%>'">▶</button>
	<h2>전제 주문 내역</h2>
	<table>
	<c:choose>
	<c:when test="${carOrderList ne null && carOrderList.size() > 0}">
		<c:forEach var="order" items="${carOrderList}" varStatus="status">
		<tr>
			<td style="background-color: yellow;"><b>주문번호 :</b>${order.order_no}	&nbsp;&nbsp;
			</td>
			<td>
			<b>결제금액 :</b>${order.rental_price}</td>
			<td>&nbsp;&nbsp;<b>결제상태 :</b>			
				<c:if test="${order.order_status eq 'get'}">
					대기
				</c:if>
				<c:if test="${order.order_status eq 'ing'}">
					진행중
				</c:if>
				<c:if test="${order.order_status eq 'cancel'}">
					취소
				</c:if>
				<c:if test="${order.order_status eq 'done'}">
					승인
				</c:if>
			</td>
		<tr>
		</c:forEach>
		<c:if test="${carTotalMoney ne null}">
			<tr>
				<td colspan="3"><b><%=month+1%>달의 총 결제금액은 </b>${carTotalMoney}원 입니다.</td>
			</tr>
			<tr>
				<td colspan="3"><%=month+1%>달의 총 예약건수: 00건[대기:0, 진행:0, 취소:0, 승인:0]</td>
			</tr>
		</c:if>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${param.year eq null}">
			<a href="./totalSalesList.ad?year=<%=year%>&month=<%=month+1%>"><button>조회하기</button></a> 버튼을 클릭해주세요
			</c:when>
			<c:otherwise>
			<%=month+1%>달 주문내역이 없습니다.
			</c:otherwise>
		</c:choose>
	</c:otherwise>
	</c:choose>
	
	</table>
	
</section>
</body>
</html>


