package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import vo.Address;
import vo.CustomerBean;

public class CustomerDAO {
	// 멤버변수(전역변수 : 전체 메서드에서 사용 가능)
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	/***
	 * 싱글톤 패턴 : CustomerDAO객체 단 1개만 생성************************** 이유? 외부 클래스에서 "처음
	 * 생성된 CustomerDAO객체를 공유해서 사용하도록 하기 위해"
	 */
	private CustomerDAO() {
	}

	private static CustomerDAO customerDAO;

	// static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만CustomerDAO객체를 1개만
	// 만들도록 하기 위해
	public static CustomerDAO getInstance() {
		if (customerDAO == null) {// 객체가 없으면
			customerDAO = new CustomerDAO();// 객체 생성
		}

		return customerDAO;// 기존 객체의 주소 리턴
	}

	/************************************************************/

	public void setConnection(Connection con) {// Connection객체를 받아 DB 연결
		this.con = con;
	}

	// 로그인 폼에서 입력한 id와 pw가 담긴 CustomerBean객체로 회원인지 조회 후 id를 반환
	public String selectLoginId(CustomerBean customer) {
		String loginId = null;
		String sql = "select c_id from customer where c_id=? and c_password=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getC_id());
			pstmt.setString(2, customer.getC_password());
			rs = pstmt.executeQuery();

			if (rs.next()) {// 회원가입이 되어있으면서 id와 pw를 정확히 입력했으면 "id를 리턴"
				loginId = rs.getString("c_id");
			}
		} catch (Exception e) {
			System.out.println("selectLoginId 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return loginId;// 회원가입이 되어있으면 id를 리턴, 그렇지 않으면 null
	}

	// id로 조회한 회원정보 반환
	public CustomerBean getCustomerInfo(String c_id) {
		CustomerBean customerInfo = null;
		// 사용자가 입력한 id 회원정보를 조회
		String sql = "select * from customer where c_id=?";
		// String sql = "select * from customer_table where c_id=?" + c_id;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 id에 대한 정보가 있으면
				// 기본값으로 채워진 CustomerBean객체에 조회한 회원정보값으로 셋팅
				customerInfo = new CustomerBean();
				customerInfo.setC_id(rs.getString("c_id"));
				customerInfo.setC_grade(rs.getString("c_grade"));
				customerInfo.setC_name(rs.getString("c_name"));
				customerInfo.setC_gender(rs.getString("c_gender"));
				customerInfo.setC_birth(rs.getString("c_birth"));
				customerInfo.setC_email1(rs.getString("c_email1"));
				customerInfo.setC_email2(rs.getString("c_email2"));
				customerInfo.setC_tel(rs.getString("c_tel"));
			}
		} catch (Exception e) {
			System.out.println("getCustomerInfo 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return customerInfo;
	}

	// address_table안의 사용자 주소정보 조회하여 반환
	public Address getCustomerAddrInfo(String viewId) {
		Address customerAddrInfo = null;

		String sql = "select postcode,address1,address2 from address where c_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, viewId);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 id에 대한 정보가 있으면
				// 기본값으로 채워진 Address객체에 조회한 주소정보값으로 셋팅
				customerAddrInfo = new Address();
				customerAddrInfo.setPostcode(rs.getInt("postcode"));
				customerAddrInfo.setAddress1(rs.getString("address1"));
				customerAddrInfo.setAddress2(rs.getString("address2"));
			}
		} catch (Exception e) {
			System.out.println("getCustomerAddrInfo 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return customerAddrInfo;
	}

	// 로그인한 사용자의 id로 지난달 구매한 금액 조회(이 때, status='get'(주문승인))

	/*
	 * public int getLastMonthMoney(String c_id) { int lastMonthMoney = 0;
	 */
	/*
	 * month(order_date)=month(2021-09-17)=9 month(order_date)=month(2021-08-27)=8
	 *
	 * month(now())-1 = month(2021-10-1)-1=10-9월(지난달) 즉, =(같다)
	 * "월이 같은 것을 찾아 totalmoney를 sum함"
	 */
	/*
	 * String sql = "select sum(totalmoney) from order_table" +
	 * "where c_id=? and month(order_date)= month(now())-1 and order_status ='get'";
	 * 
	 * try { pstmt = con.prepareStatement(sql); pstmt.setString(1, c_id); rs =
	 * pstmt.executeQuery();
	 * 
	 * if(rs.next()) {//해당 id에 대한 정보가 있으면 lastMonthMoney =
	 * rs.getInt("sum(totalmoney)"); } } catch (Exception e) {
	 * System.out.println("getLastMonthMoney 에러:"+ e); } finally { close(rs);
	 * close(pstmt); } return lastMonthMoney; }
	 */
	// 사용자의 등급을 "vip"로 업그레이드
	public int upGrade(CustomerBean customer) {
		int gradeCount = 0;// 업데이트 성공 여부

		String sql = "update customer_table set c_grade='Vip' where c_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getC_id());
			gradeCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("upGrade 에러:" + e);
		} finally {
			// close(rs);
			close(pstmt);
		}
		return gradeCount;
	}

