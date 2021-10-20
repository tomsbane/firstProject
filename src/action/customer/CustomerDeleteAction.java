package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.customer.CustomerDeleteService;
import svc.customer.CustomerJoinService;
import svc.customer.CustomerModifyService;
import vo.ActionForward;
import vo.Address;
import vo.CustomerBean;

public class CustomerDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//둘 중 하나의 방법 사용하여 id값 가져오면 됨
		//1.session영역에 공유된 id값을 가져와
		HttpSession session = request.getSession();
		String c_id = (String)session.getAttribute("c_id");
		//2.파라미터로 전송된 id값을 가져와
		//String c_id = request.getParameter("c_id");
		
		CustomerDeleteService customerDeleteService = new CustomerDeleteService();
		boolean iscustomerDeleteSuccess = customerDeleteService.customerDelete(c_id);
		
		if(iscustomerDeleteSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보 삭제에 실패했습니다. 다시 시도해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			request.setAttribute("showPage", "customer/customerDeleteComplete.jsp");
			forward = new ActionForward("index.jsp", false);//요청(리다이렉트 방식)
		}
		return forward;
	}

}
