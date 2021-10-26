package dao;

import static db.JdbcUtil.*;

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

	public ArrayList<Review> getReviewList() {
		ArrayList<Review> reviewList = null;
		
		try {
			pstmt = con.prepareStatement("select * from review");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				reviewList = new ArrayList<Review>();
				
				do {
					reviewList.add(new Review(rs.getInt("review_num"),
											  rs.getString("c_id"),
											  rs.getString("c_name"),
											  rs.getInt("car_no"),
											  rs.getString("car_name"),
											  rs.getInt("rating"),
											  rs.getString("text")));							 
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getReviewList 에러 :" + e);
		} finally {
			if (rs != null) try { close(rs); } catch(Exception ex) {}
		    if (pstmt != null) try { close(pstmt); } catch(Exception ex) {}
		}

		return reviewList;
	}

	public int insertReview(String c_id, String c_name, int car_no, String car_name, int rating, String text) {
		int insertReviewCount = 0;
		String sql="insert into review(c_id, c_name, car_no, car_name, rating, text) values(?, ?, ?, ?, ?, ?)";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, c_id);
			pstmt.setString(2, c_name);
			pstmt.setInt(3, car_no);
			pstmt.setString(4, car_name);
			pstmt.setInt(5, rating);
			pstmt.setString(6, text);
			
			insertReviewCount= pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertReview 에러 :" + e);
		}finally {
			if (rs != null) try { close(rs); } catch(Exception ex) {}
		    if (pstmt != null) try { close(pstmt); } catch(Exception ex) {}
		}
		
		return insertReviewCount;
	}

	public int deleteReview(int review_num, String c_id) {
		int deleteReviewCount = 0;
		String sql="delete from review_table WHERE review_num=? AND c_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setInt(1, review_num);						
			pstmt.setString(2, c_id);
			
			deleteReviewCount = pstmt.executeUpdate();//업데이트가 성공하면 1리턴	
			
				
		} catch (Exception e) {			
			System.out.println("deleteReview 에러:"+ e);
		} finally {
			if (rs != null) try { close(rs); } catch(Exception ex) {}
		    if (pstmt != null) try { close(pstmt); } catch(Exception ex) {}
		}	
		return deleteReviewCount;
	}

}
