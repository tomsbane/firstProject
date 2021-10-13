package svc.customer;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CustomerDAO;
import vo.CustomerBean;
public class CustomerLoginService {
	//멤버변수
	//생성자
	//메서드
	
	//회원 여부
	public boolean login(CustomerBean customer){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:CustomerDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		String loginId = customerDAO.selectLoginId(customer);
		
		boolean isloginResult = false;
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		if(loginId != null) {//회원가입이 되어있으면서 id와 pw를 정확히 입력했으면
			isloginResult = true;
		}		
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return isloginResult;
	}

	//id로 회원정보 가져오기
	public CustomerBean getCustomerInfo(String u_id) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:CustomerDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 CustomerDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		CustomerBean customerInfo = customerDAO.getCustomerInfo(u_id);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/				
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return customerInfo;
		
	}
}




