package action.rent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.rent.ShortRentCheckService;
import vo.ActionForward;
import vo.Rentcar;

public class ShortRentCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		String rental_date = request.getParameter("rental_date");
		String return_date = request.getParameter("return_date");
		int car_no = Integer.parseInt(request.getParameter("car_no"));
		ShortRentCheckService shortRentCheckService = new ShortRentCheckService();

		Rentcar carInfo = shortRentCheckService.getShortRentInfo(car_no);

		request.setAttribute("carInfo", carInfo);
		request.setAttribute("rental_date", rental_date);
		request.setAttribute("return_date", return_date);

		forward = new ActionForward("/rentFirstCheck.jsp", false);

		return forward;
	}

}
