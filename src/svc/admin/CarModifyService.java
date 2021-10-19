package svc.admin;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.CarDAO;
import vo.Rentcar;

public class CarModifyService {

	public boolean carModify(Rentcar car) {
		//1.커넥션 풀에서 Connection객체 얻어와
				Connection con = getConnection();
				//2.싱글톤 패턴:CarDAO객체 생성
				CarDAO carDAO = CarDAO.getInstance();
				//3.DB작업에 사용될 Connection객체를 CarDAO의 멤버변수로 삽입하여 DB 연결
				carDAO.setConnection(con);
				
				/*----DAO의 해당 메서드를 호출하여 처리-------------------*/		
				int modifyCarCount = carDAO.modifyCar(car);
				
				boolean isModifyCarResult = false;
				/*-(update,delete,insert)성공하면 commit 실패하면 rollback
				 * (select제외)----*/
				if(modifyCarCount>0) {//회원가입에 성공하면
					isModifyCarResult = true;
					commit(con);
				}else {
					rollback(con);
				}
				
				//4.해제
				close(con);//Connection객체 해제		
				
				return isModifyCarResult;
			}
	}


