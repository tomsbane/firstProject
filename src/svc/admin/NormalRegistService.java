package svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CustomerDAO;

public class NormalRegistService {

	public boolean setNormalRegist(String c_id) {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:CustomerDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);

		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
		int updateCustomerCount = customerDAO.normalUpdate(c_id);

		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		boolean isUpdateCustomerResult = false;
		if (updateCustomerCount > 0) {
			isUpdateCustomerResult = true;
			commit(con);
		} else {
			rollback(con);
		}

		// 4.해제
		close(con);// Connection객체 해제

		return isUpdateCustomerResult;
	}

}
