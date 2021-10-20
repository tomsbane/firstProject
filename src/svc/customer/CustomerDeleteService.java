package svc.customer;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CustomerDAO;

public class CustomerDeleteService {

	public boolean customerDelete(String c_id) {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:CustomerDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);

		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/
		int DeleteCustomerCount = customerDAO.DeleteCustomer(c_id);
		int DeleteAddrCount = customerDAO.DeleteAddr(c_id);
		boolean isDeleteResult = false;
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		if (DeleteCustomerCount>0 && DeleteAddrCount>0) {// 회원탈퇴에 성공하면
			isDeleteResult = true;
			commit(con);
		} else {
			rollback(con);
		}

		// 4.해제
		close(con);// Connection객체 해제

		return isDeleteResult;
	}

}
