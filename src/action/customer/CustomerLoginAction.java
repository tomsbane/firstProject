package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.customer.CustomerLoginService;
import vo.ActionForward;
import vo.CustomerBean;

public class CustomerLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//로그인 페이지에서 파라미터로 전송된 id와 비번값을 저장할 CustomerBean객체를 생성
		CustomerBean customer = new CustomerBean();
		String c_id =request.getParameter("c_id");
		String c_password =request.getParameter("c_password");
		
		String remember =request.getParameter("remember");//아이디 저장 체크 여부를 확인->쿠키객체 생성여부에 사용함
		
		customer.setC_id(c_id);
		customer.setC_password(c_password);
		
		//로그인 처리를 위한 Service 객체를 생성하여
		CustomerLoginService customerLoginService = new CustomerLoginService();
		//로그인 요청을 처리하는 login()호출(이 때, 매개값:로그인 정보가 저장된 CustomerBean객체)
		boolean isloginSuccess = customerLoginService.login(customer);
		
		CustomerBean customerInfo = null;
		//로그인에 실패하면 경고창을 띄운 후 다시 '로그인 폼 보기'요청
		if(!isloginSuccess) {//if(isloginResult == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디나 비밀번호가 일치하지 않습니다.');");
			out.println("location.href='customerLogin.usr';");
			out.println("</script>");
		}else {//로그인에 성공하면=회원가입이 되어있으면서 id와 pw를 정확히 입력했으면
			//★★입력한 id로 "회원정보"를 가져와(가져오는 이유? 정보를 session영역에 공유하기 위해)
			//CustomerBean customerInfo = customerLoginService.getCustomerInfo(c_id);
			customerInfo = customerLoginService.getCustomerInfo(c_id);
			
			if(customerInfo == null) {//만약 null이면
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('존재하지 않는 계정입니다.');");
				out.println("location.href='customerLogin.usr';");//다시 '로그인 폼 보기' 요청
				out.println("</script>");
			}else {//null이 아니면(회원이면)
				/* Cookie와 Session은 둘다 로그인 정보 유지하기 위한 방법
				 * 차이점 : 서버에서 생성한 Cookie는 클라이언트에서 관리(보안에 취약)
				 *       Session은 서버에서 생성하고 관리				 
				 */
				Cookie cookie = new Cookie("c_id", c_id);//id로 쿠키 객체 생성
				//쿠키를 생성하면 기본 생존기간이 -1이다.
				//브라우저가 실행중일 때는 쿠키가 생존하지만 브라우저를 닫으면 쿠키가 사라짐
				//cookie.setMaxAge(60 * 60 * 24);//단위는 초임(24시간)
				System.out.println("Cookie객체 생성");
				if(remember != null) {//'아이디 저장'에 체크했으면
					response.addCookie(cookie);//response객체에 추가하여 클라이언트쪽을 보냄					
				}else {//'아이디 저장'에 체크해제되어 있으면 "쿠키 유효시간을 0으로 해서" response객체에 추가하여 클라이언트쪽을 보냄
					cookie.setMaxAge(0);//쿠키즉시삭제(쿠키는 삭제메서드를 제공하지 않음)
					//cookie.setMaxAge(-1); 세션이 끝나면 삭제(즉, 세션유효범위인 "브라우저 종료할 때 삭제됨")
					response.addCookie(cookie);
				}	
				/*------------------------------------------------------*/
				//"지난달 구매금액을 조회하여 사용자 등급 변경"한 사용자정보를 다시 리턴받아
				//GradeService gradeService = new GradeService();
				//gradeService.updateGrade(customerInfo);
				
				//customerInfo = GradeService.updateGrade(customerInfo);//static 메서드:클래스이름으로 접근하여 바로 호출가능
				
				/* 실제로 포털 사이트들이 로그인을 유지할 수 있는 것은 세션객체에 id를 저장하면서 다른 페이지를 실행할 때마다 
				 * 세션 객체 내에 있는 id를 매번 확인하기 때문이다. 
				 * 확인할 때 세션 객체에 id가 존재하지 않을 경우는 로그인되지 않은 상태이거나 로그아웃된 상태로 간주하게 된다.
				 */
				//세션 영역에 속성으로 저장하기 위해 Session객체를 생성					
				HttpSession session = request.getSession();
				//로그인에 성공한 사용자의 c_id,c_password,c_grade,c_name의 이름으로 각 속성값을 공유하여 
				session.setAttribute("c_id", c_id);
				session.setAttribute("c_password", c_password);
				
				//grade를 세션영역에 추가하는 이유?향후에 등급을 보고 세일정도를 결정하므로(예:Normal:2%, Vip:5%) 
				session.setAttribute("c_grade", customerInfo.getC_grade());
				session.setAttribute("c_name", customerInfo.getC_name());
			
				session.setMaxInactiveInterval(60*60*1);//세션 수명시간을 1시간으로 설정(3600초=1시간)
			
				//리다이렉트 방식으로 customerMain.jsp로 포워딩
			   forward = new ActionForward("/index.jsp", false);				
			}		
			
		}
		return forward;
	}

}






