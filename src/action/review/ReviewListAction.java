package action.review;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import action.Action;
import svc.review.ReviewListService;
import vo.ActionForward;
import vo.Review;

public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		ReviewListService reveiwService = new ReviewListService();
		ArrayList<Review> reviewList =reveiwService.getReviewList();
		
		request.setAttribute("reviewList", reviewList);
		
		forward = new ActionForward("review/showReview.jsp", false);
		
		return forward;
	}

}
