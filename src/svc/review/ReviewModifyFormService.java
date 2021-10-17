package svc.review;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.ReviewDAO;
import vo.Review;

public class ReviewModifyFormService {
	//멤버변수
	//생성자
	//메서드
	
	//해당 리뷰번호(review_num)로 '원본 리뷰의 정보'를 조회하여 리턴
	public Review getReview(int review_num){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:ReviewDAO객체 생성
		ReviewDAO reviewDAO = ReviewDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 MenuDAO의 멤버변수로 삽입하여 DB 연결
		reviewDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		Review reviewInfo = reviewDAO.selectReview(review_num);//메뉴에 대한 리뷰정보를 얻어어
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/				
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return reviewInfo;
	}
}
