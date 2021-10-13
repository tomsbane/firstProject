package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.admin.CarViewService;
import vo.ActionForward;
import vo.Rentcar;

public class CarViewAction implements action.Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		CarViewService carViewService=new CarViewService();
		int no = Integer.parseInt(request.getParameter("no"));

		Rentcar car=carViewService.getCarView(no);

		request.setAttribute("car", car);
		request.setAttribute("showAdmin", "/admin/carView.jsp");
		
		forward = new ActionForward("admin_template.jsp", false);
		return forward;
	}

}
