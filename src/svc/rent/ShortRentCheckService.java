package svc.rent;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CarDAO;
import vo.Rentcar;

public class ShortRentCheckService {

	public Rentcar getShortRentInfo(int car_no) {

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
