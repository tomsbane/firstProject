//개 상품 목록보기 요청을 처리하는 비지니스 로직을 구현하는 Service클래스
package svc.admin;
import static db.JdbcUtil.*;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import vo.Rentcar;
public class CarListService {
	
	public ArrayList<Rentcar> getCarList(){
		//1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴:DogDAO객체 생성
		CarDAO carDAO = CarDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결
		carDAO.setConnection(con);
		
		/*-----DogDAO의 해당 메서드를 호출하여 처리--------------------------------*/
		ArrayList<Rentcar> carList = carDAO.selectCarList();
		
		/*-(update,delete,insert)성공하면 commit 실패하면 rollback
		 * (select제외)----*/
		
		//4.해제
		close(con);//Connection객체 해제
		
		return carList;
	}
	
}
