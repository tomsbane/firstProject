package action.customer;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.customer.CustomerJoinService;
import svc.customer.CustomerModifyService;
import vo.ActionForward;
import vo.Address;
import vo.CustomerBean;

public class CustomerModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//둘 중 하나의 방법 사용하여 id값 가져오면 됨
		//1.session영역에 공유된 id값을 가져와
		HttpSession session = request.getSession();
		String c_id = (String)session.getAttribute("c_id");
		//2.파라미터로 전송된 id값을 가져와
		//String c_id = request.getParameter("c_id");
		
		//기본값으로 채워진
		CustomerBean customer = new CustomerBean();
		Address addr = new Address();
		
		//파라미터로 전송된 값들로 다시 채움
		/*------------------------------------*/
		//customer.setC_id(request.getParameter("c_id"));
		customer.setC_id(c_id);	
		customer.setC_name(request.getParameter("c_name"));
		customer.setC_email1(request.getParameter("c_email1"));
		customer.setC_email2(request.getParameter("c_email2"));
		customer.setC_tel(request.getParameter("c_tel"));
		
		/*//달(mm)이 이상하게 변형됨
		Date nowDate = new Date();//오늘 날짜를 생성
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");//오늘 날짜의 형식을 간소화시킴
		String c_joindate = simpleDateFormat.format(nowDate);
		
		customer.setC_joindate(c_joindate);
		*/
		/*------------------------------------*/
		addr.setC_id(c_id);//★추가-address_table에서 해당 id를 찾아서 수정해야 하므로 
		addr.setPostcode(Integer.parseInt(request.getParameter("postcode")));
		addr.setAddress1(request.getParameter("address1"));
		addr.setAddress2(request.getParameter("address2"));
		
		CustomerModifyService customerModifyService = new CustomerModifyService();
		boolean isCustomerModifySuccess = customerModifyService.customerModify(customer, addr);
		
		if(isCustomerModifySuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원정보 수정에 실패했습니다. 다시 시도해주세요.');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			request.setAttribute("showPage", "customer/customerModifyComplete.jsp");
			forward = new ActionForward("customer.jsp", false);//요청(리다이렉트 방식)
		}
		return forward;
	}

}
