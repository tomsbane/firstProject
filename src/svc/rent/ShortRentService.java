package svc.rent;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CarDAO;
import dao.OrderDAO;
import vo.Driver_detail;
import vo.Order;
import vo.Rentcar;


public class ShortRentService {

	public boolean insertOrder(Driver_detail driver, Order order) {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:DogDAO객체 생성
		OrderDAO orderDAO = OrderDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결
		orderDAO.setConnection(con);
		
		int insertOrderCount = orderDAO.insertOrder(order);
		int insertDriverCount = orderDAO.insertDriverDetail(driver);
		
		boolean isAllInsertResult = false;
		
		System.out.println("드라이버"+insertDriverCount);
		System.out.println("오더"+insertOrderCount);
		
		if(insertDriverCount>0 && insertOrderCount>0) {
			isAllInsertResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return isAllInsertResult;

	}

	public Rentcar getFinalInfo(int car_no) {

		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:DogDAO객체 생성
		CarDAO carDAO = CarDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결
		carDAO.setConnection(con);
		
		/*----------------------------------*/
		Rentcar carInfo = carDAO.viewCar(car_no);
		
		return carInfo;
		
	
	}

}
