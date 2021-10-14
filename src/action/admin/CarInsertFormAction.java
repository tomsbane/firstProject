package action.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import action.Action;
import vo.ActionForward;

public class CarInsertFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		request.setAttribute("showAdmin", "/admin/carInsertForm.jsp");
		forward = new ActionForward("admin_template.jsp", false);
		
		return forward;
	}

}
