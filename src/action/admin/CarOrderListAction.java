package action.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.admin.CarOrderListService;
import vo.ActionForward;
import vo.Order;

public class CarOrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		Date today = new Date();
		/***** 원하는 데이터 포맷 지정하여 반환하는 방법*****************
		 *  ★★주의:월은 MM대문자로, mm :시간의 분을 나타냄
		 * HH:24시간, hh:12시간 기준으로 am/pm을 추가로 표기 가능
		 * (예)yyyy.MM.dd. HH:mm:ss -> 2021.10.15 13:52:27
		 ***************************************************/
		/*'실시간주문관리' 요청은 Admin만 할 수 있도록 session영역의 등급(admin_grade)를 얻어와 확인 */
		HttpSession session = request.getSession();
		String c_grade =(String)session.getAttribute("c_grade");
		if(!c_grade.equals("admin")) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인해주세요.');");
			out.println("location.href='customerLogin.cus'");
			out.println("</script>");
		}else {
		    
			CarOrderListService carOrderListService = new CarOrderListService();
			ArrayList<Order> carOrderList = carOrderListService.getCarOrderList();//오늘 날짜로 주문한 리스트를 얻어와
			
			request.setAttribute("carOrderList", carOrderList);//"오늘 주문한 리스트" 이므로
			session.setMaxInactiveInterval(60*60*12);//12시간으로 시간 설정함			
			
			request.setAttribute("showAdmin", "/admin/carOrderList.jsp");
			forward = new ActionForward("admin_template.jsp", false);
		}
		return forward;
	}

}
