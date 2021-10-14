package svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CarDAO;

public class ReserveChangeService {

	public boolean setreserveCar(int car_no, String car_reserve) {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:CarDAO객체 생성
		CarDAO carDAO = CarDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 CarDAO의 멤버변수로 삽입하여 DB 연결
		carDAO.setConnection(con);

		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
		int updatecarReserveCount = carDAO.carReserveUpdate(car_no, car_reserve);

		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		boolean isUpdatecarReserveResult = false;
		if (updatecarReserveCount > 0) {
			isUpdatecarReserveResult = true;
			commit(con);
		} else {
			rollback(con);
		}

		// 4.해제
		close(con);// Connection객체 해제

		return isUpdatecarReserveResult;
	}

}
