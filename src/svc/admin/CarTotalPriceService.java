package svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.OrderDAO;
import vo.Order;

public class CarTotalPriceService {

	public String getcarTotalPrice(String year, String month) {
		// 1.커넥션 풀에서 Connection객체 얻어와
				Connection con = getConnection();
				// 2.싱글톤 패턴:OrderDAO객체 생성
				OrderDAO orderDAO = OrderDAO.getInstance();
				// 3.DB작업에 사용될 Connection객체를 OrderDAO의 멤버변수로 삽입하여 DB 연결
				orderDAO.setConnection(con);

				/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
				String carTotalMoeny= orderDAO.selectCarTotalMoney(year, month);

				/*-(update,delete,insert)성공하면 commit 실패하면 rollback
				 * (select제외)----*/

				// 4.해제
				close(con);// Connection객체 해제

				return carTotalMoeny;
	}

}
