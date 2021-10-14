//DB로 SQL구문을 전송하는 클래스
package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Driver_detail;
import vo.Order;

import static db.JdbcUtil.*;//static:모든 메서드들 미리 메모리에 올림

public class OrderDAO {
	// 멤버변수(전역변수 : 전체 메서드에서 사용 가능)
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/***
	 * 싱글톤 패턴 : DogDAO객체 단 1개만 생성************************** 이유? 외부 클래스에서 "처음 생성된ㅇ
	 * DogDAO객체를 공유해서 사용하도록 하기 위해"
	 */
	private OrderDAO() {
	}

	private static OrderDAO orderDAO;

	// static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 BoardDAO객체를 1개만 만들도록
	// 하기 위해
	public static OrderDAO getInstance() {
		if (orderDAO == null) {// 객체가 없으면
			orderDAO = new OrderDAO();// 객체 생성
		}

		return orderDAO;// 기존 객체의 주소 리턴
	}

	/************************************************************/

	public void setConnection(Connection con) {// Connection객체를 받아 DB 연결
		this.con = con;
	}

	public int insertOrder(Order order) {
		int insertOrderCount = 0;
		
		String sql="insert into order_car(c_id, car_no, rental_date, return_date, rental_price)"
				+ " value (?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, order.getC_id());
			pstmt.setInt(2, order.getCar_no());
			pstmt.setString(3, order.getRental_date());
			pstmt.setString(4, order.getReturn_date());
			pstmt.setInt(5, order.getRental_price());
			
			insertOrderCount=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("insertOrder 에러 :" + e);

		} finally {
			close(pstmt);
		}
		
		return insertOrderCount;
	}

	public int insertDriverDetail(Driver_detail driver) {
		int insertDriverCount = 0;
		
		String sql="insert into driver_detail value(?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, driver.getC_id());
			pstmt.setString(2, driver.getC_name());
			pstmt.setInt(3, driver.getC_birth());
			pstmt.setInt(4, driver.getC_tel());
			pstmt.setString(5, driver.getRental_place1());
			pstmt.setString(6, driver.getRental_place2());
			pstmt.setString(7, driver.getRental_place3());
			pstmt.setString(8, driver.getReturn_place1());
			pstmt.setString(9, driver.getReturn_place2());
			pstmt.setString(10, driver.getReturn_place3());
			pstmt.setString(11, driver.getRequest());
			
			insertDriverCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertDriverDetail 에러 :" + e);

		} finally {
			close(pstmt);
		}
		
		return insertDriverCount;
	}


}
