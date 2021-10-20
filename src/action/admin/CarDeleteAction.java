package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.CarDeleteService;
import vo.ActionForward;

public class CarDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		int car_no = Integer.parseInt(request.getParameter("car_no"));
		
		CarDeleteService carDeleteService=new CarDeleteService();
		boolean isDeleteCarSuccess = carDeleteService.deleteCar(car_no);
		
		if(!isDeleteCarSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('차량 삭제 실패')");
			out.println("history.back()");
			out.println("</script>");
		}else {
			forward = new ActionForward("adminCarList.ad", true);
		}
		
		return forward;
	}

}
