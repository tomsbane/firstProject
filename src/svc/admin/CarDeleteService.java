package svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CarDAO;

public class CarDeleteService {

	public boolean deleteCar(int car_no) {
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:CarDAO객체 생성
		CarDAO carDAO = CarDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 CarDAO의 멤버변수로 삽입하여 DB 연결
		carDAO.setConnection(con);
		
		/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
		int deleteCarCount = carDAO.deleteCar(car_no);
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/	
		boolean isDeleteCarResult = false;
		if(deleteCarCount > 0) {
			isDeleteCarResult = true;
			commit(con);
		}else {
			rollback(con);
		}
		
		//4.해제
		close(con);//Connection객체 해제		
		
		return isDeleteCarResult;
	}

}
