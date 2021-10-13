package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.customer.CustomerViewService;
import vo.ActionForward;
import vo.Address;
import vo.CustomerBean;

public class CustomerViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		/*현재 사용자가 로그인된 상태인지를 알아보기 위해서 session객체를 얻어옴
		 * session객체에 공유되어 있는 id속성값이 null이 아니면 로그인된 상태이다.		 *  
		 */
		HttpSession session = request.getSession();
		String viewId = (String)session.getAttribute("c_id");
		
		if(viewId == null) {//현재 로그인된 상태가 아니면
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다.');");
			out.println("location.href='customerLogin.cus';");
			out.println("</script>");
		}else {
			/*session영역에 공유된 id를 사용하여
			 * 해당 id의 사용자정보와 주소정보를 얻어온 후
			 * request객체에 속성값으로 공유시킴
			 */
			CustomerViewService customerViewService = new CustomerViewService();
			CustomerBean customerInfo = customerViewService.getCustomer(viewId);
			Address customerAddrInfo = customerViewService.getAddress(viewId);
			
			request.setAttribute("customer", customerInfo);
			request.setAttribute("addr", customerAddrInfo);
			request.setAttribute("showPage", "customer/customerView.jsp");
			
			forward = new ActionForward("customer.jsp", false);//디스패치 방식으로 포워딩
		}
		return forward;
	}

}





