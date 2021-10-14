package action.rent;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.rent.ShortRentCheckService;
import vo.ActionForward;
import vo.Rentcar;

public class ShortRentCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;

		String rental_date = request.getParameter("rental_date");
		String return_date = request.getParameter("return_date");
		int car_no = Integer.parseInt(request.getParameter("car_no"));

		HttpSession session = request.getSession();
		String c_id = (String) session.getAttribute("c_id");
		
		
		if (c_id == null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 후 이용해주세요.')");
			out.println("history.back();");
			out.println("</script>");
		} else {
			ShortRentCheckService shortRentCheckService = new ShortRentCheckService();

			Rentcar carInfo = shortRentCheckService.getShortRentInfo(car_no);

			request.setAttribute("car_no", car_no);
			request.setAttribute("carInfo", carInfo);
			request.setAttribute("rental_date", rental_date);
			request.setAttribute("return_date", return_date);

			forward = new ActionForward("/rentFirstCheck.jsp", false);

		}

		return forward;
	}

}
