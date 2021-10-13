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

		String rental_date = request.getParameter("rental_date");
		String return_date = request.getParameter("return_date");
		
		String rental_place2 = request.getParameter("rental_place2");
		String rental_place3 = request.getParameter("rental_place3");
		String return_place2 = request.getParameter("rental_place2");
		String return_place3 = request.getParameter("rental_place3");
		
		int car_no = Integer.parseInt(request.getParameter("car_no"));
		
		ShortRentReserveService shortRentReserveService = new ShortRentReserveService();

		Rentcar carInfo = shortRentReserveService.getFinalInfo(car_no);
	
		
		request.setAttribute("rental_place2", rental_place2);
		request.setAttribute("rental_place3", rental_place3);
		request.setAttribute("return_place2", return_place2);
		request.setAttribute("return_place3", return_place3);
		request.setAttribute("carInfo", carInfo);
		request.setAttribute("rental_date", rental_date);
		request.setAttribute("return_date", return_date);

		forward = new ActionForward("/rentFinalCheck.jsp", false);

		return forward;
	}

}
