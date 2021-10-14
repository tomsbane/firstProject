package action.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.admin.CustomerListService;
import vo.ActionForward;
import vo.CustomerBean;

public class CustomerListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		CustomerListService customerListService  = new CustomerListService(); 
		
		ArrayList<CustomerBean> customerList = customerListService.getCustomerList();
		
		request.setAttribute("customerList", customerList);
		request.setAttribute("showAdmin", "/admin/customerList.jsp");
		
		forward = new ActionForward("admin_template.jsp", false);//---▶이 파일 소스 작성하기
		
		return forward;
	}

}
