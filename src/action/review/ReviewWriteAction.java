package action.review;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import action.Action;
import svc.rent.ShortRentCheckService;
import svc.rent.ShortRentListService;
import svc.review.ReviewListService;
import vo.ActionForward;
import vo.Rentcar;
import vo.Review;

public class ReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		
		
		//파라미터로 전송된 리뷰 대상인 car_no와 car_name 얻어오고

		String car_name = request.getParameter("car_name");
		
		//파라미터로 전송된 평점(rating)을 int로 변환
		int rating = Integer.parseInt(request.getParameter("rating"));
		
		//파라미터로 전송된 한줄평(text)을 얻어옴
		String text = request.getParameter("text");
		
		//로그인이 된 상태에서 리뷰를 달 수 있도록 아래와 같이 조취함
		HttpSession session=request.getSession();
		String c_id = (String)session.getAttribute("c_id");
		String c_name = (String) session.getAttribute("c_name");
		
		if(c_id==null) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('리뷰 작성은 로그인 후 이용할 수 있습니다.')");
			out.println("history.back();");
			out.println("</script>");
		}else {
			if(rating == 0) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out=response.getWriter();
				out.println("<script>");
				out.println("alert('평점을 선택해주세요.')");
				out.println("history.back();");
				out.println("</script>");
			}else {
				//해당 메뉴에 사용자가 작성한 리뷰를 DB에 등록
				ReviewListService reviewListService=new ReviewListService();
				boolean isWriteReviewSuccess = reviewListService.writeReview(c_id, c_name, car_no, car_name, rating, text);
				
				if(!isWriteReviewSuccess) {//리뷰등록 실패하면
					response.setContentType("text/html; charset=utf-8");
					PrintWriter out=response.getWriter();
					out.println("<script>");
					out.println("alert('리뷰등록실패')");
					out.println("history.back();");
					out.println("</script>");
				}else {//리뷰등록 성공하면
					//해당 메뉴의 정보를 얻어와  request객체에 공유하고
					ShortRentCheckService shortRentCheckService = new ShortRentCheckService();
					Rentcar carInfo = shortRentCheckService.getShortRentInfo(car_no);
					request.setAttribute("carInfo", carInfo);
					
					//작성된 리뷰들(사용자가 방금 등록한 리뷰를 포함한)을 얻어와  request객체에 공유하고
					ArrayList<Review> reviewList = reviewListService.getReview();
					request.setAttribute("reviewList", reviewList);
					
					request.setAttribute("reviewPage", "/review/reviewTemplate.jsp");
					request.setAttribute("showReview", "/review/showReview.jsp");
					
					request.setAttribute("showMenu", "/kiosk/menuView.jsp");
					
					request.setAttribute("car_no", car_no);
					request.setAttribute("c_id", c_id);
					
					forward = new ActionForward("menuTemplate.jsp", false);
					
				}			
			}
			
		}
		
		return forward;
	}

}





