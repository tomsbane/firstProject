package svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CarDAO;
import vo.Rentcar;

public class CarInsertService {
	
	public boolean registCar(Rentcar car){
		//1.커넥션 풀에서 Connection객체 얻어와
				Connection con = getConnection();
				//2.싱글톤 패턴:DogDAO객체 생성
				CarDAO carDAO = CarDAO.getInstance();
				//3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결
				carDAO.setConnection(con);
				
				/*-----DogDAO의 해당 메서드를 호출하여 처리--------------------------------*/
				int insertCount = carDAO.insertNewCar(car);
				
				/*--(update,delete,insert)성공하면 commit 실패하면 rollback
				 * (select 제외)--*/
				boolean isRegistSuccess = false;//지역변수 초기화
				if(insertCount > 0) {
					commit(con);
					isRegistSuccess = true;
				}else rollback(con);
				
				//4.해제
				close(con);//Connection객체 해제
				
				return isRegistSuccess;
	}
}