	// 사용자의 등급을 "Normal"로 다운
	public int downGrade(CustomerBean customer) {
		int gradeCount = 0;// 업데이트 성공 여부

		String sql = "update customer_table set c_grade='Normal' where c_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getC_id());
			gradeCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("downGrade 에러:" + e);
		} finally {
			// close(rs);
			close(pstmt);
		}
		return gradeCount;
	}

	public int insertCustomer(CustomerBean customer) {
		int insertCustomerCount = 0;

		String sql = "insert into customer(c_id,c_grade,c_password,c_name,c_gender,c_birth,c_email1,c_email2,c_tel) values(?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getC_id());
			pstmt.setString(2, customer.getC_grade());
			pstmt.setString(3, customer.getC_password());
			pstmt.setString(4, customer.getC_name());
			pstmt.setString(5, customer.getC_gender());
			pstmt.setString(6, customer.getC_birth());
			pstmt.setString(7, customer.getC_email1());
			pstmt.setString(8, customer.getC_email2());
			pstmt.setString(9, customer.getC_tel());
			// pstmt.setString(7, customer.getc_joindate());
			insertCustomerCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("insertCustomer 에러:" + e);
		} finally {
			// close(rs);
			close(pstmt);
		}
		return insertCustomerCount;
	}

	// CustomerBean customer 매개값인 이유:c_id
	public int insertAddr(Address addr, CustomerBean customer) {
		int insertAddrCount = 0;

		String sql = "insert into address(c_id,postcode,address1,address2) values(?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);
			// customer에서
			pstmt.setString(1, customer.getC_id());
			// addr에서 값을 가져옴
			pstmt.setInt(2, addr.getPostcode());
			pstmt.setString(3, addr.getAddress1());
			pstmt.setString(4, addr.getAddress2());

			insertAddrCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("insertAddr 에러:" + e);
		} finally {
			// close(rs);
			close(pstmt);
		}
		return insertAddrCount;
	}

	// 회원정보 수정 -customer_table
	public int modifyCustomer(CustomerBean customer) {
		int modifyCustomerCount = 0;
		String sql = "update customer set c_name=?, c_email1=?,c_email2=?, c_tel=? where c_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer.getC_name());
			pstmt.setString(2, customer.getC_email1());
			pstmt.setString(3, customer.getC_email2());
			pstmt.setString(4, customer.getC_tel());
			pstmt.setString(5, customer.getC_id());

			modifyCustomerCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("modifyCustomer 에러:" + e);
		} finally {
			// close(rs);
			close(pstmt);
		}
		return modifyCustomerCount;
	}

	// 회원정보 수정 -address_table
	public int modifyAddr(Address addr, CustomerBean customer) {
		int modifyAddrCount = 0;
		String sql = "update address set postcode=?, address1=?, address2=? where c_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, addr.getPostcode());
			pstmt.setString(2, addr.getAddress1());
			pstmt.setString(3, addr.getAddress2());
			pstmt.setString(4, customer.getC_id());

			modifyAddrCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("modifyAddr 에러:" + e);
		} finally {
			// close(rs);
			close(pstmt);
		}
		return modifyAddrCount;
	}

	public ArrayList<CustomerBean> customerList() {
		ArrayList<CustomerBean> customerList = null;
		// 사용자가 입력한 id 회원정보를 조회
		String sql = "select c_id,c_grade,c_name,c_email1,c_email2,c_tel,c_joindate from customer";
		// String sql = "select * from customer_table where c_id=?" + c_id;

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 id에 대한 정보가 있으면
				customerList = new ArrayList<CustomerBean>();
				do {
					customerList.add(new CustomerBean(rs.getString("c_id"),
													  rs.getString("c_grade"),
													  rs.getString("c_name"),
												 	  rs.getString("c_email1"),
													  rs.getString("c_email2"),
													  rs.getString("c_tel"),
													  rs.getString("c_joindate") 
									));
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("customerList 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return customerList;
	}

	public ArrayList<CustomerBean> selectCustomer(String c_id) {
		ArrayList<CustomerBean> customerList = null;
		// 사용자가 입력한 id 회원정보를 조회
		String sql = "select c_id,c_grade,c_name,c_email1,c_email2,c_tel,c_joindate from customer where c_id like ?";
		// String sql = "select * from customer_table where c_id=?" + c_id;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, '%'+c_id+'%');
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 id에 대한 정보가 있으면
				customerList = new ArrayList<CustomerBean>();
				do {
					customerList.add(new CustomerBean(rs.getString("c_id"),
													  rs.getString("c_grade"),
													  rs.getString("c_name"),
												 	  rs.getString("c_email1"),
													  rs.getString("c_email2"),
													  rs.getString("c_tel"),
													  rs.getString("c_joindate")
									));
				}while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("selectCustomer 에러:" + e);
		} finally {
			close(rs);
			close(pstmt);
		}
		return customerList;
	}

	public int normalUpdate(String c_id) {
		int normalUpdateCount = 0;
		//방법-1:기존의 이미지를 그대로 사용하려면
		String sql="update customer set c_grade='normal' where c_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_id);			
			
			normalUpdateCount = pstmt.executeUpdate();		
			
		} catch (Exception e) {			
			System.out.println("adminUpdate 에러:"+ e);
		} finally {
			//close(rs);
			close(pstmt);
		}			
		
		return normalUpdateCount;
	}

	public int adminUpdate(String c_id) {
		int adminUpdateCount = 0;
		//방법-1:기존의 이미지를 그대로 사용하려면
		String sql="update customer set c_grade='admin' where c_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_id);			
			
			adminUpdateCount = pstmt.executeUpdate();		
			
		} catch (Exception e) {			
			System.out.println("adminUpdate 에러:"+ e);
		} finally {
			//close(rs);
			close(pstmt);
		}			
		
		return adminUpdateCount;
	}

	public int DeleteCustomer(String c_id) {
		int DeleteCustomerCount = 0;
		String sql = "delete from customer where c_id=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, c_id);

			DeleteCustomerCount = pstmt.executeUpdate();// 업데이트를 성공하면 1을 리턴받음

		} catch (Exception e) {
			System.out.println("DeleteCustomer 에러:" + e);
		} finally {
			// close(rs);
			close(pstmt);
		}
		return DeleteCustomerCount;
	}


}
