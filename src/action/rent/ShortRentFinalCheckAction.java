package action.rent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.customer.CustomerViewService;
import svc.rent.ShortRentService;
import vo.ActionForward;
import vo.CustomerBean;
import vo.Rentcar;

public class ShortRentFinalCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		//대여-반납일자
		String rental_date = request.getParameter("rental_date");
		String return_date = request.getParameter("return_date");
		
		//대여-반납위치
		String rental_place1 = request.getParameter("rental_place1");
		String rental_place2 = request.getParameter("rental_place2");
		String rental_place3 = request.getParameter("rental_place3");
		String return_place1 = request.getParameter("return_place1");
		String return_place2 = request.getParameter("rental_place2");
		String return_place3 = request.getParameter("rental_place3");
		
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("c_id");
		
		int car_no = Integer.parseInt(request.getParameter("car_no"));
		
		ShortRentService shortRentService = new ShortRentService();

		Rentcar carInfo = shortRentService.getFinalInfo(car_no);
	
		request.setAttribute("rental_place1", rental_place1);
		request.setAttribute("rental_place2", rental_place2);
		request.setAttribute("rental_place3", rental_place3);
		request.setAttribute("return_place1", return_place1);
		request.setAttribute("return_place2", return_place2);
		request.setAttribute("return_place3", return_place3);
		
		request.setAttribute("c_id", c_id);
		request.setAttribute("car_no", car_no);
		request.setAttribute("carInfo", carInfo);
		request.setAttribute("rental_date", rental_date);
		request.setAttribute("return_date", return_date);

		CustomerViewService customerViewService =new CustomerViewService();
		CustomerBean customerInfo=customerViewService.getCustomer(c_id);
		request.setAttribute("customer", customerInfo);
		
		request.setAttribute("showRent", "/rent/rentFinalCheck.jsp");
		
		forward = new ActionForward("rent_template.jsp", false);

		return forward;
	}

}
