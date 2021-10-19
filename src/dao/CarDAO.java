//DB로 SQL구문을 전송하는 클래스
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Rentcar;

import static db.JdbcUtil.*;//static:모든 메서드들 미리 메모리에 올림

public class CarDAO {
	// 멤버변수(전역변수 : 전체 메서드에서 사용 가능)
	Connection con = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	/***
	 * 싱글톤 패턴 : DogDAO객체 단 1개만 생성************************** 이유? 외부 클래스에서 "처음 생성된ㅇ
	 * DogDAO객체를 공유해서 사용하도록 하기 위해"
	 */
	private CarDAO() {
	}

	private static CarDAO carDAO;

	// static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만 BoardDAO객체를 1개만 만들도록
	// 하기 위해
	public static CarDAO getInstance() {
		if (carDAO == null) {// 객체가 없으면
			carDAO = new CarDAO();// 객체 생성
		}

		return carDAO;// 기존 객체의 주소 리턴
	}

	/************************************************************/

	public void setConnection(Connection con) {// Connection객체를 받아 DB 연결
		this.con = con;
	}

	public ArrayList<Rentcar> selectCarList() {
		ArrayList<Rentcar> carList = null;

		try {
			psmt = con.prepareStatement("select * from rentcar");
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				carList = new ArrayList<Rentcar>();
				do {
					Rentcar rentcar = new Rentcar(
						rs.getInt("car_no"),
						rs.getString("car_name"),
						rs.getString("car_group"),
						rs.getInt("car_year"),
						rs.getString("car_reserve"),
						rs.getInt("car_price"),
						rs.getString("car_brand"),
						rs.getString("car_image"),
						rs.getInt("car_readCount"));
					
					carList.add(rentcar);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectCarList 에러 :" + e);
		}finally {
			close(rs);
			close(psmt);
		}

		return carList;
	}
	public ArrayList<Rentcar> selectReserveCarList() {
		ArrayList<Rentcar> carList = null;
		
		try {
			psmt = con.prepareStatement("select * from rentcar where car_reserve='n'");
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				carList = new ArrayList<Rentcar>();
				do {
					Rentcar rentcar = new Rentcar(
							rs.getInt("car_no"),
							rs.getString("car_name"),
							rs.getString("car_group"),
							rs.getInt("car_year"),
							rs.getString("car_reserve"),
							rs.getInt("car_price"),
							rs.getString("car_brand"),
							rs.getString("car_image"),
							rs.getInt("car_readCount"));
					
					carList.add(rentcar);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectCarList 에러 :" + e);
		}finally {
			close(rs);
			close(psmt);
		}
		
		return carList;
	}
	public int insertNewCar(Rentcar newCar){
		int insertCount = 0;
		 
		String sql = "INSERT INTO rentcar VALUES(null,?,?,?,?,?,?,?,?)"; 
		
		try {
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, newCar.getCar_name());
			psmt.setString(2, newCar.getCar_group());
			psmt.setInt(3, newCar.getCar_year());
			psmt.setString(4, newCar.getCar_reserve());
			psmt.setInt(5, newCar.getCar_price());
			psmt.setString(6, newCar.getCar_brand());
			psmt.setString(7, newCar.getCar_image());
			psmt.setInt(8, newCar.getCar_readCount());
			
			insertCount = psmt.executeUpdate();//업데이트가 성공하면 1리턴받음
			
		} catch (Exception e) {					
			System.out.println("insertNewCar 에러 :" + e);//e:예외종류+예외메세지
		}finally {
			close(psmt);			
		}
		
		return insertCount;
		
	}
	
	public Rentcar viewCar(int car_no) {
		Rentcar carInfo = null;
		String sql="select car_no,car_name,car_group,car_year,car_reserve,car_price,car_brand,car_image from rentcar where car_no=?";
		
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, car_no);
			rs= psmt.executeQuery();
			
			if(rs.next()) {
				carInfo = new Rentcar();
				carInfo.setCar_no(rs.getInt("car_no"));
				carInfo.setCar_name(rs.getString("car_name"));
				carInfo.setCar_group(rs.getString("car_group"));
				carInfo.setCar_year(rs.getInt("car_year"));
				carInfo.setCar_reserve(rs.getString("car_reserve"));
				carInfo.setCar_price(rs.getInt("car_price"));
				carInfo.setCar_brand(rs.getString("car_brand"));
				carInfo.setCar_image(rs.getString("car_image"));
			}
			
		}catch(Exception e) {
			System.out.println("viewCar 에러 : "+e);
		}finally {
			close(con);
			close(psmt);
		}
		return carInfo;
		
		
	}

	public int carReserveUpdate(int car_no, String car_reserve) {
		int carReserveUpdateCount = 0;
		//방법-1:기존의 이미지를 그대로 사용하려면
		String sql="";
		
		if(car_reserve.equals("y")) {
			sql="update rentcar set car_reserve='n' where car_no=?";
		}else {
			sql="update rentcar set car_reserve='y' where car_no=?";
		}

		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, car_no);
			carReserveUpdateCount = psmt.executeUpdate();		
			
		} catch (Exception e) {			
			System.out.println("carReserveUpdate 에러:"+ e);
		} finally {
			//close(rs);
			close(psmt);
		}			
		
		return carReserveUpdateCount;
	}

	public int deleteCar(int car_no) {
		int deleteCarCount = 0;
		String sql = "delete from rentcar where car_no=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, car_no);
			
			deleteCarCount=psmt.executeUpdate();
			
		}catch (Exception e) {
			System.out.println("deleteCar 에러"+e);
		}finally {
			close(psmt);
		}
		return deleteCarCount;
	}

	public ArrayList<Rentcar> selectSmallSizeList() {
		ArrayList<Rentcar> carList = null;

		try {
			psmt = con.prepareStatement("select * from rentcar where car_group='경형' ");
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				carList = new ArrayList<Rentcar>();
				do {
					Rentcar rentcar = new Rentcar(
						rs.getInt("car_no"),
						rs.getString("car_name"),
						rs.getString("car_group"),
						rs.getInt("car_year"),
						rs.getString("car_reserve"),
						rs.getInt("car_price"),
						rs.getString("car_brand"),
						rs.getString("car_image"),
						rs.getInt("car_readCount"));
					
					carList.add(rentcar);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectSmallSizeList 에러 :" + e);
		}finally {
			close(rs);
			close(psmt);
		}

		return carList;
	}

	public ArrayList<Rentcar> selectMidSizeList() {
		ArrayList<Rentcar> carList = null;

		try {
			psmt = con.prepareStatement("select * from rentcar where car_group='중소형' ");
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				carList = new ArrayList<Rentcar>();
				do {
					Rentcar rentcar = new Rentcar(
						rs.getInt("car_no"),
						rs.getString("car_name"),
						rs.getString("car_group"),
						rs.getInt("car_year"),
						rs.getString("car_reserve"),
						rs.getInt("car_price"),
						rs.getString("car_brand"),
						rs.getString("car_image"),
						rs.getInt("car_readCount"));
					
					carList.add(rentcar);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectMidSizeList 에러 :" + e);
		}finally {
			close(rs);
			close(psmt);
		}

		return carList;
	}

	public ArrayList<Rentcar> selectFullSizeList() {
		ArrayList<Rentcar> carList = null;

		try {
			psmt = con.prepareStatement("select * from rentcar where car_group='대형' ");
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				carList = new ArrayList<Rentcar>();
				do {
					Rentcar rentcar = new Rentcar(
						rs.getInt("car_no"),
						rs.getString("car_name"),
						rs.getString("car_group"),
						rs.getInt("car_year"),
						rs.getString("car_reserve"),
						rs.getInt("car_price"),
						rs.getString("car_brand"),
						rs.getString("car_image"),
						rs.getInt("car_readCount"));
					
					carList.add(rentcar);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectFullSizeList 에러 :" + e);
		}finally {
			close(rs);
			close(psmt);
		}

		return carList;
	}

	public ArrayList<Rentcar> selectOverseasList() {
		ArrayList<Rentcar> carList = null;

		try {
			psmt = con.prepareStatement("select * from rentcar where car_group='수입' ");
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				carList = new ArrayList<Rentcar>();
				do {
					Rentcar rentcar = new Rentcar(
						rs.getInt("car_no"),
						rs.getString("car_name"),
						rs.getString("car_group"),
						rs.getInt("car_year"),
						rs.getString("car_reserve"),
						rs.getInt("car_price"),
						rs.getString("car_brand"),
						rs.getString("car_image"),
						rs.getInt("car_readCount"));
					
					carList.add(rentcar);
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectOverseasList 에러 :" + e);
		}finally {
			close(rs);
			close(psmt);
		}

		return carList;
	}

	public int modifyCar(Rentcar car) {
		int modifyCarCount = 0;
		String car_image=car.getCar_image();
		String sql = "update rentcar set car_name=? ,car_group=? ,car_year=? ,car_reserve=? ,car_price=? ,car_brand=?";
		if(car_image != null) {
		sql += ", car_image='"+ car_image +"'";
		}
		sql += " where car_no=?";
		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, car.getCar_name());
			psmt.setString(2, car.getCar_group());
			psmt.setInt(3, car.getCar_year());
			psmt.setString(4, car.getCar_reserve());
			psmt.setInt(5, car.getCar_price());
			psmt.setString(6, car.getCar_brand());
			psmt.setInt(7, car.getCar_no());
			modifyCarCount = psmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("modifyCar 에러:"+ e);
		}
		finally {
			close(psmt);
		}
		
		return modifyCarCount;
	}
}
