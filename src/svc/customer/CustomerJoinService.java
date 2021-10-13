package svc.customer;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CustomerDAO;
import vo.Address;
import vo.CustomerBean;

public class CustomerJoinService {
	//멤버변수
	//생성자
	//메서드
	
	//회원 여부
	public boolean customerJoin(CustomerBean customer, Address addr){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:CustomerDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		int insertCustomerCount = customerDAO.insertCustomer(customer);
		int insertAddrCount = customerDAO.insertAddr(addr, customer);
		
		boolean isJoinResult = false;
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		if(insertCustomerCount>0 && insertAddrCount>0) {//회원가입에 성공하면
			isJoinResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return isJoinResult;
	}
}
