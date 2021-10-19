package action.admin;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.admin.DayOrderListService;
import svc.admin.TotalOrderListService;
import vo.ActionForward;
import vo.Order;

public class TotalOrderManageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;		
		
		/*'전체주문관리' 요청은 Admin만 할 수 있도록 session영역의 등급(admin_grade)를 얻어와 확인 */
		HttpSession session = request.getSession();
		String admin_grade =(String)session.getAttribute("admin_grade");
		if(admin_grade == null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인해주세요.');");
			out.println("location.href='adminMain.jsp'");
			out.println("</script>");
		}else {
			//관리자모드 : 전체 주문관리 -고객들이 전체 주문한 리스트를 조회하여 반환받아 request객체에 속성값으로 공유한 후 
			TotalOrderListService totalOrderListService = new TotalOrderListService();
			ArrayList<Order> totalOrderList = totalOrderListService.getTotalOrderList();//전체 주문한 리스트를 얻어와
			
			request.setAttribute("totalOrderList", totalOrderList);//"전체 주문한 리스트"
						
			request.setAttribute("showAdmin", "/admin/totalOrderList.jsp");
			forward = new ActionForward("adminMain.jsp", false);//디스패치 방식으로 포워딩
		}
		return forward;
	}

}
