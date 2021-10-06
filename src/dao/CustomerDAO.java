package dao;

import static db.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Address;
import vo.CustomerBean;


public class CustomerDAO {
		//멤버변수(전역변수 : 전체 메서드에서 사용 가능)
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			/*** 싱글톤 패턴 : UserDAO객체 단 1개만 생성**************************
			 * 이유? 외부 클래스에서 "처음 생성된 UserDAO객체를 공유해서 사용하도록 하기 위해" 
			 */
			private CustomerDAO(){}
			
			private static CustomerDAO customerDAO;
			//static이유? 객체를 생성하기 전에 이미 메모리에 올라간 getInstance()메서드를 통해서만UserDAO객체를 1개만 만들도록 하기 위해
			public static CustomerDAO getInstance(){
				if(customerDAO == null) {//객체가 없으면
					customerDAO = new CustomerDAO();//객체 생성
				}
				
				return customerDAO;//기존 객체의 주소 리턴
			}
			/************************************************************/
			
			public void setConnection(Connection con){//Connection객체를 받아 DB 연결
				this.con=con;
			}	
			
			//로그인 폼에서 입력한 id와 pw가 담긴 UserBean객체로 회원인지 조회 후 id를 반환
			public String selectLoginId(CustomerBean user){
				String loginId = null;
				String sql = "select u_id from user_table where u_id=? and u_password=?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user.getC_id());
					pstmt.setString(2, user.getC_password());
					rs = pstmt.executeQuery();
					
					if(rs.next()) {//회원가입이 되어있으면서 id와 pw를 정확히 입력했으면 "id를 리턴"
						loginId = rs.getString("c_id");
					}
				} catch (Exception e) {
					System.out.println("selectLoginId 에러:"+ e);
				} finally {
					close(rs);
					close(pstmt);
				}
				return loginId;//회원가입이 되어있으면 id를 리턴, 그렇지 않으면 null
			}
			
			//id로 조회한 회원정보 반환
			public CustomerBean getCustomerInfo(String c_id) {
				CustomerBean customerInfo = null;
				//사용자가 입력한 id 회원정보를 조회
				String sql = "select * from customer where c_id=?";
				//String sql = "select * from user_table where u_id=?" + u_id;
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, c_id);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {//해당 id에 대한 정보가 있으면
						//기본값으로 채워진  UserBean객체에 조회한 회원정보값으로 셋팅
						customerInfo=new CustomerBean();
						customerInfo.setC_id(rs.getString("c_id"));
						customerInfo.setC_grade(rs.getString("c_grade"));
						customerInfo.setC_name(rs.getString("c_name"));
						customerInfo.setC_email1(rs.getString("c_email1"));
						customerInfo.setC_email2(rs.getString("c_email2"));
						customerInfo.setC_tel(rs.getString("c_tel"));
					}
				} catch (Exception e) {
					System.out.println("getCustomerInfo 에러:"+ e);
				} finally {
					close(rs);
					close(pstmt);
				}
				return customerInfo;
			}
			
			//address_table안의 사용자 주소정보 조회하여 반환
			public Address getUserAddrInfo(String viewId) {
				Address userAddrInfo = null;

				String sql = "select postcode,address1,address2 from address_table where u_id=?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, viewId);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {//해당 id에 대한 정보가 있으면
						//기본값으로 채워진  Address객체에 조회한 주소정보값으로 셋팅
						userAddrInfo=new Address();
						userAddrInfo.setPostcode(rs.getInt("postcode"));
						userAddrInfo.setAddress1(rs.getString("address1"));
						userAddrInfo.setAddress2(rs.getString("address2"));
					}
				} catch (Exception e) {
					System.out.println("getUserAddrInfo 에러:"+ e);
				} finally {
					close(rs);
					close(pstmt);
				}
				return userAddrInfo;
			}
			
			//로그인한 사용자의 id로 지난달 구매한 금액 조회(이 때, status='get'(주문승인))
			public int getLastMonthMoney(String u_id) {
				int lastMonthMoney = 0;
				/*month(order_date)=month(2021-09-17)=9
				*month(order_date)=month(2021-08-27)=8
				*
				*month(now())-1 = month(2021-10-1)-1=10-9월(지난달)
				*즉, =(같다) "월이 같은 것을 찾아 totalmoney를 sum함"
				*/
				String sql = "select sum(totalmoney) from order_table"
				+ "where u_id=? and month(order_date)= month(now())-1 and order_status ='get'";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, u_id);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {//해당 id에 대한 정보가 있으면
						lastMonthMoney = rs.getInt("sum(totalmoney)");
					}
				} catch (Exception e) {
					System.out.println("getLastMonthMoney 에러:"+ e);
				} finally {
					close(rs);
					close(pstmt);
				}
				return lastMonthMoney;
			}
			//사용자의 등급을 "vip"로 업그레이드 
			public int upGrade(CustomerBean user) {
				int gradeCount = 0;//업데이트 성공 여부
				
				String sql = "update user_table set u_grade='Vip' where u_id=?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user.getU_id());
					gradeCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음
					
				} catch (Exception e) {
					System.out.println("upGrade 에러:"+ e);
				} finally {
					//close(rs);
					close(pstmt);
				}
				return gradeCount;
			}
			//사용자의 등급을 "Normal"로 다운 
			public int downGrade(CustomerBean user) {
				int gradeCount = 0;//업데이트 성공 여부
				
				String sql = "update user_table set u_grade='Normal' where u_id=?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user.getU_id());
					gradeCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음
					
				} catch (Exception e) {
					System.out.println("downGrade 에러:"+ e);
				} finally {
					//close(rs);
					close(pstmt);
				}
				return gradeCount;
			}
			public int insertUser(CustomerBean user) {
				int insertUserCount = 0;
				
				String sql = "insert into user_table(u_id,u_grade,u_password,u_name,u_email,u_call,j_joindate) values(?,?,?,?,?,?,now())";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user.getU_id());
					pstmt.setString(2, user.getU_grade());
					pstmt.setString(3, user.getU_password());
					pstmt.setString(4, user.getU_name());
					pstmt.setString(5, user.getU_email());
					pstmt.setString(6, user.getU_call());
					//pstmt.setString(7, user.getu_joindate());
					insertUserCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음
					
				} catch (Exception e) {
					System.out.println("insertUser 에러:"+ e);
				} finally {
					//close(rs);
					close(pstmt);
				}
				return insertUserCount;
			}
			//UserBean user 매개값인 이유:u_id
			public int insertAddr(Address addr, CustomerBean user) {
				int insertAddrCount = 0;
				
				String sql = "insert into address_table(u_id,postcode,address1,address2) values(?,?,?,?)";
				
				try {
					pstmt = con.prepareStatement(sql);
					//user에서
					pstmt.setString(1, user.getU_id());
					//addr에서 값을 가져옴
					pstmt.setInt(2, addr.getPostcode());
					pstmt.setString(3, addr.getAddress1());
					pstmt.setString(4, addr.getAddress2());
					
					insertAddrCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음
					
				} catch (Exception e) {
					System.out.println("insertAddr 에러:"+ e);
				} finally {
					//close(rs);
					close(pstmt);
				}
				return insertAddrCount;
			}
			
			//회원정보 수정 -user_table
			public int modifyUser(CustomerBean user) {
				int modifyUserCount = 0;
				String sql = "update user_table set u_name=?, u_email=?, u_call=? where u_id=?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user.getU_name());
					pstmt.setString(2, user.getU_email());
					pstmt.setString(3, user.getU_call());
					pstmt.setString(4, user.getU_id());

					modifyUserCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음
					
				} catch (Exception e) {
					System.out.println("modifyUser 에러:"+ e);
				} finally {
					//close(rs);
					close(pstmt);
				}
				return modifyUserCount;
			}
			//회원정보 수정 -address_table
			public int modifyAddr(Address addr, CustomerBean user) {
				int modifyAddrCount = 0;
				String sql = "update address_table set postcode=?, address1=?, address2=? where u_id=?";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, addr.getPostcode());
					pstmt.setString(2, addr.getAddress1());
					pstmt.setString(3, addr.getAddress2());
					pstmt.setString(4, user.getU_id());

					modifyAddrCount = pstmt.executeUpdate();//업데이트를 성공하면 1을 리턴받음
					
				} catch (Exception e) {
					System.out.println("modifyAddr 에러:"+ e);
				} finally {
					//close(rs);
					close(pstmt);
				}
				return modifyAddrCount;
			}
			
			
}
