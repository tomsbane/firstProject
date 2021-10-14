package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.AdminRegistService;
import vo.ActionForward;
import vo.CustomerBean;

public class AdminRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		AdminRegistService adminRegistService=new AdminRegistService();
		String c_id = request.getParameter("c_id");

		boolean isAdminRegistSuccess=adminRegistService.setAdminRegist(c_id);
		
		if(isAdminRegistSuccess) {
			forward = new ActionForward("customerList.ad", true);
		}else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('어드민 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
