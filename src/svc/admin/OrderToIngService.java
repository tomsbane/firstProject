package svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.OrderDAO;
import vo.Order;

public class OrderToIngService {

	public boolean modifyOrderToIng(int order_no) {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:OrderDAO객체 생성
		OrderDAO orderDAO = OrderDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 OrderDAO의 멤버변수로 삽입하여 DB 연결
		orderDAO.setConnection(con);
		
		int orderModifyCount = orderDAO.modifyOrderGetToIng(order_no);

		boolean isOrderModifySuccess = false;

		if (orderModifyCount > 0) {
			isOrderModifySuccess = true;
			commit(con);
		} else {
			rollback(con);
		}
		close(con);

		return isOrderModifySuccess;
	}

}
