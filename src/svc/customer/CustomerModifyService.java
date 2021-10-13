package svc.customer;

import static db.JdbcUtil.close;
import static db.JdbcUtil.commit;
import static db.JdbcUtil.getConnection;
import static db.JdbcUtil.rollback;

import java.sql.Connection;

import dao.CustomerDAO;
import vo.Address;
import vo.CustomerBean;

public class CustomerModifyService {
	//멤버변수
	//생성자
	//메서드
	
	//회원 여부
	public boolean customerModify(CustomerBean customer, Address addr){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:CustomerDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		int modifyCustomerCount = customerDAO.modifyCustomer(customer);
		int modifyAddrCount = customerDAO.modifyAddr(addr, customer);
		
		boolean isModifyResult = false;
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		if(modifyCustomerCount>0 && modifyAddrCount>0) {//회원가입에 성공하면
			isModifyResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return isModifyResult;
	}
}