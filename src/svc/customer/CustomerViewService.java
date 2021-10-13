package svc.customer;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CustomerDAO;
import vo.Address;
import vo.CustomerBean;

public class CustomerViewService {
	//멤버변수
	//생성자
	//메서드
	
	//customer_table안의 회원정보
	public CustomerBean getCustomer(String viewId){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:CustomerDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		CustomerBean customerInfo = customerDAO.getCustomerInfo(viewId);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/				
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return customerInfo;
	}
	
	//address_table안의 주소정보
	public Address getAddress(String viewId){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:CustomerDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		Address customerAddrInfo = customerDAO.getCustomerAddrInfo(viewId);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/				
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return customerAddrInfo;
	}
	
}
