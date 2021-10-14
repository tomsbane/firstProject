package action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.CarListService;
import vo.ActionForward;
import vo.Rentcar;

public class ReserveCarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		CarListService carListService  = new CarListService(); 
		
		ArrayList<Rentcar> reservecarList = carListService.getCarList();
		
		request.setAttribute("reservecarList", reservecarList);
		request.setAttribute("showAdmin", "/admin/reserveCar.jsp");
		forward =new ActionForward("admin_template.jsp", false);
		
		return forward;
	}

}
