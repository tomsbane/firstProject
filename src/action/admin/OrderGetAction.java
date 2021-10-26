package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.GetCarNoService;
import svc.admin.OrderToDoneService;
import svc.admin.OrderToFreshService;
import svc.admin.ReserveChangeService;
import svc.rent.SelectReserveService;
import vo.ActionForward;

public class OrderGetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		GetCarNoService getCarNoService = new GetCarNoService();
		int order_no = Integer.parseInt(request.getParameter("order_no"));

		int car_no = getCarNoService.getCarNo(order_no);
		SelectReserveService selectReserveService=new SelectReserveService();
		String car_reserve=selectReserveService.getReserve(car_no);
		
		OrderToFreshService orderToFreshService = new OrderToFreshService();
		boolean modifyOrderStatus = orderToFreshService.modifyOrderToFresh(order_no);
		if(!modifyOrderStatus) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('배차종료가 실패했습니다. 다시 시도해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}
		if(car_reserve.equals("n")) {
			ReserveChangeService reserveChangeService = new ReserveChangeService();
			boolean isReserveChangeSuccess=reserveChangeService.setreserveCar(car_no, car_reserve);
			if (!isReserveChangeSuccess) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('배차종료가 실패했습니다. 다시 시도해주세요.');");
				out.println("history.back();");
				out.println("</script>");
			}
		}else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('배차종료가 실패했습니다. 다시 시도해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}
			forward = new ActionForward("carOrderList.ad", false);
		
		return forward;
	}

}
