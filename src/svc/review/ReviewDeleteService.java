package svc.review;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.ReviewDAO;

public class ReviewDeleteService {
	//멤버변수
	//기본생성자
	//메서드
	
	//해당 메뉴에 사용자가 작성한 리뷰를 찾아 DB에서 삭제
	public boolean deleteReview(int review_num, String c_id) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:ReviewDAO객체 생성
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		reviewDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
		int deleteReviewCount = reviewDAO.deleteReview(review_num, c_id);
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		boolean isdeleteReviewResult = false;
		if(deleteReviewCount > 0) {
			isdeleteReviewResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		//4.해제
		close(con);//Connection객체 해제
		return isdeleteReviewResult;
	}
}
