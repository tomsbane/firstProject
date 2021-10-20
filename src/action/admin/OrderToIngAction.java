package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.OrderToIngService;
import vo.ActionForward;
import vo.Order;

public class OrderToIngAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		
		System.out.println(order_no);
		
		OrderToIngService orderToIngService = new OrderToIngService();
		boolean isOrderChanged = orderToIngService.changeOrderToIng(order_no);
		
		
		if(!isOrderChanged) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('주문 승인이 실패했습니다. 다시 시도해주세요.');");
			out.println("location.reload();");
			out.println("</script>");
		}else {
			forward = new ActionForward("carOrderList.ad", true);
		}
		
		return forward;
	}

}
