package svc.rent;

import static db.JdbcUtil.getConnection;

import java.sql.Connection;
import java.util.ArrayList;

import dao.CarDAO;
import vo.Rentcar;

public class SelectSmallSizeService {

	public ArrayList<Rentcar> getSmallSizeList() {
		// 1.커넥션 풀에서 Connection객체 얻어와
		Connection con = getConnection();
		// 2.싱글톤 패턴:DogDAO객체 생성
		CarDAO carDAO = CarDAO.getInstance();
		// 3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결함
		carDAO.setConnection(con);

		ArrayList<Rentcar> smallSizeList = carDAO.selectSmallSizeList();

		return smallSizeList;
	}

}
