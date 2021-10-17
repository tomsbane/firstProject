package action.review;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.review.ReviewDeleteService;
import svc.review.ReviewListService;

import vo.ActionForward;
import vo.Review;

public class ReviewDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		//리뷰삭제를 위해 파라미터로 전송된 값들을 얻어와
		int review_num = Integer.parseInt(request.getParameter("review_num"));
		String c_id = request.getParameter("c_id");
		String car_no = request.getParameter("car_no");
		
		//session영역에 공유한 u_id를 얻어와 check_u_id변수에 저장하고
		HttpSession session = request.getSession();
		String  check_c_id =(String)session.getAttribute("c_id");
		
		//session영역에 공유한 u_id(check_u_id)와 파라미터로 전송된 u_id가 같지 않으면
		if(check_c_id == null || !check_c_id.equals(c_id)) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out =response.getWriter();
			out.println("<script>");
			out.println("alert('본인이 작성한 리뷰만 삭제가 가능합니다.')");
			out.println("history.back();");
			out.println("</script>");			
		}else {
			//해당 메뉴에 대한 사용자가 작성한 리뷰를 DB에서 삭제
			ReviewDeleteService reviewDeleteService=new ReviewDeleteService();
			boolean isDeleteReviewSuccess = reviewDeleteService.deleteReview(review_num, c_id);
			
			if(!isDeleteReviewSuccess) {//리뷰삭제 실패하면
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('리뷰삭제실패')");
				out.println("history.back();");
				out.println("</script>");
			}else {//리뷰삭제 성공하면
				
				//작성된 리뷰들(사용자가 방금 삭제한 리뷰는 제외된)을 다시 얻어와  request객체에 공유하고
				ReviewListService reviewListService = new ReviewListService();
				ArrayList<Review> reviewList = reviewListService.getReviewList();
				request.setAttribute("reviewList", reviewList);
				
				request.setAttribute("reviewPage", "/review/reviewTemplate.jsp");
				request.setAttribute("showReview", "/review/showReview.jsp");
				
				request.setAttribute("showMenu", "/kiosk/menuView.jsp");
				
				request.setAttribute("c_id", c_id);
				request.setAttribute("car_no", car_no);
				
				forward = new ActionForward("menuTemplate.jsp", false);
			}
		}
		return forward;
	}

}
