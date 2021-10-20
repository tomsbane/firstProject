package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.CarOrderDetailService;
import vo.ActionForward;

public class CarOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int order_no = Integer.parseInt(request.getParameter("order_no"));
		String c_id = request.getParameter("c_id");
		
		CarOrderDetailService carOrderDetailService = new CarOrderDetailService();
		carOrderDetailService.getOrderDetail(order_no);
		
		return forward;
	}

}
