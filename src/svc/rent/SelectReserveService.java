package svc.rent;

import static db.JdbcUtil.close;
import static db.JdbcUtil.getConnection;

import java.sql.Connection;

import dao.CarDAO;
import vo.Rentcar;

public class SelectReserveService {

	public String getReserve(int car_no) {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:DogDAO객체 생성
		CarDAO carDAO = CarDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결함
		carDAO.setConnection(con);

		String car_reserve = carDAO.getReserve(car_no);
		//4.해제
		close(con);//Connection객체 해제	
		return car_reserve;
	}

}
