package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.ReserveChangeService;
import vo.ActionForward;

public class ReserveChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		ReserveChangeService reserveChangeService = new ReserveChangeService();
		int car_no = Integer.parseInt(request.getParameter("car_no"));
		String car_reserve = request.getParameter("car_reserve");

		boolean isReserveChangeSuccess = reserveChangeService.setreserveCar(car_no, car_reserve);

		if (isReserveChangeSuccess) {
			forward = new ActionForward("adminCarList.ad", true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('전환 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
