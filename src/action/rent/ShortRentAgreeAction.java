package action.rent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import vo.ActionForward;

public class ShortRentAgreeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward("rentAgreeMent.jsp", false);
		String rental_date = request.getParameter("rental_date");
		String rental_time = request.getParameter("rental_time");
		String return_date = request.getParameter("return_date");
		String return_time = request.getParameter("return_time");
		
		return forward;
	}

}
