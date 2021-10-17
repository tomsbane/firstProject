package svc.review;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import dao.ReviewDAO;
import vo.Review;

public class ReviewListService {

	public ArrayList<Review> getReviewList() {

		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:DogDAO객체 생성
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결함
		reviewDAO.setConnection(con);

		ArrayList<Review> reviewList = reviewDAO.getReviewList(); 
		
		return reviewList;

	}

	public boolean writeReview(String c_id, String c_name, int car_no, String car_name, int rating, String text) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:ReviewDAO객체 생성
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		reviewDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
		int insertReviewCount = reviewDAO.insertReview(c_id, c_name, car_no, car_name, rating, text);
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		boolean isWriteReviewResult = false;
		if(insertReviewCount > 0) {
			isWriteReviewResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		//4.해제
		close(con);//Connection객체 해제
		return isWriteReviewResult;
	}
}
