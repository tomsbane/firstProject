package action.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.admin.CarOrderListService;
import svc.admin.CarTotalPriceService;
import vo.ActionForward;
import vo.Order;

public class TotalSalesListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String year=request.getParameter("year");
		String month=request.getParameter("month");
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
			ArrayList<Order> carOrderList = carOrderListService.getCarTotalOrderList(year, month);//오늘 날짜로 주문한 리스트를 얻어와
			CarTotalPriceService carTotalPriceService=new CarTotalPriceService();
			String carTotalMoney =carTotalPriceService.getcarTotalPrice(year, month);
			request.setAttribute("carOrderList", carOrderList);//"오늘 주문한 리스트" 이므로
			request.setAttribute("carTotalMoney", carTotalMoney);
			session.setMaxInactiveInterval(60*60*12);//12시간으로 시간 설정함			
			
			request.setAttribute("showAdmin", "/admin/totalSales.jsp");
			forward = new ActionForward("admin_template.jsp", false);
		}
		return forward;
	}

}
