package action.rent;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.admin.ReserveChangeService;
import svc.rent.SelectReserveService;
import svc.rent.ShortRentService;
import vo.ActionForward;
import vo.Driver_detail;
import vo.Order;
import vo.Rentcar;

public class ShortRentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("c_id");
		
		
		
	
		Driver_detail driver = new Driver_detail();
		
		driver.setC_id(c_id);
		driver.setC_name(request.getParameter("c_name"));
		driver.setC_birth((request.getParameter("c_birth")));
		driver.setC_tel(Integer.parseInt(request.getParameter("c_tel")));
		driver.setRental_place1(request.getParameter("rental_place1"));
		driver.setRental_place2(request.getParameter("rental_place2"));
		driver.setRental_place3(request.getParameter("rental_place3"));
		driver.setReturn_place1(request.getParameter("return_place1"));
		driver.setReturn_place2(request.getParameter("return_place2"));
		driver.setReturn_place3(request.getParameter("return_place3"));
		driver.setRequest(request.getParameter("request"));
		
		Order order = new Order();
	
		order.setC_id(c_id);
		order.setCar_no(Integer.parseInt(request.getParameter("car_no")));
		order.setRental_date(request.getParameter("rental_date"));
		order.setReturn_date(request.getParameter("return_date"));
		order.setRental_price(Integer.parseInt(request.getParameter("rental_price")));
		
		int car_no=order.getCar_no();
		
		SelectReserveService selectReserveService=new SelectReserveService();
		String car_reserve=selectReserveService.getReserve(car_no);
		
		if(car_reserve.equals("n")) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('이미 예약중인 상품입니다. 다른 상품을 선택해주세요.');");
			out.println("location.href='shortRentList.do'");
			out.println("</script>");
		}else {
			ReserveChangeService reserveChangeService = new ReserveChangeService();
			boolean isReserveChangeSuccess = reserveChangeService.setreserveCar(car_no, car_reserve);
			if (isReserveChangeSuccess) {
				
			}
			ShortRentService shortRentService = new ShortRentService();
			boolean isAllInsertSuccess =  shortRentService.insertOrder(driver, order);

			if(isAllInsertSuccess == false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('예약 실패');");
				out.println("location.href='shortRentList.do'");
				out.println("</script>");
			}else {
				forward = new ActionForward("shortRentList.do", false);
			}
		}
		
		return forward;
		
	}

}
