package action.customer;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.customer.CustomerJoinService;
import vo.ActionForward;
import vo.Address;
import vo.CustomerBean;

public class CustomerJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		//기본값으로 채워진
		CustomerBean customer = new CustomerBean();
		Address addr = new Address();
		
		//파라미터로 전송된 값들로 다시 채움
		/*------------------------------------*/
		customer.setC_id(request.getParameter("c_id"));
		customer.setC_grade(request.getParameter("c_grade"));//"Normal"
		customer.setC_password(request.getParameter("c_password"));
		customer.setC_name(request.getParameter("c_name"));
		customer.setC_gender(request.getParameter("c_gender"));
		customer.setC_birth(request.getParameter("c_birth"));
		customer.setC_email1(request.getParameter("c_email1"));
		customer.setC_email2(request.getParameter("c_email2"));
		customer.setC_tel(request.getParameter("c_tel"));
		
		/*//달(mm)이 이상하게 변형됨
		Date nowDate = new Date();//오늘 날짜를 생성
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");//오늘 날짜의 형식을 간소화시킴
		String c_joindate = simpleDateFormat.format(nowDate);
		
		tomer.setC_joindate(c_joindate);
		*/
		/*------------------------------------*/
		addr.setPostcode(Integer.parseInt(request.getParameter("postcode")));
		addr.setAddress1(request.getParameter("address1"));
		addr.setAddress2(request.getParameter("address2"));
		
		CustomerJoinService tomerJoinService = new CustomerJoinService();
		boolean isCustomerJoinSuccess = tomerJoinService.customerJoin(customer, addr);
		
		if(isCustomerJoinSuccess == false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패');");
			out.println("history.back();");
			out.println("</script>");
		}else {
			forward = new ActionForward("customerLogin.cus", true);//"로그인 폼 보기" 요청(리다이렉트 방식)
		}
		return forward;
	}

}











