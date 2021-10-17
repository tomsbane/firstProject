package action.review;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.menu.MenuViewService;
import svc.review.ReviewListService;
import svc.review.ReviewModifyService;
import vo.ActionForward;
import vo.Menu;
import vo.Review;

public class ReviewModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		//hidden:파라미터로 전송된  review_num를 int로 변환
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		//hidden:파라미터로 전송된  u_id를 얻어오고
		String c_id = request.getParameter("c_id");
		//hidden:파라미터로 전송된  m_id를 얻어오고
		String car_no = request.getParameter("car_no");
		
		//파라미터로 전송된 평점(rating)을 int로 변환
		int rating = Integer.parseInt(request.getParameter("rating"));
		//파라미터로 전송된 한줄평(text)을 얻어옴
		String text = request.getParameter("text");
		
		if(rating == 0) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('평점을 선택해주세요.')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			//해당 메뉴에 사용자가 작성한 리뷰를 DB에 등록
			ReviewModifyService reviewModifyService=new ReviewModifyService();
			boolean isModifyReviewSuccess = reviewModifyService.modifyReview(review_num, u_id, m_id, rating, text);
			
			if(!isModifyReviewSuccess) {//리뷰수정 실패하면
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('리뷰수정실패')");
				out.println("history.back();");
				out.println("</script>");
			}else {//리뷰수정 성공하면
				//'수정된 해당 메뉴의 정보'를 얻어와  request객체에 공유하고
				MenuViewService menuViewService = new MenuViewService();
				Menu menuInfo = menuViewService.getMenuView(m_id);
				request.setAttribute("menuInfo", menuInfo);
				
				//작성된 리뷰들(사용자가 방금 수정한 리뷰를 포함한)을 얻어와  request객체에 공유하고
				ReviewListService reviewListService	= new ReviewListService();
				ArrayList<Review> reviewList = reviewListService.getReviewList(m_id);
				request.setAttribute("reviewList", reviewList);
				
				request.setAttribute("reviewPage", "/review/reviewTemplate.jsp");
				request.setAttribute("showReview", "/review/showReview.jsp");
				
				request.setAttribute("showMenu", "/kiosk/menuView.jsp");
				
				request.setAttribute("m_id", m_id);
				request.setAttribute("u_id", u_id);
				
				forward = new ActionForward("menuTemplate.jsp", false);
				
			}			
		}
		
		return forward;
	}

}
