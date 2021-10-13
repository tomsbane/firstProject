package action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.rent.ShortRentListService;
import vo.ActionForward;
import vo.Rentcar;

public class ReserveCarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ShortRentListService shortRentListService  = new ShortRentListService(); 
		
		ArrayList<Rentcar> carList = shortRentListService.getCarList();
		
		request.setAttribute("carList", carList);
		
		forward =new ActionForward("admin/carInsert.jsp", false);
		
		return forward;
	}

}
