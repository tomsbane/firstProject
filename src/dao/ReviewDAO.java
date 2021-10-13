package dao;

import static db.JdbcUtil.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Review;

public class ReviewDAO {
	// 멤버변수(전역변수 : 전체 메서드에서 사용 가능)
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/***
	 * 싱글톤 패턴 : ReviewDAO객체 단 1개만 생성************************** 이유? 외부 클래스에서 "처음 생성된
	 * ReviewDAO객체를 공유해서 사용하도록 하기 위해"
	 */
	private ReviewDAO() {
	}

	private static ReviewDAO reviewDAO;

	// static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 BoardDAO객체를 1개만 만들도록
	// 하기 위해
	public static ReviewDAO getInstance() {
		if (reviewDAO == null) {// 객체가 없으면
			reviewDAO = new ReviewDAO();// 객체 생성
		}

		return reviewDAO;// 기존 객체의 주소 리턴
	}

	/************************************************************/

	public void setConnection(Connection con) {// Connection객체를 받아 DB 연결
		this.con = con;
	}
	
	//m_id로 review_table안의 리뷰정보를 조회하여 ArrayList<Review>타입으로 리턴
		public ArrayList<Review> selectReviewList(String m_id) {
			ArrayList<Review> reviewList = null;
			
			String sql="select * from review where m_id="+m_id;
			
			try {
				pstmt = con.prepareStatement(sql);						
				rs = pstmt.executeQuery();			
				
				if(rs.next()) {
					//기본값인 NULL로 채워진 ArrayList<Review>객체를 
					reviewList = new ArrayList<Review>();
					
					do {
						//"조회한 review정보값으로 채워진 Review객체" 생성하여 바로 ArrayList로 추가
						reviewList.add(new Review(rs.getInt("review_no"),
												  rs.getInt("customer_no"),
												  rs.getInt("car_no"),
												  rs.getString("review_name"),
												  rs.getString("review_contents"),
												  rs.getInt("review_rating")));
					}while(rs.next());
				}
			} catch (Exception e) {			
				System.out.println("selectReviewList 에러:"+ e);
			} finally {
				close(rs);
				close(pstmt);
			}	
			
			return reviewList;
		}
	
}
