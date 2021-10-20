package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.OrderToDoneService;
import svc.admin.OrderToIngService;
import vo.ActionForward;

public class OrderToDoneAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		
		System.out.println("asd"+order_no);
		OrderToDoneService orderToDoneService = new OrderToDoneService();
		boolean modifyOrderStatus = orderToDoneService.modifyOrderToDone(order_no);
		
		
		if(!modifyOrderStatus) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('요청이 실패했습니다. 다시 시도해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("carOrderList.ad", true);
		}
		
		return forward;
	}

}
