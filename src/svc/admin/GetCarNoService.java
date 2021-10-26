package svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.io.PrintWriter;
import java.sql.Connection;

import dao.OrderDAO;
import svc.rent.SelectReserveService;
import svc.rent.ShortRentService;
import vo.ActionForward;

public class GetCarNoService {

	public int getCarNo(int order_no) {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:OrderDAO객체 생성
		OrderDAO orderDAO = OrderDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 OrderDAO의 멤버변수로 삽입하여 DB 연결
		orderDAO.setConnection(con);

		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
		int car_no = orderDAO.getCarNo(order_no);
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		
		// 4.해제
		close(con);// Connection객체 해제

		return car_no;
	}
	
}
