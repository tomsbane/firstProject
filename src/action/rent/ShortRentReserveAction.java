package action.rent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.rent.ShortRentReserveService;
import vo.ActionForward;
import vo.Rentcar;

public class ShortRentReserveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();

		String rental_date = request.getParameter("rentel_date");
		String return_date = request.getParameter("return_date");

		ShortRentReserveService shortRentReserveService = new ShortRentReserveService();

		int car_no = Integer.parseInt(request.getParameter("car_no"));
		Rentcar carInfo = shortRentReserveService.getFinalInfo(car_no);

		request.setAttribute("carInfo", carInfo);
		request.setAttribute("rental_date", rental_date);
		request.setAttribute("return_date", return_date);

		forward = new ActionForward("rentFinalCheck.jsp", false);

		return forward;
	}

}
