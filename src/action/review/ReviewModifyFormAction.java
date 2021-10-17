package action.review;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.menu.MenuViewService;
import svc.review.ReviewModifyFormService;
import vo.ActionForward;
import vo.Menu;
import vo.Review;

public class ReviewModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		//리뷰수정을 위해 파라미터로 전송된 값들을 얻어와
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String u_id = request.getParameter("u_id");
		String m_id = request.getParameter("m_id");
		
		//session영역에 공유한 u_id를 얻어와 check_u_id변수에 저장하고
		HttpSession session = request.getSession();
		String  check_u_id =(String)session.getAttribute("u_id");
		
		//session영역에 공유한 u_id(check_u_id)와 파라미터로 전송된 u_id가 같지 않으면
		if(check_u_id == null || !check_u_id.equals(u_id)) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alert('본인이 작성한 리뷰만 수정이 가능합니다.')");
			out.println("history.back();");
			out.println("</script>");			
		}else {
			//먼저, 해당 '메뉴정보'를 얻어와 request객체에 공유하고
			MenuViewService menuViewService = new MenuViewService();
			Menu menuInfo = menuViewService.getMenuView(m_id);
			
			//해당 리뷰번호(review_num)로 '원본 리뷰의 정보'를 얻어와 request객체에 공유하고
			ReviewModifyFormService reviewModifyFormService = new ReviewModifyFormService();
			Review reviewInfo = reviewModifyFormService.getReview(review_num);
			
			request.setAttribute("menuInfo", menuInfo);
			request.setAttribute("reviewInfo", reviewInfo);
			
			request.setAttribute("reviewPage", "/review/reviewModifyForm.jsp");		
			
			request.setAttribute("showMenu", "/kiosk/menuView.jsp");
			
			request.setAttribute("m_id", m_id);
			request.setAttribute("u_id", u_id);
			
			forward = new ActionForward("menuTemplate.jsp", false);
		}
		return forward;
	}

}
