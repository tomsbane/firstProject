package action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.SelectCustomerService;
import vo.ActionForward;
import vo.CustomerBean;

public class SelectCustomerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward =null;
		
		SelectCustomerService selectCustomerService=new SelectCustomerService();
		String c_id = request.getParameter("c_id").trim();

		ArrayList<CustomerBean> customerList=selectCustomerService.getSelectCustomer(c_id);

		request.setAttribute("customerList", customerList);
		request.setAttribute("showAdmin", "/admin/customerList.jsp");
		
		forward = new ActionForward("admin_template.jsp", false);
		return forward;
	}

}
