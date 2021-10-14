package action.admin;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.AdminRegistService;
import svc.admin.NormalRegistService;
import vo.ActionForward;

public class NormalRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		NormalRegistService normalRegistService = new NormalRegistService();
		String c_id = request.getParameter("c_id");

		boolean isAdminRegistSuccess = normalRegistService.setNormalRegist(c_id);

		if (isAdminRegistSuccess) {
			forward = new ActionForward("customerList.ad", true);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('일반고객 등록 실패')");
			out.println("history.back()");
			out.println("</script>");
		}
		return forward;
	}

}
