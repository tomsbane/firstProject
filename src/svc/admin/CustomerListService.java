package svc.admin;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CustomerDAO;
import vo.CustomerBean;

public class CustomerListService {
	public ArrayList<CustomerBean> getCustomerList(){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:DogDAO객체 생성
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결
		customerDAO.setConnection(con);
		
		/*-----DogDAO의 해당 메서드를 호출하여 처리--------------------------------*/
		ArrayList<CustomerBean> customerList = customerDAO.customerList();
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		
		//4.해제
		close(con);//Connection객체 해제
		
		return customerList;
	}
}
