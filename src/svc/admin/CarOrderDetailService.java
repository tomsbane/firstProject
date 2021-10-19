package svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CustomerDAO;
import dao.OrderDAO;
import vo.Order;

public class CarOrderDetailService {

	public  getOrderDetail() {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:CustomerDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);
		
		ArrayList<Order> orderDetail = OrderDAO.selectOrderDetail();
		
		close(con);
		
		return orderDetail;
	}

}
