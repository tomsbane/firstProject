package svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CarDAO;
import vo.Rentcar;

public class CarViewService {
	//해당 개 상품의 '조회수 1증가' -> 'id로 조회한 개상품정보를 Dog객체로 반환'
	public Rentcar getCarView(int no){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:CarDAO객체 생성
		CarDAO carDAO = CarDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 CarDAO의 멤버변수로 삽입하여 DB 연결
		carDAO.setConnection(con);
		
		 /*-(update,delete,insert)성공하면 commit 실패하면 rollback
		  * (select제외)---*/
		 
		 //'id'로 조회한 개상품정보를 Dog객체로 반환'
		 Rentcar car = carDAO.viewCar(no);
		 
		 /*------------------------------------------------------*/
		 
		//4.해제
		close(con);//Connection객체 해제
				
		return car;
	}
}
